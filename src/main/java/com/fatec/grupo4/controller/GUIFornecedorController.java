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

import com.fatec.grupo4.model.Fornecedor;
import com.fatec.grupo4.services.MantemFornecedor;

@Controller
@RequestMapping(path = "/grupo4")
public class GUIFornecedorController {
	Logger logger = LogManager.getLogger(GUIFornecedorController.class);
	@Autowired
	MantemFornecedor servico;

	@GetMapping("/fornecedores")
	public ModelAndView retornaFormDeConsultaTodosFornecedor() {
		ModelAndView mv = new ModelAndView("consultarFornecedor");
		mv.addObject("fornecedores", servico.consultaTodos());
		return mv;
	}

	@GetMapping("/fornecedor")
	public ModelAndView retornaFormDeCadastroDe(Fornecedor fornecedor) {
		ModelAndView mv = new ModelAndView("cadastrarFornecedor");
		List<String> lista = Arrays.asList("Gestão", "Filiado", "Outros");
		mv.addObject("lista", lista);
		mv.addObject("Fornecedor", fornecedor);
		return mv;
	}

	@GetMapping("/fornecedores/{cnpj}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarFornecedor(@PathVariable("cnpj") String cnpj) {
		ModelAndView mv = new ModelAndView("atualizarFornecedor");
		List<String> lista = Arrays.asList("Gestão", "Filiado", "Outros");
		mv.addObject("lista", lista);
		Optional<Fornecedor> fornecedor = servico.consultaPorCnpj(cnpj);
		if (fornecedor.isPresent()) {
			mv.addObject("fornecedor", fornecedor.get()); // retorna um objeto do tipo Fornecedor
		} else {
			return new ModelAndView("paginaMenu");
		}
		return mv; // addObject adiciona objetos para view
	}

	@GetMapping("/fornecedores/id/{id}")
	public ModelAndView excluirNoFormDeConsultaFornecedor(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("consultarFornecedor");
		modelAndView.addObject("fornecedores", servico.consultaTodos());
		return modelAndView;
	}

	@PostMapping("/fornecedores")
	public ModelAndView save(@Valid Fornecedor fornecedor, BindingResult result) {
		ModelAndView mv = new ModelAndView("consultarFornecedor");
		if (result.hasErrors()) {
			List<String> lista = Arrays.asList("Gestão", "Filiado", "Outros");
			mv.addObject("lista", lista);
			mv.setViewName("cadastrarFornecedor");
		} else {
			if (servico.save(fornecedor).isPresent()) {
				logger.info(">>>>>> controller chamou cadastrar e consultar todos");
				mv.addObject("fornecedores", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				mv.setViewName("cadastrarFornecedor");
				mv.addObject("message", "Dados invalidos");
			}
		}
		return mv;
	}

	@PostMapping("/fornecedores/id/{id}")
	public ModelAndView atualizaFornecedor(@PathVariable("id") Long id, @Valid Fornecedor fornecedor, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarFornecedor");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			fornecedor.setId(id);
			return new ModelAndView("redirect:/grupo4/fornecedores");
		} else {
			servico.atualiza(fornecedor);
			modelAndView.addObject("fornecedores", servico.consultaTodos());
			if (servico.save(fornecedor).isPresent()) {
				logger.info(">>>>>> controller chamou atualizar e consultar todos");
				modelAndView.addObject("fornecedores", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				modelAndView.setViewName("redirect:/grupo4/fornecedores");
				modelAndView.addObject("message", "Dados invalidos");
			}
		}
		
		return modelAndView;
	}
}