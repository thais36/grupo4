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
import com.fatec.grupo4.services.MantemAluno;

@Controller
@RequestMapping(path = "/grupox")
public class GUIAlunoController {
	Logger logger = LogManager.getLogger(GUIAlunoController.class);
	@Autowired
	MantemAluno servico;

	@GetMapping("/alunos")
	public ModelAndView retornaFormDeConsultaTodosAluno() {
		ModelAndView mv = new ModelAndView("consultarAluno");
		mv.addObject("alunos", servico.consultaTodos());
		return mv;
	}

	@GetMapping("/aluno")
	public ModelAndView retornaFormDeCadastroDe(Aluno aluno) {
		ModelAndView mv = new ModelAndView("cadastrarAluno");
		List<String> lista = Arrays.asList("Gestão", "Filiado", "Outros");
		mv.addObject("lista", lista);
		mv.addObject("Aluno", aluno);
		return mv;
	}

	@GetMapping("/alunos/{cpf}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarAluno(@PathVariable("cpf") String cpf) {
		ModelAndView mv = new ModelAndView("atualizarAluno");
		List<String> lista = Arrays.asList("Gestão", "Filiado", "Outros");
		mv.addObject("lista", lista);
		Optional<Aluno> aluno = servico.consultaPorCpf(cpf);
		if (aluno.isPresent()) {
			mv.addObject("aluno", aluno.get()); // retorna um objeto do tipo cliente
		} else {
			return new ModelAndView("paginaMenu");
		}
		return mv; // addObject adiciona objetos para view
	}

	@GetMapping("/alunos/id/{id}")
	public ModelAndView excluirNoFormDeConsultaAluno(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("consultarAluno");
		modelAndView.addObject("alunos", servico.consultaTodos());
		return modelAndView;
	}

	@PostMapping("/alunos")
	public ModelAndView save(@Valid Aluno aluno, BindingResult result) {
		ModelAndView mv = new ModelAndView("consultarAluno");
		if (result.hasErrors()) {
			List<String> lista = Arrays.asList("Gestão", "Filiado", "Outros");
			mv.addObject("lista", lista);
			mv.setViewName("cadastrarAluno");
		} else {
			if (servico.save(aluno).isPresent()) {
				logger.info(">>>>>> controller chamou cadastrar e consultar todos");
				mv.addObject("alunos", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				mv.setViewName("cadastrarAluno");
				mv.addObject("message", "Dados invalidos");
			}
		}
		return mv;
	}

	@PostMapping("/alunos/id/{id}")
	public ModelAndView atualizaAluno(@PathVariable("id") Long id, @Valid Aluno aluno, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("redirect:/grupox/alunos");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			aluno.setId(id);
			return new ModelAndView("atualizarAluno");
		} else {
			servico.atualiza(aluno);
			modelAndView.addObject("alunos", servico.consultaTodos());
			if (servico.save(aluno).isPresent()) {
				logger.info(">>>>>> controller chamou atualizar e consultar todos");
				modelAndView.addObject("alunos", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				modelAndView.setViewName("atualizarAluno");
				modelAndView.addObject("message", "Dados invalidos");
			}
		}
		
		return modelAndView;
	}
}