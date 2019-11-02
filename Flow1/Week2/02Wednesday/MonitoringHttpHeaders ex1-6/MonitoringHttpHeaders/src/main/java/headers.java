/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joe
 */
@WebServlet(urlPatterns = {"/headers"})
public class headers extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet headers</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table style=\"width:100%\" border=1px>");
            
            out.println("<tr>");
            out.println("<th>" + "Header" + "</th>");
            out.println("<th>" + "Value" + "</th>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>" + "Host" + "</td>");
            out.println("<td>" + request.getHeader("host") + "</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>" + "Connection" + "</td>");
            out.println("<td>" + request.getHeader("connection") +  "</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>" + "Cache-control" + "</td>");
            out.println("<td>" + request.getHeader("cache-control") + "</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>" + "Accept" + "</td>");
            out.println("<td>" + request.getHeader("accept") + "</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>" + "User-agent" + "</td>");
            out.println("<td>" + request.getHeader("user-agent") + "</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>" + "Accept-encoding" + "</td>");
            out.println("<td>" + request.getHeader("accept-encoding") + "</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>" + "Accept-language" + "</td>");
            out.println("<td>" + request.getHeader("accept-language") + "</td>");
            out.println("</tr>");
            
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
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
