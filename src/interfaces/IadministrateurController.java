/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Administrateur;


/**
 *
 * @author Ranim Ahmadi
 */
public interface IadministrateurController {
     public void ajouterAdministrateur(Administrateur admin);

    public void modifierAdministrateur(Administrateur admin, int id);

    public void supprimerAdministrateur(int id);

    public List<Administrateur> afficherAdministrateur();

    public Administrateur rechAdministrateur(int id);
    
}
