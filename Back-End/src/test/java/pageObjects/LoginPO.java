package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.DSL;
import core.DriveFactory;

public class LoginPO {

	DSL dsl;

	public LoginPO(){
	    PageFactory.initElements(DriveFactory.getSeleniumDriver(), this);
	    dsl = new DSL();
	}
	
	@FindBy(id = "username")
	private WebElement inputUsuario;
	
	@FindBy(id = "password")
	private WebElement inputSenha;
	
	@FindBy(css = ".login100-form-btn")
	private WebElement btnLogin;

	
	public void escreveUsername(String nome) {
		dsl.clearAndSendText(inputUsuario, nome);
	}
	public void escreveSenha(String senha) {
		dsl.clearAndSendText(inputSenha, senha);
	}
	public void clicaLogin() {
		dsl.click(btnLogin);
	}
	public void acessarPaginaLogin() {
		dsl.acessar("http://localhost:8080/myApp/login.html");
	}
	
}
