/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionreclamation;

import Entities.EtatEnum;
import Entities.Reclamation;
import Entities.Reponse;
import Entities.TypeEnum;
import Service.ServiceReclamation;
import Service.ServiceReponse;
import java.sql.Date;

/**
 *
 * @author houss
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Reclamation r= new Reclamation("tesssssssst",EtatEnum.En_Cours,TypeEnum.Song,1);
        ServiceReclamation sr=new ServiceReclamation();
       // sr.add(r);
        
        
        
        
        /*
        
        for(int i = 0 ;i<sr.Show().size();i++){
            System.out.println(sr.Show().get(i).toString());
        } 
        
       */
        
        
      
        /*
        r.setId(1);
        r.setEtat(EtatEnum.Traité);
        sr.update(r);
        for(int i = 0 ;i<sr.Show().size();i++){
            System.out.println(sr.Show().get(i).toString());
        }  
        
       */
        
        
        /*
        r.setId(1);
      sr.Delete(r);
      */
        
        
       
       Reclamation rec=sr.getById(2);
      //  Reponse rep=new Reponse("test",rec);
        ServiceReponse serp=new ServiceReponse();
       // serp.add(rep);
       
       Reponse rep=serp.getById(1);
      // rep.setMessage("noooooo");
       // serp.update(rep);
       
       serp.Delete(rep);
        for(int i = 0 ;i<serp.Show().size();i++){
            System.out.println(serp.Show().get(i).toString());
        } 

        
    }
    
}
