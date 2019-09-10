package Cadastros;

import java.text.SimpleDateFormat;
import java.util.*;

import Contabilidade.ContaDiaria;
import Contabilidade.Lancamento;
import Contabilidade.LancamentoCompra;
import Outros.Pessoa;
import Produtos.Produto;

public class CadastroContabil {

	private ArrayList<ContaDiaria> registrosDiarios;
	SimpleDateFormat formData = new SimpleDateFormat("dd/MM/yyyy");
//	private double txLucro;
	private double dinheiro=10000;

	public CadastroContabil(){
		registrosDiarios = new ArrayList<ContaDiaria>();
//		txLucro = 1.1;
	}
/*
	public double getTaxaLucro() {
		return txLucro;
	}

	public void setTaxaLucro(double txLucro) {
		this.txLucro = txLucro;
	}
*/	
	public double getDinheiro() {
		return dinheiro;
	}

	private void setDinheiro(double dinheiro) {
		this.dinheiro += dinheiro;
	}

	public String getFormData(Calendar dataT){
		return formData.format(dataT.getTime());
	}

	
	public void addLancamento(Pessoa usAuth, Lancamento lance){
		ContaDiaria diaAtual;
		
		//verifica se o ultimo registro coincide com o dia atual, caso contrario cria um novo registro
		if (getUltimoRegistro(usAuth).getData().get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR))
			diaAtual = getUltimoRegistro(usAuth);
		else {
			diaAtual = new ContaDiaria(usAuth);
			registrosDiarios.add(diaAtual);
		}
		//altera dinheiro em conta
		if(lance instanceof LancamentoCompra)
			setDinheiro(-lance.getPrecoTotal());
		else
			setDinheiro(lance.getPrecoTotal());
		diaAtual.addLancamento(usAuth, lance);
	}

	//retorna o ultimo registro
	public ContaDiaria getUltimoRegistro(Pessoa usAuth) {
		if (registrosDiarios.isEmpty())
			registrosDiarios.add(new ContaDiaria(usAuth));
		return registrosDiarios.get(registrosDiarios.size()-1);
	}

	//retorna intervalo de registros
	public ArrayList<ContaDiaria> getConsultarIntervaloDatas(Calendar inicio, Calendar fim){
		ArrayList<ContaDiaria> registrosD = new ArrayList<ContaDiaria>();
		for(ContaDiaria x:registrosDiarios){
			if (x.getData().after(fim))
				break;
			if (!x.getData().before(inicio))
				registrosD.add(x);
		}
		return registrosD;
	}

	//Relatórios totais por Insumos e/ou Derivados 
	public String getRelatorioEspecifico(int caso, Calendar dataIni){
		ArrayList<ContaDiaria> registrosD = getConsultarIntervaloDatas(dataIni,Calendar.getInstance());
		String strT = "Consulta ";
		if (caso == 1)
			strT += "Insumos\t "+ getFormData(dataIni)  + " - " + getFormData(Calendar.getInstance()) + "\n";
		else if (caso == 2)
			strT += "Derivados\t "+ getFormData(dataIni)  + " - " + getFormData(Calendar.getInstance()) + "\n";
		else if (caso == 3)
			strT += "Insumos e Derivados\t "+ getFormData(dataIni)  + " - " + getFormData(Calendar.getInstance()) + "\n";
			strT += "Produto                       	Quantidade    Valor Unitário - Total       Cliente                       Usuario\n";
		for(ContaDiaria x:registrosD) {
			if (caso == 1)
				strT += x.getRelatorios(x.arrayLancamentosDeCompra());
			else if (caso == 2)
				strT += x.getRelatorios(x.arrayLancamentosDeVenda());
			else if (caso == 3)
				strT += x.getRelatorios(x.arrayLancamentosOrdenados());
		}
		return strT;
	}

	//Relatórios especificos por Produto, Pessoa e datas
	public String getRelatorioEspecifico(Produto produtoT, Pessoa pessoaT, Calendar dataIni, Calendar dataFim){
		if (dataFim==null)
			dataFim = new GregorianCalendar();
		if (dataIni==null)
			dataIni = new GregorianCalendar();
		dataIni.set(Calendar.HOUR_OF_DAY,0);
		ArrayList<ContaDiaria> registrosD = getConsultarIntervaloDatas(dataIni,dataFim);
		ArrayList<Lancamento> registrosLances;
		String strT = "Consulta " + getFormData(dataIni)  + " - " + getFormData(dataFim) + "\n";
		if (produtoT != null)
			strT += "Produto: " + produtoT.getCodNome() + "\n";
		if (pessoaT != null)
			strT += "Pessoa: " + pessoaT.getCodNome() + "\n";
		for(ContaDiaria x:registrosD) {
			registrosLances = x.getLancamentosEspecificos(produtoT, pessoaT);
			for(Lancamento y:registrosLances) 
				strT += x.getFormData() + "\t " + y.toString();
		}
		return strT;
	}

	//Prepara registros mensais MM/YYYY
	public String getFormMes(Calendar dataT) {
		return formData.format(dataT.getTime()).substring(3);
	}

	//Relatórios mensais MM/YYYY
	public String getRelatorioTotalMensal(){
		Calendar dataT = registrosDiarios.get(0).getData();
		String strT="\nRelatorio Mensal\n";
		double total = 0;
		for(ContaDiaria x:registrosDiarios){
			if (getFormMes(dataT).compareTo(getFormMes(x.getData())) !=0 ) {
				strT+= getFormMes(dataT) + "\t - R$ " + total + "\n";
				total = x.getBalanco(x.getArrayLancamentos());
				dataT = x.getData();
			}
			else
				total += x.getBalanco(x.getArrayLancamentos());
		}
		strT+= getFormMes(dataT) + "\t - R$ " + total + "\n";
		return strT;
	}
}
