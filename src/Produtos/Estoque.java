package Produtos;

import java.text.SimpleDateFormat;
import java.util.*;

import Outros.Pessoa;

public class Estoque {

	private Calendar dataEntrada = new GregorianCalendar();
	private Calendar dataValidade = new GregorianCalendar();

	SimpleDateFormat formData = new SimpleDateFormat("dd/MM/yyyy");

	private double quantidade=0;
	private double valor=0;
	private Pessoa regUserAuth;

	public Estoque(double qtd, double val, Calendar validade, Pessoa usAuth){
		quantidade=qtd;
		valor=val;
		setDataEntrada(new GregorianCalendar());
		setDataValidadeDia(validade);
		regUserAuth = usAuth;

	}

	public double getQuantidade(){
		return quantidade;
	}

	public void setQuantidade(double qtd){
		quantidade=qtd;
	}

	public double getValor(){
		return valor;
	}

	public void setValor(double val) {
		valor=val;
	}

	public Calendar getDataEntrada(){
		return dataEntrada;
	}

	public void setDataEntrada(Calendar data) {
		dataEntrada = data;			
	}

	public Calendar getDataValidade() {
		return dataValidade;
	}

	public void setDataValidadeDia(Calendar data){
		dataValidade = data;
	}

	public String getFormDataEntrada(){
		return(formData.format(dataEntrada.getTime()));
	}

	public String getFormDataValidade(){
		return(formData.format(dataValidade.getTime()));
	}

	public String toString(){
		return(		"\n" + getFormDataEntrada() +"\t"
				+ 	getQuantidade() +"\t"
				+ 	getValor() +"\t"
				+	getFormDataValidade() +"\t");
	}

	public Pessoa getRegUserAuth() {
		return regUserAuth;
	}

	public void setRegUserAuth(Pessoa regUserAuth) {
		this.regUserAuth = regUserAuth;
	}
}