/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newproj;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author tscos
 */
public class WriteToFile {

    String fileInput;
    String targetFile;
    String txtExtension = ".txt";
    boolean append = true;
    static HashMap<Integer, Users> usersToWrite;

    public WriteToFile(String fileInput, String targetFile, boolean append) {
        this.fileInput = fileInput;
        this.targetFile = targetFile;
        this.append = append;
        writeFile();
    }

    public WriteToFile(HashMap<Integer, Users> users, String targetFile) {

        this.usersToWrite = users;
        this.targetFile = targetFile;

        //System.out.println("From WTF class: " + users);

        HashMapWriter();
    }

    public void writeFile() {
        try {
            PrintWriter pw = null;
            pw = new PrintWriter(new FileOutputStream(targetFile + txtExtension, append));
            pw.println(fileInput);
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

    public void HashMapWriter() {
        try {

            PrintWriter pw = null;

            pw = new PrintWriter(new FileOutputStream(targetFile + txtExtension, append));

            for (Map.Entry<Integer, Users> entry : usersToWrite.entrySet()) {
                pw.write(entry.getKey() + ": " + entry.getValue());
            }

            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

//    public void DestinationsWriter(){
//        try {
//            PrintWriter pw = null;
//            pw = new PrintWriter(new FileOutputStream("dests.txt"));
//
//            for (Destinations desArray1 : desArray) {
//                String output = desArray1.getIndex() + "|" + desArray1.getDestName() + "|" + desArray1.getPrice() + "|" + desArray1.getNumSeatsAvailable();
//                pw.println(output);
//            }
//
//            pw.close();
//
//        } catch (FileNotFoundException ex) {
//            System.out.println("File is not found!");
//        }
//    }
    
}
