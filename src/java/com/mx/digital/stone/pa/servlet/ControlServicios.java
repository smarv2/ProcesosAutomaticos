/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.digital.stone.pa.servlet;

import com.mx.digital.stone.pa.EnviaCorreoFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author smarv
 */
public class ControlServicios extends HttpServlet {
    
    protected static final Logger LOG = LogManager.getLogger(ControlServicios.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String StrAction = "3"; ///nada en caso contrario
        String StrIDProcess = "0"; //ninguno
        PrintWriter out = response.getWriter();

        if (request.getParameter("Action") != null) {
            StrAction = request.getParameter("Action").toString();
        }
        if (request.getParameter("IDProcess") != null) {
            StrIDProcess = request.getParameter("IDProcess").toString();
        }
        
        if (StrIDProcess.compareToIgnoreCase("1") == 0) {
            EnviaCorreoFacade enviaCorreoFacade = new EnviaCorreoFacade();
            // Genera Reportes
            if (StrAction.compareToIgnoreCase("1") == 0) {
                LOG.info(" --------------------------------------- SE INICIA ENVIO DE CORREOS - P/A --------------------------------------- ");
                enviaCorreoFacade.Start();
            }
            if (StrAction.compareToIgnoreCase("0") == 0) {
                LOG.info(" --------------------------------------- SE DETIENE ENVIO DE CORREOS - P/A --------------------------------------- ");
                enviaCorreoFacade.Stop();
            }
        }

        out.print("<HTML><body><script>location.href='index.jsp';window.close()</script></body></html>");
        
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
