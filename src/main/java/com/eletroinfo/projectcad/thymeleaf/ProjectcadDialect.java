package com.eletroinfo.projectcad.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.eletroinfo.projectcad.thymeleaf.processor.ClassForErrorAtributeTagProcessor;
import com.eletroinfo.projectcad.thymeleaf.processor.MenuAtibuteTagProcessor;
import com.eletroinfo.projectcad.thymeleaf.processor.MessageElementTagProcessor;

public class ProjectcadDialect extends AbstractProcessorDialect {

	public ProjectcadDialect() {
		super("eletroinfo_Projectcad","projectcad", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAtributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAtibuteTagProcessor(dialectPrefix));
		return processadores;
	}
}
