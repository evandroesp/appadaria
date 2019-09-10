package Outros;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Produtos.Insumo;

public class InsumoPrecoFornecedor {
	
	SimpleDateFormat formData = new SimpleDateFormat("dd/MM/yyyy");

	private Insumo insumo;
	private double preco;
	private Pessoa fornecedor;
	private Calendar registroPreco;
	private Pessoa regUserAuth;
	
	public InsumoPrecoFornecedor(Insumo insumo, double preco, Pessoa fornec, Calendar data, Pessoa usAuth) {
		this.insumo = insumo;
		this.preco = preco;
		fornecedor = fornec;
		registroPreco = new GregorianCalendar();
		setRegistroPreco(data);
		regUserAuth = usAuth;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public double getCotacao() {
		return preco;
	}

	public void setCotacao(double preco) {
		this.preco = preco;
	}

	public Pessoa getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Pessoa fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Calendar getRegistroPreco() {
		return registroPreco;
	}

	public void setRegistroPreco(Calendar registroPreco) {
		this.registroPreco = registroPreco;
	}

	public Pessoa getRegUserAuth() {
		return regUserAuth;
	}

	public void setRegUserAuth(Pessoa regUserAuth) {
		this.regUserAuth = regUserAuth;
	}
	
	public String toString() {
		return(getInsumo().getCodNome() + "\t " + formData.format(getRegistroPreco().getTime())
				+ "\t " + getCotacao() + "\t\t " + getFornecedor().getCodNome() + "\n");
	}

	

}
