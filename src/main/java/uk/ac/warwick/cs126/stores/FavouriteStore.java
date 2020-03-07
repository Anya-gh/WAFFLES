package uk.ac.warwick.cs126.stores;

import uk.ac.warwick.cs126.interfaces.IFavouriteStore;
import uk.ac.warwick.cs126.models.Favourite;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;

import uk.ac.warwick.cs126.structures.MyArrayList;
import uk.ac.warwick.cs126.util.DataChecker;
import uk.ac.warwick.cs126.structures.BinarySearchTree;
import uk.ac.warwick.cs126.structures.HashMap;
import uk.ac.warwick.cs126.structures.Pair;

public class FavouriteStore implements IFavouriteStore {

    private MyArrayList<Favourite> favouriteArray;
    private DataChecker dataChecker;
    private BinarySearchTree<Long, Favourite> favourites;
    private HashMap<Long, Favourite> blacklistedIDs;
    private HashMap<Long, Pair<Long[], Favourite>> allFavourites;

    public FavouriteStore() {
        // Initialise variables here
        favouriteArray = new MyArrayList<>();
        dataChecker = new DataChecker();
        favourites = new BinarySearchTree<>();
        blacklistedIDs = new HashMap<>();
        allFavourites = new HashMap<>();
    }

    public Favourite[] loadFavouriteDataToArray(InputStream resource) {
        Favourite[] favouriteArray = new Favourite[0];

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

            Favourite[] loadedFavourites = new Favourite[lineCount - 1];

            BufferedReader csvReader = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(inputStreamBytes), StandardCharsets.UTF_8));

            int favouriteCount = 0;
            String row;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                if (!("".equals(row))) {
                    String[] data = row.split(",");
                    Favourite favourite = new Favourite(
                            Long.parseLong(data[0]),
                            Long.parseLong(data[1]),
                            Long.parseLong(data[2]),
                            formatter.parse(data[3]));
                    loadedFavourites[favouriteCount++] = favourite;
                }
            }
            csvReader.close();

            favouriteArray = loadedFavourites;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return favouriteArray;
    }

    public boolean addFavourite(Favourite favourite) {
        if (dataChecker.isValid(favourite)) {
            if (blacklistedIDs.get(favourite.getID()) == null) {
                /*Long[] idArray = {favourite.getRestaurantID(), favourite.getCustomerID()};
                Pair<Long[], Favourite> idPair = new Pair<>(idArray, favourite);
                allFavourites.add(favourite.getRestaurantID() + favourite.getCustomerID(), idPair);*/
                if (favourites.add(favourite.getID(), favourite) == false) {
                    blacklistedIDs.add(favourite.getID(), favourite);
                    favourites.remove(favourite.getID());
                    //allFavourites.remove(favourite.getRestaurantID() + favourite.getCustomerID(), idPair);
                    return false;
                }
                else {
                    /*Favourite oldestFavourite = allFavourites.getOldestFavourite(favourite.getRestaurantID() + favourite.getCustomerID(), idArray);
                    if (favourite.equals(oldestFavourite) == false) {
                        favourites.remove(favourite.getID());
                    }*/
                    return true;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean addFavourite(Favourite[] favourites) {
        boolean returnValue = true;
        for (int i = 0; i < favourites.length; i++) {
            returnValue = addFavourite(favourites[i]);
        }
        return returnValue;
    }

    public Favourite getFavourite(Long id) {
        return favourites.get(id);
    }

    public Favourite[] getFavourites() {
        Favourite[] sortedArray = new Favourite[favourites.getSize()];
        BinarySearchTree.inOrder(favourites.getRoot(), sortedArray, 0);
        return sortedArray;
    }

    public Favourite[] getFavouritesByCustomerID(Long id) {
        // TODO
        return new Favourite[0];
    }

    public Favourite[] getFavouritesByRestaurantID(Long id) {
        // TODO
        return new Favourite[0];
    }

    public Long[] getCommonFavouriteRestaurants(Long customer1ID, Long customer2ID) {
        // TODO
        return new Long[0];
    }

    public Long[] getMissingFavouriteRestaurants(Long customer1ID, Long customer2ID) {
        // TODO
        return new Long[0];
    }

    public Long[] getNotCommonFavouriteRestaurants(Long customer1ID, Long customer2ID) {
        // TODO
        return new Long[0];
    }

    public Long[] getTopCustomersByFavouriteCount() {
        // TODO
        return new Long[20];
    }

    public Long[] getTopRestaurantsByFavouriteCount() {
        // TODO
        return new Long[20];
    }
}
