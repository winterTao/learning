package com.tao.download;

import com.tao.util.FtpUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

/**
 * @author DongTao
 * @since 2020-03-10
 */
public class Ftp {

    public static void main(String[] args) throws IOException {

        FTPClient ftpClient = FtpUtils.getFTPClient("10.24.65.81",
                "ftpuser1", "zETDVHX8r0Zq56gd", 21);

//        final Path path = Paths.get( "opt", "test");
//
//        System.out.println(path.toAbsolutePath());
//        System.out.println(path.toString());
        FtpUtils.downloadFtpFile(ftpClient,
                Paths.get("/opt/test/test.xlsx"), Paths.get("/Users/tao/Downloads/ftp/test.xlsx"));

        ftpClient.changeWorkingDirectory("/opt/test");
//
        FTPFileFilter ftpFileFilter = createFtpFileFilter();
        FTPFile[] ftpFiles = ftpClient.listFiles("/opt/test", ftpFileFilter);

        System.out.println("size : " + ftpFiles.length);
        for (FTPFile ftpFile : ftpFiles) {

            Path ftpPath = Paths.get("/opt/test", ftpFile.getName());

            System.out.println(ftpPath);
            if (ftpFile.isDirectory()) {

                FTPFile[] ftpFiles1 = ftpClient.listFiles(ftpPath.toString(), ftpFileFilter);

                System.out.println(ftpFiles1.length);

            } else {



                Path localPath = Paths.get("/Users/tao/Downloads/ftp", ftpPath.getFileName().toString());
                System.out.println(localPath);

                FtpUtils.downloadFtpFile(ftpClient, ftpPath, localPath);
            }
        }
    }

//    public Set<File> performListing(final FTPFile directory, final FTPFileFilter filter,
//            final boolean recurseSubdirectories) {
//
//        if (!directory.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION)
//                || !directory.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION) ) {
//            throw new IllegalStateException("Directory '" + directory
//                    + "' does not have sufficient permissions (i.e., not writable and readable)");
//        }
//        final Set<File> queue = new HashSet<>();
//        if (!directory.isValid()) {
//            return queue;
//        }
//
//        final File[] children = directory.listFiles();
//        if (children == null) {
//            return queue;
//        }
//
//        for (final File child : children) {
//            if (child.isDirectory()) {
//                if (recurseSubdirectories) {
//                    queue.addAll(performListing(child, filter, recurseSubdirectories));
//                }
//            } else if (filter.accept(child)) {
//                queue.add(child);
//            }
//        }
//
//        return queue;
//    }

    public static FTPFileFilter createFtpFileFilter() {

        String fileFilterStr = ".*.xlsx";
        String pathPatternStr = "";
        Boolean recurseDirs = false;
        Boolean keepFileSource = true;
        Pattern filePattern = Pattern.compile(fileFilterStr);
        Pattern pathPattern = (!recurseDirs || pathPatternStr == null)
                ? null : Pattern.compile(pathPatternStr);

        return file -> {

            if (file.getName().startsWith(".")) {
                return false;
            }
            if (pathPattern != null && file.isDirectory()) {
                if (!pathPattern.matcher(file.getName()).matches()) {
                    return false;
                }
            }

            //Verify that we have at least read permissions on the file we're considering grabbing
            if (!file.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION)) {
                return false;
            }

            //Verify that if we're not keeping original that we have write permissions on the directory the file is in
            if (!keepFileSource
                    && !file.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION)) {
                return false;
            }

            return filePattern.matcher(file.getName()).matches();
        };
    }


}
