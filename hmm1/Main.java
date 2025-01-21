import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // Initialize scanner to read the input file
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("hmm2_01.in")));

        // Read the matrices and observation data from the input
        String read1 = scanner.nextLine();
        String read2 = scanner.nextLine();
        String read3 = scanner.nextLine();
        String read4 = scanner.nextLine();

        // Create Matrix objects for A, B, and pi
        Matrix A = new Matrix(read1);
        Matrix B = new Matrix(read2);
        Matrix pi = new Matrix(read3);

        // Process the observation sequence
        String[] splitString = read4.split(" ");
        int numberOfObservations = Integer.parseInt(splitString[0]);
        Matrix listOfObservations = new Matrix(1, numberOfObservations);

        // Set the values of the observations in the matrix (1-based indexing)
        for (int i = 1; i <= numberOfObservations; i++) {
            listOfObservations.setValue(1, i, Integer.parseInt(splitString[i]) + 1);
        }

        // Create the ForwardAlgorithm object and calculate the alpha vector
        ForwardAlgorithm solution = new ForwardAlgorithm(A, B, pi, listOfObservations);
        Matrix alphaVector = solution.getAlphaVector(numberOfObservations);

        // Compute the sum of the alpha vector's values
        double sum = 0.0;
        for (int i = 1; i <= alphaVector.M; i++) {
            sum += alphaVector.getValue(i, 1);
        }

        // Output the result
        System.out.println(sum);
    }
}
