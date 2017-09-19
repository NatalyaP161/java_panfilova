package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/6.png");
        ContactData contact = new ContactData("Иван", "Васильевич", "Абашев", null, "221-65-52", "89185555550","456-55-51","test1",
                "1.ru", "2.ru", "3.ru", photo);
        app.contact().create(contact,true);
        app.goTo().HomePage();
        assertEquals(app.contact().count(),before.size() + 1);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}
