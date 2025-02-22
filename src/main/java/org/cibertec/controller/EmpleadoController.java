package org.cibertec.controller;

import org.cibertec.entity.Empleado;
import org.cibertec.service.AreaService;
import org.cibertec.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class EmpleadoController {

	@Autowired
	private EmpleadoService service;
	
	@Autowired
	private AreaService areaService;
	
	@GetMapping("/list-empleado")
	private String viewHome(Model model) {
		model.addAttribute("empleadoList", service.getEmpleados());
		return "list-empleado";
	}
	
	@GetMapping("/add")
	private String addNewEmpleado(Model model) {
		model.addAttribute("empleado", new Empleado());
		model.addAttribute("areaList", areaService.getAreas());
		return "new-empleado";
	}
	
	@GetMapping("/edit/{id}")
	private String showUpdateEmpleado(@PathVariable(name = "id") Integer id, Model model) {
		try {
			Empleado empleado = service.getEmpleado(id);
			model.addAttribute("empleado", empleado);
			model.addAttribute("areaList", areaService.getAreas());
			return "edit-empleado";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/";
		}
	}
	
	@GetMapping("/view/{id}")
	private String showViewEmpleado(@PathVariable(name = "id") Integer id, Model model) {
		try {
			Empleado empleado = service.getEmpleado(id);
			model.addAttribute("empleado", empleado);
			return "view-empleado";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/";
		}
	}
	
	@PostMapping("/save")
	private String saveEmpleado(@Valid @ModelAttribute("empleado") Empleado empleado, BindingResult bindingResult, Model model) {
		try {
			if (empleado.getCodigo() == null) {
				if (bindingResult.hasErrors()) {
					model.addAttribute("areaList", areaService.getAreas());
					return "new-empleado";
				}
				empleado.setEstado("A");
				service.addEmpleado(empleado);
			} else {
				if (bindingResult.hasErrors()) {
					model.addAttribute("areaList", areaService.getAreas());
					return "edit-empleado";
				}
				service.updateEmpleado(empleado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/list-empleado";
	}
	
	@GetMapping("/remove/{id}")
	private String removeEmpleado(@PathVariable(name = "id") Integer id) {
		try {
			service.deleteEmpleado(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
}
