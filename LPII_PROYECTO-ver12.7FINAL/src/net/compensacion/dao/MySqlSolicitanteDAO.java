package net.compensacion.dao;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.compensacion.entidad.Solicitante;
import net.compensacion.interfaces.SolicitanteDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlSolicitanteDAO implements SolicitanteDAO {

	@Override
	public List<Solicitante> ListSolicitanteSinSolicitud() {
		List<Solicitante> lista = new ArrayList<Solicitante>();
		Solicitante bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select cod_sol, nom_sol, apePat_Sol, apeMat_sol, dni_sol, dir_sol, foto_usuario " + 
					" from tbSolicitante " + 
					" where cod_sol not in (select cod_sol from tbSolicitud) ";
				
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Solicitante();
	
				bean.setId(rs.getString(1));
				bean.setNom(rs.getString(2));
				bean.setApePat(rs.getString(3));
				bean.setApeMat(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setDir(rs.getString(6));	
				byte [] imagen=rs.getBytes(7);
				String fo=Base64.getEncoder().encodeToString(imagen);
				bean.setFotoString(fo);
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
	public int addSolicitante(Solicitante bean) {
		
		int salida=-1;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="INSERT INTO tbsolicitante VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, bean.getId());
			pstm.setString(2, bean.getNom());
			pstm.setString(3, bean.getApePat());
			pstm.setString(4, bean.getApeMat());
			pstm.setString(5, bean.getDni());
			pstm.setString(6, bean.getDir());
			pstm.setString(7, bean.getTel());
			pstm.setString(8, bean.getCorreo());
			pstm.setString(9, bean.getFecha());
			pstm.setString(10, bean.getHora());
			pstm.setBytes(11, bean.getFoto());
			pstm.setInt(12, bean.getCodDist());
			pstm.setString(13, bean.getIdUsuario());
			
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
			String sql="select max(cod_sol) from tbsolicitante" ;
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				codigo=rs.getInt(1)+1;
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
	public String validarDni(String dni) {
		
		String vDni="";
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select dni_sol from tbsolicitante  where dni_sol=?";
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
	
	@Override
	public String validarDniUpdate(String dni, String codigo) {
		
		String vDniUpdate="";
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select dni_sol from tbsolicitante  where dni_sol=? and cod_sol <> ?";
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
	public List<Solicitante> ListAllUsuarios() {
		List<Solicitante> lista = new ArrayList<Solicitante>();
		Solicitante bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select s.cod_sol, s.nom_sol, s.apePat_Sol, apeMat_sol, s.dni_sol, s.dir_sol, s.tel_sol, s.correo_Sol,DATE_FORMAT(s.fecha_sol, '%d/%m/%Y'),s.hora_sol,s.foto_usuario,d.distrito,s.id_usuario "+ 
					   "from tbsolicitante s inner join tbdistrito d " +
					   "on s.cod_dist=d.cod_dist  " +
					   "order by s.cod_sol asc ";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Solicitante();

				bean.setId(rs.getString(1));
				bean.setNom(rs.getString(2));
				bean.setApePat(rs.getString(3));
				bean.setApeMat(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setDir(rs.getString(6));
				bean.setTel(rs.getString(7));
				bean.setCorreo(rs.getString(8));
				bean.setFecha(rs.getString(9));
				bean.setHora(rs.getString(10));
				bean.setFoto(rs.getBytes(11));
				bean.setNomDistrito(rs.getString(12));
				bean.setIdUsuario(rs.getString(13));
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
	public int returnCantidadSolicitante() {
		
		int cantidadSolicitante = 0;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null; 
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="SELECT COUNT(cod_sol) FROM tbsolicitante";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				cantidadSolicitante=rs.getInt(1);
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
		
		return cantidadSolicitante;
	}

	@Override
	public Solicitante firstSolicitante() {
		
		Solicitante bean = null;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select s.cod_sol, s.nom_sol, s.apePat_Sol, apeMat_sol, s.dni_sol, s.dir_sol, s.tel_sol, s.correo_Sol,DATE_FORMAT(s.fecha_sol, '%d/%m/%Y'),s.hora_sol,s.foto_usuario, "
					+  "d.distrito,pr.provincia,dp.departamento,s.id_usuario "+ 
					   "from tbsolicitante s inner join tbdistrito d " +
					   "on s.cod_dist=d.cod_dist inner join tbprovincia pr " + 
					   "on d.cod_prov=pr.cod_prov inner join tbdepartamento dp " + 
					   "on pr.cod_dep=dp.cod_dep " +
					   "order by s.cod_sol asc limit 1 ";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				
				bean=new Solicitante();

				bean.setId(rs.getString(1));
				bean.setNom(rs.getString(2));
				bean.setApePat(rs.getString(3));
				bean.setApeMat(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setDir(rs.getString(6));
				bean.setTel(rs.getString(7));
				bean.setCorreo(rs.getString(8));
				bean.setFecha(rs.getString(9));
				bean.setHora(rs.getString(10));
				bean.setFoto(rs.getBytes(11));
				bean.setNomDistrito(rs.getString(12));
				bean.setNomProvincia(rs.getString(13));
				bean.setNomDepartamento(rs.getString(14));
				bean.setIdUsuario(rs.getString(15));
				
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
	public Solicitante SolicitanteByCodigo(String codigo) {
		
		Solicitante bean = null;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select s.cod_sol, s.nom_sol, s.apePat_Sol, apeMat_sol, s.dni_sol, s.dir_sol, s.tel_sol, s.correo_Sol,DATE_FORMAT(s.fecha_sol, '%d/%m/%Y'),s.hora_sol,s.foto_usuario, "
					+  "d.distrito,pr.provincia,dp.departamento,s.id_usuario,dp.cod_dep, pr.cod_prov,d.cod_dist "+ 
					   "from tbsolicitante s inner join tbdistrito d " +
					   "on s.cod_dist=d.cod_dist inner join tbprovincia pr " + 
					   "on d.cod_prov=pr.cod_prov inner join tbdepartamento dp " + 
					   "on pr.cod_dep=dp.cod_dep " +
					   "where s.cod_sol=?";
			
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, codigo);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				
				bean=new Solicitante();

				bean.setId(rs.getString(1));
				bean.setNom(rs.getString(2));
				bean.setApePat(rs.getString(3));
				bean.setApeMat(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setDir(rs.getString(6));
				bean.setTel(rs.getString(7));
				bean.setCorreo(rs.getString(8));
				bean.setFecha(rs.getString(9));
				bean.setHora(rs.getString(10));
				bean.setFoto(rs.getBytes(11));
				bean.setNomDistrito(rs.getString(12));
				bean.setNomProvincia(rs.getString(13));
				bean.setNomDepartamento(rs.getString(14));
				bean.setIdUsuario(rs.getString(15));
				bean.setCod_dep(rs.getInt(16));
				bean.setCod_prov(rs.getInt(17));
				bean.setCodDist(rs.getInt(18));
				byte [] imagen=rs.getBytes(11);
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
	public int updateSolicitante(Solicitante bean) {
		
		int salida=-1;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="update TBSolicitante set nom_sol=?,apePat_sol=?,apeMat_sol=?,dni_sol=?,dir_sol=?, tel_sol=?,correo_sol=?,fecha_sol=CONCAT_WS('-',YEAR(SYSDATE()),MONTH(SYSDATE()),DAY(SYSDATE())),hora_sol=CONCAT_WS(':',HOUR(SYSDATE()),MINUTE(SYSDATE()),SECOND(SYSDATE())),foto_usuario=?,cod_dist=?,id_usuario=? where cod_sol=?";
			pstm=cn.prepareStatement(sql);
		
			pstm.setString(1, bean.getNom());
			pstm.setString(2, bean.getApePat());
			pstm.setString(3, bean.getApeMat());
			pstm.setString(4, bean.getDni());
			pstm.setString(5, bean.getDir());
			pstm.setString(6, bean.getTel());
			pstm.setString(7, bean.getCorreo());
			//pstm.setString(8, bean.getFecha());
			//pstm.setString(9, bean.getHora());
			pstm.setBytes(8, bean.getFoto());
			pstm.setInt(9, bean.getCodDist());
			pstm.setString(10, bean.getIdUsuario());
			pstm.setString(11, bean.getId());
			
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
	public int deleteSolicitante(String codigo) {
		
		int salida=-1;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="delete from tbsolicitante where cod_sol=?";
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
	
	/*
	@Override
	public int deleteSolicitante(String codigo) {
		
		int salida=-1;
		
		Connection cn=null;
		PreparedStatement pstm=null,pstm1=null, pstm2=null, pstm3=null, pstm4=null, pstm5=null, pstm6=null, pstm7=null, pstm8=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			cn.setAutoCommit(false);
			
			String sql="delete from TBInformeEscalafonario where cod_exp IN "
					+ "(SELECT cod_solic FROM tbsolicitud WHERE cod_sol=?)";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, codigo);
			salida=pstm.executeUpdate();

			String sql1="delete from TBInformeTecnicoLegal where cod_exp IN "
					+ "(SELECT cod_solic FROM tbsolicitud WHERE cod_sol=?)";
			pstm1=cn.prepareStatement(sql1);
			pstm1.setString(1, codigo);
			salida+=pstm1.executeUpdate();
			
			String sql2="delete from TBInformeDispoPresup where cod_exp IN "
					+ "(SELECT cod_solic FROM tbsolicitud WHERE cod_sol=?)";
			pstm2=cn.prepareStatement(sql2);
			pstm2.setString(1, codigo);
			salida+=pstm2.executeUpdate();
			
			String sql3="delete from TBInformeLegal where cod_exp IN "
					+ "(SELECT cod_solic FROM tbsolicitud WHERE cod_sol=?)";
			pstm3=cn.prepareStatement(sql3);
			pstm3.setString(1, codigo);
			salida+=pstm3.executeUpdate();
			
			String sql4="delete from TBResolucion where cod_exp IN "
					+ "(SELECT cod_solic FROM tbsolicitud WHERE cod_sol=?)";
			pstm4=cn.prepareStatement(sql4);
			pstm4.setString(1, codigo);
			salida+=pstm4.executeUpdate();
			
			String sql5="delete from  tbexpediente where cod_exp IN "
					+ "(SELECT cod_solic FROM tbsolicitud WHERE cod_sol=?)";
			pstm5=cn.prepareStatement(sql5);
			pstm5.setString(1, codigo);
			
			salida+=pstm5.executeUpdate();
			
			String sql6="delete from tbrequisitos where cod_solic IN (SELECT cod_solic FROM tbsolicitud WHERE cod_sol=?)";
			pstm6=cn.prepareStatement(sql6);
			pstm6.setString(1, codigo);
			
			salida+=pstm6.executeUpdate();
			
			String sql7="delete from tbsolicitud where cod_sol=?";
			pstm7=cn.prepareStatement(sql7);
			pstm7.setString(1, codigo);
			
			salida+=pstm7.executeUpdate();
			
			String sql8="delete from tbsolicitante where cod_sol=?";
			pstm8=cn.prepareStatement(sql8);
			pstm8.setString(1, codigo);
			
			salida+=pstm8.executeUpdate();
			
			cn.commit();
			
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				if(pstm8!=null) pstm7.close();
				if(pstm7!=null) pstm7.close();
				if(pstm6!=null) pstm6.close();
				if(pstm5!=null) pstm5.close();
				if(pstm4!=null) pstm1.close();
				if(pstm3!=null) pstm3.close();
				if(pstm2!=null) pstm2.close();
				if(pstm1!=null) pstm1.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
	}
    */
	
	@Override
	public List<Solicitante> ListSolicitanteByApellido(String apellido, int CodSoli) {
		
		List<Solicitante> lista = new ArrayList<Solicitante>();
		Solicitante bean = null;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select s.cod_sol, s.nom_sol, s.apePat_Sol, apeMat_sol, s.dni_sol, s.dir_sol, s.tel_sol, s.correo_Sol,DATE_FORMAT(s.fecha_sol, '%d/%m/%Y'),s.hora_sol,s.foto_usuario,d.distrito,pr.provincia,dp.departamento,s.id_usuario "+
					   "from tbsolicitante s inner join tbdistrito d  "+
					   "on s.cod_dist=d.cod_dist inner join tbprovincia pr  "+
					   "on d.cod_prov=pr.cod_prov inner join tbdepartamento dp "+
					   "on pr.cod_dep=dp.cod_dep "+
					   "where (concat_ws(' ',s.apePat_sol,s.apeMat_sol) like ? or concat_ws(' ',s.apePat_sol,s.apeMat_sol) like ?) and s.cod_sol <> ? ";
						
			
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,apellido+"%");
			pstm.setString(2,"% "+apellido+"%");
			pstm.setInt(3, CodSoli);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Solicitante();
	
				bean.setId(rs.getString(1));
				bean.setNom(rs.getString(2));
				bean.setApePat(rs.getString(3));
				bean.setApeMat(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setDir(rs.getString(6));
				bean.setTel(rs.getString(7));
				bean.setCorreo(rs.getString(8));
				bean.setFecha(rs.getString(9));
				bean.setHora(rs.getString(10));
				bean.setFoto(rs.getBytes(11));
				bean.setNomDistrito(rs.getString(12));
				bean.setNomProvincia(rs.getString(13));
				bean.setNomDepartamento(rs.getString(14));
				bean.setIdUsuario(rs.getString(15));		
				
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
	public List<Solicitante> ListSolicitanteByApellidoSinSolicitud(String apellido) {
		
		List<Solicitante> lista = new ArrayList<Solicitante>();
		Solicitante bean = null;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select s.cod_sol, s.nom_sol, s.apePat_Sol, apeMat_sol, s.dni_sol, s.dir_sol, s.tel_sol, s.correo_Sol,DATE_FORMAT(s.fecha_sol, '%d/%m/%Y'),s.hora_sol,s.foto_usuario,d.distrito,pr.provincia,dp.departamento,s.id_usuario "+
					   "from tbsolicitante s inner join tbdistrito d  "+
					   "on s.cod_dist=d.cod_dist inner join tbprovincia pr  "+
					   "on d.cod_prov=pr.cod_prov inner join tbdepartamento dp "+
					   "on pr.cod_dep=dp.cod_dep left join tbsolicitud sol " + 
					   "on s.cod_sol=sol.cod_sol "+
					   "where (concat_ws(' ',s.apePat_sol,s.apeMat_sol) like ? or concat_ws(' ',s.apePat_sol,s.apeMat_sol) like ?) and sol.cod_sol is null ";
				
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,apellido+"%");
			pstm.setString(2,"% "+apellido+"%");
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Solicitante();
	
				bean.setId(rs.getString(1));
				bean.setNom(rs.getString(2));
				bean.setApePat(rs.getString(3));
				bean.setApeMat(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setDir(rs.getString(6));
				bean.setTel(rs.getString(7));
				bean.setCorreo(rs.getString(8));
				bean.setFecha(rs.getString(9));
				bean.setHora(rs.getString(10));
				bean.setFoto(rs.getBytes(11));
				bean.setNomDistrito(rs.getString(12));
				bean.setNomProvincia(rs.getString(13));
				bean.setNomDepartamento(rs.getString(14));
				bean.setIdUsuario(rs.getString(15));		
				
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
	public String findExp(int codSoli) {
		String codSolic="";
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select cod_solic from tbsolicitud where cod_sol=? ";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1,codSoli);
			rs=pstm.executeQuery();
			
			if(rs.next()){
				codSolic=rs.getString(1);
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
		return codSolic;
	}
	
}
