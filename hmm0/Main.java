import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // Create a scanner to read input data from the file
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("./tests/sample_00.in")));

        // Read the transition matrix, emission matrix, and initial state distribution data
        String transitionMatrixData = scanner.nextLine();
        String emissionMatrixData = scanner.nextLine();
        String initialStateDistributionData = scanner.nextLine();

        // Initialize matrices from the input data
        Matrix transitionMatrix = new Matrix(transitionMatrixData);
        Matrix emissionMatrix = new Matrix(emissionMatrixData);
        Matrix initialStateDistribution = new Matrix(initialStateDistributionData);

        // Perform matrix multiplications to compute the state distribution after transition
        // and the final emission distribution
        Matrix stateDistributionAfterTransition = initialStateDistribution.matrixMultiplication(transitionMatrix);
        Matrix finalEmissionDistribution = stateDistributionAfterTransition.matrixMultiplication(emissionMatrix);

        // Output the final emission distribution
        System.out.println(finalEmissionDistribution);
    }
}
