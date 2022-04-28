/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author 839217
 */
public class DBUtil extends HttpServlet {

    
    //entity  manager created.
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("HomeInventoryPU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }

  
}
