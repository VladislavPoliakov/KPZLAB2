package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


abstract class Item {

    public String title;
    public String uniqueID;
    private boolean isBorrowed = false;
    public String borrowItem(){
        if(!isBorrowed){
            this.isBorrowed=true;
            return "OK";
        } else {
            return "ITEM ALREADY BORROWED";
        }
        }
        public String returnItem(){
            if(isBorrowed){
                this.isBorrowed=false;
                return "OK";
            } else {
                return "ITEM ALREADY IN LIBRARY";
            }
    }
    };
class  DVD extends Item{
    public int duration;
    public DVD(String nm, String num, int dur){
        this.title = nm;
        this.uniqueID = num;
        this.duration = dur;
    }
}
class Book extends Item {

    public String author;
    public int year;


    public Book(String nm, String auth, String num, int yr){
        this.title = nm;
        this.author = auth;
        this.uniqueID = num;
        this.year = yr;
    }

    public String getInfo(){
        String fullInfo = this.title + " | " + this.author + " | " + this.uniqueID + " | " + this.year;
        return fullInfo;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Book> library = new ArrayList<Book>();
        library.add(new Book("Head first. Java", "Kathy Sierra", "9785699545742", 2003));
        library.add(new Book("Effective Java", "Joshua Bloch", "9780132345286", 2008));
        library.add(new Book("Java Concurency in Practice", "Brian Goetz", "9780321349606", 2006));
        library.add(new Book("java 8 Pocket guide", "Robert Liguory", "9785845920508", 2015));
        //menu
        boolean menuExt = false;
        int readInt;
        String readString;
        Scanner console = new Scanner(System.in);
        while(!menuExt){
            System.out.println("MENU. ENTER NUMBER TO MAKE:");
            System.out.println("1.Add book");
            System.out.println("2.Show all books");
            System.out.println("3.Search by name");
            System.out.println("4.Delete by ISBN");
            System.out.println("9.EXIT");

            readInt = console.nextInt();
            readString = console.nextLine();
            switch (readInt){
                case 1: {
                    String nameTemp;
                    String authorTemp;
                    String isbnTemp;
                    int yearTemp;
                    System.out.println("Enter name:");
                    readString = console.nextLine();
                    nameTemp = readString;
                    System.out.println("Enter author:");
                    readString = console.nextLine();
                    authorTemp = readString;
                    System.out.println("Enter ISBN:");
                    readString = console.nextLine();
                    isbnTemp = readString;
                    System.out.println("Enter year:");
                    readInt = console.nextInt();

                    yearTemp = readInt;
                    library.add(new Book(nameTemp,authorTemp,isbnTemp,yearTemp));
                    break;

                }
                case 2: {
                    for(int i = 0; i < library.size();i++){
                        System.out.println(">------------->");
                        System.out.println(library.get(i).getInfo());
                    }
                    System.out.println(">-------------.");
                    break;
                }
                case 3: {
                    System.out.println("Enter name");
                    readString = console.nextLine();
                    boolean nobooksfound = true;

                    for(int i = 0; i < library.size();i++){
                        if(library.get(i).title.toLowerCase().contains(readString.toLowerCase())) {
                            System.out.println(">------------->");
                            System.out.println(library.get(i).getInfo());
                            nobooksfound = false;
                        }
                    }
                    if(nobooksfound){
                        System.out.println("NO BOOKS FOUND");
                    }
                    System.out.println(">-------------.");
                    break;
                }
                case 4: {
                    System.out.println("Enter ISBN");
                    readString = console.nextLine();
                    boolean nobooksfound = true;

                    for(int i = 0; i < library.size();i++){
                        if(library.get(i).uniqueID.equals(readString)) {
                            System.out.println(">------------->");
                            System.out.print(library.get(i).getInfo());
                            System.out.println(" | DELETED");
                            library.remove(i);
                            nobooksfound = false;
                        }
                    }
                    if(nobooksfound){
                        System.out.println("NO BOOKS DELETED");
                    }
                    System.out.println(">-------------.");
                    break;
                }
                case 9: {
                    menuExt = true;
                    break;
                }
                default: {
                    System.out.println("ERROR! INCORRECT INPUT");
                    break;
                }
            }
        }
    }
}