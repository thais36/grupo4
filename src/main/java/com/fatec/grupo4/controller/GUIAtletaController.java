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
import com.fatec.grupo4.model.Atleta;
import com.fatec.grupo4.services.MantemAluno;
import com.fatec.grupo4.services.MantemAtleta;

@SuppressWarnings("unused")
@Controller
@RequestMapping(path = "/grupo4")
public class GUIAtletaController {
	Logger logger = LogManager.getLogger(GUIAtletaController.class);
	@Autowired
	MantemAtleta servico;

	@GetMapping("/atletas")
	public ModelAndView retornaFormDeConsultaTodosAtleta() {
		ModelAndView mv = new ModelAndView("consultarAtleta");
		mv.addObject("atletas", servico.consultaTodos());
		return mv;
	}

	@GetMapping("/atleta")
	public ModelAndView retornaFormDeCadastroDe(Atleta atleta) {
		ModelAndView mv = new ModelAndView("cadastrarAtleta");
		List<String> lista = Arrays.asList("Gestão", "Filiado", "Outros");
		mv.addObject("lista", lista);
		mv.addObject("Atletas", atleta);
		return mv;
	}

	@GetMapping("/atletas/{cpf}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarAtleta(@PathVariable("cpf") String cpf) {
		ModelAndView mv = new ModelAndView("atualizarAtleta");
		List<String> lista = Arrays.asList("Gestão", "Filiado", "Outros");
		mv.addObject("lista", lista);
		Optional<Atleta> atleta = servico.consultaPorCpf(cpf);
		if (atleta.isPresent()) {
			mv.addObject("atleta", atleta.get()); // retorna um objeto do tipo cliente
		} else {
			return new ModelAndView("paginaMenu");
		}
		return mv; // addObject adiciona objetos para view
	}

	@GetMapping("/atletas/id/{id}")
	public ModelAndView excluirNoFormDeConsultaAtleta(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("consultarAtleta");
		modelAndView.addObject("atletas", servico.consultaTodos());
		return modelAndView;
	}

	@PostMapping("/atletas")
	public ModelAndView save(@Valid Atleta atleta, BindingResult result) {
		ModelAndView mv = new ModelAndView("consultarAtleta");
		if (result.hasErrors()) {
			List<String> lista = Arrays.asList("Gestão", "Filiado", "Outros");
			mv.addObject("lista", lista);
			mv.setViewName("cadastrarAtleta");
		} else {
			if (servico.save(atleta).isPresent()) {
				logger.info(">>>>>> controller chamou cadastrar e consultar todos");
				mv.addObject("atletas", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				mv.setViewName("cadastrarAtleta");
				mv.addObject("message", "Dados invalidos");
			}
		}
		return mv;
	}

	@PostMapping("/atletas/id/{id}")
	public ModelAndView atualizaAtleta(@PathVariable("id") Long id, @Valid Atleta atleta, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarAtleta");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			atleta.setId(id);
			return new ModelAndView("redirect:/grupo4/atletas");
		} else {
			servico.atualiza(atleta);
			modelAndView.addObject("atletas", servico.consultaTodos());
			if (servico.save(atleta).isPresent()) {
				logger.info(">>>>>> controller chamou atualizar e consultar todos");
				modelAndView.addObject("atletas", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				modelAndView.setViewName("redirect:/grupo4/atletas");
				modelAndView.addObject("message", "Dados invalidos");
			}
		}
		
		return modelAndView;
	}
}
