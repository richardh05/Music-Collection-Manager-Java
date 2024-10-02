import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static ArrayList<Song> songCollection = new ArrayList<>(); //songCollection will store every 'Song' object.
    static boolean cont = true; //cont will always be true up until the user wants to exit.
    public static void main(String[] args) {
        Validations.loadSongs(); //immediately load songs from Song arraylist file upon launch
        while (cont) {
            System.out.println("Select a menu: \n" +
                               "1 - Display song list \n" +
                               "2 - Add a new song \n" +
                               "3 - Edit a song \n" +
                               "4 - Sort songs \n" +
                               "5 - View statistics \n" +
                               "6 - Exit");
            switch (Validations.menuInput(6)) {     //menuInput method used to validate all menu inputs from user.
                case 1 -> displaySongs();
                case 2 -> addSong();
                case 3 -> editSong();
                case 4 -> sortSongs();
                case 5 -> analyseSongs();
                case 6 -> Validations.exit();
            }
        }
    }

    public static void addSong() {
        String title = Validations.inputTitle();    //call the input methods in the Validations class
        String artist = Validations.inputArtist();
        String album = Validations.inputAlbum();
        int trackNo = Validations.inputTrackNo();
        int format = Validations.inputFormat();
        Song newSong = new Song(title, artist, album, trackNo, format); //construct song
        songCollection.add(newSong);
    }

    public static void displaySongs() {
        if (songCollection.isEmpty()) { //if statement to check there are songs in the collection.
            System.out.println("There are no songs in the collection to display, please add a song first.");
            return;
        }
        System.out.println(Song.propertiesHeader());
        for (Song counterSong : songCollection) {
            System.out.println(Song.properties(counterSong));
        }
    }

    public static void editSong() {
        if (songCollection.isEmpty()) { //check if collection is empty
            System.out.println("There are no songs in the collection to edit, please add a song first.");
            return;
        }
        System.out.println("What is the ID of the song to be edited?");
        int inputID = Validations.inputInt();
        Song counterSong = songCollection.getFirst(); //may break if there are no songs
        for (int i = 0; i < songCollection.size() && counterSong.hashCode() != inputID; i++) {
            counterSong = songCollection.get(i);
        } //iterate through song collection
        if (counterSong.hashCode() != inputID) { //check that counterSong stores the correct inputted song before continuing
            System.out.println("A song with this ID could not be found");
        } else {
            System.out.println(Song.propertiesHeader() + "\n" + Song.properties(counterSong) + "\n" + """
                            What would you like to edit?\s
                            1 - Edit title\s
                            2 - Edit artist\s
                            3 - Edit album\s
                            4 - Edit track number\s
                            5 - Delete song from collection
                            """);
            int selection = Validations.menuInput(5);
            switch (selection) {    //call set methods in Validations
                case 1 -> counterSong.setTitle(Validations.inputTitle());
                case 2 -> counterSong.setArtist(Validations.inputArtist());
                case 3 -> counterSong.setAlbum(Validations.inputAlbum());
                case 4 -> counterSong.setTrackNo(Validations.inputTrackNo());
                case 5 -> songCollection.remove(counterSong);
            }
        }
    }

    public static void sortSongs() {
        System.out.println("""
                What would you like to sort by?\s
                1 - Sort by title\s
                2 - Sort by artist\s
                3 - Sort by album\s
                4 - Sort by track number\s
                5 - Sort by format\s
                """);
        int selection = Validations.menuInput(5);
        switch (selection) {
            case 1 -> Collections.sort(songCollection); //uses Song comparable implementation
            case 2 -> songCollection.sort(new SongArtistComparator());
            case 3 -> songCollection.sort(new SongAlbumComparator());
            case 4 -> songCollection.sort(new SongTrackNoComparator());
            case 5 -> songCollection.sort(new SongFormatComparator());
        }
    }
    public static void analyseSongs() {
        System.out.println("""
                What statistics would you like to view?\s
                1 - Most frequent artist\s
                2 - Song format distribution\s
                """);
        int selection = Validations.menuInput(2);
        switch (selection){
            case 1 -> artistStatistics();
            case 2 -> formatStatistics();
        }
    }

    public static void artistStatistics(){
        ArrayList<String> artistList = new ArrayList<>();
        ArrayList<String> numberedArtistList = new ArrayList<>();
        for (Song iSong : songCollection) {artistList.add(iSong.getArtist());}   //set artistList as a string arraylist of songCollection artists
        for (String iArtist : artistList) {     //add artistList names to numberedArtistList in format "NUM OF OCCURRENCES - Artist Name"
            int iFrequency = Collections.frequency(artistList, iArtist);    //<-code adapted from Java.Util.Collections.Frequency() in Java - GeeksforGeeks, 2018
            numberedArtistList.add(iFrequency + " - " + iArtist);
        }
        numberedArtistList.sort(Collections.reverseOrder());    //<- code adapted from ‘Java Program to Sort an ArrayList’, 2020 and Collections.reverseOrder() in Java with Examples, 2023
        List<String> filteredArtistList = numberedArtistList    //code adapted from ‘How to Remove Duplicates from ArrayList in Java’, 2018
                .stream()
                .distinct()
                .toList();        //end of adapted code
        for (String s : filteredArtistList) {
            System.out.println(s);
            }
        }

    public static void formatStatistics(){
        ArrayList<String> formatList = new ArrayList<>();
        //set artistList as a string arraylist of songCollection artists
        for (Song iSong : songCollection) {formatList.add(iSong.getFormat());}
        System.out.println( //code adapted from Ball, 2010; Lawrey, 2010; Java.Util.Collections.Frequency() in Java - GeeksforGeeks, 2018
                        ((double) (Collections.frequency(formatList, "CD")) / formatList.size()) * 100  + "% - CD\n" +
                        ((double) (Collections.frequency(formatList, "Vinyl")) / formatList.size()) * 100  + "% - Vinyl\n" +
                        ((double) (Collections.frequency(formatList, "Digital")) / formatList.size()) * 100  + "% - Digital\n" +
                        ((double) (Collections.frequency(formatList, "Streaming")) / formatList.size()) * 100  + "% - Streaming"
            ); //end of adapted code
        }
    }
