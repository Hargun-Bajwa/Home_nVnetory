/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dataaccess.CategoriesDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Categories;
import models.Items;
import models.Users;
import services.AccountService;
import services.InventoryService;

/**
 *
 * @author 839217
 */
public class InventoryServlet extends HttpServlet {

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username= (String) session.getAttribute("username");
        InventoryService is = new InventoryService();
        UserDB db = new UserDB();
        try
        {Users user =db.get(username);
        session.setAttribute("username", username);
        request.setAttribute("username", username);
        }
        catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // getting connection with categoriesDB to display category names.
        CategoriesDB categoriesDB= new CategoriesDB();
        
        // making options in jsp to show all categories by name
        try{
            List<Categories> categories =  categoriesDB.getAll();
            request.setAttribute("categories", categories);         
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            List<Items> items = is.getAll(username);
            
            request.setAttribute("items", items);         
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) {
            try {
                String itemID = (String) request.getParameter("itemID");
                Items item = is.get(parseInt(itemID),username);
                                request.setAttribute("selecteditem", item);}
                
            catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }   }
        
        if (request.getParameter("logout")!=null){
                session.invalidate();
                session.setAttribute("username",null);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            session=request.getSession();
            
                }
            request.setAttribute("username",username);
            session.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
       String action = request.getParameter("action");
               
        String itemName = request.getParameter("itemName");
        String price = request.getParameter("itemPrice");
        String categoryID =request.getParameter("category");
        String username = (String) session.getAttribute("username");
        InventoryService is = new InventoryService();
        try {
            List<Items> items = is.getAll(username);
            request.setAttribute("items", items);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //int categoryID, String itemName,Double price, String username
                     
         
         try {
            switch (action) {
                case "add":
                    if(itemName==null ||  itemName.equals("")|| price==null || price.equals("")){
                    request.setAttribute("error",true);}
                    else{
                        int category=parseInt(categoryID);
                        double itemPrice=parseDouble(price);
                    is.insert(category,itemName,itemPrice,username);
                    List<Items> items = is.getAll(username);
                    request.setAttribute("items", items);
                    break;}
                case "delete":
                    int itemID = parseInt( request.getParameter("itemID"));
                    is.delete(itemID,username);
                    List<Items> items = is.getAll(username);
                    request.setAttribute("items", items);
                    break;
                case "update":
                    itemName = request.getParameter("itemName");
                    price = request.getParameter("itemPrice");
                    categoryID =request.getParameter("category");
                    username = (String) session.getAttribute("username");
                    itemID = parseInt( request.getParameter("itemID"));
                    if(itemName==null ||  itemName.equals("")|| price==null || price.equals("")){
                    request.setAttribute("error",true);}
                    else{
                        int category=parseInt(categoryID);
                        double itemPrice=parseDouble(price);
                    is.update(itemID, category, itemName, itemPrice, username);
                    items = is.getAll(username);
                    request.setAttribute("items", items);
                    break;}    
               
               
            }          
        }
             
        
        catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
        doGet(request, response);

    }

    

}
