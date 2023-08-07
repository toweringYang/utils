package cn.bbahh.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtil {

    private static final String GBK = "gbk";

    /**
     * 文件解压
     *
     * @param is
     * @param dirPath
     */
    public static void doUnzip(InputStream is, String dirPath) {
        try (ZipInputStream zins = new ZipInputStream(is, Charset.forName(GBK))) {
            ZipEntry zipEntry = null;
            while ((zipEntry = zins.getNextEntry()) != null) {
                if (zipEntry.isDirectory()) {
                    File dirFile = new File(dirPath + File.separator + zipEntry.getName());
                    dirFile.mkdirs();
                } else {
                    File newFile = new File(dirPath + File.separator + zipEntry.getName());
                    if (!newFile.getParentFile().exists()) {
                        newFile.getParentFile().mkdirs();
                    }
                    newFile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(newFile);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zins.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        fos.flush();
                    }
                    fos.close();
                }
            }
            zins.closeEntry();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
