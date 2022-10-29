package com.fatec.grupo4.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.grupo4.model.Aluno;
import com.fatec.grupo4.model.Campeonato;
import com.fatec.grupo4.model.Produto;
import com.fatec.grupo4.services.MantemCampeonato;

@SuppressWarnings("unused")
@Controller

@RequestMapping(path = "grupox")

public class GUICampeonatoController {
	
	Logger logger = LogManager.getLogger(GUICampeonatoController.class);
	
	@Autowired
	MantemCampeonato servico;

	@GetMapping("/campeonatos")
	public ModelAndView retornaFormDeConsultaTodosCampeonatos() {
		ModelAndView mv = new ModelAndView("consultarCampeonato");
		mv.addObject("campeonatos", servico.consultaTodos());
		return mv;
	}

	@GetMapping("/campeonato")
	public ModelAndView retornaFormDeCadastroDe(Campeonato campeonato) {
		ModelAndView mv = new ModelAndView("cadastrarCampeonato");
		//List<String> lista = Arrays.asList("Individual", "Coletiva");
		//mv.addObject("lista", lista);
		//mv.addObject("campeonato", campeonato);
		return mv;
	}

	@GetMapping("/campeonatos/id/{id}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarCampeonato(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("atualizarCampeonato");
		//List<String> lista = Arrays.asList("Individual", "Coletiva");
		//mv.addObject("lista", lista);
		Optional<Campeonato> campeonato = servico.findById(id);
		if (campeonato.isPresent()) {
			mv.addObject("campeonato", campeonato.get()); // retorna um objeto do tipo Campeonato
		} else {
			return new ModelAndView("paginaMenu");
		}
		return mv; // addObject adiciona objetos para view
	}

	@GetMapping("/campeonatos/delete/{id}")
	public ModelAndView excluirNoFormDeConsultaCampeonato(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("redirect:/grupox/campeonatos");
		return modelAndView;
	}
	
	@PostMapping("/campeonatos")
	public ModelAndView save(@Valid Campeonato campeonato, BindingResult result) {
		ModelAndView mv = new ModelAndView("consultarCampeonato");
		//if (result.hasErrors()) {
		//	List<String> lista = Arrays.asList("Individual", "Coletiva");
		//	mv.addObject("lista", lista);
		//	mv.setViewName("cadastrarCampeonato");
		//} else {
//			if (servico.save(campeonato).isPresent()) {
				servico.save(campeonato);
				logger.info(">>>>>> controller chamou cadastrar e consultar todos");
				mv.addObject("campeonatos", servico.consultaTodos());
//			} else {
//				logger.info(">>>>>> controller cadastrar com dados invalidos");
//				mv.setViewName("cadastrarCampeonato");
//				mv.addObject("message", "Dados invalidos");
//			}
		//}
		return mv;
	}

	@PostMapping("/campeonato/id/{id}")
	public ModelAndView atualizarCampeonato(@PathVariable("id") Long id, @Valid Campeonato campeonato, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("redirect:/grupox/campeonatos");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			campeonato.setId(id);
			return new ModelAndView("atualizarCampeonato");
		} else {
			servico.atualiza(campeonato);
			modelAndView.addObject("Campeonato", servico.consultaTodos());
		}
		return modelAndView;
	}
}