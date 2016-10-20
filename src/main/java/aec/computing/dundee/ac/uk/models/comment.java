/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aec.computing.dundee.ac.uk.models;

import aec.computing.dundee.ac.uk.stores.commentStore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import javax.sql.DataSource;

/**
 *
 * @author andy
 */
public class comment {

    private DataSource _ds = null;

    public comment() {

    }

    public void setDatasource(DataSource _ds) {
        this._ds = _ds;
        System.out.println("Set Data Source in Model" + _ds.toString());
    }

    public LinkedList<commentStore> getComments() {
        LinkedList<commentStore> psl = new LinkedList<commentStore>();
        Connection Conn;
        commentStore ps = null;
        ResultSet rs = null;
        try {
            Conn = _ds.getConnection();
        } catch (Exception et) {

            System.out.println("No Connection in comment Model");
            return null;
        }

        Statement stmt = null;
        String sqlQuery = "select * from comments.comment";
        System.out.println("comment Query " + sqlQuery);
        try {
            try {
                // pmst = Conn.prepareStatement(sqlQuery);
                stmt = Conn.createStatement();
            } catch (Exception et) {
                System.out.println("Can't create prepare statement");
                return null;
            }
            System.out.println("Created prepare");
            try {
                // rs=pmst.executeQuery();
                rs = stmt.executeQuery(sqlQuery);
            } catch (Exception et) {
                System.out.println("Can not execut query " + et);
                return null;
            }
            System.out.println("Statement executed");
            if (rs.wasNull()) {
                System.out.println("result set was null");
            } 
            while (rs.next()) {
                System.out.println("Getting RS");
                ps = new commentStore();
                ps.setComment(rs.getString("comment"));
                ps.setPic(rs.getString("Picture"));
                
                psl.add(ps);
            }
        } catch (Exception ex) {
            System.out.println("Opps, error in query " + ex);
            return null;
        }

        try {

            Conn.close();
        } catch (Exception ex) {
            return null;
        }
        return psl;

    }

    public void SaveComment(String comment,String filename) {
        Connection Conn;

        try {
            Conn = _ds.getConnection();
        } catch (Exception et) {

            System.out.println("No Connection in comment Model");
            return;
        }
        String sqlQuery = "Insert into comments.comment (comment,picture) values ('" + comment + "','"+filename+"')";
        System.out.println("comment Query " + sqlQuery);

        try {
            // pmst = Conn.prepareStatement(sqlQuery);
            Statement  stmt = Conn.createStatement();
            stmt.execute(sqlQuery);
        } catch (Exception et) {
            System.out.println("Can't execute insert "+et );
            return;
        }
        try {
            Conn.close();
        } catch (Exception ex) {
            return;
        }
        return;

    }
}
