
package gestion_iut;

/**
 * Classe EtudiantBoursier contient des constructeurs qui ont des différentes paramètres
 * @param n Nombre de personne (paramètre d'entré)
 * @param p Prenom de personne (paramètre d'entré)
 * @param nbAb Nombre d'absence (paramètre d'entré)
 * @param montant Montant de bourse (paramètre d'entre) 
 */
public class EtudiantBoursier extends Etudiant{
    private double montantBourse;
    
    EtudiantBoursier(){
        super();
        montantBourse = 0;
    }
    
    EtudiantBoursier(String n, String p){
        super(n , p, 0);
        montantBourse = 0;
    }
    
    EtudiantBoursier(String n, String p, int nbAb){
        super(n, p, nbAb);
        montantBourse = 0;
    }
    
    EtudiantBoursier(String n, String p, int nbAb, double montant){
        super(n, p, nbAb);
        montantBourse = montant;
    }
    
    /**
     * Méthode getMontantBourse() est l'accesseur de la variable montantBourse
     * @return le montant de la bourse touchée par l'étudiant
     */
    public double getMontantBourse(){
        return montantBourse;
    }
    
    /**
     * Méthode setMontantBourse est un mutateur de la variable montantBourse
     * @param montant est un paramètre d'entré que l'utilisateur saisit
     */
    public void setMontantBourse(double montant){
        if(montant>=0){
            montantBourse = montant;
        } else{
            System.out.println("Le bourse ne peut pas etre negatif.");
        }
        
    }
    
    /**
     * Méthode addMontantBourse ajoute un montant à la variable montantBourse
     * @param montant est un paramètre d'entré pour ajouter sur le montantBourse
     */
    public void addMontantBourse(double montant){
        montantBourse += montant;
    }
    
}
