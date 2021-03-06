package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class HashCracker {

    private String results[] = new String[2];
    private Map<String, String> map = new HashMap<>();
    Path directory = Paths.get("");
    String s = directory.toAbsolutePath().toString();

    void readRainbowTable() {

        try {
            BufferedReader in = new BufferedReader(new FileReader("tables.txt"));

            String line;
            while ((line = in.readLine()) != null) {
                String parts[] = line.split("  ");
                map.put(parts[1], parts[0]);
            }
            in.close();
        } catch (Exception e) {
            System.out.println("File tables.txt not found ");
            System.out.println("Please create a tables.txt file in the folowing location " + s + "\nThen run the program again");
            System.exit(0);
        }
    }


    ArrayList readHashesToCrack()  {

        ArrayList<String> hashesFromFile = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader("crackMe.txt"));

        String line;
        while ((line = in.readLine()) != null) {
            String parts = line;
            hashesFromFile.add(parts);
        }
        in.close();}
        catch (Exception e) {
            System.out.println("File crackMe.txt not found or File is empty \n ");
            System.out.println("Please create/populate a crackMe.txt file in the folowing location " + s + "\nThen run the program again");
            System.exit(0);

        }

        return hashesFromFile;
    }


    String[] searchForHash(String givenHashFromUser) {


        if (map.containsKey(givenHashFromUser)) {

            String plainTextValue = map.get(givenHashFromUser);
            results[0] = plainTextValue;
            results[1] = givenHashFromUser;

            return results;
        } else {
            return null;
        }
    }
}