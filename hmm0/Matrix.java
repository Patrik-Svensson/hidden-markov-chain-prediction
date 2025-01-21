public class Matrix {

    private int M;
    private int N;
    double data[][];

    // Constructor to initialize matrix dimensions
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // Constructor to initialize matrix from a string input
    public Matrix(String line) {
        String[] splitString = line.split(" ");
        M = Integer.parseInt(splitString[0]);
        N = Integer.parseInt(splitString[1]);
        this.data = new double[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                this.data[i][j] = Float.parseFloat(splitString[i * N + j + 2]);
            }
        }
    }

    // Method to set value at a specific position
    public double setValue(int i, int j, double value) {
        return data[i - 1][j - 1] = value;
    }

    // Method to get value at a specific position
    public double getValue(int i, int j) {
        return data[i - 1][j - 1];
    }

    // Method to multiply matrices
    public Matrix matrixMultiplication(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) {
            throw new IllegalArgumentException("Dimensions are not correct");
        }

        Matrix C = new Matrix(A.M, B.N);
        double multiplicationSum;

        for (int i = 1; i <= A.M; i++) {
            for (int j = 1; j <= B.N; j++) {
                multiplicationSum = 0.0;
                for (int k = 1; k <= A.N; k++) {
                    multiplicationSum += A.getValue(i, k) * B.getValue(k, j);
                }
                C.setValue(i, j, multiplicationSum);
            }
        }

        return C;
    }

    // Method to convert matrix to a string representation
    public String toString() {
        StringBuilder strbd = new StringBuilder();
        strbd.append(M + " " + N);

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                strbd.append(" " + getValue(i, j));
            }
        }

        return strbd.toString();
    }
}
