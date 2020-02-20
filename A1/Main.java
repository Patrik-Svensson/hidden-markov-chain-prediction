import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.Scanner;
public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader KattisIN = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        //Scanner scanner = new Scanner (new BufferedReader(new FileReader("sample_00.in")));

       // String read1 = scanner.nextLine();
       // String read2 = scanner.nextLine();
       //String read3 = scanner.nextLine();

        String read1 = KattisIN.readLine();
        String read2 = KattisIN.readLine();
        String read3 = KattisIN.readLine();
        Matrix A = new Matrix(read1);
        Matrix B = new Matrix(read2);
        Matrix pi = new Matrix(read3);
        
        Matrix changeStateDistribution = pi.matrixMultiplication(A);
        Matrix  emissionDistribution =  changeStateDistribution.matrixMultiplication(B);
        System.out.println(emissionDistribution);
        //Matrix C = A.matrixMultiplication(B);
       
    

      
    
    }
}