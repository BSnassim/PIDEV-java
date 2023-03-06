/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enteties.event;
import java.util.List;

/**
 *
 * @author sywar
 */
public interface IEventController <T> {
    
    /////CRUD
    public boolean  ajouter(event e );
    public List<event> afficher();
    public boolean  modifier (T t, int id);
    public boolean supprimer (int id);
    
    
    /////RECHERCHE
    public List<event> rechercheAdresse(String adresse);
    public List<event> recherchePrix(int prix);

    
    /////TRI
    public List<event> triDate();
    public List<event> triPrix();



    
}
