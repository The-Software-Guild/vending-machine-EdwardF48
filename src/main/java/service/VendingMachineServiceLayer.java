package service;

import dao.VendingMachinePersistenceException;
import dto.Item;

import java.util.List;

public interface VendingMachineServiceLayer {

    List<Item> getAvailableItems() throws VendingMachinePersistenceException;

    Item buyItem(String code, int funds) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException;

    void exitSequence() throws VendingMachinePersistenceException;

    void bootUpSequence() throws VendingMachinePersistenceException;
}
