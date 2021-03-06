/**
 * DigitalCertificateIO.java
 *
 * Created By: Unknown Last Edit: Paul Hunter
 *
 * Last edit on 2014-JULY-20
 *
 */
package eCheque;

import java.io.*;

/**
 * The DigitalCertificateIO Class offers a static read and write method to
 * serialize and deserialize a DigitalCerticate to and from storage.
 *
 * @author Paul
 */
public class DigitalCertificateIO {

    /**
     * Write a DigitalCertificate structure to disk under a given filename.
     *
     * @param certificate Digital Certificate to be serialized.
     * @param filename Full filename path to file to be created.
     * @throws NullPointerException if filename is null.
     * @throws FileNotFoundException if file already exists of is a directory or
     * or other non-regular directory.
     * @throws SecurityException if the file or director is not in the
     * permission group of the user.
     * @throws IOException if there is an error during serialization or writing.
     * @throws InvalidClassException if the ECheque class violates serialization
     * rules.
     * @throws NotSerializableException if a member of the ECheque class
     * violates the serialization rules.
     */
    public static void writeDigitalCertificate(DigitalCertificate certificate, String filename)
            throws NullPointerException, FileNotFoundException,
            SecurityException, IOException, InvalidClassException,
            NotSerializableException {

        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(new File(filename)));
        out.writeObject(certificate);
        out.close();
    }

    /**
     * Deserialize a Digital Certificate structure from disk at a parameterized
     * location.
     *
     * @param filename location of the Digital Certificate file.
     * @return The Digital Certificate structure read from disk.
     * @throws NullPointerException if filename is null.
     * @throws FileNotFoundException if file does not exist.
     * @throws SecurityException if user does not have read permission to file.
     * @throws IOException if there is an error while reading the file; try
     * again.
     * @throws ClassNotFoundException if the file read does not contain an
     * ECheque structure.
     */
    public static DigitalCertificate readDigitalCertificate(String filename)
            throws NullPointerException, FileNotFoundException,
            SecurityException, IOException, ClassNotFoundException {

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(filename)));
        DigitalCertificate DC = null;

        try {
            DC = (DigitalCertificate) in.readObject();
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
        return DC;
    }

}
