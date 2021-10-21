package service;

import dao.VendingMachineAuditDao;
import dao.VendingMachineDao;
import dao.VendingMachineDaoFileImpl;
import dao.VendingMachinePersistenceException;
import dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineServiceLayerImplTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();

        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }

    @Test
    void getAvailableItems() throws Exception {
        List<Item> availableItems = service.getAvailableItems();
        Item item1 = new Item("T1", "Test Bar", 100, 10);
        Item item2 = new Item("T2", "Test Crisps", 60, 1);
        Item item3 = new Item("T3", "Test Snack", 130, 0);

        assertEquals(2, availableItems.size());
        assertTrue(availableItems.contains(item1));
        assertTrue(availableItems.contains(item2));
        assertFalse(availableItems.contains(item3));
    }

    @Test
    void buyItem() {
        //Try code thats not there
        try {
            service.buyItem("T4", 150);
            fail("Item does not exist");
        } catch (InsufficientFundsException | VendingMachinePersistenceException e) {
            fail("Incorrect error");
        } catch (NoItemInventoryException e) {
            return;
        }

        //try code with no stock left
        try {
            service.buyItem("T3", 150);
            fail("Item does not exist");
        } catch (InsufficientFundsException | VendingMachinePersistenceException e) {
            fail("Incorrect error");
        } catch (NoItemInventoryException e) {
            return;
        }

        //try buy item with insufficient funds
        try {
            service.buyItem("T1", 80);
            fail("Item does not exist");
        } catch (NoItemInventoryException | VendingMachinePersistenceException e) {
            fail("Incorrect error");
        } catch (InsufficientFundsException e) {
            return;
        }
    }

}