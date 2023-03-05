/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Administrateur
 */
public class ImageReclamation {
    private int id;
    private String path;

    public ImageReclamation() {
    }

    public ImageReclamation(int id, String path) {
        this.id = id;
        this.path = path;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ImageReclamation{" + "id=" + id + ", path=" + path + '}';
    }
    
    
    
}
