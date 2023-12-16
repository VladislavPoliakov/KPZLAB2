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
    public List<Item> borrowedItems;

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
    public List<Item> items;
    public List<Patron> patrons;
    @Override
    public void addItem(Item toAdd) {
        items.add(toAdd);
    }

    @Override
    public void removeItem(Item toRemove) {
        items.remove(toRemove);
    }

    @Override
    public void listAllBorrowed() {
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).isBorrowed == true){
                System.out.println(items.get(i).getInfo());
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


            readInt = console.nextInt();
            readString = console.nextLine();
            switch (readInt){

                default: {
                    System.out.println("ERROR! INCORRECT INPUT");
                    break;
                }
            }
        }
    }
}

