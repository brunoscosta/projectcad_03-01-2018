package com.eletroinfo.projectcad.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.eletroinfo.projectcad.service.CadastroProjetoService;

@Configuration
@ComponentScan(basePackageClasses = CadastroProjetoService.class)
public class ServiceConfig {

}
