import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int aHeight = scanner.nextInt(); // row
        int aWidth = scanner.nextInt();  // column
        double[][] a = new double[aHeight][aWidth];

        // Reading matrix A
        for (int i = 0; i < aHeight; i++) {
            for (int j = 0; j < aWidth; j++) {
                a[i][j] = scanner.nextDouble();
            }
        }
        int N = aHeight;

        int bHeight = scanner.nextInt(); // row
        int bWidth = scanner.nextInt();  // column
        double[][] b = new double[bHeight][bWidth]; // B matrix

        // Reading matrix B
        for (int i = 0; i < bHeight; i++) {
            for (int j = 0; j < bWidth; j++) {
                b[i][j] = scanner.nextDouble();
            }
        }

        int piHeight = scanner.nextInt(); // row
        int piWidth = scanner.nextInt();  // column
        double[][] pi = new double[piHeight][piWidth]; // pi Matrix

        // Reading matrix pi
        for (int i = 0; i < piHeight; i++) {
            for (int j = 0; j < piWidth; j++) {
                pi[i][j] = scanner.nextDouble();
            }
        }

        int oWidth = scanner.nextInt();  // column // observationSequence
        int[] o = new int[oWidth];

        // Reading observation sequence
        for (int i = 0; i < oWidth; i++) {
            o[i] = scanner.nextInt();
        }
        int T = oWidth;

        // Let βT−1(i) = 1, scaled by cT−1
        double[] c = new double[T];
        double[][] beta = new double[N][T];

        // Initializing β for T-1
        for (int i = 0; i < N; i++) {
            beta[i][T - 1] = c[T - 1];
            // SCALE
        }

        // Backward calculation of β
        for (int t = T - 2; t >= 0; t--) {
            for (int i = 0; i < N; i++) {
                beta[i][t] = 0;
                for (int j = 0; j < N; j++) {
                    beta[i][t] += a[i][j] * b[j][o[t + 1]] * beta[j][t + 1];
                }
                beta[i][t] *= c[t];
            }
        }
    }
}
