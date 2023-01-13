package gestion_iut;

public class test {
        
        public static void main(String[] args) {
        GestionIUT gestionnaireIUT = new GestionIUT();
        Personnel p = new PersonnelEnseignantVacataire("caca", "man");
        p.setNbAbsInj(10);
        
        gestionnaireIUT.gestionnairePerso.ajouterPersonnel(new PersonnelEnseignantTitulaire("mister","freeze"));
        gestionnaireIUT.gestionnairePerso.ajouterPersonnel(p);
        
        gestionnaireIUT.boucleMenuPrincipal();
        
        gestionnaireIUT.gestionnairePerso.afficherBureaux();
    }
}