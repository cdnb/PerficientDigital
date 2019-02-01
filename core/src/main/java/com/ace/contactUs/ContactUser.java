package com.ace.contactUs;

public class ContactUser {

    private String firstName;
    private String lastname;
    private String workEmail;

    public ContactUser() {
    }

    public ContactUser(String firstName, String lastname, String workEmail) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.workEmail = workEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    @Override
    public String toString() {
        return "ContactUser{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", workEmail='" + workEmail + '\'' +
                '}';
    }
}