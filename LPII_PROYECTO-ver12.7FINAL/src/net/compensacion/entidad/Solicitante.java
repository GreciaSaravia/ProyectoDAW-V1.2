package net.compensacion.entidad;

public class Solicitante {

	private String id;
	private String nom;
	private String apePat;
	private String apeMat;
	private String dni;
	private String dir;
	private String tel;
	private String correo;
	private String fecha;
	private String hora;
	private byte[] foto;
	private int codDist;
	private int cod_prov;
	private int cod_dep;
	private String fotoString;
	private String idUsuario;
	/*Insertados para traer data adicional*/
	private String nomDistrito;
	private String nomProvincia;
	private String nomDepartamento;
	
	public Solicitante() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getApePat() {
		return apePat;
	}

	public void setApePat(String apePat) {
		this.apePat = apePat;
	}

	public String getApeMat() {
		return apeMat;
	}

	public void setApeMat(String apeMat) {
		this.apeMat = apeMat;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public int getCodDist() {
		return codDist;
	}

	public void setCodDist(int codDist) {
		this.codDist = codDist;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomDistrito() {
		return nomDistrito;
	}

	public void setNomDistrito(String nomDistrito) {
		this.nomDistrito = nomDistrito;
	}

	public String getNomProvincia() {
		return nomProvincia;
	}

	public void setNomProvincia(String nomProvincia) {
		this.nomProvincia = nomProvincia;
	}

	public String getNomDepartamento() {
		return nomDepartamento;
	}

	public void setNomDepartamento(String nomDepartamento) {
		this.nomDepartamento = nomDepartamento;
	}

	public int getCod_prov() {
		return cod_prov;
	}

	public void setCod_prov(int cod_prov) {
		this.cod_prov = cod_prov;
	}

	public int getCod_dep() {
		return cod_dep;
	}

	public void setCod_dep(int cod_dep) {
		this.cod_dep = cod_dep;
	}

	public String getFotoString() {
		return fotoString;
	}

	public void setFotoString(String fotoString) {
		this.fotoString = fotoString;
	}
	
	
}
