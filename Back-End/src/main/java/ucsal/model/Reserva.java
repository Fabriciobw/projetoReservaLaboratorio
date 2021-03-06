package ucsal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import ucsal.enums.StatusReserva;

@Entity
@Data // Create getters and setters
@NoArgsConstructor
public class Reserva {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;

	  @JsonFormat(pattern="dd/MM/yyyy HH:mm")
	  private Date dataReserva;
	  
	  @Size(min = 4, max = 255, message = "Minimum descricao length: 4 characters")
	  @Column(unique = true, nullable = false)
	  private String descricao;
	  
	  @JsonFormat(pattern="dd/MM/yyyy HH:mm")
	  private Date dataLimite;
	  
	  @ManyToOne
	  @JoinColumn(name="laboratorio_id")
	  private Laboratorio laboratorio;
	  
	  @ManyToOne
	  @JoinColumn(name="usuario_id")
	  private AppUser usuario;
	  
	  private StatusReserva status;
	  
	  public void desistir() {
		  status = StatusReserva.DESISTIDO;
	  }
	  
	  public void solicitarReserva(Date data,String descricao) {
		  this.dataReserva = data;
		  this.descricao = descricao;
	  }
	  
	  

}
