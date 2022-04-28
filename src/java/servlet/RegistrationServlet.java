/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

/**
 *
 * @author 839217
 */
public class RegistrationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid=request.getParameter("uuid");
        if(uuid!=null){
        
        getServletContext().getRequestDispatcher("/WEB-INF/verify.jsp").forward(request, response);
        
        request.setAttribute("registerDone", true);
        return;}
        getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("username");
        
        String uuid=request.getParameter("uuid");
        if(uuid==null|| uuid.equals("")){
       String fName=request.getParameter("firstname");
       String lName=request.getParameter("lastname");
       String email=request.getParameter("email");
       username=request.getParameter("username");
       String password=request.getParameter("password");
       AccountService as = new AccountService();
       if(email==null|| fName==null || lName==null|| password==null || username==null ||email.equals("")|| fName.equals("") || lName.equals("")|| password.equals("") || username.equals(""))
               {request.setAttribute("errorEmptyValue", true);
            getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
               return;}
        try {
            as.register(username, password, email,fName, lName);
            String path= getServletContext().getRealPath("/WEB-INF");
        String url = request.getRequestURL().toString();
            as.registerUUID(username, path, url);
           response.sendRedirect("login");
        } catch (Exception ex) {
            request.setAttribute("error", true);
            getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);}
        
        }
        else{
        
        AccountService as = new AccountService();
        as.verification(uuid);
        response.sendRedirect("login");
         request.setAttribute("registerDone", true);
        }
        
    }
}
