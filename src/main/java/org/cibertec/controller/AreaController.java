package org.cibertec.controller;

import org.cibertec.entity.Area;
import org.cibertec.service.AreaService;
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
public class AreaController {
	
	@Autowired
	private AreaService areaService;

	@GetMapping("/list-area")
	public String viewListArea(Model model) {
		model.addAttribute("areaList", areaService.getAreas());
		
		return "list-area";
	}
	
	@GetMapping("/add-area")
	public String newArea(Model model) {
		model.addAttribute("area", new Area());
		
		return "new-area";
	}
	
	@GetMapping("/edit-area/{id}")
	public String editArea(@PathVariable(name = "id") Integer id, Model model) {
		try {
			model.addAttribute("area", areaService.getArea(id));
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/list-area";
		}
		return "edit-area";
	}
	
	@GetMapping("/view-area/{id}")
	public String viewArea(@PathVariable(name = "id") Integer id, Model model) {
		try {
			model.addAttribute("area", areaService.getArea(id));
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/list-area";
		}
		return "view-area";
	}
	
	@PostMapping("/save-new-area")
	public String saveNewArea(@Valid @ModelAttribute("area") Area area, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return "new-area";
			}
			
			areaService.addArea(area);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/list-area";
	}
	
	@PostMapping("/save-edit-area")
	public String saveEditArea(@Valid @ModelAttribute("area") Area area, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return "edit-area";
			}
			
			areaService.updateArea(area);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/list-area";
	}
	
	@GetMapping("/remove-area/{id}")
	public String removeArea(@PathVariable(name = "id") Integer id) {
		try {
			areaService.deleteArea(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/list-area";
	}
	
	
}
