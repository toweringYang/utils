package cn.bbahh.util;

import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.OutputStream;

/**
 * sftp连接工具
 */
@Component
public class SftpUtil {
    public void uploadFile(String host,int point,String user,String password,File file,String path) {
        Sftp sftp= JschUtil.createSftp(host, point, user, password);
        sftp.upload(path,file);
        sftp.close();
    }
    public void download(String host, int point, String user, String password, String path, OutputStream outputStream) {
        Sftp sftp= JschUtil.createSftp(host, point, user, password);
        sftp.download(path,outputStream);
        sftp.close();
    }
}


