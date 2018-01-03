package com.eletroinfo.projectcad.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eletroinfo.projectcad.model.Usuario;
import com.eletroinfo.projectcad.repository.helper.usuario.UsuariosQueries;

public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {
	
	public Optional<Usuario> findByEmail(String email);

}
