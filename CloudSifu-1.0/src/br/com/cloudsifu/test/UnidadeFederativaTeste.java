package br.com.cloudsifu.test;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.cloudsifu.objects.UnidadeFederativa;

public class UnidadeFederativaTeste {
	
	public UnidadeFederativaTeste(UnidadeFederativa uf, String classe, String metodo){
		
		if (uf == null){
			System.out.println("\n -----------------------------------------------------------");
			System.out.println("\n ------------- Objeto Unidade Federativa nulo! ------------- ");
			System.out.println("\n -----------------------------------------------------------");
			return;
		}
		
	}

	private void montaSaida(String saida, String classe, String metodo){
		System.out.println("------------ CLASSE :  "+classe+" ----- METODO : "+metodo+" ------------\n");
		System.out.println(saida);
		System.out.println("------------------------------------------------");
		System.out.println("------------------------------------------------\n\n");
	}
	
	public void mostraUnidadeFederativa(UnidadeFederativa uf, String classe, String metodo){
		String saida = mostraConsole(uf);
		montaSaida(saida, classe, metodo);
	}

	public String mostraConsole(UnidadeFederativa uf){
		String saida =	"\n UF ID : "+uf.getId()
					   +"\n UF NOME : "+uf.getNome()
					   +"\n UF SIGLA : "+uf.getSigla();
		return saida;
	}

	public void mostraUnidadeFederativaId(UnidadeFederativa uf, String classe, String metodo){
		String saida = mostraConsoleId(uf);
		montaSaida(saida, classe, metodo);
	}


	public String mostraConsoleId(UnidadeFederativa uf){
		String saida = "\n UF ID : "+uf.getId();
		return saida;
	}
	
	public void mostraUnidadeFederativaLista(ArrayList<UnidadeFederativa> listaUnidadeFederativas, String classe, String metodo) {
		String saida = "";
		Iterator<UnidadeFederativa> lista = listaUnidadeFederativas.iterator();
		while(lista.hasNext()){
			UnidadeFederativa uf = new UnidadeFederativa();
			saida = saida+mostraConsole(uf);
			saida=saida+"\n -------------------------------------------------------";
		}
		montaSaida(saida, classe, metodo);
	}
	
	

}
