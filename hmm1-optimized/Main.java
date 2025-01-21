import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read matrix A dimensions and data
        int aHeight = scanner.nextInt(); // row
        int aWidth = scanner.nextInt();   // column
        double[][] a = new double[aHeight][aWidth];

        for (int i = 0; i < aHeight; i++) {
            for (int j = 0; j < aWidth; j++) { // Corrected to aWidth
                a[i][j] = scanner.nextDouble();
            }
        }

        int N = aHeight;

        // Read matrix B dimensions and data
        int bHeight = scanner.nextInt(); // row
        int bWidth = scanner.nextInt();   // column
        double[][] b = new double[bHeight][bWidth];

        for (int i = 0; i < bHeight; i++) {
            for (int j = 0; j < bWidth; j++) {
                b[i][j] = scanner.nextDouble();
            }
        }

        // Read matrix pi dimensions and data
        int piHeight = scanner.nextInt(); // row
        int piWidth = scanner.nextInt();   // column
        double[][] pi = new double[piHeight][piWidth];

        for (int i = 0; i < piHeight; i++) {
            for (int j = 0; j < piWidth; j++) {
                pi[i][j] = scanner.nextDouble();
            }
        }

        // Read observation sequence
        int oWidth = scanner.nextInt();   // column
        int[] o = new int[oWidth];
        for (int i = 0; i < oWidth; i++) {
            o[i] = scanner.nextInt();
        }
        int T = oWidth;

        // Initialize variables for forward algorithm
        double[] c = new double[T];
        double[][] alpha = new double[N][T];

        // Initialization step
        for (int i = 0; i < N; i++) {
            alpha[i][0] = pi[0][i] * b[i][o[0]];
        }

        // Induction step
        for (int t = 1; t < T; t++) {
            for (int i = 0; i < N; i++) {
                alpha[i][t] = 0.0;
                for (int j = 0; j < N; j++) {
                    alpha[i][t] += alpha[j][t - 1] * a[j][i];
                }
                alpha[i][t] *= b[i][o[t]];
            }
        }

        // Final summation
        double sum = 0.0;
        for (int i = 0; i < N; i++) {
            sum += alpha[i][T - 1];
        }

        // Output the result
        System.out.println(sum);
    }
}
