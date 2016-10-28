package utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lever02
 */
public class FileUtils {
    public static void saveUrl(String urlString, String directory, String filename, String cookies)
            throws IOException {
        if (filename == null || filename.isEmpty()) {
            filename = urlString.substring(urlString.lastIndexOf('/') + 1, urlString.lastIndexOf(".mp4") + 4);
        }
        File directoryExist = new File(directory);
        if (!directoryExist.exists()) {
            directoryExist.mkdirs();
        }
        filename = directory + File.separator + filename;
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        URLConnection connection = null;
        try {
            connection = new URL(urlString).openConnection();
            if (cookies != null) {
                connection.setRequestProperty("Cookie", cookies);
            }
            connection.connect();
            int size = connection.getContentLength();
            System.out.println("About to download file size : " + size / (1024 * 1000) + "MB");

            in = new BufferedInputStream(connection.getInputStream());

            fout = new FileOutputStream(filename);

            byte data[] = new byte[1024];
            int count;
            int downloaded = 0;
            int percentage = 0;
            System.out.println("         0%----------------------------------------------------------------------------------------------------100%");
            System.out.print("Downloading:");
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
                downloaded += count;
                if (Math.round((float) downloaded / size * 100) > percentage) {
                    percentage = Math.round((float) downloaded / size * 100);
//                      		 System.out.println("Downloading : " + percentage + "%");
                    System.out.print("|");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error  wrong file location :" + urlString);
        } finally {
            if (in != null)
                in.close();
            if (fout != null)
                fout.close();
            if (connection != null)
                connection.getInputStream().close();

        }
    }

}
