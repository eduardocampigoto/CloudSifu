package br.com.cloudsifu.objects;

public class DiarioUsuario {

		private int id;
		private DiarioTurma diarioTurma;
		private Usuario usuario;
		private boolean presenca;
		private String observacao;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public DiarioTurma getDiarioTurma() {
			return diarioTurma;
		}
		public void setDiarioTurma(DiarioTurma diarioTurma) {
			this.diarioTurma = diarioTurma;
		}
		public Usuario getUsuario() {
			return usuario;
		}
		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		public boolean getPresenca() {
			return presenca;
		}
		public void setPresenca(boolean presenca) {
			this.presenca = presenca;
		}
		
		public String getObservacao() {
			return observacao;
		}
		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}
		
		
}
