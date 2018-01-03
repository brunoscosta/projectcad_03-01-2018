package com.eletroinfo.projectcad.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Controle para a pagina de login
@Controller
public class LoginController {
	
	/*mapeando página de login e injetando informacoes do usuario
	na variavel user quando logado*/
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		
		//se a variavel user nao estiver vazia, redireciona para dashboard
		if(user != null) {
			return "redirect:/projetos";
		}
		
		//não autenticado retorna para login
		return "Login";
	}
	
	//mapeando a pagina de acesso negado
	@GetMapping("/acesso-negado")
	public String acessoNegado() {
		return "acesso-negado";
	}

}
