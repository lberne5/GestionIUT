package gestion_iut;

public class GestionEtudiant {
    public static final int NB_MAX_ETUDIANTS = 200;
    public static final int NB_MAX_ABSENCES = 5;
    private static int nbEtudiants = 0;
    private String montantBourseAjouter;
    
    private Etudiant listeEtudiants[] = new Etudiant[NB_MAX_ETUDIANTS];
    
    final public MenuApp menuPrincipal = new MenuApp("GESTIONNAIRE DES ETUDIANTS",
                                                    "Ajouter un etudiant", "Supprimer un etudiant", "Lister les etudiants", "Chercher un etudiant", "Retour");
    final public MenuApp menuListe = new MenuApp("LISTER DES ETUDIANTS",
                                                "Lister tous les etudiants", "Lister les etudiants boursiers", "Lister les etudiants non boursiers", "Lister les etudiant ayant moins de 5 absences", "Lister les etudiants ayant 5 absences ou plus", "Retour");
    final public MenuApp menuAjouter = new MenuApp("AJOUTER UN ETUDIANT",
                                                  "Ajouter", "Retour");
    final public MenuApp menuSupprimer = new MenuApp("SUPPRIMER UN ETUDIANT",
                                                    "Supprimer", "Retour");
    final public MenuApp menuRechercher = new MenuApp("RECHERCHER DES ETUDIANTS",
                                                    "Rechercher par nom (modifications indisponibles)", "Rechercher par ID", "Retour");
    final public MenuApp menuModification = new MenuApp("MODIFICATIONS",
                                                        "Changer nom", "Changer prenom", "Ajouter absence injustifiee", "Justifier une absence","Modifier le bourse", "Retour");
    
    /**
     * Méthode ajouterEtudiant qui ajoute un etudiant dans le gestionnaire, affiche un message d'erreur si le gestionnaire est plein
     * @param e détails d'étudiant saisit par l'utilisateur
     */
    public void ajouterEtudiant(Etudiant e){
        if(nbEtudiants + 1 > NB_MAX_ETUDIANTS){
            System.out.println("Impossible d'ajouter l'étudiant : maximum atteint");
        }
        else {
            e.setId(nbEtudiants+1);
            listeEtudiants[nbEtudiants++] = e;
        }
    }
    
     /**
     * Méthode supprimerEtudiant qui enleve un etudiant dans le gestionnaire
     * @param n nom d'étudiant saisit par l'utilisateur (paramètre d'entré)
     * @param p prenom d'étudiant saisit par l'utilisateur (paramètre d'entré)
     */
    public void supprimerEtudiant(String n, String p){
        Etudiant nouvelleListe[] = new Etudiant[NB_MAX_ETUDIANTS];
        int indice = 0;
        
        for(int i = 0; i < nbEtudiants; i++){   //on parcour toute la liste d'etudiants, on copie tous les etudiant ne matchant pas dans une nouvelle liste
            if(!(listeEtudiants[i].getNom().equals(n) && listeEtudiants[i].getPrenom().equals(p)))
            {
                
                nouvelleListe[indice] = listeEtudiants[i];
                nouvelleListe[indice++].setId(nbEtudiants);
            }
        }
        
        listeEtudiants = nouvelleListe; //on ecrase la liste d'etudiant precedente avec la nouvelle
        nbEtudiants = indice;
    }
    
    /**
     * Méthode estBoursier vérifie si l'étudiant est un objet de classe EtudiantBoursier
     * si e est de la classe EtudiantBoursier : true, false sinon
     * @param e etudiant dans la liste d'étudiant (paramètre d'entré)
     * @return true si étudiant est un objet de classe EtudiantBoursier, sinon false
     */
    public boolean estBoursier(Etudiant e){
        return e instanceof EtudiantBoursier;
    }
    
    
    /**
     * Méthode estTropAbsent teste si l'etudiant e est trop absent pour valider son année: true si trop absent, false sinon
     * @param e etudiant dans la liste d'étudiant (paramètre d'entré)
     * @return true si l'étudiant a plus d'absence que le maximum absence injustifié (5)
     */
    public boolean estTropAbsent(Etudiant e){
        return e.getNbAbsInj() > NB_MAX_ABSENCES;
    }
    
    /**
     * Méthode doitRembourserBourse verifie si le nombre d'absence est supérieure de 5
     * @param e est un paramètre d'entré pour un étudiant boursier
     * @return true si l'élève a plus de 5 absence, sinon elle return false
     */
    public boolean doitRembourserBourse(EtudiantBoursier e){
        if(estTropAbsent(e)){
            return true;
        }    
        
        return false;
    }
    /**
     * Méthode afficheListeEtudiants affiche la liste d'étudiants par leur nom, leur prénom, leurs absences, et s'il est validé l'année.
     * De plus, la méthode montre si l'étudiant est boursier, le montant de son bourse et s'il a besoin de rembourser le bourse.
     */
    public void afficheListeEtudiants(){
        System.out.println("Liste des etudiants :\nID\tNOM\t\tPRENOM\t\tABSENCES\t\tVALIDE?\t\tBOURSIER?\tBOURSE (en euros)\tDoit Rembourser");
        for(int i = 0; i < nbEtudiants; i++){
            if(estBoursier(listeEtudiants[i])){
                System.out.println(listeEtudiants[i].getId() + "\t" + listeEtudiants[i].getNom() +"\t\t"+ listeEtudiants[i].getPrenom() +"\t\t"+ 
                        listeEtudiants[i].getNbAbsInj() +"\t\t\t"+ (estTropAbsent(listeEtudiants[i])? "NON":"OUI") +"\t\t"+ (estBoursier(listeEtudiants[i])?"OUI":"NON")+
                        "\t\t"+ ((EtudiantBoursier)listeEtudiants[i]).getMontantBourse()+ "\t\t\t"+ (doitRembourserBourse((EtudiantBoursier)listeEtudiants[i])?"OUI":"NON"));
            } else {
                System.out.println(listeEtudiants[i].getId() + "\t" + listeEtudiants[i].getNom() +"\t\t"+ listeEtudiants[i].getPrenom() +"\t\t"+ 
                        listeEtudiants[i].getNbAbsInj() +"\t\t\t"+ (estTropAbsent(listeEtudiants[i])? "NON":"OUI") +"\t\t"+ (estBoursier(listeEtudiants[i])?"OUI":"NON")+ 
                        "\t\t"+ "---"+ "\t\t\t"+ "---");
            }    
        }
        System.out.println("\n");
    }
    
    /**
     * Méthode afficheListeEtudiantsValides affiche la liste des étudiant avec 5 absences ou moins
     */
    public void afficheListeEtudiantsValides(){
    System.out.println("Etudiants validant :\nID\tNOM\t\tPRENOM");
        for(int i = 0; i < nbEtudiants; i++){
            if(!estTropAbsent(listeEtudiants[i])){
                System.out.println(listeEtudiants[i].getId() + "\t" + listeEtudiants[i].getNom() +"\t\t"+ listeEtudiants[i].getPrenom());
            }
        }
        System.out.println("\n");
    }
    
    /**
     * Méthode afficheListeEtudiantsNonValides affiche la liste des étudiants avec strictement plus de 5 absences
     */
    public void afficheListeEtudiantsNonValides(){
        System.out.println("Etudiants non validant :\nID\tNOM\t\tPRENOM");
        for(int i = 0; i < nbEtudiants; i++){
            if(estTropAbsent(listeEtudiants[i])){
                System.out.println(listeEtudiants[i].getId() + "\t" + listeEtudiants[i].getNom() +"\t\t"+ listeEtudiants[i].getPrenom());
            }
        }
        System.out.println("\n");
    }
    
    /**
     * Méthode afficheListeEtudiantsBoursiers affiche la liste des étudiants boursiers
     */
    public void afficheListeEtudiantsBoursiers(){
        System.out.println("Etudiants boursiers :\nID\tNOM\t\tPRENOM\t\tBourse(en euros)\tDoit Rembourser");
        for(int i = 0; i < nbEtudiants; i++){
            if(estBoursier(listeEtudiants[i])){
                System.out.println(listeEtudiants[i].getId() + "\t" + listeEtudiants[i].getNom() +"\t\t"+ listeEtudiants[i].getPrenom()+"\t\t"+ ((EtudiantBoursier)listeEtudiants[i]).getMontantBourse()+"\t\t\t"+ (doitRembourserBourse((EtudiantBoursier)listeEtudiants[i])?"OUI":"NON"));
            }
        }
        System.out.println("\n");
    }
    
    /**
     * Méthode afficheListeEtudiantsNonBoursiers affiche la liste des étudiants non boursiers 
     */
    public void afficheListeEtudiantsNonBoursiers(){
        System.out.println("Etudiants non boursiers :\nID\tNOM\t\tPRENOM");
        for(int i = 0; i < nbEtudiants; i++){
            if(!estBoursier(listeEtudiants[i])){
                System.out.println(listeEtudiants[i].getId() + "\t" + listeEtudiants[i].getNom() +"\t\t"+ listeEtudiants[i].getPrenom());
            }
        }
        System.out.println("\n");
    }
    
    /**
     * Méthode afficherListeRechercheEtudiantsNom affiche un étudiant par son nom n et son prenom p
     * @param n est le nom d'étudiant
     * @param p est le prénom d'étudiant
     */
    public void afficherListeRechercheEtudiantsNom(String n, String p){
        System.out.println("Etudiants ayant pour nom " + n + " " + p + "\n");
        
        System.out.println("Liste des etudiants :\nID\tNOM\t\tPRENOM\t\tABSENCES\t\tVALIDE?\t\tBOURSIER?\t\tBourse(en euros)\tDoit Rembourser");
        for(int i = 0; i < nbEtudiants; i++){
            if(listeEtudiants[i].getNom().equals(n) && listeEtudiants[i].getPrenom().equals(p)){
                if(estBoursier(listeEtudiants[i])){
                    System.out.println(listeEtudiants[i].getId() + "\t" + listeEtudiants[i].getNom() +"\t\t"+ listeEtudiants[i].getPrenom() +"\t\t"+ listeEtudiants[i].getNbAbsInj() +"\t\t\t"+ (estTropAbsent(listeEtudiants[i])? "NON":"OUI") +"\t\t"+ (estBoursier(listeEtudiants[i])?"OUI":"NON")+"\t\t\t"+((EtudiantBoursier)listeEtudiants[i]).getMontantBourse()+"\t\t\t"+ (doitRembourserBourse((EtudiantBoursier)listeEtudiants[i])?"OUI":"NON"));
                } else{
                    System.out.println(listeEtudiants[i].getId() + "\t" + listeEtudiants[i].getNom() +"\t\t"+ listeEtudiants[i].getPrenom() +"\t\t"+ listeEtudiants[i].getNbAbsInj() +"\t\t\t"+ (estTropAbsent(listeEtudiants[i])? "NON":"OUI") +"\t\t"+ (estBoursier(listeEtudiants[i])?"OUI":"NON")+"\t\t\t"+ "---"+"\t\t\t"+ "---");
                }
                
            }
        }
        System.out.println();
    }
    
     /**
     * Méthode afficherListeRechercheEtudiantsID affiche un étudiant par son ID d'étudiant 
     * @param id est l'ID d'étudiant
     */
    public void afficherListeRechercheEtudiantsID(int id){
        System.out.println("Liste des etudiants :\nID\tNOM\t\tPRENOM\t\tABSENCES\t\tVALIDE?\t\tBOURSIER?\t\tBourse(en euros)\tDoit Rembourser");
        for(int i = 0; i < nbEtudiants; i++){
            if(listeEtudiants[i].getId() == id){
               if(estBoursier(listeEtudiants[i])){
                    System.out.println(listeEtudiants[i].getId() + "\t" + listeEtudiants[i].getNom() +"\t\t"+ listeEtudiants[i].getPrenom() +"\t\t"+ listeEtudiants[i].getNbAbsInj() +"\t\t\t"+ (estTropAbsent(listeEtudiants[i])? "NON":"OUI") +"\t\t"+ (estBoursier(listeEtudiants[i])?"OUI":"NON")+"\t\t\t"+((EtudiantBoursier)listeEtudiants[i]).getMontantBourse()+"\t\t\t"+ (doitRembourserBourse((EtudiantBoursier)listeEtudiants[i])?"OUI":"NON"));
                } else{
                    System.out.println(listeEtudiants[i].getId() + "\t" + listeEtudiants[i].getNom() +"\t\t"+ listeEtudiants[i].getPrenom() +"\t\t"+ listeEtudiants[i].getNbAbsInj() +"\t\t\t"+ (estTropAbsent(listeEtudiants[i])? "NON":"OUI") +"\t\t"+ (estBoursier(listeEtudiants[i])?"OUI":"NON")+"\t\t\t"+ "---"+"\t\t\t"+ "---");
                }     
            }
        }
        System.out.println();
        
    }
    
    /**
     * Méthode boucleMenuPrincipal affiche les options dans le menu principal
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
     * Méthode actionMenuPrincipal determine l'action à faire en fonction de l'option choisie pour le menu principal 
     * @param choix est un intéger que l'utilisateur saisit pour faire un choix dans la liste proposée
     */
    public void actionMenuPrincipal(int choix){
        switch(choix){
            case 1: //ajouter un etudiant
                boucleMenuAjouter();
                break;
            case 2: //Supprimer un etudiant
                boucleMenuSupprimer();
                break;
            case 3: //Lister les etudiants
                boucleMenuListe();
                break;
            case 4: //Chercher un etudiant
                boucleMenuRechercher();
                break;
            case 5: //retour
                System.out.println("retour !\n");
                break;
        }
    }
    
    /**
     * Méthode boucleMenuListe montre le menu de listage
     */
    public void boucleMenuListe(){
        boolean stop = false;
        
        while(!stop){
            menuListe.afficher();
            menuListe.saisirOption();

            stop = (menuListe.getChoixOption() == menuListe.getDerniereOption());   //en cas de retour(derniere option)

            actionMenuListe(menuListe.getChoixOption());
        }
        
        
    }
    
    /**
     * Méthode actionMenuListe determine l'action à faire en fonction de l'option choisie pour le menu de listage
     * @param choix est un paramètre entré que l'utilisateur saisit pour faire un choix dans la liste proposée
     */
    public void actionMenuListe(int choix){
        switch(choix){
            case 1: //lister tous les etudiants
                afficheListeEtudiants();
                break;
            case 2: //listes les boursiers
                afficheListeEtudiantsBoursiers();
                break;
            case 3: //Lister les non boursiers
                afficheListeEtudiantsNonBoursiers();
                break;
            case 4: //lister les <=5 absences
               afficheListeEtudiantsValides();
                break;
            case 5: //lister les >5 abs
                afficheListeEtudiantsNonValides();
                break;
            case 6:     //retour
                System.out.println("retour !\n");
                break;
        }
    }
    
    /**
     * Méthode boucleMenuAjouter montre le menu d'ajout
     */
    public void boucleMenuAjouter(){
        boolean stop = false;
        
        while(!stop){
            menuAjouter.afficher();
            menuAjouter.saisirOption();

            stop = (menuAjouter.getChoixOption() == menuAjouter.getDerniereOption());   //en cas de retour(derniere option)

            actionMenuAjouter(menuAjouter.getChoixOption());
        
        }
    }
    
    /**
     * Méthode actionMenuAjouter determine l'action à faire en fonction de l'option choisie pour le menu d'ajout
     * @param choix est un paramètre entré que l'utilisateur saisit pour faire un choix dans la liste proposée
     */
    public void actionMenuAjouter(int choix){
        switch(choix){
            case 1: //Ajouter
                String nom, prenom;
                boolean boursier;
                
                System.out.print("Veuillez saisir le nom de l'etudiant : ");
                nom = Clavier.lireString();
                System.out.print("\nVeuillez saisir le prenom de l'etudiant : ");
                prenom = Clavier.lireString();
                
                System.out.print("\nL'etudiant est-il boursier ? (o/n)");
                
                boursier = (Clavier.lireString().equals("o"));
                System.out.print("\n" + (boursier? "boursier" : "Non boursier") + "\n");
                
                if(boursier){
               
                    EtudiantBoursier e = new EtudiantBoursier(nom, prenom);
                    ajouterEtudiant(e);
                    
                    do{ 
                        System.out.println("\nQuel est le montant de bourse? \n");
                        montantBourseAjouter= Clavier.lireString();
                        
                        if(Double.parseDouble(montantBourseAjouter)>=0){
                            e.setMontantBourse(Double.parseDouble(montantBourseAjouter));
                        } else {
                            System.out.println("\nLe montant de bourse doit etre superieur a zero.\n");
                    }
                    } while(Double.parseDouble(montantBourseAjouter)<0);

                    
                }
                else{
                    ajouterEtudiant(new Etudiant(nom, prenom));
                }
                break;
            case 2: //Retour
                System.out.println("retour !\n");
                break;
        }
    }
    
    /**
     * Méthode boucleMenuSupprimer montre le menu de suppression
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
     * Méthode actionMenuSupprimer determine l'action à faire en fonction de l'option choisie pour le menu de suppression
     * @param choix est un paramètre entré que l'utilisateur saisit pour supprimer l'étudiant ou retourner sur le menu précédent 
     */
    public void actionMenuSupprimer(int choix){
        switch(choix){
            case 1: //Supprimer
                String nom, prenom;
                boolean certain;
                
                System.out.print("Veuillez saisir le nom de l'etudiant : ");
                nom = Clavier.lireString();
                System.out.print("\nVeuillez saisir le prenom de l'etudiant : ");
                prenom = Clavier.lireString();
                
                System.out.print("Vous allez supprimer TOUS les etudiant ayant ce nom et ce prenom, etes-vous sur? (o/n)");
                certain = (Clavier.lireString().equals("o"));
                
                if(certain){
                    supprimerEtudiant(nom, prenom);
                    System.out.print("\nEtudiants supprimes.\n");
                }
                else{
                    System.out.print("\nAnnulation... \n");
                }
                
            case 2: //Retour
                System.out.println("retour !\n");
                break;
        }
    }
    
    /**
     * Méthode boucleMenuRechercher montre le menu de recherche
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
     * Méthode actionMenuRechercher determine l'action à faire en fonction de l'option choisie pour le menu de recherche
     * @param choix est un paramètre entré que l'utilisateur saisit pour chercher un étudiant par son nom/prénom ou par son id d'étudiant ou pour retourner au menu précédent
     */
    public void actionMenuRechercher(int choix){
        switch(choix){
            case 1: //Rechercher par nom et prenom
                String nom, prenom;
                
                System.out.print("Veuillez saisir le nom de l'etudiant : ");
                nom = Clavier.lireString();
                System.out.print("\nVeuillez saisir le prenom de l'etudiant : ");
                prenom = Clavier.lireString();
                
                afficherListeRechercheEtudiantsNom(nom, prenom);
                
                for(int i = 0; i < nbEtudiants; i++){
                    if(listeEtudiants[i].getNom().equals(nom) && listeEtudiants[i].getPrenom().equals(prenom)){
                        int id= listeEtudiants[i].getId();
                        boucleMenuModification(id);
                    }
                }
                break;
            case 2: //rechercher par id
                int id;
                Etudiant e;
                System.out.print("Veuillez saisir l'ID de l'etudiant : ");
                id = Clavier.lireInt();
                
                if (id > nbEtudiants || id < 1){
                    System.out.println("Aucun etudiant ne porte cet ID.");
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
     * Méthode boucleMenuModification montre le menu de modification sur l'information d'étudiant 
     * @param id est un paramètre entré que l'utilisateur saisit pour chercher un étudiant par son id d'étudiant et fait les modification sur son dossier
     */
    public void boucleMenuModification(int id){
        boolean stop = false;
        
        while(!stop){
            afficherListeRechercheEtudiantsID(id);
            menuModification.afficher();
            menuModification.saisirOption();

            stop = (menuModification.getChoixOption() == menuModification.getDerniereOption());   //en cas de retour(derniere option)

            actionMenuModification(menuModification.getChoixOption(), id);
        
        }
    }
    
    /**
     * Méthode actionMenuModification donne option de faire la modification sur l'information d'étudiant 
     * @param choix est un paramètre entré par l'utilisateur pour faire la choix dans la liste
     * @param id est l'ID d'étudiant
     */
    public void actionMenuModification(int choix, int id){
        switch(choix){
            case 1: //Changer nom
                String nom;
                               
                System.out.print("Veuillez saisir le nouveau nom de l'etudiant : ");
                nom = Clavier.lireString();
                
                listeEtudiants[id-1].setNom(nom);
                
                System.out.print("Le nom a ete change\n");
                break;
            case 2: //changer prenom
                String prenom;
                               
                System.out.print("Veuillez saisir le nouveau prenom de l'etudiant : ");
                prenom = Clavier.lireString();
                
                listeEtudiants[id-1].setPrenom(prenom);
                
                System.out.print("Le prenom a ete change\n");
                break;
            case 3: //ajouter absence
                listeEtudiants[id-1].incrementeAbsInj();
                
                System.out.print("Nombre d'absences injustifiee actualise : " + listeEtudiants[id-1].getNbAbsInj() + "\n");
                break;
            case 4: //justifier absence
                if(listeEtudiants[id-1].getNbAbsInj()>0){
                    listeEtudiants[id-1].decrementeAbsInj();
                    System.out.print("Nombre d'absences injustifiee actualise : " + listeEtudiants[id-1].getNbAbsInj() + "\n");
                } else {
                    System.out.println("Le nombre d'absence ne peut pas etre negatif.");
                }

                break;
            case 5: //Ajouter le bourse d'étudiant
                if(estBoursier(listeEtudiants[id-1])){
                    System.out.print("Veuillez saisir le nouveau montant de bourse (ou saisir retour pour retourner au menu): ");
                    String montantNouveau= Clavier.lireString();

                    if(Double.parseDouble(montantNouveau)>=0){
                       ((EtudiantBoursier)listeEtudiants[id-1]).setMontantBourse(Double.parseDouble(montantNouveau)); 
                    } else {
                        System.out.println("\nLe bourse doit etre un montant superieur a zero.\n\n");
                    }
                    
                } else {
                    System.out.print("Cet etudiant n'est pas boursier(e).\n\n");
                }

                break;
            case 6: //Retour
                System.out.println("retour !\n");
                break;
        }
    }
    
}
// "Changer nom", "Changer prenom", "Ajouter absence injustifiee", "Justifier une absence", "Retour");
