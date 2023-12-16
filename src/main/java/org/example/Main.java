package org.example;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


abstract class Item {

    public String title;
    public String uniqueID;
    public boolean isBorrowed = false;
    public boolean borrowItem(){
        if(!isBorrowed){
            this.isBorrowed=true;
            return true;
        } else {
            return false;
        }
        }
        public boolean returnItem(){
            if(isBorrowed){
                this.isBorrowed=false;
                return true;
            } else {
                return false;
            }
    }
    public abstract String getInfo();
    };
class  DVD extends Item{
    public int duration;
    public DVD(String nm, String num, int dur){
        this.title = nm;
        this.uniqueID = num;
        this.duration = dur;
    }
    @Override
    public String getInfo(){
        String fullInfo = this.title + " | " + this.duration+ " | " + this.uniqueID;
        return fullInfo;
    }
}
class Patron{
    public String name;
    public String patronID;
    public ArrayList<Item> borrowedItems;

    public String borrowItem(Item toBorrow){
        boolean temp = toBorrow.borrowItem();
        if(temp){
            this.borrowedItems.add(toBorrow);
            return "OK";
        }else {
            return "UNABLE TO BOOROW";
        }
    }
    public String returnItem(Item toReturn){
        boolean temp = toReturn.borrowItem();
        if(temp){
            this.borrowedItems.remove(toReturn);
            return "OK";
        }else {
            return "UNABLE TO RETURN";
        }
    }

    public Patron(String nm, String id){
        this.name=nm;
        this.patronID=id;
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
    @Override
    public String getInfo(){
        String fullInfo = this.title + " | " + this.author + " | " + this.uniqueID + " | " + this.year;
        return fullInfo;
    }
}

abstract interface IManageable{
    public void addItem(Item toAdd);
    public void removeItem(Item toRemove);
    public void listAllBorrowed();
    public void listAllAvailable();
}

class Library implements IManageable{
    public ArrayList<Item> items = new ArrayList<Item>();
    public ArrayList<Patron> patrons = new ArrayList<Patron>();
    @Override
    public void addItem(Item toAdd) {
        items.add(toAdd);
    }

    @Override
    public void removeItem(Item toRemove) {

        if(toRemove.isBorrowed==true){
        for(int i = 0; i<patrons.size(); i++){

            for(int j = 0; j<patrons.get(i).borrowedItems.size(); j++){
                if(patrons.get(i).borrowedItems.contains(toRemove)){
                    patrons.get(i).borrowedItems.remove(toRemove);
                }
            }

        }}
        items.remove(toRemove);
    }

    @Override
    public void listAllBorrowed() {
        for(int i = 0; i<patrons.size(); i++){

                for(int j = 0; j<patrons.get(i).borrowedItems.size(); j++){
                    System.out.print(patrons.get(i).name);
                    System.out.print(" | ");
                    System.out.println(patrons.get(i).borrowedItems.get(j).getInfo());
                }

        }
    }

    @Override
    public void listAllAvailable() {
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).isBorrowed == false){
                System.out.println(items.get(i).getInfo());
            }
        }
    }

    public void registerPatron(Patron toRegister){
        patrons.add(toRegister);
    }
    public void lendItem(Patron client, Item toBorrow){
        System.out.println(client.borrowItem(toBorrow));
    }
    public void returnItem(Patron client, Item toReturn){
        System.out.println(client.returnItem(toReturn));
    }
    }



public class Main {
    public static void main(String[] args) throws IOException {
        Library library = new Library();
        library.addItem(new Book("Head first. Java", "Kathy Sierra", "9785699545742", 2003));
        library.addItem(new Book("Effective Java", "Joshua Bloch", "9780132345286", 2008));
        library.addItem(new Book("Java Concurency in Practice", "Brian Goetz", "9780321349606", 2006));
        library.addItem(new Book("java 8 Pocket guide", "Robert Liguory", "9785845920508", 2015));
        //menu
        boolean menuExt = false;
        int readInt;
        String readString;
        Scanner console = new Scanner(System.in);
        while(!menuExt){
            System.out.println("MENU. ENTER NUMBER TO MAKE:");
            System.out.println("1.Add item to library");
            System.out.println("2.Remove item from library by ID");
            System.out.println("3.Register new patron");
            System.out.println("4.Borrow item to patron");
            System.out.println("5.Return item to library");
            System.out.println("6.Show all available");
            System.out.println("7.Show all borrowed");
            readInt = console.nextInt();
            readString = console.nextLine();
            switch (readInt){
                case 1: {
                    System.out.println("What item do you want to add?");
                    System.out.println("1.Book");
                    System.out.println("2.DVD");
                    readInt = console.nextInt();
                    readString = console.nextLine();
                    if(readInt==1) {
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
                        readString = console.nextLine();
                        yearTemp = readInt;
                        library.addItem(new Book(nameTemp, authorTemp, isbnTemp, yearTemp));
                    }
                    else if(readInt==2){
                        String nameTemp;
                        String idTemp;
                        int durationTemp;
                        System.out.println("Enter name:");
                        readString = console.nextLine();
                        nameTemp = readString;
                        System.out.println("Enter ID:");
                        readString = console.nextLine();
                        idTemp = readString;
                        System.out.println("Enter year:");
                        readInt = console.nextInt();
                        readString = console.nextLine();
                        durationTemp = readInt;
                        library.addItem(new DVD(nameTemp,idTemp, durationTemp));
                    }else{
                        System.out.println("INCORRECT");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter ISBN or ID");
                    readString = console.nextLine();
                    boolean noitemsfound = true;

                    for(int i = 0; i < library.items.size();i++){
                        if(library.items.get(i).uniqueID.equals(readString)) {
                            library.removeItem(library.items.get(i));
                            noitemsfound = false;
                        }
                    }
                    if(noitemsfound){
                        System.out.println("NO ITEMS DELETED");
                    }
                    break;
                }
                case 3: {
                    String nameTemp;
                    String idTemp;
                    System.out.println("Enter name:");
                    readString = console.nextLine();
                    nameTemp = readString;
                    System.out.println("Enter patronID:");
                    readString = console.nextLine();
                    idTemp = readString;
                    library.registerPatron(new Patron(nameTemp,idTemp));
                    break;
                }
                case 4: {
                    String patronidTemp;
                    String itemidTemp;
                    System.out.println("Enter patronID:");
                    readString = console.nextLine();
                    patronidTemp= readString;
                    System.out.println("Enter item ID/ISBN:");
                    readString = console.nextLine();
                    patronidTemp= readString;
                    Item itemTemp = null;
                    Patron patronTemp = null;
                    boolean nofound = true;
                    for(int i = 0; i < library.items.size();i++){
                        if(library.items.get(i).uniqueID.equals(readString)) {
                            itemTemp = library.items.get(i);
                            nofound = false;
                        }
                    }
                    if(nofound){
                        System.out.println("NO SUCH ITEM");
                        break;
                    }
                    nofound = true;
                    for(int i = 0; i < library.items.size();i++){
                        if(library.patrons.get(i).patronID.equals(readString)) {
                            patronTemp = library.patrons.get(i);
                            nofound = false;
                        }
                    }
                    if(nofound){
                        System.out.println("NO SUCH PATRON");
                        break;
                    }

                    library.lendItem(patronTemp,itemTemp);
                    break;
                }
                case 5: {
                    String patronidTemp;
                    String itemidTemp;
                    System.out.println("Enter patronID:");
                    readString = console.nextLine();
                    patronidTemp= readString;
                    System.out.println("Enter item ID/ISBN:");
                    readString = console.nextLine();
                    patronidTemp= readString;
                    Item itemTemp = null;
                    Patron patronTemp = null;
                    boolean nofound = true;
                    for(int i = 0; i < library.items.size();i++){
                        if(library.items.get(i).uniqueID.equals(readString)) {
                            itemTemp = library.items.get(i);
                            nofound = false;
                        }
                    }
                    if(nofound){
                        System.out.println("NO SUCH ITEM");
                        break;
                    }
                    nofound = true;
                    for(int i = 0; i < library.items.size();i++){
                        if(library.patrons.get(i).patronID.equals(readString)) {
                            patronTemp = library.patrons.get(i);
                            nofound = false;
                        }
                    }
                    if(nofound){
                        System.out.println("NO SUCH PATRON");
                        break;
                    }
                    library.returnItem(patronTemp,itemTemp);

                    break;
                }
                case 6: {
                    library.listAllAvailable();
                    break;
                }
                case 7: {
                    library.listAllBorrowed();
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

