package gestion_iut;

public class PersonnelEnseignantVacataire extends PersonnelEnseignant{
    
    /**Redefinition des constructeurs de la classe mere.*/
    PersonnelEnseignantVacataire(){
        super();
        setNbHeuresEffectuees(0);
    }
    
    PersonnelEnseignantVacataire(String n, String p){
        super(n, p);
        setNbHeuresEffectuees(0);
    }
        
    PersonnelEnseignantVacataire(String n, String p, float pr){
        super(n, p, pr);
    }
  
    
    @Override
    public float getSalaire(){
       return getTarifHoraire() * getNbHeuresEffectuees();
    }
    
}
