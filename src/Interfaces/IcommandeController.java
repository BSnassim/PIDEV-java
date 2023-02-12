/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import model.Commande;

/**
 *
 * @author Marwen.M
 */
public interface IcommandeController {
    
    public void ajouterCommande(Commande c);

    public void modifierCommande(Commande c, int id);

    public void supprimerCommande(int id);

    public List<Commande> afficherCommande();

    public Commande rechCommande(int id);

}
