package org.cibertec.service;

import java.util.List;
import java.util.Optional;

import org.cibertec.entity.Area;
import org.cibertec.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AreaService {
	
	@Autowired
	private AreaRepository areaRepository;

	public List<Area> getAreas() {
		return areaRepository.findByEstado("A");
	}
	
	public Area getArea(Integer id) throws Exception {
		Optional<Area> opt = areaRepository.findById(id);
		
		if (opt.isEmpty()) {
			throw new Exception("El área no existe");
		}
		
		return opt.get();
	}
	
	public void addArea(Area area) throws Exception {
		area.setEstado("A");
		areaRepository.save(area);
	}
	
	public void updateArea(Area area) throws Exception {
		Area a = getArea(area.getId());
		a.setDescripcion(area.getDescripcion());
		
		areaRepository.save(a);
	}
	
	public void deleteArea(Integer id) throws Exception {
		Area a = getArea(id);
		a.setEstado("I");
		
		areaRepository.save(a);
	}
	
	@Transactional
	public Integer getTotalAreasInactivas() {
		return areaRepository.getTotalAreasPorEstado("I");
	}
	
	@Transactional
	public List<Area> getAreasInactivasProcedure() {
		return areaRepository.findAreasPorEstado("I");
	}
	
	@Transactional
	public void batch1() {
		Area a1 = new Area("batch1-1", "A");
		Area a2 = new Area("batch1-2", "A");
		Area a3 = new Area("batch1-3", "A");
		
		areaRepository.save(a1);
		areaRepository.save(a2);
		areaRepository.save(a3);
		
		//Integer.parseInt("a");
	}
	
	@Transactional
	public void batch2() {
		Area a1 = new Area("batch2-1", "A");
		Area a2 = new Area("batch2-2", "A");
		Area a3 = new Area("batch2-3", "A");
		
		areaRepository.save(a1);
		Integer.parseInt("a");
		areaRepository.save(a2);
		areaRepository.save(a3);
	}
	
}
