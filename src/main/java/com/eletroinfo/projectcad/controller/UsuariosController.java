package com.eletroinfo.projectcad.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eletroinfo.projectcad.model.Usuario;
import com.eletroinfo.projectcad.repository.Grupos;
import com.eletroinfo.projectcad.repository.Usuarios;
import com.eletroinfo.projectcad.repository.filter.UsuarioFilter;
import com.eletroinfo.projectcad.service.CadastroUsuarioService;
import com.eletroinfo.projectcad.service.exception.EmailUsuarioJaCadastradoException;
import com.eletroinfo.projectcad.service.exception.ExcecaoSenha;

//Controller para usuarios
@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	//injetando o repository Grupos
	@Autowired
	private Grupos grupos;
	
	//injetando repository Projetos
	@Autowired
	private Usuarios usuarios;


	//renderiza a página de cadastro de usuario
	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		
		//Passando nomes dos grupos a partir da consulta no banco
		mv.addObject("grupos", grupos.findAll());
		return mv;
	}
	
	/*retornando a página de cadastro caso tenha erro, mensagem de erro é exibida e valores
	do form inseridos inicialmente são mantidos*/
	@PostMapping({"/novo", "{\\+d}"})
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(usuario);
		}
		//Se não houver erros, salvar informacoes do usuario no banco
		try {
			cadastroUsuarioService.salvar(usuario);
			
		//retorno para email que ja existe na base, ou senha nao confere
		} catch (EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		} catch (ExcecaoSenha e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		
		/*Emitir mensagem de sucesso e redirecionar para
		 novo cadastro de usuario, redirecionar limpará todos os dados do form*/
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/usuarios/novo");
	}
	
	//realizando pesquisa de usuarios
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter) {
		ModelAndView mv = new ModelAndView("/usuario/PesquisaUsuario");
		mv.addObject("usuarios", usuarios.filtrar(usuarioFilter));
		//mv.addObject("grupos", grupos.findAll());
		return mv;
	}
	
	//realizando a edicao de usuarios
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Usuario usuario = usuarios.comGrupos(codigo);
		ModelAndView mv = novo(usuario);
		mv.addObject(usuario);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo) {
		
			cadastroUsuarioService.excluir(codigo);
		 
		return ResponseEntity.ok().build();
	}
	
}
