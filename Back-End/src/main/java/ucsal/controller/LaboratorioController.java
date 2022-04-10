package ucsal.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import ucsal.model.AppUser;
import ucsal.model.Laboratorio;
import ucsal.service.LaboratorioService;
import ucsal.service.UserService;

@RestController
@RequestMapping("/laboratorio")
@Api(tags = "laboratorios")
@RequiredArgsConstructor
public class LaboratorioController {

	private final LaboratorioService ls;
	private final UserService us;
	 
	  //@PreAuthorize("hasRole('ROLE_GESTOR')")
	  @GetMapping("/listarLaboratorios")
	  public List<Laboratorio> listarLaboratorios() {
		  //solução para o json ignore não tem dto ainda
		  List<Laboratorio> list =  ls.getAllLaboratorios();
		  list.forEach(l -> l.setPrioridadeUsuarios(null)) ;
	    return list;
	  }
	  @PreAuthorize("hasRole('ROLE_GESTOR')")
	  @PostMapping("/cadastrar")
	  @ApiOperation(value = "${LaboratorioController.criarLaboratorio}")
	  @ApiResponses(value = {//
	  @ApiResponse(code = 400, message = "Something went wrong")}) //
	  public String criarLaboratorio(@RequestBody Laboratorio laboratorio) {
		List<AppUser> users =  us.getAllUsersByIds(laboratorio.getPrioridadeUsuarios().stream().map(AppUser::getId).collect(Collectors.toList()));
		
		users.forEach(e -> e.setLaboratorio(laboratorio));
		laboratorio.setPrioridadeUsuarios(users);
		 ls.saveLaboratorio(laboratorio);
		//cascade não estava funcionando irei implementar algo melhor futuramente
		us.saveAll(users);
		return "Created";
	  }
	
}
