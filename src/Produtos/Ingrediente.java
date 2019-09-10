package Produtos;

public class Ingrediente {

	private Insumo ingrediente;
	private double quantidade;

	public Ingrediente(Insumo ingr, double qtd) {
		setInsumoIngredi(ingr);
		setQuantidade(qtd);
	}

	/*TROCADO por consumirIngredientes
	public double consumirIngredienteEAgregarValor(double qtdPorcao) {
		int count=0;
		Estoque estConsumido;
		double qtdTotal = qtdPorcao * quantidade;
		double resto = qtdTotal;
		double valorTotalAgregado=0, valorUnitarioAgregado=0;
		while(true){
			if(ingrediente.getEstoque().getQtdPrimeiroEstoque()<resto) {
				count++;
				estConsumido = ingrediente.getEstoque().removeEstoque();
				valorTotalAgregado += estConsumido.getQuantidade() * estConsumido.getValor();

				System.out.print(String.format("Estoque %-2d : Consumindo ", count));
				System.out.print(String.format("%-8.2f (estoque %-8.2f)", estConsumido.getQuantidade(), ingrediente.getEstoque().getQuantidadeTotalProduto()+estConsumido.getQuantidade()));
				System.out.print(String.format(" de %-8.2f %-6s", resto, ingrediente.getUnidade()));
				System.out.println(String.format(" %-20s (R$ %-8.2f)", ingrediente.getCodNome(), ingrediente.getPrecoAtual()*quantidade));
				resto -= estConsumido.getQuantidade();
			}
			else {
				if (resto>=0) {
					count++;
					valorTotalAgregado += resto * ingrediente.getEstoque().getValorPrimeiroEstoque();
					System.out.print(String.format("Estoque %-2d : Consumindo ", count));
					System.out.print(String.format("%-8.2f (estoque %-8.2f)", resto, ingrediente.getEstoque().getQuantidadeTotalProduto()));
					System.out.print(String.format(" de %-8.2f %-6s", resto, ingrediente.getUnidade()));
					System.out.println(String.format(" %-20s (R$ %-8.2f)", ingrediente.getCodNome(), ingrediente.getPrecoAtual()*quantidade));
					ingrediente.getEstoque().setDecremento(resto);
				}
				break;
			}
		}
		
		valorUnitarioAgregado = valorTotalAgregado/qtdTotal;
		//removendo milésimos, arredondando para cima
		valorUnitarioAgregado = Math.ceil(valorUnitarioAgregado*100);
		return (valorUnitarioAgregado/100);
	}
	*/
	
	public void consumirIngrediente(double qtdPorcao) {
		int count=0;
		qtdPorcao *= getQuantidade();
		double precoAtual = getInsumoIngredi().getPrecoAtual();
		Estoque estConsumido;
		while(qtdPorcao>0){
			//quando o estoque é menor que a quantidade necessaria
			if(ingrediente.getEstoque().getQtdPrimeiroEstoque()<qtdPorcao) {
				count++;
				estConsumido = ingrediente.getEstoque().removeEstoque();
				System.out.print(String.format("Estoque %-2d : Consumindo ", count));
				System.out.print(String.format("%-7.1f(estoque %-7.1f)", estConsumido.getQuantidade(), ingrediente.getEstoque().getQuantidadeTotalProduto()+estConsumido.getQuantidade()));
				System.out.print(String.format(" de %-7.1f%-4s", qtdPorcao, ingrediente.getUnidade()));
				System.out.println(String.format(" %-25s (R$ %-8.2f)", ingrediente.getCodNome(), precoAtual*quantidade));
				qtdPorcao -= estConsumido.getQuantidade();
			}
			else {	
				count++;
				System.out.print(String.format("Estoque %-2d : Consumindo ", count));
				System.out.print(String.format("%-7.1f(estoque %-7.1f)", qtdPorcao, ingrediente.getEstoque().getQuantidadeTotalProduto()));
				System.out.print(String.format(" de %-7.1f%-4s", qtdPorcao, ingrediente.getUnidade()));
				System.out.println(String.format(" %-25s (R$ %-8.2f)", ingrediente.getCodNome(), precoAtual*quantidade));
				ingrediente.getEstoque().setDecremento(qtdPorcao);
				qtdPorcao=0;
			}
		}
	}

	public double getLimiteProducao(){
		return (ingrediente.getEstoque().getQuantidadeTotalProduto()/getQuantidade());
	}

	public Insumo getInsumoIngredi() {
		return ingrediente;
	}

	public void setInsumoIngredi(Insumo ingrediente) {
		this.ingrediente = ingrediente;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public String toString() {
		if(getInsumoIngredi().getEstoque().getQuantidadeTotalProduto()<getQuantidade())
			return String.format("%-20s = %6.1f %-8s\t%6.1f !FALTA DE ESTOQUE PARA PRODUZIR!\n",getInsumoIngredi().getCodNome(), getQuantidade(), getInsumoIngredi().getUnidade(), getInsumoIngredi().getEstoque().getQuantidadeTotalProduto());
		return String.format("%-20s = %6.1f %-8s\t%6.1f\n",getInsumoIngredi().getCodNome(), getQuantidade(), getInsumoIngredi().getUnidade(), getInsumoIngredi().getEstoque().getQuantidadeTotalProduto());
	}



}
