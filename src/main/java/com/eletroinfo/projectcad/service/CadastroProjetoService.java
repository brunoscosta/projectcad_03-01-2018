package com.eletroinfo.projectcad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eletroinfo.projectcad.model.Projeto;
import com.eletroinfo.projectcad.repository.Projetos;

@Service
public class CadastroProjetoService {
	
	@Autowired
	private Projetos projetos;
	
	//Insert dos dados do novo projeto no banco de dados
	@Transactional
	public void salvar(Projeto projeto) {
		projetos.save(projeto);
	}
	
	@Transactional
	public void excluir(Long codigo) {
		projetos.delete(codigo);
	}
	
}
