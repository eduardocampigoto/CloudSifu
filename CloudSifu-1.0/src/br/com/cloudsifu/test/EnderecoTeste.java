package br.com.cloudsifu.test;

import java.util.ArrayList;
import java.util.Iterator;
import br.com.cloudsifu.objects.Endereco;

public class EnderecoTeste {

public EnderecoTeste(Endereco endereco, String classe, String metodo){
		
		if (endereco == null || endereco.getBairro() == null || endereco.getBairro().getCidade() == null || 
				endereco.getBairro().getCidade().getUnidadeFederativa() == null){
			System.out.println("\n -------------------------------------------------------------");
			System.out.println("\n ----------- Objeto Endereco nulo ou incompleto! ------------- ");
			System.out.println("\n -------------------------------------------------------------");
			return;
		}
	}

	private void mostraSaida(String saida, String classe, String metodo){
		System.out.println(" \n ------------ CLASSE :  "+classe+" ----- METODO : "+metodo+" ------------\n");
		System.out.println(saida);
		System.out.println(" \n ------------------------------------------------");
		System.out.println(" \n ------------------------------------------------\n\n");
	}
	
	public void mostraEndereco(Endereco endereco, String classe, String metodo){
		String saida = mostraConsole(endereco);
		mostraSaida(saida, classe, metodo);
		
	}
	
	public void mostraEnderecoId(Endereco endereco, String classe, String metodo){
		String saida = mostraConsoleId(endereco);
		mostraSaida(saida, classe, metodo);
		
	}
	
	public String mostraConsoleId(Endereco endereco){
		String saida = "\n ENDERECO ID : "+endereco.getId();
		return saida;
		
	}

	public void mostraEnderecoLista(ArrayList<Endereco> listaEnderecos, String classe, String metodo) {
		String saida="";
		Iterator<Endereco> lista = listaEnderecos.iterator();
		while(lista.hasNext()){
			Endereco endereco = new Endereco();
			saida= saida+mostraConsole(endereco);
			saida=saida+"\n -------------------------------------------------------";
		}
		mostraSaida(saida, classe, metodo);
	}


	public String mostraConsole(Endereco endereco){
		String saida = "\n ENDERECO ID : "+endereco.getId()
		+"\n ENDERECO CEP : "+endereco.getCep()
		+"\n ENDERECO LOGRADOURO TIPO : "+endereco.getLogradouro()
		+"\n ENDERECO LOGRADOURO : "+endereco.getNome()
		+"\n ENDERECO NUMERO : "+endereco.getNumero()
		+"\n ENDERECO COMPLEMENTO : "+endereco.getReferencia()
		+"\n ENDERECO CEP : "+endereco.getCep()
		+"\n BAIRRO ID : "+endereco.getBairro().getId()
		+"\n BAIRRO NOME : "+endereco.getBairro().getNome()
		+"\n CIDADE ID : "+endereco.getBairro().getCidade().getId()
		+"\n CIDADE NOME : "+ endereco.getBairro().getCidade().getNome()		
		+"\n UF ID: "+endereco.getBairro().getCidade().getUnidadeFederativa().getId()
		+"\n UF SIGLA : "+endereco.getBairro().getCidade().getUnidadeFederativa().getSigla()
		+"\n UF NOME : "+endereco.getBairro().getCidade().getUnidadeFederativa().getNome();
		return saida;
		
	}
	
	
}
