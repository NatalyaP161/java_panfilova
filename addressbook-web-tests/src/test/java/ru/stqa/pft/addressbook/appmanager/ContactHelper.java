package ru.stqa.pft.addressbook.appmanager;

import org.apache.xpath.SourceTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("middlename"),contactData.getMiddlename());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("address"),contactData.getAddres());
        type(By.name("home"),contactData.getHomephone());
        type(By.name("mobile"),contactData.getMobilephone());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void     initContactModification(int index) {
        click(By.xpath("//table[@id='maintable']/tbody/tr[" + index + "]/td[8]/a/img"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void createContact(ContactData contact, boolean b) {
        initContactCreation();
        fillContactForm(contact,true);
        submitCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.tagName("tr"));
        elements.remove(0);
        for (int i = 0; i < elements.size(); i++) {
            String text = elements.get(i).getText();
            WebElement lastnameElement = elements.get(i).findElement(By.xpath("//tr[" + (i+2) + "]//td[2]"));
            WebElement firstnameElement = elements.get(i).findElement(By.xpath("//tr[" + (i+2) + "]//td[3]"));
            String lastname = lastnameElement.getText();
            String firstname = firstnameElement.getText();
            ContactData contact = new ContactData(firstname,null, lastname,null,null,null,null);
            contacts.add(contact);
        }
        return contacts;
    }
}
