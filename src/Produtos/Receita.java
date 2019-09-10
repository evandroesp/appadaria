package Produtos;

import java.util.ArrayList;
import java.util.List;

public class Receita {

	private List<Ingrediente> listaIngredientes = new ArrayList<Ingrediente>();
	private String preparo;
	private int diasParaVencimento;
	private double rendimento;

	public Receita() {
		preparo = null;
		rendimento=0;
	}

	public Receita(String preparo, double rendimento, int dias) {
		this.preparo = preparo;
		this.rendimento = rendimento;
		setDiasParaVencimento(dias);
	}

	public String getPreparo(){
		return "\n### Preparo: " + preparo;	
	}
	
	public void setPreparo(String prep) {
		preparo=prep;
	}
	
	public double getRendimento(){
		return rendimento;
	}
	
	public void setRendimento(double rend)	{
		rendimento = rend;
	}
	
	public int getDiasParaVencimento() {
		return diasParaVencimento;
	}

	public void setDiasParaVencimento(int diasParaVencimento) {
		this.diasParaVencimento = diasParaVencimento;
	}

	public boolean adicionaIngrediente(Insumo ingr, double qtd) {
		//teste para Insumo não cadastrar 2x, muda a quantidade
		for(Ingrediente x:listaIngredientes)
			if(x.getInsumoIngredi().equals(ingr)) {
				x.setQuantidade(qtd);
				return false;
			}
		//cadastra novo Insumo
		listaIngredientes.add(new Ingrediente(ingr, qtd));
		return true;
	}

	public boolean removeIngrediente(Insumo ingr) {
		for(Ingrediente x:listaIngredientes)
			if(x.getInsumoIngredi().equals(ingr)) {
				listaIngredientes.remove(x);
				return true;
			}
		return false;
	}

	//indice de relatorio
	public boolean removeIngrediente(int indice) {
		try {
			listaIngredientes.remove(indice-1);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//indice de relatorio
	public Ingrediente getIngrediente(int indice) {
		try {
			return listaIngredientes.get(indice-1);
		} catch (Exception e) {
			System.out.println("Erro em selecionar Ingrediente");
			return null;
		}
	}

	
	public void apagarTodosIngredientes() {
		listaIngredientes.clear();
	}
	

	/*SUBSTITUIDO POR produzirReceita
	public double produzirReceitaEAgregarValor(double qtdPorcao){
		double valorFinalProduzido=0;
		for(Ingrediente x:listaIngredientes)
			valorFinalProduzido += x.consumirIngredienteEAgregarValor(qtdPorcao);
		return valorFinalProduzido;
	}*/
	
	public void produzirReceita(double qtdPorcao){
		for(Ingrediente x:listaIngredientes)
			x.consumirIngrediente(qtdPorcao);
	}

	public void ordenarIngrPorLimiteProducao() {
		for(int i=0;i<listaIngredientes.size()-1;i++)
			for(int j=i+1;j<listaIngredientes.size();j++)
				if(listaIngredientes.get(i).getLimiteProducao()>listaIngredientes.get(j).getLimiteProducao())
					listaIngredientes.add(i,listaIngredientes.remove(j));
	}
	
	//public double getCustoProducao() {
	public double getCustoUnitarioProducao() {
		double total=0;
		for(Ingrediente x:listaIngredientes)
			total+=x.getInsumoIngredi().getPrecoAtual()*x.getQuantidade();
		return total/getRendimento();
	}

	public double limiteGeralProducao() {
		ordenarIngrPorLimiteProducao();
		return (Math.floor(listaIngredientes.get(0).getLimiteProducao()));
	}

	public String toString() {
		if(listaIngredientes.isEmpty())
			return "Erro! Lista de Ingredientes vazia!";
		int count=0;
		String str;
		str ="\nLista de Ingredientes:\n\tInsumo\t\t     Quantidade\t\tEstoque\n";
		for(Ingrediente x:listaIngredientes) {
			count++;
			str += count + " - " + x ;
		}
		str += "### Rendimento: " + getRendimento();
		str += getPreparo();
		return str;
	}
}
