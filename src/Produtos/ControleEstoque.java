package Produtos;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class ControleEstoque{
	
	private List<Estoque> listaEstoque = new ArrayList<Estoque>();

	public boolean addEstoque(Estoque est){
		try{
			listaEstoque.add(est);;
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	//remove no setDecremento
	public Estoque removeEstoque(){
		if (!listaEstoque.isEmpty())
			return listaEstoque.remove(0);
		return null;
		}
	
	public double getQtdPrimeiroEstoque() {
		if (listaEstoque.isEmpty())
			return 0;
		return listaEstoque.get(0).getQuantidade();
	}
	
	public double getQtdUltimoEstoque() {
		if (listaEstoque.isEmpty())
			return 0;
		return listaEstoque.get(listaEstoque.size()-1).getQuantidade();
	}
	
	public double getValorPrimeiroEstoque() {
		if (listaEstoque.isEmpty())
			return 0;
		return listaEstoque.get(0).getValor();
	}
	
	public double getValorUltimoEstoque() {
		if (listaEstoque.isEmpty())
			return 0;
		return listaEstoque.get(listaEstoque.size()-1).getValor();
	}
	
	public double setDecremento(double qtd) {
		listaEstoque.get(0).setQuantidade(getQtdPrimeiroEstoque()-qtd);
		if(getQtdPrimeiroEstoque()==0)
			removeEstoque();
		return getQtdPrimeiroEstoque();
	}
	
	public double getQuantidadeTotalProduto(){
		if (listaEstoque.isEmpty())
			return 0;
		double total=0;
		for(Estoque x:listaEstoque)
			total+=x.getQuantidade();
		return total;
	}
	/*
	public double getValorMedioTotal() {
		if (listaEstoque.isEmpty())
			return 0;
		double valTotal=0, qtdTotal=0;
		for(Estoque x:listaEstoque) {
			qtdTotal+=x.getQuantidade();
			valTotal+=x.getValor()*x.getQuantidade();
		}
		return valTotal / qtdTotal;
	}
	*/
	//valor sempre atual com amortização
	public double getValorAtual() {
		if (listaEstoque.isEmpty())
			return 0;
		double valTotal=0, valAmortizado=0;
		if (listaEstoque.size()>1) {
			valTotal+= getValorUltimoEstoque()*getQtdUltimoEstoque();
			valAmortizado = getValorUltimoEstoque() - getValorPrimeiroEstoque();
			valAmortizado /= 2;
			valTotal+= (valAmortizado+getValorPrimeiroEstoque()) * getQtdPrimeiroEstoque();
			return valTotal/(getQtdPrimeiroEstoque()+getQtdUltimoEstoque());
		}
		else
			return getValorUltimoEstoque();
	}
	
	public void ordenaEstoquePorValidade() {
		Estoque estTemp;
		for(int i=0;i<listaEstoque.size();i++)
			for(int j=i+1;j<listaEstoque.size();j++)
				if (listaEstoque.get(i).getDataValidade().after(listaEstoque.get(j).getDataValidade())){
					estTemp = listaEstoque.get(j);
					listaEstoque.set(j, listaEstoque.get(i));
					listaEstoque.set(i, estTemp);
				}
	}

	//nao utilizado
	public List<Estoque> removeEstoqueZeroOuVencido(){
		List<Estoque> Lixo = new LinkedList<Estoque>();
		for(int i=0;i<listaEstoque.size();i++){
			if (listaEstoque.get(i).getQuantidade()==0 || listaEstoque.get(i).getDataValidade().before(Calendar.getInstance())){
				Lixo.add(listaEstoque.get(i));
				listaEstoque.remove(i);
				i--;
			}
		}
		return Lixo;
	}
	
/*
	public String proximaValidade(){
		ordenaEstoquePorValidade();
		return(listaEstoque.get(0).strDataValidade());
	}
*/
	
	//public String getEstoqueOrdenado(Produto prodT){
	public String toString(){
		ordenaEstoquePorValidade();
		Calendar pertoDoVencimento = new GregorianCalendar();
		pertoDoVencimento.add(Calendar.DATE, 15);
		//String str = "\nEstoque: " + prodT.getCodNome();
		String str = "\nEstoque: ";
		str += "\nValidade\t- Quantidade\t- Estado\t\t- Preco";
		for(int i=0;i<listaEstoque.size();i++){
			str	+= "\n" + listaEstoque.get(i).getFormDataValidade() + "\t- " + listaEstoque.get(i).getQuantidade();
			if (listaEstoque.get(i).getDataValidade().before(Calendar.getInstance()))
				str += "\t\t- Vencido         ";
			else if (listaEstoque.get(i).getDataValidade().before(pertoDoVencimento))
				if (Calendar.getInstance().get(Calendar.YEAR) == listaEstoque.get(i).getDataValidade().get(Calendar.YEAR)) {
					int dias = listaEstoque.get(i).getDataValidade().get(Calendar.DAY_OF_YEAR);
					dias -= Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
					str += "\t\t- restam " + dias + " dias  ";
				} else
					str += "\t\t- restam " + (365 - Calendar.getInstance().get(Calendar.DAY_OF_YEAR) + listaEstoque.get(i).getDataValidade().get(Calendar.DAY_OF_YEAR)) + " dias";
			else
				str += "\t\t- Bom             ";
			str += "\t- " + listaEstoque.get(i).getValor();
//			str += "\n" + Calendar.getInstance().get(Calendar.DAY_OF_YEAR) + "\n" + listaEstoque.get(i).getDataValidade().get(Calendar.DAY_OF_YEAR);
		}
		return str;		
	}
}
