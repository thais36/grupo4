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

import com.fatec.grupo4.model.Evento;
import com.fatec.grupo4.services.MantemEvento;

@Controller
@RequestMapping(path = "/grupo4")
public class GUIEventoController {
	Logger logger = LogManager.getLogger(GUIEventoController.class);
	@Autowired
	MantemEvento servico;

	@GetMapping("/eventos")
	public ModelAndView retornaFormDeConsultaTodosEvento() {
		ModelAndView mv = new ModelAndView("consultarEvento");
		mv.addObject("eventos", servico.consultaTodos());
		return mv;
	}

	@GetMapping("/evento")
	public ModelAndView retornaFormDeCadastroDe(Evento evento) {
		ModelAndView mv = new ModelAndView("cadastrarEvento");
		List<String> lista = Arrays.asList("Show", "Palestra", "Festas", "Outros");
		mv.addObject("lista", lista);
		mv.addObject("Evento", evento);
		return mv;
	}

	@GetMapping("/eventos/{nome}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarEvento(@PathVariable("nome") String nome) {
		ModelAndView mv = new ModelAndView("atualizarEvento");
		List<String> lista = Arrays.asList("Show", "Palestra", "Festas", "Outros");
		mv.addObject("lista", lista);
		Optional<Evento> evento = servico.consultaPorNome(nome);
		if (evento.isPresent()) {
			mv.addObject("evento", evento.get()); // retorna um objeto do tipo cliente
		} else {
			return new ModelAndView("paginaMenu");
		}
		return mv; // addObject adiciona objetos para view
	}

	@GetMapping("/eventos/id/{id}")
	public ModelAndView excluirNoFormDeConsultaEvento(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("consultarEvento");
		modelAndView.addObject("eventos", servico.consultaTodos());
		return modelAndView;
	}

	@PostMapping("/eventos")
	public ModelAndView save(@Valid Evento evento, BindingResult result) {
		ModelAndView mv = new ModelAndView("consultarEvento");
		if (result.hasErrors()) {
			List<String> lista = Arrays.asList("Show", "Palestra", "Festas", "Outros");
			mv.addObject("lista", lista);
			mv.setViewName("cadastrarEvento");
		} else {
			if (servico.save(evento).isPresent()) {
				logger.info(">>>>>> controller chamou cadastrar e consultar todos");
				mv.addObject("eventos", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				mv.setViewName("cadastrarEvento");
				mv.addObject("message", "Dados invalidos");
			}
		}
		return mv;
	}

	@PostMapping("/eventos/id/{id}")
	public ModelAndView atualizaEvento(@PathVariable("id") Long id, @Valid Evento evento, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView(("consultarEvento"));
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			evento.setId(id);
			return new ModelAndView("redirect:/grupo4/eventos");
		} else {
			servico.atualiza(evento);
			modelAndView.addObject("eventos", servico.consultaTodos());
			if (servico.save(evento).isPresent()) {
				logger.info(">>>>>> controller chamou atualizar e consultar todos");
				modelAndView.addObject("eventos", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				modelAndView.setViewName("redirect:/grupo4/eventos");
				modelAndView.addObject("message", "Dados invalidos");
			}
		}
		
		return modelAndView;
	}
}