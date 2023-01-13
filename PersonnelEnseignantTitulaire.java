package gestion_iut;

public class PersonnelEnseignantTitulaire extends PersonnelEnseignant implements Primable {

    PersonnelEnseignantTitulaire() {
        super();
        setNbHeuresEffectuees(0);
        salaireFixe = 0;

    }

    PersonnelEnseignantTitulaire(String n, String p) {
        super(n, p);
        setNbHeuresEffectuees(0);
        salaireFixe = 0;
    }

    PersonnelEnseignantTitulaire(String n, String p, float pr) {
        super(n, p, pr);
        salaireFixe = 0;
    }
    PersonnelEnseignantTitulaire(String n, String p, float pr, float tpsTrav) {
        super(n, p, pr);
        setNbHeuresEffectuees(tpsTrav);
        nbHeuresStatutaires = 0;
        salaireFixe = 0;
    }
    PersonnelEnseignantTitulaire(String n, String p, float pr, float tpsTrav, float tpsATrav) {
        super(n, p, pr);
        setNbHeuresEffectuees(tpsTrav);
        nbHeuresStatutaires = tpsATrav;
        salaireFixe = 0;
    }

    public float getSalaireFixe() {
        return salaireFixe;
    }

    public void setSalaireFixe(float val) {
        salaireFixe = val;
    }

    /**
     * Accesseur de la variable nbHeuresStatutaires
     *
     * @return le temps total a travailler de l'enseignant
     */
    public float getNbHeuresStatutaires() {
        return nbHeuresStatutaires;
    }

    /**
     * Mutateur de la variable nbHeuresStatutaires
     *
     * @param val , le nombre d'heures que l'enseignant doit effectuer dans
     * l'année
     */
    public void setNbHeuresStatutaires(int val) {
        while (val < 64 || val > 384) {
            System.out.println("La valeur saisie n'est pas valide. Merci de saisir une valeur comprise entre 64 et 384.");
            val = Clavier.lireInt();
        }
        nbHeuresStatutaires = val;
    }

    @Override
    public void setMontantPrime(float val) {
        if (val == 0 || val < 0) {
            System.out.println("Aucune prime n'a ete attribuee a l'enseignent. Le montant d'une prime ne peut pas etre nulle ou négative.");
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
            System.out.println("Cet enseignant n'avait pas de prime.");
        } else {
            prime = false;
            montantPrime = 0f;
        }
    }

    @Override
    public float getSalaire() {

        if (getNbHeuresEffectuees() < nbHeuresStatutaires) {
            float nbHeuresAEnlever = nbHeuresStatutaires - getNbHeuresEffectuees();
            setSalaire(salaireFixe - (nbHeuresAEnlever * getTarifHoraire()));
        } else {
            if ((getNbHeuresEffectuees() == nbHeuresStatutaires) || (getNbHeuresEffectuees() > nbHeuresStatutaires && nbHeuresStatutaires < 192)) {
                setSalaire(salaireFixe);
            }

            if (getNbHeuresEffectuees() > nbHeuresStatutaires && nbHeuresStatutaires >= 192) {
                float heuresSupplementaires = getNbHeuresEffectuees() - nbHeuresStatutaires;
                setSalaire(salaireFixe + (getTarifHoraire() * heuresSupplementaires));
            }
        }
        if (prime) {
            return super.getSalaire() + montantPrime;
        } else {
            return super.getSalaire();
        }
    }
    private float nbHeuresStatutaires, salaireFixe;
    private float montantPrime = 0;
    private boolean prime = false;
}
