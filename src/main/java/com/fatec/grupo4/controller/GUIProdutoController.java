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

import com.fatec.grupo4.model.Produto;
import com.fatec.grupo4.services.MantemProduto;

@Controller
@RequestMapping(path = "/grupo4")
public class GUIProdutoController {
	Logger logger = LogManager.getLogger(GUIProdutoController.class);
	@Autowired
	MantemProduto servico;

	// /produto/Produtos
	@GetMapping("/produtos")
	public ModelAndView retornaFormDeConsultaTodosProduto() {
		ModelAndView mv = new ModelAndView("consultarProduto");
		mv.addObject("produtos", servico.consultaTodos());
		return mv;
	}

	// /produto/produto
	@GetMapping("/produto")
	public ModelAndView retornaFormDeCadastroDe(Produto produto) {
		ModelAndView mv = new ModelAndView("cadastrarProduto");
		List<String> lista = Arrays.asList("Gestão", "Filiado", "Outros");
		mv.addObject("lista", lista);
		mv.addObject("Produto", produto);
		return mv;
	}

	@GetMapping("/produto/{nome}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarProduto(@PathVariable("nome") String nome) {
		ModelAndView mv = new ModelAndView("atualizarProduto");
		Optional<Produto> produto = servico.consultaPorNome(nome);
		if (produto.isPresent()) {
			mv.addObject("produto", produto.get()); // retorna um objeto do tipo Produto
		} else {
			return new ModelAndView("paginaMenu");
		}
		return mv; // addObject adiciona objetos para view
	}

	@GetMapping("/produtos/id/{id}")
	public ModelAndView excluirNoFormDeConsultaProduto(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("redirect:/grupo4/produtos");
		modelAndView.addObject("produtos", servico.consultaTodos());
		return modelAndView;
	}

	@PostMapping("/produtos")
	public ModelAndView save(@Valid Produto produto, BindingResult result) {
		ModelAndView mv = new ModelAndView("consultarProduto");
		if (result.hasErrors()) {
			mv.setViewName("cadastrarProduto");
		} else {
			if (servico.save(produto).isPresent()) {
				servico.save(produto);
				logger.info(">>>>>> controller chamou cadastrar e consultar todos");
				mv.addObject("produto", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				mv.setViewName("cadastrarProduto");
				mv.addObject("message", "Dados invalidos");
			}
		}
		return mv;
	}

	@PostMapping("/produto/id/{id}")
	public ModelAndView atualizarProduto(@PathVariable("id") Long id, @Valid Produto produto, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarProduto");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			produto.setId(id);
			return new ModelAndView("redirect:/grupo4/produtos");
		} else {
		servico.atualiza(produto);
		modelAndView.addObject("produtos", servico.consultaTodos());
		}
		return modelAndView;
	}
}
