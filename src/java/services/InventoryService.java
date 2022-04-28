/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoriesDB;
import dataaccess.ItemsDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Categories;
import models.Items;
import models.Users;

/**
 *
 * @author 839217
 */
public class InventoryService {
    // can pu su=ecurity in wither of get or delete, preffered is get, but i put it in delete
public Items get(int itemID, String username) throws Exception {
        UserDB userdb= new UserDB();
        ItemsDB itemsDB = new ItemsDB();
        Items item = itemsDB.get(itemID); 
        String oEmail = item.getOwner().getUsername();
          if(username != null && username.equals(oEmail)){
               
        return item;}
          else {return null;}
    }
    
    public List<Items> getAll(String username) throws Exception {
        ItemsDB itemsDB = new ItemsDB();
        List<Items> items = itemsDB.getAll(username);
        return items;
    }
    // format of items from sql .(`Category`,`ItemName`,`Price`,`Owner`), Owner is Foriegn key in SQL .(not in needed directly in models)
    public void insert(int categoryID, String itemName,double price, String owner) throws Exception {
        UserDB userdb= new UserDB();
        ItemsDB itemsDB = new ItemsDB();
        CategoriesDB catDB= new CategoriesDB();
        Items items = new Items(0,itemName,price);
        items.setOwner(userdb.get(owner));
        items.setCategory(catDB.get(categoryID));
        itemsDB.insert(items);
    }
    
// something i was trying but removed in the end becaue it did not worked
     public void update(int itemID, int category, String itemName,double price, String username) throws Exception {
        
        ItemsDB itemdb = new ItemsDB();
        Items item = new Items(itemID, itemName, price);
        item.setItemName(itemName);
        item.setPrice(price);
        CategoriesDB catDB= new CategoriesDB();
        item.setCategory(catDB.get(category));
        UserDB userdb= new UserDB();
        item.setOwner(userdb.get(username));
        
          String oEmail = item.getOwner().getUsername();
          if(username != null && username.equals(oEmail)){
        itemdb.update(item);}
    }
         
   
    
   public void delete(int itemID, String sUsername) throws Exception {        
        ItemsDB itemDB = new ItemsDB();
        Items items = itemDB.get(itemID); 
        String oEmail = items.getOwner().getUsername();
        if(sUsername != null && sUsername.equals(oEmail)){
            itemDB.delete(items);
        } else {
            return;
    }
        
    }
   
}
