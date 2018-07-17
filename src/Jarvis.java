import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class Jarvis {

	static WebDriver driver, downloadDriver;
	static FirefoxProfile profile ;
	static int msgCnt, imgCnt;
	static String msg, folderName, imgName;
	static Properties prop;
	static Robot r,er;
	
	public static void clickwait(WebDriver driver)
	{
		Wait wait = new FluentWait(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
		            public Boolean apply(WebDriver driver) 
		            {
		            	Boolean status=null;
		            	try{
		            	
		            	if(((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"))
		            		status=true;
		            	else
		            		status=false;
		                
		            	}
		            	catch(WebDriverException ex)
		            	{
		            		System.out.println("JavaScriptError");
		            		
		            	}
		            	return status;
		            }
		        };
		wait.until(pageLoadCondition);
		try 
		{
			Thread.sleep(200);
		} catch (InterruptedException e) 
		{
			
			e.printStackTrace();
		}		
	}

	public void screenshot()
	{
		BufferedImage image;
		try {
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png", new File(folderName+imgName+imgCnt+".png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public String listen(WebDriver driver)
	{
		
		Wait wait = new WebDriverWait(driver, 50000);
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
		            public Boolean apply(WebDriver driver) 
		            {
		            	List<WebElement> msgs = driver.findElements(By.xpath("//div[@class='clearfix _o46 _3erg _3i_m _nd_ direction_ltr text_align_ltr']"));
		            	Boolean status=null;
		            	if(msgs.size()>msgCnt)
		            		status = true;
		            	msg = msgs.get(msgs.size()-1).getText();
		            	msgCnt = msgs.size();
		            	return status;
		            }
		        };
		wait.until(pageLoadCondition);
		
		return msg;
	}

	public void sendMessage(WebDriver driver, String text)
	{
//		driver.findElement(By.xpath("//div[@class='_1mf _1mj']")).sendKeys(text);
//		driver.findElement(By.xpath("//div[contains(text(), 'Type a message...')]")).sendKeys(Keys.ENTER);
		Actions actions = new Actions(driver);
		 actions.moveToElement(driver.findElement(By.xpath("//div[@class='_1mf _1mj']")));
		 actions.click();
		 actions.sendKeys(text);
		 actions.build().perform();
		 actions.sendKeys(Keys.ENTER);
		 actions.build().perform();

		 
	}
	
	public void doType(int keyCode)
	{
		r.keyPress(keyCode);
		r.keyRelease(keyCode);
		
	}
	
	public void doType(int keyCode1, int keyCode2)
	{
		r.keyPress(keyCode1);
		r.keyPress(keyCode2);
		r.keyRelease(keyCode2);
		r.keyRelease(keyCode1);
	}
	
	public void type(char character) {
        switch (character) {
        case 'a': doType(KeyEvent.VK_A); break;
        case 'b': doType(KeyEvent.VK_B); break;
        case 'c': doType(KeyEvent.VK_C); break;
        case 'd': doType(KeyEvent.VK_D); break;
        case 'e': doType(KeyEvent.VK_E); break;
        case 'f': doType(KeyEvent.VK_F); break;
        case 'g': doType(KeyEvent.VK_G); break;
        case 'h': doType(KeyEvent.VK_H); break;
        case 'i': doType(KeyEvent.VK_I); break;
        case 'j': doType(KeyEvent.VK_J); break;
        case 'k': doType(KeyEvent.VK_K); break;
        case 'l': doType(KeyEvent.VK_L); break;
        case 'm': doType(KeyEvent.VK_M); break;
        case 'n': doType(KeyEvent.VK_N); break;
        case 'o': doType(KeyEvent.VK_O); break;
        case 'p': doType(KeyEvent.VK_P); break;
        case 'q': doType(KeyEvent.VK_Q); break;
        case 'r': doType(KeyEvent.VK_R); break;
        case 's': doType(KeyEvent.VK_S); break;
        case 't': doType(KeyEvent.VK_T); break;
        case 'u': doType(KeyEvent.VK_U); break;
        case 'v': doType(KeyEvent.VK_V); break;
        case 'w': doType(KeyEvent.VK_W); break;
        case 'x': doType(KeyEvent.VK_X); break;
        case 'y': doType(KeyEvent.VK_Y); break;
        case 'z': doType(KeyEvent.VK_Z); break;
        case 'A': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_A); break;
        case 'B': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_B); break;
        case 'C': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_C); break;
        case 'D': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_D); break;
        case 'E': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_E); break;
        case 'F': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_F); break;
        case 'G': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_G); break;
        case 'H': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_H); break;
        case 'I': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_I); break;
        case 'J': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_J); break;
        case 'K': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_K); break;
        case 'L': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_L); break;
        case 'M': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_M); break;
        case 'N': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_N); break;
        case 'O': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_O); break;
        case 'P': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_P); break;
        case 'Q': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Q); break;
        case 'R': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_R); break;
        case 'S': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_S); break;
        case 'T': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_T); break;
        case 'U': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_U); break;
        case 'V': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_V); break;
        case 'W': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_W); break;
        case 'X': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_X); break;
        case 'Y': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Y); break;
        case 'Z': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Z); break;
        case '`': doType(KeyEvent.VK_BACK_QUOTE); break;
        case '0': doType(KeyEvent.VK_0); break;
        case '1': doType(KeyEvent.VK_1); break;
        case '2': doType(KeyEvent.VK_2); break;
        case '3': doType(KeyEvent.VK_3); break;
        case '4': doType(KeyEvent.VK_4); break;
        case '5': doType(KeyEvent.VK_5); break;
        case '6': doType(KeyEvent.VK_6); break;
        case '7': doType(KeyEvent.VK_7); break;
        case '8': doType(KeyEvent.VK_8); break;
        case '9': doType(KeyEvent.VK_9); break;
        case '-': doType(KeyEvent.VK_MINUS); break;
        case '=': doType(KeyEvent.VK_EQUALS); break;
        case '~': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE); break;
        case '!': doType(KeyEvent.VK_EXCLAMATION_MARK); break;
        case '@': doType(KeyEvent.VK_AT); break;
        case '#': doType(KeyEvent.VK_NUMBER_SIGN); break;
        case '$': doType(KeyEvent.VK_DOLLAR); break;
        case '%': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_5); break;
        case '^': doType(KeyEvent.VK_CIRCUMFLEX); break;
        case '&': doType(KeyEvent.VK_AMPERSAND); break;
        case '*': doType(KeyEvent.VK_ASTERISK); break;
        case '(': doType(KeyEvent.VK_LEFT_PARENTHESIS); break;
        case ')': doType(KeyEvent.VK_RIGHT_PARENTHESIS); break;
        case '_': doType(KeyEvent.VK_UNDERSCORE); break;
        case '+': doType(KeyEvent.VK_PLUS); break;
        case '\t': doType(KeyEvent.VK_TAB); break;
        case '\n': doType(KeyEvent.VK_ENTER); break;
        case '[': doType(KeyEvent.VK_OPEN_BRACKET); break;
        case ']': doType(KeyEvent.VK_CLOSE_BRACKET); break;
        case '\\': doType(KeyEvent.VK_BACK_SLASH); break;
        case '{': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET); break;
        case '}': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET); break;
        case '|': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH); break;
        case ';': doType(KeyEvent.VK_SEMICOLON); break;
        case ':': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_SEMICOLON); break;
        case '\'': doType(KeyEvent.VK_QUOTE); break;
        case '"': doType(KeyEvent.VK_QUOTEDBL); break;
        case ',': doType(KeyEvent.VK_COMMA); break;
        case '<': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA); break;
        case '.': doType(KeyEvent.VK_PERIOD); break;
        case '>': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD); break;
        case '/': doType(KeyEvent.VK_SLASH); break;
        case '?': doType(KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH); break;
        case ' ': doType(KeyEvent.VK_SPACE); break;
        default:
            System.out.println("Invalid Character");
            break;
        }
	}
	
	public void action(String command)
	{
		String commands[] = command.split(" ");
		try
		{
		if(commands[0].trim().equalsIgnoreCase("shutdown"))
		{
			driver.close();
			driver.quit();
			Runtime.getRuntime().exec("shutdown -s -f");
			
		}
		else if(commands[0].trim().equalsIgnoreCase("download"))
		{
			if(prop.getProperty("use_idm").equalsIgnoreCase("yes"))
			{
			String str = prop.getProperty("idm_path")+" /d  "+commands[1]+" /n";
			Runtime.getRuntime().exec(str);
			Thread.sleep(5000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(3000);
			sendMessage(driver, "We have started your download in IDM Sir");
			}
			else
			{
				downloadDriver = new FirefoxDriver(profile);
				driver.get(commands[1]);
				clickwait(downloadDriver);
				Thread.sleep(5000);
				sendMessage(driver, "We have started your download in FireFox Sir");
			}
		}
		else if(commands[0].trim().equalsIgnoreCase("screenshot"))
		{
			screenshot();
			String filenameArray = folderName+imgName+imgCnt+".png";
			driver.findElement(By.xpath("//input[@accept='image/*']")).click();
			driver.findElement(By.partialLinkText("Add Photos")).click();
			System.out.println(filenameArray);
			Thread.sleep(2000);
			for(char c: filenameArray.toCharArray())
			{
				System.out.println(c);
				type(c);
			}
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			r.waitForIdle();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			er.keyPress(KeyEvent.VK_ENTER);
			er.keyRelease(KeyEvent.VK_ENTER);
			driver.findElement(By.xpath("//div[@class='_1mf _1mj']")).sendKeys(Keys.ENTER);
		}
		else{
			sendMessage(driver,"I don't understand Kili Kili Language");
			sendMessage(driver,"1. download <<url>>");
			sendMessage(driver,"2. screenshot");
			sendMessage(driver,"3. shutdown");
			msgCnt += 4;
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void login(WebDriver driver)
	{
		
	    driver.get("https://www.facebook.com/messages/t/"+prop.getProperty("fb_profileid"));
	    driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys(prop.getProperty("fb_profileid"));
		driver.findElement(By.id("pass")).sendKeys(prop.getProperty("fb_password"));
		driver.findElement(By.id("loginbutton")).click();	
		sendMessage(driver,"Hello Sir! How may I help you?");
		
		//driver.findElement(By.xpath("//div[@class='_1mf _1mj']")).click();
	}
	
	//idman.exe /d  http://www.starmusiq.com/download/?songID=888 /p F:\ /f summa
	
	public static void main(String[] args) throws AWTException, IOException 
	{
		File propFile = new File(System.getProperty("user.dir")+"\\config.properties");
		FileInputStream propFIS = new FileInputStream(propFile);
		prop = new Properties();
		prop.load(propFIS);
		
		SimpleDateFormat sf = new SimpleDateFormat("mmm dd hh mm");
		imgName = sf.format(new Date());
		File screenshotFolder = new File(System.getProperty("user.dir")+"\\Screenshot");
		screenshotFolder.mkdir();
		folderName = System.getProperty("user.dir")+"\\Screenshot\\";
		
		Jarvis obj = new Jarvis();
		profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.dir", prop.getProperty("downloadPath"));
		driver = new FirefoxDriver();
		obj.login(driver);
		r = new Robot();
		er = new Robot();
		List<WebElement> messageList = driver.findElements(By.className("__i_"));
		msgCnt = messageList.size();
		String message ="";
		for(;message!="shutdown";)
		{
			message = obj.listen(driver);
			obj.action(message);
		}
	}

}
