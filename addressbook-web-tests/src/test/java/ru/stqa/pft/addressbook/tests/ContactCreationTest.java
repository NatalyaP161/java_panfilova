package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().HomePage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData("Иван", "Васильевич", "Абашев", null, "221-65-52", "89185555550","test1");
        app.contact().create(contact,true);
        app.goTo().HomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
