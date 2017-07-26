package br.com.cloudsifu.test;

public class JDBCTeste {
	
	public void  mostraQuerie(String comando, String classe, String metodo){
		System.out.println("------------ CLASSE :  "+classe+"----- METODO : "+metodo+" ------------\n");
		System.out.println(comando);
		System.out.println("-----------------------------------------------------------");
		System.out.println("-----------------------------------------------------------\n\n");
		
	}

}
