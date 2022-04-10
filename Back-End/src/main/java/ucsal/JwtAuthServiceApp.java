package ucsal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import lombok.RequiredArgsConstructor;
import ucsal.enums.StatusLaboratorio;
import ucsal.model.AppUser;
import ucsal.model.AppUserRole;
import ucsal.model.Laboratorio;
import ucsal.service.LaboratorioService;
import ucsal.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@RequiredArgsConstructor
public class JwtAuthServiceApp implements CommandLineRunner {

  final UserService userService;

  public static void main(String[] args) {
    SpringApplication.run(JwtAuthServiceApp.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

//  @Bean
//  public CorsFilter corsFilter() {
//      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//      final CorsConfiguration config = new CorsConfiguration();
//      config.setAllowCredentials(true);
//      config.setAllowedOrigins(Collections.singletonList("*"));
//      config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
//      config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
//      source.registerCorsConfiguration("/**", config);
//      return new CorsFilter(source);
//  }
//  
  @Autowired
  LaboratorioService laboratorioService;
  
  @Override
  public void run(String... params) throws Exception {
	Laboratorio laboratorio = new Laboratorio();
	laboratorio.setNome("adsXAU");
	laboratorio.setStatus(StatusLaboratorio.LIBERADO);
	laboratorioService.saveLaboratorio(laboratorio);
    AppUser admin = new AppUser();
    admin.setUsername("admin");
    admin.setPassword("admin");
    admin.setEmail("admin@email.com");
    admin.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_ADMIN)));

    userService.signup(admin);

    AppUser client = new AppUser();
    client.setUsername("client");
    client.setPassword("client");
    client.setEmail("client@email.com");
    client.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_CLIENT)));

    userService.signup(client);
  }

}
