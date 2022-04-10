package ucsal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void saveReserva(Reserva reserva) {
		reservaRespository.save(reserva);
	}
}
