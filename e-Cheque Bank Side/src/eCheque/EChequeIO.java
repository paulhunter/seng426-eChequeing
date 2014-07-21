/**
 * EChequeIO.java
 * 
 * Created By: Basel
 * Last Edit: Paul Hunter
 * 
 * Created on March 4, 2007, 9:44 PM
 * Last edit on 2014-JULY-20
 */
package eCheque;

import java.io.*;

/**
 * The EChequeIO class is a static helper class used to serialize and
 * deserialize ECheque data structures to persistent storage.
 *
 * @version 0.01 JUL 2014
 * @author Basel
 *
 */
public class EChequeIO {

    /**
     * Write an ECheque structure to disk under a given filename.
     * @param cheque ECheque to be serialized.
     * @param filename Full filename path to file to be created.
     * @throws NullPointerException if filename is null.
     * @throws FileNotFoundException if file already exists of is a directory or
     * or other non-regular directory.
     * @throws SecurityException if the file or director is not in the permission
     * group of the user.
     * @throws IOException if there is an error during serialization or writing.
     * @throws InvalidClassException if the ECheque class violates serialization
     * rules.
     * @throws NotSerializableException if a member of the ECheque class violates
     * the serialization rules.
     */
    public static void writeECheque(ECheque cheque, String filename)
            throws NullPointerException, FileNotFoundException, 
            SecurityException, IOException, InvalidClassException, 
            NotSerializableException {

        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(new File(filename)));
        out.writeObject(cheque);
        out.close();
    }
    
    /**
     * Deserialize an ECheque structure from disk at a parameterized location.
     * @param filename location of the ECheque file.
     * @return The ECheque structure read from disk.
     * @throws NullPointerException if filename is null.
     * @throws FileNotFoundException if file does not exist.
     * @throws SecurityException if user does not have read permission to file.
     * @throws IOException if there is an error while reading the file; try again.
     * @throws ClassNotFoundException if the file read does not contain an 
     * ECheque structure.
     */
    public static ECheque readECheque(String filename) 
            throws NullPointerException, FileNotFoundException, 
            SecurityException, IOException, ClassNotFoundException {

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(filename)));//new File(filename))
        ECheque cheq = null;

        try {
            cheq = (ECheque) in.readObject();
        } catch (StreamCorruptedException e) {
            throw new ClassNotFoundException();
        } catch (InvalidClassException e) {
            throw new ClassNotFoundException();
        } catch (OptionalDataException e) {
            throw new ClassNotFoundException();
        } catch (ClassCastException e) {
            throw new ClassNotFoundException();
        } finally {
            in.close();
        }

        return cheq;
    }
}
