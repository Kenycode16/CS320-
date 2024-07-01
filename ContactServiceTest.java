package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ContactService.Contact;
import ContactService.ContactService;

public class ContactServiceTest {
    private ContactService contactService;

    // Method to set up the ContactService instance before each test
    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
    }

    // Test method to verify adding a contact
    @Test
    public void testAddContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        assertEquals(contact, contactService.getContact("1234567890"));
    }

    // Test method to verify deleting a contact
    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.deleteContact("1234567890");
        assertNull(contactService.getContact("1234567890"));
    }

    // Test method to verify updating a contact
    @Test
    public void testUpdateContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.updateContact("1234567890", "Jane", "Doe", "0987654321", "456 Elm St");
        Contact updatedContact = contactService.getContact("1234567890");
        assertEquals("Jane", updatedContact.getFirstName());
        assertEquals("Doe", updatedContact.getLastName());
        assertEquals("0987654321", updatedContact.getPhoneNumber());
        assertEquals("456 Elm St", updatedContact.getAddress());
    }

    // Test method to verify adding a contact with a duplicate ID
    @Test
    public void testAddContactWithDuplicateId() {
        Contact contact1 = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        Contact contact2 = new Contact("1234567890", "Jane", "Doe", "0987654321", "456 Elm St");
        contactService.addContact(contact1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(contact2);
        });
        assertEquals("Contact ID already exists", exception.getMessage());
    }

    // Test method to verify updating a non-existent contact
    @Test
    public void testUpdateNonExistentContact() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContact("9999999999", "Jane", "Doe", "0987654321", "456 Elm St");
        });                               
        assertEquals("Contact ID not found", exception.getMessage());
    }

    // Test method to verify deleting a non-existent contact
    @Test
    public void testDeleteNonExistentContact() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            contactService.deleteContact("5654988788");
        });
        assertEquals("Contact ID not found", exception.getMessage());
    }
}
