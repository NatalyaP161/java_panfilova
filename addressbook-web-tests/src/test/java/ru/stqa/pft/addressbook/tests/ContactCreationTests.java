package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Иван", "Васильевич", "Иванов", "г. Орел, ул. Левый берег реки Оки", "221-65-52", "89185555550"));
        app.getContactHelper().submitCreation();
        app.getNavigationHelper().gotoHomePage();
    }

}
