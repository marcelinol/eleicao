package Eleicao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import View.TelaPrincipal;
import dominio.CandidatoMapper;
import dominio.EleitorMapper;
import dominio.OpcoesMapper;
import dominio.VotoMapper;

public class ControladorUrna {
	
	private VotoMapper votoMapper = new VotoMapper();
	private EleitorMapper eleitorMapper = new EleitorMapper();
	private OpcoesMapper opcoesMapper = new OpcoesMapper();
	private CandidatoMapper candidatosMapper = new CandidatoMapper();
	private Urna urna = new Urna();
	private int qtdeCargos = 0;
	private int faltam = 0;
	private Eleicao eleicao = new Eleicao(false);
	
	
	public void iniciaEleicao(Urna urna) {
			File file = new File("/Users/LeoSL/Dropbox/Documents/workspace/Eleicao/Candidatos.urn");
			if (!file.exists()){
				Configuracao config = new Configuracao(urna);
				qtdeCargos = opcoesMapper.load();
				iniciaVotacao(qtdeCargos);
			} else {
				qtdeCargos = opcoesMapper.load();
				iniciaVotacao(qtdeCargos);
			}
	}

	private void iniciaVotacao(int qtdeCargos) {
		ArrayList<Candidato> candidatos = candidatosMapper.load(); 
		urna.setCandidatos(candidatos);
		Cargo cargo = verificaCargo();
		faltam = quantosVotam(qtdeCargos);
		if(faltam > 0){
			TelaPrincipal tela = new TelaPrincipal(urna, cargo, this, faltam);
			tela.iniciaTela();
			System.out.println("Votação iniciada.");
		} else {
			encerraEleicao();
		}
	}
	
	private Cargo verificaCargo() {
		File votosFile = new File("/Users/LeoSL/Dropbox/Documents/workspace/Eleicao/votos.urn");
		Cargo cargo = Cargo.PRESIDENTE;
		if(votosFile.exists()){
			ArrayList<Voto> votos = votoMapper.load();
			if(qtdeCargos > 1 && votos.size() % 2 == 0){
				cargo = Cargo.GOVERNADOR;
			}
		} else {
			if(qtdeCargos > 1) {
				cargo = Cargo.GOVERNADOR;
			}
		}
		return cargo;
	}

	public void encerraEleicao(){
		System.out.println("            ELEIÇÃO");
		eleicao.exibeTotalVotos(urna);
		System.out.println("====");
		eleicao.apuraUrna(urna, Cargo.GOVERNADOR);
	}
	
	public int getQtdeCargos(){
		return this.qtdeCargos;
	}

	private int quantosVotam(int qtdeCargos) {
		File votosFile = new File("/Users/LeoSL/Dropbox/Documents/workspace/Eleicao/votos.urn");
		if (!votosFile.exists()){
			votoMapper.persist();
		}
		ArrayList<Voto> votos = votoMapper.load();
		ArrayList<Eleitor> eleitores = eleitorMapper.load();
		int votosEsperados = (eleitores.size())*qtdeCargos;
		int faltam = votosEsperados-votos.size();
		return faltam;
	}
}
