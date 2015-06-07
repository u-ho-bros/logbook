package logbook.gui.widgets;

import logbook.config.AppConfig;
import logbook.config.bean.AppConfigBean;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * 設定画面-キャプチャ
 *
 */
public final class ChartConfigComposite extends Composite {

    /** 燃料の色 */
    private final Label fuel;
    /** 弾薬の色 */
    private final Label ammo;
    /** 鋼材の色 */
    private final Label metal;
    /** ボーキの色 */
    private final Label bauxite;

    /**
     * Create the composite.
     * @param parent
     * @param style
     */
    public ChartConfigComposite(Composite parent, int style) {
        super(parent, style);

        this.setLayout(new GridLayout(3, false));

        this.fuel = new Label(this, SWT.NONE);
        this.fuel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        this.fuel.setText("燃料の色■");
        this.fuel.setForeground(SWTResourceManager.getColor(AppConfig.get().getFuelColor()));

        Button changeFuelColor = new Button(this, SWT.NONE);
        changeFuelColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        changeFuelColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ColorDialog dialog = new ColorDialog(ChartConfigComposite.this.getShell());
                RGB rgb = dialog.open();
                if (rgb != null) {
                    ChartConfigComposite.this.fuel.setForeground(SWTResourceManager.getColor(rgb));
                }
            }
        });
        changeFuelColor.setText("色の設定");

        Button resetFuelColor = new Button(this, SWT.NONE);
        resetFuelColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        resetFuelColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ChartConfigComposite.this.fuel.setForeground(SWTResourceManager.getColor(new RGB(0x00, 0x80, 0x00)));
            }
        });
        resetFuelColor.setText("リセット");

        this.ammo = new Label(this, SWT.NONE);
        this.ammo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        this.ammo.setText("弾薬の色■");
        this.ammo.setForeground(SWTResourceManager.getColor(AppConfig.get().getAmmoColor()));

        Button changeAmmoColor = new Button(this, SWT.NONE);
        changeAmmoColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        changeAmmoColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ColorDialog dialog = new ColorDialog(ChartConfigComposite.this.getShell());
                RGB rgb = dialog.open();
                if (rgb != null) {
                    ChartConfigComposite.this.ammo.setForeground(SWTResourceManager.getColor(rgb));
                }
            }
        });
        changeAmmoColor.setText("色の設定");

        Button resetAmmoColor = new Button(this, SWT.NONE);
        resetAmmoColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        resetAmmoColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ChartConfigComposite.this.ammo.setForeground(SWTResourceManager.getColor(new RGB(0x66, 0x33, 0x00)));
            }
        });
        resetAmmoColor.setText("リセット");

        this.metal = new Label(this, SWT.NONE);
        this.metal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        this.metal.setText("鋼材の色■");
        this.metal.setForeground(SWTResourceManager.getColor(AppConfig.get().getMetalColor()));

        Button changeMetalColor = new Button(this, SWT.NONE);
        changeMetalColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        changeMetalColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ColorDialog dialog = new ColorDialog(ChartConfigComposite.this.getShell());
                RGB rgb = dialog.open();
                if (rgb != null) {
                    ChartConfigComposite.this.metal.setForeground(SWTResourceManager.getColor(rgb));
                }
            }
        });
        changeMetalColor.setText("色の設定");

        Button resetMetalColor = new Button(this, SWT.NONE);
        resetMetalColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        resetMetalColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ChartConfigComposite.this.metal.setForeground(SWTResourceManager.getColor(new RGB(0x80, 0x80, 0x80)));
            }
        });
        resetMetalColor.setText("リセット");

        this.bauxite = new Label(this, SWT.NONE);
        this.bauxite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        this.bauxite.setText("ボーキの色■");
        this.bauxite.setForeground(SWTResourceManager.getColor(AppConfig.get().getBauxiteColor()));

        Button changeBauxiteColor = new Button(this, SWT.NONE);
        changeBauxiteColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        changeBauxiteColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ColorDialog dialog = new ColorDialog(ChartConfigComposite.this.getShell());
                RGB rgb = dialog.open();
                if (rgb != null) {
                    ChartConfigComposite.this.bauxite.setForeground(SWTResourceManager.getColor(rgb));
                }
            }
        });
        changeBauxiteColor.setText("色の設定");

        Button resetBauxiteColor = new Button(this, SWT.NONE);
        resetBauxiteColor.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        resetBauxiteColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ChartConfigComposite.this.bauxite.setForeground(SWTResourceManager.getColor(new RGB(0xCC, 0x33, 0x00)));
            }
        });
        resetBauxiteColor.setText("リセット");
    }

    @Override
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }

    /**
     * 燃料の色を取得します。
     * @return 燃料の色
     */
    public RGB getFuel() {
        return this.fuel.getForeground().getRGB();
    }

    /**
     * 弾薬の色を取得します。
     * @return 弾薬の色
     */
    public RGB getAmmo() {
        return this.ammo.getForeground().getRGB();
    }

    /**
     * 鋼材の色を取得します。
     * @return 鋼材の色
     */
    public RGB getMetal() {
        return this.metal.getForeground().getRGB();
    }

    /**
     * ボーキの色を取得します。
     * @return ボーキの色
     */
    public RGB getBauxite() {
        return this.bauxite.getForeground().getRGB();
    }

    /**
     * 画面設定を登録します
     * @param config アプリケーション設定
     */
    public void setConfig(AppConfigBean config) {
        config.setFuelColor(this.getFuel());
        config.setAmmoColor(this.getAmmo());
        config.setMetalColor(this.getMetal());
        config.setBauxiteColor(this.getBauxite());
    }
}
