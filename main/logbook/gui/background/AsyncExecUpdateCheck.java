package logbook.gui.background;

import java.awt.Desktop;
import java.text.MessageFormat;

import logbook.constants.AppConstants;
import logbook.internal.Version;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * アップデートチェックを行います
 *
 */
public final class AsyncExecUpdateCheck implements Runnable {

    private static class LoggerHolder {
        /** ロガー */
        private static final Logger LOG = LogManager.getLogger(AsyncExecUpdateCheck.class);
    }

    private static final String MESSAGE = "新しいバージョンがあります。ダウンロードサイトを開きますか？\r\n"
            + "現在のバージョン:" + AppConstants.VERSION + "\r\n"
            + "新しいバージョン:{0}\r\n"
            + "※自動アップデートチェックは[その他]-[設定]からOFFに出来ます";

    private final Shell shell;

    /**
     * コンストラクター
     *
     * @param shell
     */
    public AsyncExecUpdateCheck(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void run() {
        try {
            Version newversion = new Version(IOUtils.toString(AppConstants.UPDATE_CHECK_URI));

            if (AppConstants.VERSION.compareTo(newversion) < 0) {
                Display.getDefault().asyncExec(() -> {
                    if (!this.shell.isDisposed()) {
                        MessageBox box = new MessageBox(this.shell, SWT.YES | SWT.NO
                                | SWT.ICON_QUESTION);
                        box.setText("新しいバージョン");
                        box.setMessage(MessageFormat.format(MESSAGE, newversion));

                        // OKを押されたらダウンロードサイトへ移動する
                        if (box.open() == SWT.YES) {
                            try {
                                Desktop.getDesktop().browse(AppConstants.HOME_PAGE_URI);
                            } catch (Exception e) {
                                LoggerHolder.LOG.warn("ダウンロードサイトに移動が失敗しました", e);
                            }
                        }
                    }
                });
            }
        } catch (Exception e) {
            LoggerHolder.LOG.info(e.toString() + "が原因でアップデートチェックに失敗しました");
        }
    }
}
