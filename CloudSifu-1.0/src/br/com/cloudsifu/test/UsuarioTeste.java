package br.com.cloudsifu.test;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.cloudsifu.objects.Usuario;

public class UsuarioTeste {
	
	public UsuarioTeste(Usuario usuario, String classe, String metodo){

		if (usuario == null){
			System.out.println("\n ------------------------------------------------------------");
			System.out.println("\n ------------------- Objeto Usuario nulo! ------------------- ");
			System.out.println("\n ------------------------------------------------------------");
			return;
		}
	
	}
	
	private void mostraSaida(String saida, String classe, String metodo){
		System.out.println(" \n ------------ CLASSE :  "+classe+" ----- METODO : "+metodo+" ------------\n");
		System.out.println(saida);
		System.out.println(" \n ------------------------------------------------------------");
		System.out.println(" \n ------------------------------------------------------------\n\n");
	}
	
	public void mostraUsuario(Usuario usuario, String classe, String metodo) {
		String saida  = mostraConsole(usuario);
		mostraSaida(saida, classe, metodo);
	}

	public void mostraUsuarioLista(ArrayList<Usuario> listaUsuarios,String classe, String metodo) {
		String saida ="";
		Iterator<Usuario> lista = listaUsuarios.iterator();
		while (lista.hasNext()) {
			Usuario usuario = new Usuario();
			saida = saida+mostraConsole(usuario);
			saida=saida+"\n -------------------------------------------------------";
		}
		mostraSaida(saida, classe, metodo);
	}

	public void mostraUsuarioId(Usuario usuario, String classe, String metodo) {
		String saida  = mostraConsole(usuario);
		mostraSaida(saida, classe, metodo);
	}
	
	public String mostraConsoleId(Usuario usuario) {
			String saida = "-----------------USUARIO--------------------" 
			+"\n USUARIO ID : " + usuario.getId();
			return saida;
	}
	
	public String mostraConsole(Usuario usuario) {
		
		String saida = "-----------------USUARIO--------------------" 
		+"\n USUARIO ID : " + usuario.getId()
		+"\n USUARIO NOME : " + usuario.getNome()
		+"\n USUARIO SOBRENOME : " + usuario.getSobrenome()
		+"\n USUARIO GENERO : " + usuario.getGenero()
		+"\n USUARIO NASCIMENTO : " + usuario.getNascimento()
		+"\n USUARIO RG : " + usuario.getRg()
		+"\n USUARIO CPF : " + usuario.getCpf()
		+"\n USUARIO TELEFONE : "+usuario.getTelefone()
		+"\n USUARIO CELULAR : " + usuario.getCelular()
		+"\n USUARIO CONTATO : " + usuario.getContato()
		+"\n USUARIO ENDERECO ID : " + usuario.getEndereco().getId()
		+"\n USUARIO EMAIL : " + usuario.getEmail()
		+"\n USUARIO  TIPO CONTA : " + usuario.getTipoConta()
		+"\n USUARIO  USUARIO : " + usuario.getUsuario()
		+"\n USUARIO  SENHA : " + usuario.getSenha()
		+"\n USUARIO  PROFISSAO : " + usuario.getProfissao()
		+"\n USUARIO  TIPO SANGUE : " + usuario.getTipoSangue()
		+"\n USUARIO  DESCRICAO : " + usuario.getDescricao()
		+ "\n ------------- USUARIO - ENDERECO --------------------"
		+"\n USUARIO ENDERECO ID : "+ usuario.getEndereco().getId()
		+"\n USUARIO ENDERECO CEP : "+ usuario.getEndereco().getCep()
		+"\n USUARIO ENDERECO LOGRADOURO TIPO : "+ usuario.getEndereco().getLogradouro()
		+"\n USUARIO ENDERECO LOGRADOURO : "+ usuario.getEndereco().getNome()
		+"\n USUARIO ENDERECO NUMERO : "+ usuario.getEndereco().getNumero()
		+"\n USUARIO ENDERECO COMPLEMENTO : "+ usuario.getEndereco().getReferencia()
		+"\n USUARIO ENDERECO CEP : "+ usuario.getEndereco().getCep()
		+"\n USUARIO ENDERECO BAIRRO ID : "+ usuario.getEndereco().getBairro().getId()
		+"\n USUARIO ENDERECO BAIRRO NOME : "+ usuario.getEndereco().getBairro().getNome()
		+"\n USUARIO ENDERECO CIDADE ID : "+ usuario.getEndereco().getBairro().getCidade().getId()
		+"\n USUARIO ENDERECO CIDADE NOME : "+ usuario.getEndereco().getBairro().getCidade().getNome()
		+"\n USUARIO ENDERECO UF ID: "+ usuario.getEndereco().getBairro().getCidade().getUnidadeFederativa().getId()
		+"\n USUARIO ENDERECO UF SIGLA : "+ usuario.getEndereco().getBairro().getCidade().getUnidadeFederativa().getSigla()
		+"\n USUARIO ENDERECO UF NOME : "+ usuario.getEndereco().getBairro().getCidade().getUnidadeFederativa().getNome();
		
		if (usuario.getEscola() != null){
			saida = saida+ "\n -------------USUARIO - ESCOLA--------------------"
			+"\n USUARIO ESCOLA ID : "+ usuario.getEscola().getId()
			+"\n USUARIO ESCOLA NOME : "+ usuario.getEscola().getNome()
			+"\n USUARIO ESCOLA RESPONSAVEL : "+ usuario.getEscola().getResponsavel()
			+"\n USUARIO ESCOLA ENDERECO EMAIL : "+ usuario.getEscola().getEmail()
			+"\n USUARIO ESCOLA ENDERECO TELEFONE : "+ usuario.getEscola().getTelefone()
			+"\n USUARIO ESCOLA ENDERECO ID : "+ usuario.getEscola().getEndereco().getId()
			+"\n USUARIO ESCOLA ENDERECO CEP : "+ usuario.getEscola().getEndereco().getCep()
			+"\n USUARIO ESCOLA ENDERECO LOGRADOURO TIPO : "+ usuario.getEscola().getEndereco().getLogradouro()
			+"\n USUARIO ESCOLA ENDERECO LOGRADOURO : "+ usuario.getEscola().getEndereco().getNome()
			+"\n USUARIO ESCOLA ENDERECO NUMERO : "+ usuario.getEscola().getEndereco().getNumero()
			+"\n USUARIO ESCOLA ENDERECO COMPLEMENTO : "+ usuario.getEscola().getEndereco().getReferencia()
			+"\n USUARIO ESCOLA ENDERECO CEP : "+ usuario.getEscola().getEndereco().getCep()
			+"\n USUARIO ESCOLA ENDERECO BAIRRO ID : "+ usuario.getEscola().getEndereco().getBairro().getId()
			+"\n USUARIO ESCOLA ENDERECO BAIRRO NOME : "+ usuario.getEscola().getEndereco().getBairro().getNome()
			+"\n USUARIO ESCOLA ENDERECO CIDADE ID : "+ usuario.getEscola().getEndereco().getBairro().getCidade().getId()
			+"\n USUARIO ESCOLA ENDERECO CIDADE NOME : "+ usuario.getEscola().getEndereco().getBairro().getCidade().getNome()
			+"\n USUARIO ESCOLA ENDERECO UF ID: "+ usuario.getEscola().getEndereco().getBairro().getCidade().getUnidadeFederativa().getId()
			+"\n USUARIO ESCOLA ENDERECO UF SIGLA : "+ usuario.getEscola().getEndereco().getBairro().getCidade().getUnidadeFederativa().getSigla()
			+"\n USUARIO ESCOLA ENDERECO UF NOME : "+ usuario.getEscola().getEndereco().getBairro().getCidade().getUnidadeFederativa().getNome();
		}
		return saida;
	}
	

}
