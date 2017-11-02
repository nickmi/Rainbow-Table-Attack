package com.company;
import com.google.common.base.CharMatcher;
import com.google.common.hash.Hashing;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.util.Hashtable;

 class HashGen {


    private Hashtable<String, String> numbers
            = new Hashtable<>();

    private SecureRandom  rand = new SecureRandom();

    String[] tableGenerator() throws FileNotFoundException {

        int number = rand.nextInt(9999);
        String startingPlaintext = String.valueOf(number);
        String temp = "0";
        String startEnd[] = new String[2];
        startEnd[0] = startingPlaintext;
        for (int i = 0; i < 10000; i++) {

            temp = hashGenerator(startingPlaintext);
            numbers.put(temp, startingPlaintext);
            startingPlaintext = reduceHash(temp);

        }
        startEnd[1] = temp;
        return startEnd;
    }

    String[] tableGenerator(String startingPlaintext, String hashToCheck) throws FileNotFoundException {

        String temp = "0";
        String startEnd[] = new String[3];
        startEnd[0] = startingPlaintext;
        for (int i = 0; i < 10000; i++) {

            temp = hashGenerator(startingPlaintext);
            numbers.put(temp, startingPlaintext);
            startingPlaintext = reduceHash(temp);

        }
        startEnd[2] = numbers.get(hashToCheck);
        startEnd[1] = temp;
        return startEnd;
    }

    String reduceHash(String hash) {

        int number2 = rand.nextInt(6);

        String theDigits = CharMatcher.DIGIT.retainFrom(hash);
        String reducedHash = theDigits.substring(number2);
        if(reducedHash.length()>=4) {
            return reducedHash.substring(0, 4);
        }
       else

          return   reducedHash.substring(0, 3);

    }

    String hashGenerator(String plaintext) {

        return Hashing.md5()
                .hashString(plaintext, StandardCharsets.UTF_8)
                .toString();
    }

    void writeRainbowTables() throws FileNotFoundException {

        String data[] = tableGenerator();
        Path path = Paths.get("testing2.txt");
        Charset charset = Charset.forName("UTF-8");
        try {
            try (BufferedWriter writer = Files.newBufferedWriter(path, charset, StandardOpenOption.APPEND)) {
                writer.write(data[0] + "  " + data[1]);
                writer.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}





