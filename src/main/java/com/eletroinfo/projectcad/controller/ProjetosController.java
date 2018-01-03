package com.eletroinfo.projectcad.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eletroinfo.projectcad.model.Projeto;
import com.eletroinfo.projectcad.model.Status;
import com.eletroinfo.projectcad.repository.Projetos;
import com.eletroinfo.projectcad.repository.filter.ProjetoFilter;
import com.eletroinfo.projectcad.service.CadastroProjetoService;

//Controller para projetos

@Controller
public class ProjetosController {
	
	@Autowired
	private CadastroProjetoService cadastroProjetoService;
	
	//injetando repository Projetos
	@Autowired
	private Projetos projetos;

	//renderiza a página de cadastro de projetos incluindo o enum de status
	@RequestMapping("/projetos/novo")
	public ModelAndView novo(Projeto projeto) {
		ModelAndView mv = new ModelAndView("projeto/CadastroProjeto");
		mv.addObject("status", Status.values());
		return mv;
	}
	
	
	/*retornando a página de cadastro caso tenha erro, mensagem de erro é exibida e valores
	do form inseridos inicialmente são mantidos*/
	@RequestMapping(value = { "/projetos/novo", "/projetos/{\\d}" }, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Projeto projeto, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(projeto);
		}
		
		/*Se não houver erros, salvar informacoes do projeto no banco, emitir mensagem de sucesso
		e redirecionar para novo cadastro de projeto, redirecionar limpará todos os dados do form*/
		cadastroProjetoService.salvar(projeto);
		attributes.addFlashAttribute("mensagem", "Projeto Salvo com sucesso!");
		return new ModelAndView("redirect:/projetos/novo");		
	}
	
	//realizando pesquisa de projetos
	@GetMapping("/projetos")
	public ModelAndView pesquisar(ProjetoFilter projetoFilter, BindingResult result) {
		ModelAndView mv = new ModelAndView("projeto/PesquisaProjetos");
		mv.addObject("status", Status.values());
		
		mv.addObject("projetos", projetos.filtrar(projetoFilter));
		return mv;
		
	}
	
	@GetMapping("/projetos/{codigo}")
	public ModelAndView editar(@PathVariable("codigo")  Projeto projeto ) {
		ModelAndView mv = novo(projeto);
		mv.addObject(projeto);

		return mv;
	}
	
	@DeleteMapping("/projetos/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo) {
		
			cadastroProjetoService.excluir(codigo);
		 
		return ResponseEntity.ok().build();
	}
	
}
