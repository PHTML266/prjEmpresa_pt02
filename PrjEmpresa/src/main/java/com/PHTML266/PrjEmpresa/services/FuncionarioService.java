package com.PHTML266.PrjEmpresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PHTML266.PrjEmpresa.entities.Funcionario;
import com.PHTML266.PrjEmpresa.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	private final FuncionarioRepository funcionarioRepository;
	
	@Autowired
	public FuncionarioService (FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public Funcionario saveFuncionario(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	public List<Funcionario> getAllFuncionarios(){
		return funcionarioRepository.findAll();
	}
	
	public Funcionario getFuncionarioById(Long funcodigo) {
		return funcionarioRepository.findById(funcodigo).orElse(null);
	}

	public List<Funcionario> getFuncionariosByFunnomeAproximado(String funnome) {
        return funcionarioRepository.findByNomeContaining(funnome);
    }

	public boolean deleteFuncionario(Long id) {
		Optional<Funcionario> funcionarioExistente = funcionarioRepository.findById(id);
		if (funcionarioExistente.isPresent()) {
			funcionarioRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public Funcionario updateFuncionario(Long funcodigo, Funcionario novoFuncionario) {
		Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(funcodigo);
		if (funcionarioOptional.isPresent()) {
			Funcionario funcionarioExistente = funcionarioOptional.get();
			funcionarioExistente.setFunnome(novoFuncionario.getFunnome());
			funcionarioExistente.setFunnascimento(novoFuncionario.getFunnascimento());
			funcionarioExistente.setFunsalario(novoFuncionario.getFunsalario());

			// Atualize o departamento
			if (novoFuncionario.getDepartamento() != null) {
				funcionarioExistente.setDepartamento(novoFuncionario.getDepartamento());
			}		
			return funcionarioRepository.save(funcionarioExistente);
		} else {
			return null; // Ou lançar uma exceção indicando que o funcionário não foi encontrado
		}
	}
}
