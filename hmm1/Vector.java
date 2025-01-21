public class Vector {

    public static Matrix dotProduct(Matrix A, Matrix B) {

        if (A.M != B.M || A.N != 1 || B.N != 1) {
            throw new IllegalArgumentException("Matrix dimensions are not compatible for dot product.");
        }

        Matrix C = new Matrix(A.M, 1);

        for (int i = 1; i <= A.M; i++) {
            C.setValue(i, 1, A.getValue(i, 1) * B.getValue(i, 1));
        }

        return C;
    }
}
