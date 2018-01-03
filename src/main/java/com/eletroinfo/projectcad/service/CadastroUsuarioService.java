package com.eletroinfo.projectcad.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.eletroinfo.projectcad.model.Usuario;
import com.eletroinfo.projectcad.repository.Usuarios;
import com.eletroinfo.projectcad.service.exception.EmailUsuarioJaCadastradoException;
import com.eletroinfo.projectcad.service.exception.ExcecaoSenha;

@Service
public class CadastroUsuarioService {
	
	//injetando repository Usuarios
	@Autowired
	private Usuarios usuarios;
	
	//injetando PasswordEncoder do spring security
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*Iniciando uma nova transação para salvar dados do usuario
	(PersistenceContext, Hibernate Session)*/
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarios.findByEmail(usuario.getEmail());
		
		/*Para novos usuarios: Verifica se o email informado no cadastro de usuario 
		  ja consta na base caso conste retorna a mensagem de email ja cadastrado*/
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado!");
		}
		
		//Senha obrigatória para usuario novo
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new ExcecaoSenha ("Senha é obrigatória para novo usuário!");
		}
		
		
		/*Criptografando a senha do usuário e confirmacao de senha
		 com bCrypt do spring security*/		
		if (usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		} else if (StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
		}
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		
		/*Caso nao entre no if de email ja cadastrado, ou senha
		obrigatória os dados sao salvos*/
		usuarios.save(usuario);
	}
	
	//delete de usuarios
	@Transactional
	public void excluir(Long codigo) {
		usuarios.delete(codigo);
	}

}
