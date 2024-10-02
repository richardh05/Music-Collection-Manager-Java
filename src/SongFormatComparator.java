//code adapted from ‘Comparator Interface in Java with Examples’, 2016
import java.util.Comparator;
public class SongFormatComparator implements Comparator<Song> {
    @Override
    public int compare(Song song1, Song song2) {
        return song1.getFormat().compareTo(song2.getFormat());
    }
}
//end of adapted code