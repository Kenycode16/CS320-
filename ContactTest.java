package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ContactService.Contact;

public class ContactTest {
    private Contact contact;

    @BeforeEach
    public void setUp() {
        contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
    }

    @Test
    public void testContactCreation() {
        assertNotNull(contact);
        assertEquals("1234567890", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhoneNumber());
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    public void testInvalidContactId() {
        assertAll("invalidContactId",
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Contact(null, "John", "Doe", "1234567890", "123 Main St");
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
                })
        );
    }

    @Test
    public void testInvalidFirstName() {
        assertAll("invalidFirstName",
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Contact("1234567890", null, "Doe", "1234567890", "123 Main St");
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Contact("1234567890", "ThisNameIsWayTooLong", "Doe", "1234567890", "123 Main St");
                })
        );
    }

    @Test
    public void testInvalidLastName() {
        assertAll("invalidLastName",
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Contact("1234567890", "John", null, "1234567890", "123 Main St");
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Contact("1234567890", "John", "ThisLastNameIsWayTooLong", "1234567890", "123 Main St");
                })
        );
    }

    @Test
    public void testInvalidPhoneNumber() {
        assertAll("invalidPhoneNumber",
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Contact("1234567890", "John", "Doe", null, "123 Main St");
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Contact("1234567890", "John", "Doe", "12345", "123 Main St");
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Contact("1234567890", "John", "Doe", "1234567890123", "123 Main St");
                })
        );
    }

    @Test
    public void testInvalidAddress() {
        assertAll("invalidAddress",
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Contact("1234567890", "John", "Doe", "1234567890", null);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Contact("1234567890", "John", "Doe", "1234567890", "This address is way too long for a valid address");
                })
        );
    }

    @Test
    public void testSetFirstName() {
        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName());
    }

    @Test
    public void testSetFirstNameInvalid() {
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("ThisNameIsWayTooLong"));
    }

    @Test
    public void testSetLastName() {
        contact.setLastName("Smith");
        assertEquals("Smith", contact.getLastName());
    }

    @Test
    public void testSetLastNameInvalid() {
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("ThisLastNameIsWayTooLong"));
    }

    @Test
    public void testSetPhoneNumber() {
        contact.setPhoneNumber("0987654321");
        assertEquals("0987654321", contact.getPhoneNumber());
    }

    @Test
    public void testSetPhoneNumberInvalid() {
        assertThrows(IllegalArgumentException.class, () -> contact.setPhoneNumber(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhoneNumber("12345"));
    }

    @Test
    public void testSetAddress() {
        contact.setAddress("456 Elm St");
        assertEquals("456 Elm St", contact.getAddress());
    }

    @Test
    public void testSetAddressInvalid() {
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress("This address is way too long for a valid address"));
    }

    @Test
    public void testGetters() {
        assertEquals("1234567890", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhoneNumber());
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    public void testSettersAndGetters() {
        contact.setFirstName("Alice");
        contact.setLastName("Johnson");
        contact.setPhoneNumber("1122334455");
        contact.setAddress("789 Oak St");

        assertAll("settersAndGetters",
                () -> assertEquals("Alice", contact.getFirstName()),
                () -> assertEquals("Johnson", contact.getLastName()),
                () -> assertEquals("1122334455", contact.getPhoneNumber()),
                () -> assertEquals("789 Oak St", contact.getAddress())
        );
    }

    @Test
    public void testEdgeCases() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "A", "B", "123456789", "123"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", "Firstname", "Lastname", "123456789", "123456789012345678901234567890123"));
    }
}