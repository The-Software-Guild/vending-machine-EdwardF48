package dao;

import dto.Item;

import java.util.List;

public interface VendingMachineDao {

    List<Item> getAllItems() throws VendingMachinePersistenceException;

    Item buyItem(String ItemCode) throws VendingMachinePersistenceException;

    void loadInventory() throws VendingMachinePersistenceException;

    void saveInventory() throws VendingMachinePersistenceException;

    Item getItem(String itemCode) throws VendingMachinePersistenceException;
}
