package ucsal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ucsal.model.Reserva;

public interface ReservaRespository extends JpaRepository<Reserva, Integer> {

	@Query("SELECT r FROM Reserva r WHERE r.status = 1")
	List<Reserva> findAllWhoIsReservado();
	
	@Query("SELECT r FROM Reserva r WHERE r.usuario.id = ?1")
	List<Reserva> findAllReservasByUserId(Integer id);
	
}
