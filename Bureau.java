package gestion_iut;

public class Bureau {

    private int numero; //le numero d'identification du bureau, se veut unique
    private Personnel[] utilisateursDuBureau;   //un tableau contenant le personnel siegeant à ce bureau
    private int nbUtilisateurs; //le nombre d'utilisateur actuelle du bureau, maximum 3 par bureau

    public Bureau() {
        utilisateursDuBureau = new Personnel[3];
        nbUtilisateurs = 0;
    }

    public Bureau (Personnel user) {
        utilisateursDuBureau = new Personnel[3];
        utilisateursDuBureau[0] = user;
        nbUtilisateurs = 1;
    }
        public Bureau (Personnel user1, Personnel user2) {
        utilisateursDuBureau = new Personnel[3];
        utilisateursDuBureau[0] = user1;
        utilisateursDuBureau[1] = user2;
        nbUtilisateurs = 2;

    }
    public Bureau (Personnel user1, Personnel user2, Personnel user3) {
        utilisateursDuBureau = new Personnel[3];
        utilisateursDuBureau[0] = user1;
        utilisateursDuBureau[1] = user2;
        utilisateursDuBureau[2] = user3;
        nbUtilisateurs = 3;
    }
    
    public void setNumero(int val){
        numero = val;
    }
    public int getNumeroBureau() {
        return numero;
    }
     /**
     * affiche les utilisateurs du bureau
     */
    public void afficheUtilisateurs(){
        
        for(int i = 0; i < nbUtilisateurs; i++){
            System.out.println(utilisateursDuBureau[i].getId() + "\t" + utilisateursDuBureau[i].getNom() +"\t\t"+ utilisateursDuBureau[i].getPrenom());
        }
        System.out.println("\n");

    }    
    /**
     * Ajoute un utilisateur a un bureau, si le bureau est plein affiche un
     * message d'erreur.
     *
     * @param user
     */
    public void ajouterUtilisateur(Personnel user) {
        if (nbUtilisateurs + 1 > 3) {
            System.out.println("Ajout impossible : bureau plein. Merci de créer un nouveau bureau.");
        } else {
            utilisateursDuBureau[nbUtilisateurs++] = user;
        }
    }

}
