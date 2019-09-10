package Contabilidade;

import Outros.Pessoa;
import Produtos.Produto;

public abstract class Lancamento {

	private Produto produto;
	private double quantidade;
	private double precoUnitario;
	private Pessoa cliente;
	private Pessoa regUserAuth;
	
	public Lancamento(Produto p,double qtd, double prc, Pessoa cli, Pessoa usAuth){
		produto = p;
		quantidade = qtd;
		precoUnitario = prc;
		cliente = cli;
		regUserAuth = usAuth;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPreco(double preco) {
		this.precoUnitario = preco;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public Pessoa getRegUserAuth() {
		return regUserAuth;
	}

	public void setRegUserAuth(Pessoa regUserAuth) {
		this.regUserAuth = regUserAuth;
	}
	
	public double getPrecoTotal() {
		return precoUnitario*quantidade;
	}
		
	public abstract String toString();
}
