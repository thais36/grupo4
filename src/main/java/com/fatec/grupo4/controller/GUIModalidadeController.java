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

import com.fatec.grupo4.model.Modalidade;
import com.fatec.grupo4.services.MantemModalidade;

@Controller
@RequestMapping(path = "/grupox")
public class GUIModalidadeController {
	
	Logger logger = LogManager.getLogger(GUIModalidadeController.class);
	
	@Autowired
	MantemModalidade servico;

	@GetMapping("/modalidade")
	public ModelAndView retornaFormDeConsultaTodosModalidades() {
		ModelAndView mv = new ModelAndView("consultarModalidade");
		mv.addObject("modalidade", servico.consultaTodos());
		return mv;
	}

	@GetMapping("/modalidade")
	public ModelAndView retornaFormDeCadastroDe(Modalidade modalidade) {
		ModelAndView mv = new ModelAndView("cadastrarModalidade");
		List<String> lista = Arrays.asList("Individual", "Coletiva");
		mv.addObject("lista", lista);
		mv.addObject("modalidade", modalidade);
		return mv;
	}

	@GetMapping("/modalidades/{modalidade}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarModalidade(@PathVariable("modalidade") String modalidade) {
		ModelAndView mv = new ModelAndView("atualizarModalidade");
		List<String> lista = Arrays.asList("Individual", "Coletiva");
		mv.addObject("lista", lista);
//		Optional<Modalidade> modalidade = servico.findByModalidade(modalidade);
//		if (modalidade.isPresent()) {
//			mv.addObject("modalidade", modalidade.get()); // retorna um objeto do tipo cliente
//		} else {
//			return new ModelAndView("paginaMenu");
//		}
		return mv; // addObject adiciona objetos para view
	}

	@GetMapping("/modalidades/id/{id}")
	public ModelAndView excluirNoFormDeConsultaModalidade(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("consultarModalidade");
		modelAndView.addObject("modalidade", servico.consultaTodos());
		return modelAndView;
	}

	@PostMapping("/modalidade")
	public ModelAndView save(@Valid Modalidade modalidade, BindingResult result) {
		ModelAndView mv = new ModelAndView("consultarModalidade");
		if (result.hasErrors()) {
			List<String> lista = Arrays.asList("Individual", "Coletiva");
			mv.addObject("lista", lista);
			mv.setViewName("cadastrarModalidade");
		} else {
			if (servico.save(modalidade).isPresent()) {
				logger.info(">>>>>> controller chamou cadastrar e consultar todos");
				mv.addObject("modalidade", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				mv.setViewName("cadastrarModalidade");
				mv.addObject("message", "Dados invalidos");
			}
		}
		return mv;
	}

	@PostMapping("/modalidade/id/{id}")
	public ModelAndView atualizaModalidade(@PathVariable("id") Long id, @Valid Modalidade modalidade, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarModalidade");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			modalidade.setId(id);
			return new ModelAndView("atualizarModalidade");
		} else {
			servico.atualiza(modalidade);
			modelAndView.addObject("Modalidade", servico.consultaTodos());
		}
		return modelAndView;
	}
}