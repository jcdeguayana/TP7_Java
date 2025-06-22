package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoSeguro;
import daoImpl.DaoSeguroImpl;
import daoImpl.DaoTipoSeguroImpl;
import entidades.Seguro;
import entidades.TipoSeguro;

/**
 * Servlet implementation class ServletSeguros
 */
@WebServlet("/ServletSeguros")
public class ServletSeguros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSeguros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
   /* accion = agregar --- sirve para preparar y mostrar el formulario de agregar un nuevo seguro.*/
   /* accion = listar --- sirve para traer y mostrar los datos de la tabla seguros.*/
   /*--------------------------------------------------------------------------------------------------------------*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if(accion.isEmpty()) return;
		
		
		if ("agregar".equals(accion))
		{
		DaoTipoSeguroImpl ts = new DaoTipoSeguroImpl();
		List<TipoSeguro> tiposSeguros = ts.listarTipoSeguros();
		int id = ts.siguienteId();
		request.setAttribute("siguienteId", id);
	    request.setAttribute("tiposSeguros", tiposSeguros);
	    RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");
	    rd.forward(request, response);
		}
		
		if("listar".equals(accion)) 
		{
			DaoTipoSeguroImpl ts = new DaoTipoSeguroImpl();
			List<TipoSeguro> tiposSeguros = ts.listarTipoSeguros();
			int id = ts.siguienteId();
			request.setAttribute("siguienteId", id);
		    request.setAttribute("tiposSeguros", tiposSeguros);
		    
			DaoSeguroImpl dao = new DaoSeguroImpl();
			List<Seguro> lista = dao.listarSeguros();
			
			request.setAttribute("ListaSeguros", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
			rd.forward(request, response);
		}
		
		if ("filtrar".equals(accion)) {
		    String tipoSeguro = request.getParameter("tipoSeguro"); 

		    DaoSeguroImpl dao = new DaoSeguroImpl();
		    List<Seguro> listaFiltrada;

		    if (tipoSeguro != null && !tipoSeguro.isEmpty()) {
		        listaFiltrada = dao.listarSegurosPorTipo(tipoSeguro); 
		    } else {
		        listaFiltrada = dao.listarSeguros(); 
		    }
		    
		    DaoTipoSeguroImpl daoTipo = new DaoTipoSeguroImpl(); 
		    List<TipoSeguro> tipos = daoTipo.listarTipoSeguros();
		    request.setAttribute("tiposSeguros", tipos);


		    request.setAttribute("ListaSeguros", listaFiltrada);
		    request.setAttribute("tipoSeleccionado", tipoSeguro); 

		    RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
		    rd.forward(request, response);
		}

	}
//----------------------------------------------------------------------------------------------------------------------
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		 String btnAgregar = request.getParameter("btnAgregar");

		    if (btnAgregar != null && btnAgregar.equals("Aceptar")) {
		        try {
		            //  Leer los datos del formulario
		            String descripcion = request.getParameter("txtDescripcion");
		            int idTipo = Integer.parseInt(request.getParameter("tipo"));
		            double costoContratacion = Double.parseDouble(request.getParameter("txtCosto"));
		            double costoAsegurado = Double.parseDouble(request.getParameter("txtMaximoAgregado"));

		            //  Creo los objetos
		            TipoSeguro tipo = new TipoSeguro();
		            tipo.setIdTipo(idTipo);

		            Seguro seguro = new Seguro();
		            seguro.setDescripcion(descripcion);
		            seguro.setTipo(tipo);
		            seguro.setCostoContratacion(costoContratacion);
		            seguro.setCostoAsegurado(costoAsegurado);

		            //  Llamo al DAO para insertar en BD
		            DaoSeguro dao = new DaoSeguroImpl();
		            boolean insertado = dao.insertarSeguro(seguro);

		            //  Redirigir según resultado
		            if (insertado) {
		                response.sendRedirect("Exito.jsp");
		            } else {
		                request.setAttribute("error", "No se pudo insertar el seguro.");
		                request.getRequestDispatcher("/AgregarSeguro.jsp").forward(request, response);
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		            request.setAttribute("error", "Error en el alta del seguro.");
		            request.getRequestDispatcher("/AgregarSeguro.jsp").forward(request, response);
		        }
		    }


			}
	
	//-----------------------------------------------------------------------------------------------------
	
	}
