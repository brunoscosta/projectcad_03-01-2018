package com.eletroinfo.projectcad.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eletroinfo.projectcad.model.Usuario;
import com.eletroinfo.projectcad.repository.Usuarios;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private Usuarios usuarios;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOp = usuarios.EmailAtivo(email);
		Usuario usuario = usuarioOp.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos!"));
		return new UsuarioLogado(usuario, getPermissoes(usuario));
	}

	//buscando as permissoes do usuario
	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> autorizado = new HashSet<>();
		
		//listando as permissoes de acesso do usuario
		List<String> acessos = usuarios.permissoes(usuario);
		acessos.forEach(p -> autorizado.add(new SimpleGrantedAuthority(p.toUpperCase())));
		
		return autorizado;
	}

}
