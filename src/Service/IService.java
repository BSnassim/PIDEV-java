/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author Saida
 */
public interface IService <T> {
    void add (T entity);
    void update (T entity);
    void Delete (T entity);
    List <T> Show();
    T getById(int id);
   
    
    
}
