package cz.businesslogic.eshop.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.businesslogic.eshop.service.ItemService;

@Controller
public class IndexController {

	@Autowired
	private ItemService itemService;
	
	public String getCurrentDate() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd. MM YYYY"));
	}
	
	public String getTextWithHtml() {
		return "some text with <strong>strong</strong>";
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("items", itemService.findAll());
		return "index";
	}
}
