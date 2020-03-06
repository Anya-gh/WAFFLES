package uk.ac.warwick.cs126.stores;

import uk.ac.warwick.cs126.interfaces.ICustomerStore;
import uk.ac.warwick.cs126.models.Customer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;

import uk.ac.warwick.cs126.structures.MyArrayList;
import uk.ac.warwick.cs126.structures.BinarySearchTree;
import uk.ac.warwick.cs126.structures.HashMap;
import uk.ac.warwick.cs126.structures.Pair;

import uk.ac.warwick.cs126.util.DataChecker;
import uk.ac.warwick.cs126.util.StringFormatter;
import uk.ac.warwick.cs126.util.Sorter;

public class CustomerStore implements ICustomerStore {

    private MyArrayList<Customer> customerArray;
    private DataChecker dataChecker;
    private BinarySearchTree<Long, Customer> customers;
    private HashMap<Long, Customer> blacklistedIDs;

    public CustomerStore() {
        // Initialise variables here
        customerArray = new MyArrayList<>();
        dataChecker = new DataChecker();
        customers = new BinarySearchTree<Long, Customer>();
        blacklistedIDs = new HashMap<Long, Customer>();
    }

    public Customer[] loadCustomerDataToArray(InputStream resource) {
        Customer[] customerArray = new Customer[0];

        try {
            byte[] inputStreamBytes = IOUtils.toByteArray(resource);
            BufferedReader lineReader = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(inputStreamBytes), StandardCharsets.UTF_8));

            int lineCount = 0;
            String line;
            while ((line=lineReader.readLine()) != null) {
                if (!("".equals(line))) {
                    lineCount++;
                }
            }
            lineReader.close();

            Customer[] loadedCustomers = new Customer[lineCount - 1];

            BufferedReader csvReader = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(inputStreamBytes), StandardCharsets.UTF_8));

            int customerCount = 0;
            String row;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                if (!("".equals(row))) {
                    String[] data = row.split(",");

                    Customer customer = (new Customer(
                            Long.parseLong(data[0]),
                            data[1],
                            data[2],
                            formatter.parse(data[3]),
                            Float.parseFloat(data[4]),
                            Float.parseFloat(data[5])));

                    loadedCustomers[customerCount++] = customer;
                }
            }
            csvReader.close();

            customerArray = loadedCustomers;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return customerArray;
    }

    public boolean addCustomer(Customer customer) {
        // TODO
        if (dataChecker.isValid(customer)) {
            if (blacklistedIDs.get(customer.getID()) == null) {
                if (customers.add(customer.getID(), customer) == false) {
                    blacklistedIDs.add(customer.getID(), customer);
                    customers.remove(customer.getID());
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

    public boolean addCustomer(Customer[] customers) {
        boolean returnValue = false;
        for (int i = 0; i < customers.length; i++) {
            returnValue = addCustomer(customers[i]);
        }
        return returnValue;
    }

    public Customer getCustomer(Long id) {
        return customers.get(id);
    }

    public Customer[] getCustomers() {
        Customer[] sortedArray = new Customer[customers.getSize()];
        MyArrayList<Pair<Long, Customer>> arrayList = BinarySearchTree.inOrder(customers.getRoot(), new MyArrayList<>());
        for (int i = 0; i < arrayList.size(); i++) {
            sortedArray[i] = arrayList.get(i).getValue();
        }
        return sortedArray;
    }

    public Customer[] getCustomers(Customer[] customers) {
        return Sorter.quickSortCustomersID(customers);
    }

    public Customer[] getCustomersByName() {
        Customer[] sortedArray = new Customer[customers.getSize()];
        MyArrayList<Pair<Long, Customer>> arrayList = BinarySearchTree.inOrder(customers.getRoot(), new MyArrayList<>());
        for (int i = 0; i < arrayList.size(); i++) {
            sortedArray[i] = arrayList.get(i).getValue();
        }
        return Sorter.quickSortCustomersName(sortedArray);
    }

    public Customer[] getCustomersByName(Customer[] customers) {
        return Sorter.quickSortCustomersName(customers);
    }

    public Customer[] getCustomersContaining(String searchTerm) {
        // TODO
        // String searchTermConverted = stringFormatter.convertAccents(searchTerm);
        String searchTermConvertedFaster = StringFormatter.convertAccentsFaster(searchTerm);
        MyArrayList<Customer> matchedCustomersList = BinarySearchTree.inOrderSearch(customers.getRoot(), new MyArrayList<>(), searchTermConvertedFaster);
        MyArrayList<Customer> sortedCustomersList = Sorter.quickSortCustomersName(matchedCustomersList);
        Customer[] sortedArray = new Customer[sortedCustomersList.size()];
        for (int i = 0; i < sortedCustomersList.size(); i++) {
            sortedArray[i] = sortedCustomersList.get(i);
        }
        return sortedArray;
        /*BinarySearchTree<String, Customer> byNameTree = new BinarySearchTree<>();
        BinarySearchTree.inOrderSearch(customers.getRoot(), customers, byNameTree, searchTermConvertedFaster);
        System.out.println(byNameTree.getSize());
        MyArrayList<Pair<String, Customer>> arrayList = BinarySearchTree.inOrder(byNameTree.getRoot(), new MyArrayList<>());
        for (int i = 0; i < arrayList.size(); i++) {
            sortedArray[i] = arrayList.get(i).getValue();
        }
        return sortedArray;*/
    }

}
