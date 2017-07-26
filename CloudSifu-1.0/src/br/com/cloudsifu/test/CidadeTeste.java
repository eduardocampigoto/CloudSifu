package br.com.cloudsifu.test;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.cloudsifu.objects.Cidade;
public class CidadeTeste {
	
public CidadeTeste(Cidade cidade, String classe, String metodo){
		
		if (cidade == null){
			System.out.println("\n ------------------------------------------------------------");
			System.out.println("\n ------------------ Objeto Cidade nulo! --------------------- ");
			System.out.println("\n ------------------------------------------------------------");
			return;
		}
	}

	private void montaSaida(String saida, String classe, String metodo){
		System.out.println(" \n ------------ CLASSE :  "+classe+" ----- METODO : "+metodo+" ------------\n");
		System.out.println(saida);
		System.out.println(" \n ------------------------------------------------");
		System.out.println(" \n ------------------------------------------------\n\n");
	}

	
	public void mostraCidade(Cidade cidade, String classe, String metodo) {
		String saida = mostraConsole(cidade);
		montaSaida(saida, classe, metodo);
	}

	public String mostraConsole(Cidade cidade) {
		String saida = " \n CIDADE ID : " + cidade.getId()
					  +" \n CIDADE NOME : " + cidade.getNome()
					  +" \n UF ID : "+ cidade.getUnidadeFederativa().getId();
		return saida;
	}
	
	public void mostraCidadeId(Cidade cidade, String classe, String metodo) {
		String saida = mostraConsoleId(cidade);
		montaSaida(saida, classe, metodo);
	}
	
	public String mostraConsoleId(Cidade cidade) {
		String saida = " \n CIDADE ID : " + cidade.getId();
		return saida;
	}
	

	public String mostraCidadeLista(ArrayList<Cidade> listaCidades,
			String classe, String metodo) {
		String saida= "";
		Iterator<Cidade> lista = listaCidades.iterator();
		while (lista.hasNext()) {
			Cidade cidade = new Cidade();
			saida = saida+mostraConsole(cidade);
			saida=saida+"\n -------------------------------------------------------";
		}
		return saida;
	}

	
	
	
	
}
