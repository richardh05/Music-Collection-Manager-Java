//code adapted from ‘Comparator Interface in Java with Examples’, 2016
import java.util.Comparator;
public class SongArtistComparator implements Comparator<Song> {
    @Override
    public int compare(Song song1, Song song2) {
        return song1.getArtist().compareTo(song2.getArtist());
    }
}
//end of adapted code