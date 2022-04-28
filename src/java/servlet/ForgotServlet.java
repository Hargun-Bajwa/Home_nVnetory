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
public class ForgotServlet extends HttpServlet {

   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid=request.getParameter("uuid");
        if(uuid!=null){
            getServletContext().getRequestDispatcher("/WEB-INF/newpasswordPage.jsp").forward(request, response);
            return;
        }
        else{
       getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);}
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        String username=request.getParameter("username");
        //email should not be empty
        String uuid=request.getParameter("uuid");
        if(uuid==null|| uuid.equals("")){
        if(username==null||username.equals("")){
            request.setAttribute("mssgTest", true);
        request.setAttribute("message","Username should not be empty");
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
        }
        else{
        
        String path= getServletContext().getRealPath("/WEB-INF");
        String url = request.getRequestURL().toString();
        try{as.reset(username,path,url);
        request.setAttribute("done", true);
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
        request.setAttribute("done", true);}
        catch (Exception ex) {
               Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }}
        
        else{
        String newpassword= request.getParameter("newPassword");
         
        if(newpassword==null || newpassword.equals("")){
        request.setAttribute("message","Password should not be empty");
        request.setAttribute("uuid", uuid);
        getServletContext().getRequestDispatcher("/WEB-INF/newpasswordPage.jsp").forward(request, response);
        }
       
        else{
        as.newPassword(uuid, newpassword);
        response.sendRedirect("login");
        }
                }
    }

}

