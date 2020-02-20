public class ForwardAlgorithm{

    private final Matrix A;
    private final Matrix B;
    private final Matrix pi;
    private final Matrix O;
    private Matrix alpha;

    private final int numberOfStates;
    private final int numberOfObservations;
    public ForwardAlgorithm(Matrix A,Matrix B, Matrix pi, Matrix O){

        this.A = A;
        this.B = B;
        this.pi = pi;
        this.O = O;
        this.numberOfObservations = O.N;
        this.numberOfStates = A.M;
        this.alpha = new Matrix(numberOfStates,numberOfObservations);

    }

    public Matrix getAlphaVector(int time){

        for (int i = 1; i <= time; i++) {
            for (int state = 1; state <= numberOfStates; state++) {
                alpha.setValue(state, i, getAlpha(i, state)); // sätter alpha vektorn
            }
        }

        Matrix alphaVector = new Matrix(numberOfStates,1);

        for (int i = 1; i <= numberOfStates; i++) {
            double test = alpha.getValue(i, time);
            alphaVector.setValue(i, 1, test); //sista value
        }
        return alphaVector;

    }


    private double getAlpha(int time, int state){
        Matrix  observationColumn = Matrix.getColumn(B, (int)O.getValue(1,time));
        Matrix alphaVector;
        Matrix previousAlphaVector;
        Matrix tempMatrix;
        if(time == 1){

            alphaVector = Vector.dotProduct(pi.transpose(), observationColumn);

        }else{
            previousAlphaVector =  Matrix.getColumn(alpha, time-1);
            tempMatrix = previousAlphaVector.transpose().matrixMultiplication(A);
            alphaVector = Vector.dotProduct(tempMatrix.transpose(), observationColumn);
        }

        return alphaVector.getValue(state, 1);
    }



}
