/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aec.computing.dundee.ac.uk.controllers;

import aec.computing.dundee.ac.uk.models.comment;
import aec.computing.dundee.ac.uk.stores.commentStore;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
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
@MultipartConfig
public class comments extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DataSource _ds = null;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        Dbutils db = new Dbutils();
        _ds = db.assemble(config);

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
        comment cc = new comment();
        cc.setDatasource(_ds);
        LinkedList<commentStore> psl = cc.getComments();
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
        final Part filePart = request.getPart("file");

        System.out.println("Part Name " + filePart.getName());

        String type = filePart.getContentType();
        String filename = filePart.getSubmittedFileName();

        InputStream is = request.getPart(filePart.getName()).getInputStream();
        int i = is.available();

        if (i > 0) {
            byte[] b = new byte[i + 1];
            is.read(b);
            System.out.println("Length : " + b.length);
            ByteBuffer buffer = ByteBuffer.wrap(b);
            int length = b.length;
            String relativeWebPath = "/";
            ServletContext sc= request.getServletContext();
String absoluteDiskPath = sc.getRealPath(relativeWebPath);
            //The following is a quick and dirty way of doing this, will fill the disk quickly !
            Boolean success = (new File(absoluteDiskPath+"Pics")).mkdirs();
            FileOutputStream output = new FileOutputStream(new File(absoluteDiskPath+"Pics/" + filename));
            output.write(b);

            is.close();
        }

        String Comment = request.getParameter("comment");
        comment cc = new comment();
        cc.setDatasource(_ds);
        cc.SaveComment(Comment,filename);
        LinkedList<commentStore> psl = cc.getComments();
        request.setAttribute("comments", psl); //Set a bean with the list in it
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
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
