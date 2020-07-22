package com.example.lastone;

public class UserHelperClass
{
    String lic,phone_nubmer,email1,password;

    public UserHelperClass() {
    }

    public UserHelperClass(String lic, String phone_numer, String email1, String password) {
        this.lic = lic;
        this.phone_nubmer = phone_numer;
        this.email1 = email1;
        this.password = password;
    }

    public String getLic() {
        return lic;
    }

    public void setLic(String lic) {
        this.lic = lic;
    }

    public String getPhone_numer() {
        return phone_nubmer;
    }

    public void setPhone_numer(String phone_numer) {
        this.phone_nubmer = phone_numer;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
