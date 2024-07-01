package ContactService;

import java.util.List;
import java.util.Vector;

public class ContactService {
    private final List<Contact> contacts = new Vector<>();

    public void addContact(Contact contact) {
        for (Contact c : contacts) {
            if (c.getContactId().equals(contact.getContactId())) {
                throw new IllegalArgumentException("Contact ID already exists");
            }
        }
        contacts.add(contact);
    }

    public void deleteContact(String contactId) {
        Contact contactToDelete = null;
        for (Contact c : contacts) {
            if (c.getContactId().equals(contactId)) {
                contactToDelete = c;
                break;
            }
        }
        if (contactToDelete != null) {
            contacts.remove(contactToDelete);
        } else {
            throw new IllegalArgumentException("Contact ID not found");
        }
    }

    public void updateContact(String contactId, String firstName, String lastName, String number, String address) {
        for (Contact c : contacts) {
            if (c.getContactId().equals(contactId)) {
                if (firstName != null) {
                    c.setFirstName(firstName);
                }
                if (lastName != null) {
                    c.setLastName(lastName);
                }
                if (number != null) {
                    c.setPhoneNumber(number);
                }
                if (address != null) {
                    c.setAddress(address);
                }
                return;
            }
        }
        throw new IllegalArgumentException("Contact ID not found");
    }

    public Contact getContact(String contactId) {
        for (Contact c : contacts) {
            if (c.getContactId().equals(contactId)) {
                return c;
            }
        }
        return null;
    }
}