/* 
 * CSCI 4171 Assignment 3
 * Student: Qingqing Wu
 * ID: B00886956
 * Date: Mon, Oct. 30th, 2023
 */

import java.io.*;
import java.util.Random;

public class BridgeProcessingSimulation {

    public static void main(String[] args) {
        // Read the MAC addresses 
        // and their port numbers from BridgeFDB.txt
        String[] macAddr = new String[4];
        int[] portNum = new int[4];

        // read file BridgeFDB
        try (BufferedReader buffer = new BufferedReader(new FileReader("BridgeFDB.txt"))) {
            for (int i = 0; i < 4; i++) {
                macAddr[i] = buffer.readLine();
                portNum[i] = Integer.parseInt(buffer.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Create a random frame generator
        Random random = new Random();

        // write to output file
        try (PrintWriter writer = new PrintWriter("RandomFrames.txt")) {
            for (int i = 0; i < 100; i++) {
                // Generate random MAC addresses
                String srcMAC = macAddr[random.nextInt(4)];
                String destMAC = macAddr[random.nextInt(4)];

                // Generate a random arrival port number 1 to 4
                int arrPort = random.nextInt(4) + 1;

                // Print the src, dest, and port
                writer.println(srcMAC);
                writer.println(destMAC);
                writer.println(arrPort);

                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

