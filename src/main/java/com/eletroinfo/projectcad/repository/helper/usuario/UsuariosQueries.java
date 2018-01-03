package com.eletroinfo.projectcad.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import com.eletroinfo.projectcad.model.Usuario;
import com.eletroinfo.projectcad.repository.filter.UsuarioFilter;

public interface UsuariosQueries {
	
	public Optional<Usuario> EmailAtivo(String email);
	
	public List<String> permissoes(Usuario usuario);
	
	public List<Usuario> filtrar(UsuarioFilter filtro);
	
	public Usuario comGrupos(Long codigo);

}
