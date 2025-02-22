package org.cibertec.service;

import java.util.List;
import java.util.Optional;

import org.cibertec.entity.Empleado;
import org.cibertec.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository repository;
	
	public List<Empleado> getEmpleados() {
		return repository.findByEstadoOrderByApellido("A");
	}
	
	public Empleado getEmpleado(Integer codigo) throws Exception {
		Optional<Empleado> opt = repository.findById(codigo);
		
		if (opt.isEmpty()) {
			throw new Exception("El empleado no existe");
		}
		
		return opt.get();
	}
	
	public void addEmpleado(Empleado empleado) throws Exception {
		if (!StringUtils.hasText(empleado.getNombre())) {
			throw new Exception("Debe ingresar el nombre");
		}
		
		if (!StringUtils.hasText(empleado.getApellido())) {
			throw new Exception("Debe ingresar el apellido");
		}
		
		/*
		if (!StringUtils.hasText(empleado.getArea())) {
			throw new Exception("Debe ingresar el area");
		}
		*/
		
		/*
		Empleado emp = repository.getEmpleado(empleado.getCodigo());
		
		if (emp != null) {
			throw new Exception("El empleado ya existe");
		}
		*/
		
		repository.save(empleado);
	}
	
	public void updateEmpleado(Empleado empleado) throws Exception {
		if (!StringUtils.hasText(empleado.getNombre())) {
			throw new Exception("Debe ingresar el nombre");
		}
		
		if (!StringUtils.hasText(empleado.getApellido())) {
			throw new Exception("Debe ingresar el apellido");
		}
		
		/*
		if (!StringUtils.hasText(empleado.getArea())) {
			throw new Exception("Debe ingresar el area");
		}
		*/
		
		Empleado emp = getEmpleado(empleado.getCodigo());
		emp.setNombre(empleado.getNombre());
		emp.setApellido(empleado.getApellido());
		emp.setArea(empleado.getArea());
		
		repository.save(emp);
	}
	
	public void deleteEmpleado(Integer codigo) throws Exception {
		Empleado empleado = getEmpleado(codigo);
		empleado.setEstado("I");
		repository.save(empleado);
	}

}