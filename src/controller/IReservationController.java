/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enteties.reservation;
import java.util.List;

/**
 *
 * @author sywar
 */
public interface IReservationController <T> {
      public boolean  ajouter(reservation r );
    public List<reservation> afficher();
    public boolean  modifier (T t, int id);
    public boolean supprimer (T t);
    public int nbSurPlace();
     public int nbSurEnLigne();
    
}
