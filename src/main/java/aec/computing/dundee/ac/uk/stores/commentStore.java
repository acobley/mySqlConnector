/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aec.computing.dundee.ac.uk.stores;

/**
 *
 * @author andy
 */
public class commentStore {
    private String comment=null;
    private String pic=null;
    public commentStore(){
        
    }
    
    public void setComment(String comment){
        this.comment=comment;
    }
    public void setPic(String Pic){
        this.pic=Pic;
    }
    
    public String getComment(){
        return comment;
    }
    
    public String getPic(){
        return pic;
    }
}
