/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newproj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tscos
 */
public class ReadFromFile {

    HashMap<Integer, Users> readUsers;
    String filePath;
    String stringOut;
    String fileExtension = ".txt";

    public ReadFromFile(String filePath) {
        this.filePath = filePath;
    }

    //Generic File Reader from week 2
    public void readFile() {

        try {
            BufferedReader br;// = null;

            br = new BufferedReader(new FileReader(filePath + fileExtension));
            String line;// = null;
            try {
                while ((line = br.readLine()) != null) {
                    System.out.println(line);

                }
            } catch (IOException ex) {
                Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //This returns a string from the inputed filepath, rather than sys.out etc
    public String readFileString() {

        try {
            BufferedReader br;

            br = new BufferedReader(new FileReader(filePath+fileExtension));
            String line;// = null;
            try {
                while ((line = br.readLine()) != null) {
                    stringOut = line;

                }
            } catch (IOException ex) {
                Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        return stringOut;

    }

    public void readIntoMap() {

        try {
            readUsers = new HashMap<Integer, Users>();

            BufferedReader br = null;

            // create BufferedReader object from the File
            br = new BufferedReader(new FileReader(filePath));

            String line;

            try {
                // read file line by line
                while ((line = br.readLine()) != null) {

                    // line.replace("(", ":");
                    // split the line by :
                    String[] parts = line.split(":");

                    // first part is name, second is number
                    Integer id = Integer.parseInt(parts[0].trim());//parts[0];// = parts[0].trim();
                    String name = parts[1].trim();

                    String password = parts[2].trim();

                    // put name, number in HashMap if they are
                    // not empty
                    if (!id.equals("") && !name.equals("")) {
                        readUsers.put(id, new Users(name, password));
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
