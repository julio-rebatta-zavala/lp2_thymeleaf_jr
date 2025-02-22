package org.cibertec.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.pdf.JRPdfExporter;
import net.sf.jasperreports.pdf.SimplePdfExporterConfiguration;
import net.sf.jasperreports.pdf.SimplePdfReportConfiguration;

@Service
public class ReporteService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JasperPrint getReporteEmpleadosPorArea() throws SQLException, JRException {
		Connection cnx = jdbcTemplate.getDataSource().getConnection();
		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/Empleados_Area.jasper");
		Map<String, Object> params = new HashMap<String, Object>();
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, cnx);
		
		return jasperPrint;
	}
	
	public void getReporteEmpleadosPorArea(HttpServletResponse response, String formato) throws IOException, SQLException, JRException {
		String contentType = "application/pdf";
		
		if ("csv".equals(formato)) {
			contentType = "text/csv";
		} else if ("xlsx".equals(formato)) {
			contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		}
		
		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment; filename=reporteEmpleado." + formato);
		
		final OutputStream outputStream = response.getOutputStream();
		
		JasperPrint jasperPrint = getReporteEmpleadosPorArea();
		
		if ("csv".equals(formato)) {
			JRCsvExporter exporter = new JRCsvExporter();
			
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
			
			exporter.exportReport();
		} else if ("xlsx".equals(formato)) {
			JRXlsxExporter exporter = new JRXlsxExporter();
			
			SimpleXlsxReportConfiguration reportConfiguration = new SimpleXlsxReportConfiguration();
			reportConfiguration.setSheetNames(new String[] {"Empleados"});
			
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			exporter.setConfiguration(reportConfiguration);
			
			exporter.exportReport();
		} else {
			JRPdfExporter exporter = new JRPdfExporter();
			
			SimplePdfExporterConfiguration exportConfiguration = new SimplePdfExporterConfiguration();
			exportConfiguration.setMetadataAuthor("Julio Rebatta");
			
			SimplePdfReportConfiguration reportConfiguration = new SimplePdfReportConfiguration();
			reportConfiguration.setSizePageToContent(true);
			reportConfiguration.setForceLineBreakPolicy(false);
			
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			exporter.setConfiguration(exportConfiguration);
			exporter.setConfiguration(reportConfiguration);
			
			exporter.exportReport();
		}
	}
	
}
