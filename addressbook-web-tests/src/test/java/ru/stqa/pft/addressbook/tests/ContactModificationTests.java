package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Иван", "Васильевич", "Иванов", null, "221-65-52", "89185555550","test1"),
                    true);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Иван", "Васильевич", "Иванов", "г. Орел, ул. Левый берег реки Оки, д. 23", "221-65-52",
                "89185555550",null),false);
        app.getGroupHelper().submitModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
