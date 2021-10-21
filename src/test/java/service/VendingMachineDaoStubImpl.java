package service;

import dao.VendingMachineDao;
import dao.VendingMachinePersistenceException;
import dto.Item;

import java.io.*;
import java.util.*;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    private Map<String, Item> inventory = new HashMap<>();

    public VendingMachineDaoStubImpl(){
        inventory.put("T1", new Item("T1","Test Bar",100,10));
        inventory.put("T2",new Item("T2","Test Crisps",60,1));
        inventory.put("T3", new Item("T3","Test Snack",130,0));
    }
    @Override
    public void loadInventory() throws VendingMachinePersistenceException {

    }

    @Override
    public void saveInventory() throws VendingMachinePersistenceException{

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
