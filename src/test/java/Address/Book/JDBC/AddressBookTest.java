/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Address.Book.JDBC;

import org.junit.Test;

import Address.Book.JDBC.AddressBookService.IOService;
import junit.framework.Assert;

import static org.junit.Assert.*;

import java.util.List;

public class AddressBookTest {
	@Test
    public void givenEmpPayrollDataInDB_ShouldMatchEmpCount() {
    	AddressBookService addBookService = new AddressBookService();
    	List<AddressBookData> addBookData = addBookService.readAddresBookData(IOService.DB_IO);
    	Assert.assertEquals(6, addBookData.size());
    }
	
	@Test 
    public void givenNewCity_WhenUpdated_shouldMatchWithDB() {
    	AddressBookService addBookService = new AddressBookService();
    	addBookService.readAddresBookData(IOService.DB_IO);
    	addBookService.updateContactsCity("Soumik", "Noida");
    	AddressBookData contact = addBookService.checkAddressBookDataInSyncWithDB("Soumik");
    	Assert.assertEquals("Noida", contact.city);
    }
}
