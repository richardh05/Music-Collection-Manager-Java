//code adapted from ‘Comparator Interface in Java with Examples’, 2016
import java.util.Comparator;
public class SongAlbumComparator implements Comparator<Song> {
    @Override
    public int compare(Song song1, Song song2) {
        return song1.getAlbum().compareTo(song2.getAlbum());
    }
}
//end of adapted code