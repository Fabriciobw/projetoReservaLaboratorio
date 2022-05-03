package testes;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import core.DriveFactory;
import pageObjects.HomePO;
import pageObjects.LoginPO;
import pageObjects.ReservaPO;

@TestMethodOrder(OrderAnnotation.class)
public class ReservaTest {

	LoginPO loginPO = new LoginPO();
	HomePO homePO = new HomePO();
	ReservaPO reservaPO = new ReservaPO();
	
	final String USERNAME_SOLICITANTE = "client";
	final String PASSWORD_SOLICITANTE = "client";
	
	final String USERNAME_GESTOR = "admin";
	final String PASSWORD_GESTOR = "admin";
	
	final static String DESCRICAO = "Descricao teste " + System.currentTimeMillis();
	final String LABORATORIO = "Laboratorio";
	
	
	@Test
	@Order(1) 
	public void reservarLaboratorio() {
		loginPO.acessarPaginaLogin();
		loginPO.escreveUsername(USERNAME_SOLICITANTE);
		loginPO.escreveSenha(PASSWORD_SOLICITANTE);
		loginPO.clicaLogin();
		homePO.clicaCadastrarReserva();
		reservaPO.escreveDescricao(DESCRICAO);
		reservaPO.escreveData("02-05-2022", "0230PM");;
		reservaPO.escreveDataLimite("02-05-2022", "1500AM");
		reservaPO.selecionaLaboratorio(LABORATORIO);
		reservaPO.clicaEnviar();
		reservaPO.clicaOkAlert();
	}
	
	@Test
	@Order(2) 
	public void aprovarReserva() {
		loginPO.acessarPaginaLogin();
		loginPO.escreveUsername(USERNAME_GESTOR);
		loginPO.escreveSenha(PASSWORD_GESTOR);
		loginPO.clicaLogin();
		homePO.clicaReservas();
		reservaPO.editaReservaPeloNome(DESCRICAO);
		reservaPO.selecionaStatus("APROVADO");
		reservaPO.clicaEnviarGestor();
		reservaPO.clicaOkAlert();
		homePO.clicaReservas();
		Assert.assertTrue(reservaPO.verificaStatusLinhaTabelaReservaPorDescricao(DESCRICAO).contains("APROVADO"));
		
		
	}
	
	@AfterEach
	public void afterTest() {
		homePO.clicaSair();
		DriveFactory.killSeleniumDriver();;
	}
	
}
