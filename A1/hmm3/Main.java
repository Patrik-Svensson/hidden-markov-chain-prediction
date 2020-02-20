import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.Scanner;
public class Main{

    public static void main(String[] args) throws IOException {
        //BufferedReader KattisIN = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        Scanner scanner = new Scanner (System.in);

        String read1 = scanner.nextLine();
        String read2 = scanner.nextLine();
        String read3 = scanner.nextLine();
        String read4 = scanner.nextLine();

        /*String read1 = KattisIN.readLine();
        String read2 = KattisIN.readLine();
        String read3 = KattisIN.readLine();
        String read4 = KattisIN.readLine();*/
        Matrix A = new Matrix(read1);
        Matrix B = new Matrix(read2);
        Matrix pi = new Matrix(read3);

    

        String[] splitString = read4.split(" ");

        int numberOfObservations = Integer.parseInt(splitString[0]);
        Matrix listOfObservations = new Matrix(1,numberOfObservations);

        for (int i = 1; i <= numberOfObservations; i++) {
            listOfObservations.setValue(1, i, Integer.parseInt(splitString[i]) +1);
        }

        BaumWelchAlgorithm test = new BaumWelchAlgorithm(A, B, pi, listOfObservations);

        Lambda newLambdaApproximation;
        for (int i = 0; i < 50; i++) {

            newLambdaApproximation = test.getApproximatedLambda();
            System.out.println(i + "  dsd" );
            test = new BaumWelchAlgorithm(newLambdaApproximation.A, newLambdaApproximation.B, newLambdaApproximation.pi, listOfObservations);

        }
        
        System.out.println(test.pi);
    }


}
