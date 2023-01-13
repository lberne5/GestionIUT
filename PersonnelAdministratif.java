package gestion_iut;

public class PersonnelAdministratif extends Personnel implements Primable {

    PersonnelAdministratif() {
        super();
    }

    PersonnelAdministratif(String n, String p) {
        super(n, p);
        salaireFixe =0;
    }
    PersonnelAdministratif (String n, String p, int nbAbsInj){
        super(n, p, nbAbsInj);
        salaireFixe =0;

    }
    PersonnelAdministratif(String n, String p, float pr) {
        super(n, p, pr);
        salaireFixe =0;
    }
    PersonnelAdministratif (String n, String p, int nbAbsInj, float pr){
        super(n, p, nbAbsInj, pr);
        salaireFixe =0;
    }
   
    public float getSalaireFixe(){
        return salaireFixe;
    }
    public void setSalaireFixe(float val){
        salaireFixe = val; 
    }
    
    @Override
    public void setMontantPrime(float val) {
        if (val == 0 || val < 0) {
            System.out.println("Aucune prime n'a ete attribuee a l'administratif. Le montant d'une prime ne peut pas etre nulle ou négative.");
        } else {
            if (prime == false) {
                prime = true;
            }
            montantPrime = val;
        }
    }

    @Override
    public void enleverPrime() {
        if (prime == false) {
            System.out.println("Cet administratif n'avait pas de prime.");
        } else {
            prime = false;
            montantPrime = 0f;
        }
    }

    @Override
    public float getSalaire() {
        if (getNbAbsInj() == 0) {
            if ( prime == false)
                return salaireFixe;
            else
                return salaireFixe + montantPrime;
        } else {

            if(prime == false){
                setSalaire(salaireFixe * ((getNbAbsInj() * salaireFixe) / nbJoursOuvresParAn));
            }else{
                setSalaire(montantPrime +  salaireFixe * ((getNbAbsInj() * salaireFixe) / nbJoursOuvresParAn));
            }
            return super.getSalaire();
        }
    }
    private float salaireFixe;
    private final float nbJoursOuvresParAn = 228; // nombre de jours où l'PersonnelAdministratif va devoir travailler en un, si il pose tous ses jours de congés
    private boolean prime = false;
    private float montantPrime = 0;
}
