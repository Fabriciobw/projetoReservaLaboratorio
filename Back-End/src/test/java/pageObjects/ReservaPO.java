package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.DSL;
import core.DriveFactory;

public class ReservaPO {

	DSL dsl;
	
	public ReservaPO(){
	    PageFactory.initElements(DriveFactory.getSeleniumDriver(), this);
	    dsl = new DSL();
	}
	
	@FindBy(id = "descricao")
	private WebElement inputDescricaoReserva;
	
	@FindBy(id = "laboratorio")
	private WebElement selectLaboratorio;
	
	@FindBy(id = "data")
	private WebElement inputData;
	
	@FindBy(id = "dataLimite")
	private WebElement inputDataLimite;
	
	@FindBy(id = "enviar")
	private WebElement btnEnviar;
	
	@FindBy(id = "status")
	private WebElement btnStatus;
	
	@FindBy(id = "enviar")
	private WebElement btnEnviarGestor;
	
	
	public void escreveDescricao(String descricao) {
		dsl.clearAndSendText(inputDescricaoReserva, descricao);
	}
	
	public void escreveData(String data, String hora) {
		inputData.sendKeys(data);
		inputData.sendKeys(Keys.TAB);
		inputData.sendKeys(hora);
	}
	
	public void escreveDataLimite(String data, String hora) {
		inputDataLimite.sendKeys(data);
		inputDataLimite.sendKeys(Keys.TAB);
		inputDataLimite.sendKeys(hora);
	}
	
	public void selecionaLaboratorio(String laboratorio) {
		dsl.selectByVisibleText(selectLaboratorio, laboratorio);
	}

	public void clicaEnviar() {
		dsl.click(btnEnviar);
	}

	public void clicaOkAlert() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DriveFactory.getSeleniumDriver().switchTo().alert().accept();
		
	}

	public void editaReservaPeloNome(String descricao) {
		dsl.interateThroughTableAndSelect(descricao, "//table[contains(@class,'table')]");
		
	}
	public String verificaStatusLinhaTabelaReservaPorDescricao(String descricao) {
		return dsl.interateThroughTable(descricao, "//table[contains(@class,'table')]");
		
	}
	
	public void selecionaStatus(String status) {
		dsl.selectByVisibleText(btnStatus, status);
	}
	
	public void clicaEnviarGestor() {
		dsl.click(btnEnviarGestor);
	}
}
