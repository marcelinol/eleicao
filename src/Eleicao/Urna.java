package Eleicao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dominio.VotoMapper;

public class Urna implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean foiConfigurada = false;
	private ArrayList<Candidato> candidatos = new ArrayList<Candidato>();
	private ArrayList<Eleitor> eleitores = new ArrayList<Eleitor>();
	private ArrayList<Voto> votos = new ArrayList<Voto>();
	private Map<Candidato,Integer> votosCandidatos = new HashMap<Candidato,Integer>();
	private Map<VotoInvalido,Cargo> votosInvalidos = new HashMap<VotoInvalido,Cargo>();
	private VotoMapper mapeadorVoto;

	public Urna(){
		mapeadorVoto = new VotoMapper();
	}
	
	public void setCandidatos(ArrayList<Candidato> candidatos) {
		for (Candidato candidato : candidatos) {
			addCandidato(candidato);
		}
	}
	
	public void foiConfigurada(boolean c) {
		this.foiConfigurada = c;
	}

	public ArrayList<Candidato> getCandidatos() {
		return candidatos;
	}
	
	public ArrayList<Eleitor> getEleitores(){
		return eleitores;
	}
	
	public Map<Candidato, Integer> getVotosCandidatos() {
		return votosCandidatos;
	}
	
	public Map<VotoInvalido, Cargo> getVotosInvalidos() {
		return votosInvalidos;
	}
	
	public void addCandidato(Candidato candidato){
		candidatos.add(candidato);
		votosCandidatos.put(candidato, 0);
	}
	
	public void addEleitor(Eleitor eleitor){
		eleitores.add(eleitor);
	}
	
	public void computaVoto(int numeroVoto, Cargo cargo){
		
		try{
			VotoValido votoValido = new VotoValido(buscaCandidato(numeroVoto, cargo));
			salvaVoto(votoValido);
		}catch(CandidatoNotFoundException e){
			if (numeroVoto > 0){
				VotoNulo votoNulo = new VotoNulo(99);
				salvaVoto(votoNulo);
			} else {
				VotoBranco votoBranco = new VotoBranco(00);
				salvaVoto(votoBranco);
			}
		}
	}
	
	public Candidato buscaCandidato(int numero, Cargo cargo) throws CandidatoNotFoundException {
	    for (Candidato candidato : candidatos) {
	            if (candidato.getNumero() == numero && candidato.getCargo().equals(cargo) ){
	                    return candidato;
	            }
	    }
	    throw new CandidatoNotFoundException();
	}
	
	public ArrayList<Voto> getVotos(){
		ArrayList<Voto> votos = mapeadorVoto.load();
		return votos;
	}
	
	public void salvaVoto (Voto voto) {
		mapeadorVoto.putVoto(voto);
		mapeadorVoto.persist();
	}

	public int getVotosBrancos() {
		ArrayList<Voto> votos = mapeadorVoto.load();
		int votosBrancos = 0;
		for (Voto voto : votos) {
			if (voto.getNumero()==0){
				votosBrancos++;
			}
		}
		return votosBrancos;
	}
	
	public int getVotosNulos() {
		ArrayList<Voto> votos = mapeadorVoto.load();
		int votosNulos = 0;
		for (Voto voto : votos) {
			if (voto.getNumero()==99){
				votosNulos++;
			}
		}
		return votosNulos;
	}
	
	public int getTotalVotos() {
		ArrayList<Voto> votos = mapeadorVoto.load();
		return votos.size();
	} 
}
