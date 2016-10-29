# LyndaDownloader
<a href="https://medium.com/@lever02176/download-that-course-11655185b18d">Less than a minute read : The Story Behind</a>

lynda course downloader

to run the app all you need to do is
Download the proper chromedriver i recommend the <a href="http://chromedriver.storage.googleapis.com/index.html?path=2.25/">latest</a>
extract it to the root folder ( next to location.properties )
edit/fill the "location.properies" file
and run the main from Program class

or you can use maven to pack the app - just use **"mvn install"**
and then get the Linkedin Video Downloader\target\LinkedInVideoDownloader-jar-with-dependencies.jar
and run it with **"java -jar LinkedInVideoDownloader-jar-with-dependencies.jar"** while location.properties and the 
chromedriver file ( the executable within the zip) are in the same path of the jar

