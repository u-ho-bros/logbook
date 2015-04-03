package logbook.util;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;

import logbook.config.AppConfig;

import org.apache.commons.io.FilenameUtils;

/**
 * ファイルに関する処理のutilです
 *
 */
public class FileUtils {

    /**
     * 書き込み先のファイルを返します
     *
     * @param name ファイル名
     * @param altername 代替ファイル名
     * @return File
     * @throws IOException
     */
    public static File getStoreFile(String name, String altername) throws IOException {
        // 報告書の保存先にファイルを保存します
        File report = new File(FilenameUtils.concat(AppConfig.get().getReportPath(), name));
        if ((report.getParentFile() == null) && report.mkdirs()) {
            // 報告書の保存先ディレクトリが無く、ディレクトリの作成に失敗した場合はカレントフォルダにファイルを保存
            report = new File(name);
        }
        if (isLocked(report)) {
            // ロックされている場合は代替ファイルに書き込みます
            report = new File(FilenameUtils.concat(report.getParent(), altername));
        }
        return report;
    }

    /**
     * ファイルがロックされているかを確認します
     *
     * @param file ファイル
     * @return
     * @throws IOException
     */
    public static boolean isLocked(File file) {
        if (!file.isFile()) {
            return false;
        }
        try (FileChannel fc = FileChannel.open(file.toPath(),
                StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            FileLock lock = fc.tryLock();
            if (lock != null) {
                lock.release();
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            return true;
        }
    }
}
