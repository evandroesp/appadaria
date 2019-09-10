package Telas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import Cadastros.Cadastros;
import Contabilidade.LancamentoCompra;
import Contabilidade.LancamentoVenda;
import Outros.Encomenda;
import Outros.Pessoa;
import Produtos.*;

public class ControleTelas{

	protected static Pessoa userAuth=null;
	protected static Pessoa pessoaT;
	protected static Insumo insumoT;
	protected static Derivado derivadoT;
	protected static Estoque estoqueT;
	protected static Receita receitaT;
	protected static Encomenda encomendaT;
	protected static Calendar dataT;
	protected static int op;
	protected static int userCod;
	protected static String password;
	protected static String strT;
	protected static boolean vf;

	static SimpleDateFormat formData = new SimpleDateFormat("dd/MM/yyyy");

	static Scanner scan = new Scanner(System.in);

	protected static void cabecalho() {
		ajustaTela(0);
		System.out.print(validarTexto("I-R$ " + Cadastros.depositoInsumos.getValorAgregado(), 3)); 
		System.out.print(validarTexto("D-R$ " + Cadastros.depositoDerivados.getValorAgregado(), 3));
		System.out.print(validarTexto("C-R$ " + Cadastros.registroContabil.getDinheiro(), 3));
		System.out.println("Empresa " + validarTexto("R$ "+(Cadastros.depositoInsumos.getValorAgregado()+Cadastros.depositoDerivados.getValorAgregado()+Cadastros.registroContabil.getDinheiro()), 3));
		System.out.println(validarData(Calendar.getInstance()) + "\t " + userAuth.getCodNome());
	}

	//======================================================================
	//======================================================================
	protected static void encontrarPessoa(){
		System.out.println("Encontrar pessoa por:\n1 - Codigo\n2 - Nome\n3 - Registro");
		op = lerNumeroInt();
		switch(op){
		case 1:
			encontrarPessoaPorCodigo();
			break;
		case 2:
			encontrarPessoaPorNome();
			break;
		case 3:
			encontrarPessoaPorRegistro();
			break;
		}
		if (pessoaT==null){
			System.out.println("\nNao foi encontrado pessoa com o codigo "+ strT +"!");
		}
	}

	protected static void encontrarPessoaPorCodigo() {
		System.out.print("\nDigite o codigo da pessoa: ");
		pessoaT = Cadastros.registroPessoas.getPessoaPorCodigo(lerNumeroInt());
	}

	protected static void encontrarPessoaPorNome() {
		System.out.print("\nDigite o nome da pessoa: ");
		strT = scan.nextLine();
		pessoaT = Cadastros.registroPessoas.getPessoaPorNome(strT);
	}

	protected static void encontrarPessoaPorRegistro() {
		System.out.print("\nDigite o registro da pessoa: ");
		strT = scan.nextLine();
		pessoaT = Cadastros.registroPessoas.getPessoaPorRegistro(validarTexto(strT, 1));
	}

	//======================================================================
	//======================================================================

	protected static void encontrarInsumo(int caso) {
		switch(caso){
		case 1:
			System.out.println("\nDigite o codigo do Insumo: ");
			insumoT = Cadastros.depositoInsumos.getInsumoPorCodigo(lerNumeroInt());
			break;
		case 2:
			System.out.println("\nDigite a descricao do Insumo: ");
			insumoT = Cadastros.depositoInsumos.getInsumoPorDescricao(scan.nextLine());
			break;
		}
		if (insumoT==null){
			System.out.println("\nNao foi encontrado Insumo com o codigo "+ strT +"!");
		}
	}

	protected static void encontrarDerivado(int caso) {
		switch(caso){
		case 1:
			System.out.println("\nDigite o codigo do Derivado: ");
			derivadoT = Cadastros.depositoDerivados.getDerivadoPorCodigo(lerNumeroInt());
			break;
		case 2:
			System.out.println("\nDigite a descricao do Derivado: ");
			derivadoT = Cadastros.depositoDerivados.getDerivadoPorDescricao(scan.nextLine());
			break;
		}
		if (derivadoT==null)
			System.out.println("Nao foi encontrado Derivado com o codigo "+ strT +"!");
	}

	protected static void digiteProdutoNome(int caso) {
		System.out.println("Digite o nome do Produto: ");
		if (caso == 1) //Insumos
			strT = validarIdentidadesUnicas(scan.nextLine(), 2);
		if (caso == 2) //Derivados
			strT = validarIdentidadesUnicas(scan.nextLine(), 3);
	}

	protected static void digiteProdutoUnidade(Produto prodT) {
		System.out.println("Digite a unidade de medida: ");
		strT = validarTexto(scan.nextLine(), 2);
		prodT.setUnidade(strT);
	}

	protected static void digiteProdutoCodigo(Produto prodT) {
		int novoCodigo = digiteNovoCodigo();
		int codTemp;
		if (prodT instanceof Insumo) {
			if (novoCodigo!=0) {
				if (Cadastros.depositoInsumos.alteraCodigo(insumoT, novoCodigo)) {
					System.out.println("Codigo alterado!");
				}
				else {
					System.out.println("\nJa existe um insumo cadastrado com esse codigo!\n"
							+ novoCodigo + " - " + Cadastros.depositoInsumos.getInsumoPorCodigo(novoCodigo)
							+ "\nDigite:\n777 - Trocar os codigos.\n9 - Cancelar (default)");
					op = lerNumeroInt();
					if (op==777) {
						codTemp = insumoT.getCodigo();
						insumoT.setCodigo(novoCodigo);
						Cadastros.depositoInsumos.getInsumoPorCodigo(novoCodigo).setCodigo(codTemp);
					}
					else
						System.out.println("Cancelado!");
				}
			}
			else
				System.out.println("Cancelado!");
		}
		else if (prodT instanceof Derivado) {
			if (novoCodigo!=0) {
				if (Cadastros.depositoDerivados.alteraCodigo(derivadoT, novoCodigo)) {
					System.out.println("Codigo alterado!");
				}
				else {
					System.out.println("\nJa existe um derivado cadastrado com esse codigo!\n"
							+ novoCodigo + " - " + Cadastros.depositoDerivados.getDerivadoPorCodigo(novoCodigo)
							+ "\nDigite:\n777 - Trocar os codigos.\n9 - Cancelar (default)");
					op = lerNumeroInt();
					if (op==777) {
						codTemp = derivadoT.getCodigo();
						derivadoT.setCodigo(novoCodigo);
						Cadastros.depositoDerivados.getDerivadoPorCodigo(novoCodigo).setCodigo(codTemp);
					}
					else
						System.out.println("Cancelado!");
				}
			}
			else
				System.out.println("Cancelado!");
		}
	}

	protected static void removeProduto(Produto prodT) {
		if(prodT instanceof Insumo) {
			Cadastros.registroCotacoes.removeInsumoInexist(insumoT);
			Cadastros.depositoInsumos.removeInsumo(prodT.getCodigo());
		}
		else
			Cadastros.registroEncomendas.removeDerivadoInexist(derivadoT);
			Cadastros.depositoDerivados.removeDerivado(prodT.getCodigo());
	}

	protected static void adicionarEstoque(Produto prodT) {
		double qtd, valor;

		System.out.println("Digite a quantidade: ");
		qtd = lerNumeroDouble();
		System.out.println("Digite o valor unitario: ");
		valor = lerNumeroDouble();
		System.out.println("Digite a data de Validade: ");
		validarData(scan.nextLine(), 1);
		if(dataT==null)
			System.out.println("Cancelado!");
		else {
			estoqueT = new Estoque(qtd, valor, dataT, userAuth);
			System.out.println("Digite a data de Entrada: ");
			validarData(scan.nextLine(), 2);
			estoqueT.setDataEntrada(dataT);
			prodT.getEstoque().addEstoque(estoqueT);
		}
	}

	protected static void m1126_m1212_m1226_adicionarCotacao(int caso) {
		if(caso!=1) // Define apenas Insumo na cotacao
			encontrarInsumo(1);
		if(caso!=2) // Define apenas Pessoa na Cotacao
			encontrarPessoa();
		//casos contrarios define ambos

		System.out.println("\nInsumo: " + insumoT.getCodNome());
		System.out.println("Fornecedor: " + pessoaT.getCodNome());
		System.out.println("Digite o preco: ");
		double valor = lerNumeroDouble();
		System.out.println("Digite a data de registro: ");
		System.out.println(validarData(scan.nextLine(), 2));

		System.out.println("\nInsumo: " + insumoT.getCodNome() + "\t - " +insumoT.getUnidade());
		System.out.println("Fornecedor: " + pessoaT.getCodNome());
		System.out.println("Cotacao: R$" + valor + "\t - " + validarData(dataT));

		do {
			System.out.println("Digite:\t1 - Confirmar\t 2 - Cancelar");
			op = lerNumeroInt();
		} while (op!=1 && op!=2);

		if (op == 1) {
			Cadastros.registroCotacoes.addCotacao(insumoT, pessoaT, valor, dataT, userAuth);
			System.out.println("Cotacao Cadastrada");
		}
	}

	protected static void adicionaIngrediente(){
		double qtd;
		System.out.println("Digite a quantidade do ingrediente: (0 - Cancelar)");
		qtd = lerNumeroDouble();
		if(qtd==0)
			System.out.println("Cancelado!");
		else
			receitaT.adicionaIngrediente(insumoT, qtd);
	}

	protected static void encontrarInsumoIngrediente() {
		insumoT=null;
		System.out.println("Digite o indice do ingrediente: (0 - Cancelar)");
		insumoT=receitaT.getIngrediente(lerNumeroInt()).getInsumoIngredi();
	}

	protected static void removerIngrediente() {
		if(insumoT!=null) {
			receitaT.removeIngrediente(insumoT);
			System.out.println("Ingrediente Removido");
		}
	}

	//======================================================================
	//======================================================================


	protected static void produzirDerivado(){
		double qtd;

		if(derivadoT.getReceita().limiteGeralProducao()<=0) {
			System.out.println("\nFalta de Insumos para produzir!");
			System.out.println(derivadoT.getReceita());
		}
		else {
			System.out.println("Digite uma quantidade menor ou igual que " + derivadoT.getReceita().limiteGeralProducao() + " para produzir: ");
			qtd = lerNumeroDouble();

			if (qtd <= derivadoT.getReceita().limiteGeralProducao())
				derivadoT.operarProduzir(qtd,userAuth);
			else
				System.out.println("Erro! quantidade superior a " + derivadoT.getReceita().limiteGeralProducao() + " foi digitada.");
		}
	}

	protected static void m32_venderDerivado() {
		dataT = new GregorianCalendar();
		double qtd;
		System.out.println("Digite uma quantidade menor ou igual a " + derivadoT.getEstoque().getQuantidadeTotalProduto() + " para vender: ");
		qtd = lerNumeroDouble();
		if (qtd > derivadoT.getEstoque().getQuantidadeTotalProduto()) {
			System.out.println("Erro! quantidade superior a " + derivadoT.getEstoque().getQuantidadeTotalProduto() + " foi digitada.");
		}
		else {
			System.out.println("Vender " + qtd + " " + derivadoT.getUnidade() + " de " + derivadoT.getNome() + " para " + pessoaT.getNome()
			+ " por R$ " + (qtd * derivadoT.getPrecoAtual()*Cadastros.taxaLucro) + " (R$ " + derivadoT.getPrecoAtual() * Cadastros.taxaLucro+"un)");
			System.out.println("1 - Confirmar\t\t2 - Cancelar (default)");
			if(lerNumeroInt() == 1) {
				venderDerivado(qtd);
			}
			else
				System.out.println("Cancelado!");
		}
	}
	
	protected static void venderDerivado(double qtd) {
		double precoVenda = derivadoT.getPrecoAtual();
//		double valorTotal = qtd*precoVenda* Cadastros.taxaLucro;
		int count=0;
		Estoque estVendido;
		while(qtd>0){
			if(derivadoT.getEstoque().getQtdPrimeiroEstoque()<=qtd) {
				count++;
				estVendido = derivadoT.getEstoque().removeEstoque();
				System.out.println("Estoque " + count + ": Vendido "  + estVendido.getQuantidade() + " (Estoque " + (derivadoT.getEstoque().getQuantidadeTotalProduto()+estVendido.getQuantidade()) + ") do total de " + qtd + " " + derivadoT.getUnidade() + "(un)\t " + derivadoT.getCodNome());
				qtd -= estVendido.getQuantidade();
			}
			else {
				if (qtd>0) {
					count++;
					System.out.println("Estoque " + count + ": Vendido "  +  qtd + " (estoque " + (derivadoT.getEstoque().getQuantidadeTotalProduto()-qtd) + ") de " + qtd + " " + derivadoT.getUnidade() + "(un)\t " + derivadoT.getCodNome());
					derivadoT.getEstoque().setDecremento(qtd);
				}
				break;
			}
		}
		Cadastros.registroContabil.addLancamento(userAuth, new LancamentoVenda(derivadoT, qtd, precoVenda , pessoaT, userAuth));
	}


	protected static void comprarInsumo() {
		adicionarEstoque(insumoT);
		if (estoqueT==null)
			return;
		if (pessoaT==null)
			pessoaT = userAuth;

		Cadastros.registroContabil.addLancamento(userAuth, new LancamentoCompra(insumoT, estoqueT.getQuantidade(), estoqueT.getValor(), pessoaT, userAuth));

		System.out.println("Foi comprado " + estoqueT.getQuantidade() + " no valor R$ " + (estoqueT.getQuantidade()*estoqueT.getValor()) + " (R$" + estoqueT.getValor() + "un) ");
		if (!pessoaT.equals(userAuth)) {
			System.out.println("de " + pessoaT.getCodNome() + "\nDeseja registrar a cotação?\n777 - Sim \t\t2 - Nao (default)");
			if (lerNumeroInt()==777) {
				Cadastros.registroCotacoes.addCotacao(insumoT, pessoaT, estoqueT.getValor() , estoqueT.getDataEntrada(), userAuth);
				System.out.println("Cotacao cadastrada!");
				estoqueT = null; //limpando referencia
			}
		}
	}

	protected static void registrarEncomenda(){
		encontrarDerivado(1);
		if (derivadoT==null)
			return;
		encontrarPessoa();
		if (pessoaT==null)
			return;

		System.out.println("Digite a quantidade encomendada: ");
		double qtd = lerNumeroDouble();

		System.out.println("Digite o prazo de entrega: ");
		dataT=null;
		validarData(scan.nextLine(), 1);

//		Cadastros.registroEncomendas.addEncomenda(derivadoT, qtd, pessoaT, dataT, userAuth);
		Encomenda encT = new Encomenda(derivadoT, qtd, pessoaT, dataT, userAuth);
		Cadastros.registroEncomendas.addEncomenda(encT);
		
		System.out.println("Encomenda cadastrada com sucesso!");
	}

	protected static void atenderProduzirEncomenda() {
		System.out.println(Cadastros.registroEncomendas.getRelatorioDisponibilidade());
		System.out.println("Digite o indice da encomenda");
		encomendaT = Cadastros.registroEncomendas.getEncomenda(lerNumeroInt());
		if (encomendaT==null)
			System.out.println("Encomenda nao encontrada");
		else { 
			System.out.println(encomendaT);
			if (encomendaT.isDisponivel()) {
				encomendaT.getDerivado();
				System.out.println("Em estoque: " + derivadoT.getEstoque().getQuantidadeTotalProduto() + "\t\t Disponivel para produzir: " + encomendaT.getDerivado().getReceita().limiteGeralProducao());
				encomendaT.getDerivado();
				if (derivadoT.getEstoque().getQuantidadeTotalProduto() >= encomendaT.getQtd())
					System.out.println("777 - Atender");
				else
					System.out.println("1 - Produzir");
				System.out.println("2 - Cancelar (default)");
				derivadoT = encomendaT.getDerivado();
				op = lerNumeroInt();

				//ATENDER ENCOMENDA
				if(op==777) {
					pessoaT = encomendaT.getCliente();
					Cadastros.registroContabil.addLancamento(userAuth, new LancamentoVenda(derivadoT, encomendaT.getQtd(), derivadoT.getPrecoAtual() , pessoaT, userAuth));
					venderDerivado(encomendaT.getQtd());			
					Cadastros.registroEncomendas.removeEncomenda(encomendaT);
				}
				else if (op==1) {
					//opcao 1 desabilitada quando opcao 777 ativada
					if (derivadoT.getEstoque().getQuantidadeTotalProduto() >= encomendaT.getQtd())
						System.out.println("Cancelado!");
					else
						produzirDerivado();
				}
				else
					System.out.println("Cancelado!");
			}
		}
	}

	public static void apagarEncomenda() {
		System.out.println(Cadastros.registroEncomendas.getRelatorioPorPrazo());
		System.out.println("Escolha o indice que dejesa apagar: ");
		encomendaT = Cadastros.registroEncomendas.getEncomenda(lerNumeroInt());
		if(encomendaT==null)
			System.out.println("Nao foi encontrado encomenda com esse indice!");
		else {
			System.out.println(encomendaT + "\n777 - Confirmar\n2 - Cancelar (default)");
			if(lerNumeroInt()==777) {
				if(Cadastros.registroEncomendas.removeEncomenda(encomendaT))
					System.out.println("Encomenda removida!");
				else
					System.out.println("Erro ao remover encomenda!");
			}
		}

	}


	//======================================================================
	//======================================================================

	//======================================================================
	//======================================================================








	protected static int digiteNovoCodigo() {
		System.out.println("Digite o novo codigo: (0 - para cancelar");
		return lerNumeroInt();
	}

	protected static int lerNumeroInt(){
		int numeroTemp;
		strT = scan.nextLine();
		try{
			numeroTemp = Integer.parseInt(strT);
		} catch (Exception e){
			System.out.println("Erro! Digite o numero novamente: ");
			return lerNumeroInt();
		}
		return numeroTemp;
	}

	protected static double lerNumeroDouble(){
		double numeroTemp;
		strT = scan.nextLine();
		try{
			numeroTemp = Double.parseDouble(strT);
		} catch (Exception e){
			System.out.println("Erro! Digite o numero novamente.");
			return lerNumeroDouble();
		}
		return numeroTemp;
	}

	//Validações
	//1 Registro por pessoa
	//1 Nome por produto
	public static String validarIdentidadesUnicas(String str, int caso){
		if(isZero(str))
			return validarIdentidadesUnicas(scan.nextLine(), caso);

		switch (caso) {
		case 1: //Registros de CPF e CNPJ
			str = str.replaceAll("[^0-9]","");
			if(isZero(str))
				return validarIdentidadesUnicas(scan.nextLine(), caso);
			System.out.println(str);
			if(!Cadastros.registroPessoas.getArrayPessoa().isEmpty()) {  
				for(Pessoa x:Cadastros.registroPessoas.getArrayPessoa()){
					if(x.getRegistro().equals(str)){
						System.out.println("\nErro! Ja existe cadastro com esse registro!");
						System.out.println("Deseja:\n1 - Digitar novamente\n2 - Manter codigo " + str + "\n9 - Cancelar");
						switch(lerNumeroInt()){
						case 2:
							return str;
						case 9:
							return null;
						default: //case 1
							System.out.println("\nDigite o registro novamente: ");
							return validarIdentidadesUnicas(scan.nextLine(), caso);
						}
					}
				}
			}
			break;

		case 2: //Nome de Insumos
			str = str.toUpperCase();
			if(!Cadastros.depositoInsumos.getArrayInsumos().isEmpty()) {  
				for(Insumo x:Cadastros.depositoInsumos.getArrayInsumos()){
					if(x.getNome().equals(str)){
						System.out.println("\nErro! Ja existe Insumo registrado com esse nome!");
						System.out.println("Deseja:\n1 - Digitar novamente\n2 - Manter o nome " + str + "\n9 - Cancelar");
						op = lerNumeroInt();
						switch(op){
						case 2:
							return str;
						case 9:
							return null;
						default: //case 1
							System.out.println("\nDigite novamente o nome do Insumo: ");
							return validarIdentidadesUnicas(scan.nextLine(), caso);
						}
					}
				}
			}
			break;

		case 3: //Nome de Derivados
			str = str.toUpperCase();
			if(!Cadastros.depositoDerivados.getArrayDerivados().isEmpty()) {   
				for(Derivado x:Cadastros.depositoDerivados.getArrayDerivados()){
					if(x.getNome().equals(str)){
						System.out.println("\nErro! Ja existe Derivado registrado com esse nome!");
						System.out.println("Deseja:\n1 - Digitar novamente\n2 - Manter o nome " + str + "\n9 - Cancelar");
						op = lerNumeroInt();
						switch(op){
						case 2:
							return str;
						case 9:
							return null;
						default: //case 1
							System.out.println("\nDigite novamente o nome do Derivado: ");
							return validarIdentidadesUnicas(scan.nextLine(), caso);
						}
					}
				}
			}
		}
		if(str.isEmpty()) {
			System.out.println("\nErro! Texto em branco ou incompativel. Digite novamente: ");
			return validarIdentidadesUnicas(scan.nextLine(), caso);
		}
		return str;
	}

	public static String validarTexto(String strT, int caso) {
		if(isZero(strT))
			return validarTexto(scan.nextLine(),caso);
		switch (caso) {
		case 1:	//apenas numeros
			strT = strT.replaceAll("[^0-9]","");
			if(isZero(strT))
				return validarTexto(scan.nextLine(),caso);
			else if (strT.length()<1){
				System.out.println("\nErro! Digite os numeros novamente: ");
				return validarTexto(scan.nextLine(),1);
			}
			break;
		case 2:	//texto nao vazio
			if (strT.isEmpty()){
				System.out.println("\nErro!\nDigite novamente: ");
				strT = scan.nextLine();
				return validarTexto(strT,2);
			}		
			break;
		case 3:	//texto dinheiro
			while(strT.length()<20)
				strT = strT.concat(" ");
		}
		return strT;
	}

	public static String validarData(Calendar data){
		return formData.format(data.getTime());
	}

	public static String validarData(String data, int caso){
		dataT= new GregorianCalendar();
		try {
			dataT.setTime(formData.parse(data));
		} catch (ParseException e) {
			System.out.println("Erro data invalida! ");
			switch (caso) {
			case 1: //obrigatorio
				System.out.println("(digite: dd/mm/aaaa ou 0 para sair)");
				data = scan.nextLine();
				if (data.equals("0")) {
					dataT=null;
					return null;
				}
				return validarData(data, 1);
			case 2: //opcional para entrada
				System.out.println("Inserido data atual.");
				return formData.format(dataT.getTime());
			}
		}
		return formData.format(dataT.getTime());
	}

	public static String getUser() {
		return userAuth.getCodNome();
	}

	public static boolean isZero(String str){
		if (str.equals("0")){
			System.out.println("Erro! Campo nao pode ser 0 (zero). Digite novamente!");
			return true;
		}
		return false;
	}

	public static void ajustaTela(int caso) {
		System.out.println();
		if(caso == 1) { //pause
			System.out.println("Aperte ENTER para Continuar");
			scan.nextLine();
		}
		else
			for (int i=0;i<10;i++)
				System.out.println();
	}
}
