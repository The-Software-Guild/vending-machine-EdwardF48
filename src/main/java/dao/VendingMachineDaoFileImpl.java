package dao;

import dto.Item;

import java.io.*;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao{

    private Map<String, Item> inventory = new HashMap<>();
    private final String INVENTORY_FILE;
    public static final String DELIMITER = "::";

    public VendingMachineDaoFileImpl(){
        INVENTORY_FILE = "inventory.txt";
    }
    public VendingMachineDaoFileImpl(String inventoryTextFile){
        INVENTORY_FILE = inventoryTextFile;
    }

    private Item unmarshallItem(String itemAsText){
        //Code::Name::Cost::Stock
        String[] itemTokens = itemAsText.split(DELIMITER);
        Item itemFromFile = new Item(itemTokens[0],itemTokens[1],Integer.parseInt(itemTokens[2]),Integer.parseInt(itemTokens[3]));
        return itemFromFile;
    }

    @Override
    public void loadInventory() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }

        String currentLine;
        Item currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            inventory.put(currentItem.getCode(), currentItem);
        }
        scanner.close();
    }

    private String marshallItem(Item aItem){
        //Code::Name::Cost::Stock
        String itemAsText = aItem.getCode() + DELIMITER;
        itemAsText += aItem.getName() + DELIMITER;
        itemAsText += aItem.getCost() + DELIMITER;
        itemAsText += aItem.getStockLeft();

        return itemAsText;
    }

    @Override
    public void saveInventory() throws VendingMachinePersistenceException{
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save item data.", e);
        }
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return new ArrayList(inventory.values());
    }

    @Override
    public Item buyItem(String itemCode) throws VendingMachinePersistenceException {
        inventory.get(itemCode).setStockLeft(inventory.get(itemCode).getStockLeft() -1);
        saveInventory();
        return inventory.get(itemCode);
    }

    @Override
    public Item getItem(String itemCode) throws VendingMachinePersistenceException {
        return inventory.get(itemCode);
    }
}
