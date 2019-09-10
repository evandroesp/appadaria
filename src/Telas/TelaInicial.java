package Telas;

import Cadastros.Cadastros;
import Outros.Pessoa;

public class TelaInicial extends ControleTelas{
	
	public static void run() {
		login();
		if (userAuth!=null)
			menuPrincipal();
	}

	protected static void openAdmin() {
		userAuth = new Pessoa(999998,"admin2", "admin");
		Cadastros.registroPessoas.addPessoa(userAuth);
		System.out.println(getUser() + "\t pass: " +  userAuth.getSenha());;
	}

	protected static void login() {
		do {
			System.out.println("1 - Login");
			System.out.println("5 - (entrar como admin)");
			System.out.println("8 - (listar usuarios)");
			System.out.println("0 - Sair");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				if (Cadastros.registroPessoas.getArrayPessoa().size()==0) {
					System.out.println("Nao existem pessoas cadastradas! Acessando como ADMIN");
					openAdmin();
				}
				else {
					System.out.println("Digite o codigo do usuario: ");
					userCod = lerNumeroInt();
					System.out.println("Digite a senha: ");
					password = scan.nextLine();
					if(Cadastros.registroPessoas.getPessoaPorCodigo(userCod)!=null) {
						if (Cadastros.registroPessoas.getPessoaPorCodigo(userCod).getSenha().equals(password)) {
							System.out.println("Usuario Logado!");
							userAuth = Cadastros.registroPessoas.getPessoaPorCodigo(userCod);
						}
					}
					else
						System.out.println("Senha errada!");
				}
				break;

			case 5:
				openAdmin();
				break;

			case 8:
				System.out.println("--------------------");
				if (Cadastros.registroPessoas.getArrayPessoa().size()!=0) {
					vf=false;
					for (Pessoa x:Cadastros.registroPessoas.getArrayPessoa()) {
						if (x.getSenha()!=null) {
							vf=true;
							System.out.println(x.getCodigo() + " - " + x.getNome() + "\t - Pass: " + x.getSenha());
						}
					}
				}
				if(!vf)
					System.out.println("Não existem usuários cadastrados");
				break;

			default:
				System.out.println("Opcao Invalida");
			}
			ajustaTela(1);
		}while(op!=0 && userAuth==null);

	}

	

	protected static void menuPrincipal() {
		do {
			cabecalho();
			System.out.println("=== INICIO === ");
			System.out.println("1 - Cadastros");
			System.out.println("2 - Consultas");
			System.out.println("3 - Operacoes");
			System.out.println("5 - Contabilidade");
			System.out.println("9 - Sair!");
			op = lerNumeroInt();
			switch (op) {
			case 1:
				TelasCadastros.menu_1_cadastros();
				break;
			case 2:
				TelasConsultas.menu_2_consultas();
				break;
			case 3:
				TelasOperacoes.menu_3_operacoes();
				break;
			case 5:
				TelasContabilidade.menu_5_contabilidade();
				break;
			}
		}while(op!=9);
	}
}
