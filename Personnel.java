package gestion_iut;

public abstract class Personnel extends Personne{

    Personnel(){
        super();
        salaire = 0;
        prime = 0;
        numeroBureau = -1;
    }
    
    Personnel(String n, String p){
        super(n, p);

        salaire = 0;
        prime = 0;
    }
    
    
    Personnel (String n, String p, int nbAbsInj){
        super (n, p, nbAbsInj);
        salaire = 0;
        prime = 0;
        numeroBureau = -1;
    }

    Personnel(String n, String p, float pr){
        super(n, p);
        salaire = 0;
        prime = pr;
        numeroBureau = -1;
    }
    Personnel (String n, String p, int nbAbsInj, float pr){
        super(n, p);
        salaire = 0;
        prime = pr;
        numeroBureau = -1;
    }
    /**
    * Accesseur de la variable salaire. 
    * @return salaire
    */
    public float getSalaire(){
        return salaire;
    }
    /**
     * Mutateur de la variable salaire
     * @param val 
     */
    public void setSalaire(float val){
        salaire = val;
    }
    
    /**
     * Accesseur de la variable prime
     * @return prime
     */
    public float getPrime(){
        return prime;
    }
    
    /**
     * Mutateur de la variable prime
     * @param val 
     */
    public void setPrime(float val){
        prime = val;
    }
    
    /**
     * Accesseur de la variable numeroBureau
     * @return numero de bureau auquel siege le membre du personnel
     */
    public int getNumeroBureau(){
        return numeroBureau;
    }
    
    /**
     * Mutateur de la variable numeroBureau
     * @param val 
     */
    public void setNumeroBureau(int val){ 
        numeroBureau = val;
    }
    
    private float salaire;  //le montant du salaire annuel
    private float prime;    //le montant de la prime
    private int numeroBureau;   //numero de bureau, -1 signifie qu'aucun bureau n'est encore assigné a ce membre du personnel
    
}
