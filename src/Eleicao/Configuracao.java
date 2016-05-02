package Eleicao;

import java.util.ArrayList;
import java.util.Scanner;

import dominio.CandidatoMapper;
import dominio.EleitorMapper;
import dominio.OpcoesMapper;
import dominio.VotoMapper;

public class Configuracao {
	
	private Urna urna = new Urna();
	private boolean verificaNumeroCandidato = true;
	private Scanner add = new Scanner(System.in);
	private int qtdeCargos = 0;
	private ArrayList<Candidato> candidatos = new ArrayList<>();
	private CandidatoMapper gravaCandidatos = new CandidatoMapper();
	private EleitorMapper gravaEleitores = new EleitorMapper();
	private VotoMapper criaVotosFile = new VotoMapper();
	private OpcoesMapper gravaOpcoes = new OpcoesMapper();
	
	public Configuracao(Urna urna){
		this.urna = urna;
		boolean cadastrando = true;
		try {
		System.out.println("Iniciando Modo de Configuração - Eleição Bagual 2014");
		gravaCandidatos.persist(candidatos);
		candidatos = (ArrayList<Candidato>) gravaCandidatos.getListaCandidatos();
		System.out.println("----------------Eleição Bagual 2014----------------");
		setQtdeCargos();
		
		while (cadastrando){
			System.out.println("Escolha a opção desejada:");
			System.out.println("1. Cadastrar Candidatos");
			System.out.println("2. Cadastrar Eleitores");
			System.out.println("0. Encerra configurações");
			System.out.println("---------------------------------------------------");
			int opcao = add.nextInt();
			switch (opcao) {
			case 1:
				cadastraCandidato();
				break;
				
			case 2:
				cadastraEleitores();
				break;
				
			case 0:
				gravaArquivos(urna);
				System.out.println("Encerrando Modo de Configuração...");
				System.out.println("Urna configurada!");
				cadastrando=false;
				break;

			default:
				System.out.println("Opção inexistente. Tente de novo.");
				break;
			}
		}
		} catch (Exception e){
			System.out.println(e);
		}
	}

	private void setQtdeCargos() {
		System.out.println("Quantos cargos serão cadastrados para esta urna?");
		int teste = add.nextInt();
		if(teste > 0 && teste < 3) {
			qtdeCargos = teste;
		}else {
			System.out.println("Quantidade de cargos fora do escopo.");
			setQtdeCargos();
		}
	}

	private void gravaArquivos(Urna urna) {
		criaVotosFile.persist();
		gravaCandidatos.persist(candidatos);
		gravaOpcoes.persist(qtdeCargos);
		gravaEleitores.persist();
		urna.foiConfigurada(true);
	}

	private void cadastraEleitores() {
		System.out.println("Quantos eleitores essa urna irá receber? Digite a quantidade exata:");
		int qtdeEleitores = add.nextInt();
		if (qtdeEleitores>0){
			try{
				for(int i = 0; i < qtdeEleitores; i++){
					Eleitor eleitor = new Eleitor();
					gravaEleitores.addEleitor(eleitor);
				}
				System.out.println(qtdeEleitores+" eleitores cadastrados com sucesso!");
			} catch (Exception e) {
				System.out.println(e);
			}
		} else { 
			System.out.println("Número de eleitores inválido. Tente novamente.");
			cadastraEleitores();
		}
	}

	private void cadastraCandidato(){
		String nomeCandidato = null;
		int numeroPartido = 0;
		Partido partido = null;
		Cargo cargo = null;
		int numeroCargo;
		
		Scanner entraCand = new Scanner(System.in);
		
		try {
			System.out.println("Digite o nome do candidato: ");
			nomeCandidato = entraCand.nextLine();
			
			System.out.println("Qual o partido deste candidato?");
			System.out.println("1.PT		2.PSDB		3.PSOL		4.PV");
			numeroPartido = (entraCand.nextInt())-1;
			partido = Partido.values()[numeroPartido];
			
			System.out.println("Qual o cargo deste candidato?");
			System.out.println("1. PRESIDENTE 		2. GOVERNADOR");
			numeroCargo = (entraCand.nextInt())-1;
			cargo = Cargo.values()[numeroCargo];
			
			int numeroCandidato = numeroDoCandidato(cargo);
			
			Candidato candidato = new Candidato(nomeCandidato, partido, cargo, numeroCandidato);
			candidatos.add(candidato);
			
		} catch (Exception e) {
			System.out.println(e);
			return;
		}
	}
	
	private int numeroDoCandidato(Cargo cargo) {
		int numeroCandidato = 0;
		int numeroProvisorio = 0;
		verificaNumeroCandidato = true;
		Scanner entraNum = new Scanner(System.in);
		while (verificaNumeroCandidato) {
			System.out.println("Digite o número do candidato: ");
			numeroProvisorio = entraNum.nextInt();
			if (!candidatoRepetido(numeroProvisorio, cargo, candidatos) && numeroProvisorio > 0 && numeroProvisorio < 98) { 
				numeroCandidato = numeroProvisorio;
			} else {
				System.out.println("Esse número de candidato para "+ cargo.getCargo()+" já existe ou é inválido. Por favor, escolha outro.");
			}
		}
		return numeroCandidato;
	}

	private boolean candidatoRepetido(int numero, Cargo cargo, ArrayList<Candidato> candidatos){
		for (Candidato c : candidatos) {
			if (c.getNumero() == numero && c.getCargo() == cargo){
				return true;
			}
		}
		verificaNumeroCandidato = false;
		return false;
	}

	public int getQtdeCargos() {
		return qtdeCargos;
	}
}
