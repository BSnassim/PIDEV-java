/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Utilisateur;

/**
 *
 * @author Ranim Ahmadi
 */
public interface IutilisateurController {
    
    public void ajouterUtilisateur(Utilisateur u);

    public void modifierUtilisateur(Utilisateur u,int id);

    public void supprimerUtilisateur(int id);

    List<Utilisateur> afficherUtilisateur();

    public Utilisateur rechUtilisateur(int id);
    
}
