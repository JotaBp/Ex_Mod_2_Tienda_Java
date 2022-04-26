package vistas.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Producto;
import negocio.Tienda;
import negocio.TiendaImpl;


public class ControllerListaProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private Tienda tienda;
    
    public ControllerListaProductos() {
    	this.tienda = new TiendaImpl();    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<Producto> productos = tienda.getProductos();
		request.setAttribute("prods", productos);
		request.getRequestDispatcher("/WEB-INF/vistas/listado_productos.jsp").forward(request, response);
	}


}
