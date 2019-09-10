package Telas;

import Cadastros.Cadastros;

public class TelasConsultas extends ControleTelas{

	public static void menu_2_consultas() {
		do {
			cabecalho();
			System.out.println("=== CONSULTAS === ");
			System.out.println("1 - Pessoas");
			System.out.println("2 - Insumos");
			System.out.println("3 - Derivados");
//			System.out.println("5 - Contabilidade");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				menu_21_consultas_pessoa();
				break;
			case 2:
				menu_22_consultas_insumo();
				break;
			case 3:
				menu_23_consultas_derivado();
				break;
			case 5:
//				menu_5_contabilidade();
				break;
			case 9:
				op = -1;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}

		}while(op!=-1 && op!=0);
	}

	protected static void menu_21_consultas_pessoa() {
		do {
			cabecalho();
			System.out.println("=== CONSULTAR PESSOAS por... === ");
			System.out.println("1 - Codigo");
			System.out.println("2 - Nome");
			System.out.println("3 - Registro");
			System.out.println("5 - Listar TODOS");
			System.out.println("6 - Encomenda");
			System.out.println("7 - Fornecedor de insumo (cotacao)");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				encontrarPessoaPorCodigo();
				break;
			case 2:
				encontrarPessoaPorNome();
				break;
			case 3:
				encontrarPessoaPorRegistro();
				break;
			case 5:
				System.out.println(Cadastros.registroPessoas.listarPessoas());
				break;
			case 6:
				System.out.println(Cadastros.registroEncomendas.getRelatorioPorPrazo());
				break;
			case 7:
				System.out.println(Cadastros.registroCotacoes);
				break;
			case 9:
				op = -1;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
			if (0 < op && op < 4 ) {
				if (pessoaT==null)
					System.out.println("Nao foi encontrado! "+ strT);
				else
					System.out.println(pessoaT);
			}
			if(op>0)
				ajustaTela(1);

		}while(op!=-1 && op!=0);
	}

	protected static void menu_22_consultas_insumo() {
		do {
			cabecalho();
			System.out.println("=== CONSULTAR INSUMOS === ");
			System.out.println("1 - Codigo");
			System.out.println("2 - Descricao");
			System.out.println("4 - Pesquisar");
			System.out.println("5 - Listar TODOS");
			System.out.println("7 - Fornecedores...(cotacao)");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
			case 2:
				encontrarInsumo(op);
				if(insumoT!=null){
					System.out.println(insumoT);
					System.out.println(insumoT.getEstoque());
				}
				ajustaTela(1);
				break;
			case 4:
				System.out.println("Digite a descricao: ");
				System.out.println(Cadastros.depositoInsumos.getConsultaInsumoPorDescricao(validarTexto(scan.nextLine(), 2)));
				ajustaTela(1);
				break;
			case 5:
				System.out.println(Cadastros.depositoInsumos.listarInsumos());
				ajustaTela(1);
				break;
			case 7:
				m227_consultas_insumo_fornecedores();
				break;
			case 9:
				op = -2;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}

		}while(op!=-2 && op!=0);
	}

	protected static void menu_23_consultas_derivado(){
		do {
			cabecalho();
			System.out.println("=== CONSULTAR DERIVADOS por... === ");
			System.out.println("1 - Codigo");
			System.out.println("2 - Descricao");
			System.out.println("4 - Pesquisar");
			System.out.println("5 - Listar TODOS");
			System.out.println("7 - Lista de encomendas");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
			case 2:
				encontrarDerivado(op);
				System.out.println(derivadoT);
				System.out.println(derivadoT.getEstoque());
				ajustaTela(1);
				break;
			case 4:
				System.out.println("Digite a descricao: ");
				System.out.println(Cadastros.depositoDerivados.getConsultaDerivadoPorDescricao(validarTexto(scan.nextLine(), 2)));
				ajustaTela(1);
				break;
			case 5:
				System.out.println(Cadastros.depositoDerivados.listarDerivados());
				ajustaTela(1);
				break;
			case 6:
				break;
			case 7:
				System.out.println(Cadastros.registroEncomendas.getRelatorioPorPrazo());
				break;
			case 9:
				op = -2;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}

		}while(op!=-2 && op!=0);
	}

	protected static void m227_consultas_insumo_fornecedores(){
		do {
			cabecalho();
			System.out.println("=== CONSULTAR FORNECEDORES... === ");
			System.out.println("1 - por Insumo");
			System.out.println("2 - por Pessoa");
//			System.out.println("3 - Resumo");
			System.out.println("5 - Todos");
			System.out.println("6 - Todos pelo menor preco");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				encontrarInsumo(1);
				if(insumoT!=null)
					System.out.println(Cadastros.registroCotacoes.getRelatorioInsumoEspecifico(insumoT));
				break;
			case 2:
				encontrarPessoa();
				if(pessoaT!=null)
					System.out.println(Cadastros.registroCotacoes.getRelatorioPorFornecedor(pessoaT));
				break;
//			case 3:
//				System.out.println(Cadastros.registroCotacoes);
//				break;
			case 5:
				System.out.println(Cadastros.registroCotacoes);
				break;
			case 6:
				System.out.println(Cadastros.registroCotacoes.getRelatorioPorPreco());
				break;
			case 9:
				op = -3;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
			if(op!=-3 && op!=0)
				ajustaTela(1);

		}while(op!=-3 && op!=0);
	}
}
