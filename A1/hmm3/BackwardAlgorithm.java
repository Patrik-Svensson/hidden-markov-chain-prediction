public class BackwardAlgorithm{

    private final Matrix A;
    private final Matrix B;
    private final Matrix O;
    private Matrix beta;

    private final int numberOfStates;
    private final int numberOfObservations;
    public BackwardAlgorithm(Matrix A,Matrix B, Matrix O){

        this.A = A;
        this.B = B;
        this.O = O;
        this.numberOfObservations = O.N;
        this.numberOfStates = A.M;
        this.beta = new Matrix(numberOfStates,numberOfObservations);

    }

    public Matrix getBetaVector(int time){

        for (int i = numberOfObservations; i >= time; i--) {
            for (int state = 1; state <= numberOfStates; state++) {
                beta.setValue(state, i, getBeta(i, state)); // sätter beta vektorn
              //  System.out.println("time: " + i + " state: " + state + " value: "  + getBeta(i, state));
            }
        }

        Matrix betaVector = new Matrix(numberOfStates,1);

        for (int i = 1; i <= numberOfStates; i++) {
            double betaValue = beta.getValue(i, time);
            betaVector.setValue(i, 1, betaValue); //sista value
        }
        return betaVector;

    }

    private double getBeta(int time, int state){
        double betaValue = 1.0;

        if (time < numberOfObservations){
          Matrix betaVector = beta.getColumn(time+1);
//          System.out.println("BETA VECTOR " + betaVector);
          Matrix emissionVector = Matrix.getColumn(B, (int)O.getValue(1, time+1));
  //        System.out.println("EMISSION VECTOR " + emissionVector);
          Matrix transitionRow = A.getRow(state); // kommer från samma state
  //        System.out.println("TRANSITION ROW " + transitionRow);

          Matrix tempMatrix = Vector.dotProduct(betaVector,emissionVector);
//          System.out.println("TEMP MATRIX " + tempMatrix);
          betaValue = transitionRow.matrixMultiplication(tempMatrix).getValue(1,1);
  //        System.out.println("MATRIX MULTIPLICATION " + tempMatrix.matrixMultiplication(transitionRow));
        }

        return betaValue;
    }
}
