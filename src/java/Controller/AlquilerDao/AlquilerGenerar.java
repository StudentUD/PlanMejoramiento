package Controller.AlquilerDao;

import Controller.AlquilerDao.Alquiler;
import Controller.AlquilerDao.AlquilerDAO;
import Modelo.Carrito;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AlquilerGenerar extends HttpServlet {

    AlquilerDAO adao = new AlquilerDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            int precio_alquiler = Integer.parseInt(request.getParameter("precio_alquiler"));
            String fecha_entrega = request.getParameter("fecha_entrega");
            String fecha_devolucion = request.getParameter("fecha_devolucion");
            String numero_documento = request.getParameter("numero_documento");
            

            HttpSession sesion = request.getSession(true);
            ArrayList<Carrito> lista = sesion.getAttribute("carrito") == null ? null : (ArrayList) sesion.getAttribute("carrito");

            Alquiler alquiler = new Alquiler(precio_alquiler, fecha_entrega, fecha_devolucion, numero_documento, precio_alquiler, lista);
            
            int res = adao.generarAlquiler(alquiler);
            
            if(res != 0){
                sesion.setAttribute("carrito", null);
                response.sendRedirect("menuUsuario.jsp");
            }else{
                response.sendRedirect("carrito.jsp");
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
