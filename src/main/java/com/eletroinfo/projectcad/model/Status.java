package com.eletroinfo.projectcad.model;

/*enum para status do projeto. informacoes da tela de cadastro*/
public enum Status {
	
	PROGRAMADO("Programado"),
	DESENVOLVIMENTO("Desenvolvimento"),
	TESTE("Teste"),
	ENTREGUE("Entregue");
	
	private String descricao;
	
	Status(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}
