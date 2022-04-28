/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoriesDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Categories;
import models.Users;

/**
 *
 * @author 839217
 */

public class AccountService{
    public Users login(String username, String password) {
        UserDB userDB = new UserDB();
        
        try {
            Users user = userDB.get(username);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    public Users get(String username) throws Exception {
        UserDB userDB = new UserDB();
        Users user = userDB.get(username);
        return user;
    }
    
    public List<Users> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<Users> users = userDB.getAll();
        return users;
    }
    
    public void insert(String username, String password , String email , String firstName, String lastName) throws Exception {
        Users user = new Users(username, password , email, firstName, lastName, true , false);
        
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }
    
    public void update(String username, String password , String email , String firstName, String lastName) throws Exception {
        UserDB userdb = new UserDB();
        Users user = new Users(username, password , email, firstName, lastName, true , false);
        
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
          
        userdb.update(user);
    }
    
   public void delete(String username) throws Exception {        
        UserDB userDB = new UserDB();
        Users user = userDB.get(username);        
        userDB.delete(user);
    }

    public void updateStatus(String username) throws Exception{
       UserDB userDB = new UserDB();
        Users user = userDB.get(username);
        user.setActive(false);
        userDB.update(user);
    }
    public void updateUser(String username, String password , String email , String firstName, String lastName, boolean active) throws Exception {
        UserDB userdb = new UserDB();
        Users user = userdb.get(username);
        
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setActive(active);
        userdb.update(user);
    }
     
       public void register(String username, String password , String email , String firstName, String lastName) throws Exception {
        Users user = new Users(username, password , email, firstName, lastName, false, false);
        
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }
     
       
    // the categories stetings because admins should be able to add new categories
   public List<Categories> getAllCategories() throws Exception
    {
        CategoriesDB categoriesDB = new CategoriesDB();
        return categoriesDB.getAll();
    }
    
    
    public Categories getCategory(int id) throws Exception
    { 
        CategoriesDB categoriesDB = new CategoriesDB();
        return categoriesDB.get(id);
    }
    
    public void insertCategory(String name) throws Exception
    {
        Categories cat = new Categories();
                cat.setCategoryName(name);
        CategoriesDB categoriesDB = new CategoriesDB();
         categoriesDB.insert(cat);
    }
     public void updateCategory(int catID, String catName) throws Exception
    {  
        CategoriesDB categoriesDB = new CategoriesDB();
        Categories cat = new Categories(catID,catName);
        cat.setCategoryName(catName);
      
        
         categoriesDB.update(cat);
         
         
    }
     public void changeStatus(String username,boolean updatestatus) throws Exception {
        UserDB userdb = new UserDB();
        Users user=userdb.get(username);
        user.setIsAdmin(updatestatus);
        userdb.update(user);
     }
      public void changeActive(String username, boolean updateactive) throws Exception {
     UserDB userdb = new UserDB();
        Users user=userdb.get(username);
        user.setActive(updateactive);
        userdb.update(user);
     }
      
      public void reset(String username, String path, String url) {
        
           try{
           String uuid= UUID.randomUUID().toString();
           String fullURL = url+"?uuid="+uuid;
           
           UserDB userDB = new UserDB();

            Users user = userDB.get(username);
            
            
            String to = user.getEmail();
            String template = path + "/emailtemplates/resetTemplate.html";
            String subject = "home nVentory Password Reset";

            HashMap<String, String> tags = new HashMap<>();
            tags.put("firstname", user.getFirstName());
            tags.put("lastname", user.getLastName());
            tags.put("link", fullURL);

            GmailService.sendMail(to, subject, template, tags);

            user.setResetUUID(uuid);
            userDB.update(user);
           }catch(Exception ex){} 
}
       
     public void newPassword(String uuid, String password) {
        
        try {
            UserDB userDB = new UserDB();
            Users user = userDB.getUser(uuid);
            if(uuid.equals(user.getResetUUID())){
            user.setPassword(password);
            user.setResetUUID(null);
            userDB.update(user);
        }} catch (Exception e) {
        } }
     
     public void registerUUID(String username, String path, String url) {
        
           try{
           String uuid= UUID.randomUUID().toString();
           String fullURL = url+"?uuid="+uuid;
           
           UserDB userDB = new UserDB();

            Users user = userDB.get(username);
            
            
            String to = user.getEmail();
            String template = path + "/emailtemplates/registerTemplate.html";
            String subject = "Registration home nVentory";

            HashMap<String, String> tags = new HashMap<>();
            tags.put("firstname", user.getFirstName());
            tags.put("lastname", user.getLastName());
            tags.put("link", fullURL);

            GmailService.sendMail(to, subject, template, tags);

            user.setRegisterUUID(uuid);
            userDB.update(user);
           }catch(Exception ex){}
     } 
     public void verification(String uuid) {
        
        try {
            UserDB userDB = new UserDB();
            Users user = userDB.getUserbyRegisterUUID(uuid);
            
            if(uuid.equals(user.getRegisterUUID())){
            user.setActive(true);
            userDB.update(user);
            user.setRegisterUUID(null);
            userDB.update(user);
        }} catch (Exception e) {
        } }
}
