package uk.ac.warwick.cs126.util;

import uk.ac.warwick.cs126.models.Customer;
import java.util.Date;

public class CustomerCompare {
    
    public CustomerCompare() {
    }

    public static int compareID(Customer customer1, Customer customer2) {
        Long firstCustomerID = customer1.getID();
        Long secondCustomerID = customer2.getID();
        return firstCustomerID.compareTo(secondCustomerID);
    }

    public static int compareFirstName(Customer customer1, Customer customer2) {
        String firstCustomerName = customer1.getFirstName();
        String secondCustomerName = customer2.getFirstName();
        return firstCustomerName.compareTo(secondCustomerName);
    }

    public static int compareLastName(Customer customer1, Customer customer2) {
        String firstCustomerName = customer1.getLastName();
        String secondCustomerName = customer2.getLastName();
        return firstCustomerName.compareTo(secondCustomerName);
    }

    public static int compareDateJoined(Customer customer1, Customer customer2) {
        Date firstCustomerDate = customer1.getDateJoined();
        Date secondCustomerDate = customer2.getDateJoined();
        return firstCustomerDate.compareTo(secondCustomerDate);
    }

}