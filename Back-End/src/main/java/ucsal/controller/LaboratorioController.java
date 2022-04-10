package ucsal.controller;

import java.util.List;

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
import ucsal.model.Laboratorio;
import ucsal.service.LaboratorioService;

@RestController
@RequestMapping("/laboratorio")
@Api(tags = "laboratorios")
@RequiredArgsConstructor
public class LaboratorioController {

	private final LaboratorioService ls;
	
	 
	  //@PreAuthorize("hasRole('ROLE_GESTOR')")
	  @GetMapping("/listarLaboratorios")
	  public List<Laboratorio> listarLaboratorios() {
	    return ls.getAllLaboratorios();
	  }
	  @PreAuthorize("hasRole('ROLE_GESTOR')")
	  @PostMapping("/cadastrar")
	  @ApiOperation(value = "${LaboratorioController.criarLaboratorio}")
	  @ApiResponses(value = {//
	  @ApiResponse(code = 400, message = "Something went wrong")}) //
	  public String criarLaboratorio(@RequestBody Laboratorio laboratorio) {
	    return ls.saveLaboratorio(laboratorio);
	  }
	
}
