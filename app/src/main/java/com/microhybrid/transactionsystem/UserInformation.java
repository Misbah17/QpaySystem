package com.microhybrid.transactionsystem;

public class UserInformation {

    private String Name;
    private String Email;
    private String etconfirmpass;
    private  String PhoneNo;
    private String Address;
    private String Password;
    private String CompanyName;

    public UserInformation(String Name, String Email, String PhoneNo, String Address, String Password, String CompanyName) {
        this.Name = Name;
        this.Email = Email;
        this.PhoneNo = PhoneNo;
        this.Address = Address;
        this.Password = Password;
        this.CompanyName = CompanyName;
    }

    public UserInformation() {

    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEtconfirmpass() {
        return etconfirmpass;
    }

    public void setEtconfirmpass(String etconfirmpass) {
        this.etconfirmpass = etconfirmpass;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }
}