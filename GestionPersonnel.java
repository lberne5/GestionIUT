package gestion_iut;

class GestionPersonnel {
    
    public static final int NB_MAX_PERSONNEL = 200; //nombre maximum de personnel dans l'iut
    public static final int NB_MAX_ABSENCES = 5;    //nombre max d'abscence avant demande de remboursement ou retenue sur salaire
    private static int nbPersonnel = 0; //le nombre de personne dans la liste du personnel
    
    private Personnel listePersonnel[] = new Personnel[NB_MAX_PERSONNEL];   //un tableau contenant les membres du personnel
    
    private static int nbBureau = 0;
    private Bureau listeBureau[] =new Bureau[NB_MAX_PERSONNEL];
    
    /*
    Tous les menu d'affichage dans le terminal
    */
    final public MenuApp menuPrincipal = new MenuApp("GESTIONNAIRE DU PERSONNEL",
                                                    "Ajouter un membre", "Supprimer un membre", "Lister les membres", "Chercher un membre", "Retour");
    final public MenuApp menuAjouter = new MenuApp("AJOUTER DU PERSONNEL",
                                                  "Ajouter", "Retour");
    final public MenuApp menuSupprimer = new MenuApp("SUPPRIMER DU PERSONNEL",
                                                    "Supprimer", "Retour");
    final public MenuApp menuLister = new MenuApp("LISTER DU PERSONNEL",
                                                    "Lister tous les membres","Lister les bureaux", "Lister les administratifs", "Lister tous les enseignants", "Lister les enseignants titulaires", "Lister les enseignants vacataires", "Retour");
    final public MenuApp menuRechercher = new MenuApp("RECHERCHER DU PERSONNEL",
                                                    "Rechercher par nom (modifications indisponibles)", "Rechercher par ID", "Retour");
    final public MenuApp menuModification = new MenuApp("MODIFICATIONS",
                                                        "Changer nom", "Changer prenom","Changer salaire", "Changer prime", "Ajouter absence injustifiee", "Justifier une absence", "Retour");
    
    /**
     * ajoute p a la listePersonnel
     * @param p 
     */
    public void ajouterPersonnel(Personnel p){
        if(nbPersonnel + 1 > NB_MAX_PERSONNEL){
            System.out.println("Impossible d'ajouter le membre du personnel : maximum atteint");
        }
        else {
            p.setId(nbPersonnel+1);
            listePersonnel[nbPersonnel++] = p;
        }
        
        miseAJourBureaux();
    }
    
    /**
     *Mise a jour des bureaux et de leurs utilisateurs 
     */
    public void miseAJourBureaux(){
        nbBureau = 0;
        
        for(int i = 0; i < nbPersonnel; i++){
            if(i%3 == 0){
                listeBureau[nbBureau++] = new Bureau();
                
            }
            listeBureau[nbBureau-1].setNumero((int)Math.floor(i/3) + 1);
            listeBureau[nbBureau-1].ajouterUtilisateur(listePersonnel[i]);
        }
    }
    
    /**
     * affiche les bureaux et leurs utilisateurs
     */
    public void afficherBureaux(){
        for(int i = 0; i < nbBureau; i++){
            System.out.println(i);
            listeBureau[i].afficheUtilisateurs();
        }
    }
    
    /**
     * supprime de la listePersonnel tous les membres ayant pour nom n et prenom p, realloue les id pour chaque entrées
     * @param n
     * @param p 
     */
    public void supprimerPersonnel(String n, String p){
        Personnel nouvelleListe[] = new Personnel[NB_MAX_PERSONNEL];
        int indice = 0;
        
        for(int i = 0; i < nbPersonnel; i++){   //on parcour toute la liste du personnel, on copie tous le personnel ne matchant pas dans une nouvelle liste
            if(!(listePersonnel[i].getNom().equals(n) && listePersonnel[i].getPrenom().equals(p)))
            {
                
                nouvelleListe[indice] = listePersonnel[i];
                nouvelleListe[indice++].setId(nbPersonnel);
            }
        }
        
        listePersonnel = nouvelleListe; //on ecrase la liste du personnel precedente avec la nouvelle
        nbPersonnel = indice;
        
        miseAJourBureaux();
    }
    
    /**
     * renvoie un String contenant le nom de la fonction de p
     * @param p
     * @return nom fonction
     */
    public String getNomFonction(Personnel p){
        if(p instanceof PersonnelAdministratif){return "Administratif";}
        else if(p instanceof PersonnelEnseignantVacataire){return "Ens Vacataire";}
        else if(p instanceof PersonnelEnseignantTitulaire){return "Ens Titulaire";}
        else{return "Personnel";}
    }
    
    /**
     * associe un choix a un string
     * @param choix
     * @return 
     */
    private String getNomMetierDepuisChoix(int choix){
        switch (choix) {
            case 1:
                return "administratif";
            case 2:
                return "enseignant titulaire";
            case 3:
                return "enseignant vacataire";
            default:
                return "";
        }
    }
    
    /**
     * Affichage de la liste de tout le personnel
     */
    public void afficherListePersonnel(){
        System.out.println("Liste des membres du personnel :\nID\tNOM\t\tPRENOM\t\tABSENCES\tFONCTION\tSALAIRE");
        
        for(int i = 0; i < nbPersonnel; i++){
            System.out.println(listePersonnel[i].getId() + "\t" + listePersonnel[i].getNom() +"\t\t"+ listePersonnel[i].getPrenom() +"\t\t"+ listePersonnel[i].getNbAbsInj() +"\t\t"+ getNomFonction(listePersonnel[i]) +"\t" + listePersonnel[i].getSalaire());
        }
        System.out.println("\n");
    }
    
    /**
     * Affichage de la liste des enseignants vacataires
     */
    public void afficherListeEnsVac(){
        System.out.println("Liste des enseignants vacataires :\nID\tNOM\t\tPRENOM\t\tABSENCES\tSALAIRE\tHEURES EFFECTUEES");
        
        for(int i = 0; i < nbPersonnel; i++){
            
            if(listePersonnel[i] instanceof PersonnelEnseignantVacataire){
                System.out.println(listePersonnel[i].getId() + "\t" + listePersonnel[i].getNom() +"\t\t"+ listePersonnel[i].getPrenom() +"\t\t"+ listePersonnel[i].getNbAbsInj() +"\t\t"+ ((PersonnelEnseignantVacataire)listePersonnel[i]).getSalaire() +"\t\t"+ ((PersonnelEnseignant)listePersonnel[i]).getNbHeuresEffectuees());
            }
        }
        System.out.println("\n");
    }
    
    /**
     * Affichage de la liste des enseignants titulaires
     */
    public void afficherListeEnsTit(){
        System.out.println("Liste des enseignants vacataires :\nID\tNOM\t\tPRENOM\t\tABSENCES\tSALAIRE\t\tHEURES EFFECTUEES");
        
        for(int i = 0; i < nbPersonnel; i++){
            
            if(listePersonnel[i] instanceof PersonnelEnseignantTitulaire){
                System.out.println(listePersonnel[i].getId() + "\t" + listePersonnel[i].getNom() +"\t\t"+ listePersonnel[i].getPrenom() +"\t\t"+ listePersonnel[i].getNbAbsInj() +"\t\t"+ ((PersonnelEnseignantTitulaire)listePersonnel[i]) +"\t\t"+ ((PersonnelEnseignant)listePersonnel[i]).getNbHeuresEffectuees());
            }
        }
    }
    
    /**
     * Affichage de la liste de tous les enseignants (vacataires ET titulaires)
     */
    public void afficherListeEnseignant(){
        System.out.println("Liste des enseignants :\nID\tNOM\t\tPRENOM\t\tABSENCES\tSALAIRE\t\tHEURES EFFECTUEES\tFONCTION");
        
        for(int i = 0; i < nbPersonnel; i++){
            
            if(listePersonnel[i] instanceof PersonnelEnseignantVacataire){
                System.out.println(listePersonnel[i].getId() + "\t" + listePersonnel[i].getNom() +"\t\t"+ listePersonnel[i].getPrenom() +"\t\t"+ listePersonnel[i].getNbAbsInj() +"\t\t"+ ((PersonnelEnseignantVacataire)listePersonnel[i]).getSalaire() +"\t\t"+ ((PersonnelEnseignant)listePersonnel[i]).getNbHeuresEffectuees());
            }
            else if(listePersonnel[i] instanceof PersonnelEnseignantTitulaire){
                System.out.println(listePersonnel[i].getId() + "\t" + listePersonnel[i].getNom() +"\t\t"+ listePersonnel[i].getPrenom() +"\t\t"+ listePersonnel[i].getNbAbsInj() +"\t\t"+ ((PersonnelEnseignantTitulaire)listePersonnel[i]) +"\t\t"+ ((PersonnelEnseignant)listePersonnel[i]).getNbHeuresEffectuees());
            }
        }
    }
    
    /**
     * Affichage de la liste des administratifs
     */
    public void afficherListeAdministratif(){
        System.out.println("Liste des administratifs :\nID\tNOM\t\tPRENOM\t\tABSENCES\tSALAIRE");
        
        for(int i = 0; i < nbPersonnel; i++){
            
            if(listePersonnel[i] instanceof PersonnelAdministratif){
                System.out.println(listePersonnel[i].getId() + "\t" + listePersonnel[i].getNom() +"\t\t"+ listePersonnel[i].getPrenom() +"\t\t"+ listePersonnel[i].getNbAbsInj() +"\t\t"+ ((PersonnelAdministratif)listePersonnel[i]));
            }
        }
    }
    
    /**
     * affiche la liste des membres du personnel ayant pour nom n et pour prenom p
     * @param n
     * @param p 
     */
    public void afficherListeRecherchePersonnelNom(String n, String p){
        System.out.println("Personnel ayant pour nom " + n + " " + p + "\n");
        
        System.out.println("Liste du personnel:\nID\tNOM\t\tPRENOM\t\tABSENCES\tFONCTION\tSALAIRE");
        for(int i = 0; i < nbPersonnel; i++){
            if(listePersonnel[i].getNom().equals(n) && listePersonnel[i].getPrenom().equals(p)){
                System.out.println(listePersonnel[i].getId() + "\t" + listePersonnel[i].getNom() +"\t"+ listePersonnel[i].getPrenom() +"\t\t"+ listePersonnel[i].getNbAbsInj() +"\t\t"+ getNomFonction(listePersonnel[i]) +"\t\t"+ listePersonnel[i].getSalaire());
            }
        }
        System.out.println();
    }
    
    /**
     * Affiche la liste des membres du personnel ayant pour ID id
     * @param id 
     */
    public void afficherListeRecherchePersonnelID(int id){
        System.out.println("Liste des membres du personnel :\nID\tNOM\t\tPRENOM\t\tABSENCES\tFONCTION\tSALAIRE");
        for(int i = 0; i < nbPersonnel; i++){
            if(listePersonnel[i].getId() == id){
                System.out.println(listePersonnel[i].getId() + "\t" + listePersonnel[i].getNom() +"\t\t"+ listePersonnel[i].getPrenom() +"\t\t"+ listePersonnel[i].getNbAbsInj() +"\t\t"+ getNomFonction(listePersonnel[i]) +"\t\t"+ listePersonnel[i].getSalaire());
            }
        }
        System.out.println();
        
    }
    
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
            case 1: //ajouter un membre
                boucleMenuAjouter();
                break;
            case 2: //Supprimer un membre
                boucleMenuSupprimer();
                break;
            case 3: //Lister les membre
                boucleMenuLister();
                break;
            case 4: //Chercher un membre
                boucleMenuRechercher();
                break;
            case 5: //retour
                System.out.println("retour !\n");
                break;
        }
    }
    
    /**
     * Boucle du menu d'ajout
     */
    public void boucleMenuAjouter(){
        boolean stop = false;
        
        while(!stop){
            menuAjouter.afficher();
            menuAjouter.saisirOption();

            stop = (menuAjouter.getChoixOption() == menuAjouter.getDerniereOption());

            actionMenuAjouter(menuAjouter.getChoixOption());
        }
    }
    
    /**
     * Determine l'action à faire en fonction de l'option choisie pour le menu d'ajout
     * @param choix 
     */
    public void actionMenuAjouter(int choix){
        switch(choix){
            case 1: //ajouter
                String nom, prenom;
                int metierChoix = -1, salaire = -1;
                
                System.out.print("Veuillez saisir le nom du membre du personnel : ");
                nom = Clavier.lireString();
                System.out.print("\nVeuillez saisir le prenom du membre du personnel : ");
                prenom = Clavier.lireString();
                
                while(metierChoix < 1 || metierChoix > 3){
                    System.out.print("\nQuelle est la fonction du membre :"
                                    + "\nAdministratif (1)"
                                    + "\nEnseignant Titulaire (2)"
                                    + "\nEnseignant Vacataire (3)\n"
                                    + "Veuillez saisir le numero de l'option : ");

                    metierChoix = Clavier.lireInt();
                }
                
                float salaireFixe = -1;
                switch (metierChoix) {
                    case 1:
                        PersonnelAdministratif tempA = new PersonnelAdministratif(nom, prenom);
                        
                        while (salaireFixe < 0) {                            
                            System.out.println("Entrez le salaire fixe de l'employe : ");
                            salaireFixe = Clavier.lireFloat();
                        }
                        tempA.setSalaireFixe(salaireFixe);
                        
                        ajouterPersonnel(tempA);
                        break;
                    case 2:
                        PersonnelEnseignantTitulaire tempET = new PersonnelEnseignantTitulaire(nom, prenom);
                        
                        while (salaireFixe < 0) {                            
                            System.out.println("Entrez le salaire fixe de l'employe : ");
                            salaireFixe = Clavier.lireFloat();
                        }
                        tempET.setSalaireFixe(salaireFixe);
                        
                        ajouterPersonnel(tempET);
                        break;
                        
                    case 3:
                        PersonnelEnseignantVacataire tempEV = new PersonnelEnseignantVacataire(nom, prenom);
                        
                        ajouterPersonnel(tempEV);
                        break;
                }
                break;
            case 2: //retour
                System.out.println("retour !");
                break;
            
        }
    }
    
    
    /**
     * Boucle du menu de suppression
     */
    public void boucleMenuSupprimer(){
        boolean stop = false;
        
        while(!stop){
            menuSupprimer.afficher();
            menuSupprimer.saisirOption();

            stop = (menuSupprimer.getChoixOption() == menuSupprimer.getDerniereOption());   //en cas de retour(derniere option)

            actionMenuSupprimer(menuSupprimer.getChoixOption());
        
        }
    }
    
    /**
     * Determine l'action à faire en fonction de l'option choisie pour le menu de suppression
     * @param choix 
     */
    public void actionMenuSupprimer(int choix){
        switch(choix){
            case 1: //Ajouter
                String nom, prenom;
                boolean certain;
                
                System.out.print("Veuillez saisir le nom du membres du personnel : ");
                nom = Clavier.lireString();
                System.out.print("\nVeuillez saisir le prenom du membres du personnel : ");
                prenom = Clavier.lireString();
                
                System.out.print("Vous allez supprimer TOUS les du membres du personnel ayant ce nom et ce prenom, etes-vous sur? (o/n)");
                certain = (Clavier.lireString().equals("o"));
                
                if(certain){
                    supprimerPersonnel(nom, prenom);
                    System.out.print("\nMembre supprimes.\n");
                }
                else{
                    System.out.print("\nAnnulation... \n");
                }
                break;
                
            case 2: //Retour
                System.out.println("retour !\n");
                break;
        }
    }
    
    /**
     * Boucle du menu de listage
     */
    public void boucleMenuLister(){
        boolean stop = false;
        
        while(!stop){
            menuLister.afficher();
            menuLister.saisirOption();

            stop = (menuLister.getChoixOption() == menuLister.getDerniereOption());

            actionMenuLister(menuLister.getChoixOption());
        }
    }
    
    /**
     * Determine l'action à faire en fonction de l'option choisie pour le menu de listage
     * @param choix 
     */
    public void actionMenuLister(int choix){
        switch(choix){
            case 1: //tous
                afficherListePersonnel();
                break;
                
            case 2: //bureaux
                afficherBureaux();
                break;
            case 3: //administratifs
                afficherListeAdministratif();
                break;
            case 4: //tous les enseignants
                afficherListeEnseignant();
                break;
            case 5: //enseignants titulaires
                afficherListeEnsTit();
                break;
            case 6: //enseignant vacataires
                afficherListeEnsVac();
                break;
            case 7: //retour
                System.out.println("retour !");
                break;
                
                
            
        }
    }
    
    /**
     * boucle du menu de recherche
     */
    public void boucleMenuRechercher(){
        boolean stop = false;
        
        while(!stop){
            menuRechercher.afficher();
            menuRechercher.saisirOption();

            stop = (menuRechercher.getChoixOption() == menuRechercher.getDerniereOption());   //en cas de retour(derniere option)

            actionMenuRechercher(menuRechercher.getChoixOption());
        
        }
    }
    
    /**
     * Determine l'action à faire en fonction de l'option choisie pour le menu de recherche
     * @param choix 
     */
    public void actionMenuRechercher(int choix){
        switch(choix){
            case 1: //Rechercher par nom et prenom
                String nom, prenom;
                
                System.out.print("Veuillez saisir le nom du membre du personnel : ");
                nom = Clavier.lireString();
                System.out.print("\nVeuillez saisir le prenom du membre du personnel : ");
                prenom = Clavier.lireString();
                
                afficherListeRecherchePersonnelNom(nom, prenom);
                
                for(int i = 0; i < nbPersonnel; i++){
                    if(listePersonnel[i].getNom().equals(nom) && listePersonnel[i].getPrenom().equals(prenom)){
                        int id= listePersonnel[i].getId();
                        boucleMenuModification(id);
                    }
        }
                break;
            case 2: //rechercher par id
                int id;
                
                System.out.print("Veuillez saisir l'ID du memebre du personnel : ");
                id = Clavier.lireInt();
                
                if (id > nbPersonnel || id < 1){
                    System.out.println("Aucun membre ne porte cet ID.");
                }
                else{
                    boucleMenuModification(id);
                }
                
                
            case 3: //Retour
                System.out.println("retour !\n");
                break;
        }
    }
    
    
    /**
     * Boucle du menu modification
     * @param id 
     */
    public void boucleMenuModification(int id){
        boolean stop = false;
        
        while(!stop){
            afficherListeRecherchePersonnelID(id);
            menuModification.afficher();
            menuModification.saisirOption();

            stop = (menuModification.getChoixOption() == menuModification.getDerniereOption());   //en cas de retour(derniere option)

            actionMenuModification(menuModification.getChoixOption(), id);
        
        }
    }
   
    /**
     * Determine l'action à faire en fonction de l'option choisie pour le menu de modification
     * @param choix
     * @param id 
     */
    public void actionMenuModification(int choix, int id){
        switch(choix){
            case 1: //Changer nom
                String nom;
                               
                System.out.print("Veuillez saisir le nouveau nom du membre du personnel : ");
                nom = Clavier.lireString();
                
                listePersonnel[id-1].setNom(nom);
                
                System.out.print("Le nom a ete change\n");
                break;
            case 2: //changer prenom
                String prenom;
                               
                System.out.print("Veuillez saisir le nouveau prenom du membre du personnel : ");
                prenom = Clavier.lireString();
                
                listePersonnel[id-1].setPrenom(prenom);
                
                System.out.print("Le prenom a ete change\n");
                break;
            case 3: //changer salaire
                int salaire = -1;
                
                while(salaire < 0) {
                    System.out.println("Veuillez saisir le nouveau salaire : ");
                    salaire = Clavier.lireInt();
                }
                
                listePersonnel[id-1].setSalaire(salaire);
                
                System.out.print("Le salaire a ete change\n");
                break;
                
            case 4: //changer prime
                int prime = -1;
                
                while(prime < 0) {
                    System.out.println("Veuillez saisir la prime : ");
                    prime = Clavier.lireInt();
                }
                
                listePersonnel[id-1].setPrime(prime);
                
                System.out.print("La prime a ete change\n");
                break;
            case 5: //ajouter absence
                listePersonnel[id-1].incrementeAbsInj();
                
                System.out.print("Nombre d'absences injustifiee actualise : " + listePersonnel[id-1].getNbAbsInj() + "\n");
                break;
            case 6: //justifier absence
                listePersonnel[id-1].decrementeAbsInj();
                System.out.print("Nombre d'absences injustifiee actualise : " + listePersonnel[id-1].getNbAbsInj() + "\n");
                break;
                
            case 7: //Retour
                System.out.println("retour !\n");
                break;
        }
    }
}