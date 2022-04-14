package ucsal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucsal.model.Laboratorio;
import ucsal.repository.LaboratorioRepository;

@Service
public class LaboratorioService {

	@Autowired
	private LaboratorioRepository laboratorioRepository;

	public LaboratorioService() {
	}

	public Laboratorio getLaboratorio(Integer id) {
		return laboratorioRepository.getById(id);
	}

	public List<Laboratorio> getAllLaboratorios() {
		return laboratorioRepository.findAll();
	}
	
	public void saveLaboratorio(Laboratorio laboratorio) {
		laboratorioRepository.save(laboratorio);
	
	}
	
	
	
}
