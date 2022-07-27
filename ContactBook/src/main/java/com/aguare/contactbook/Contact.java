package com.aguare.contactbook;

import org.json.simple.JSONObject;

/**
 *
 * @author marco
 */
public class Contact {

    private String name;
    private String tel;
    private String email;
    private String fb;

    public Contact(String name, String tel, String email, String fb) {
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.fb = fb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public JSONObject getJSON() {
        JSONObject contact = new JSONObject();
        contact.put("name", this.name);
        contact.put("tel", this.tel);
        contact.put("email", this.email);
        contact.put("fb", this.fb);
        JSONObject contactObj = new JSONObject();
        contactObj.put("contact", contact);
        return contactObj;
    }

    @Override
    public String toString() {
        return this.name + " \t " + tel + " \t " + email + " \t " + fb;
    }
}
