package Eleicao;
 
import java.io.Serializable;
 
public abstract class Voto implements Serializable {
	private int numero;
 
	public int getNumero() {
		return numero;
	}
 
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}