package net.compensacion.entidad;

import java.util.List;

public class MenuOpciones {
	
	private int codMenu;
	private String nomMenu;
	private String claseIcono;
	private List<Opciones> aOpciones;

	
	public int getCodMenu() {
		return codMenu;
	}

	public void setCodMenu(int codMenu) {
		this.codMenu = codMenu;
	}

	public String getNomMenu() {
		return nomMenu;
	}

	public void setNomMenu(String nomMenu) {
		this.nomMenu = nomMenu;
	}

	public List<Opciones> getaOpciones() {
		return aOpciones;
	}

	public void setaOpciones(List<Opciones> aOpciones) {
		this.aOpciones = aOpciones;
	}

	public String getClaseIcono() {
		return claseIcono;
	}

	public void setClaseIcono(String claseIcono) {
		this.claseIcono = claseIcono;
	}
	
	
	
}
