package org.cibertec;

import java.util.List;
import java.util.Map;

import org.cibertec.entity.Area;
import org.cibertec.repository.AreaRepository;
import org.cibertec.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafBootApplication implements CommandLineRunner {

	@Autowired
	private AreaService areaService;

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Integer contador = areaService.getTotalAreasInactivas();
		System.out.println("Total de Areas Inactivas: " + contador);

		List<Area> areaList = areaService.getAreasInactivasProcedure();
		areaList.forEach(System.out::println);
	}

}
