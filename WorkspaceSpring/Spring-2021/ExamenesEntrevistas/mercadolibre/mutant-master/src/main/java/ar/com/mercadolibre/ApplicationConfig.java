/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.mercadolibre;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author palucero
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {  
   
    @Override
   public Set<Class<?>> getClasses() {
       Set<Class<?>> resources = new java.util.HashSet<>();
       resources.add(ar.com.mercadolibre.ServicioMuntate.class); 
       return resources;
   }
   
        
}
