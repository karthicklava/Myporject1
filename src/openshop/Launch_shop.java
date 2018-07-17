package openshop;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class Launch_shop {

	public static void main(String[] args) throws IOException {
		System.out.println("Selenium");
		System.out.println(new Date());
     WebDriver f=new FirefoxDriver();
    f.manage().window().setSize(new org.openqa.selenium.Dimension(420,600));
     f.get("http://m.shopclues.com");
     

    
	
	
     }

}
