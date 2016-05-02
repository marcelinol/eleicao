package dominio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

import Eleicao.Eleitor;

public class OpcoesMapper {
	private final String fileName = "Opcoes.urn";
	
	public void persist(int qtdeCargos){
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			ObjectOutputStream objectOutputStrem = new ObjectOutputStream(fileOutputStream);
			objectOutputStrem.writeObject(qtdeCargos);
			objectOutputStrem.flush();
			fileOutputStream.flush();
			objectOutputStrem.close();
			fileOutputStream.close();
			objectOutputStrem = null;
			fileOutputStream = null;
		} catch (FileNotFoundException e) {
			System.out.println("Tem arquivo não sô... " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Desculpe, deu alguma zica. " + e.getMessage());
		}
	}
	
	public int load(){
		FileInputStream file;
		int qtdeCargos = 1;
		try{
			file = new FileInputStream(this.fileName);
			ObjectInputStream object = new ObjectInputStream(file);
			qtdeCargos = (int) object.readObject();
		} catch (FileNotFoundException e){
			System.out.println(e);
			
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return qtdeCargos;
	}

}
