package service;

import dao.VendingMachineAuditDao;
import dao.VendingMachineDao;
import dao.VendingMachinePersistenceException;
import dto.Item;

import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> getAvailableItems() throws VendingMachinePersistenceException {
        List<Item> allItems = dao.getAllItems();
        List<Item> availableItems = allItems.stream().filter(i -> i.getStockLeft()>0).collect(Collectors.toList());
        return availableItems;
    }

    @Override
    public Item buyItem(String code, int funds) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException {
        Item chosenItem = dao.getItem(code);
        if (chosenItem == null || chosenItem.getStockLeft() == 0){
            throw new NoItemInventoryException("ERROR: This item is not available to buy");
        }else if (chosenItem.getCost() > funds){
            throw new InsufficientFundsException("ERROR: You don't have enough money to buy this item!");
        }else{
            auditDao.writeAuditEntry("Item: "+ chosenItem.getCode() + ": "+ chosenItem.getName() + " bought");
            return dao.buyItem(code);
        }
    }

    @Override
    public void exitSequence() throws VendingMachinePersistenceException{
            dao.saveInventory();
    }

    @Override
    public void bootUpSequence() throws VendingMachinePersistenceException{
        dao.loadInventory();
    }
}
