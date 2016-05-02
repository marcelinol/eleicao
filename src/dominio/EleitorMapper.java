package dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import Eleicao.Eleitor;


public class EleitorMapper {
	private final String fileName = "Eleitores.urn";
	private ArrayList<Eleitor> eleitores = new ArrayList<>();
	
	public void addEleitor(Eleitor eleitor){
		this.eleitores.add(eleitor);
	}
	
	public Eleitor getleitor(int numeroEleitor){
		return this.eleitores.get(numeroEleitor);
	}
	
	public Collection getListaeleitores(){
		return this.eleitores;
	}
	
	public void persist(){
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			ObjectOutputStream objectOutputStrem = new ObjectOutputStream(fileOutputStream);
			objectOutputStrem.writeObject(this.eleitores);
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
	
	public ArrayList<Eleitor> load(){
		FileInputStream file;
		try{
			file = new FileInputStream(this.fileName);
			ObjectInputStream object = new ObjectInputStream(file);
			this.eleitores = (ArrayList<Eleitor>) object.readObject();
		} catch (FileNotFoundException e){
			System.out.println(e);
			
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return eleitores;
	}
}
