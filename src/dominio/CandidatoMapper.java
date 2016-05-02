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

import Eleicao.Candidato;

public class CandidatoMapper {
	private final String fileName = "Candidatos.urn";
	private ArrayList<Candidato> candidatos = new ArrayList<>();
	
	public Candidato getCandidato(int numeroCandidato){
		return this.candidatos.get(numeroCandidato);
	}
	
	public Collection getListaCandidatos(){
		load();
		return this.candidatos;
	}
	
	public void persist(ArrayList<Candidato> candidatos){
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			ObjectOutputStream objectOutputStrem = new ObjectOutputStream(fileOutputStream);
			objectOutputStrem.writeObject(candidatos);
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
	
	public ArrayList<Candidato> load(){
		FileInputStream file;
		try{
			file = new FileInputStream(this.fileName);
			ObjectInputStream object = new ObjectInputStream(file);
			this.candidatos = (ArrayList<Candidato>) object.readObject();
			
		} catch (FileNotFoundException e){
			System.out.println(e);
			
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return candidatos;
	}
}
