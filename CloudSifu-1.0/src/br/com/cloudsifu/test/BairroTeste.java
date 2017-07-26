package br.com.cloudsifu.test;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.cloudsifu.objects.Bairro;

public class BairroTeste {
	
public BairroTeste(Bairro bairro, String classe, String metodo){
		
		if (bairro == null){
			System.out.println("\n ------------------------------------------------------------");
			System.out.println("\n ---------------- Objeto Bairro nulo! ----------------------- ");
			System.out.println("\n ------------------------------------------------------------");
			return;
		}
	}

	private void mostraSaida(String saida, String classe, String metodo){
		System.out.println("\n ------------ CLASSE :  "+classe+" ----- METODO : "+metodo+" ------------");
		System.out.println(saida);
		System.out.println("\n ------------------------------------------------");
		System.out.println("\n ------------------------------------------------");
	}

	
	public void mostraBairro(Bairro bairro, String classe, String metodo){
		String saida = mostraConsole(bairro);
		mostraSaida(saida, classe, metodo);
	}
	
	public String mostraConsole(Bairro bairro){
		String saida ="\n BAIRRO NOME : "+bairro.getNome()
					  +"\n CIDADE ID : "+bairro.getCidade().getId();
		return saida;
	}
	

	public void mostraBairroId(Bairro bairro, String classe, String metodo){
		String saida = mostraConsoleId(bairro);
		mostraSaida(saida, classe, metodo);	
	}


	public String mostraConsoleId(Bairro bairro){
		String saida = "BAIRRO ID : "+bairro.getId();
		return saida;
	}
	
	public void mostraBairroLista(ArrayList<Bairro> listaBairros, String classe, String metodo) {
		String saida = "";
		Iterator<Bairro> lista = listaBairros.iterator();
		while(lista.hasNext()){
			Bairro mostraBairro = new Bairro();
			saida = saida+mostraConsole(mostraBairro);
			saida=saida+"\n -------------------------------------------------------";
		}
		mostraSaida(saida, classe, metodo);
	}

	
	
}
