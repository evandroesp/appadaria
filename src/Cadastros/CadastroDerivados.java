package Cadastros;
import java.util.*;

import Produtos.Derivado;

public class CadastroDerivados{

	private ArrayList<Derivado> listaDerivados;

	public CadastroDerivados(){
		listaDerivados = new ArrayList<Derivado>();
	}

	/*
	public boolean addDerivado(String nome, String unidade, Pessoa usAuth){
		try{
			listaDerivados.add(new Derivado(getNextCodigo(), nome, unidade, usAuth));
			return true;
		} catch (Exception e){
			return false;
		}
	}
	*/

	public boolean addDerivado(Derivado derivadoT){
		try{
			if(derivadoT.getCodigo()==0)
				derivadoT.setCodigo(getNextCodigo());
			listaDerivados.add(derivadoT);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public boolean removeDerivado(int cod){
		for(Derivado x:listaDerivados){
			if(x.getCodigo()==cod){
				listaDerivados.remove(x);
				return true;
			}
		}
		return false;
	}

	public boolean alteraCodigo(Derivado derivadoT,int novoCod){
		for(Derivado x:listaDerivados)
			if(x.getCodigo()==novoCod)
				return false;
		derivadoT.setCodigo(novoCod);
		return true;
	}

	/*
	public Derivado get(int i){
		return listaDerivados.get(i);
	}
	*/

	public Derivado getDerivadoPorCodigo(int cod){
		for(Derivado x:listaDerivados){
			if(x.getCodigo()==cod){
				return x;
			}
		}
		return null;
	}

	public Derivado getDerivadoPorDescricao(String desc){
		for(Derivado x:listaDerivados){
			if(x.getNome().equals(desc)){
				return x;
			}
		}
		return null;
	}

	public int getNextCodigo() {
		ordenaDerivadoPorCodigo();
		int i=0;
		for(i=0;i<listaDerivados.size();i++) {
			if(listaDerivados.get(i).getCodigo()!=(i+1))
				return i+1;
		}
		return i+1;
	}

	public void ordenaDerivadoPorCodigo() {
		Derivado derivadoT;
		for(int i=0;i<listaDerivados.size();i++)
			for(int j=i+1;j<listaDerivados.size();j++) {
				if(listaDerivados.get(i).getCodigo()>listaDerivados.get(j).getCodigo()) {
					derivadoT = listaDerivados.get(i);
					listaDerivados.set(i,listaDerivados.get(j));
					listaDerivados.set(j, derivadoT);
				}
			}
	}
	
	public String getConsultaDerivadoPorDescricao(String desc){
		String str="";
		desc = ".*" + desc.toLowerCase() + ".*";
		for(Derivado x:listaDerivados) {
			if(x.getNome().toLowerCase().matches(desc))
				str += "\n" + x;
		}
		return str;
	}

	public String listarDerivados() {
		String str="-----------------------\nLista de Derivados:";
		for(Derivado x:listaDerivados)
			str+= "\n" + x;
		return str;
	}
	
/*
	public int size(){
		return listaDerivados.size();
	}
*/
	
	//para visualizar o valor no cabecalho da contabilidade
	public double getValorAgregado() {
		double total=0;
		for (Derivado x:listaDerivados)
			total += x.getEstoque().getValorAtual() * x.getEstoque().getQuantidadeTotalProduto();
		return total;
	}

	public ArrayList<Derivado> getArrayDerivados() {
		return listaDerivados;
	}

	/*
	void setArrayDerivados(ArrayList<Derivado> arrayList) {
		listaDerivados = arrayList;
	}
	*/

}