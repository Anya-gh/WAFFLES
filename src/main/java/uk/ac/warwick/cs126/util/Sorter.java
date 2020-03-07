package uk.ac.warwick.cs126.util;
import uk.ac.warwick.cs126.models.Customer;
import uk.ac.warwick.cs126.models.Restaurant;
import uk.ac.warwick.cs126.structures.MyArrayList;

public class Sorter {

    public Sorter() {
    }

    public static Customer[] quickSortCustomersID(Customer[] customers) {
        MyArrayList<Customer> customerArrayList = new MyArrayList<>();
        for (int i = 0; i < customers.length; i++) {
            customerArrayList.add(customers[i]);
        }
        customerArrayList = quickSortCustomersID(customerArrayList);
        Customer[] sortedArray = new Customer[customerArrayList.size()];
        for (int i = 0; i < customerArrayList.size(); i++) {
            sortedArray[i] = customerArrayList.get(i);
        }
        return sortedArray;
    }

    private static MyArrayList<Customer> quickSortCustomersID(MyArrayList<Customer> customers) {
        MyArrayList<Customer> left = new MyArrayList<>();
        MyArrayList<Customer> right = new MyArrayList<>();
        MyArrayList<Customer> current = new MyArrayList<>();
        int pointer = customers.size() / 2;
        current.add(customers.get(pointer));
        if (customers.size() > 1) {
            for (int i = 0; i < customers.size(); i++) {
                if (StoreCompare.compareID(customers.get(i), customers.get(pointer)) > 0) {
                    right.add(customers.get(i));
                }
                else if (StoreCompare.compareID(customers.get(i), customers.get(pointer)) < 0) {
                    left.add(customers.get(i));
                }
            }
            return MyArrayList.concat(MyArrayList.concat(quickSortCustomersID(left), current), quickSortCustomersID(right));
        }
        else {
            return customers;
        }
        
    }

    public static Restaurant[] quickSortRestaurantsID(Restaurant[] restaurants) {
        MyArrayList<Restaurant> restaurantArrayList = new MyArrayList<>();
        for (int i = 0; i < restaurants.length; i++) {
            restaurantArrayList.add(restaurants[i]);
        }
        restaurantArrayList = quickSortRestaurantsID(restaurantArrayList);
        Restaurant[] sortedArray = new Restaurant[restaurantArrayList.size()];
        for (int i = 0; i < restaurantArrayList.size(); i++) {
            sortedArray[i] = restaurantArrayList.get(i);
        }
        return sortedArray;
    }

    private static MyArrayList<Restaurant> quickSortRestaurantsID(MyArrayList<Restaurant> restaurants) {
        MyArrayList<Restaurant> left = new MyArrayList<>();
        MyArrayList<Restaurant> right = new MyArrayList<>();
        MyArrayList<Restaurant> current = new MyArrayList<>();
        int pointer = restaurants.size() / 2;
        current.add(restaurants.get(pointer));
        if (restaurants.size() > 1) {
            for (int i = 0; i < restaurants.size(); i++) {
                if (StoreCompare.compareID(restaurants.get(i), restaurants.get(pointer)) > 0) {
                    right.add(restaurants.get(i));
                }
                else if (StoreCompare.compareID(restaurants.get(i), restaurants.get(pointer)) < 0) {
                    left.add(restaurants.get(i));
                }
            }
            return MyArrayList.concat(MyArrayList.concat(quickSortRestaurantsID(left), current), quickSortRestaurantsID(right));
        }
        else {
            return restaurants;
        }
        
    }

    public static Customer[] quickSortCustomersName(Customer[] customers) {
        MyArrayList<Customer> customerArrayList = new MyArrayList<>();
        for (int i = 0; i < customers.length; i++) {
            customerArrayList.add(customers[i]);
        }
        customerArrayList = quickSortCustomersName(customerArrayList);
        Customer[] sortedArray = new Customer[customerArrayList.size()];
        for (int i = 0; i < customerArrayList.size(); i++) {
            sortedArray[i] = customerArrayList.get(i);
        }
        return sortedArray;
    }

    public static MyArrayList<Customer> quickSortCustomersName(MyArrayList<Customer> customers) {
        MyArrayList<Customer> left = new MyArrayList<>();
        MyArrayList<Customer> right = new MyArrayList<>();
        MyArrayList<Customer> current = new MyArrayList<>();
        int pointer = customers.size() / 2;
        current.add(customers.get(pointer));
        if (customers.size() > 1) {
            for (int i = 0; i < customers.size(); i++) {
                if (StoreCompare.compareLastName(customers.get(i), customers.get(pointer)) > 0) {
                    right.add(customers.get(i));
                }
                else if (StoreCompare.compareLastName(customers.get(i), customers.get(pointer)) < 0) {
                    left.add(customers.get(i));
                }
                else {
                    if (StoreCompare.compareFirstName(customers.get(i), customers.get(pointer)) > 0) {
                        right.add(customers.get(i));
                    }
                    else if (StoreCompare.compareFirstName(customers.get(i), customers.get(pointer)) < 0) {
                        left.add(customers.get(i));
                    }
                    else {
                        if (StoreCompare.compareID(customers.get(i), customers.get(pointer)) > 0) {
                            right.add(customers.get(i));
                        }
                        else if (StoreCompare.compareID(customers.get(i), customers.get(pointer)) < 0) {
                            left.add(customers.get(i));
                        }
                    }
                }
            }
            return MyArrayList.concat(MyArrayList.concat(quickSortCustomersName(left), current), quickSortCustomersName(right));
        }
        else {
            return customers;
        }
    }

    public static Restaurant[] quickSortRestaurantsName(Restaurant[] restaurants) {
        MyArrayList<Restaurant> restaurantArrayList = new MyArrayList<>();
        for (int i = 0; i < restaurants.length; i++) {
            restaurantArrayList.add(restaurants[i]);
        }
        restaurantArrayList = quickSortRestaurantsName(restaurantArrayList);
        Restaurant[] sortedArray = new Restaurant[restaurantArrayList.size()];
        for (int i = 0; i < restaurantArrayList.size(); i++) {
            sortedArray[i] = restaurantArrayList.get(i);
        }
        return sortedArray;
    }

    private static MyArrayList<Restaurant> quickSortRestaurantsName(MyArrayList<Restaurant> restaurants) {
        MyArrayList<Restaurant> left = new MyArrayList<>();
        MyArrayList<Restaurant> right = new MyArrayList<>();
        MyArrayList<Restaurant> current = new MyArrayList<>();
        int pointer = restaurants.size() / 2;
        current.add(restaurants.get(pointer));
        if (restaurants.size() > 1) {
            for (int i = 0; i < restaurants.size(); i++) {
                if (StoreCompare.compareName(restaurants.get(i), restaurants.get(pointer)) > 0) {
                    right.add(restaurants.get(i));
                }
                else if (StoreCompare.compareName(restaurants.get(i), restaurants.get(pointer)) < 0) {
                    left.add(restaurants.get(i));
                }
                else {
                    if (StoreCompare.compareID(restaurants.get(i), restaurants.get(pointer)) > 0) {
                        right.add(restaurants.get(i));
                    }
                    else if (StoreCompare.compareID(restaurants.get(i), restaurants.get(pointer)) < 0) {
                        left.add(restaurants.get(i));
                    }
                }
            }
            return MyArrayList.concat(MyArrayList.concat(quickSortRestaurantsName(left), current), quickSortRestaurantsName(right));
        }
        else {
            return restaurants;
        }
    }

    public static Restaurant[] quickSortRestaurantsDate(Restaurant[] restaurants) {
        MyArrayList<Restaurant> restaurantArrayList = new MyArrayList<>();
        for (int i = 0; i < restaurants.length; i++) {
            restaurantArrayList.add(restaurants[i]);
        }
        restaurantArrayList = quickSortRestaurantsDate(restaurantArrayList);
        Restaurant[] sortedArray = new Restaurant[restaurantArrayList.size()];
        for (int i = 0; i < restaurantArrayList.size(); i++) {
            sortedArray[i] = restaurantArrayList.get(i);
        }
        return sortedArray;
    }

    private static MyArrayList<Restaurant> quickSortRestaurantsDate(MyArrayList<Restaurant> restaurants) {
        MyArrayList<Restaurant> left = new MyArrayList<>();
        MyArrayList<Restaurant> right = new MyArrayList<>();
        MyArrayList<Restaurant> current = new MyArrayList<>();
        int pointer = restaurants.size() / 2;
        current.add(restaurants.get(pointer));
        if (restaurants.size() > 1) {
            for (int i = 0; i < restaurants.size(); i++) {
                if (StoreCompare.compareDateEstablished(restaurants.get(i), restaurants.get(pointer)) > 0) {
                    right.add(restaurants.get(i));
                }
                else if (StoreCompare.compareDateEstablished(restaurants.get(i), restaurants.get(pointer)) < 0) {
                    left.add(restaurants.get(i));
                }
                else {
                    if (StoreCompare.compareName(restaurants.get(i), restaurants.get(pointer)) > 0) {
                        right.add(restaurants.get(i));
                    }
                    else if (StoreCompare.compareName(restaurants.get(i), restaurants.get(pointer)) < 0) {
                        left.add(restaurants.get(i));
                    }
                    else {
                        if (StoreCompare.compareID(restaurants.get(i), restaurants.get(pointer)) > 0) {
                            right.add(restaurants.get(i));
                        }
                        else if (StoreCompare.compareName(restaurants.get(i), restaurants.get(pointer)) < 0) {
                            left.add(restaurants.get(i));
                        }
                    }
                }
            }
            return MyArrayList.concat(MyArrayList.concat(quickSortRestaurantsName(left), current), quickSortRestaurantsName(right));
        }
        else {
            return restaurants;
        }
    }

}