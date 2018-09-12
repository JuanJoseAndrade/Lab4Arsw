package edu.eci.arsw.myrestaurant.test;

import edu.eci.arsw.myrestaurant.beans.BillCalculator;
import edu.eci.arsw.myrestaurant.model.Order;
import edu.eci.arsw.myrestaurant.services.OrderServicesException;
import edu.eci.arsw.myrestaurant.services.RestaurantOrderServicesStub;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class ApplicationServicesTests {
    
    @Autowired
    @Qualifier("Stub")
    RestaurantOrderServicesStub ros;
    @Autowired
    @Qualifier("Basic")
    BillCalculator calcBasic ;
    //Suponiendo que se usará el calculador de factores con impuestos y calculador tributario de 2016 de Colombia
    //El siguiente test mira si efectivamente se están calculando los impuestos teniendo en cuenta si es bebida o no
    @Test
    public void valoresFrontera() throws OrderServicesException{
        Order o = new Order(4);
        o.addDish("BEER", 2);
        o.addDish("COKE", 2);
        Order o2 = new Order(5);
        o2.addDish("PIZZA", 3);
        o2.addDish("HOTDOG", 6);
        o2.addDish("HAMBURGER", 4);
        ros.addNewOrderToTable(o);
        ros.addNewOrderToTable(o2);
        assertEquals(ros.calculateTableBill(4),Math.round(1.19*calcBasic.calculateBill(o, ros.getAllProducts())));
        assertEquals(ros.calculateTableBill(5),Math.round(1.16*calcBasic.calculateBill(o, ros.getAllProducts())));
    }
    
}
