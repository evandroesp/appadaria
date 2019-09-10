package Produtos;

import Outros.Pessoa;

import java.util.Calendar;
import java.util.GregorianCalendar;

import Cadastros.Cadastros;

public class Derivado extends Produto {

	private Receita receita;
	
	public Derivado(String nome, Pessoa usAuth){
		super(nome, usAuth);
	}
	
	public Derivado(int nextCodigo, String nome, String unidade, Pessoa usAuth) {
		super(nextCodigo,nome,unidade,usAuth);
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}
	
	/*
	public double getPrecoAtual() {
		return getEstoque().getValorAtual();

	}
	*/
	
	//public void operarProduzir(Estoque est, double qtd)
	public void operarProduzir(double qtd, Pessoa usAuth) {
		Estoque est = new Estoque(qtd,0,new GregorianCalendar(),usAuth);
				
		//aplicando valores da receita
		est.setQuantidade(qtd * getReceita().getRendimento());
		est.setValor(getReceita().getCustoUnitarioProducao() * Cadastros.taxaLucro);
		est.getDataValidade().add(Calendar.DATE, getReceita().getDiasParaVencimento());
		
		getEstoque().addEstoque(est);
		
		System.out.println("\nProduzindo " + est.getQuantidade() + " (x" + getReceita().getRendimento() + " rendimento) " + getUnidade() + " de " + getCodNome());
		getReceita().produzirReceita(qtd);
	}
	
	
}
