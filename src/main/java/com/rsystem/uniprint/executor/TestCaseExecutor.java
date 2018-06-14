package com.rsystem.uniprint.executor;

import com.rsystem.uniprint.domain.Property;
import com.rsystem.uniprint.domain.TestCase;
import com.thoughtworks.selenium.webdriven.Timer;
import com.thoughtworks.selenium.webdriven.commands.SetTimeout;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestCaseExecutor {

    private TestCase testCase;
    private WiniumDriver driver;

    public TestCaseExecutor(final TestCase testCase){
        this.testCase = testCase;
        //init();
    }

    public void init() {
        DesktopOptions option = new DesktopOptions();
        option.setApplicationPath(testCase.getFile());
        try {
        	driver = new WiniumDriver(new URL("http://localhost:9999"), option);
        	System.out.println("Launching Application: " + testCase.getFile());
        	start();
        }catch(Exception e) {
        	System.out.println("Error launching application Or Invalid Application Name/File Path: " + testCase.getFile());
        	System.out.println("Terminating Program...");
        }
    }

    private void afterStart() throws InterruptedException {
		System.out.println("Clicking " + driver.findElementByName("OK").getAttribute("Name") + " on Printer Preferences dialog");
		driver.findElementByName("OK").click();
		Thread.sleep(2000);
		try {
			driver.findElementByName("Apply").isDisplayed();
			System.out.println("Clicking " + driver.findElementByName("Apply").getAttribute("Name") + " on Print Dialog");
			driver.findElementByName("Apply").click();
		}catch(NoSuchElementException e) {
			System.out.println("Apply button does not exist on Print Dialog");
		}

		try {
			driver.findElementByName("Cancel").isDisplayed();
			System.out.println("Clicking " + driver.findElementByName("Cancel").getAttribute("Name") + " on Print Dialog");
			driver.findElementByName("Cancel").click();
		}catch(NoSuchElementException e) {
			System.out.println("Cancel button does not exist on Print Dialog");
		}

	}

    private void beforeStart() throws InterruptedException {
		//driver.findElementByName("Untitled - Notepad").click();
		Thread.sleep(2000);
		System.out.println("Clicking on " + driver.findElementByName("File").getAttribute("Name"));
		driver.findElementByName("File").click();
		System.out.println("Clicking on " + driver.findElementByName("Print...").getAttribute("Name"));
		driver.findElementByName("Print...").click();
		Thread.sleep(2000);
		System.out.println("Launching Printer Dialog");

	}

    private void destroy() {
        //driver.close();
    	System.out.println("Closing Application: " + testCase.getFile());
    	System.out.println("-------------------------------------------------------------------------");
    	driver.findElementByName("File").click();
    	driver.findElementByName("Exit").click();
    }

    private void start() throws InterruptedException, AWTException {
        /* Test Start */
    	
        beforeStart();
        selectPrintProfile();
        System.out.println("Clicking on " + driver.findElementByName("Preferences").getAttribute("Name"));
        driver.findElementByName("Preferences").click();
       
        for (Property property : testCase.getProperties()) {
            Map<String, Object> attribute = property.getAttribute();
            switch (property.getType()) {
                case ("pagesize"):
                    selectPageSize(attribute);
                    break;
                case ("watermark"):
                    selectWaterMark(attribute);
                	break;
                case ("mediatype"):
                    selectMediaType(attribute);
                	break;
                case ("collate"):
                    setCollate(attribute);
                	break;
                case ("orientation"):
                    setOrientation(attribute);
                	break;
                case ("duplex"):
                    setDuplex(attribute);
                	break;
                case ("Eco Print"):
                    setEchoPrint(attribute);
                	break;
                
                	
            }
        }
        afterStart();
        sendPrintCommand();
        destroy();
    }

    private void setEchoPrint(Map<String, Object> ecoprint) throws InterruptedException {// TODO Auto-generated method stub
    	System.out.println("Clicking " + driver.findElementByName("Basic").getAttribute("Name") + " tab on Printer Preferences dialog");
		driver.findElementByName("Basic").click();
		
    	if(ecoprint.get("value").toString().equalsIgnoreCase("off")) {
			driver.findElementById("5166").click();
			System.out.println("Selecting Ecoprint " + ecoprint.get("value").toString());
		}else if(ecoprint.get("value").toString().equalsIgnoreCase("on")) {
			driver.findElementById("5167").click();
			System.out.println("Selecting Ecoprint " + ecoprint.get("value").toString());
		}else System.out.println("Invalid value for EcoPrint option");
    	
    	Thread.sleep(1000);
	}

    private void setDuplex(Map<String, Object> duplex) throws InterruptedException {// TODO Auto-generated method stub
    	System.out.println("Clicking " + driver.findElementByName("Basic").getAttribute("Name") + " tab on Printer Preferences dialog");
		driver.findElementByName("Basic").click();
		
    	if(duplex.get("value").toString().equalsIgnoreCase("off")) {
			driver.findElementById("5172").click();
			System.out.println("Selecting Orientation " + duplex.get("value").toString());
		}else if(duplex.get("value").toString().equalsIgnoreCase("flip on long edge")) {
			driver.findElementById("5173").click();
			System.out.println("Selecting Orientation " + duplex.get("value").toString());
		}else if(duplex.get("value").toString().equalsIgnoreCase("flip on short edge")) {
			driver.findElementById("5174").click();
			System.out.println("Selecting Orientation " + duplex.get("value").toString());
		}else System.out.println("Invalid value for Duplex option");
    	
    	Thread.sleep(1000);
	}

    private void setOrientation(Map<String, Object> orientation) throws InterruptedException {// TODO Auto-generated method stub
    	System.out.println("Clicking " + driver.findElementByName("Basic").getAttribute("Name") + " tab on Printer Preferences dialog");
		driver.findElementByName("Basic").click();
		
    	if(orientation.get("value").toString().equalsIgnoreCase("portrait")) {
			driver.findElementById("5168").click();
			System.out.println("Selecting Orientation " + orientation.get("value").toString());
		}else if(orientation.get("value").toString().equalsIgnoreCase("landscape")) {
			driver.findElementById("5170").click();
			System.out.println("Selecting Orientation " + orientation.get("value").toString());
		}else if(orientation.get("value").toString().equalsIgnoreCase("rotated portrait")) {
			driver.findElementById("5169").click();
			System.out.println("Selecting Orientation " + orientation.get("value").toString());
		}else if(orientation.get("value").toString().equalsIgnoreCase("rotated landscape")) {
			driver.findElementById("5171").click();
			System.out.println("Selecting Orientation " + orientation.get("value").toString());
		}else System.out.println("Invalid value for Orientation option");
    	
    	Thread.sleep(1000);
	}

    private void setCollate(Map<String, Object> collate) throws InterruptedException {// TODO Auto-generated method stub
    	System.out.println("Clicking " + driver.findElementByName("Basic").getAttribute("Name") + " tab on Printer Preferences dialog");
		driver.findElementByName("Basic").click();
		
    	if(collate.get("value").toString().equalsIgnoreCase("on")) {
			driver.findElementById("5162").click();
			System.out.println("Selecting Collate " + collate.get("value").toString());
		}else if(collate.get("value").toString().equalsIgnoreCase("off")) {
			driver.findElementById("5161").click();
			System.out.println("Selecting Collate " + collate.get("value").toString());
		}else System.out.println("Invalid value for Collate option");
    	
    	Thread.sleep(1000);
	}

    private void selectPrintProfile() {/* TODO Auto-generated method stub*/

		//Select Printer Profile
		driver.findElementByName(testCase.getProfile()).click();
		System.out.println("Selecting printer profile as: " + testCase.getProfile().toString());
		
	}

    private void selectPageSize(Map<String, Object> pagesize) throws AWTException, InterruptedException {/* TODO Auto-generated method stub*/
    	System.out.println("Clicking " + driver.findElementByName("Basic").getAttribute("Name") + " tab on Printer Preferences dialog");
		driver.findElementByName("Basic").click();
		
		System.out.println("Clicking on " + driver.findElementByName("Page Sizes...").getAttribute("Name"));
		driver.findElementByName("Page Sizes...").click();
		
/*		Actions keyAction = new Actions(driver); 
		keyAction.keyDown(Keys.CONTROL).keyDown(Keys.HOME).build().perform();
		keyAction.keyUp(Keys.CONTROL).keyUp(Keys.HOME).build().perform();
		
		System.out.println("TESTTTT");*/
		
		List<WebElement> l = driver.findElements(By.xpath("/*[contains(@ControlType,'ControlType.Window') and contains(@ClassName,'Notepad')]"
												 + "//*[contains(@ClassName,'#32770') and contains(@Name,'Print')]"
												 + "//*[contains(@ClassName,'#32770') and contains(@Name,'Printing Preferences')]"
												 + "//*[contains(@ClassName,'#32770') and contains(@Name,'Page Sizes')]"
												 + "//*[contains(@ClassName,'ListBox') and contains(@AutomationId,'4107')]"
												 + "//*[contains(@ControlType,'ControlType.ListItem') and contains(@LocalizedControlType,'list item')]"
												 ));
		
		//Moving at the top of Page Sizes list
		Robot actionObj = new Robot();
		actionObj.keyPress(KeyEvent.VK_CONTROL);
		actionObj.keyPress(KeyEvent.VK_HOME);
		actionObj.keyRelease(KeyEvent.VK_CONTROL);
		actionObj.keyRelease(KeyEvent.VK_HOME);
		
		//Selecting the required page size
		for(int i=0;i<l.size();i++) {
			if(l.get(i).getAttribute("Name").equals(pagesize.get("value").toString())) {
				while(!l.get(i).isDisplayed()) {
					driver.findElementById("SmallIncrement").click();
					//actionObj.keyPress(KeyEvent.VK_DOWN);
					//Thread.sleep(100);
				}
				System.out.println("Selecting Page Size value as: " + l.get(i).getAttribute("Name"));
				l.get(i).click();
				System.out.println("Clicking " + driver.findElementByName("OK").getAttribute("Name") + " on Page Sizes dialog");
				driver.findElementByName("OK").click();
				break;
			}
			if(i==l.size()-1) {
				System.out.println("Page Size: " + pagesize.get("value").toString() + " not found!");
				System.out.println("Clicking " + driver.findElementByName("OK").getAttribute("Name") + " on Page Sizes dialog");
				driver.findElementByName("OK").click();
			}
		}
    }

    private void selectWaterMark(Map<String, Object> watermark) throws InterruptedException {/* TODO Auto-generated method stub*/
        System.out.println("Clicking " + driver.findElementByName("Advanced").getAttribute("Name") + " tab on Printer Preferences dialog");
        driver.findElementByName("Advanced").click();
        System.out.println("Clicking on Watermark Plugin");
        driver.findElementById("5057").click();
        System.out.println("Clicking on Select Watermark dropdown");
        driver.findElementByName("Drop Down Button").click();
        try {
        	driver.findElementByName(watermark.get("value").toString());
        	System.out.println("Selecting Watermark value as: " + driver.findElementByName(watermark.get("value").toString()).getAttribute("Name"));
            driver.findElementByName(watermark.get("value").toString()).click();
        }catch(NoSuchElementException e) {
        	System.out.println("Watermark value: " + watermark.get("value").toString() + " not found!");
        	driver.findElementByName("Drop Down Button").click();
        }finally {
        	System.out.println("Clicking " + driver.findElementByName("OK").getAttribute("Name") + " on Watermark dialog");
            driver.findElementByName("OK").click();
            Thread.sleep(2000);
        }
    }

    private void selectMediaType(Map<String, Object> mediatype) throws InterruptedException {/* TODO Auto-generated method stub*/
        System.out.println("Clicking " + driver.findElementByName("Basic").getAttribute("Name") + " tab on Printer Preferences dialog");
        driver.findElementByName("Basic").click();
        System.out.println("Clicking on Media Type dropdown");
        driver.findElementById("4124").click();
        try {
        	driver.findElementByName(mediatype.get("value").toString());
        	System.out.println("Selecting Media Type value as: " + driver.findElementByName(mediatype.get("value").toString()).getAttribute("Name"));
            driver.findElementByName(mediatype.get("value").toString()).click();
        }catch(NoSuchElementException e) {
        	System.out.println("Media Type value: " + mediatype.get("value").toString() + " not found!");
        	driver.findElementById("4124").click();
        }
    }

    private void sendPrintCommand() {
        /*driver.findElementByName("Print").click();*/
        System.out.println("Sent Print command to the printer with profile " + testCase.getProfile().toString());

    }
}
