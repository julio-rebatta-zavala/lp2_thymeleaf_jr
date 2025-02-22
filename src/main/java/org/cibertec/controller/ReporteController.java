package org.cibertec.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import org.cibertec.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;

@RestController
public class ReporteController {
	
	@Autowired
	private ReporteService reporteService;

	@GetMapping("/reporte-empleado-area")
	public void empleadosPorAreaReport(HttpServletResponse response) throws IOException, JRException, SQLException {
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=reporteEmpleado.pdf");
		
		final OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(reporteService.getReporteEmpleadosPorArea(), outputStream);
	}
	
	@GetMapping("/reporte-empleado-area-2")
	public void empleadosPorAreaReportConFormato(HttpServletResponse response, @RequestParam String formato) throws IOException, SQLException, JRException {
		reporteService.getReporteEmpleadosPorArea(response, formato);
	}
	
}
