package com.eletroinfo.projectcad.config.init;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.eletroinfo.projectcad.config.JPAConfig;
import com.eletroinfo.projectcad.config.SecurityConfig;
import com.eletroinfo.projectcad.config.ServiceConfig;
import com.eletroinfo.projectcad.config.webConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class, SecurityConfig.class };
	}
	
	//definindo a classe de configuracao padrao webconfig
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { webConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	//forcando utf8 como charset padrao
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		return new Filter[] { characterEncodingFilter };
	}

}
