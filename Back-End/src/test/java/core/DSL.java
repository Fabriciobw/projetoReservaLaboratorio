package core;

import static core.DriveFactory.getSeleniumDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DSL {

	WebDriver driver;
	WebDriverWait wait;

	public DSL() {
		driver = getSeleniumDriver();
		wait = new WebDriverWait(driver, 10);
	}

	public void rollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,200)");
	}

	public void click(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element)).click();
	}

	public String getText(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element)).getText();
	}

	public void clearAndSendText(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(text);
	}

	public void acessar(String pagina) {
		driver.get(pagina);
	}

	public void selectByVisibleText(WebElement element, String texto) {
		Select select = new Select(element);
		select.selectByVisibleText(texto);
	}

	public boolean containsCell(List<WebElement> celulas, String celula) {

		for (WebElement element : celulas) {
			if (element.getText().equals(celula)) {
				return true;
			}
		}
		return false;

	}

	public void interateThroughTableAndSelect(String descricao, String xpath) {
		WebElement table = driver.findElement(By.xpath(xpath));
		List<WebElement> rowsList = table.findElements(By.xpath("//tr"));
		for (WebElement row : rowsList) {
			if (row.getText().contains(descricao)) {
				WebElement button = driver.findElement(By.xpath("//tbody[@id='tbody']/tr["+rowsList.indexOf(row)+"]//button"));
				button.click();
			}
		}
	}
	
	public String interateThroughTable(String descricao, String xpath) {
		WebElement table = driver.findElement(By.xpath(xpath));
		List<WebElement> rowsList = table.findElements(By.xpath("//tr"));
		for (WebElement row : rowsList) {
			if (row.getText().contains(descricao)) {
				return row.getText();
			}
		}
		return "";
	}
}
