package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String addres;
    private final String homephone;
    private final String mobilephone;

    public ContactData(String firstname, String middlename, String lastname, String addres, String homephone, String mobilephone) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.addres = addres;
        this.homephone = homephone;
        this.mobilephone = mobilephone;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddres() {
        return addres;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getMobilephone() {
        return mobilephone;
    }
}
