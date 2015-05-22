package logbook.server.proxy;

import java.net.BindException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * プロキシサーバーです
 *
 */
public final class ProxyServer extends Thread {

    private static class LoggerHolder {
        /** ロガー */
        private static final Logger LOG = LogManager.getLogger(ProxyServer.class);
    }

    private final int port;
    private final Shell shell;

    private Server server;

    public ProxyServer(int port, Shell shell) {
        this.port = port;
        this.shell = shell;
        this.setName("logbook_proxy_server");
    }

    @Override
    public void run() {
        try {
            this.server = new Server(this.port);

            ServletHandler servletHandler = new ServletHandler();
            servletHandler.addServletWithMapping(ReverseProxyServlet.class, "/*");
            servletHandler.setServer(this.server);

            this.server.setHandler(servletHandler);
            try {
                try {
                    this.server.start();
                    try {
                        this.server.join();
                    } catch (InterruptedException e) {
                    }
                } catch (Exception e) {
                    this.handle(e);
                }
            } finally {
                this.server.stop();
            }
        } catch (Exception e) {
            LoggerHolder.LOG.fatal("サーバーの起動に失敗しました", e);
            throw new RuntimeException(e);
        }
    }

    private void handle(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("プロキシサーバーが予期せず終了しました").append("\r\n");
        sb.append("例外 : " + e.getClass().getName()).append("\r\n");
        sb.append("原因 : " + e.getMessage()).append("\r\n");
        if (e instanceof BindException) {
            sb.append("おそらく、二重起動か同じポートを使用しているアプリケーションがあります。").append("\r\n");
        }

        final String message = sb.toString();

        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                MessageBox box = new MessageBox(ProxyServer.this.shell, SWT.YES | SWT.ICON_ERROR);
                box.setText("プロキシサーバーが予期せず終了しました");
                box.setMessage(message);
                box.open();
            }
        });
    }
}
