package cn.bbahh.util;


import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class JarUtil {

    public static String getJarVersion(String url) throws IOException {
        URL jarUrl=new URL(url);
        JarURLConnection jarUrlConnection = (JarURLConnection) jarUrl.openConnection();
        JarFile jarFile=jarUrlConnection.getJarFile();
        Manifest manifest = jarFile.getManifest();
        return manifest.getMainAttributes().getValue("Implementation-Version");
    }
}