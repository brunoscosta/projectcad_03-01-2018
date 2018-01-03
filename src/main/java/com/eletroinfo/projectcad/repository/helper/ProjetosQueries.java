package com.eletroinfo.projectcad.repository.helper;

import java.util.List;

import com.eletroinfo.projectcad.model.Projeto;
import com.eletroinfo.projectcad.repository.filter.ProjetoFilter;

public interface ProjetosQueries {

	
	public List<Projeto> filtrar(ProjetoFilter filtro);
}
