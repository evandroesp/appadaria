package Cadastros;
import java.util.*;

import Produtos.Insumo;

public class CadastroInsumos{
    
    private ArrayList<Insumo> listaInsumos;
    
    public CadastroInsumos(){
        listaInsumos = new ArrayList<Insumo>();
    }
    
    /*
    public boolean addInsumo(String nome, String unidade, Pessoa usAuth){
    	try{
    		listaInsumos.add(new Insumo(getNextCodigo(), nome, unidade, usAuth));
    		return true;
    	} catch (Exception e){
    		return false;
    	}
    }
    */
    
    public boolean addInsumo(Insumo insumoT){
    	try{
    		if(insumoT.getCodigo()==0)
    			insumoT.setCodigo(getNextCodigo());
    		listaInsumos.add(insumoT);
    		return true;
    	} catch (Exception e){
    		return false;
    	}
    }
    
    public boolean removeInsumo(int cod){
        for(Insumo x:listaInsumos){
            if(x.getCodigo()==cod){
                listaInsumos.remove(x);
                return true;
            }
        }
        return false;
    }
    
    public boolean alteraCodigo(Insumo insumoT,int novoCod){
        for(Insumo x:listaInsumos)
            if(x.getCodigo()==novoCod)
                return false;
        insumoT.setCodigo(novoCod);
        return true;
    }
    
    public Insumo getInsumoPorCodigo(int cod){
        for(Insumo x:listaInsumos){
            if(x.getCodigo()==cod){
                return x;
            }
        }
        return null;
    }
    
    public Insumo getInsumoPorDescricao(String desc){
        for(Insumo x:listaInsumos){
            if(x.getNome().equals(desc)){
                return x;
            }
        }
        return null;
    }
    
    public int getNextCodigo() {
    	ordenaInsumosPorCodigo();
    	int i=0;
    	for(i=0;i<listaInsumos.size();i++) {
    		if(listaInsumos.get(i).getCodigo()!=(i+1))
    			return i+1;
    	}
    	return i+1;
    }
    
    public void ordenaInsumosPorCodigo() {
    	Insumo insumoT;
		for(int i=0;i<listaInsumos.size();i++)
			for(int j=i+1;j<listaInsumos.size();j++) {
				if(listaInsumos.get(i).getCodigo()>listaInsumos.get(j).getCodigo()) {
					insumoT = listaInsumos.get(i);
					listaInsumos.set(i,listaInsumos.get(j));
					listaInsumos.set(j, insumoT);
				}
			}
	}
    
    public String getConsultaInsumoPorDescricao(String desc){
		String strT="";
		desc = ".*" +desc.toLowerCase()+".*";
		for(Insumo x:listaInsumos)
			if(x.getNome().toLowerCase().matches(desc))
				strT += x;
		return strT;
	}
    
    public String listarInsumos() {
    	String str="-----------------------\nLista de Insumos:";
    	for(Insumo x:listaInsumos)
    		str+= "\n" + x;
    	return str;
    }
    
    //visualizar o valor investido no cabecalho contabil
    public double getValorAgregado() {
		double total=0;
		for (Insumo x:listaInsumos)
			total += x.getEstoque().getValorAtual() * x.getEstoque().getQuantidadeTotalProduto();
		return total;
	}
    
    /*
    public int size(){
        return listaInsumos.size();
    }
    */

    public ArrayList<Insumo> getArrayInsumos() {
        return listaInsumos;
    }

    /*
    void setArrayInsumos(ArrayList<Insumo> arrayList) {
        listaInsumos = arrayList;
    }
    */

}