package gestion_iut;


public abstract class Personne {
    private String nom; //le nom de la personne
    private String prenom;  //le prenom de la personne
    private int nbAbsInj;  //nombre d'absence injustifiées de la personne, sert aussi bien aux etudiants qu'au personnel
    private int id;
    Personne(){
        nom = "NoName";
        prenom = "NoName";
        nbAbsInj = 0;
    }
    
    Personne(String n, String p){
        nom = n;
        prenom = p;
        nbAbsInj = 0;
    }
    
    Personne(String n, String p, int nb){
        nom = n;
        prenom = p;
        nbAbsInj = nb;
    }
    
    /**
     * Accesseur de la variable nom
     * @return nom
     */
    public String getNom(){
        return nom;
    }
    
    /**
     * Mutateur de la variable nom
     * @param n 
     */
    public void setNom(String n){
        nom = n;
    }
    
    /**
     * Accesseur de la variable prenom
     * @return prenom
     */
    public String getPrenom(){
        return prenom;
    }
    
    /**
     * Mutateur de la variable prenom
     * @param p 
     */
    public void setPrenom(String p){
        prenom = p;
    }
    
    /**
     * Accesseur de la variable nbAbsInj
     * @return le nombre d'absence injustifiées
     */
    public int getNbAbsInj(){
        return nbAbsInj;
    }
    
    /**
     * Mutateur de la variable nbAbsInj
     * @param nb 
     */
    public void setNbAbsInj(int nb){
        nbAbsInj = nb;
    }
    
    /**
     * Incremente la varaible nbAbsInj
     */
    public void incrementeAbsInj(){
        nbAbsInj += 1;
    }
     /**
     * Decremente la varaible nbAbsInj
     */ 
    public void decrementeAbsInj(){
        nbAbsInj -= 1;
    }
    
    public void setId(int value){id = value;}
    public int getId(){return id;}
}
