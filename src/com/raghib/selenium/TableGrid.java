package com.raghib.selenium;

/**
 * Reference:-
 * C:\STUDY_DATA\TESTING\New folder_2\1-B_SELANIUM\
 * 0.1Udemy - Selenium WebDriver with Java -Basics to Advanced+Frameworks\
 * 14. Miscellaneous topics in Selenium WebDriver
 * 
 */

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TableGrid extends BaseClass {

	public static WebDriver driver;
	public static String browserName = "chrome";
	public static String browserVersion = "116";
	
	public static String url = "http://www.cricbuzz.com/live-cricket-scorecard/18970/pak-vs-sl-2nd-t20i-pakistan-v-sri-lanka-in-uae-2017";
	
	public static void main(String[] args) throws InterruptedException {
		int sum = 0;
		// Chrome Browser
		driver = BaseClass.getDriver(browserName, browserVersion);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(url);

		WebElement table = driver.findElement(By.cssSelector("div[class='cb-col cb-col-100 cb-ltst-wgt-hdr']"));
		//Third Column
		int count = table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)"))
				.size();

		for (int i = 0; i < count-2; i++) {
			String value = table
					.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).get(i)
					.getText();
			int valueinteger = Integer.parseInt(value);
			sum = sum + valueinteger;
		}
		System.out.println("Without Extra "+sum); // 120
		
		String Extras = driver.findElement(By.xpath("//div[text()='Extras']/following-sibling::div")).getText();
		int extrasValue = Integer.parseInt(Extras);
		int TotalSumValue = sum + extrasValue; // 120 + 4
		System.out.println("With Extra "+TotalSumValue); //124

		String ActualTotal = driver.findElement(By.xpath("//div[text()='Total']/following-sibling::div")).getText();
		int ActualTotalVAlue = Integer.parseInt(ActualTotal);
		if (ActualTotalVAlue == TotalSumValue) {
			System.out.println("Count Matches");
		} else {
			System.out.println("count fails");
		}
		//Thread.sleep(5000);
		//BaseClass.quitDriver();
	}
}