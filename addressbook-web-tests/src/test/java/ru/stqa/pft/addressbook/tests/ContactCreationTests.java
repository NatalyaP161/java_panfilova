package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Иван", "Васильевич", "Абашев", null, "221-65-52", "89185555550","test1");
        app.getContactHelper().createContact(contact,true);
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() + 1);

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        after.sort(byId);
        contact.setId(after.get(after.size() - 1).getId());
        before.add(contact);
        before.sort(byId);
        Assert.assertEquals(before, after);
    }

}
