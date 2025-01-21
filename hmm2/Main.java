import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String read1 = scanner.nextLine();
        String read2 = scanner.nextLine();
        String read3 = scanner.nextLine();
        String read4 = scanner.nextLine();

        Matrix A = new Matrix(read1);
        Matrix B = new Matrix(read2);
        Matrix pi = new Matrix(read3);

        String[] splitString = read4.split(" ");

        int numberOfObservations = Integer.parseInt(splitString[0]);
        Matrix listOfObservations = new Matrix(1, numberOfObservations);

        for (int i = 1; i <= numberOfObservations; i++) {
            listOfObservations.setValue(1, i, Integer.parseInt(splitString[i]) + 1);
        }

        Sequence<Integer> stateSequence = HiddenMarkovModel.getStateSequence(A, B, pi, listOfObservations);

        for (int i = 1; i <= stateSequence.length; i++) {
            if (i != 1) {
                System.out.print(" ");
            }
            System.out.print(stateSequence.getValue(i) - 1);
        }
    }
}
