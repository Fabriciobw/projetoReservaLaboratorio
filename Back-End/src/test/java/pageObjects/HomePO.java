package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.DSL;
import core.DriveFactory;

public class HomePO {

	DSL dsl;

	public HomePO(){
	    PageFactory.initElements(DriveFactory.getSeleniumDriver(), this);
	    dsl = new DSL();
	}
	
	@FindBy(linkText = "Cadastrar Reserva")
	private WebElement btnCadastrarReserva;
	
	@FindBy(linkText = "Minhas Reservas")
	private WebElement btnMinhasReservas;
	
	@FindBy(linkText = "Sair")
	private WebElement btnSair;
	
	@FindBy(linkText = "Reservas")
	private WebElement btnReservas;

	
	public void clicaCadastrarReserva() {
		dsl.click(btnCadastrarReserva);
	}
	public void clicaMinhasReservas() {
		dsl.click(btnMinhasReservas);
	}
	public void clicaSair() {
		dsl.click(btnSair);
	}
	
	public void clicaReservas() {
		dsl.click(btnReservas);
	}
	
	
}
