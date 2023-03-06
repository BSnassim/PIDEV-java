/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import controller.EventController;
import controller.ReservationController;
import enteties.event;
import java.time.LocalDate;
import utils.Connection;

/**
 *
 * @author sywar
 */
public class PIDEV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Connection a = Connection.getInstance();
       
       System.out.println(a.hashCode());
         EventController e =  new EventController();
         ReservationController r =  new ReservationController();
         
         LocalDate dt = LocalDate.parse("2018-09-11");
         event v = new event("2016-10-12","event","picture","Mahdia",120);

         

//e.ajouter(v);
//e.supprimer(1);
e.modifier(v,21);
System.out.println(e.afficher()); 
//System.out.println(e.recherche("Nabeul")); 
//System.out.println(e.triPrix()); 




//System.out.println(r.afficher());  

    
    
    }
    
}
