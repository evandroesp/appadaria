package Outros;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Produtos.Derivado;

public class Encomenda {
	private Derivado derivado;
	private double qtd;
	private Calendar diaSolicitado;
	private Calendar prazoEntrega;
	private Pessoa cliente;
	private Pessoa regUserAuth;
	
	SimpleDateFormat formData = new SimpleDateFormat("dd/MM/yyyy");
	
	public Encomenda(Derivado der, double qtd, Pessoa cli, Calendar prazo, Pessoa usAuth) {
		this.derivado = der;
		this.qtd = qtd;
		this.cliente = cli;
		diaSolicitado = new GregorianCalendar();
		this.prazoEntrega=prazo;
		regUserAuth = usAuth;
	}

	public Derivado getDerivado() {
		return derivado;
	}

	public void setDerivado(Derivado derivado) {
		this.derivado = derivado;
	}

	public double getQtd() {
		return qtd;
	}

	public void setQtd(double qtd) {
		this.qtd = qtd;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public Calendar getDiaSolicitado() {
		return diaSolicitado;
	}

	public void setDiaSolicitado(Calendar diaSolicitado) {
		this.diaSolicitado = diaSolicitado;
	}

	public Calendar getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(Calendar prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public Pessoa getRegUserAuth() {
		return regUserAuth;
	}

	public void setRegUserAuth(Pessoa regUserAuth) {
		this.regUserAuth = regUserAuth;
	}
	
	public boolean isDisponivel() {
		if( (derivado.getEstoque().getQuantidadeTotalProduto() + derivado.getReceita().limiteGeralProducao()) >= getQtd())
			return true;
		return false;
	}
	
	public String toString() {
		String strT = String.format("%-30s ",derivado.getCodNome());
		strT+= String.format("%-8.2f",getQtd());
		if(getPrazoEntrega().getTime().before(Calendar.getInstance().getTime()))
			strT+= String.format("%-10s#!  ",formData.format(getPrazoEntrega().getTime()));	
		else
			strT+= String.format("%-14s",formData.format(getPrazoEntrega().getTime()));
		strT+= String.format("%-20s", getCliente().getCodNome());
		strT+= String.format("%-14s", formData.format(getDiaSolicitado().getTime()));
		strT+= String.format("%-20s",getRegUserAuth().getCodNome());
		return strT;
	}

}
