package com.adidas.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.adidas.base.Constants;
import com.adidas.base.BaseSetup;

public class FunctionsClass {
	private static final String TAG_NAME = "tagname";
	private static final String CSS_SELECTOR = "cssselector";
	private static final String LINK_TEXT = "linktext";
	private static final String XPATH = "xpath";
	private static final String NAME = "name";
	private static final String ID = "id";
	private static final String CLASS_NAME = "classname";
	private static final String TEXT_IS_NOT_CLEARED = "Text is not cleared";
	WebDriver driver;
	private static String directory = "./Current test results/Reports/";
	private static String oldReportsDirectory = "./Arhived test results/";
	private static String subDirectory = "screenshots/";

	public FunctionsClass(WebDriver driver) {
		this.driver = driver;
	}

	public FunctionsClass() {
		// Blank Constructor
	}

	/**
	 * Function to read Excel Data and getting the result in List for a particular
	 * row
	 */
	public List<String> ExcelReader(String sheetName, String columnName) throws IOException {
		List<String> excelData = new ArrayList<>();
		Workbook wb = null;
		try {
			FileInputStream fis = new FileInputStream("./src/main/Resources/TestData.xlsx");
			wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet(sheetName);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.toString().equalsIgnoreCase(columnName)) {
						int rowNum = cell.getRowIndex();
						for (int i = rowNum; i <= rowNum; i++) {
							for (int j = 1; j < sheet.getRow(rowNum).getLastCellNum(); j++) {
								Row row1 = sheet.getRow(i);
								if ((row1.getCell(j).getCellType().toString()).contains("NUMERIC")) {
									Cell cell1 = row1.getCell(j);
									excelData.add(NumberToTextConverter.toText(cell1.getNumericCellValue()));
								} else {
									Cell cell1 = row1.getCell(j);
									excelData.add(cell1.toString());
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			BaseSetup.logger.error("Excel File is not readable" + e);
		} finally {
			if (wb != null) {
				wb.close();
			}
		}
		return excelData;
	}

	/** Enum for Locators */
	public enum locatorType {
		XPATH, NAME, ID, CLASSNAME, LINKTEXT, CSSSELECTOR, TAGNAME,
	}

	/** Function to create mobile element using different locators */
	public WebElement createElement(String locator, locatorType selector) {
		WebElement element = null;
		try {
			if (locator != null) {
				switch (selector.toString().toLowerCase()) {
				case CLASS_NAME:
					element = driver.findElement(By.className(locator));
					break;
				case ID:
					element = driver.findElement(By.id(locator));
					break;
				case NAME:
					element = driver.findElement(By.name(locator));
					break;
				case XPATH:
					element = driver.findElement(By.xpath(locator));
					break;
				case LINK_TEXT:
					element = driver.findElement(By.linkText(locator));
					break;
				case CSS_SELECTOR:
					element = driver.findElement(By.cssSelector(locator));
					break;
				case TAG_NAME:
					element = driver.findElement(By.tagName(locator));
					break;
				default:
					element = driver.findElement(By.xpath(locator));
					break;
				}
			} else {
				BaseSetup.logger.error("Locator is null");
			}
		} catch (Exception e) {
			BaseSetup.logger.error("Locator is not correct" + e);
		}
		return element;
	}

	/** Method used to wait until element is found */
	public void waitUntilFound(String locator, locatorType selector, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			if (locator != null) {
				switch (selector.toString().toLowerCase()) {
				case CLASS_NAME:
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
					break;
				case ID:
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
					break;
				case NAME:
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
					break;
				case XPATH:
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					break;
				case LINK_TEXT:
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locator)));
					break;
				case CSS_SELECTOR:
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
					break;
				case TAG_NAME:
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locator)));
					break;
				default:
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					break;
				}
			} else {
				BaseSetup.logger.info("Element not found");
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	/** Method used to wait until element is Clickable */
	public void waitUntilClickable(String locator, locatorType selector, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			if (locator != null) {
				switch (selector.toString().toLowerCase()) {
				case CLASS_NAME:
					wait.until(ExpectedConditions.elementToBeClickable(By.className(locator)));
					break;
				case ID:
					wait.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
					break;
				case NAME:
					wait.until(ExpectedConditions.elementToBeClickable(By.name(locator)));
					break;
				case XPATH:
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
					break;
				case LINK_TEXT:
					wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locator)));
					break;
				case CSS_SELECTOR:
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
					break;
				case TAG_NAME:
					wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locator)));
					break;
				default:
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
					break;
				}

			} else {
				BaseSetup.logger.info("Element not clickable");
			}

		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	/** Method used to click on element using xpath */
	public void clickElement(String locator, locatorType selector) {
		try {
			if (locator != null) {
				switch (selector.toString().toLowerCase()) {
				case CLASS_NAME:
					driver.findElement(By.className(locator)).click();
					break;
				case ID:
					driver.findElement(By.id(locator)).click();
					break;
				case NAME:
					driver.findElement(By.name(locator)).click();
					break;
				case XPATH:
					driver.findElement(By.xpath(locator)).click();
					break;
				case LINK_TEXT:
					driver.findElement(By.linkText(locator)).click();
					break;
				case CSS_SELECTOR:
					driver.findElement(By.cssSelector(locator)).click();
					break;
				case TAG_NAME:
					driver.findElement(By.tagName(locator)).click();
					break;
				default:
					driver.findElement(By.xpath(locator)).click();
					break;
				}
			} else {
				BaseSetup.logger.info("Element is null");
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	/** Method used to click on element using web element */
	public void clickElement(WebElement element) {
		try {
			if (element != null) {
				element.click();
			} else {
				BaseSetup.logger.info("Elemnt not clickable");
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}
	
	/** Method used to click on element using web element */
	public void clickElement(String xpath) {
		try {
			if (xpath != null) {
				driver.findElement(By.xpath(xpath)).click();
			} else {
				BaseSetup.logger.info("Elemnt not clickable");
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	/** Method used to create list of elements */
	public List<WebElement> createElements(String locator, locatorType selector) {
		List<WebElement> elements = null;
		try {
			if (locator != null) {
				switch (selector.toString().toLowerCase()) {
				case CLASS_NAME:
					elements = driver.findElements(By.className(locator));
					break;
				case ID:
					elements = driver.findElements(By.id(locator));
					break;
				case NAME:
					elements = driver.findElements(By.name(locator));
					break;
				case XPATH:
					elements = driver.findElements(By.xpath(locator));
					break;
				case LINK_TEXT:
					elements = driver.findElements(By.linkText(locator));
					break;
				case CSS_SELECTOR:
					elements = driver.findElements(By.cssSelector(locator));
					break;
				case TAG_NAME:
					elements = driver.findElements(By.tagName(locator));
					break;
				default:
					elements = driver.findElements(By.xpath(locator));
					break;
				}
			} else {
				BaseSetup.logger.info("locator not found");
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
		return elements;
	}

	/** Method used to send keys to element */
	public void sendText(WebElement element, String text) {
		try {
			sleep(1);
			if (element != null && text != null) {
				element.sendKeys(text);
			} else {
				BaseSetup.logger.info("Set Text failed");
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	/** Method used to send keys to element using xpath */
	public void sendText(String locator, String text, locatorType selector) {
		try {
			sleep(1);
			if (locator != null && text != null) {
				switch (selector.toString().toLowerCase()) {
				case CLASS_NAME:
					driver.findElement(By.className(locator)).sendKeys(text);
					break;
				case ID:
					driver.findElement(By.id(locator)).sendKeys(text);
					break;
				case NAME:
					driver.findElement(By.name(locator)).sendKeys(text);
					break;
				case XPATH:
					driver.findElement(By.xpath(locator)).sendKeys(text);
					break;
				case LINK_TEXT:
					driver.findElement(By.linkText(locator)).sendKeys(text);
					break;
				case CSS_SELECTOR:
					driver.findElement(By.cssSelector(locator)).sendKeys(text);
					break;
				case TAG_NAME:
					driver.findElement(By.tagName(locator)).sendKeys(text);
					break;
				default:
					driver.findElement(By.xpath(locator)).sendKeys(text);
					break;
				}
			} else {
				BaseSetup.logger.info("Set Text failed");
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	/** Method used to check if element is visible using xpath */
	public boolean elementVisible(String locator, locatorType selector) {
		boolean result = false;
		try {
			if (locator != null) {
				switch (selector.toString().toLowerCase()) {
				case CLASS_NAME:
					result = driver.findElement(By.className(locator)).isDisplayed();
					break;
				case ID:
					result = driver.findElement(By.id(locator)).isDisplayed();
					break;
				case NAME:
					result = driver.findElement(By.name(locator)).isDisplayed();
					break;
				case XPATH:
					result = driver.findElement(By.xpath(locator)).isDisplayed();
					break;
				case LINK_TEXT:
					result = driver.findElement(By.linkText(locator)).isDisplayed();
					break;
				case CSS_SELECTOR:
					result = driver.findElement(By.cssSelector(locator)).isDisplayed();
					break;
				case TAG_NAME:
					result = driver.findElement(By.tagName(locator)).isDisplayed();
					break;
				default:
					result = driver.findElement(By.xpath(locator)).isDisplayed();
					break;
				}
			} else {
				BaseSetup.logger.info("Element not visible");
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	/** Method used to check if element is visible using element */
	public boolean elementVisible(WebElement element) {
		boolean result = false;
		try {
			if (element != null) {
				result = element.isDisplayed();
			} else {
				BaseSetup.logger.info("Element not visible");
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
		return result;
	}

	/** This function is used to sleep for a given time in sec */
	public void sleep(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	/** This function is used to get the Text of Element */
	public String getText(String locator, locatorType selector) {
		String txt = null;
		try {
			if (locator != null) {
				switch (selector.toString().toLowerCase()) {
				case CLASS_NAME:
					txt = driver.findElement(By.className(locator)).getText();
					break;
				case ID:
					txt = driver.findElement(By.id(locator)).getText();
					break;
				case NAME:
					txt = driver.findElement(By.name(locator)).getText();
					break;
				case XPATH:
					txt = driver.findElement(By.xpath(locator)).getText();
					break;
				case LINK_TEXT:
					txt = driver.findElement(By.linkText(locator)).getText();
					break;
				case CSS_SELECTOR:
					txt = driver.findElement(By.cssSelector(locator)).getText();
					break;
				case TAG_NAME:
					txt = driver.findElement(By.tagName(locator)).getText();
					break;
				default:
					txt = driver.findElement(By.xpath(locator)).getText();
					break;
				}
			} else {
				BaseSetup.logger.info(TEXT_IS_NOT_CLEARED);
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
		return txt;
	}

	/** This function is used to get the Text of Element */
	public String getText(String xpath) {
		String txt = null;
		try {
			if (xpath != null) {
				txt = driver.findElement(By.xpath(xpath)).getText();
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
		return txt;
	}

	/** This function is used to capture pass screenshots */
	public String passScreenCapture(String tcName) throws IOException {
		String dest = null;
		try {
			if (tcName != null) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				dest = Constants.PASS_TEST_CASE_FOLDER + sdf.format(d) + tcName + Constants.PASS_TEST_CASE_EXTENSION;
				FileUtils.copyFile(scrFile, new File(dest));
				dest = dest.substring(1);
				Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
				dest = path + dest;
			} else {
				BaseSetup.logger.info("Screen Capture Failed");
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
		return dest;
	}

	/** This function is used to capture failed screenshots */
	public String failScreenCapture(String tcName) throws IOException {
		String dest = null;
		try {
			if (tcName != null) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmmss");
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				dest = Constants.FAILED_TEST_CASE_FOLDER + sdf.format(d) + tcName
						+ Constants.FAILED_TEST_CASE_EXTENSION;
				FileUtils.copyFile(scrFile, new File(dest));
				dest = dest.substring(1);
				Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
				dest = path + dest;
			} else {
				BaseSetup.logger.info("Screen Capture Failed");
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
		return dest;
	}

	/** Method use to hover the element */
	public void mouseHover(String value) {
		if (value != null) {
			Actions actions = new Actions(driver);
			WebElement elementLocator = driver.findElement(By.xpath(value));
			actions.moveToElement(elementLocator).build().perform();
		} else {
			BaseSetup.logger.info("Mouse hover not working");
		}
	}

	/** Method use to create directory */
	public void createDirectory() {
		try {
			File file = new File(directory + subDirectory);
			FileUtils.forceMkdir(file);
			FileUtils.cleanDirectory(file);
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	/**
	 * Method to archive reports at the end of the suite execution by calling it via
	 * aftersuite annotation
	 */
	public void copyReportToOld() {
		File oldDirectory = new File(directory);
		DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_hh_mm_ssaa");
		String dateName = dateFormat.format(new Date());
		File newDirectory = new File(oldReportsDirectory + dateName);
		try {
			FileUtils.copyDirectory(oldDirectory, newDirectory);
		} catch (IOException e) {
			BaseSetup.logger.error(e);
		}
	}

	/** Method to log message in report with screenshot */
	public void passTest(String message, String tcName) {
		try {
			BaseSetup.test.get().pass(message);
			BaseSetup.test.get().addScreenCaptureFromPath(passScreenCapture(tcName));
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	/** Method to log message in report with screenshot */
	public void failTest(String message, String tcName) {
		try {
			BaseSetup.test.get().fail(message);
			BaseSetup.test.get().addScreenCaptureFromPath(failScreenCapture(tcName));
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	/** Function to accept Alert */
	public void alertAccept() {
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	/** Function to dismiss Alert */
	public void alertDismiss() {
		try {
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}
	
	/** Method used to get Sub string */
	public String splitString(String string) {
		try {
			String[] arrOfStr = string.split(" "); 
			return arrOfStr[1].split("\n")[0].toString();
		} catch (Exception e) {
			BaseSetup.logger.error(e);
			return null;
		}
	}

}
