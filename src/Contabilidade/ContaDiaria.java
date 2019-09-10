package Contabilidade;

import java.text.SimpleDateFormat;
import java.util.*;

import Outros.Pessoa;
import Produtos.*;

public class ContaDiaria {

	private Calendar fechamentoDia;
	private Pessoa regUserAuth;

	private ArrayList<Lancamento> registrosLancamentos;

	public ContaDiaria(Pessoa usAuth){
		registrosLancamentos = new ArrayList<Lancamento>();
		fechamentoDia = new GregorianCalendar();
		regUserAuth=usAuth;		
	}

	public void addLancamento(Pessoa usAuth, Lancamento lance){
		registrosLancamentos.add(lance);
		regUserAuth = usAuth;
	}

	public Calendar getData() {
		return fechamentoDia;
	}

	public void setData(Calendar data) {
		fechamentoDia = data;
	}

	public String getFormData(){
		SimpleDateFormat formData = new SimpleDateFormat("dd/MM/yyyy");
		return formData.format(fechamentoDia.getTime());
	}

	public String getPessoaFechamento(){
		return regUserAuth.getCodigo() + " - " + regUserAuth.getNome();
	}
	
	//nao aplicado
	public double getBalanco(ArrayList<Lancamento> registrosLancamentosT){
		return getTotalVendasValor(registrosLancamentosT) - getTotalComprasValor(registrosLancamentosT);
	}

	public double getTotalComprasQtd(ArrayList<Lancamento> registrosLancamentosT){
		double qtd=0;
		for(Lancamento x:registrosLancamentosT)
			if (x instanceof LancamentoCompra)
				qtd += x.getQuantidade();
		return qtd;
	}

	public double getTotalComprasValor(ArrayList<Lancamento> registrosLancamentosT){
		double valor=0;
		for(Lancamento x:registrosLancamentosT)
			if (x instanceof LancamentoCompra)
				valor += x.getPrecoTotal();
		return valor;
	}

	public String getRelatorioTotalCompras(ArrayList<Lancamento> registrosLancamentosT) {
		return getTotalComprasQtd(registrosLancamentosT) + "\t - R$ " + getTotalComprasValor(registrosLancamentosT);
	}	

	public double getTotalVendasQtd(ArrayList<Lancamento> registrosLancamentosT){
		double qtd=0;
		for(Lancamento x:registrosLancamentosT)
			if (x instanceof LancamentoVenda)
				qtd += x.getQuantidade();
		return qtd;
	}

	public double getTotalVendasValor(ArrayList<Lancamento> registrosLancamentosT){
		double valor=0;
		for(Lancamento x:registrosLancamentosT)
			if (x instanceof LancamentoVenda)
				valor += x.getPrecoTotal();
		return valor;
	}

	//relatorio
	public String getRelatorioTotalVendas(ArrayList<Lancamento> registrosLancamentosT) {
		return getTotalVendasQtd(registrosLancamentosT) + "\t - R$ " + getTotalVendasValor(registrosLancamentosT);
	}

	//filtra por Funcionario ou Cliente (filtra apenas 1 ator)
	public ArrayList<Lancamento> lancamentosPorPessoa(Pessoa pessoaT, ArrayList<Lancamento> registrosLancamentosT){
		ArrayList<Lancamento> registrosL = new ArrayList<Lancamento>();
		if (pessoaT.getSenha()==null) {
			for(Lancamento x:registrosLancamentosT)
				if (x.getCliente().equals(pessoaT))
					registrosL.add(x);
		}
		else
			for(Lancamento x:registrosLancamentosT)
				if (x.getRegUserAuth().equals(pessoaT))
					registrosL.add(x);
		return registrosL;
	}

	//filtra lista por Produto
	public ArrayList<Lancamento> lancamentosPorProduto(Produto produtoT, ArrayList<Lancamento> registrosLancamentosT){
		ArrayList<Lancamento> registrosL = new ArrayList<Lancamento>();
		for(Lancamento x:registrosLancamentosT)
			if (x.getProduto().equals(produtoT))
				registrosL.add(x);
		return registrosL;
	}

	//reune os filtros de lista 
	public ArrayList<Lancamento> getLancamentosEspecificos(Produto produtoT, Pessoa pessoaT){
		ArrayList<Lancamento> registrosLance = arrayLancamentosOrdenados();
		if(produtoT != null)
			registrosLance = lancamentosPorProduto(produtoT, registrosLance);
		if(pessoaT != null)
			registrosLance = lancamentosPorPessoa(pessoaT, registrosLance);
		return registrosLance;
	}

	public ArrayList<Lancamento> getArrayLancamentos(){
		return registrosLancamentos;
	}

	//apenas compras
	public ArrayList<Lancamento> arrayLancamentosDeCompra(){
		ArrayList<Lancamento> registrosL = new ArrayList<Lancamento>();
		for(Lancamento x:registrosLancamentos)
			if (x instanceof LancamentoCompra)
				registrosL.add(x);
		return registrosL;
	}

	//apenas vendas
	public ArrayList<Lancamento> arrayLancamentosDeVenda(){
		ArrayList<Lancamento> registrosL = new ArrayList<Lancamento>();
		for(Lancamento x:registrosLancamentos)
			if (x instanceof LancamentoVenda)
				registrosL.add(x);
		return registrosL;
	}

	//agrupa lancamentos por compra e venda
	public ArrayList<Lancamento> arrayLancamentosOrdenados(){
		ArrayList<Lancamento> registrosLances = new ArrayList<Lancamento>();
		for(Lancamento x:arrayLancamentosDeCompra())
			registrosLances.add(x);
		for(Lancamento x:arrayLancamentosDeVenda())
			registrosLances.add(x);
		return registrosLances;
	}

	public String getRelatoriosOrdenados(ArrayList<Lancamento> registrosLances){
		String strT="";
		String strVend="\nLancamentos de Compra: " + getFormData() + "\n";
		String strComp="\nLancamentos de Vendas: " + getFormData() + "\n";
		if (registrosLances.size()==0)
			//return null;
			strT += "Nao tem lancamentos!";
		else {
			strT += "\nLancamentos de Compra: " + getFormData() + "\n";
			for (Lancamento x:registrosLances) {
				if (x instanceof LancamentoCompra)
					strComp += x.toString() + "\n";
				else
					strVend += x.toString() + "\n";
			}
			strT += strComp + "Total de Compras:\t " + getRelatorioTotalCompras(registrosLances);
			strT += strVend + "Total de Vendas:\t " + getRelatorioTotalVendas(registrosLances);
		}
		return strT;
	}	

	public String getRelatorios(ArrayList<Lancamento> registrosL){
		String strT="Lancamentos: " + getFormData() + "\n";
		if (registrosL.size()<1)
			return "";
			//strT += "Nao tem lancamentos!\n";
		else
			for (Lancamento x:registrosL)
				strT += x.toString();
		return strT;
	}	

	public String getSaldo() {
		String strT="\nSaldo: " + getFormData() + "\tQtd\t - R$\n";
		strT += "Venda de Produto:\t" + getTotalVendasQtd(getArrayLancamentos()) + "\t - " + getTotalVendasValor(getArrayLancamentos()) + "\n";
		strT += "Compra de Insumo:\t" + getTotalComprasQtd(getArrayLancamentos()) + "\t - " + getTotalComprasValor(getArrayLancamentos()) + "\n";
		return strT;
	}

	public String toString(){
		return getRelatorios(registrosLancamentos);
	}
}
