import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.io.*;

/**
 * <p>
 *     Simple test for testing functionalities of ObjectStreams on INDArray objects.
 * </p>
 */
public class Test {

    public static void main(String[] args) {
        INDArray matrix = Nd4j.create(3, 3);
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("testFile.txt"))) {
            os.writeInt(10);
            os.writeChar('\n');
            os.writeObject("Ime Prezime");
            os.writeChar('\n');
            os.writeObject(matrix);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("testFile.txt"))) {
            int i = is.readInt();
            char nl1 = is.readChar();
            String str = (String) is.readObject();
            char nl2 = is.readChar();
            INDArray readMatrix = (INDArray) is.readObject();

            System.out.println("|" + i + "|");
            System.out.println("|" + nl1 + "|");
            System.out.println("|" + str + "|");
            System.out.println("|" + nl2 + "|");
            System.out.println("==========");
            System.out.println(readMatrix);
            System.out.println("==========");
            System.out.println(readMatrix.toStringFull());
            System.out.println("==========");
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
