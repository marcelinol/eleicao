package Eleicao;
import java.io.Serializable;
 
import dominio.VotoMapper;

public class VotoValido extends Voto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Candidato candidato;
	
	VotoValido(Candidato candidato){
		this.candidato = candidato;
		super.setNumero(candidato.getNumero());
	}
 
	public Candidato getCandidato() {
		return candidato;
	}
	
}