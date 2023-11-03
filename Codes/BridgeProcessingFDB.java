/* 
 * CSCI 4171 Assignment 3
 * Student: Qingqing Wu
 * ID: B00886956
 * Date: Mon, Oct. 30th, 2023
 */

import java.io.*;
import java.util.HashMap;

public class BridgeProcessingFDB {

    public static void main(String[] args) {
        // Initialize FDB
        HashMap<String, Integer> fdb = new HashMap<>();

        // read BridgeFDB
        try (BufferedReader buffer = new BufferedReader(new FileReader("BridgeFDB.txt"))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                String macAddr = line;
                int portNum = Integer.parseInt(buffer.readLine());
                fdb.put(macAddr, portNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // read RandomFrames
        try (BufferedReader randomFramesReader = new BufferedReader(new FileReader("RandomFrames.txt"));
            PrintWriter outputWriter = new PrintWriter("BridgeOutput.txt");
            PrintWriter updateFDBWriter = new PrintWriter("BridgeFDBUpdate.txt")) {
            String line = randomFramesReader.readLine(); // get the first line
            while (line != null) {
                String srcMAC = line;
                String destMAC = randomFramesReader.readLine(); // dest mac
                int arrPort = Integer.parseInt(randomFramesReader.readLine()); // port

                String output = makeFrame(srcMAC, destMAC, arrPort, fdb);
                outputWriter.println(output);

                // Update the FDB based on the source MAC and arrival port
                fdb.put(srcMAC, arrPort);
                String updatedFDB = srcMAC + "\n" + arrPort;
                updateFDBWriter.println(updatedFDB);

                randomFramesReader.readLine(); // the empty line
                line = randomFramesReader.readLine(); // another src mac
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // process a frame and decide whether to forward, discard, or broadcast
    private static String makeFrame(String srcMAC, String destMAC, int arrPort, HashMap<String, Integer> fdb) {
        if (fdb.containsKey(srcMAC)) {
            int srcPort = fdb.get(srcMAC);
            if (srcPort == arrPort) {
                return srcMAC + "\t" + destMAC + "\t" + arrPort + "\tForwarded on port " + arrPort;
            } else {
                return srcMAC + "\t" + destMAC + "\t" + arrPort + "\tBroadcast";
            }
        } else {
            return srcMAC + "\t" + destMAC + "\t" + arrPort + "\tDiscarded";
        }
    }
}
