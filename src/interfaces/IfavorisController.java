/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Favoris;

/**
 *
 * @author Ranim Ahmadi
 */
public interface IfavorisController {
    
    public void ajouterFavoris(Favoris f);

    /**
     *
     * @param f
     */
    public void modifierFavoris(Favoris f);

    public void supprimerFavoris(Favoris f);

    List<Favoris> afficherFavoris();

    /**
     *
     * @param id_user
     * @return
     */
    public Favoris rechFavoris(int id_user);
    
    
}
