import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
public class Validations {
    public static int inputInt() { //code adapted from polygenelubricants, 2010
        Scanner intScan = new Scanner(System.in);
        int number = 0;
        while (number <= 0) {
            while (!intScan.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                intScan.nextLine();
            }
            number = intScan.nextInt();
            if (number <= 0) System.out.println("Please enter a number above 1.");
        }
        return number;
    } //end of adapted code
    public static int menuInput(int optionsNum) {
        int userInput = inputInt();
        while (userInput > optionsNum) {
            System.out.println("Please enter one of the specified numbers.");
            userInput = inputInt();
        }
        return userInput;
    }
    public static String inputString(){
        Scanner strScan = new Scanner(System.in);
        while (!strScan.hasNext()) {
            System.out.println("Please enter text.");
            strScan.nextLine();
        }
        return strScan.nextLine();
    }
    public static void loadSongs() {
        try {
            FileInputStream fis = new FileInputStream("save.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Main.songCollection = (ArrayList<Song>) ois.readObject();
        } catch (Exception ex) {
            System.out.println("Error reading or restoring data from `save.dat`.\n" +
                    "Ensure the file is in the project folder, or add songs and exit to create a new save.");
        }
    }
    public static void exit() {
        try {
            FileOutputStream fos = new FileOutputStream("save.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Main.songCollection);
        } catch (Exception ex) {
            System.out.println("Can't write data to file");
        }
        Main.cont = false;
        //System.exit(0);
    }
    public static String inputTitle() {
        System.out.println("What is the title of the song?:");
        return inputString();
    }
    public static String inputArtist() {
        System.out.println("Who is the song's artist?:");
        return inputString();
    }
    public static String inputAlbum() {
        System.out.println("What album is this song from?:");
        return inputString();
    }
    public static int inputTrackNo() {
        System.out.println("What number is this song on the album?:");
        return inputInt();
    }
    public static int inputFormat() {
        System.out.println("""
                What format do you own the song in?:\s
                1 - CD\s
                2 - Vinyl\s
                3 - Digital\s
                4 - Streaming""");
        return Validations.menuInput(4);
        }
    }