/*
 * Copyright (C) 2016 Pivotal Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.arsw.myrestaurant.restcontrollers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.eci.arsw.myrestaurant.model.Order;
import edu.eci.arsw.myrestaurant.model.ProductType;
import edu.eci.arsw.myrestaurant.model.RestaurantProduct;
import edu.eci.arsw.myrestaurant.services.OrderServicesException;
import edu.eci.arsw.myrestaurant.services.RestaurantOrderServices;
import edu.eci.arsw.myrestaurant.services.RestaurantOrderServicesStub;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Juan
 */

@RestController
@RequestMapping(value = "/orders") 
 public class OrdersAPIController {
        
        @Autowired
        @Qualifier("Stub")
        RestaurantOrderServices restaurantorderservices;
        Gson gson = new Gson();
        
        @GetMapping("/{id}")
            public ResponseEntity<?> getOrder(@PathVariable int id) {
                if(restaurantorderservices.getTableOrder(id)!=null){
                    return new ResponseEntity<>(restaurantorderservices.getTableOrder(id),HttpStatus.ACCEPTED);
                }
                else{	
                    return new ResponseEntity<>("Error 404, esa orden no existe en el sistema",HttpStatus.NOT_FOUND);
                }
            
            }
        @GetMapping("/{id}/total")    
            public ResponseEntity<?> getOrderTotal(@PathVariable int id) {
                try {
                    return new ResponseEntity<>(restaurantorderservices.calculateTableBill(id),HttpStatus.ACCEPTED);
                } catch (OrderServicesException ex) {
                    Logger.getLogger(OrdersAPIController.class.getName()).log(Level.SEVERE, null, ex);
                    return new ResponseEntity<>("Error 404, esa orden no existe en el sistema",HttpStatus.NOT_FOUND);
                }
            }
 	@RequestMapping(method = RequestMethod.GET)
 	public ResponseEntity<?> GetOrders(){
         
                //restaurantorderservices.getTablesWithOrders();
 		try {
 			//obtener datos que se enviarán a través del API
 			return new ResponseEntity<>(restaurantorderservices.getAllTable(),HttpStatus.ACCEPTED);
 		} catch (Exception ex) {
 			
 			return new ResponseEntity<>("Error 404",HttpStatus.NOT_FOUND);
 		}  
 	}
        @RequestMapping(method = RequestMethod.POST)	
	public ResponseEntity<?> manejadorPostRecursoXX(@RequestBody Order newOrder){
		try {
			restaurantorderservices.addNewOrderToTable(newOrder);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception ex) {
			Logger.getLogger(OrdersAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error bla bla bla",HttpStatus.FORBIDDEN);            
		}        
	
	}
 }

