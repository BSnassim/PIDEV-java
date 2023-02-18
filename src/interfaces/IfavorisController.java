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

    public void modifierFavoris(Favoris f,int id_user,int id_musique);

    public void supprimerFavoris(int id_user,int id_musique);

    List<Favoris> afficherFavoris();

    public Favoris rechFavoris(int id_user,int id_musique);
    
    
}
