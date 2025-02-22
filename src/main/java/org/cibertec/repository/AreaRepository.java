package org.cibertec.repository;

import java.util.List;
import java.util.Map;

import org.cibertec.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer>{
	
	List<Area> findByEstado(String estado);

	
	
	
	
	@Procedure("getTotalAreasPorEstado")
	int getTotalAreasPorEstado(@Param("v_estado") String estado);
	
	@Procedure
	List<Area> findAreasPorEstado(@Param("v_estado") String estado);
	
}
