package edu.jsu.mcis;

/**
 *
 * @author Ayste
 */
public class Badge {
    private String badgeId;
    private String description;
    
    /**
     * Creates Badge object
     */
    public Badge() {
        badgeId= "";
        description= "";
    }
    
    /**
     *
     * @param badgeid
     * @param desc
     */
    public Badge(String badgeid, String desc) {
        this.badgeId = badgeid;
        this.description = desc;
    }
    
    /**
     *
     * @return badgeId
     */
    public String getBadgeId(){
        return badgeId;
        
    }

    /**
     *
     * @return Description
     */
    public String getDescription(){
       return description;
   }
   
    /**
     *
     * @param badgeId
     */
    public void setBadgeId(String badgeId){
       this.badgeId = badgeId;
   }

    /**
     *
     * @param description
     */
    public void setDescription(String description){
       this.description = description;
   }
   
    @Override
   public String toString() {
       return ("#" + badgeId + " (" + description + ")");
   }
    
 }