package Eleicao;
 
import java.io.Serializable;
 
import dominio.VotoMapper;
 
public class VotoNulo extends VotoInvalido implements Serializable {
 
	public VotoNulo(int numero) {
		super.setNumero(numero);
	}
 
}