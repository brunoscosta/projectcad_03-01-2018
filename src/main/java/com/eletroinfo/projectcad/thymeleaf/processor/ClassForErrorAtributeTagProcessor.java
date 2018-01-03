package com.eletroinfo.projectcad.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.util.FieldUtils;
import org.thymeleaf.templatemode.TemplateMode;

public class ClassForErrorAtributeTagProcessor extends AbstractAttributeTagProcessor {
	
	//variável String NOME_ATRIBUTO recebe classforerror
	private static final String NOME_ATRIBUTO = "classforerror";
	
	//variável int PRECEDENCIA recebe o valor 1000
	private static final int PRECEDENCIA = 1000;
	
	//Construtor de classforerror
	public ClassForErrorAtributeTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, NOME_ATRIBUTO, true, PRECEDENCIA, true);
	}
	
	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		
		//true para temErro caso erro no atributo seja true 
		boolean temErro = FieldUtils.hasErrors(context, attributeValue);
		
		/*valida se tem erro para o atributo, caso tenha acrescenta a classe has-error
		conservando as já existentes*/
		if(temErro) {
			String classesExistentes = tag.getAttributeValue("class");
			structureHandler.setAttribute("class", classesExistentes + " has-error");
		}
	}

}
