package Cadastros;

import java.util.*;

import Outros.Encomenda;
import Outros.Pessoa;
import Produtos.Derivado;

public class CadastroEncomendas {

	private List<Encomenda> listaEncomenda;

	public CadastroEncomendas() {
		listaEncomenda = new ArrayList<Encomenda>();
	}
	
/*
	public boolean addEncomenda(Derivado deri, double qtd, Pessoa cli,Calendar prazo, Pessoa usAuth) {
		listaEncomenda.add(new Encomenda(deri, qtd, cli, prazo, usAuth));
		ordemPrazo();
		return true;
	}
*/
	
	public boolean addEncomenda(Encomenda encT) {
		listaEncomenda.add(encT);
		ordemPrazo();
		return true;
	}

	public Encomenda getEncomenda(int i) {
		if(i>0 && !listaEncomenda.isEmpty())
			return listaEncomenda.get(i-1);
		return null;
	}
	
	public boolean removeEncomenda(Encomenda encomT) {
		try {
			return listaEncomenda.remove(encomT);
		} catch (Exception e) {
			System.out.println("Erro ao remover encomenda!");
			return false;
		}
	}

	/*Exception in thread "main" java.util.ConcurrentModificationException
	 * ERRO: alteracao da lista e indices quando executa remove
	public boolean removeDerivadoInexist(Derivado deriRemov) {
		for (Encomenda x:listaEncomenda)
			if (x.getDerivado().equals(deriRemov))
				listaEncomenda.remove(x);
		return true;
	}

	public boolean removePessoasInexist(Pessoa pesRemov) {
		for (Encomenda x:listaEncomenda)
			if (x.getCliente().equals(pesRemov))
				listaEncomenda.remove(x);
		return true;
	}
	*/
	public void removeDerivadoInexist(Derivado deriRemov) {
		for (int i=0;i<listaEncomenda.size();i++){
			if (listaEncomenda.get(i).getDerivado().equals(deriRemov)){
				listaEncomenda.remove(i);
				i--;
			}
		}
	}

	public void removePessoaInexist(Pessoa pesRemov) {
		for (int i=0;i<listaEncomenda.size();i++){
			if (listaEncomenda.get(i).getCliente().equals(pesRemov)){
				listaEncomenda.remove(i);
				i--;
			}
		}
	}

	public void ordemDisponibilidade() {
		ordemPrazo();
		Calendar pertoDoPrazo = new GregorianCalendar();
		pertoDoPrazo.add(Calendar.DATE, 15);
		for(int i=0;i<listaEncomenda.size()-1;i++) {
			for(int j=i+1; j<listaEncomenda.size() ;j++) {
				if (listaEncomenda.get(i).getDerivado().getEstoque().getQuantidadeTotalProduto() >= listaEncomenda.get(i).getQtd())
					break;
				else if(listaEncomenda.get(j).getDerivado().getEstoque().getQuantidadeTotalProduto() >= listaEncomenda.get(j).getQtd()) {
					listaEncomenda.add(i,listaEncomenda.remove(j));
					break;
				}
				else if(listaEncomenda.get(i).isDisponivel())
					break;
				else if(listaEncomenda.get(j).isDisponivel()){
					listaEncomenda.add(i,listaEncomenda.remove(j));
					break;
				}
				else if(listaEncomenda.get(i).getPrazoEntrega().before(listaEncomenda.get(j).getPrazoEntrega())) 
					break;
				else
					listaEncomenda.add(i,listaEncomenda.remove(j));
			}
		}
	}


	public void ordemPrazo() {
		boolean vf;
		for(int i=0;i<listaEncomenda.size()-1;i++) {
			for(int j=i+1; j<listaEncomenda.size() ;j++) {
				vf=true;
				if(listaEncomenda.get(i).getPrazoEntrega().before(listaEncomenda.get(j).getPrazoEntrega()))
					vf=false;
				else if(listaEncomenda.get(i).getPrazoEntrega().compareTo(listaEncomenda.get(j).getPrazoEntrega())==0)
					if(listaEncomenda.get(i).getDerivado().getReceita().limiteGeralProducao()>listaEncomenda.get(j).getDerivado().getReceita().limiteGeralProducao())
						vf=false;
				if(vf)
					listaEncomenda.add(i,listaEncomenda.remove(j));
			}
		}
	}

	public String getRelatorioPorPrazo() {
		ordemPrazo();
		return toString();
	}

	public String getRelatorioDisponibilidade(){
		ordemDisponibilidade();
		return toString();
	}

	public String toString() {
		if (listaEncomenda.isEmpty())
			return "Sem Registro!";

		int count=0;
		String strT = "\nEncomenda de Produto:\n";
		strT += String.format("%-8s%-27s%-14s%-12s%-20s%s\n","Ordem", "Produto", "Quantidade", "Prazo", "Cliente", "Registro do usuario");
		Calendar pertoDoPrazo = new GregorianCalendar();
		pertoDoPrazo.add(Calendar.DATE, 15);

		for(Encomenda x:listaEncomenda){
			count++; 
			strT += String.format("%4d -  %s", count, x);
			if(x.getPrazoEntrega().before(Calendar.getInstance()))
				strT+= "\t !FORA DO PRAZO!";
			else if(x.getPrazoEntrega().before(pertoDoPrazo))
				strT+= "\t Prazo menor que 15 dias!";
			if(x.getDerivado().getEstoque().getQuantidadeTotalProduto()>=x.getQtd())
				strT+= "\t Em estoque!";
			if(x.getDerivado().getReceita().limiteGeralProducao()>=x.getQtd())
				strT+= "\t Disponivel para produzir!";
			strT += "\n";
		}
		return strT;
	}
}
