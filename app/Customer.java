package com.microhybrid.login;

public class Customer {

    private  String etname;
    private String etmail;
    private  String etconfirmpass;
    private String edphone;
    private String  edcnic;
    private String etpass;

    public Customer() {


    }


    public Customer(String etname, String etmail, String edphone, String edcnic, String etpass) {
        this.etname = etname;
        this.etmail = etmail;
        this.edphone = edphone;
        this.edcnic = edcnic;
        this.etpass = etpass;
    }

    public String getEtname() {
        return etname;
    }

    public void setEtname(String etname) {
        this.etname = etname;
    }

    public String getEtmail() {
        return etmail;
    }

    public void setEtmail(String etmail) {
        this.etmail = etmail;
    }

    public String getEdphone() {
        return edphone;
    }

    public void setEdphone(String edphone) {
        this.edphone = edphone;
    }

    public String getEdcnic() {
        return edcnic;
    }

    public void setEdcnic(String edcnic) {
        this.edcnic = edcnic;
    }

    public String getEtpass() {
        return etpass;
    }

    public void setEtpass(String etpass) {
        this.etpass = etpass;
    }




}
