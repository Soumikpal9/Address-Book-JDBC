/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Address.Book.JDBC;

import org.junit.Test;

import Address.Book.JDBC.AddressBookService.IOService;
import junit.framework.Assert;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AddressBookTest {
	@Test
    public void givenEmpPayrollDataInDB_ShouldMatchEmpCount() {
    	AddressBookService addBookService = new AddressBookService();
    	List<AddressBookData> addBookData = addBookService.readAddressBookData(IOService.DB_IO);
    	Assert.assertEquals(6, addBookData.size());
    }
	
	@Test 
    public void givenNewCity_WhenUpdated_shouldMatchWithDB() {
    	AddressBookService addBookService = new AddressBookService();
    	addBookService.readAddressBookData(IOService.DB_IO);
    	addBookService.updateContactsCity("Soumik", "Noida");
    	AddressBookData contact = addBookService.checkAddressBookDataInSyncWithDB("Soumik");
    	Assert.assertEquals("Noida", contact.city);
    }
	
	@Test 
    public void givenDateRange_WhenRetrieved_ShouldMatchContactsCount() {
    	AddressBookService addBookService = new AddressBookService();
    	addBookService.readAddressBookData(IOService.DB_IO);
    	LocalDate startDate = LocalDate.of(2017, 01, 01);
    	LocalDate endDate = LocalDate.now();
    	List<AddressBookData> addBookData = addBookService.readAddressBookForDateRange(IOService.DB_IO, startDate, endDate);
    	Assert.assertEquals(6, addBookData.size());
    }
	
	@Test
    public void givenContactsData_WhenCountByCity_ShouldReturnProperValue() {
    	AddressBookService addBookService = new AddressBookService();
    	addBookService.readAddressBookData(IOService.DB_IO);
    	Map<String, Integer> countContactsByCity = addBookService.readCountContactsByCity(IOService.DB_IO);
    	Assert.assertTrue(countContactsByCity.get("Bangalore").equals(1) && countContactsByCity.get("Kolkata").equals(3) && countContactsByCity.get("Chennai").equals(1) && countContactsByCity.get("Siliguri").equals(1));
    }
	
	@Test
    public void givenContactsData_WhenCountByState_ShouldReturnProperValue() {
    	AddressBookService addBookService = new AddressBookService();
    	addBookService.readAddressBookData(IOService.DB_IO);
    	Map<String, Integer> countContactsByState = addBookService.readCountContactsByState(IOService.DB_IO);
    	Assert.assertTrue(countContactsByState.get("Karnataka").equals(1) && countContactsByState.get("West Bengal").equals(4) && countContactsByState.get("Tamilnadu").equals(1));
    }
	
	@Test
    public void givenNewContact_WhenAdded_ShouldSyncWithDB() {
    	AddressBookService addBookService = new AddressBookService();
    	addBookService.readAddressBookData(IOService.DB_IO);
    	addBookService.addContactToBook("Anirban", "Mukherjee", "Dhakuria", "Kolkata", "West Bengal", "700055", "9191919191", "anirban@gmail.com");
    	AddressBookData contact = addBookService.checkAddressBookDataInSyncWithDB("Anirban");
    	Assert.assertEquals("anirban@gmail.com", contact.email);
    }
}
