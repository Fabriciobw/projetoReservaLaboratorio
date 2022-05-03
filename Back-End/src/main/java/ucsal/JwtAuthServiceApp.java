package ucsal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import ucsal.enums.StatusReserva;
import ucsal.model.AppUser;
import ucsal.model.AppUserRole;
import ucsal.model.Laboratorio;
import ucsal.model.Reserva;
import ucsal.service.LaboratorioService;
import ucsal.service.ReservaService;
import ucsal.service.UserService;

@SpringBootApplication
public class JwtAuthServiceApp extends SpringBootServletInitializer implements CommandLineRunner
{
	@Autowired
   UserService userService;
	@Autowired
   ReservaService reservaService;
  
  public static void main(String[] args) {
    SpringApplication.run(JwtAuthServiceApp.class, args);
  }
  

  public JwtAuthServiceApp() {
	super();
	}

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Autowired
  LaboratorioService laboratorioService;
  
  @Override
  public void run(String... params) throws Exception {

    AppUser admin = new AppUser();
    admin.setUsername("admin");
    admin.setPassword("admin");
    admin.setEmail("admin@email.com");
    admin.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_GESTOR)));
   
	
    userService.signup(admin);


    AppUser client = new AppUser();
    client.setUsername("client");
    client.setPassword("client");
    client.setEmail("client@email.com");
    client.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_SOLICITANTE)));

    userService.signup(client);
    

	  Laboratorio laboratorio = new Laboratorio();
	  laboratorio.setNome("Laboratorio");
	  
	  
	  
	   List<AppUser> users = userService.getAllUsersByIds(Arrays.asList(1,2));
	   users.forEach(u -> u.setLaboratorio(laboratorio));
	   //userService.saveAll(users);
	   
	   
		laboratorio.setPrioridadeUsuarios(users);
		laboratorioService.saveLaboratorio(laboratorio);
    
    
	Reserva reserva = new Reserva();
	reserva.setDataLimite(new Date(2022, 04, 12));
	reserva.setDataReserva(new Date());
	reserva.setLaboratorio(laboratorio);
	reserva.setStatus(StatusReserva.APROVADO);
	reserva.setUsuario(admin);
	reserva.setDescricao("Reserva para o laboratorio");
	reservaService.salvarReserva(reserva);
  }




}
