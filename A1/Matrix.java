public class Matrix
{
    private int M;
    private int N;
    double data[][];

    public Matrix(int M, int N){

        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // Create a matrix 
    public Matrix(String line){

        String[] splitString = line.split(" ");
        M = Integer.parseInt(splitString[0]); //row
        N = Integer.parseInt(splitString[1]);   // column
        this.data = new double[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                this.data[i][j] = Float.parseFloat(splitString[i*N+j+2]);
            }
        }
    }


	public double setValue(int i, int j, double value){
       return data[i - 1][j - 1] = value;
    }

    public double getValue(int i, int j){
        return data[i - 1][j - 1];
    }

    // return Matrix, C = A * B
    public Matrix matrixMultiplication(Matrix B){
        Matrix A = this;
        /* Check dimentions of the matrixes*/
        if(A.N != B.M){
          throw new IllegalArgumentException("Dimentions are not correct");
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

    public String toString()
    {
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