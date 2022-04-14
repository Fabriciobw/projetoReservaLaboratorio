package ucsal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Create getters and setters
@NoArgsConstructor
public class Laboratorio {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;
	 
	  @Size(min = 4, max = 255, message = "Minimum name length: 4 characters")
	  @Column(unique = true, nullable = false)
	  private String nome;
	  
	  @JsonIgnore
	  @OneToMany(mappedBy = "laboratorio")
	  private List<Reserva> reserva;
	  
	  @OneToMany(mappedBy = "laboratorio", cascade = CascadeType.MERGE)
	  private List<AppUser> prioridadeUsuarios;
	  
	
	  
	  
	  
}
