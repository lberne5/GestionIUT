package gestion_iut;


public class GestionIUT {
    public GestionEtudiant gestionnaireEtu = new GestionEtudiant();
    public GestionPersonnel gestionnairePerso = new GestionPersonnel();
    
    public MenuApp menuPrincipal= new MenuApp("GESTIONNAIRE IUT",
                                "Gestion des etudiants", "Gestion du personnel", "Sortir");
    
    /**
     * Boucle du menu principal
     */
    public void boucleMenuPrincipal(){
        boolean stop = false;
        
        while(!stop){
            menuPrincipal.afficher();
            menuPrincipal.saisirOption();

            stop = (menuPrincipal.getChoixOption() == menuPrincipal.getDerniereOption());

            actionMenuPrincipal(menuPrincipal.getChoixOption());
        }
    }
    
    /**
     * Determine l'action à faire en fonction de l'option choisie pour le menu principal 
     * @param choix 
     */
    public void actionMenuPrincipal(int choix){
        switch(choix){
            case 1: //Gestion des etudiants
                gestionnaireEtu.boucleMenuPrincipal();
                break;
            case 2: //Gestion du personnel
                gestionnairePerso.boucleMenuPrincipal();
                break;
            case 3: //Retour
                System.out.println("Sortie !");
                
        }
    }
    
}
