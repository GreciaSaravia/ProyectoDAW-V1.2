package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import net.compensacion.entidad.Expediente;
import net.compensacion.entidad.Requisitos;
import net.compensacion.entidad.Solicitud;
import net.compensacion.interfaces.SolicitudDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlSolicitudDAO implements SolicitudDAO {

	@Override
	public int returnCodigo() {
		int codigo = 0;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null; 
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select max(cod_solic) from tbsolicitud" ;
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
	public int addSolicitud(Solicitud bean, Requisitos req, Expediente exp) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		PreparedStatement pstm2=null;
		PreparedStatement pstm3=null;
		try {
			
			cn=MySqlBDConexion.getConexion();
			cn.setAutoCommit(false);
			String sql="INSERT INTO tbsolicitud VALUES (?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, bean.getCodSolicitud());
			pstm.setString(2, bean.getFecha());
			pstm.setString(3, bean.getHora());
			pstm.setInt(4, bean.getCodSolicitante());
			pstm.setString(5, bean.getCodUsuario());
			
			salida=pstm.executeUpdate();
			
			String sql2="insert into tbrequisitos values (?,?,?,?,?,?,?)";
			pstm2=cn.prepareStatement(sql2);
			pstm2.setInt(1, req.getCodRequisitos());
			pstm2.setString(2, req.getDecJurada());
			pstm2.setString(3, req.getResCont());
			pstm2.setString(4, req.getResQuinq());
			pstm2.setString(5, req.getConstPago());
			pstm2.setString(6, req.getDni());
			pstm2.setInt(7, req.getCodSolicitud());
			
			salida+=pstm2.executeUpdate();
			
			String sql3="insert into tbexpediente values (?)";
			pstm3=cn.prepareStatement(sql3);
			pstm3.setInt(1, exp.getCodigoExp());
			
			salida+=pstm3.executeUpdate();
			
			cn.commit();
			
		} catch (SQLException e) {
			try {
				cn.rollback();
				salida=-1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				if(pstm3!=null) pstm.close();
				if(pstm2!=null) pstm.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
	}

	@Override
	public List<Solicitud> ListAllSolicitud() {
	
		List<Solicitud> lista=new ArrayList<Solicitud>();
		Solicitud bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select s.cod_solic, DATE_FORMAT(s.fecha_solic, '%d/%m/%Y'), s.hora_solic, s.cod_sol, sol.nom_sol, " + 
					"	 sol.apePat_sol,sol.apeMat_sol, sol.dni_sol, sol.dir_sol, sol.foto_usuario ,r.decl_jura,r.res_cont, " + 
					"     r.res_quin,r.const_pago,r.dni" + 
					"	 from tbsolicitud s inner join tbsolicitante sol on s.cod_sol=sol.cod_sol  " + 
					"   inner join tbrequisitos r on r.cod_solic=s.cod_solic ";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Solicitud();

				bean.setCodSolicitud(rs.getInt(1));
				bean.setFecha(rs.getString(2));
				bean.setHora(rs.getString(3));
				bean.setCodSolicitante(rs.getInt(4));
				bean.setNombres(rs.getString(5));
				bean.setApePat(rs.getString(6));
				bean.setApeMat(rs.getString(7));
				bean.setDni(rs.getString(8));
				bean.setDir(rs.getString(9));
				byte [] imagen=rs.getBytes(10);
				String fo=Base64.getEncoder().encodeToString(imagen);
				bean.setFoto(fo);
				
				bean.setDecJurada(rs.getString(11));
				bean.setResCont(rs.getString(12));
				bean.setResQuinq(rs.getString(13));
				bean.setConstPago(rs.getString(14));
				bean.setDocdni(rs.getString(15));
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
	public int updateSolicitud(Solicitud bean, Requisitos req) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		PreparedStatement pstm2=null;
		try {
			
			cn=MySqlBDConexion.getConexion();
			cn.setAutoCommit(false);
			String sql="update tbsolicitud set fecha_solic=?, hora_solic=?, "+
					   "cod_sol=?, id_usuario=? "+
					   "where cod_solic=?";
			pstm=cn.prepareStatement(sql);
			
			pstm.setString(1, bean.getFecha());
			pstm.setString(2, bean.getHora());
			pstm.setInt(3, bean.getCodSolicitante());
			pstm.setString(4, bean.getCodUsuario());
			pstm.setInt(5, bean.getCodSolicitud());
			
			salida=pstm.executeUpdate();
			
			String sql2="update tbrequisitos " + 
					    "set decl_jura=?, res_cont=?, res_quin=?, const_pago=?, dni=? " + 
					    "where cod_solic=? ";
			pstm2=cn.prepareStatement(sql2);
		
			pstm2.setString(1, req.getDecJurada());
			pstm2.setString(2, req.getResCont());
			pstm2.setString(3, req.getResQuinq());
			pstm2.setString(4, req.getConstPago());
			pstm2.setString(5, req.getDni());
			pstm2.setInt(6, req.getCodSolicitud());
			
			salida+=pstm2.executeUpdate();
			
			cn.commit();
			
		} catch (SQLException e) {
			try {
				cn.rollback();
				salida=-1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				if(pstm2!=null) pstm.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	
	@Override
	public int deleteSolicitud(String codigo) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null,pstm1=null, pstm2=null, pstm3=null, pstm4=null, pstm5=null, pstm6=null, pstm7=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			cn.setAutoCommit(false);
			
			String sql="delete from tbInformeEscalafonario where cod_exp=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, codigo);
			salida=pstm.executeUpdate();

			String sql1="delete from tbInformeTecnicoLegal where cod_exp=?";
			pstm1=cn.prepareStatement(sql1);
			pstm1.setString(1, codigo);
			salida+=pstm1.executeUpdate();
			
			String sql2="delete from tbInformeDispoPresup where cod_exp=?";
			pstm2=cn.prepareStatement(sql2);
			pstm2.setString(1, codigo);
			salida+=pstm2.executeUpdate();
			
			String sql3="delete from tbInformeLegal where cod_exp=?";
			pstm3=cn.prepareStatement(sql3);
			pstm3.setString(1, codigo);
			salida+=pstm3.executeUpdate();
			
			String sql4="delete from TBResolucion where cod_exp=?";
			pstm4=cn.prepareStatement(sql4);
			pstm4.setString(1, codigo);
			salida+=pstm4.executeUpdate();
			
			String sql5="delete from  tbExpediente where cod_exp=?";
			pstm5=cn.prepareStatement(sql5);
			pstm5.setString(1, codigo);
			
			salida+=pstm5.executeUpdate();
			
			String sql6="delete from tbRequisitos where cod_solic=?";
			pstm6=cn.prepareStatement(sql6);
			pstm6.setString(1, codigo);
			
			salida+=pstm6.executeUpdate();
			
			String sql7="delete from tbsolicitud where cod_solic=?";
			pstm7=cn.prepareStatement(sql7);
			pstm7.setString(1, codigo);
			
			salida+=pstm7.executeUpdate();
			
			
			cn.commit();
			
		} catch (SQLException e) {
			try {
				cn.rollback();
				salida=-1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
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

	@Override
	public List<Solicitud> ListSolicitudxCodi(String cod) {
		List<Solicitud> lista=new ArrayList<Solicitud>();
		Solicitud bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select  DATE_FORMAT(s.fecha_solic, '%d/%m/%Y'), s.hora_solic, s.cod_sol, sol.nom_sol, " + 
					"	 sol.apePat_sol,sol.apeMat_sol, sol.dni_sol, sol.dir_sol, sol.foto_usuario ,r.decl_jura,r.res_cont, " + 
					"     r.res_quin,r.const_pago,r.dni ,s.id_usuario,concat( u.nom_usuario,' ' , u.apePat_usuario,' ' , u.apeMat_usuario)" + 
					"						 from tbsolicitud s inner join tbsolicitante sol on s.cod_sol=sol.cod_sol " + 
					"					   inner join tbrequisitos r on r.cod_solic=s.cod_solic " + 
					"                       inner join tbusuario u on s.id_usuario=u.id_usuario where s.cod_solic=? ";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,cod);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Solicitud();
				bean.setFecha(rs.getString(1));
				bean.setHora(rs.getString(2));
				bean.setCodSolicitante(rs.getInt(3));
				bean.setNombres(rs.getString(4));
				bean.setApePat(rs.getString(5));
				bean.setApeMat(rs.getString(6));
				bean.setDni(rs.getString(7));
				bean.setDir(rs.getString(8));
				byte [] imagen=rs.getBytes(9);
				String fo=Base64.getEncoder().encodeToString(imagen);
				bean.setFoto(fo);
				
				bean.setDecJurada(rs.getString(10));
				bean.setResCont(rs.getString(11));
				bean.setResQuinq(rs.getString(12));
				bean.setConstPago(rs.getString(13));
				bean.setDocdni(rs.getString(14));
				bean.setCodUsuario(rs.getString(15));
				bean.setNom_usuario(rs.getString(16));
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
	public List<Solicitud> ListAllSolicitudReporte() {
		List<Solicitud> lista = new ArrayList<Solicitud>();
		Solicitud bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select s.cod_solic, sol.nom_sol, CONCAT_WS(' ',sol.apePat_sol, sol.apeMat_sol), sol.dni_sol, DATE_FORMAT(s.fecha_solic, '%d/%m/%Y'), DATE_FORMAT(s.hora_solic ,'%T') "+
					   "from tbsolicitud s inner join tbsolicitante sol " +
					   "on s.cod_sol=sol.cod_sol ";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Solicitud();

				bean.setCodSolicitud(rs.getInt(1));
				bean.setNombres(rs.getString(2));
				bean.setApellidos(rs.getString(3));
				bean.setDni(rs.getString(4));
				bean.setFecha(rs.getString(5));
				bean.setHora(rs.getString(6));
				
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

	
}
