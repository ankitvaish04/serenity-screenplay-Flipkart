/*
package com.rsystem.uniprint;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.rsystem.uniprint.util.ExtentManager;
import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class DemoClass {	
	
	public ExtentReports rep = ExtentManager.getInstance();
	public ExtentTest test;
	public DocumentBuilderFactory documentFactory;
	public DocumentBuilder documentBuilder;
	public Document document;
	public TransformerFactory transformerFactory;
	public Transformer transformer;
	public DOMSource domSource;
	public StreamResult streamResult;
	public Element root;
	public Element preferences;
	final String xmlFilePath = new File(System.getProperty("user.dir").toString()) + "\\output.xml";
	DesktopOptions option;
	WiniumDriver driver;
	
	@BeforeClass
	public void setup() throws ParserConfigurationException, MalformedURLException {
		
		DesktopOptions option = new DesktopOptions();
		option.setApplicationPath("C:\\Windows\\system32\\notepad.exe");
		driver = new WiniumDriver(new URL("http://localhost:9999"), option);
		Reporter.log("Launched Notepad");
		
		documentFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentFactory.newDocumentBuilder();
		document = documentBuilder.newDocument();
		
	}
	
	@AfterClass
	public void teardown() throws TransformerException {
		
        // create the XML file
        // transform the DOM Object to an XML File
        transformerFactory = TransformerFactory.newInstance();
        transformer = transformerFactory.newTransformer();
        domSource = new DOMSource(document);
        streamResult = new StreamResult(new File(xmlFilePath));
        
        // You can use that for debugging
        transformer.transform(domSource, streamResult);
        System.out.println("Done creating XML File");
	}

	@Test(priority=0)
	public void printdemo() throws MalformedURLException, InterruptedException, ParserConfigurationException {
		
		driver.findElementByName("Untitled - Notepad").click();
		Thread.sleep(2000);
		
		Reporter.log("Clicking on " + driver.findElementByName("File").getAttribute("Name"));
		driver.findElementByName("File").click();
		
		Reporter.log("Clicking on " + driver.findElementByName("Print...").getAttribute("Name"));
		driver.findElementByName("Print...").click();
		Thread.sleep(2000);
		Reporter.log("Launched Printer Dialog");
		// Create 'print' Root element
		root = createXMLRootElement("print");
		
		Reporter.log("Clicking on " + driver.findElementByName("Preferences").getAttribute("Name"));
		driver.findElementByName("Preferences").click();
		// creating 'preferences' element under root node
		preferences = createXMLelement("preferences", root);

		selectPageSize("Legal");
		selectWaterMark("Sample");
				
		Reporter.log("Clicking " + driver.findElementByName("OK").getAttribute("Name") + " on Printer Preferences dialog");
		driver.findElementByName("OK").click();
				
		Reporter.log("Clicking " + driver.findElementByName("Apply").getAttribute("Name") + " on Print Dialog");
		driver.findElementByName("Apply").click();
		
		Reporter.log("Clicking " + driver.findElementByName("Cancel").getAttribute("Name") + " on Print Dialog");
		driver.findElementByName("Cancel").click();
		
		Reporter.log("Closed Notepad");
		driver.findElementByName("Close").click();
		
		//rep.endTest(test);
		//rep.flush();

	}
	
	*/
/*private void runCommand(String command){
		if( null != command){
			switch(command){
			case: PrintCommand
			}
		}
	}*//*

	
	public void selectPageSize(String pagesize) throws ParserConfigurationException {
		
		// TODO Auto-generated method stub
		
		Reporter.log("Clicking on " + driver.findElementByName("Basic").getAttribute("Name"));
		driver.findElementByName("Basic").click();		
		// creating 'basic' element under 'preferences' node
		Element basic = createXMLelement("basic", preferences);
        
		Reporter.log("Clicking on " + driver.findElementByName("Page Sizes...").getAttribute("Name"));
		driver.findElementByName("Page Sizes...").click();
        // creating 'page sizes' element under 'basic' node
		Element pagesizes = createXMLelement("pagesizes", basic);
		
		Reporter.log("Selecting page size " + driver.findElement(By.className("ListBox")).findElement(By.name(pagesize)).getAttribute("Name"));
		driver.findElement(By.className("ListBox")).findElement(By.name(pagesize)).click();
		// creating 'selectedpagesize' attribute under 'pagesizes' node with value
		createXMLelementWithValue("selectedpagesize", pagesizes, pagesize);
        
		Reporter.log("Clicking " + driver.findElementByName("OK").getAttribute("Name") + " on Page Sizes dialog");
		driver.findElementByName("OK").click();
		
	}
	
	public void selectWaterMark(String type) throws ParserConfigurationException {
		
		// TODO Auto-generated method stub
		
		Reporter.log("Clicking " + driver.findElementByName("Advanced").getAttribute("Name") + " tab on Printer Preferences dialog");
		driver.findElementByName("Advanced").click();
		// creating 'advanced' element under 'preferences' node
		Element advanced = createXMLelement("advanced", preferences);

		Reporter.log("Clicking on Watermark Plugin");
		driver.findElementById("5057").click();
		// creating 'watermark' element under 'advanced' node with value
		Element watermark = createXMLelement("watermark", advanced);
		
		Reporter.log("Clicking on Select Watermark dropdown");
		driver.findElementByName("Drop Down Button").click();
		
		Reporter.log("Selecting " + driver.findElementByName(type).getAttribute("Name") + " watermark");
		driver.findElementByName(type).click();
		// creating 'selectedwatermark' attribute under 'watermark' node with value
		createXMLelementWithValue("selectedwatermark", watermark, type);
			
		Reporter.log("Clicking " + driver.findElementByName("OK").getAttribute("Name") + " on Watermark dialog");
		driver.findElementByName("OK").click();		
		
	}

	public Element createXMLelement(String elementName, Element parentName) throws ParserConfigurationException {
		
		Element element = document.createElement(elementName);
		parentName.appendChild(element);
		return element;
        
	}
	
	public Element createXMLelementWithValue(String elementName, Element parentName, String value) throws ParserConfigurationException {
		
		Element element = document.createElement(elementName);
		parentName.appendChild(element);
		element.appendChild(document.createTextNode(value));
		return element;
        
	}
	
	public Element createXMLRootElement(String elementName) throws ParserConfigurationException {
		
		Element root = document.createElement(elementName);
		document.appendChild(root);
		return root;
        
	}

}
*/
