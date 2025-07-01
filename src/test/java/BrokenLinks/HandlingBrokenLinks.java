package BrokenLinks;

import TestBase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class HandlingBrokenLinks extends TestBase {
 

	@Test
	public void brokenLinks() throws IOException {

		init();

		driver=  launchBrowser(Param.getProperty("browser"),false);
		driver.get("http://www.deadlinkcity.com/");
		driver.manage().window().maximize();
		
		// Capture all the links on the page
		
		 List<WebElement> links = driver.findElements(By.tagName("a"));
		 System.out.println("Total number of links on the page: " + links.size());
		 
		 // Loop through each link and check if it is broken
		 int noofBrokenLinks = 0;
		 for(WebElement linkElements : links)
		 {
			 
			String hrefurl = linkElements.getAttribute("href");
			if(hrefurl == null || hrefurl.isEmpty()) {
				System.out.println("URL is empty");
				continue;
		 }
			try {
			// hit url to the server and get the response code
			URL linkURL = new URL(hrefurl);// Convert string to URL
			HttpURLConnection httpURLConnect = (HttpURLConnection) linkURL.openConnection();// open connection
			httpURLConnect.connect();// connect to the URL
			
			// get response code
			int responseCode = httpURLConnect.getResponseCode();
			if(responseCode >= 400) {
				System.out.println(hrefurl + " - " + responseCode + " - Broken Link");
				noofBrokenLinks++;
			}
			else {
				System.out.println(hrefurl + " - " + responseCode + " - Valid Link");
			}
			}
			catch(Exception e)
			{
				System.out.println("Exception caught: " + e.getMessage());
			}
			// close the connection
			
			System.out.println("Total number of broken links: " + noofBrokenLinks);
			
	}
	}
	}


