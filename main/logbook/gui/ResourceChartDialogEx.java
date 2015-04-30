package logbook.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Platform;
import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import logbook.config.AppConfig;
import logbook.constants.AppConstants;
import logbook.gui.listener.SaveWindowLocationAdapter;
import logbook.gui.logic.CreateReportLogic;
import logbook.gui.logic.LayoutLogic;
import logbook.gui.logic.TableItemCreator;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * 資材チャートのダイアログ
 *
 */
public final class ResourceChartDialogEx extends Dialog {

    static {
        Platform.setImplicitExit(false);
    }
    /** ロガー */
    private static final Logger LOG = LogManager.getLogger(ResourceChartDialogEx.class);
    /** スケールテキスト */
    private static final String[] SCALE_TEXT = { "1日", "1週間", "2週間", "1ヶ月", "2ヶ月", "3ヶ月", "半年", "1年" };
    /** スケールテキストに対応する日 */
    private static final int[] SCALE_DAYS = { 0, 6, 13, 29, 59, 89, 179, 364 };
    /** 日付の表示パターン */
    private static final String DATE_PATTERN = "M月d日HH:mm";
    /** 資材テーブルに表示する資材のフォーマット */
    private static final String COMPARE_FORMAT = "{0,number,0}({1,number,+0;-0})";

    private Shell shell;
    private NumberAxis xaxis;
    private NumberAxis yaxis;
    private LineChart<Number, Number> chart;
    private XYChart.Series<Number, Number> fuel = new XYChart.Series<>();
    private XYChart.Series<Number, Number> ammo = new XYChart.Series<>();
    private XYChart.Series<Number, Number> metal = new XYChart.Series<>();
    private XYChart.Series<Number, Number> bauxite = new XYChart.Series<>();
    private XYChart.Series<Number, Number> bucket = new XYChart.Series<>();
    private XYChart.Series<Number, Number> burner = new XYChart.Series<>();
    private XYChart.Series<Number, Number> research = new XYChart.Series<>();
    private Combo combo;
    private DateTime dateTimeFrom;
    private DateTime dateTimeTo;
    private Button fuelBtn;
    private Button ammoBtn;
    private Button metalBtn;
    private Button bauxiteBtn;
    private Button bucketBtn;
    private Button burnerBtn;
    private Button researchBtn;
    private FXCanvas fxCanvas;
    private Button forceZeroBtn;
    private Table table;
    /** 資材テーブルのヘッダ */
    private final String[] header = Arrays.copyOfRange(CreateReportLogic.getMaterialHeader(), 1, 9);
    /** 資材テーブルのボディ */
    private final List<String[]> body = new ArrayList<>();

    /**
     * Create the dialog.
     * @param parent
     * @param style
     */
    public ResourceChartDialogEx(Shell parent) {
        super(parent, SWT.SHELL_TRIM | SWT.MODELESS);
        this.setText("資材チャート改");
    }

    /**
     * Open the dialog.
     * @return the result
     */
    public void open() {
        this.createContents();
        this.shell.open();
        this.shell.layout();
        Display display = this.getParent().getDisplay();
        while (!this.shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        this.shell = new Shell(this.getParent(), this.getStyle());
        this.shell.setMinimumSize(450, 300);
        this.shell.setSize(800, 650);
        this.shell.setText(this.getText());

        // ウインドウ位置を復元
        LayoutLogic.applyWindowLocation(this.getClass(), this.shell);
        // 閉じた時にウインドウ位置を保存
        this.shell.addShellListener(new SaveWindowLocationAdapter(this.getClass()));
        this.shell.setLayout(new GridLayout(1, false));

        SashForm sashForm = new SashForm(this.shell, SWT.SMOOTH | SWT.VERTICAL);
        sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        Composite mainComposite = new Composite(sashForm, SWT.NONE);
        mainComposite.setLayout(new GridLayout(1, false));

        Composite rangeComposite = new Composite(mainComposite, SWT.NONE);
        rangeComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        rangeComposite.setLayout(new RowLayout(SWT.HORIZONTAL));

        Label label1 = new Label(rangeComposite, SWT.NONE);
        label1.setText("期間");
        this.combo = new Combo(rangeComposite, SWT.READ_ONLY);
        this.combo.setItems(SCALE_TEXT);
        this.combo.select(2);
        this.combo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ResourceChartDialogEx.this.setRange();
                ResourceChartDialogEx.this.reload();
            }
        });

        Label label2 = new Label(rangeComposite, SWT.NONE);
        label2.setText("開始");
        this.dateTimeFrom = new DateTime(rangeComposite, SWT.BORDER | SWT.DROP_DOWN);
        this.dateTimeFrom.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ResourceChartDialogEx.this.reload();
            }
        });
        Label label3 = new Label(rangeComposite, SWT.NONE);
        label3.setText("終了");
        this.dateTimeTo = new DateTime(rangeComposite, SWT.BORDER | SWT.DROP_DOWN);
        this.dateTimeTo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ResourceChartDialogEx.this.reload();
            }
        });

        Composite checkComposite = new Composite(mainComposite, SWT.NONE);
        checkComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        checkComposite.setLayout(new RowLayout(SWT.HORIZONTAL));

        this.fuelBtn = new Button(checkComposite, SWT.CHECK);
        this.fuelBtn.setText("燃料");
        this.fuelBtn.setSelection(true);
        this.fuelBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ResourceChartDialogEx.this.changeSeries();
            }
        });
        this.ammoBtn = new Button(checkComposite, SWT.CHECK);
        this.ammoBtn.setText("弾薬");
        this.ammoBtn.setSelection(true);
        this.ammoBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ResourceChartDialogEx.this.changeSeries();
            }
        });
        this.metalBtn = new Button(checkComposite, SWT.CHECK);
        this.metalBtn.setText("鋼材");
        this.metalBtn.setSelection(true);
        this.metalBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ResourceChartDialogEx.this.changeSeries();
            }
        });
        this.bauxiteBtn = new Button(checkComposite, SWT.CHECK);
        this.bauxiteBtn.setText("ボーキ");
        this.bauxiteBtn.setSelection(true);
        this.bauxiteBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ResourceChartDialogEx.this.changeSeries();
            }
        });
        this.bucketBtn = new Button(checkComposite, SWT.CHECK);
        this.bucketBtn.setText("高速修復材");
        this.bucketBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ResourceChartDialogEx.this.changeSeries();
            }
        });
        this.burnerBtn = new Button(checkComposite, SWT.CHECK);
        this.burnerBtn.setText("高速建造材");
        this.burnerBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ResourceChartDialogEx.this.changeSeries();
            }
        });
        this.researchBtn = new Button(checkComposite, SWT.CHECK);
        this.researchBtn.setText("開発資材");
        this.researchBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ResourceChartDialogEx.this.changeSeries();
            }
        });
        this.forceZeroBtn = new Button(checkComposite, SWT.CHECK);
        this.forceZeroBtn.setText("ゼロを基準");
        this.forceZeroBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ResourceChartDialogEx.this.yaxis.setForceZeroInRange(ResourceChartDialogEx.this.forceZeroBtn
                        .getSelection());
                ResourceChartDialogEx.this.changeSeries();
            }
        });

        try {
            // Create an FXCanvas
            this.fxCanvas = new FXCanvas(mainComposite, SWT.NONE);
            this.fxCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            VBox group = new VBox();

            this.xaxis = new NumberAxis();
            this.yaxis = new NumberAxis();
            this.yaxis.setForceZeroInRange(false);

            this.chart = new LineChart<Number, Number>(this.xaxis, this.yaxis);
            this.chart.setPrefHeight(800);
            this.chart.setCreateSymbols(false);
            // アニメーションを有効にするとなぜかaxisの描画が正しく行われない
            this.chart.setAnimated(false);
            group.getChildren().add(this.chart);
            // データを用意する
            this.setRange();
            this.reload();

            Scene scene = new Scene(group, Color.rgb(
                    this.shell.getBackground().getRed(),
                    this.shell.getBackground().getGreen(),
                    this.shell.getBackground().getBlue()));
            this.fxCanvas.setScene(scene);
        } catch (Exception e) {
            LOG.warn("資材チャートを作成中に例外が発生しました", e);
        }

        Composite compositeTable = new Composite(sashForm, SWT.NONE);
        compositeTable.setLayout(new GridLayout(1, false));

        this.table = new Table(compositeTable, SWT.BORDER | SWT.FULL_SELECTION);
        this.table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        sashForm.setWeights(new int[] { 3, 1 });

        // 資材ログのテーブル
        this.setTableHeader();
        try {
            createTableBody(Paths.get(AppConfig.get().getReportPath(),
                    AppConstants.LOG_RESOURCE), this.body);
            this.setTableBody();
            this.packTableHeader();
        } catch (Exception e) {
            LOG.warn("資材ログのテーブルを作成中例外が発生しました", e);
        }
    }

    /**
     * 設定されている期間で資材ログを読み込み資材チャートを描画する
     */
    private void reload() {
        this.changeRange();
        this.changeSeries();
    }

    /**
     * 期間のコンボボックスを変更した時の処理
     */
    private void setRange() {
        int idx = this.combo.getSelectionIndex();
        if (idx == -1) {
            idx = 2;
        }
        int days = SCALE_DAYS[idx];
        // Toを取得
        Calendar base = getCalendar(this.dateTimeTo);
        // コンボボックスで選択された日数だけ日を減らす
        base.add(Calendar.DAY_OF_YEAR, -days);
        // Fromを変更
        setCalendar(base, this.dateTimeFrom);
    }

    /**
     * 期間が変更された時の処理
     */
    private void changeRange() {
        // 開始
        Date from = getCalendar(this.dateTimeFrom).getTime();
        // 終了
        Calendar calTo = getCalendar(this.dateTimeTo);
        // ログを読み込む日付はその日の23:59.59までなので1日加算する
        calTo.add(Calendar.DAY_OF_YEAR, 1);
        Date to = calTo.getTime();

        this.xaxis.setTickLabelFormatter(new DateTimeConverter(from));
        this.loadSeries(from, to);
    }

    /**
     * 表示する資材の種類が変更された時の処理
     */
    private void changeSeries() {
        this.chart.getData().clear();
        List<XYChart.Series<Number, Number>> list = new ArrayList<>();
        if (this.fuelBtn.getSelection())
            list.add(this.fuel);
        if (this.ammoBtn.getSelection())
            list.add(this.ammo);
        if (this.metalBtn.getSelection())
            list.add(this.metal);
        if (this.bauxiteBtn.getSelection())
            list.add(this.bauxite);
        if (this.bucketBtn.getSelection())
            list.add(this.bucket);
        if (this.burnerBtn.getSelection())
            list.add(this.burner);
        if (this.researchBtn.getSelection())
            list.add(this.research);
        this.chart.getData().addAll(list);
    }

    /**
     * 資材ログを読み込む
     *
     * @param from 開始(自身を含む)
     * @param to 終了(自身を含まない)
     */
    private void loadSeries(Date from, Date to) {
        // Seriesに直接addすると1回addするごとにXYChart$Series$1.onChanged()が呼ばれて非常にパフォーマンスが悪いのでArrayListに入れてからaddAllする
        List<XYChart.Data<Number, Number>> fuelList = new ArrayList<>();
        List<XYChart.Data<Number, Number>> ammoList = new ArrayList<>();
        List<XYChart.Data<Number, Number>> metalList = new ArrayList<>();
        List<XYChart.Data<Number, Number>> bauxiteList = new ArrayList<>();
        List<XYChart.Data<Number, Number>> bucketList = new ArrayList<>();
        List<XYChart.Data<Number, Number>> burnerList = new ArrayList<>();
        List<XYChart.Data<Number, Number>> researchList = new ArrayList<>();
        try {
            try (Stream<String> stream = Files.lines(
                    Paths.get(AppConfig.get().getReportPath(), AppConstants.LOG_RESOURCE), AppConstants.CHARSET)) {
                stream.skip(1)
                        .map(Log::new)
                        .filter(e -> e.date != null)
                        .filter(e -> e.date.compareTo(from) >= 0)
                        .filter(e -> e.date.compareTo(to) < 0)
                        .forEach(
                                e -> {
                                    long time = e.date.getTime() - from.getTime();
                                    fuelList.add(new XYChart.Data<Number, Number>(time, e.fuel));
                                    ammoList.add(new XYChart.Data<Number, Number>(time, e.ammo));
                                    metalList.add(new XYChart.Data<Number, Number>(time, e.metal));
                                    bauxiteList.add(new XYChart.Data<Number, Number>(time, e.bauxite));
                                    bucketList.add(new XYChart.Data<Number, Number>(time, e.bucket));
                                    burnerList.add(new XYChart.Data<Number, Number>(time, e.burner));
                                    researchList.add(new XYChart.Data<Number, Number>(time, e.research));
                                });
            }
        } catch (Exception e) {
            LOG.warn("資材チャートの読み込み中に例外が発生しました", e);
        }

        this.fuel = new XYChart.Series<>();
        this.ammo = new XYChart.Series<>();
        this.metal = new XYChart.Series<>();
        this.bauxite = new XYChart.Series<>();
        this.bucket = new XYChart.Series<>();
        this.burner = new XYChart.Series<>();
        this.research = new XYChart.Series<>();
        this.fuel.getData().addAll(fuelList);
        this.ammo.getData().addAll(ammoList);
        this.metal.getData().addAll(metalList);
        this.bauxite.getData().addAll(bauxiteList);
        this.bucket.getData().addAll(bucketList);
        this.burner.getData().addAll(burnerList);
        this.research.getData().addAll(researchList);
        this.fuel.setName("燃料");
        this.ammo.setName("弾薬");
        this.metal.setName("鋼材");
        this.bauxite.setName("ボーキ");
        this.bucket.setName("高速修復材");
        this.burner.setName("高速建造材");
        this.research.setName("開発資材");
    }

    /**
     * テーブルヘッダーをセットする
     */
    private void setTableHeader() {
        for (int i = 0; i < this.header.length; i++) {
            TableColumn col = new TableColumn(this.table, SWT.LEFT);
            col.setText(this.header[i]);
        }
        this.packTableHeader();
    }

    /**
     * テーブルヘッダーの幅を調節する
     */
    private void packTableHeader() {
        TableColumn[] columns = this.table.getColumns();
        for (int i = 0; i < columns.length; i++) {
            columns[i].pack();
        }
    }

    /**
     * テーブルボディーをセットする
     */
    private void setTableBody() {
        TableItemCreator creator = TableItemCreator.DEFAULT_TABLE_ITEM_CREATOR;
        creator.init();
        for (int i = 0; i < this.body.size(); i++) {
            String[] line = this.body.get(i);
            creator.create(this.table, line, i);
        }
    }

    /**
     * 資材テーブルのボディを作成する
     *
     * @param log 資材ログ
     * @param body テーブルボディ
     * @throws IOException
     */
    private static void createTableBody(Path path, List<String[]> body) throws IOException {

        SimpleDateFormat format = new SimpleDateFormat(AppConstants.DATE_DAYS_FORMAT);
        format.setTimeZone(AppConstants.TIME_ZONE_MISSION);

        try (Stream<String> stream = Files.lines(path, AppConstants.CHARSET)) {
            BiConsumer<Map<String, Log>, Log> accumulator = (t, u) -> {
                String key = format.format(u.date);
                Log get = t.get(key);
                if ((get == null) || (get.date.compareTo(u.date) < 0))
                    t.put(key, u);
            };
            BiConsumer<Map<String, Log>, Map<String, Log>> combiner = (t, u) -> {
                for (Entry<String, Log> entry : u.entrySet()) {
                    String key = format.format(entry.getValue().date);
                    Log get = t.get(key);
                    if ((get == null) || (get.date.compareTo(entry.getValue().date) < 0))
                        t.put(key, entry.getValue());
                }
            };
            List<Entry<String, Log>> list = stream.skip(1)
                    .map(Log::new)
                    .filter(e -> e.date != null)
                    .collect(HashMap<String, Log>::new, accumulator, combiner)
                    .entrySet()
                    .stream()
                    .sorted((a, b) -> a.getKey().compareTo(b.getKey()))
                    .collect(Collectors.toList());

            MessageFormat compare = new MessageFormat(COMPARE_FORMAT);
            Log before = null;
            for (Entry<String, Log> entry : list) {
                Log val = entry.getValue();
                int[] material = { val.fuel, val.ammo, val.metal, val.bauxite, val.bucket, val.burner, val.research };
                int[] materialCompare = new int[material.length];
                if (before != null) {
                    int[] materialBefore = { before.fuel, before.ammo, before.metal, before.bauxite, before.bucket,
                            before.burner, before.research };
                    for (int i = 0; i < material.length; i++) {
                        materialCompare[i] = material[i] - materialBefore[i];
                    }
                }
                before = val;
                String[] line = new String[material.length + 1];
                line[0] = entry.getKey();
                for (int i = 0; i < material.length; i++) {
                    line[i + 1] = compare.format(new Object[] { material[i], materialCompare[i] });
                }
                body.add(line);
            }
        }
        Collections.reverse(body);
    }

    /**
     * DateTimeで選択されている日付からCalendarインスタンスを作成します
     *
     * @param dateTime
     * @return
     */
    private static Calendar getCalendar(DateTime dateTime) {
        Calendar cal = DateUtils.truncate(Calendar.getInstance(TimeZone.getDefault()), Calendar.DAY_OF_MONTH);
        cal.set(Calendar.YEAR, dateTime.getYear());
        cal.set(Calendar.MONTH, dateTime.getMonth());
        cal.set(Calendar.DAY_OF_MONTH, dateTime.getDay());
        return cal;
    }

    /**
     * DateTimeにCalendarの年月日をセットします
     *
     * @param cal
     * @param dateTime
     */
    private static void setCalendar(Calendar cal, DateTime dateTime) {
        dateTime.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 資材ログ
     *
     */
    private static class Log {
        /** 日付 */
        private Date date;
        /** 燃料 */
        private int fuel;
        /** 弾薬 */
        private int ammo;
        /** 鋼材 */
        private int metal;
        /** ボーキ */
        private int bauxite;
        /** 高速修復材 */
        private int bucket;
        /** 高速建造材 */
        private int burner;
        /** 開発資材 */
        private int research;

        public Log(String line) {
            try {
                String[] cols = line.split(",", -1);
                SimpleDateFormat format = new SimpleDateFormat(AppConstants.DATE_FORMAT);
                this.date = format.parse(cols[0]);
                this.fuel = Integer.parseInt(cols[1]);
                this.ammo = Integer.parseInt(cols[2]);
                this.metal = Integer.parseInt(cols[3]);
                this.bauxite = Integer.parseInt(cols[4]);
                this.bucket = Integer.parseInt(cols[5]);
                this.burner = Integer.parseInt(cols[6]);
                this.research = Integer.parseInt(cols[7]);
            } catch (ParseException e) {
                LOG.warn("資材ログを読み込み中に例外が発生しました:日付の形式が間違っています", e);
                LOG.warn(line);
            } catch (NumberFormatException e) {
                LOG.warn("資材ログを読み込み中に例外が発生しました:数値型に変換出来ません", e);
                LOG.warn(line);
            } catch (IndexOutOfBoundsException e) {
                LOG.warn("資材ログを読み込み中に例外が発生しました:項目数が一致しません", e);
                LOG.warn(line);
            }
        }
    }

    /**
     * チャートの時間軸ラベルに表示するテキスト
     *
     */
    private static class DateTimeConverter extends StringConverter<Number> {
        /** チャートに設定する最小の時刻 */
        private final long from;
        /** フォーマッター */
        private final SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);

        /**
         * @param from チャートに設定する最小の時刻
         */
        public DateTimeConverter(Date from) {
            this.from = from.getTime();
        }

        @Override
        public Number fromString(String str) {
            throw new UnsupportedOperationException();
        }

        @Override
        public String toString(Number n) {
            return this.format.format(new Date(n.longValue() + this.from));
        }
    }
}
