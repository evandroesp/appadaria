package Produtos;
import Outros.Pessoa;

public abstract class Produto{
	String nome, unidade;
	
	private ControleEstoque estoque = new ControleEstoque();
	private int codigo;
	private Pessoa regUserAuth;

	public Produto(int cod, String nome, String unidade, Pessoa usAuth){
		this.nome = nome;
		this.unidade = unidade;
		codigo = cod;
		regUserAuth = usAuth;
	}

	public Produto(String nome, Pessoa usAuth){
		this.nome = nome;
		codigo = 0;
		regUserAuth = usAuth;
	}


	public String getNome(){
		return nome;			
	}
	
	public void setNome(String nome){
		this.nome = nome;	
	}
	
	public String getUnidade(){
		return unidade;	
	}
	
	public void setUnidade(String uni){	
		unidade = uni;	
	}
	
	public int getCodigo(){
		return codigo;	
	}
	
	public void setCodigo(int cod){	
		codigo = cod;	
	}
	
	public Pessoa getRegUserAuth() {
		return regUserAuth;
	}

	public void setRegUserAuth(Pessoa regUserAuth) {
		this.regUserAuth = regUserAuth;
	}
	
	public String getCodNome(){
		return(	getCodigo() + " - " + getNome() );
	}

	public ControleEstoque getEstoque() {
		return estoque;
	}

	/*
	public void setEstoque(ControleEstoque estoque) {
		this.estoque = estoque;
	}
	*/
	
	public double getPrecoAtual() {
		return getEstoque().getValorAtual();
	}
	
	public String toString(){
		return String.format("%-40sqtd: %-14s\t Preco: R$ %,-8.2f", getCodNome(), getEstoque().getQuantidadeTotalProduto() + " " + getUnidade(), getPrecoAtual());
	}
}