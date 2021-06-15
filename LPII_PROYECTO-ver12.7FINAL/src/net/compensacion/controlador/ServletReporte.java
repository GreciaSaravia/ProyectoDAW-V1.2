package net.compensacion.controlador;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.compensacion.entidad.Expediente;
import net.compensacion.entidad.InformeDisponibilidadPresupuestal;
import net.compensacion.entidad.InformeEscalafonario;
import net.compensacion.entidad.InformeLegal;
import net.compensacion.entidad.InformeTecnicoLegal;
import net.compensacion.entidad.Resolucion;
import net.compensacion.entidad.Solicitud;
import net.compensacion.service.ExpAreaService;
import net.compensacion.service.InfoDisponPresuService;
import net.compensacion.service.InfoEscalafonarioService;
import net.compensacion.service.InfoLegalService;
import net.compensacion.service.InfoTecnicoLegalService;
import net.compensacion.service.ResolucionService;
import net.compensacion.service.SolicitudService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;


@WebServlet("/ServletReporte")
public class ServletReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	SolicitudService servicioSolicitud;
	InfoEscalafonarioService servicioIE;
	InfoTecnicoLegalService servicioITL;
	InfoDisponPresuService servicioIDP;
	InfoLegalService servicioIL;
	ResolucionService servicioRES;
	ExpAreaService servicioEXP;
    
    public ServletReporte() {
        super();
        servicioSolicitud = new SolicitudService();
        servicioIE = new InfoEscalafonarioService();
        servicioITL = new InfoTecnicoLegalService();
        servicioIDP = new InfoDisponPresuService();
        servicioIL = new InfoLegalService();
        servicioRES = new ResolucionService();
        servicioEXP = new ExpAreaService();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String documento=request.getParameter("reporte");
		
		switch (documento) {
			case "7":
				
				List<Expediente> listaEXP=servicioEXP.listExpTerminadosReporte();
				String rutaEXP = "C:\\Users\\formu\\OneDrive\\Escritorio\\CICLO V\\DESARROLLO DE APLICACIONES WEB I\\PROYECTO 4TO CICLO\\reporteExpedientes.jasper";
				JRBeanCollectionDataSource dataSourceEXP=new JRBeanCollectionDataSource(listaEXP);
				
				
				
				JasperPrint jasperPrintEXP =null;
				try {
					FileInputStream fisEXP = new FileInputStream(rutaEXP);
					BufferedInputStream bufferedInputStreamEXP = new BufferedInputStream(fisEXP);

					JasperReport jasperReportEXP = (JasperReport) JRLoader.loadObject(bufferedInputStreamEXP);

					jasperPrintEXP = JasperFillManager.fillReport(jasperReportEXP, null, dataSourceEXP);
					response.setHeader("Content-Disposition", "");	
					response.setContentType("application/pdf");
					
					JasperExportManager.exportReportToPdfStream(jasperPrintEXP, response.getOutputStream());
					
					

				} catch (JRException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				break;
				
			case "1":
				
				List<Solicitud> lista=servicioSolicitud.ListAllSolicitudReporte();
				String ruta = "C:\\Users\\lazaro cornejo\\Desktop\\PROYECTO JAVA\\LPII_PROYECTO-ver12.1\\WebContent\\Reportes\\reporteSolicitudes.jasper";
				JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(lista);

				
				
				JasperPrint jasperPrint =null;
				try {
					FileInputStream fis = new FileInputStream(ruta);
					BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);

					JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);

					jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
					response.setHeader("Content-Disposition", "");	
					response.setContentType("application/pdf");
					
					JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
					
					

				} catch (JRException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				
				break;
				
			case "2":
				
				List<InformeEscalafonario> listaIE=servicioIE.listIEReporte();
				String rutaIE = "C:\\Users\\formu\\OneDrive\\Escritorio\\CICLO V\\DESARROLLO DE APLICACIONES WEB I\\PROYECTO 4TO CICLO\\reporteInformesEscalafonarios.jasper";
				JRBeanCollectionDataSource dataSourceIE=new JRBeanCollectionDataSource(listaIE);

				
				
				JasperPrint jasperPrintIE =null;
				try {
					FileInputStream fisIE = new FileInputStream(rutaIE);
					BufferedInputStream bufferedInputStreamIE = new BufferedInputStream(fisIE);

					JasperReport jasperReportIE = (JasperReport) JRLoader.loadObject(bufferedInputStreamIE);

					jasperPrintIE = JasperFillManager.fillReport(jasperReportIE, null, dataSourceIE);
					response.setHeader("Content-Disposition", "");	
					response.setContentType("application/pdf");
					
					JasperExportManager.exportReportToPdfStream(jasperPrintIE, response.getOutputStream());
					
					

				} catch (JRException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				

				break;
				
			case "3":
				
				List<InformeTecnicoLegal> listaITL=servicioITL.listaItlReporte();
				String rutaITL = "C:\\Users\\formu\\OneDrive\\Escritorio\\CICLO V\\DESARROLLO DE APLICACIONES WEB I\\PROYECTO 4TO CICLO\\reporteInformesTecnicosLegales.jasper";
				JRBeanCollectionDataSource dataSourceITL=new JRBeanCollectionDataSource(listaITL);

				
				
				JasperPrint jasperPrintITL =null;
				try {
					FileInputStream fisITL = new FileInputStream(rutaITL);
					BufferedInputStream bufferedInputStreamITL = new BufferedInputStream(fisITL);

					JasperReport jasperReportITL = (JasperReport) JRLoader.loadObject(bufferedInputStreamITL);

					jasperPrintITL = JasperFillManager.fillReport(jasperReportITL, null, dataSourceITL);
					response.setHeader("Content-Disposition", "");	
					response.setContentType("application/pdf");
					
					JasperExportManager.exportReportToPdfStream(jasperPrintITL, response.getOutputStream());
					
					

				} catch (JRException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				break;
			case "4":
				
				List<InformeDisponibilidadPresupuestal> listaIDP=servicioIDP.listaIDPReporte();
				String rutaIDP = "C:\\Users\\formu\\OneDrive\\Escritorio\\CICLO V\\DESARROLLO DE APLICACIONES WEB I\\PROYECTO 4TO CICLO\\reporteInformesTDisponibilidadPresupuestal.jasper";
				JRBeanCollectionDataSource dataSourceIDP=new JRBeanCollectionDataSource(listaIDP);

				
				
				JasperPrint jasperPrintIDP =null;
				try {
					FileInputStream fisIDP = new FileInputStream(rutaIDP);
					BufferedInputStream bufferedInputStreamIDP = new BufferedInputStream(fisIDP);

					JasperReport jasperReportIDP = (JasperReport) JRLoader.loadObject(bufferedInputStreamIDP);

					jasperPrintIDP = JasperFillManager.fillReport(jasperReportIDP, null, dataSourceIDP);
					response.setHeader("Content-Disposition", "");	
					response.setContentType("application/pdf");
					
					JasperExportManager.exportReportToPdfStream(jasperPrintIDP, response.getOutputStream());
					
					

				} catch (JRException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				break;
				
			case "5":
				
				List<InformeLegal> listaIL=servicioIL.listILReporte();
				String rutaIL = "C:\\Users\\formu\\OneDrive\\Escritorio\\CICLO V\\DESARROLLO DE APLICACIONES WEB I\\PROYECTO 4TO CICLO\\reporteInformesLegales.jasper";
				JRBeanCollectionDataSource dataSourceIL=new JRBeanCollectionDataSource(listaIL);

				
				
				JasperPrint jasperPrintIL =null;
				try {
					FileInputStream fisIL = new FileInputStream(rutaIL);
					BufferedInputStream bufferedInputStreamIL = new BufferedInputStream(fisIL);

					JasperReport jasperReportIL = (JasperReport) JRLoader.loadObject(bufferedInputStreamIL);

					jasperPrintIL = JasperFillManager.fillReport(jasperReportIL, null, dataSourceIL);
					response.setHeader("Content-Disposition", "");	
					response.setContentType("application/pdf");
					
					JasperExportManager.exportReportToPdfStream(jasperPrintIL, response.getOutputStream());
					
					

				} catch (JRException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				break;
				
			case "6":
				
				List<Resolucion> listaRES=servicioRES.listaResReporte();
				String rutaRES = "C:\\Users\\formu\\OneDrive\\Escritorio\\CICLO V\\DESARROLLO DE APLICACIONES WEB I\\PROYECTO 4TO CICLO\\reporteResoluciones.jasper";
				JRBeanCollectionDataSource dataSourceRES=new JRBeanCollectionDataSource(listaRES);

				
				
				JasperPrint jasperPrintRES =null;
				try {
					FileInputStream fisRES = new FileInputStream(rutaRES);
					BufferedInputStream bufferedInputStreamRES = new BufferedInputStream(fisRES);

					JasperReport jasperReportRES = (JasperReport) JRLoader.loadObject(bufferedInputStreamRES);

					jasperPrintRES = JasperFillManager.fillReport(jasperReportRES, null, dataSourceRES);
					response.setHeader("Content-Disposition", "");	
					response.setContentType("application/pdf");
					
					JasperExportManager.exportReportToPdfStream(jasperPrintRES, response.getOutputStream());
					
					

				} catch (JRException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				break;	
			
			default:
				break;
		}
		
	}

}
