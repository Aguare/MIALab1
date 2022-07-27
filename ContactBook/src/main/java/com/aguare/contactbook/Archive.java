package com.aguare.contactbook;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author marco
 */
public class Archive {

    //private final String path = "C:\\Users\\marco\\Documents\\contacts.json";
    private final String path = "contacts.json";

    private void createFile() {
        try {
            FileWriter writer = new FileWriter(path);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("");
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Contact> readFile() {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Contact> contacts = new ArrayList<>();
        try ( FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);
            JSONArray contactList = (JSONArray) obj;
            contactList.forEach(emp -> contacts.add(castContactObj((JSONObject) emp)));
        } catch (Exception e) {
            System.out.println("Error en la lectura del archivo");
        }
        return contacts;
    }

    public boolean saveList(Contact contact) {
        JSONArray contactList = new JSONArray();
        ArrayList<Contact> list = readFile();
        if (exists(contact, list)) {
            System.out.println("El registro ya existe");
            return false;
        } else {
            for (Contact c : list) {
                contactList.add(c.getJSON());
            }
            contactList.add(contact.getJSON());
            try ( FileWriter file = new FileWriter(path)) {
                file.write(contactList.toJSONString());
                file.flush();
            } catch (Exception e) {
                System.out.println("No se pudo guardar el archivo");
                return false;
            }
        }
        return true;
    }

    private boolean exists(Contact con, ArrayList<Contact> list) {
        for (Contact contact : list) {
            if (con.getName().equalsIgnoreCase(contact.getName())) {
                return true;
            }
        }
        return false;
    }

    private Contact castContactObj(JSONObject contact) {
        JSONObject contactObj = (JSONObject) contact.get("contact");
        String name = (String) contactObj.get("name");
        String tel = (String) contactObj.get("tel");
        String email = (String) contactObj.get("email");
        String fb = (String) contactObj.get("fb");
        return new Contact(name, tel, email, fb);
    }
}
