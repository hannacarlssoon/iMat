package menu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Henrik on 24/02/2017.
 */
public class SubmenuModel {

    private List<String> fruits = new ArrayList<>();
    private List<String> meat = new ArrayList<>();
    private List<String> dairy = new ArrayList<>();
    private List<String> drinks = new ArrayList<>();
    private List<String> bread = new ArrayList<>();
    private List<String> pantry = new ArrayList<>();
    private List<String> sweets = new ArrayList<>();

    LinkedList<List<String>> submenus = new LinkedList<>();

    private List<String> submenu;

    Menu menu;

    public SubmenuModel(Menu menu){
        this.menu = menu;

        setFruits();
        setMeat();
        setDairy();
        setDrinks();
        setBread();
        setPantry();
        setSweets();

        //add empty list for favorites

        setSubmenus();
    }

    public void setSubmenu(int index){
        submenu = submenus.get(index);

        int i=0;
        for(String s:submenu){
            i = menu.paintSubmenuItem(s,i+5,10);
        }

    }

    public List<String> getSubmenu(){
        return submenu;
    }

    private void setSubmenus(){
        submenus.addLast(fruits);
        submenus.addLast(meat);
        submenus.addLast(dairy);
        submenus.addLast(drinks);
        submenus.addLast(bread);
        submenus.addLast(pantry);
        submenus.addLast(sweets);
        submenus.addLast(new ArrayList<>());
    }

    private void setFruits(){
        fruits.add("• Frukt");
        fruits.add("• Grönsaker");
        fruits.add("• Rotfrukter");
        fruits.add("• Örter");
        fruits.add("• Bär");
        fruits.add("• Exotisk frukter");
        fruits.add("• Citrusfrukter");
        fruits.add("• Meloner");
    }

    private void setMeat(){
        meat.add("• Kött");
        meat.add("• Kyckling");
        meat.add("• Fisk");
        meat.add("• Skaldjur");
    }

    private void setDairy(){
        dairy.add("• Mjölk");
        dairy.add("• Ägg");
        dairy.add("• Ost");
        dairy.add("• Yoghurt");
        dairy.add("• Fil");
    }

    private void setDrinks(){
        drinks.add("• Juice");
        drinks.add("• Kaffe & Te");
        drinks.add("• Läsk");
        drinks.add("• Sodavatten");
    }

    private void setBread(){
        bread.add("• Ljust bröd");
        bread.add("• Grovt bröd");
        bread.add("• Knäckebröd");
        bread.add("• Fikabröd");
    }

    private void setPantry(){
        pantry.add("• Bakning");
        pantry.add("• Bönor & Linser");
        pantry.add("• Nötter & Frön");
        pantry.add("• Kryddor");
        pantry.add("• Kaffe & Te");
        pantry.add("• Pasta");
        pantry.add("• Ris");
        pantry.add("• Potatis");
    }

    private void setSweets(){
        sweets.add("• Godis");
        sweets.add("• Snacks");
        sweets.add("• Kakor & bakverk");
    }

}
