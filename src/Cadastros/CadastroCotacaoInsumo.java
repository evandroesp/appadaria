package Cadastros;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Outros.InsumoPrecoFornecedor;
import Outros.Pessoa;
import Produtos.Insumo;

public class CadastroCotacaoInsumo {

	private List<InsumoPrecoFornecedor> listaCotacao;
	SimpleDateFormat formData = new SimpleDateFormat("dd/MM/yyyy");

	public CadastroCotacaoInsumo() {
		listaCotacao = new ArrayList<InsumoPrecoFornecedor>();
	}

	public boolean addCotacao(Insumo ins, Pessoa pes, double preco, Calendar data, Pessoa usAuth) {
		try {
			listaCotacao.add(new InsumoPrecoFornecedor(ins, preco, pes, data, usAuth));
			getRelatorioPorInsumo();
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	/*Exception in thread "main" java.util.ConcurrentModificationException
	ERRO: muda o indice e o tamanho da lista quando remove varios.
	public boolean removeInsumosInexist(Insumo insRemov) {
		for (InsumoPrecoFornecedor x:listaCotacao)
			if (x.getInsumo().equals(insRemov))
				listaCotacao.remove(x);
		return true;
	*/
	public void removeInsumoInexist(Insumo insRemov) {
		for (int i=0;i<listaCotacao.size();i++){
			if (listaCotacao.get(i).getInsumo().equals(insRemov)){
				listaCotacao.remove(i);
				i--;
			}
		}
	}

	/*Exception in thread "main" java.util.ConcurrentModificationException
	ERRO: muda o indice e o tamanho da lista quando remove varios.
	public boolean removePessoasInexist(Pessoa pesRemov) {
		for (InsumoPrecoFornecedor x:listaCotacao)
			if (x.getFornecedor().equals(pesRemov))
				listaCotacao.remove(x);
		return true;
	}
	*/
	
	public void removePessoaInexist(Pessoa pesRemov) {
		for (int i=0;i<listaCotacao.size();i++){
			if (listaCotacao.get(i).getFornecedor().equals(pesRemov)){
				listaCotacao.remove(i);
				i--;
			}
		}
	}

	//ordena e retorna toString
	public String getRelatorioPorInsumo() {
		boolean vf;
		String strT = "\nCotacao por Insumo:";
		for(int i=0;i<listaCotacao.size()-1;i++) {
			for(int j=i+1; j<listaCotacao.size() ;j++) {
				vf=true;
				if(listaCotacao.get(i).getInsumo().getNome().compareToIgnoreCase(listaCotacao.get(j).getInsumo().getNome())<0)
					vf=false;
				else if(listaCotacao.get(i).getInsumo().getNome().compareToIgnoreCase(listaCotacao.get(j).getInsumo().getNome())==0)
					if(listaCotacao.get(i).getCotacao()<listaCotacao.get(j).getCotacao())
						vf=false;
					else if(listaCotacao.get(i).getCotacao()==listaCotacao.get(j).getCotacao())
						if(listaCotacao.get(i).getRegistroPreco().after(listaCotacao.get(j).getRegistroPreco()))
							vf=false;
				if(vf)
					listaCotacao.add(i,listaCotacao.remove(j));
			}
		}
		strT += toString();
		return strT;
	}

	//ordena e retorna toString
	public String getRelatorioPorPreco() {
		boolean vf;
		String strT = "\nCotacao pelo menor preco:";
		for(int i=0;i<listaCotacao.size()-1;i++) {
			for(int j=i+1; j<listaCotacao.size() ;j++) {
				vf=true;
				if(listaCotacao.get(i).getCotacao()<listaCotacao.get(j).getCotacao())
					vf=false;
				else if(listaCotacao.get(i).getCotacao()==listaCotacao.get(j).getCotacao())
					if(listaCotacao.get(i).getRegistroPreco().after(listaCotacao.get(j).getRegistroPreco()))
						vf=false;
					else if(listaCotacao.get(i).getRegistroPreco().compareTo(listaCotacao.get(j).getRegistroPreco())==0)
						if(listaCotacao.get(i).getInsumo().getNome().compareToIgnoreCase(listaCotacao.get(j).getInsumo().getNome())<0)
							vf=false;
				if(vf)
					listaCotacao.add(i,listaCotacao.remove(j));
			}
		}
		strT += toString();
		return strT;
	}

	//ordena e retorna toString
	public String getRelatorioPorPessoa() {
		boolean vf;
		String strT = "\nCotacao por Pessoa:";
		for(int i=0;i<listaCotacao.size()-1;i++) {
			for(int j=i+1; j<listaCotacao.size() ;j++) {
				vf=true;
				if(listaCotacao.get(i).getFornecedor().getNome().compareToIgnoreCase(listaCotacao.get(j).getFornecedor().getNome())>=0)
					vf=false;
				else if(listaCotacao.get(i).getFornecedor().getNome().compareToIgnoreCase(listaCotacao.get(j).getFornecedor().getNome())==0)
					if(listaCotacao.get(i).getFornecedor().getCodigo() < listaCotacao.get(j).getFornecedor().getCodigo())
						vf=false;
					else if(listaCotacao.get(i).getFornecedor().getCodigo() == listaCotacao.get(j).getFornecedor().getCodigo())
						if(listaCotacao.get(i).getInsumo().getNome().compareToIgnoreCase(listaCotacao.get(j).getInsumo().getNome())<0)
							vf=false;
						else if(listaCotacao.get(i).getInsumo().getNome().compareToIgnoreCase(listaCotacao.get(j).getInsumo().getNome())==0)
							if(listaCotacao.get(i).getCotacao()<listaCotacao.get(j).getCotacao())
								vf=false;
				if(vf)
					listaCotacao.add(i,listaCotacao.remove(j));
			}
		}
		strT += toString();
		return strT;
	}

	//ordena e retorna lista de Fornecedores do Insumo escolhido
	public String getRelatorioInsumoEspecifico(Insumo insumoT) {
		getRelatorioPorPreco();
		String str = "\nCotacao do insumo: " + insumoT.getCodNome() + "\nindice\tProduto: \t Data:\t\t Preco:\t\t Fornecedor:\n";
		int contador=0;
		for(InsumoPrecoFornecedor x:listaCotacao)
			if (x.getInsumo().equals(insumoT)) {
				contador++;
				str+= contador + " - \t" + x;
			}
		if (contador == 0)
			str += "\nSem registro de fornecedor!";
		return str;
	}

	//retorna uma cotacao escolhida pelo indice da lista anterior "getRelatorioPorProduto()"
	public InsumoPrecoFornecedor getCotacaoPorInsumo(Insumo insumoT, int indice) {
		getRelatorioPorPreco();
		int contador=0;
		for(InsumoPrecoFornecedor x:listaCotacao) {
			if (x.getInsumo().equals(insumoT))
				contador++;
			if (contador == indice)
				return x;
		}
		return null;
	}

	//ordena e retorna lista de Insumos do Fornecedor escolhido
	public String getRelatorioPorFornecedor(Pessoa fornecedorT) {
		getRelatorioPorInsumo();
		int contador=0;
		String str = "\nCotacao do fornecedor:" + fornecedorT.getCodNome() + "\nProduto: \t Data:\t\t Preco:\t\t Fornecedor:\n";
		for(InsumoPrecoFornecedor x:listaCotacao)
			if (x.getFornecedor().equals(fornecedorT)) { 
				contador++;
				str += contador + " - \t" + x;
			}
		if (contador == 0)
			str += "\nSem registro de produtos!";
		return str;
	}
	
	//retorna uma cotacao escolhida pelo indice da lista anterior "getRelatorioPorFornecedor()"
	public InsumoPrecoFornecedor getCotacaoPorFornecedor(Pessoa fornecedor, int indice) {
		getRelatorioPorInsumo();
		int contador=0;
		for(InsumoPrecoFornecedor x:listaCotacao) {
			if (x.getFornecedor().equals(fornecedor))
				contador++;
			if (contador == indice)
				return x;
		}
		return null;
	}

	/*
	public int sizeCotacaoEspecifico(Insumo insT) {
		if (insT==null)
			return listaCotacao.size();
		int count=0;
		for (InsumoPrecoFornecedor x:listaCotacao)
			if(x.getInsumo().equals(insT))
				count++;
		return count;
	}
	 */

	public String toString() {
		if(listaCotacao.isEmpty())
			return "Sem Registros";
		String strT = "\nProduto: \t Data:\t\t Preco:\t\t Fornecedor:\n";
		for(InsumoPrecoFornecedor x:listaCotacao)
			strT += x;
		return strT;
	}
}
