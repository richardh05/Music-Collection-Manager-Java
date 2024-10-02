import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {
static final Song testSong = new Song("Blinding Lights","The Weeknd","After Hours",9, 1);
static Song setterTestSong = new Song("Livin' la Vida Loca","Ricky Martin","Ricky Martin",1, 2);
    @Test
    void Song() {
        Song newSong = new Song("title", "artist", "album", 1, 1); //construct song
        assertEquals("title",newSong.getTitle());
        assertEquals("artist",newSong.getArtist());
        assertEquals("album",newSong.getAlbum());
        assertEquals(1,newSong.getTrackNo());
        assertEquals("CD",newSong.getFormat());

    }
    @Test
    void getTitle() {
        assertEquals("Blinding Lights",testSong.getTitle() );
    }

    @Test
    void getArtist() {
        assertEquals("The Weeknd",testSong.getArtist());
    }

    @Test
    void getAlbum() {
        assertEquals("After Hours",testSong.getAlbum());
    }

    @Test
    void getTrackNo() {
        assertEquals(9,testSong.getTrackNo());
    }

    @Test
    void getFormat() {
        assertEquals("CD",testSong.getFormat());

    }

    @Test
    void setTitle() {
        setterTestSong.setTitle("Heartless");
        assertEquals("Heartless",setterTestSong.getTitle() );

    }

    @Test
    void setArtist() {
        setterTestSong.setArtist("Kanye West");
        assertEquals("Kanye West",setterTestSong.getArtist() );
    }

    @Test
    void setAlbum() {
        setterTestSong.setAlbum("808s and Heartbreak");
        assertEquals("808s and Heartbreak",setterTestSong.getAlbum() );
    }

    @Test
    void setTrackNo() {
        setterTestSong.setTrackNo(3);
        assertEquals(3,setterTestSong.getTrackNo() );
    }

    @Test
    void setFormat() {
        setterTestSong.setFormat(3);
        assertEquals("Digital",setterTestSong.getFormat());
    }
}