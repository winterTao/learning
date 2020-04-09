package com.tao.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @author DongTao
 * @since 2020-03-10
 */
@Slf4j
public class FtpUtils {

    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
            String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();

            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUserName, ftpPassword);

            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {

                ftpClient.setControlEncoding(StandardCharsets.UTF_8.name());
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();

                System.out.println("FTP连接成功。");
            } else {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            }
        } catch (SocketException e) {
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }

    public static void downloadFtpFile(FTPClient ftpClient, Path ftpPath, Path localPath) {

        try {

            ftpClient.changeWorkingDirectory(ftpPath.getParent().toString());

            OutputStream os = new FileOutputStream(localPath.toFile());
            ftpClient.retrieveFile(ftpPath.getFileName().toString(), os);
            os.close();

        } catch (FileNotFoundException e) {

            System.out.println("没有找到 {} 文件 " + ftpPath);

        } catch (SocketException e) {

            System.out.println("连接FTP失败.");

        } catch (IOException e) {

            System.out.println("文件读取错误。");

        }

    }

}
