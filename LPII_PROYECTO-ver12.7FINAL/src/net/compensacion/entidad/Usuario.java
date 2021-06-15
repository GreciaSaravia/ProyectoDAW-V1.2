package net.compensacion.entidad;

public class Usuario {
	private String id;
	private String nom;
	private String apePat;
	private String apeMat;
	private String dni;
	private int codCargo;
	private String cuenta;
	private String contraseña;
	private String dir;
	private int codDist;
	private String tel;
	private String correo;
	private String fecha;
	
	private String hora;
	private byte[] foto;
	private int idPerfil;
	/*Insertados para traer data adicional*/
	private int cod_dep;
	private int cod_prov;
	private int cod_dist;
	private int cod_area;
	private int id_perfil;
	private String nomCargo;
	private String nomDistrito;
	private String nomProvincia;
	private String nomPerfil;
	private String nomArea;
	private String nomDepartamento;
	private String fotoString;
	
	public Usuario() {
		
	}
	
	public int getCod_dep() {
		return cod_dep;
	}


	public void setCod_dep(int cod_dep) {
		this.cod_dep = cod_dep;
	}


	public int getCod_prov() {
		return cod_prov;
	}


	public void setCod_prov(int cod_prov) {
		this.cod_prov = cod_prov;
	}


	public int getCod_dist() {
		return cod_dist;
	}


	public void setCod_dist(int cod_dist) {
		this.cod_dist = cod_dist;
	}


	public int getCod_area() {
		return cod_area;
	}


	public void setCod_area(int cod_area) {
		this.cod_area = cod_area;
	}


	public int getId_perfil() {
		return id_perfil;
	}


	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
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

	public int getCodCargo() {
		return codCargo;
	}

	public void setCodCargo(int codCargo) {
		this.codCargo = codCargo;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getCodDist() {
		return codDist;
	}

	public void setCodDist(int codDist) {
		this.codDist = codDist;
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

	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNomCargo() {
		return nomCargo;
	}

	public void setNomCargo(String nomCargo) {
		this.nomCargo = nomCargo;
	}

	public String getNomDistrito() {
		return nomDistrito;
	}

	public void setNomDistrito(String nomDistrito) {
		this.nomDistrito = nomDistrito;
	}

	public String getNomPerfil() {
		return nomPerfil;
	}

	public void setNomPerfil(String nomPerfil) {
		this.nomPerfil = nomPerfil;
	}


	public String getNomArea() {
		return nomArea;
	}


	public void setNomArea(String nomArea) {
		this.nomArea = nomArea;
	}


	public String getNomDepartamento() {
		return nomDepartamento;
	}


	public void setNomDepartamento(String nomDepartamento) {
		this.nomDepartamento = nomDepartamento;
	}


	public String getNomProvincia() {
		return nomProvincia;
	}


	public void setNomProvincia(String nomProvincia) {
		this.nomProvincia = nomProvincia;
	}

	public String getFotoString() {
		return fotoString;
	}

	public void setFotoString(String fotoString) {
		this.fotoString = fotoString;
	}

}