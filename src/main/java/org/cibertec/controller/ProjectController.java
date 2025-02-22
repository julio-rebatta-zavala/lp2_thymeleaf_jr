package org.cibertec.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cibertec.dto.Opcion;
import org.cibertec.dto.Reporte;
import org.cibertec.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

@Controller
public class ProjectController {

	@Autowired
	private ReporteService reporteService;
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Julio") String name,
			@RequestParam(name = "lastName", required = false, defaultValue = "Rebatta") String lastName, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("lastName", lastName);
		return "greeting";
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/report-view")
	public String reportView(Model model) {
		List<Opcion> formatoList = new ArrayList<>();
		formatoList.add(new Opcion("pdf", "Archivo PDF"));
		formatoList.add(new Opcion("xlsx", "Archivo Excel"));
		formatoList.add(new Opcion("csv", "Archivo CSV"));
		
		List<Opcion> reporteList = new ArrayList<>();
		reporteList.add(new Opcion("Empleados_Area.jasper", "Empleados por Área"));
		reporteList.add(new Opcion("Area_List.jasper", "Listado de Áreas"));
		
		model.addAttribute("reporteObject", new Reporte());
		model.addAttribute("formatoList", formatoList);
		model.addAttribute("reporteList", reporteList);
		
		return "report";
	}
	
	@PostMapping("/view-reporte")
	@ResponseBody
	public void empleadosPorAreaReportConFormato(HttpServletResponse response, @ModelAttribute("reporte") Reporte reporte) throws IOException, SQLException, JRException {
		reporteService.getReporteEmpleadosPorArea(response, reporte.getFormato());
	}
	
}
