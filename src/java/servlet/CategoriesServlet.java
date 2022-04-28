/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Categories;
import services.AccountService;

/**
 *
 * @author 839217
 */
public class CategoriesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String usernameSession = (String) session.getAttribute("username");
        if (session==null && (usernameSession==null || request.getAttribute("username")==null)){
                            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;}
        AccountService as = new AccountService();

        try {
            List<Categories> categories = as.getAllCategories();
            request.setAttribute("categories", categories);         
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) {
            try {
                String categoryID = (String) request.getParameter("categoryID");
                Categories category = as.getCategory(parseInt(categoryID));
                
                request.setAttribute("selectedcategory", category);
                request.setAttribute("selectedName", category.getCategoryName());
                
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (request.getParameter("logout")!=null){
                session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
            getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String action = request.getParameter("action");
        
        AccountService as = new AccountService();
        String name = request.getParameter("catname");
        String categoryID =(request.getParameter("catID"));
        
        try {
            switch (action) {
                case "add":
                    if(name==null || name.equals("")){
                    request.setAttribute("errorMssg",true);}
                    else{
                        
                    as.insertCategory(name);
                    
                    break;}
                    
                case "update":
                    if(name==null || name.equals("")){
                    request.setAttribute("errorMssg",true);}
                    else{
                        int catID=parseInt(categoryID);
                        as.updateCategory(catID,name);
                    break;   }   

            }          
        }
        catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            List<Categories> categories = as.getAllCategories();
            request.setAttribute("categories", categories);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);

    }

   

}
