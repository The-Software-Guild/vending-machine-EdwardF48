package dao;

import dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineDaoFileImplTest {

    VendingMachineDao testDao;

    public VendingMachineDaoFileImplTest() {
    }

    @BeforeEach
    void testSetUp() throws Exception{
        String testFile = "testinventory.txt";
        new FileWriter(testFile);
        PrintWriter out = new PrintWriter(new FileWriter(testFile));
        out.println("T1::Test Bar::100::10");
        out.println("T2::Test Crisps::60::1");
        out.println("T3::Test Snack::130::0");
        testDao = new VendingMachineDaoFileImpl(testFile);
    }

    @Test
    void testGetAllItems() throws Exception{
        String testFile = "testinventory.txt";
        PrintWriter out = new PrintWriter(new FileWriter(testFile));
        out.println("T1::Test Bar::100::10");
        out.println("T2::Test Crisps::60::1");
        out.println("T3::Test Snack::130::0");
        out.flush();
        out.close();
        Item item1 = new Item("T1","Test Bar",100,10);
        Item item2 = new Item("T2","Test Crisps",60,1);
        Item item3 = new Item("T3","Test Snack",130,0);
        testDao.loadInventory();

        List<Item> allItems = testDao.getAllItems();
        assertEquals(3, allItems.size());
        assertTrue(allItems.contains(item1));
        assertTrue(allItems.contains(item2));
        assertTrue(allItems.contains(item3));

    }

    @Test
    void testBuyItem() throws Exception{
        String testFile = "testinventory.txt";
        PrintWriter out = new PrintWriter(new FileWriter(testFile));
        out.println("T1::Test Bar::100::10");
        out.flush();
        out.close();
        Item item1 = new Item("T1","Test Bar",100,9);
        testDao.loadInventory();
        Item boughtItem = testDao.buyItem("T1");
        assertEquals(boughtItem, item1);
    }

    @Test
    void testGetItem() throws Exception{
        String testFile = "testinventory.txt";
        PrintWriter out = new PrintWriter(new FileWriter(testFile));
        out.println("T1::Test Bar::100::10");
        out.println("T2::Test Crisps::60::1");
        out.println("T3::Test Snack::130::1");
        out.flush();
        out.close();
        Item item1 = new Item("T1","Test Bar",100,10);

        testDao.loadInventory();
        Item retrievedItem = testDao.getItem("T1");
        assertEquals(retrievedItem,item1);
    }
}