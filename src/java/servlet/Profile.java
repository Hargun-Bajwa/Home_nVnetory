/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Items;
import models.Users;
import services.AccountService;
import services.InventoryService;

/**
 *
 * @author 839217
 */
public class Profile extends HttpServlet {

     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          HttpSession session = request.getSession(false);
         AccountService as=new AccountService();
        try {
            Users user = as.get((String) session.getAttribute("username"));
            
            request.setAttribute("userName", user.getUsername());
            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("lastName", user.getLastName());
            request.setAttribute("Email", user.getEmail());
            request.setAttribute("passWord", user.getPassword());
        } catch (Exception ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
             String username = (String)session.getAttribute("username");
             UserDB userDB = new UserDB();
             Users user = userDB.get(username);
             //admins should have access to the admin page where they can edit the info so no need for user page for them
             if(user.getIsAdmin()==true){
                 response.sendRedirect("admin");
                 return;
             }} catch (Exception ex) {
             Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        getServletContext().getRequestDispatcher("/WEB-INF/userAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        AccountService as = new AccountService();
        String username = (String)session.getAttribute("username");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String password = request.getParameter("password");
        boolean active =Boolean.parseBoolean(request.getParameter("active"));
        String action= request.getParameter("action");
        
        
        if(active==false&&action.equals("edit")){
            try {
                as.updateUser(username, password, email,firstName, lastName,active);
                response.sendRedirect("login");
                request.setAttribute("username", null);
                session.setAttribute("username", null);
                session.invalidate();
                return;
            } catch (Exception ex) {
                Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
            }
}
        if(action.equals("edit")){
            if(email==null|| firstName==null || lastName==null|| password==null ||email.equals("")|| firstName.equals("") || lastName.equals("")|| password.equals("")){
                    request.setAttribute("error",true);}
                    else{
                            try {
                            as.update(username, password, email,firstName, lastName);
              response.sendRedirect("inventory");
           try {
               InventoryService is = new InventoryService();
            List<Items> items = is.getAll(username);
            
            request.setAttribute("items", items);         
        } catch (Exception ex) {}
            request.setAttribute("username", username);
            session.setAttribute("username", username);
            return;
        } catch (Exception ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }}
            try{Users user = as.get(username);
             request.setAttribute("userName", user.getUsername());
            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("lastName", user.getLastName());
            request.setAttribute("Email", user.getEmail());
            request.setAttribute("passWord", user.getPassword());}
            catch(Exception e){}
    }}
}

