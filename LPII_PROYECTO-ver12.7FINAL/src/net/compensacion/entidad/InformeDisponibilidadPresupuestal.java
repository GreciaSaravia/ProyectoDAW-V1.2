package net.compensacion.entidad;

public class InformeDisponibilidadPresupuestal {
	private int codigo;
	private String fecha;
	private String hora;
	private String objetivo;
	private String alcance;
	private double monto;
	private String id_usuario;
	private int codExp;
	private int codigoSoli;
	private String nombre;
	private String apePaterno;
	private String apeMaterno;
	private String dni;
	private String direccion;
	private String foto;
	private String nom_usuario;
	

	private int cod_es_mag;
	private String nom_es_mag;
	private int cod_jor_labo;
	private String nom_jor_labo;
	private int tst;
	
	public InformeDisponibilidadPresupuestal() {
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getAlcance() {
		return alcance;
	}
	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getCodExp() {
		return codExp;
	}
	public void setCodExp(int codExp) {
		this.codExp = codExp;
	}
	public int getCodigoSoli() {
		return codigoSoli;
	}
	public void setCodigoSoli(int codigoSoli) {
		this.codigoSoli = codigoSoli;
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getNom_usuario() {
		return nom_usuario;
	}
	public void setNom_usuario(String nom_usuario) {
		this.nom_usuario = nom_usuario;
	}
	

	public int getCod_es_mag() {
		return cod_es_mag;
	}


	public void setCod_es_mag(int cod_es_mag) {
		this.cod_es_mag = cod_es_mag;
	}


	public String getNom_es_mag() {
		return nom_es_mag;
	}


	public void setNom_es_mag(String nom_es_mag) {
		this.nom_es_mag = nom_es_mag;
	}


	public int getCod_jor_labo() {
		return cod_jor_labo;
	}


	public void setCod_jor_labo(int cod_jor_labo) {
		this.cod_jor_labo = cod_jor_labo;
	}


	public String getNom_jor_labo() {
		return nom_jor_labo;
	}


	public void setNom_jor_labo(String nom_jor_labo) {
		this.nom_jor_labo = nom_jor_labo;
	}


	public int getTst() {
		return tst;
	}


	public void setTst(int tst) {
		this.tst = tst;
	}
}
