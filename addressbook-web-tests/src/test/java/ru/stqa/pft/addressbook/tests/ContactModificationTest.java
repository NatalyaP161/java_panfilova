package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData("Иван", "Васильевич", "Иванов", null, "221-65-52", "89185555550","456-55-51","test1"),
                    true);
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData(modifiedContact.getId(),"Иван", "Васильевич", "Иванов", "г. Орел, ул. Левый берег реки Оки, д. 23", "221-65-52",
                "89185555550",null,null);
        app.contact().modify(contact);
        app.goTo().HomePage();
        assertEquals(app.contact().count(),before.size());
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
