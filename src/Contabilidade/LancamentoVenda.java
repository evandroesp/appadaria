package Contabilidade;

import Outros.Pessoa;
import Produtos.Produto;

public class LancamentoVenda extends Lancamento{

	public LancamentoVenda(Produto p, double qtd, double prc, Pessoa cli, Pessoa usAuth) {
		super(p, qtd, prc, cli, usAuth);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return String.format("%-30s\tqtd: %-8s R$ %-8.2f + R$ %-11.2f%-30s%-30s\n", getProduto().getCodNome(), getQuantidade(), getPrecoUnitario(), getPrecoTotal(), getCliente().getCodNome(),getRegUserAuth().getCodNome());
	}
}