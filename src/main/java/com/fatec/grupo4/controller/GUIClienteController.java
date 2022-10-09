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

import com.fatec.grupo4.model.Cliente;
import com.fatec.grupo4.services.MantemCliente;

@Controller
@RequestMapping(path = "/grupox")
public class GUIClienteController {
	Logger logger = LogManager.getLogger(GUIClienteController.class);
	@Autowired
	MantemCliente servico;

	@GetMapping("/clientes")
	public ModelAndView retornaFormDeConsultaTodosClientes() {
		ModelAndView mv = new ModelAndView("consultarCliente");
		mv.addObject("clientes", servico.consultaTodos());
		return mv;
	}

	@GetMapping("/cliente")
	public ModelAndView retornaFormDeCadastroDe(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cadastrarCliente");
		List<String> lista = Arrays.asList("Técnico", "Advogado", "Analista");
		mv.addObject("lista", lista);
		mv.addObject("cliente", cliente);
		return mv;
	}

	@GetMapping("/clientes/{cpf}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarCliente(@PathVariable("cpf") String cpf) {
		ModelAndView mv = new ModelAndView("atualizarCliente");
		List<String> lista = Arrays.asList("Técnico", "Advogado", "Analista");
		mv.addObject("lista", lista);
		Optional<Cliente> cliente = servico.consultaPorCpf(cpf);
		if (cliente.isPresent()) {
			mv.addObject("cliente", cliente.get()); // retorna um objeto do tipo cliente
		} else {
			return new ModelAndView("paginaMenu");
		}
		return mv; // addObject adiciona objetos para view
	}

	@GetMapping("/clientes/id/{id}")
	public ModelAndView excluirNoFormDeConsultaCliente(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("consultarCliente");
		modelAndView.addObject("clientes", servico.consultaTodos());
		return modelAndView;
	}

	@PostMapping("/clientes")
	public ModelAndView save(@Valid Cliente cliente, BindingResult result) {
		ModelAndView mv = new ModelAndView("consultarCliente");
		if (result.hasErrors()) {
			List<String> lista = Arrays.asList("Técnico", "Advogado", "Analista");
			mv.addObject("lista", lista);
			mv.setViewName("cadastrarCliente");
		} else {
			if (servico.save(cliente).isPresent()) {
				logger.info(">>>>>> controller chamou cadastrar e consultar todos");
				mv.addObject("clientes", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				mv.setViewName("cadastrarCliente");
				mv.addObject("message", "Dados invalidos");
			}
		}
		return mv;
	}

	@PostMapping("/clientes/id/{id}")
	public ModelAndView atualizaCliente(@PathVariable("id") Long id, @Valid Cliente cliente, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarCliente");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			cliente.setId(id);
			return new ModelAndView("atualizarCliente");
		} else {
			servico.atualiza(cliente);
			modelAndView.addObject("clientes", servico.consultaTodos());
		}
		return modelAndView;
	}
}