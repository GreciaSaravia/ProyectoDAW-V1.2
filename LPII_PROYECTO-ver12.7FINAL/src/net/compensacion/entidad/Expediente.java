package net.compensacion.entidad;

public class Expediente {
	private int codigoExp;
	private int codigoSoli;
	private String nombre;
	private String apePaterno;
	private String apeMaterno;
	private String dni;
	private String direccion;
	private byte[] foto;
	private int codEscala;
	private int codJornada;
	private int tiempoTotal;
	private String fecha;
	private String hora;
	
	private int codigoArea;
	private String areaExp;

	public int getCodigoExp() {
		return codigoExp;
	}

	public void setCodigoExp(int codigoExp) {
		this.codigoExp = codigoExp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodigoSoli() {
		return codigoSoli;
	}

	public void setCodigoSoli(int codigoSoli) {
		this.codigoSoli = codigoSoli;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public int getCodEscala() {
		return codEscala;
	}

	public void setCodEscala(int codEscala) {
		this.codEscala = codEscala;
	}

	public int getCodJornada() {
		return codJornada;
	}

	public void setCodJornada(int codJornada) {
		this.codJornada = codJornada;
	}

	public int getTiempoTotal() {
		return tiempoTotal;
	}

	public void setTiempoTotal(int tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(int codigoArea) {
		this.codigoArea = codigoArea;
	}

	public String getAreaExp() {
		return areaExp;
	}

	public void setAreaExp(String areaExp) {
		this.areaExp = areaExp;
	}


	
}
