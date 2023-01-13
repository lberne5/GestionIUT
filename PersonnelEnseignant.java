package gestion_iut;

public abstract class PersonnelEnseignant extends Personnel{
    
    PersonnelEnseignant(){
        super();
        nbHeuresEffectuees = 0;
    }
    
    PersonnelEnseignant(String n, String p){
        super(n, p);
        nbHeuresEffectuees = 0;
    }


    PersonnelEnseignant (String n, String p, int nbAbsInj){
        super(n, p, nbAbsInj);
        nbHeuresEffectuees = 0;
    }    
    PersonnelEnseignant (String n, String p, float pr){
        super(n, p, pr);
        nbHeuresEffectuees = 0;

    }

    PersonnelEnseignant (String n, String p, int nbAbsInj, float pr){
        super(n, p, nbAbsInj, pr);
        nbHeuresEffectuees = 0;
    }
         
    /**
     * Accesseur de la variable nbHeuresEffectuees
     * @return le temps travaillé par l'enseignant (en heures)
     */
    public float getNbHeuresEffectuees(){
        return nbHeuresEffectuees;
    }
    
    /**
     * Mutateur de la variable nbHeuresEffectuees
     * @param tpsTrav 
     */
    public void setNbHeuresEffectuees(float tpsTrav){
        nbHeuresEffectuees = tpsTrav;
    }
    
    public void ajouterNbHeuresEffectuees (float nbHeureAjouter){
        nbHeuresEffectuees+= nbHeureAjouter; 
    }
    /**
     *Accesseur de la variable tarifHoraire
     * @return le tarif horaire des enseignents (42€/ heure).
     */
    public float getTarifHoraire(){
        return tarifHoraire;
    }

    private float nbHeuresEffectuees;   //temps travaillé par l'enseignant
    private final float tarifHoraire = 42f; // tarif horaire commun aux enseignants pour calcul heures sup titulaire & salaire vacataire
    


}
