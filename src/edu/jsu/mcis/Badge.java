package edu.jsu.mcis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ayste
 */
public class Badge {
    private String badgeId;
    private String description;
    
    public Badge() {
        badgeId= "";
        description= "";
    }
    
    public Badge(String badgeid, String desc) {
        this.badgeId = badgeid;
        this.description = desc;
    }
    
    public String getBadgeId(){
        return badgeId;
        
    }
   public String getDescription(){
       return description;
   }
   
   public void setBadgeId(String badgeId){
       this.badgeId = badgeId;
   }
   public void setDescription(String description){
       this.description = description;
   }
   
   public String toString() {
       return ("#" + badgeId + " (" + description + ")");
   }
    
 }

    

