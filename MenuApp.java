package gestion_iut;

public class MenuApp {
    private String titre;
    private String[] options;
    private int choixOption = -1;
    
    /**
     * Constructeur : t est le titre du menu, opt est une liste de noms d'options séparés par des virgules, si une option "Retour" est necessaire, la mettre en dernier
     * @param t
     * @param opt 
     */
    MenuApp(String t, String ...opt){
        titre = t;  //le titre du menu
        options = new String[opt.length];   //les options disponibles sur le menu
        
        System.arraycopy(opt, 0, options, 0, opt.length);
    }
    
    
    
    
    /**
     * Accesseur de la liste des options
     * @return options[]
     */
    public String[] getOptions(){
        return options;
    }
    
    /**
     * affiche le menu
     */
    public void afficher(){
        System.out.print(titre);    //affichage du titre avec des -
        for(int i = 0; i <50; i++){System.out.print("-");}
        
        System.out.print("\n\n");
        
        for(int i = 0; i < options.length; i++){    //affichage des options
            System.out.print(options[i] + " (" + (i+1) + ")\n" );
        }
        System.out.print("\n");
    }
    
   
    /**
     * Accesseur de choixOption
     * @return 
     */
    public int getChoixOption(){return choixOption;}
    
    /**
     * set choixOption a -1
     */
    public void resetChoixOption(){choixOption = -1;};
    
    /**
     * Gere la saisie d'une option
     */
    public void saisirOption(){
        System.out.print("Entrez le numero de l'option souhaitee : ");
        
        choixOption = Clavier.lireInt();
        
        while(choixOption < 1 || choixOption > options.length)
        {
            System.out.print("Veuillez entrer un numero valide : ");
            choixOption = Clavier.lireInt();
        }
        System.out.print("\n");

    }
    
    /**
     * Renvoie l'index de la dernier option +1
     * @return 
     */
    public int getDerniereOption(){return options.length;}
}
