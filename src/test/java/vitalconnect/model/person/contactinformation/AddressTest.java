package vitalconnect.model.person.contactinformation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static vitalconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_noParameter_success() {
        assertEquals(new Address().value, "");
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "A very long address that exceeds 50 character constraints. Dummy Dummy Dummy Dummy.";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // invalid addresses
        assertFalse(Address.isValidAddress(""));
        assertFalse(Address.isValidAddress(" "));
        // long address exceeding 50 characters
        assertFalse(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));

        // valid addresses
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress("-")); // one character

        // invalid edit address
        assertFalse(Address.isValidEditAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
        assertFalse(Address.isValidEditAddress(" "));

        // valid edit address
        assertTrue(Address.isValidEditAddress(""));
        assertTrue(Address.isValidEditAddress("Blk 456, Den Road, #01-355"));
    }

    @Test
    public void equals() {
        Address address = new Address("Valid Address");

        // same values -> returns true
        assertTrue(address.equals(new Address("Valid Address")));

        // same object -> returns true
        assertTrue(address.equals(address));

        // null -> returns false
        assertFalse(address.equals(null));

        // different types -> returns false
        assertFalse(address.equals(5.0f));

        // different values -> returns false
        assertFalse(address.equals(new Address("Other Valid Address")));
    }
}
