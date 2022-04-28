/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
          String user = (String) session.getAttribute("username");
        String logout=request.getParameter("logout");
        if (logout== null) {
            if (user != null) {
                response.sendRedirect("admin"); // if user is not admin then filter will redirect it to inventory 
                return;
            }
            else{
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }}
        else{
            session.invalidate();
            
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username==null || username.equals("") || password==null || password.equals("")){
            request.setAttribute("ErrorMssg", true);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;}
        AccountService as = new AccountService();
        Users user = as.login(username, password);
        
        if (user == null) {
            request.setAttribute("ErrorMssg", true);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        if (!(user.getActive())) {
            request.setAttribute("ErrorMssgActive", true);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
         
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        
        
        if (user.getIsAdmin()) {
            response.sendRedirect("admin");
        }
        else {
            session.setAttribute("username", username);
            session.setAttribute("firstName", user.getFirstName());
            session.setAttribute("lastName", user.getLastName());
            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("lastName", user.getLastName());
            response.sendRedirect("inventory");
            
        }
        
    
    }

}
