package uk.ac.warwick.cs126.stores;

import uk.ac.warwick.cs126.interfaces.IRestaurantStore;
import uk.ac.warwick.cs126.models.Cuisine;
import uk.ac.warwick.cs126.models.EstablishmentType;
import uk.ac.warwick.cs126.models.Place;
import uk.ac.warwick.cs126.models.PriceRange;
import uk.ac.warwick.cs126.models.Restaurant;
import uk.ac.warwick.cs126.models.RestaurantDistance;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;

import uk.ac.warwick.cs126.structures.MyArrayList;
import uk.ac.warwick.cs126.structures.BinarySearchTree;
import uk.ac.warwick.cs126.structures.HashMap;

import uk.ac.warwick.cs126.util.ConvertToPlace;
import uk.ac.warwick.cs126.util.HaversineDistanceCalculator;
import uk.ac.warwick.cs126.util.DataChecker;
import uk.ac.warwick.cs126.util.StringFormatter;
import uk.ac.warwick.cs126.util.Sorter;

public class RestaurantStore implements IRestaurantStore {

    private MyArrayList<Restaurant> restaurantArray;
    private DataChecker dataChecker;
    private BinarySearchTree<Long, Restaurant> restaurants;
    private HashMap<Long, Restaurant> blacklistedIDs;

    public RestaurantStore() {
        // Initialise variables here
        restaurantArray = new MyArrayList<>();
        dataChecker = new DataChecker();
        restaurants = new BinarySearchTree<>();
        blacklistedIDs = new HashMap<>();
    }

    public Restaurant[] loadRestaurantDataToArray(InputStream resource) {
        Restaurant[] restaurantArray = new Restaurant[0];

        try {
            byte[] inputStreamBytes = IOUtils.toByteArray(resource);
            BufferedReader lineReader = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(inputStreamBytes), StandardCharsets.UTF_8));

            int lineCount = 0;
            String line;
            while ((line = lineReader.readLine()) != null) {
                if (!("".equals(line))) {
                    lineCount++;
                }
            }
            lineReader.close();

            Restaurant[] loadedRestaurants = new Restaurant[lineCount - 1];

            BufferedReader csvReader = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(inputStreamBytes), StandardCharsets.UTF_8));

            String row;
            int restaurantCount = 0;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                if (!("".equals(row))) {
                    String[] data = row.split(",");

                    Restaurant restaurant = new Restaurant(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            Cuisine.valueOf(data[4]),
                            EstablishmentType.valueOf(data[5]),
                            PriceRange.valueOf(data[6]),
                            formatter.parse(data[7]),
                            Float.parseFloat(data[8]),
                            Float.parseFloat(data[9]),
                            Boolean.parseBoolean(data[10]),
                            Boolean.parseBoolean(data[11]),
                            Boolean.parseBoolean(data[12]),
                            Boolean.parseBoolean(data[13]),
                            Boolean.parseBoolean(data[14]),
                            Boolean.parseBoolean(data[15]),
                            formatter.parse(data[16]),
                            Integer.parseInt(data[17]),
                            Integer.parseInt(data[18]));

                    loadedRestaurants[restaurantCount++] = restaurant;
                }
            }
            csvReader.close();

            restaurantArray = loadedRestaurants;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return restaurantArray;
    }

    public boolean addRestaurant(Restaurant restaurant) {
        if (dataChecker.isValid(restaurant)) {
            if (blacklistedIDs.get(restaurant.getID()) == null) {
                if (restaurants.add(restaurant.getID(), restaurant) == false) {
                    blacklistedIDs.add(restaurant.getID(), restaurant);
                    restaurants.remove(restaurant.getID());
                    return false;
                }
                else {
                    return true;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean addRestaurant(Restaurant[] restaurants) {
        boolean returnValue = false;
        for (int i = 0; i < restaurants.length; i++) {
            returnValue = addRestaurant(restaurants[i]);
        }
        return returnValue;
    }

    public Restaurant getRestaurant(Long id) {
        return restaurants.get(id);
    }

    public Restaurant[] getRestaurants() {
        Restaurant[] sortedArray = new Restaurant[restaurants.getSize()];
        BinarySearchTree.inOrder(restaurants.getRoot(), sortedArray, 0);
        return sortedArray;
    }

    public Restaurant[] getRestaurants(Restaurant[] restaurants) {
        return Sorter.quickSortRestaurantsID(restaurants);
    }

    public Restaurant[] getRestaurantsByName() {
        Restaurant[] sortedArray = new Restaurant[restaurants.getSize()];
        BinarySearchTree.inOrder(restaurants.getRoot(), sortedArray, 0);
        return Sorter.quickSortRestaurantsName(sortedArray);
    }

    public Restaurant[] getRestaurantsByDateEstablished() {
        Restaurant[] sortedArray = new Restaurant[restaurants.getSize()];
        BinarySearchTree.inOrder(restaurants.getRoot(), sortedArray, 0);
        return Sorter.quickSortRestaurantsDate(sortedArray);
    }

    public Restaurant[] getRestaurantsByDateEstablished(Restaurant[] restaurants) {
        return Sorter.quickSortRestaurantsDate(restaurants);
    }

    public Restaurant[] getRestaurantsByWarwickStars() {
        Restaurant[] sortedArray = new Restaurant[restaurants.getSize()];
        BinarySearchTree.inOrder(restaurants.getRoot(), sortedArray, 0);
        return Sorter.quickSortRestaurantsStars(sortedArray);
    }

    public Restaurant[] getRestaurantsByRating(Restaurant[] restaurants) {
        return Sorter.quickSortRestaurantsRating(restaurants);
    }

    public RestaurantDistance[] getRestaurantsByDistanceFrom(float latitude, float longitude) {
        // TODO
        return new RestaurantDistance[0];
    }

    public RestaurantDistance[] getRestaurantsByDistanceFrom(Restaurant[] restaurants, float latitude, float longitude) {
        // TODO
        return new RestaurantDistance[0];
    }

    public Restaurant[] getRestaurantsContaining(String searchTerm) {
        // TODO
        // String searchTermConverted = stringFormatter.convertAccents(searchTerm);
        // String searchTermConvertedFaster = stringFormatter.convertAccentsFaster(searchTerm);
        return new Restaurant[0];
    }
}
