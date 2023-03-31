package com.cep.cep.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cep.cep.model.Endereco;
import com.cep.cep.service.CepService;

@Controller
@RequestMapping("/")
public class CepController {
	
	@Autowired
	CepService cepService;
	
	public Endereco endereco;

	@GetMapping("home")
	public String Home(Model model) {
		
		model.addAttribute("ola", "Busque informações aqui");
		model.addAttribute("endereco", endereco);
		model.addAttribute("info", new String());
		return "home";
	}
	
	@PostMapping("buscar")
	public ModelAndView buscarCep(@RequestParam(name = "cep") String cep) {
		ModelAndView model = new ModelAndView(new RedirectView("/home", false));
		
		try {
			
			endereco =  cepService.findEnderecoByCEP(cep);
			model.addObject("endecero", endereco);
			
		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
		}
		
		return model;
		
	}

}
