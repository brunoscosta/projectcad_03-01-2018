package com.eletroinfo.projectcad.repository.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.eletroinfo.projectcad.model.Projeto;
import com.eletroinfo.projectcad.repository.filter.ProjetoFilter;

public class ProjetosImpl implements ProjetosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Projeto> filtrar(ProjetoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Projeto.class);
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getDataInicio())) {
				criteria.add(Restrictions.ge("dataInicio", filtro.getDataInicio()));
			}
			
			if (!StringUtils.isEmpty(filtro.getDataFim())) {
				criteria.add(Restrictions.le("dataFim", filtro.getDataFim()));
			}
			
			if (filtro.getStatus() != null) {
				criteria.add(Restrictions.eq("status", filtro.getStatus()));
			}
			
			
		}
		return criteria.list();
	}

}
