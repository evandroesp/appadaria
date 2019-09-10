package Telas;

import Cadastros.Cadastros;
import Outros.Pessoa;
import Produtos.Derivado;
import Produtos.Insumo;
import Produtos.Receita;

public class TelasCadastros extends ControleTelas{

	public static void menu_1_cadastros() {
		do {
			cabecalho();
			System.out.println("=== CADASTROS === ");
			System.out.println("1 - Pessoas");
			System.out.println("2 - Insumos");
			System.out.println("3 - Derivados");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				menu_11_cadastros_pessoa();
				break;
			case 2:
				menu_12_cadastros_insumo();
				break;
			case 3:
				menu_13_cadastros_derivado();
				break;
			case 4:
			case 9:
				op = -1;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}

		}while(op!=-1 && op!=0);
	}

	protected static void menu_11_cadastros_pessoa() {
		do {
			cabecalho();
			System.out.println("=== CADASTROS DE PESSOAS === ");
			System.out.println("1 - Novo");
			System.out.println("2 - Editar");
			System.out.println("5 - Listar");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				menu_111_cadastros_pessoa_novo();
				break;
			case 2:
				menu_112_cadastros_pessoa_editar();
				break;
			case 5:
				System.out.println(Cadastros.registroPessoas.listarPessoas());
				ajustaTela(1);
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

	protected static void menu_111_cadastros_pessoa_novo() {
		do {
			cabecalho();
			System.out.println("=== NOVO CADASTRO DE PESSOAS === ");
			System.out.println("1 - Pessoa Fisica");
			System.out.println("2 - Pessoa Juridica");
			System.out.println("5 - Funcionario");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
			case 2:
			case 5:
				m111_adicionarPessoa();
				break;
			case 9:
				op = -3;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}

		}while(op!=-3 && op!=0);
	}

	protected static void menu_112_cadastros_pessoa_editar() {
		encontrarPessoa();
		if (pessoaT!=null){
			do {
				cabecalho();
				System.out.println("=== EDITAR CADASTRO DE PESSOAS === ");
				System.out.println(pessoaT);
				System.out.println("1 - Alterar nome");
				System.out.println("2 - Alterar telefone");
				System.out.println("3 - Alterar endereco e CEP");
				System.out.println("4 - Alterar registro (CPF/CNPJ)");
				System.out.println("6 - Atribuir fornecimento de Insumo");
				System.out.println("7 - Alterar codigo");
				System.out.println("8 - Atribuir acesso com senha");
				System.out.println("777 - Deletar Registro");
				System.out.println("9 - Voltar");
				System.out.println("0 - Inicio");
				op = lerNumeroInt();
				switch (op) {
				case 1:
					m111_m1121_digitePessoaNome();
					break;
				case 2:
					m111_m1122_digitePessoaTelefone();
					break;
				case 3:
					m111_m1123_digitePessoaEnderecoCep();
					break;
				case 4:
					m111_m1124_digitePessoaRegistro();
					break;
				case 6: 
					m1126_m1212_m1226_adicionarCotacao(2);
					break;
				case 7: 
					digitePessoaCodigo();
					break;
				case 8: 
					digitePessoaSenha();
					break;
				case 777: 
					Cadastros.registroCotacoes.removePessoaInexist(pessoaT);
					Cadastros.registroPessoas.removePessoa(pessoaT.getCodigo());
				case 9:
				case 0:
					pessoaT = null;
					break;
				default:
					System.out.println("Opcao Invalida!");
				}

			}while(op!=0 && pessoaT!=null);
		}
	}

	protected static void m111_adicionarPessoa() {
		pessoaT = new Pessoa(userAuth);
		pessoaT.setCodigo(Cadastros.registroPessoas.getNextCodigo());
		m111_m1121_digitePessoaNome();
		if(op == 5)
			pessoaT.setFuncionario(true);
		digitePessoaSenha();
		m111_m1124_digitePessoaRegistro();
		m111_m1122_digitePessoaTelefone();
		m111_m1123_digitePessoaEnderecoCep();
		do{	
			System.out.println();
			System.out.println(pessoaT);
			System.out.println("Digite:\t 1 - Confirmar\t 2 - Cancelar");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				if (Cadastros.registroPessoas.addPessoa(pessoaT))
					System.out.println("Cadastro efetuado!");
				else
					System.out.println("ERRO!");
				break;
			case 2:
				System.out.println("Cancelado.");
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
			ajustaTela(1);
		}while(op!=1 && op!=2);
	}

	

	

	protected static void m111_m1121_digitePessoaNome() {
		do{
			System.out.println("Digite o nome: ");
			strT = scan.nextLine();
		}while(isZero(strT));
		pessoaT.setNome(validarTexto(strT, 2));
	}

	protected static void m111_m1124_digitePessoaRegistro() {
		System.out.print("Digite o CPF/CNPJ da pessoa: ");
		pessoaT.setRegistro(validarIdentidadesUnicas(scan.nextLine(), 1));
		if(op == 1 || op == 5)
			pessoaT.setPessoaJuridica(false);
		else if(op == 2)
			pessoaT.setPessoaJuridica(true);
		else {
			System.out.println("Pessoa Juridica?\n1 - Nao (default)\n2 - Sim");
			op = lerNumeroInt();
			if (op==2)
				pessoaT.setPessoaJuridica(true);
			else
				pessoaT.setPessoaJuridica(false);
		}
	}

	protected static void m111_m1122_digitePessoaTelefone() {
		System.out.println("Digite nº telefone: ");
		strT = scan.nextLine();
		pessoaT.setTelefone(validarTexto(strT, 1));
	}

	protected static void m111_m1123_digitePessoaEnderecoCep() {
		System.out.println("Digite o endereco: ");
		strT = scan.nextLine();
		pessoaT.setEndereco(validarTexto(strT, 2));
		System.out.println("Digite o CEP: ");
		strT = scan.nextLine();
		pessoaT.setCep(validarTexto(strT, 1));
	}

	protected static void digitePessoaSenha() {
		System.out.println("Digite uma senha de acesso: ");
		strT = scan.nextLine();
		pessoaT.setSenha(validarTexto(strT, 2));
	}

	protected static void digitePessoaCodigo() {
		int novoCodigo = digiteNovoCodigo();
		if (novoCodigo!=0) {
			if (Cadastros.registroPessoas.alteraCodigo(pessoaT, novoCodigo)) {
				System.out.println("Codigo alterado!");
			}
			else {
				System.out.println("\nJa existe uma pessoa cadastrada com esse codigo!\n"
						+ novoCodigo + " - " + Cadastros.registroPessoas.getPessoaPorCodigo(novoCodigo).getNome()
						+ "\nDigite:\n777 - Trocar os codigos.\n9 - Cancelar (default)\n0 - Inicio!");
				op = lerNumeroInt();
				if (op==777) {
					Cadastros.registroPessoas.getPessoaPorCodigo(novoCodigo).setCodigo(pessoaT.getCodigo());
					pessoaT.setCodigo(novoCodigo);

				}
				else
					System.out.println("Cancelado!");
			}
		}
		else
			System.out.println("Cancelado!");
	}


	//**********************************************************
	protected static void menu_12_cadastros_insumo() {
		do {
			cabecalho();
			System.out.println("=== CADASTRO DE INSUMOS === ");
			System.out.println("1 - Novo");
			System.out.println("2 - Editar");
			System.out.println("5 - Listar");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				menu_121_cadastros_insumo_novo();
				break;
			case 2:
				menu_122_cadastros_insumo_editar();
				break;
			case 5:
				System.out.println(Cadastros.depositoInsumos.listarInsumos());
				ajustaTela(1);
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

	protected static void menu_121_cadastros_insumo_novo() {
		criarInsumo();
		while(insumoT!=null && op!=0) {
			cabecalho();
			System.out.println(insumoT);
			System.out.println("=== INSUMO NOVO CADASTRO! === ");
			System.out.println("1 - Adicionar Estoque");
			System.out.println("2 - Adicionar Cotacao");
			System.out.println("7 - Alterar Codigo");
			System.out.println("777 - Deletar Registro!");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				adicionarEstoque(insumoT);
				break;
			case 2:
				m1126_m1212_m1226_adicionarCotacao(1);
				break;
			case 7:
				digiteProdutoCodigo(insumoT);
				break;
			case 777:
				removeProduto(insumoT);
			case 9:
			case 0:
				insumoT = null;
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
		}
	}

	protected static void menu_122_cadastros_insumo_editar(){
		encontrarInsumo(1);
		while(insumoT!=null && op!=0) {
			cabecalho();
			System.out.println(insumoT);
			System.out.println("=== EDITAR CADASTRO DE INSUMO === ");
			System.out.println("1 - Alterar nome");
			System.out.println("2 - Alterar unidade");
			System.out.println("3 - Listar insumos");
			System.out.println("5 - Adicionar Estoque");
			System.out.println("6 - Adicionar Cotacao");
			System.out.println("7 - Alterar codigo");
			System.out.println("777 - Deletar Registro!");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				digiteProdutoNome(1);
				if(strT!=null)
					insumoT.setNome(strT);
				break;
			case 2:
				digiteProdutoUnidade(insumoT);
				break;
			case 3:
				System.out.println(Cadastros.depositoInsumos.listarInsumos());
				ajustaTela(1);
				break;
			case 5:
				adicionarEstoque(insumoT);
				break;
			case 6:
				m1126_m1212_m1226_adicionarCotacao(1);
				break;
			case 7:
				digiteProdutoCodigo(insumoT);
				break;
			case 777:
				removeProduto(insumoT);
			case 9:
			case 0:
				insumoT = null;
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
		}
	}

	protected static void criarInsumo() {
		digiteProdutoNome(1);
		if(strT!=null){
			insumoT = new Insumo(strT,userAuth);
			digiteProdutoUnidade(insumoT);
			Cadastros.depositoInsumos.addInsumo(insumoT);
			System.out.println("Registro efetuado com Sucesso");
		}
	}
	
	//**********************************************************
	protected static void menu_13_cadastros_derivado(){
		do {
			cabecalho();
			System.out.println("=== CADASTRO DE DERIVADOS === ");
			System.out.println("1 - Novo");
			System.out.println("2 - Editar");
			System.out.println("5 - Listar");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				menu_131_cadastros_derivado_novo();
				break;
			case 2:
				menu_132_cadastros_derivado_editar();
				break;
			case 5:
				System.out.println(Cadastros.depositoDerivados.listarDerivados());
				ajustaTela(1);
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

	protected static void menu_131_cadastros_derivado_novo() {
		criarDerivado();
		while(derivadoT!=null && op!=0) {
			cabecalho();
			System.out.println(derivadoT);
			System.out.println("=== DERIVADO NOVO CADASTRO! === ");
			System.out.println("1 - Adicionar Estoque");
			System.out.println("2 - Adicionar Receita");
			System.out.println("7 - Alterar Codigo");
			System.out.println("777 - Deletar Registro!");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				adicionarEstoque(derivadoT);
				break;
			case 2:
				adicionarReceita();
				menu_1312_menu_1326_cadastros_derivado_editar_receita();
				break;
			case 7:
				digiteProdutoCodigo(derivadoT);
				break;
			case 777:
				removeProduto(derivadoT);
			case 9:
			case 0:
				derivadoT = null;
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
		}
	}

	protected static void menu_132_cadastros_derivado_editar(){
		encontrarDerivado(1);
		while(derivadoT!=null && op!=0) {
			cabecalho();
			System.out.println(derivadoT);
			System.out.println("=== EDITAR CADASTRO DE DERIVADO === ");
			System.out.println("1 - Alterar nome");
			System.out.println("2 - Alterar unidade");
			System.out.println("3 - Listar derivados");
			System.out.println("5 - Adicionar Estoque");
			System.out.println("6 - Editar Receita");
			System.out.println("7 - Alterar codigo");
			System.out.println("777 - Deletar Registro!");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				digiteProdutoNome(2);
				if(strT!=null)
					derivadoT.setNome(strT);
				break;
			case 2:
				digiteProdutoUnidade(derivadoT);
				break;
			case 3:
				System.out.println(Cadastros.depositoDerivados.listarDerivados());
				ajustaTela(1);
				break;
			case 5:
				adicionarEstoque(derivadoT);
				break;
			case 6:
				menu_1312_menu_1326_cadastros_derivado_editar_receita();
				break;
			case 7:
				digiteProdutoCodigo(derivadoT);
				break;
			case 777:
				removeProduto(derivadoT);
			case 9:
			case 0:
				derivadoT = null;
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
		}
	}

	protected static void criarDerivado() {
		digiteProdutoNome(2);
		if(strT!=null){
			derivadoT = new Derivado(strT,userAuth);
			digiteProdutoUnidade(derivadoT);
			Cadastros.depositoDerivados.addDerivado(derivadoT);
			System.out.println("Registro efetuado com Sucesso");
		}
	}

	protected static void adicionarReceita() {
		receitaT = new Receita();
		digitaReceitaRendimento();
		if(receitaT.getRendimento()!=0) {
			digitaReceitaPreparo();
			derivadoT.setReceita(receitaT);
		}
	}

	protected static void digitaReceitaRendimento() {
		System.out.println("\nDigite o rendimento da receita: (0 - Cancelar)");
		double rendimento = lerNumeroDouble();
		if (rendimento==0)
			System.out.println("Cancelado!");
		else
			receitaT.setRendimento(rendimento);
	}

	protected static void digitaReceitaPreparo() {
		System.out.println("\nDescreva a receita:");
		receitaT.setPreparo(validarTexto(scan.nextLine(), 2));
	}

	protected static void menu_1312_menu_1326_cadastros_derivado_editar_receita() {
		while(receitaT!=null && op!=0) {
			System.out.println(derivadoT);
			System.out.println(receitaT);
			cabecalho();
			System.out.println("=== EDITAR RECEITA DE DERIVADO === ");
			System.out.println("1 - Alterar rendimento");
			System.out.println("2 - Alterar descricao de preparo");
			System.out.println("3 - Adicionar insumo como ingrediente");
			System.out.println("4 - Alterar quantidade do ingrediente");
			System.out.println("5 - Remover ingredientes");
			System.out.println("7 - Cadastrar insumo para ingrediente");
			System.out.println("777 - Remover Todos Ingredientes!");
			System.out.println("9 - Voltar");
			System.out.println("0 - Inicio");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				digitaReceitaRendimento();
				break;
			case 2:
				digitaReceitaPreparo();
				break;
			case 3:
				Cadastros.depositoInsumos.listarInsumos();
				encontrarInsumo(1);
				adicionaIngrediente();
				ajustaTela(1);
				break;
			case 4:
				encontrarInsumoIngrediente();
				adicionaIngrediente();
				break;
			case 5:
				encontrarInsumoIngrediente();
				removerIngrediente();
				break;
			case 7:
				criarInsumo();
				adicionaIngrediente();
				break;
			case 777:
				receitaT.apagarTodosIngredientes();
				break;
			case 9:
				receitaT = null;
			case 0:
				break;
			default:
				System.out.println("Opcao Invalida!");
			}
			ajustaTela(1);
		}
	}

	
	
}
