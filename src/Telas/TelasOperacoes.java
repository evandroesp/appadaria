package Telas;

import Cadastros.Cadastros;

public class TelasOperacoes extends ControleTelas{
	
	public static void menu_3_operacoes() {
		do {
			insumoT = null;
			derivadoT = null;
			pessoaT = null;
			cabecalho();
			System.out.println("=== OPERADOR === ");
			System.out.println("1 - Produzir Derivado");
			System.out.println("2 - Vender Derivado");
			System.out.println("3 - Comprar Insumo");
			System.out.println("5 - Limpar Estoques");
			System.out.println("6 - Encomenda...");
			//			System.out.println("7 - Lucro total");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				System.out.println(Cadastros.depositoDerivados.listarDerivados());
				encontrarDerivado(op);
				if (derivadoT!=null)
					produzirDerivado();
				break;
			case 2:
				System.out.println(Cadastros.depositoDerivados.listarDerivados());
				encontrarDerivado(1);
				if (derivadoT==null) 
					break;
				if(derivadoT.getEstoque().getQuantidadeTotalProduto()<=0) {
					System.out.println("\nFalta de Estoque para Vender!");
					System.out.println(derivadoT);
				}
				else {
					encontrarPessoa();
					if (pessoaT==null) //venda avulsa
						pessoaT=Cadastros.registroPessoas.getPessoaPorNome("admin");
					m32_venderDerivado();
				}
				break;
			case 3:
				encontrarInsumo(1);
				if (insumoT==null) 
					break;
				encontrarPessoa();
				comprarInsumo();
				break;
			case 5:
				System.out.println(Cadastros.depositoDerivados.listarDerivados());
				ajustaTela(1);
				break;
			case 6:
				m36_encomendas();
				break;
			case 7:
				System.out.println(Cadastros.registroEncomendas.getRelatorioPorPrazo());
				break;
			case 9:
				op = -1;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
			if(op>0)
				ajustaTela(1);

		}while(op!=-1 && op!=0);
	}

	protected static void m36_encomendas(){
		do {
			cabecalho();
			System.out.println("=== OPERADOR ENCOMENDAS === ");
			System.out.println("1 - Registrar nova Encomenda");
			System.out.println("2 - Atender ou Produzir");
			System.out.println("5 - Listar Encomendas por Disponibilidade");
			System.out.println("6 - Listar Encomendas por Prazo");
			System.out.println("7 - Apagar Encomenda");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				registrarEncomenda();
				break;
			case 2:
				atenderProduzirEncomenda();
				break;
			case 5:
				System.out.println(Cadastros.registroEncomendas.getRelatorioDisponibilidade());
				break;
			case 6:
				System.out.println(Cadastros.registroEncomendas.getRelatorioPorPrazo());
				break;
			case 7:
				apagarEncomenda();
				break;
			case 9:
				op = -1;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
			if(op>0)
				ajustaTela(1);

		}while(op!=-1 && op!=0);
	}

}
