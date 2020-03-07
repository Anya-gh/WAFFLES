package uk.ac.warwick.cs126.util;

import uk.ac.warwick.cs126.interfaces.IDataChecker;

import uk.ac.warwick.cs126.models.Customer;
import uk.ac.warwick.cs126.models.Restaurant;
import uk.ac.warwick.cs126.models.Favourite;
import uk.ac.warwick.cs126.models.Review;
import uk.ac.warwick.cs126.structures.Dictionary;

import java.util.Date;

public class DataChecker implements IDataChecker {

    DataChecker dataChecker;
    public DataChecker() {
        // Initialise things here
    }

    public Long extractTrueID(String[] repeatedID) {
        Long returnValue = null;
        if (repeatedID != null && repeatedID.length == 3)
        {
          // checks if first equals second
          if (repeatedID[0].equals(repeatedID[1]))
          {
            returnValue = Long.parseLong(repeatedID[0]);
          }
          // checks if first equals third
          if (repeatedID[0].equals(repeatedID[2]))
          {
            returnValue = Long.parseLong(repeatedID[0]);
          }
          // checks if second equals third
          if (repeatedID[1].equals(repeatedID[2]))
          {
            returnValue = Long.parseLong(repeatedID[1]);
          }
        }
        return returnValue;
    }

    public boolean isValid(Long inputID) {
        boolean returnValue = false;
        String id = inputID.toString();
        String validDigits = "123456789";
        if (id.length() == 16) {
          returnValue = true;
          Dictionary<Character> idDict = new Dictionary<>();
          for (int i = 0; i < 16; i++) {
            char c = id.charAt(i);
            if (validDigits.indexOf(c) >= 0)
            {
              idDict.addEntry(c);
              int instances = idDict.getInstances(c);
              if (instances > 3) {
                returnValue = false;
                break;
              }
            }
            else {
              returnValue = false;
              break;
            }
          }
        }
        return returnValue;
    }

    public boolean isValid(Customer customer) {
        boolean returnValue = true;
        if (customer == null) {
          returnValue = false;
        }
        else {
          returnValue = this.isValid(customer.getID());
          if (customer.getFirstName() == null) {
            returnValue = false;
          }
          if (customer.getLastName() == null) {
            returnValue = false;
          }
          if (customer.getDateJoined() == null) {
            returnValue = false;
          }
          if (customer.getLatitude() == 0.0f) {
            returnValue = false;
          }
          if (customer.getLongitude() == 0.0f) {
            returnValue = false;
          }
        }
        return returnValue;
    }
    // getRepeatedID returns a string, not a string array for some reason
    // that's why extractTrueID keeps returning null.
    public boolean isValid(Restaurant restaurant) {
        boolean returnValue = true;
        if (restaurant == null) {
          returnValue = false;
        }
        else {
          if (this.extractTrueID(restaurant.getRepeatedID()) == null) {
            //System.out.println(this.extractTrueID(restaurant.getRepeatedID()));
            returnValue = false;
          }
          else {
            restaurant.setID(this.extractTrueID(restaurant.getRepeatedID()));
            returnValue = this.isValid(restaurant.getID());
          }
          if (restaurant.getLastInspectedDate() == null || restaurant.getDateEstablished() == null) {
            returnValue = false;
          }
          else if (restaurant.getLastInspectedDate().compareTo(restaurant.getDateEstablished()) < 0) {
            returnValue = false;
          }
          if (restaurant.getFoodInspectionRating() < 0 || restaurant.getFoodInspectionRating() > 5) {
            returnValue = false;
          }
          if (restaurant.getWarwickStars() < 0 || restaurant.getWarwickStars() > 3) {
            returnValue = false;
          }
          if (restaurant.getCustomerRating() != 0.0f && (restaurant.getCustomerRating() < 1.0f || restaurant.getCustomerRating() > 5.0f)) {
            returnValue = false;
          }
          if (restaurant.getName() == null) {
            returnValue = false;
          }
          if (restaurant.getOwnerFirstName() == null) {
            returnValue = false;
          }
          if (restaurant.getOwnerLastName() == null) {
            returnValue = false;
          }
          if (restaurant.getCuisine() == null) {
            returnValue = false;
          }
          if (restaurant.getEstablishmentType() == null) {
            returnValue = false;
          }
        }
        return returnValue;
    }

    public boolean isValid(Favourite favourite) {
      boolean returnValue = true;
      if (favourite == null) {
        returnValue = false;
      }
      else {
        returnValue = this.isValid(favourite.getID());
        returnValue = this.isValid(favourite.getCustomerID());
        returnValue = this.isValid(favourite.getRestaurantID());
        if (favourite.getDateFavourited() == null) {
          returnValue = false;
        }
      }
      return returnValue;
    }

    public boolean isValid(Review review) {
        boolean returnValue = true;
        if (review == null) {
          returnValue = false;
        }
        else {
          returnValue = this.isValid(review.getID());
          returnValue = this.isValid(review.getCustomerID());
          returnValue = this.isValid(review.getRestaurantID());
          if (review.getDateReviewed() == null) {
            returnValue = false;
          }
          if (review.getReview() == null) {
            returnValue = false;
          }
          if (review.getRating() < 1 || review.getRating() > 5) {
            returnValue = false;
          }
        }
        return returnValue;
    }
}
