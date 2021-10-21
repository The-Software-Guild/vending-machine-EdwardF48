package controller;

import dao.VendingMachinePersistenceException;
import dto.Item;
import service.ChangeCalculator;
import service.InsufficientFundsException;
import service.NoItemInventoryException;
import service.VendingMachineServiceLayer;
import ui.VendingMachineView;

import java.util.List;

public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    private int funds = 0;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            bootUpSequence();
            while (keepGoing) {

                menuSelection = view.printMenuAndGetSelection(funds);

                switch (menuSelection) {
                    case 1:
                        AddFunds();
                        break;
                    case 2:
                        viewInventory();
                        break;
                    case 3:
                        buyItem();
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        view.displayUnknownCommandBanner();
                }
            }
            exitSequence(funds);
        } catch (
    VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void exitSequence(int funds) throws VendingMachinePersistenceException{
        service.exitSequence();
        int[] coinChangeList = ChangeCalculator.CalculateChange(funds);
        view.displayChange(coinChangeList);
        view.displayExitBanner();
    }

    private void bootUpSequence() throws VendingMachinePersistenceException {
        view.displayBootUpBanner();
        service.bootUpSequence();
    }

    public void AddFunds(){
        funds += view.getFundsEntered();
    }

    public void viewInventory() throws VendingMachinePersistenceException{
        List<Item> inventory = service.getAvailableItems();
        view.ShowItems(inventory);
    }

    public void buyItem() throws VendingMachinePersistenceException {
        viewInventory();
        try{
            String itemCode = view.getItemCodeChoice();
            Item boughtItem = service.buyItem(itemCode, funds);
            funds -= boughtItem.getCost();
            view.displayBuyResult(boughtItem);
        } catch(NoItemInventoryException|InsufficientFundsException e){
            view.displayErrorMessage(e.getMessage());
        }
    }
}
