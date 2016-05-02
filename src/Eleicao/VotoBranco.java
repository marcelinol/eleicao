package Eleicao;
 
import java.io.Serializable;
 
import dominio.VotoMapper;
 
public class VotoBranco extends VotoInvalido implements Serializable {
 
	public VotoBranco(int numero) {
		super.setNumero(numero);
	}
 
}