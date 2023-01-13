/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestion_iut;

/**
 *
 * @author etulyon1
 */
public interface Primable {
    /**permet de dire si un membre du personnel a une prime ou non. Par défaut, prime est à false, et passera à
     * true si on essaie de saisir un montant pour la prime d'un membre du personnel. 
     */
     static final boolean PRIME = false;
     /** indique le montant de la prime d'un membre du personnel. Si prime est à false, montant_prime sera forcément
      * à 0. Si prime est à true, alors montant_prime est forcément supérieur à 0.
      */
     static final float MONTANT_PRIME = 0;
     
    /**
     * Mutateur de la variable montantPrime: si la méthode est appelée alors que l'enseignant en question n'avait pas de prime, 
     * alors la variable prime est passée à true.
     * @param val, le montant de la prime. val ne peut pas être négatif ou égal à 0 sinon, on enverra un message d'erreur. 
     */
     void setMontantPrime(float val);
     
     /**
     * methode permettant de supprimer la prime d'un membre du personnel. S'il ne touchait pas de prime, un message d'erreur sera envoye
     * s'il touchait une prime, le booleen prime sera passée à false et le montant de la prime passera à 0;
     */
     void enleverPrime ();
}
