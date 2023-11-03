# BridgeProcessing
A program to simulate bridge-processing including bridge-learning

BridgeProcessingSimulation.java:
A program to randomly generate 100 frames. Each frame has a source MAC address and a destination MAC address. 
Both addresses must be from the list of addresses in the BridgeFDB.txt file. 
Each random entry also has the arrival port number. The arrival port number is 1, 2, 3 or 4. 
If the arrival port number matches the port number in the BridgeFDB.txt file, then the host has not moved from its original place. 
If the arrival port number is different from the port number in the BridgeFDB.txt file, then this host has moved to a different port.
Save the output from this program in a text file called RandomFrame.txt

BridgeProcessingFDB.java:
A program that reads the two text files, namely, BridgeFDB.txt and RandomFrames.txt, and for each
random frame, it should decide to forward/discard/broadcast the frame. 
If the source MAC address is not in the database, then it should update it. 
If the host has moved to a new port, again the FDB must be updated.
Ignore the CRC error detection part (that is, assume that the frames are error free). 
Store the resulting output in
another text file (BridgeOutput.txt).
