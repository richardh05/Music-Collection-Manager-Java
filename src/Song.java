import java.io.*;
public class Song implements Serializable, Comparable<Song> {
    //fields
    private String title;
    private String artist;
    private String album;
    private int trackNo;
    private enum SongFormats {
        CD, VINYL, DIGITAL, STREAMING
    }
    private SongFormats format;

    //constructor
    public Song(String title, String artist, String album,int trackNo, int format) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.trackNo = trackNo;
        this.setFormat(format);
    }

    //accessors
    public String getTitle() {return title;}
    public String getArtist() {return artist;}
    public String getAlbum() {return album;}
    public int getTrackNo() {return trackNo;}
    public String getFormat(){
        return switch (format) {
            case CD -> "CD";
            case VINYL -> "Vinyl";
            case DIGITAL -> "Digital";
            case STREAMING -> "Streaming";
            //default -> "default"; // this will never be reached unless the enum is modified
        };
    }
    public static String propertiesHeader() {
        return "Title | Artist | Album | TrackNo | Format | ID";
    }
    public static String properties(Song s) {
        return s.getTitle() + " | " +
                s.getArtist() +  " | " +
                s.getAlbum() +  " | " +
                s.getTrackNo() +  " | " +
                s.getFormat() + " | " +
                s.hashCode();
    }

    //mutators
    public void setTitle(String title) {this.title = title;}
    public void setArtist(String artist) {this.artist = artist;}
    public void setAlbum(String album) {this.album = album;}
    public void setTrackNo(int trackNo) {this.trackNo = trackNo;}
    public void setFormat (int format) {
        switch (format) {
            case 1 -> this.format = SongFormats.CD;
            case 2 -> this.format = SongFormats.VINYL;
            case 3 -> this.format = SongFormats.DIGITAL;
            case 4 -> this.format = SongFormats.STREAMING;
        }
    }
    @Override
    public int compareTo(Song otherSong) {
        return this.title.compareTo(otherSong.getTitle());
    }
}