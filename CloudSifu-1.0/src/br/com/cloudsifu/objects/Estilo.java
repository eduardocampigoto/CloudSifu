package br.com.cloudsifu.objects;

public class Estilo {

		private int id;
		private String nome;
		private String descricao;
		
		public int getId(){
			return id;
		}
		
		public String getNome(){
			return nome;
		}
		
		public String getDescricao(){
			return descricao;
		}
		
		public void setId(int id){
			this.id = id;
		}
		public void setNome(String nome){
			this.nome = nome;
		}
		
		public void setDescricao(String descricao){
			this.descricao = descricao;
		}
}
