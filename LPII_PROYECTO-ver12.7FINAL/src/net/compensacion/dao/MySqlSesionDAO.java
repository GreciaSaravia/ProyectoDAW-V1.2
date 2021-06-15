package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.compensacion.entidad.Menu;
import net.compensacion.entidad.Opciones;
import net.compensacion.entidad.Sesion;
import net.compensacion.interfaces.SesionDAO;
import net.compensacion.utils.MySqlBDConexion;;

public class MySqlSesionDAO implements SesionDAO {

	@Override
	public Sesion iniciarSesion(String cuenta, String contraseña) {
		Sesion bean = null;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			/*AGREGUÉ ID_USUARIO*/
			cn=MySqlBDConexion.getConexion();
			String sql="select u.id_usuario,u.id_perfil,u.nom_usuario,u.apePat_usuario,c.nom_cargo,u.foto_usuario from tbusuario u inner join tbcargo c "+
					   "on u.cod_cargo=c.cod_cargo where cuenta_usuario=? and contraseÃ±a_usuario=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, cuenta);
			pstm.setString(2, contraseña);
			
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				bean=new Sesion();
				bean.setCodUsuario(rs.getString(1));
				bean.setCodPerfil(rs.getInt(2));
				bean.setNomUsuario(rs.getString(3));
				bean.setApePaterno(rs.getString(4));
				bean.setCargo(rs.getString(5));
				bean.setFoto(rs.getBytes(6));
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		return bean;
	}

	public List<Menu> menuPerfil(int codigoPerfil) {
		
		List<Menu> lista=new ArrayList<Menu>();
		
		Menu bean = null;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select DISTINCT  d.id_menu, m.desc_smenu, m.claseicono from TBDetallePerfilMenuOpcion d inner join tbmenu m on d.id_menu=m.id_menu where id_perfil=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codigoPerfil);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Menu();
				
				bean.setCodigo(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setClaseIcono(rs.getString(3));
				
				lista.add(bean);
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
		
		
		return lista;
	}

	@Override
	public List<Opciones> opcionesPerfil(int codigoPerfil, int codMenu) {
		
		List<Opciones> lista=new ArrayList<Opciones>();
		
		Opciones bean = null;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select DISTINCT  o.desc_op,o.url from TBDetallePerfilMenuOpcion d inner join tbopciones o on d.id_op=o.id_op where id_perfil=? and id_menu=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codigoPerfil);
			pstm.setInt(2, codMenu);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Opciones();
				
				bean.setNombre(rs.getString(1));
				bean.setUrl(rs.getString(2));
				
				lista.add(bean);
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
		
		
		return lista;
		
	}

}
