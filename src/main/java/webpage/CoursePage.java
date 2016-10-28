package webpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Created by lever02 on 10/26/2016.
 *
 */
public class CoursePage {
    private WebDriver webDriver;

    @FindBy(tagName = "video")
    private WebElement video;

    @FindBy(xpath = "//section[@class='sidebar-area']//a")
    private List<WebElement> videosList;

    @FindBy(xpath = "//button[@title='Play']")
    private WebElement playButton;

    public CoursePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public List<String> iterateOverVideos(String url){
        webDriver.get(url);
        List<String> srcList = new ArrayList<>();
        List<String> hrefList = videosList.stream().map(webElement -> webElement.getAttribute("href")).collect(Collectors.toList());

        for (String s : hrefList) {
            if(!s.contains("/quiz/")) {
                webDriver.navigate().to(s);
            }
            new WebDriverWait(webDriver, 15).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Settings menu']")));
            System.out.println(video.getAttribute("src"));
            srcList.add(video.getAttribute("src"));
        }
        return srcList;
    }


}
