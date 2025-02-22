package org.cibertec.repository;

import org.cibertec.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

	List<Empleado> findByEstado(String estado);
	
	List<Empleado> findByEstadoOrderByApellido(String estado);
	
	@Query(value = "select codigo, nombre, apellido from empleado where estado = 'A'", nativeQuery = true)
	List<Empleado> findByCustom();
	
}
