public class Vector{

    public static Matrix dotProduct(Matrix A, Matrix B){
     
        if (A.M != B.M || A.N != 1|| B.N != 1) {
            System.out.println(A.M + " A.M");
            System.out.println(B.M + " B.M");
            System.out.println();
            System.out.println(A.N + " A.N");
            System.out.println(B.N + " B.N");
    
            throw new IllegalArgumentException();
        }

        Matrix C = new Matrix(A.M,1);

        for (int i = 1; i <= A.M; i++) {
            C.setValue(i,1, A.getValue(i,1) * B.getValue(i,1));
        }

        return C;
    }
}
