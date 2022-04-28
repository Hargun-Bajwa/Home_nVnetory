/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

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
import models.Users;
import services.AccountService;

/**
 *
 * @author 839217
 */
public class AdminServlet extends HttpServlet {

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String usernameSession = (String) session.getAttribute("username");
        if (session==null && (usernameSession==null || request.getAttribute("username")==null)){
                            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;}
        AccountService as = new AccountService();

        try {
            List<Users> users = as.getAll();
            request.setAttribute("users", users);         
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) {
            try {
                String username = (String) request.getParameter("username");
                Users user = as.get(username);
                if(user.getIsAdmin()){
                request.setAttribute("errorMssgAdmin",true);
                        
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);}
                else{
                request.setAttribute("selectedUser", user);}
                
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action != null && action.equals("delete")) {
            String username = (String) request.getParameter("username");
            
            try {
                Users user;
                user = as.get(username);
                
                if(user.getIsAdmin()){
                request.setAttribute("errorMssgAdmin",true);
                        
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);}
                else{
                as.delete(username);
                List<Users> users = as.getAll();
                request.setAttribute("users", users);
                
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                }
                
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action!=null && action.equals("changestatus"))
        {
        String username = (String) request.getParameter("username");
        try{Users user = as.get(username);
        if(user.getIsAdmin()){
           as.changeStatus(username, false);
           List<Users> users = as.getAll();
                request.setAttribute("users", users);
                
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }
        else{
            as.changeStatus(username, true);
        List<Users> users = as.getAll();
                request.setAttribute("users", users);
                
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);}
        }
        catch(Exception e){}}
        if (action!=null && action.equals("changeactive"))
        {
        String username = (String) request.getParameter("username");
        try{Users user = as.get(username);
        if(user.getActive()){
           as.changeActive(username, false);
           List<Users> users = as.getAll();
                request.setAttribute("users", users);
                
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }
        else{
            as.changeActive(username, true);}
        List<Users> users = as.getAll();
                request.setAttribute("users", users);
                
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }
        
        catch(Exception e){}}
        
        if (request.getParameter("logout")!=null){
                session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String action = request.getParameter("action");
        
        AccountService as = new AccountService();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String password = request.getParameter("password");
        
        try {
            switch (action) {
                case "add":
                    if(username==null|| firstName==null || lastName==null|| password==null || email==null ||username.equals("")|| firstName.equals("") || lastName.equals("")|| password.equals("") || username.equals("")){
                    request.setAttribute("errorMssg",true);}
                    else{
                    as.insert(username, password, email,firstName, lastName);
                    
                    break;}
                    
                case "update":
                    if(email==null|| firstName==null || lastName==null|| password==null ||email.equals("")|| firstName.equals("") || lastName.equals("")|| password.equals("")){
                    request.setAttribute("errorMssg",true);}
                    else{
                    as.update(username, password, email,firstName, lastName);
                    break;   }   

            }          
        }
        catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            List<Users> users = as.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       if (action != null && action.equals("delete")) {
            try {
                username = (String) request.getParameter("username");
                as.delete(username);
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

    }

    }


