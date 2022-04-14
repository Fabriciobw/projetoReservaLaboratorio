package ucsal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucsal.enums.StatusReserva;
import ucsal.model.Laboratorio;
import ucsal.model.Reserva;
import ucsal.repository.ReservaRespository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRespository reservaRespository;

	public ReservaService() {
	}

	public Reserva getReserva(Integer id) {
		return reservaRespository.getById(id);
	}

	public List<Reserva> getAllReservas() {
		return reservaRespository.findAll();
	}

	public String salvarReserva(Reserva reserva) {
		reservaRespository.save(reserva);
		return "Done";
	}
	
	
	public List<Reserva> getAllReservasLiberadas() {
		return reservaRespository.findAllWhoIsReservado();
	}

	public List<Reserva> getaAllReservasByUserId(Integer id) {
		// TODO Auto-generated method stub
		return reservaRespository.findAllReservasByUserId(id);
	}
	

	
	
}
