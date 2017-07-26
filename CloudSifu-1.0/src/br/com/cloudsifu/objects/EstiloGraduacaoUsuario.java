package br.com.cloudsifu.objects;

import java.util.Date;


public class EstiloGraduacaoUsuario {

		private int id;
		private Estilo estilo;
		private Graduacao graduacao;
		private Usuario usuario;
		private Date data;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		public Usuario getUsuario(){
			return usuario;
		}
		
		public void setUsuario(Usuario usuario){
			this.usuario = usuario;
		}
		
		public Estilo getEstilo() {
			return estilo;
		}
		public void setEstilo(Estilo estilo) {
			this.estilo = estilo;
		}
		public Graduacao getGraduacao() {
			return graduacao;
		}
		public void setGraduacao(Graduacao graduacao) {
			this.graduacao = graduacao;
		}
		
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}
	
}

