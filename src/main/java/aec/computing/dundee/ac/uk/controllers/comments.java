/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aec.computing.dundee.ac.uk.controllers;

import aec.computing.dundee.ac.uk.models.comment;
import aec.computing.dundee.ac.uk.stores.commentStore;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import uk.ac.dundee.computing.aec.lib.Dbutils;

/**
 *
 * @author andy
 */
@WebServlet(name = "comments", urlPatterns = {"/comments"},
        initParams = {
            @WebInitParam(name = "data-source", value = "jdbc/Faultdb")
        })


public class comments extends HttpServlet {
    LinkedList<commentStore> log=new LinkedList<commentStore>();
    private static final long serialVersionUID = 1L;
    private DataSource _ds = null;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        Dbutils db = new Dbutils();
        commentStore clog= new commentStore();
            clog.setComment("init comments" );
            log.add(clog);
        _ds = db.assemble(config,log);
        
       

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
        commentStore clog= new commentStore();
            clog.setComment("do get" );
            log.add(clog);
        comment cc = new comment();
        cc.setDatasource(_ds);
        LinkedList<commentStore> psl = cc.getComments();
        request.setAttribute("logs", log);
        request.setAttribute("comments", psl); //Set a bean with the list in it
        
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");

        rd.forward(request, response);

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
