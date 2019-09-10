package Produtos;

import Outros.Pessoa;

public class Insumo extends Produto {

	public Insumo(String nome, Pessoa usAuth){
		super(nome, usAuth);
	}

	public Insumo(int nextCodigo, String nome, String unidade, Pessoa usAuth) {
		super(nextCodigo,nome,unidade,usAuth);
	}
	
	
}
