package com.company;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {

        CrackTrial CrTrial = new CrackTrial();
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while (flag == true) {
            System.out.println("Press 1 to crack md5 hashes\nPress 2 to generate rainbow tables\nPress 3 to exit");
            int userChoice = scanner.nextInt();

            switch (userChoice) {

                case 1: {
                    Path currentRelativePath = Paths.get("");
                    String s = currentRelativePath.toAbsolutePath().toString();
                    System.out.println("Current relative path is: " + s);

                    CrTrial.setCollisionsCounter(0);
                    CrTrial.check();

                    break;
                }

                case 2: {
                    Instant b = Instant.now();
                    ExecutorService executor = Executors.newFixedThreadPool(5);
                    for (int i = 0; i < 1000; i++) {
                        Runnable worker = new HashGen();
                        executor.execute(worker);
                    }
                    executor.shutdown();
                    while (!executor.isTerminated()) {
                    }
                    System.out.println("Finished all threads");
                    Instant e = Instant.now();
                    Duration timeElapsed = Duration.between(b, e);
                    System.out.println("elapsed time ( Seconds ):..."
                            +(timeElapsed.toMillis()));
                }



                break;

                case 3: {

                    flag = false;
                    break;
                }


                case 4: {//FOR PRESENTATION ONLY DONT USE IT
                    Instant b = Instant.now();
                    HashGen hs = new HashGen();
                    for (int i = 0; i < 1000; i++) {

                        hs.writeRainbowTables();
                    }
                    Instant e = Instant.now();
                    Duration timeElapsed = Duration.between(b, e);
                    System.out.println("elapsed time ( Seconds ):..."
                            +(timeElapsed.toMillis()));

                    break;
                }

                default:

                    break;


            }
        }

    }
}








