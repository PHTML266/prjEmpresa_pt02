package com.PHTML266.PrjEmpresa.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PHTML266.PrjEmpresa.entities.Departamento;
import com.PHTML266.PrjEmpresa.services.DepartamentoService;

@RestController
@RequestMapping("/departments")
public class DepartamentoControler {

	private final DepartamentoService departamentoService;
	
	@Autowired
	public DepartamentoControler (DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}
	
	@GetMapping("/{id}")
	public Departamento getDepartamento(@PathVariable Long depcodigo) {
		return departamentoService.getDepartamentoById(depcodigo);
	}
	
	@GetMapping
	public List<Departamento> getAllDepartamentos(){
		return departamentoService.getAllDepartamentos();
	}
	
	@DeleteMapping("/{id}")
	public void deleteDepartamento(@PathVariable Long depcodigo) {
		departamentoService.deleteDepartamento(depcodigo);
	}
	
}
