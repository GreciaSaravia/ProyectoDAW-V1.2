package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import net.compensacion.entidad.Usuario;
import net.compensacion.interfaces.UsuarioDAO;
import net.compensacion.utils.MySqlBDConexion;


public class MySqlUsuarioDAO implements UsuarioDAO {

	@Override
	public int addUsuario(Usuario bean) {
		
		int salida=-1;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="INSERT INTO tbusuario VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, bean.getId());
			pstm.setString(2, bean.getNom());
			pstm.setString(3, bean.getApePat());
			pstm.setString(4, bean.getApeMat());
			pstm.setString(5, bean.getDni());
			pstm.setInt(6, bean.getCodCargo());
			pstm.setString(7, bean.getCuenta());
			pstm.setString(8, bean.getContraseña());
			pstm.setString(9, bean.getDir());
			pstm.setInt(10, bean.getCodDist());
			pstm.setString(11, bean.getTel());
			pstm.setString(12, bean.getCorreo());
			pstm.setString(13, bean.getFecha());
			pstm.setString(14, bean.getHora());
			pstm.setBytes(15, bean.getFoto());
			pstm.setInt(16, bean.getIdPerfil());
			
			salida=pstm.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
		
	}

	@Override
	public int returnCodigo() {
		
		int codigo = -1;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null; 
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select substring_index(id_usuario,'U',-1) + 1 as numero from tbusuario order by  id_usuario desc limit 1";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				codigo=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return codigo;
	}
	
	@Override
	public int ReturnCodigoCargo(String cargo) {
		
		int codigo=-1;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null; 
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select cod_cargo from tbcargo where nom_cargo=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, cargo);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				codigo=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return codigo;
	}

	@Override
	public int ReturnCodigoPerfil(String perfil) {
		
		int codigo=-1;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null; 
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select id_perfil from tbperfil where desc_perfil=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, perfil);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				codigo=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return codigo;
	}

	@Override
	public int ReturnCodigoDistrito(int distrito, int provincia) {
		
		int codigo=-1;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null; 
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select d.cod_dist from tbdistrito d inner join tbprovincia pr on d.cod_prov=pr.cod_prov where d.cod_dist=? and pr.cod_prov=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, distrito);
			pstm.setInt(2, provincia);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				codigo=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return codigo;
	}

	@Override
	public List<Usuario> ListAllUsuarios() {
		List<Usuario> lista = new ArrayList<Usuario>();
		Usuario bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select u.id_usuario,u.nom_usuario,u.apePat_usuario,u.apeMat_usuario,u.dni_usuario,c.nom_cargo,u.cuenta_usuario,u.contraseÃ±a_usuario,u.dir_usuario, " +
					   "d.distrito,u.tel_usuario,u.correo_usuario,DATE_FORMAT(u.fecha_usuario, '%d/%m/%Y'),u.hora_usuario,u.foto_usuario,p.desc_perfil from tbusuario u inner join tbcargo c " +
					   "on u.cod_cargo=c.cod_cargo inner join tbdistrito d " +
					   "on u.cod_dist=d.cod_dist inner join tbperfil p "+
					   "on u.id_perfil=p.id_perfil "+
					   "order by id_usuario asc";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Usuario();

				bean.setId(rs.getString(1));
				bean.setNom(rs.getString(2));
				bean.setApePat(rs.getString(3));
				bean.setApeMat(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setNomCargo(rs.getString(6));
				bean.setCuenta(rs.getString(7));
				bean.setContraseña(rs.getString(8));
				bean.setDir(rs.getString(9));
				bean.setNomDistrito(rs.getString(10));
				bean.setTel(rs.getString(11));
				bean.setCorreo(rs.getString(12));
				bean.setFecha(rs.getString(13));
				bean.setHora(rs.getString(14));
				bean.setFoto(rs.getBytes(15));
				bean.setNomPerfil(rs.getString(16));
				
				lista.add(bean);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null)pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return lista;
	}

	@Override
	public Usuario firstUsuarios() {
		
		Usuario bean = null;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select u.id_usuario,u.nom_usuario,u.apePat_usuario,u.apeMat_usuario,u.dni_usuario,a.nom_area,u.cuenta_usuario,u.contraseÃ±a_usuario, "+
					   "u.dir_usuario,d.distrito,pr.provincia,dp.departamento,u.tel_usuario,u.correo_usuario,DATE_FORMAT(u.fecha_usuario, '%d/%m/%Y'),u.hora_usuario,u.foto_usuario,p.desc_perfil from tbusuario u inner join tbcargo c "+
					   "on u.cod_cargo=c.cod_cargo inner join tbarea a "+
					   "on c.cod_area=a.cod_area  inner join tbdistrito d "+
					   "on u.cod_dist=d.cod_dist  inner join tbprovincia pr "+
					   "on d.cod_prov=pr.cod_prov inner join tbdepartamento dp "+
					   "on pr.cod_dep=dp.cod_dep inner join tbperfil p "+
					   "on u.id_perfil=p.id_perfil "+
					   "order by id_usuario asc limit 1";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				
				bean=new Usuario();
	
				bean.setId(rs.getString(1));
				bean.setNom(rs.getString(2));
				bean.setApePat(rs.getString(3));
				bean.setApeMat(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setNomArea(rs.getString(6));
				bean.setCuenta(rs.getString(7));
				bean.setContraseña(rs.getString(8));
				bean.setDir(rs.getString(9));
				bean.setNomDistrito(rs.getString(10));
				bean.setNomProvincia(rs.getString(11));
				bean.setNomDepartamento(rs.getString(12));
				bean.setTel(rs.getString(13));
				bean.setCorreo(rs.getString(14));
				bean.setFecha(rs.getString(15));
				bean.setHora(rs.getString(16));
				bean.setFoto(rs.getBytes(17));
				bean.setNomPerfil(rs.getString(18));		
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null)pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return bean;
	}

	@Override
	public Usuario UsuariosByCodigo(String codigo) {
		
		Usuario bean = null;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select u.id_usuario,u.nom_usuario,u.apePat_usuario,u.apeMat_usuario,u.dni_usuario,a.nom_area,u.cuenta_usuario,u.contraseÃ±a_usuario, "+
					   "u.dir_usuario,d.distrito,pr.provincia,dp.departamento,u.tel_usuario,u.correo_usuario,DATE_FORMAT(u.fecha_usuario, '%d/%m/%Y'),u.hora_usuario,u.foto_usuario,p.desc_perfil, dp.cod_dep, pr.cod_prov,d.cod_dist,a.cod_area,p.id_perfil from tbusuario u inner join tbcargo c "+
					   "on u.cod_cargo=c.cod_cargo inner join tbarea a "+
					   "on c.cod_area=a.cod_area  inner join tbdistrito d "+
					   "on u.cod_dist=d.cod_dist  inner join tbprovincia pr "+
					   "on d.cod_prov=pr.cod_prov inner join tbdepartamento dp "+
					   "on pr.cod_dep=dp.cod_dep inner join tbperfil p "+
					   "on u.id_perfil=p.id_perfil "+
					   "where id_usuario=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, codigo);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				
				bean=new Usuario();
	
				bean.setId(rs.getString(1));
				bean.setNom(rs.getString(2));
				bean.setApePat(rs.getString(3));
				bean.setApeMat(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setNomArea(rs.getString(6));
				bean.setCuenta(rs.getString(7));
				bean.setContraseña(rs.getString(8));
				bean.setDir(rs.getString(9));
				bean.setNomDistrito(rs.getString(10));
				bean.setNomProvincia(rs.getString(11));
				bean.setNomDepartamento(rs.getString(12));
				bean.setTel(rs.getString(13));
				bean.setCorreo(rs.getString(14));
				bean.setFecha(rs.getString(15));
				bean.setHora(rs.getString(16));
				bean.setFoto(rs.getBytes(17));
				bean.setNomPerfil(rs.getString(18));
				bean.setCod_dep(rs.getInt(19));
				bean.setCod_prov(rs.getInt(20));
				bean.setCod_dist(rs.getInt(21));
				bean.setCod_area(rs.getInt(22));
				bean.setId_perfil(rs.getInt(23));	
				byte [] imagen=rs.getBytes(17);
				String fo=Base64.getEncoder().encodeToString(imagen);
				bean.setFotoString(fo);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null)pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return bean;

	}

	@Override
	public int returnCantidadUsuario() {
		
		int cantidadUsuarios = 0;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null; 
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="SELECT COUNT(id_usuario) FROM tbusuario";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				cantidadUsuarios=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return cantidadUsuarios;
	}

	@Override
	public int updateUsuario(Usuario bean) {
		
		int salida=-1;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="update tbusuario set nom_usuario=?,apePat_usuario=?,apeMat_usuario=?,dni_usuario=?,cod_cargo=?,cuenta_usuario=?, contraseÃ±a_usuario=?,dir_usuario=?, " +
					   "cod_dist=?,tel_usuario=?,correo_usuario=?,foto_usuario=?,id_perfil=?," +
					   "fecha_usuario=CONCAT_WS('-',YEAR(SYSDATE()),MONTH(SYSDATE()),DAY(SYSDATE())),hora_usuario=CONCAT_WS(':',HOUR(SYSDATE()),MINUTE(SYSDATE()),SECOND(SYSDATE())) where id_usuario=?";
			pstm=cn.prepareStatement(sql);
			
			pstm.setString(1, bean.getNom());
			pstm.setString(2, bean.getApePat());
			pstm.setString(3, bean.getApeMat());
			pstm.setString(4, bean.getDni());
			pstm.setInt(5, bean.getCodCargo());
			pstm.setString(6, bean.getCuenta());
			pstm.setString(7, bean.getContraseña());
			pstm.setString(8, bean.getDir());
			pstm.setInt(9, bean.getCodDist());
			pstm.setString(10, bean.getTel());
			pstm.setString(11, bean.getCorreo());
			pstm.setBytes(12, bean.getFoto());
			pstm.setInt(13, bean.getIdPerfil());
			pstm.setString(14, bean.getId());
			
			salida=pstm.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
	}

	@Override
	public int deleteUsuario(String codigo) {
		
		int salida=-1;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="delete from tbusuario where id_usuario=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, codigo);
			
			salida=pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
	}

	@Override
	public List<Usuario> ListUsuariosByApellido(String apellido, String codUsuario) {
		List<Usuario> lista = new ArrayList<Usuario>();
		Usuario bean = null;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select u.id_usuario,u.nom_usuario,u.apePat_usuario,u.apeMat_usuario,u.dni_usuario,a.nom_area,u.cuenta_usuario,u.contraseÃ±a_usuario, "+
					   "u.dir_usuario,d.distrito,pr.provincia,dp.departamento,u.tel_usuario,u.correo_usuario,DATE_FORMAT(u.fecha_usuario, '%d/%m/%Y'),u.hora_usuario,u.foto_usuario,p.desc_perfil from tbusuario u inner join tbcargo c "+
					   "on u.cod_cargo=c.cod_cargo inner join tbarea a "+
					   "on c.cod_area=a.cod_area  inner join tbdistrito d "+
					   "on u.cod_dist=d.cod_dist  inner join tbprovincia pr "+
					   "on d.cod_prov=pr.cod_prov inner join tbdepartamento dp "+
					   "on pr.cod_dep=dp.cod_dep inner join tbperfil p "+
					   "on u.id_perfil=p.id_perfil "+
					   "where (CONCAT_WS(' ',u.apePat_usuario,u.apeMat_usuario) like ? or CONCAT_WS(' ',u.apePat_usuario,u.apeMat_usuario) like ?) and u.id_usuario <> ?";
				
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,apellido+"%");
			pstm.setString(2,"% "+apellido+"%");
			pstm.setString(3, codUsuario);;
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Usuario();
	
				bean.setId(rs.getString(1));
				bean.setNom(rs.getString(2));
				bean.setApePat(rs.getString(3));
				bean.setApeMat(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setNomArea(rs.getString(6));
				bean.setCuenta(rs.getString(7));
				bean.setContraseña(rs.getString(8));
				bean.setDir(rs.getString(9));
				bean.setNomDistrito(rs.getString(10));
				bean.setNomProvincia(rs.getString(11));
				bean.setNomDepartamento(rs.getString(12));
				bean.setTel(rs.getString(13));
				bean.setCorreo(rs.getString(14));
				bean.setFecha(rs.getString(15));
				bean.setHora(rs.getString(16));
				bean.setFoto(rs.getBytes(17));
				bean.setNomPerfil(rs.getString(18));		
				
				lista.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null)pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return lista;
	}
	
	public String validarDni(String dni) {
		
		String vDni="";
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select dni_usuario from tbusuario where dni_usuario=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, dni);
			rs=pstm.executeQuery();
			
			if(rs.next()){
				vDni=rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return vDni;
	}
	
	public String validarDniUpdate(String dni, String codigo) {
		
		String vDniUpdate="";
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select dni_usuario from tbusuario where dni_usuario=? and id_usuario <> ?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, dni);
			pstm.setString(2, codigo);
			rs=pstm.executeQuery();
			
			if(rs.next()){
				vDniUpdate=rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return vDniUpdate;
	}

}
