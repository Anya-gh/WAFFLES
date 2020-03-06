package uk.ac.warwick.cs126.util;
import uk.ac.warwick.cs126.models.Customer;
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
                if (CustomerCompare.compareID(customers.get(i), customers.get(pointer)) > 0) {
                    right.add(customers.get(i));
                }
                else if (CustomerCompare.compareID(customers.get(i), customers.get(pointer)) < 0) {
                    left.add(customers.get(i));
                }
            }
            return MyArrayList.concat(MyArrayList.concat(quickSortCustomersID(left), current), quickSortCustomersID(right));
        }
        else {
            return customers;
        }
        
    }

    public static Customer[] quickSortCustomersName(Customer[] customers) {
        MyArrayList<Customer> customerArrayList = new MyArrayList<>();
        for (int i = 0; i < customers.length; i++) {
            customerArrayList.add(customers[i]);
        }
        for (int i = 0; i < customerArrayList.size(); i++) {
            System.out.println(customerArrayList.get(i).getLastName());
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
                if (CustomerCompare.compareLastName(customers.get(i), customers.get(pointer)) > 0) {
                    right.add(customers.get(i));
                }
                else if (CustomerCompare.compareLastName(customers.get(i), customers.get(pointer)) < 0) {
                    left.add(customers.get(i));
                }
                else {
                    if (CustomerCompare.compareFirstName(customers.get(i), customers.get(pointer)) > 0) {
                        right.add(customers.get(i));
                    }
                    else if (CustomerCompare.compareFirstName(customers.get(i), customers.get(pointer)) < 0) {
                        left.add(customers.get(i));
                    }
                    else {
                        if (CustomerCompare.compareID(customers.get(i), customers.get(pointer)) > 0) {
                            right.add(customers.get(i));
                        }
                        else if (CustomerCompare.compareID(customers.get(i), customers.get(pointer)) < 0) {
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
}