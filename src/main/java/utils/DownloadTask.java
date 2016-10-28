package utils;

import java.io.IOException;

/**
 *
 * Created by lever02 on 10/26/2016.
 *
 */
public class DownloadTask implements Runnable{

    private String url;
    private String directory;
    private String filename;

    public DownloadTask(String url, String directory, String filename) {
        this.url = url;
        this.directory = directory;
        this.filename = filename;
    }

    public void run() {
        try {
            FileUtils.saveUrl(url,directory,filename,null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Fucked up shit");
        }
    }
}
