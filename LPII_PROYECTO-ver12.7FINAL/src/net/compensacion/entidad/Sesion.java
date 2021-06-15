package net.compensacion.entidad;

public class Sesion {
	
	private String codUsuario;
	private int codPerfil;
	private String nomUsuario;
	private String apePaterno;
	private String cargo;
	private byte[] foto;
	
	public Sesion() {
		
	}
	
	public Sesion(int codPerfil, String nomUsuario, String apePaterno, String cargo) {
		this.codPerfil = codPerfil;
		this.nomUsuario = nomUsuario;
		this.apePaterno = apePaterno;
		this.cargo = cargo;
	}

	public int getCodPerfil() {
		return codPerfil;
	}

	public void setCodPerfil(int codPerfil) {
		this.codPerfil = codPerfil;
	}

	public String getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	

}
