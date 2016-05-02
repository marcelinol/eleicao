package Eleicao;

import java.io.Serializable;

public class Candidato implements Serializable {

	private String nome;
	private Partido partido;
	private Cargo cargo;
	private int numero;
	
	Candidato(String nome, Partido partido, Cargo cargo, int numero){
		this.nome = nome;
		this.partido = partido;
		this.cargo = cargo;
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}
	
	public Partido getPartido(){
		return partido;
	}
	
	public Cargo getCargo(){
		return cargo;
	}
	
	public int getNumero(){
		return numero;
	}
}
