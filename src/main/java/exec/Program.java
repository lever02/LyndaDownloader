package exec;

import utils.DownloadTask;
import webpage.CoursePage;
import webpage.LinkedinLoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by lever02 on 10/26/2016.
 *
 */
public class Program {

    public static void main(String[] args) throws InterruptedException, IOException {
        LinkedinLoginPage linkedinLoginPage = null;
        try {
            Properties prop = initProp();
            linkedinLoginPage = new LinkedinLoginPage(prop);
            CoursePage coursePage = linkedinLoginPage.login();

            String courseUrl = prop.getProperty("CourseUrl");

            List<String> videosSrc = coursePage.iterateOverVideos(courseUrl);

            ExecutorService pool = Executors.newFixedThreadPool(3);
            for (String videoUrl : videosSrc) {
                pool.submit(new DownloadTask(videoUrl, System.getProperty("user.home")+"/Course/" + courseUrl.substring(courseUrl.lastIndexOf("/") + 1), null));
            }
            pool.shutdown();
            pool.awaitTermination(120, TimeUnit.MINUTES);

        } finally {
            if (linkedinLoginPage != null) {
                linkedinLoginPage.getWebDriver().quit();
            }
        }
    }

    private static Properties initProp() throws IOException {
        Properties prop;
        prop = new Properties();
        prop.load(new FileInputStream("locations.properties"));
        if (prop.getProperty("CourseUrl") == null || prop.getProperty("Username") == null || prop.getProperty("Password") == null){
            throw new IOException("Properties File must contains CourseUrl & Username & Password");
        }
        return prop;
    }
}
