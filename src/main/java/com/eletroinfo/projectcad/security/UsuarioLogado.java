package com.eletroinfo.projectcad.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.eletroinfo.projectcad.model.Usuario;

public class UsuarioLogado extends User {
	
	private static final long serialVersionUID = 1L;

	private Usuario user;

	public UsuarioLogado(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.user = usuario;
	}

	public Usuario getUsuario() {
		return user;
	}

}
