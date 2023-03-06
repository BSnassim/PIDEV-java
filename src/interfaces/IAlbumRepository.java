package interfaces;

import java.util.List;

import model.Album;

public interface IAlbumRepository {
	
	public void createAlbum(Album a);
	public void updateAlbum(Album a, int id);
	public void deleteAlbum(int id);
	public List<Album> findAllAlbum();
	public Album findAlbum(int id);

}
