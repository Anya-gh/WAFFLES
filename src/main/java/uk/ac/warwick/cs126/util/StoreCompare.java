package uk.ac.warwick.cs126.util;

import uk.ac.warwick.cs126.models.Customer;
import uk.ac.warwick.cs126.models.Favourite;
import uk.ac.warwick.cs126.models.Restaurant;
import uk.ac.warwick.cs126.models.RestaurantDistance;
import java.util.Date;

public class StoreCompare {
    
    public StoreCompare() {
    }

    public static int compareID(Customer customer1, Customer customer2) {
        Long firstCustomerID = customer1.getID();
        Long secondCustomerID = customer2.getID();
        return firstCustomerID.compareTo(secondCustomerID);
    }

    public static int compareID(Restaurant restaurant1, Restaurant restaurant2) {
        Long firstCustomerID = restaurant1.getID();
        Long secondCustomerID = restaurant2.getID();
        return firstCustomerID.compareTo(secondCustomerID);
    }

    public static int compareCustomerID(Favourite favourite1, Favourite favourite2) {
        Long firstCustomerID = favourite1.getCustomerID();
        Long secondCustomerID = favourite2.getCustomerID();
        return firstCustomerID.compareTo(secondCustomerID);
    }

    public static int compareRestaurantID(Favourite favourite1, Favourite favourite2) {
        Long firstRestaurantID = favourite1.getRestaurantID();
        Long secondRestaurantID = favourite2.getRestaurantID();
        return firstRestaurantID.compareTo(secondRestaurantID);
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

    public static int compareName(Restaurant restaurant1, Restaurant restaurant2) {
        String firstRestaurantName = restaurant1.getName();
        String secondRestaurantName = restaurant2.getName();
        return firstRestaurantName.compareTo(secondRestaurantName);
    }

    public static int compareDate(Restaurant restaurant1, Restaurant restaurant2) {
        Date firstRestaurantDate = restaurant1.getDateEstablished();
        Date secondRestaurantDate = restaurant2.getDateEstablished();
        return firstRestaurantDate.compareTo(secondRestaurantDate);
    }

    public static int compareDate(Favourite favourite1, Favourite favourite2) {
        Date firstDateFavourited = favourite1.getDateFavourited();
        Date secondDateFavourited = favourite2.getDateFavourited();
        return firstDateFavourited.compareTo(secondDateFavourited);
    }

    public static int compareStars(Restaurant restaurant1, Restaurant restaurant2) {
        Integer firstStars = restaurant1.getWarwickStars();
        Integer secondStars = restaurant2.getWarwickStars();
        return firstStars.compareTo(secondStars);
    }

    public static int compareRating(Restaurant restaurant1, Restaurant restaurant2) {
        Float firstRating = restaurant1.getCustomerRating();
        Float secondRating = restaurant2.getCustomerRating();
        return firstRating.compareTo(secondRating);
    }

    public static int compareDistance(RestaurantDistance restaurant1, RestaurantDistance restaurant2) {
        Float firstDistance = restaurant1.getDistance();
        Float secondDistance = restaurant2.getDistance();
        return firstDistance.compareTo(secondDistance);
    }

}