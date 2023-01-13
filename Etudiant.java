package gestion_iut;

/**
 * Etudiant Classe contient des constructeurs qui ont des différentes paramètres
 * @param n Nombre de personne (paramètre d'entré)
 * @param p Prenom de personne (paramètre d'entré)
 * @param nbAb Nombre d'absence (paramètre d'entré)
 * @param bourse Si l'étudiant a un boursier(e) (paramètre d'entre) 
 */
public class Etudiant extends Personne{
    private int id;
    
    Etudiant(){
        super();
    }
    
    Etudiant(String n, String p){
        super(n, p);
        
    }
    
    Etudiant(String n, String p, int nbAb){
        super(n, p, nbAb);
    }
    
    Etudiant(String n, String p, int nbAb, boolean bourse){
        super(n, p, nbAb);
    }
    
    /**
    * Méthode setId prend l'integer value et affectue au attribut id 
    * @param value nombre d'attribut id (paramètre d'entré)
    */
    public void setId(int value){id = value;}
    
    /**
    * Méthode getId retourne l'id d'étudiant 
    * @return l'id d'étudiant 
    */
    public int getId(){return id;}
    
}
