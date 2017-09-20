package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/6.png");
        list.add(new Object[] {new ContactData("firstname1", "middlename1", "lastname1", "address1", "221-65-52", "89185555550",
                "456-55-51", "test1", "11.ru", "21.ru", "31.ru", photo)});
        list.add(new Object[] {new ContactData("firstname2", "middlename2", "lastname2", "address2", "221-65-52", "89185555550",
                "456-55-51", "test1", "12.ru", "22.ru", "32.ru", photo)});
        list.add(new Object[] {new ContactData("firstname3", "middlename3", "lastname3", "address3", "221-65-52", "89185555550",
                "456-55-51", "test3", "13.ru", "23.ru", "33.ru", photo)});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        app.contact().create(contact,true);
        app.goTo().HomePage();
        assertEquals(app.contact().count(),before.size() + 1);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}
