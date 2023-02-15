package interfaces;

import java.util.List;

import model.Musique;

public interface IMusiqueController {
	
	public void createMusique(Musique m);
	public void updateMusique(Musique m, int id);
	public void deleteMusique(int id);
	public List<Musique> findAllMusique();
	public Musique findMusique(int id);

}
