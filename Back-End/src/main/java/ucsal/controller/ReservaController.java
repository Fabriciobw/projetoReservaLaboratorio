package ucsal.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import ucsal.enums.StatusReserva;
import ucsal.model.Laboratorio;
import ucsal.model.Reserva;
import ucsal.service.ReservaService;

@RestController
@RequestMapping("/reserva")
@Api(tags = "reservas")
@RequiredArgsConstructor
public class ReservaController {

	private final ReservaService reservaService;
	

	  @PreAuthorize("hasRole('ROLE_GESTOR')")
	  @GetMapping("/listarReservas")
	  public List<Reserva> listarReservas() {
		  List<Reserva> list =  reservaService.getAllReservas();
		  list.forEach(r -> r.getLaboratorio().setPrioridadeUsuarios(null));
		  
	    return list;
	  }
	  
	  @PreAuthorize("hasRole('ROLE_SOLICITANTE')")
	  @PostMapping("/solicitarReserva")
	  @ApiOperation(value = "${ReservaController.criarReserva}")
	  @ApiResponses(value = {//
	  @ApiResponse(code = 400, message = "Something went wrong")}) //
	  public String reservarLaboratorio(@RequestBody Reserva reserva) {
		reserva.setStatus(StatusReserva.PENDENTE);
	    return reservaService.salvarReserva(reserva);
	  }
	  
	 
	  @PreAuthorize("hasRole('ROLE_GESTOR')")
	  @PostMapping("/mudarStatus/{id}/{status}")
	  @ApiOperation(value = "${ReservaController.criarReserva}")
	  @ApiResponses(value = {//
	  @ApiResponse(code = 400, message = "Something went wrong")}) //
	  public String mudarStatus(@PathVariable String id,  @PathVariable StatusReserva status) {
		//reserva.setStatus(StatusReserva.NEGADO);
		Reserva reserva =  reservaService.getReserva(Integer.valueOf(id));
		reserva.setStatus(status);
		reservaService.salvarReserva(reserva);
	    return "Status alterado com sucesso!";
	  }
}
