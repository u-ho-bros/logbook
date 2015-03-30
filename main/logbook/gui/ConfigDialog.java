package logbook.gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import logbook.config.AppConfig;
import logbook.config.bean.WindowLocationBean;
import logbook.data.context.GlobalContext;
import logbook.gui.logic.LayoutLogic;
import logbook.internal.EvaluateExp;
import logbook.internal.SeaExp;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * 設定画面
 *
 */
public final class ConfigDialog extends Dialog {

    private final Map<String, Composite> compositeMap = new HashMap<String, Composite>();
    private Shell shell;
    private Composite composite;
    private ScrolledComposite scrolledComposite;

    /**
     * Create the dialog.
     * @param parent
     */
    public ConfigDialog(Shell parent) {
        super(parent, SWT.CLOSE | SWT.TITLE | SWT.MIN | SWT.RESIZE);
        this.setText("設定");
    }

    /**
     * Open the dialog.
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
        this.shell.setSize(550, 400);
        this.shell.setText(this.getText());
        this.shell.setLayout(new GridLayout(1, false));

        SashForm sashForm = new SashForm(this.shell, SWT.SMOOTH);
        sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        // ツリーメニュー
        Tree tree = new Tree(sashForm, SWT.BORDER);
        tree.addSelectionListener(new TreeSelectionAdapter(this));
        TreeItem systemroot = new TreeItem(tree, SWT.NONE);
        systemroot.setText("一般");
        systemroot.setData("system");
        TreeItem fleettab = new TreeItem(systemroot, SWT.NONE);
        fleettab.setText("艦隊タブ");
        fleettab.setData("fleettab");
        TreeItem notify = new TreeItem(systemroot, SWT.NONE);
        notify.setText("通知");
        notify.setData("notify");
        TreeItem capture = new TreeItem(systemroot, SWT.NONE);
        capture.setText("キャプチャ");
        capture.setData("capture");
        TreeItem chart = new TreeItem(systemroot, SWT.NONE);
        chart.setText("資材チャート");
        chart.setData("chart");
        TreeItem proxy = new TreeItem(systemroot, SWT.NONE);
        proxy.setText("プロキシ");
        proxy.setData("proxy");
        TreeItem extensionroot = new TreeItem(tree, SWT.NONE);
        extensionroot.setText("拡張");
        extensionroot.setData("extension");
        TreeItem userscript = new TreeItem(extensionroot, SWT.NONE);
        userscript.setText("ユーザースクリプト");
        userscript.setData("userscript");
        TreeItem development = new TreeItem(tree, SWT.NONE);
        development.setText("Development");
        development.setData("development");

        systemroot.setExpanded(true);
        extensionroot.setExpanded(true);

        this.scrolledComposite = new ScrolledComposite(sashForm, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        this.scrolledComposite.setExpandHorizontal(true);
        this.scrolledComposite.setExpandVertical(true);

        this.composite = new Composite(this.scrolledComposite, SWT.NONE);
        this.composite.setLayout(new GridLayout(1, false));

        // システム タブ
        Composite compositeSystem = new Composite(this.composite, SWT.NONE);
        this.compositeMap.put("system", compositeSystem);
        compositeSystem.setLayout(new GridLayout(3, false));

        Label label = new Label(compositeSystem, SWT.NONE);
        label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label.setText("ポート番号*");

        final Text listenport = new Text(compositeSystem, SWT.BORDER);
        GridData gdListenport = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
        gdListenport.widthHint = 90;
        listenport.setLayoutData(gdListenport);
        listenport.setText(Integer.toString(AppConfig.get().getListenPort()));
        new Label(compositeSystem, SWT.NONE);

        Label label3 = new Label(compositeSystem, SWT.NONE);
        label3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label3.setText("音量(%)");

        final Text soundlevel = new Text(compositeSystem, SWT.BORDER);
        GridData gdSoundlevel = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
        gdSoundlevel.widthHint = 90;
        soundlevel.setLayoutData(gdSoundlevel);
        soundlevel.setText(Integer.toString((int) (AppConfig.get().getSoundLevel() * 100)));
        new Label(compositeSystem, SWT.NONE);

        Label label7 = new Label(compositeSystem, SWT.NONE);
        label7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label7.setText("透明度*");

        final Spinner alpha = new Spinner(compositeSystem, SWT.BORDER);
        GridData gdAlpha = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
        gdAlpha.widthHint = 90;
        alpha.setLayoutData(gdAlpha);
        alpha.setMaximum(255);
        alpha.setMinimum(10);
        alpha.setSelection(AppConfig.get().getAlpha());
        new Label(compositeSystem, SWT.NONE);

        Label label8 = new Label(compositeSystem, SWT.NONE);
        label8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label8.setText("報告書の保存先");

        final Text reportDir = new Text(compositeSystem, SWT.BORDER);
        GridData gdReportDir = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
        gdReportDir.widthHint = 120;
        reportDir.setLayoutData(gdReportDir);
        reportDir.setText(AppConfig.get().getReportPath());

        Button reportSavedirBtn = new Button(compositeSystem, SWT.NONE);
        reportSavedirBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        reportSavedirBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                DirectoryDialog dialog = new DirectoryDialog(ConfigDialog.this.shell);
                dialog.setMessage("保存先を指定して下さい");
                String path = dialog.open();
                if (path != null) {
                    reportDir.setText(path);
                }
            }
        });
        reportSavedirBtn.setText("選択...");

        Label materialintervallabel = new Label(compositeSystem, SWT.NONE);
        materialintervallabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        materialintervallabel.setText("資材ログ保存間隔(秒)");

        final Spinner materialintervalSpinner = new Spinner(compositeSystem, SWT.BORDER);
        materialintervalSpinner.setMaximum(60 * 60 * 24);
        materialintervalSpinner.setMinimum(10);
        materialintervalSpinner.setSelection(AppConfig.get().getMaterialLogInterval());
        GridData gdMaterialIntervalSpinner = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gdMaterialIntervalSpinner.widthHint = 55;
        materialintervalSpinner.setLayoutData(gdMaterialIntervalSpinner);

        new Label(compositeSystem, SWT.NONE);

        final Button hidewindow = new Button(compositeSystem, SWT.CHECK);
        hidewindow.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
        hidewindow.setText("最小化時にタスクトレイに格納");
        hidewindow.setSelection(AppConfig.get().isHideWindow());

        final Button ontop = new Button(compositeSystem, SWT.CHECK);
        ontop.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
        ontop.setText("最前面に表示する*");
        ontop.setSelection(AppConfig.get().isOnTop());

        final Button checkUpdate = new Button(compositeSystem, SWT.CHECK);
        checkUpdate.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
        checkUpdate.setText("起動時にアップデートチェック*");
        checkUpdate.setSelection(AppConfig.get().isCheckUpdate());

        final Button checkDoit = new Button(compositeSystem, SWT.CHECK);
        checkDoit.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
        checkDoit.setText("終了時に確認する");
        checkDoit.setSelection(AppConfig.get().isCheckDoit());

        final Button onlyFromLocalhost = new Button(compositeSystem, SWT.CHECK);
        onlyFromLocalhost.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
        onlyFromLocalhost.setText("ローカルループバックアドレスからの接続のみ受け入れる*");
        onlyFromLocalhost.setSelection(AppConfig.get().isAllowOnlyFromLocalhost());

        final Button resetWindowLocation = new Button(compositeSystem, SWT.CHECK);
        resetWindowLocation.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
        resetWindowLocation.setText("サブウインドウの位置とサイズをリセット");

        // 艦隊タブ タブ
        Composite compositeFleetTab = new Composite(this.composite, SWT.NONE);
        this.compositeMap.put("fleettab", compositeFleetTab);
        compositeFleetTab.setLayout(new GridLayout(1, false));

        Group leveling = new Group(compositeFleetTab, SWT.NONE);
        leveling.setText("レベリング");
        leveling.setLayout(new RowLayout());
        leveling.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        final Button displaycount = new Button(leveling, SWT.CHECK);
        displaycount.setText("回数を表示");
        displaycount.setSelection(AppConfig.get().isDisplayCount());

        Label label9 = new Label(leveling, SWT.NONE);
        label9.setText("海域");
        final Combo seacombo = new Combo(leveling, SWT.READ_ONLY);
        int count = 0;
        for (Entry<String, Integer> entry : SeaExp.get().entrySet()) {
            seacombo.add(entry.getKey());
            if (entry.getKey().equals(AppConfig.get().getDefaultSea())) {
                seacombo.select(count);
            }
            count++;
        }
        Label label10 = new Label(leveling, SWT.NONE);
        label10.setText("評価");
        final Combo evalcombo = new Combo(leveling, SWT.READ_ONLY);
        count = 0;
        for (Entry<String, Double> entry : EvaluateExp.get().entrySet()) {
            evalcombo.add(entry.getKey());
            if (entry.getKey().equals(AppConfig.get().getDefaultEvaluate())) {
                evalcombo.select(count);
            }
            count++;
        }

        final Button warnByNeedSupply = new Button(compositeFleetTab, SWT.CHECK);
        warnByNeedSupply.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        warnByNeedSupply.setText("補給不足で警告アイコン表示");
        warnByNeedSupply.setSelection(AppConfig.get().isWarnByNeedSupply());

        final Button warnByCondState = new Button(compositeFleetTab, SWT.CHECK);
        warnByCondState.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        warnByCondState.setText("疲労状態で警告アイコン表示");
        warnByCondState.setSelection(AppConfig.get().isWarnByCondState());

        final Button warnByHalfDamage = new Button(compositeFleetTab, SWT.CHECK);
        warnByHalfDamage.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        warnByHalfDamage.setText("中破で警告アイコン表示");
        warnByHalfDamage.setSelection(AppConfig.get().isWarnByHalfDamage());

        final Button fatalBybadlyDamage = new Button(compositeFleetTab, SWT.CHECK);
        fatalBybadlyDamage.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        fatalBybadlyDamage.setText("大破で致命的アイコン表示");
        fatalBybadlyDamage.setSelection(AppConfig.get().isFatalBybadlyDamage());

        final Button balloonBybadlyDamage = new Button(compositeFleetTab, SWT.CHECK);
        balloonBybadlyDamage.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        balloonBybadlyDamage.setText("大破でバルーンツールチップを表示");
        balloonBybadlyDamage.setSelection(AppConfig.get().isBalloonBybadlyDamage());

        final Button visibleOnReturnMission = new Button(compositeFleetTab, SWT.CHECK);
        visibleOnReturnMission.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        visibleOnReturnMission.setText("遠征からの帰還時に母港タブを表示");
        visibleOnReturnMission.setSelection(AppConfig.get().isVisibleOnReturnMission());

        final Button visibleOnReturnBathwater = new Button(compositeFleetTab, SWT.CHECK);
        visibleOnReturnBathwater.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        visibleOnReturnBathwater.setText("お風呂から上がる時に母港タブを表示");
        visibleOnReturnBathwater.setSelection(AppConfig.get().isVisibleOnReturnBathwater());

        final Button useMonoIcon = new Button(compositeFleetTab, SWT.CHECK);
        useMonoIcon.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        useMonoIcon.setText("モノクロアイコンを使用");
        useMonoIcon.setSelection(AppConfig.get().isMonoIcon());

        // 通知
        Composite compositeNotify = new Composite(this.composite, SWT.NONE);
        this.compositeMap.put("notify", compositeNotify);
        compositeNotify.setLayout(new GridLayout(3, false));

        final Button remind = new Button(compositeNotify, SWT.CHECK);
        remind.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
        remind.setText("遠征の通知をリマインドする");
        remind.setSelection(AppConfig.get().isMissionRemind());

        Label intervallabel = new Label(compositeNotify, SWT.NONE);
        intervallabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        intervallabel.setText("間隔(秒)");

        final Spinner intervalSpinner = new Spinner(compositeNotify, SWT.BORDER);
        intervalSpinner.setMaximum(60 * 60);
        intervalSpinner.setMinimum(10);
        intervalSpinner.setSelection(AppConfig.get().getRemindInterbal());
        GridData gdIntervalSpinner = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gdIntervalSpinner.widthHint = 55;
        intervalSpinner.setLayoutData(gdIntervalSpinner);

        new Label(compositeNotify, SWT.NONE);

        final Button balloon = new Button(compositeNotify, SWT.CHECK);
        balloon.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
        balloon.setText("遠征・入渠をバルーンで通知する");
        balloon.setSelection(AppConfig.get().isUseBalloon());

        final Button taskbar = new Button(compositeNotify, SWT.CHECK);
        taskbar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
        taskbar.setText("母港の空きをタスクバーで通知する");
        taskbar.setSelection(AppConfig.get().isUseTaskbarNotify());

        Label fullyLabel1 = new Label(compositeNotify, SWT.NONE);
        fullyLabel1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        fullyLabel1.setText("母港の空きが");

        final Spinner fullySpinner = new Spinner(compositeNotify, SWT.BORDER);
        fullySpinner.setMaximum(Math.max(100, GlobalContext.maxChara()));
        fullySpinner.setMinimum(0);
        fullySpinner.setSelection(AppConfig.get().getNotifyFully());
        GridData gdFullySpinner = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gdFullySpinner.widthHint = 55;
        fullySpinner.setLayoutData(gdFullySpinner);

        Label fullyLabel2 = new Label(compositeNotify, SWT.NONE);
        fullyLabel2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        fullyLabel2.setText("以下で警告表示");

        // キャプチャ タブ
        Composite compositeCapture = new Composite(this.composite, SWT.NONE);
        this.compositeMap.put("capture", compositeCapture);
        compositeCapture.setLayout(new GridLayout(3, false));

        Label label4 = new Label(compositeCapture, SWT.NONE);
        label4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label4.setText("保存先");

        final Text captureDir = new Text(compositeCapture, SWT.BORDER);
        GridData gdCaptureDir = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
        gdCaptureDir.widthHint = 120;
        captureDir.setLayoutData(gdCaptureDir);
        captureDir.setText(AppConfig.get().getCapturePath());

        Button savedirBtn = new Button(compositeCapture, SWT.NONE);
        savedirBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        savedirBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                DirectoryDialog dialog = new DirectoryDialog(ConfigDialog.this.shell);
                dialog.setMessage("保存先を指定して下さい");
                String path = dialog.open();
                if (path != null) {
                    captureDir.setText(path);
                }
            }
        });
        savedirBtn.setText("選択...");

        Label label5 = new Label(compositeCapture, SWT.NONE);
        label5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label5.setText("フォーマット");

        final Combo imageformatCombo = new Combo(compositeCapture, SWT.READ_ONLY);
        imageformatCombo.setItems(new String[] { "jpg", "png" });
        imageformatCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        imageformatCombo.select(0);
        for (int i = 0; i < imageformatCombo.getItems().length; i++) {
            if (AppConfig.get().getImageFormat().equals(imageformatCombo.getItem(i))) {
                imageformatCombo.select(i);
                break;
            }
        }
        new Label(compositeCapture, SWT.NONE);

        Label label6 = new Label(compositeCapture, SWT.NONE);
        label6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label6.setText("書式");

        final Text imageNameFormat = new Text(compositeCapture, SWT.BORDER);
        GridData gdImageNameFormat = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
        gdImageNameFormat.widthHint = 250;
        imageNameFormat.setLayoutData(gdImageNameFormat);
        imageNameFormat.setText(AppConfig.get().getImageNameFormat());

        Label label11 = new Label(compositeCapture, SWT.NONE);
        label11.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label11.setText("プレビュー");

        final Text imageNamePrev = new Text(compositeCapture, SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
        GridData gdImageNamePrev = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
        gdImageNamePrev.heightHint = imageNamePrev.getLineHeight() * 3;
        gdImageNamePrev.widthHint = 250;
        imageNamePrev.setLayoutData(gdImageNamePrev);
        imageNamePrev.setText(prevImageNameFormat(AppConfig.get().getImageNameFormat()));

        imageNameFormat.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                imageNamePrev.setText(prevImageNameFormat(imageNameFormat.getText()));
            }
        });

        Label label12 = new Label(compositeCapture, SWT.NONE);
        label12.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1));
        label12.setText("英字を書式に含めるには ' (シングルクォーテーション)で囲みます");

        // 資材チャート タブ
        Composite compositeChart = new Composite(this.composite, SWT.NONE);
        this.compositeMap.put("chart", compositeChart);
        compositeChart.setLayout(new GridLayout(3, false));

        final Label fuel = new Label(compositeChart, SWT.NONE);
        fuel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        fuel.setText("燃料の色■");
        fuel.setForeground(SWTResourceManager.getColor(AppConfig.get().getFuelColor()));

        Button changeFuelColor = new Button(compositeChart, SWT.NONE);
        changeFuelColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        changeFuelColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ColorDialog dialog = new ColorDialog(ConfigDialog.this.shell);
                RGB rgb = dialog.open();
                if (rgb != null) {
                    fuel.setForeground(SWTResourceManager.getColor(rgb));
                }
            }
        });
        changeFuelColor.setText("色の設定");

        Button resetFuelColor = new Button(compositeChart, SWT.NONE);
        resetFuelColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        resetFuelColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                fuel.setForeground(SWTResourceManager.getColor(new RGB(0x00, 0x80, 0x00)));
            }
        });
        resetFuelColor.setText("リセット");

        final Label ammo = new Label(compositeChart, SWT.NONE);
        ammo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        ammo.setText("弾薬の色■");
        ammo.setForeground(SWTResourceManager.getColor(AppConfig.get().getAmmoColor()));

        Button changeAmmoColor = new Button(compositeChart, SWT.NONE);
        changeAmmoColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        changeAmmoColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ColorDialog dialog = new ColorDialog(ConfigDialog.this.shell);
                RGB rgb = dialog.open();
                if (rgb != null) {
                    ammo.setForeground(SWTResourceManager.getColor(rgb));
                }
            }
        });
        changeAmmoColor.setText("色の設定");

        Button resetAmmoColor = new Button(compositeChart, SWT.NONE);
        resetAmmoColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        resetAmmoColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ammo.setForeground(SWTResourceManager.getColor(new RGB(0x66, 0x33, 0x00)));
            }
        });
        resetAmmoColor.setText("リセット");

        final Label metal = new Label(compositeChart, SWT.NONE);
        metal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        metal.setText("鋼材の色■");
        metal.setForeground(SWTResourceManager.getColor(AppConfig.get().getMetalColor()));

        Button changeMetalColor = new Button(compositeChart, SWT.NONE);
        changeMetalColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        changeMetalColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ColorDialog dialog = new ColorDialog(ConfigDialog.this.shell);
                RGB rgb = dialog.open();
                if (rgb != null) {
                    metal.setForeground(SWTResourceManager.getColor(rgb));
                }
            }
        });
        changeMetalColor.setText("色の設定");

        Button resetMetalColor = new Button(compositeChart, SWT.NONE);
        resetMetalColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        resetMetalColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                metal.setForeground(SWTResourceManager.getColor(new RGB(0x80, 0x80, 0x80)));
            }
        });
        resetMetalColor.setText("リセット");

        final Label bauxite = new Label(compositeChart, SWT.NONE);
        bauxite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        bauxite.setText("ボーキの色■");
        bauxite.setForeground(SWTResourceManager.getColor(AppConfig.get().getBauxiteColor()));

        Button changeBauxiteColor = new Button(compositeChart, SWT.NONE);
        changeBauxiteColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        changeBauxiteColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ColorDialog dialog = new ColorDialog(ConfigDialog.this.shell);
                RGB rgb = dialog.open();
                if (rgb != null) {
                    bauxite.setForeground(SWTResourceManager.getColor(rgb));
                }
            }
        });
        changeBauxiteColor.setText("色の設定");

        Button resetBauxiteColor = new Button(compositeChart, SWT.NONE);
        resetBauxiteColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        resetBauxiteColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                bauxite.setForeground(SWTResourceManager.getColor(new RGB(0xCC, 0x33, 0x00)));
            }
        });
        resetBauxiteColor.setText("リセット");

        // プロキシ
        Composite compositeProxy = new Composite(this.composite, SWT.NONE);
        this.compositeMap.put("proxy", compositeProxy);
        compositeProxy.setLayout(new GridLayout(4, false));

        final Button useProxyButton = new Button(compositeProxy, SWT.CHECK);
        useProxyButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
        useProxyButton.setText("接続にプロキシを使用する*");
        useProxyButton.setSelection(AppConfig.get().isUseProxy());

        Label proxyHostLabel = new Label(compositeProxy, SWT.NONE);
        proxyHostLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        proxyHostLabel.setText("ホスト:");

        final Text proxyHostText = new Text(compositeProxy, SWT.BORDER);
        GridData gdProxyHostText = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gdProxyHostText.widthHint = 100;
        proxyHostText.setLayoutData(gdProxyHostText);
        proxyHostText.setText(AppConfig.get().getProxyHost());

        Label proxyPortLabel = new Label(compositeProxy, SWT.NONE);
        proxyPortLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        proxyPortLabel.setText("ポート:");

        final Spinner proxyPortSpinner = new Spinner(compositeProxy, SWT.BORDER);
        proxyPortSpinner.setMaximum(65535);
        proxyPortSpinner.setMinimum(1);
        proxyPortSpinner.setSelection(AppConfig.get().getProxyPort());
        GridData gdProxyPortSpinner = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gdProxyPortSpinner.widthHint = 55;
        proxyPortSpinner.setLayoutData(gdProxyPortSpinner);

        // 拡張
        Composite compositeExtension = new Composite(this.composite, SWT.NONE);
        this.compositeMap.put("extension", compositeExtension);
        compositeExtension.setLayout(new GridLayout(1, false));

        // ユーザースクリプト
        Composite userScriptComposite = new Composite(this.composite, SWT.NONE);
        this.compositeMap.put("userscript", userScriptComposite);
        userScriptComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        userScriptComposite.setLayout(new GridLayout(1, false));

        final Button useScript = new Button(userScriptComposite, SWT.CHECK);
        useScript.setText("ユーザースクリプトを使用する");
        useScript.setSelection(AppConfig.get().isUseUserScript());

        Group scriptgroup = new Group(userScriptComposite, SWT.NONE);
        scriptgroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        scriptgroup.setText("ユーザースクリプト*");
        scriptgroup.setLayout(new GridLayout(2, false));

        final List scriptList = new List(scriptgroup, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        GridData gdScriptList = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
        gdScriptList.heightHint = 60;
        scriptList.setLayoutData(gdScriptList);
        if (AppConfig.get().getUserScripts() != null) {
            scriptList.setItems(AppConfig.get().getUserScripts());
        }

        Button addScript = new Button(scriptgroup, SWT.NONE);
        addScript.setText("追加");
        addScript.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(ConfigDialog.this.shell, SWT.OPEN | SWT.MULTI);
                dialog.open();
                String dir = dialog.getFilterPath();
                String[] files = dialog.getFileNames();

                if ((dir != null) && !dir.isEmpty()) {
                    for (String file : files) {
                        Path path = Paths.get(dir, file);
                        if (Files.isReadable(path)) {
                            String script = path.toString();
                            if (Arrays.asList(scriptList.getItems()).indexOf(script) == -1) {
                                scriptList.add(script);
                            }
                        }
                    }
                }
            }
        });

        Button removeScript = new Button(scriptgroup, SWT.NONE);
        removeScript.setText("除去");
        removeScript.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                scriptList.remove(scriptList.getSelectionIndices());
            }
        });

        Label lblNewLabel = new Label(scriptgroup, SWT.NONE);
        lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
        lblNewLabel.setText("信頼出来ないユーザースクリプトはコンピュータに損害を与える恐れがあります");

        Group enginegroup = new Group(userScriptComposite, SWT.NONE);
        enginegroup.setLayout(new GridLayout(2, false));
        enginegroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        enginegroup.setText("スクリプトエンジン*");

        final List engineList = new List(enginegroup, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        GridData gdEngineList = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
        gdEngineList.heightHint = 60;
        engineList.setLayoutData(gdEngineList);
        if (AppConfig.get().getScriptEngines() != null) {
            engineList.setItems(AppConfig.get().getScriptEngines());
        }

        Button addEngine = new Button(enginegroup, SWT.NONE);
        addEngine.setText("追加");
        addEngine.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(ConfigDialog.this.shell, SWT.OPEN | SWT.MULTI);
                dialog.setFilterExtensions(new String[] { "*.jar" });
                dialog.open();
                String dir = dialog.getFilterPath();
                String[] files = dialog.getFileNames();

                if ((dir != null) && !dir.isEmpty()) {
                    for (String file : files) {
                        Path path = Paths.get(dir, file);
                        if (Files.isReadable(path)) {
                            String script = path.toString();
                            if (Arrays.asList(engineList.getItems()).indexOf(script) == -1) {
                                engineList.add(script);
                            }
                        }
                    }
                }
            }
        });

        Button removeEngine = new Button(enginegroup, SWT.NONE);
        removeEngine.setText("除去");
        removeEngine.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                engineList.remove(engineList.getSelectionIndices());
            }
        });

        // Development タブ
        Composite compositeDevelopment = new Composite(this.composite, SWT.NONE);
        this.compositeMap.put("development", compositeDevelopment);
        compositeDevelopment.setLayout(new GridLayout(2, false));

        new Label(compositeDevelopment, SWT.NONE);
        final Button btnJson = new Button(compositeDevelopment, SWT.CHECK);
        btnJson.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        btnJson.setText("JSONを保存する");
        btnJson.setSelection(AppConfig.get().isStoreJson());

        Label lblJson = new Label(compositeDevelopment, SWT.NONE);
        lblJson.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblJson.setText("JSON保存先");

        final Text jsonpath = new Text(compositeDevelopment, SWT.BORDER);
        GridData gdJsonpath = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gdJsonpath.widthHint = 120;
        jsonpath.setLayoutData(gdJsonpath);
        jsonpath.setText(AppConfig.get().getStoreJsonPath());

        new Label(compositeDevelopment, SWT.NONE);
        final Button btnCheckUpdateOrg = new Button(compositeDevelopment, SWT.CHECK);
        btnCheckUpdateOrg.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        btnCheckUpdateOrg.setText("起動時に本家の更新チェック*");
        btnCheckUpdateOrg.setSelection(AppConfig.get().isCheckUpdateOriginal());

        // コマンドグループ
        Composite commandComposite = new Composite(this.shell, SWT.NONE);
        commandComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        GridLayout glCommandComposite = new GridLayout(2, false);
        glCommandComposite.verticalSpacing = 0;
        glCommandComposite.marginWidth = 0;
        glCommandComposite.marginHeight = 0;
        glCommandComposite.horizontalSpacing = 0;
        commandComposite.setLayout(glCommandComposite);

        // '*'の説明
        Composite commandLeft = new Composite(commandComposite, SWT.NONE);
        GridLayout glCommandLeft = new GridLayout(1, false);
        glCommandLeft.horizontalSpacing = 0;
        glCommandLeft.marginHeight = 0;
        glCommandLeft.verticalSpacing = 0;
        glCommandLeft.marginWidth = 0;
        commandLeft.setLayout(glCommandLeft);

        Label attentionLabel = new Label(commandLeft, SWT.NONE);
        attentionLabel.setText("*再起動後に有効になります");

        Composite commandRight = new Composite(commandComposite, SWT.NONE);
        RowLayout rlCommandRight = new RowLayout(SWT.HORIZONTAL);
        rlCommandRight.marginTop = 0;
        rlCommandRight.marginLeft = 0;
        rlCommandRight.marginRight = 0;
        rlCommandRight.marginBottom = 0;
        commandRight.setLayout(rlCommandRight);
        commandRight.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));

        // OK ボタン
        Button applyBtn = new Button(commandRight, SWT.NONE);
        applyBtn.setLayoutData(new RowData(100, SWT.DEFAULT));
        applyBtn.setText("OK");
        applyBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // 設定の保存アクション

                // system
                if (StringUtils.isNumeric(listenport.getText())) {
                    AppConfig.get().setListenPort(Integer.parseInt(listenport.getText()));
                }
                AppConfig.get().setHideWindow(hidewindow.getSelection());
                AppConfig.get().setOnTop(ontop.getSelection());
                AppConfig.get().setCheckDoit(checkDoit.getSelection());
                if (StringUtils.isNumeric(soundlevel.getText())) {
                    float level = (float) Integer.parseInt(soundlevel.getText()) / 100;
                    AppConfig.get().setSoundLevel(level);
                }
                AppConfig.get().setAlpha(alpha.getSelection());
                AppConfig.get().setReportPath(reportDir.getText());
                AppConfig.get().setMaterialLogInterval(materialintervalSpinner.getSelection());
                AppConfig.get().setCheckUpdate(checkUpdate.getSelection());
                AppConfig.get().setAllowOnlyFromLocalhost(onlyFromLocalhost.getSelection());
                if (resetWindowLocation.getSelection()) {
                    Map<String, WindowLocationBean> map = AppConfig.get().getWindowLocationMap();
                    synchronized (map) {
                        map.clear();
                    }
                    MessageBox box = new MessageBox(ConfigDialog.this.shell, SWT.ICON_INFORMATION | SWT.OK);
                    box.setText("設定");
                    box.setMessage("サブウインドウの位置とサイズがリセットされました");
                    box.open();
                }
                // fleettab
                AppConfig.get().setDisplayCount(displaycount.getSelection());
                AppConfig.get().setDefaultSea(seacombo.getItem(seacombo.getSelectionIndex()));
                AppConfig.get().setDefaultEvaluate(evalcombo.getItem(evalcombo.getSelectionIndex()));
                AppConfig.get().setWarnByNeedSupply(warnByNeedSupply.getSelection());
                AppConfig.get().setWarnByCondState(warnByCondState.getSelection());
                AppConfig.get().setWarnByHalfDamage(warnByHalfDamage.getSelection());
                AppConfig.get().setFatalBybadlyDamage(fatalBybadlyDamage.getSelection());
                AppConfig.get().setBalloonBybadlyDamage(balloonBybadlyDamage.getSelection());
                AppConfig.get().setVisibleOnReturnMission(visibleOnReturnMission.getSelection());
                AppConfig.get().setVisibleOnReturnBathwater(visibleOnReturnBathwater.getSelection());
                AppConfig.get().setMonoIcon(useMonoIcon.getSelection());
                // notify
                AppConfig.get().setMissionRemind(remind.getSelection());
                AppConfig.get().setRemindInterbal(intervalSpinner.getSelection());
                AppConfig.get().setUseBalloon(balloon.getSelection());
                AppConfig.get().setUseTaskbarNotify(taskbar.getSelection());
                AppConfig.get().setNotifyFully(fullySpinner.getSelection());
                // capture
                AppConfig.get().setCapturePath(captureDir.getText());
                AppConfig.get().setImageFormat(imageformatCombo.getItem(imageformatCombo.getSelectionIndex()));
                AppConfig.get().setImageNameFormat(imageNameFormat.getText());
                // チャート
                AppConfig.get().setFuelColor(fuel.getForeground().getRGB());
                AppConfig.get().setAmmoColor(ammo.getForeground().getRGB());
                AppConfig.get().setMetalColor(metal.getForeground().getRGB());
                AppConfig.get().setBauxiteColor(bauxite.getForeground().getRGB());
                // proxy
                AppConfig.get().setUseProxy(useProxyButton.getSelection());
                AppConfig.get().setProxyHost(proxyHostText.getText());
                AppConfig.get().setProxyPort(proxyPortSpinner.getSelection());
                // ユーザースクリプト
                AppConfig.get().setUseUserScript(useScript.getSelection());
                AppConfig.get().setUserScripts(scriptList.getItems());
                AppConfig.get().setScriptEngines(engineList.getItems());
                // development
                AppConfig.get().setStoreJson(btnJson.getSelection());
                AppConfig.get().setStoreJsonPath(new File(jsonpath.getText()).getAbsolutePath());
                AppConfig.get().setCheckUpdate(btnCheckUpdateOrg.getSelection());
                try {
                    AppConfig.store();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ConfigDialog.this.shell.close();
            }
        });

        Button cancelBtn = new Button(commandRight, SWT.NONE);
        cancelBtn.setLayoutData(new RowData(100, SWT.DEFAULT));
        cancelBtn.setText("キャンセル");
        cancelBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ConfigDialog.this.shell.close();
            }
        });

        for (Entry<String, Composite> entry : this.compositeMap.entrySet()) {
            LayoutLogic.hide(entry.getValue(), true);
        }

        sashForm.setWeights(new int[] { 2, 5 });
        this.scrolledComposite.setContent(this.composite);
        this.scrolledComposite.setMinSize(this.composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
    }

    /**
     * 画像ファイル名のプレビュー
     *
     * @param format
     * @return
     */
    private static String prevImageNameFormat(String format) {
        try {
            Calendar calendar = Calendar.getInstance();
            if (format.isEmpty()) {
                throw new IllegalArgumentException();
            }
            String prev = new SimpleDateFormat(format).format(calendar.getTime())
                    + "." + AppConfig.get().getImageFormat();
            try {
                Path path = Paths.get(AppConfig.get().getCapturePath(), prev);
                return path.normalize().toString();
            } catch (InvalidPathException ex) {
                return "ファイル名に無効な文字が含まれています :" + ex.getReason();
            }
        } catch (IllegalArgumentException ex) {
            return "日付書式が無効です :" + ex.getMessage();
        }
    }

    /**
     * ツリーをクリックした時に呼び出されるアダプター
     *
     */
    private static final class TreeSelectionAdapter extends SelectionAdapter {

        /** ダイアログ */
        private final ConfigDialog dialog;

        /**
         * コンストラクター
         */
        public TreeSelectionAdapter(ConfigDialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void widgetSelected(SelectionEvent e) {
            Object data = e.item.getData();
            if (data instanceof String) {
                String treeKey = (String) data;
                for (Entry<String, Composite> entry : this.dialog.compositeMap.entrySet()) {
                    if (entry.getKey().equals(treeKey)) {
                        LayoutLogic.hide(entry.getValue(), false);
                    } else {
                        LayoutLogic.hide(entry.getValue(), true);
                    }
                }
                this.dialog.composite.layout();
                this.dialog.scrolledComposite.setMinSize(this.dialog.composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
            }
        }
    }
}
