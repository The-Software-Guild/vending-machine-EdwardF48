package ui;

import dto.Coin;
import dto.Item;

import java.util.List;

public class VendingMachineView {

    private final UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void ShowItems(List<Item> itemList) {
        io.print("===INVENTORY===");
        itemList.stream().forEach(i -> io.print(i.toString()));
        io.readString("Please hit enter to continue.");
    }

    public int printMenuAndGetSelection(int funds) {
        io.print("Main Menu");
        io.print("1. Add Funds");
        io.print("2. View item Inventory");
        io.print("3. Buy Item");
        io.print("4. Get Change & Exit");
        io.print("Current funds: £" + String.format("%.2f", ((float) funds / 100)));
        return io.readInt("Please select from the above choices.", 1, 4);
    }

    public int getFundsEntered() {
        return io.readMoney("How much money have you put in the vending machine (£PP.pp):");
    }

    public void displayExitBanner() {
        io.print("Thanks for using the vending machine. Goodbye");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
        io.readString("Please hit enter to continue.");
    }

    public void displayBuyResult(Item boughtItem) {
        if (boughtItem == null) {
            io.print("No such item available");
        } else {
            io.print("Here is your " + boughtItem.getName() + "! Enjoy!");
        }
        io.readString("Please hit enter to continue.");
    }

    public String getItemCodeChoice() {
        return io.readString("Please Enter the code of the item you wish to buy: ");
    }

    public void displayBootUpBanner() {
        io.print("Welcome to the vending machine!");
    }

    public void displayChange(int[] coinChangeList) {
        io.print("Your change is:");
        for (int n = 0; n < coinChangeList.length; n++) {
            if (coinChangeList[n] != 0) {
                io.print(coinChangeList[n] + " " + Coin.values()[n].getName() + " coin(s)");
            }
        }
    }
}
