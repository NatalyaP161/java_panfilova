package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    public static void main(String args[]) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getMiddlename(), contact.getLastname(), contact.getAddres(), contact.getHomephone(),
                    contact.getMobilephone(), contact.getWorkPhone(), contact.getGroup(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getPhoto()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData(String.format("firstname %s", i),String.format("middlename %s", i),String.format("lastname %s", i), String.format("address %s", i), "221-65-52",
                    "89185555550","456-55-51","test1", String.format("email%s.ru", i), String.format("email2%s.ru", i), String.format("email3%s.ru", i), null));
        }
        return contacts;
    }
}
