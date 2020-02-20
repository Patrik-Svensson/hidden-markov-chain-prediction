public class ViterbiAlgorithm{
  private final Matrix A;
  private final Matrix B;
  private final Matrix pi;
  private final Matrix O;
  private DeltaMatrix delta;

  private final int numberOfStates;
  private final int numberOfObservations;

  public ViterbiAlgorithm(Matrix A,Matrix B, Matrix pi, Matrix O){
      this.A = A;    Matrix  observationColumn = Matrix.getColumn(B, (int)O.getValue(1,time));
      this.B = B;
      this.pi = pi;
      this.O = O;
      this.numberOfObservations = O.N;
      this.numberOfStates = A.M;
      this.delta = new DeltaMatrix(numberOfStates,numberOfObservations);
  }

  public DeltaMatrix getDeltaVector(int time){
    for(int i = 1; i <= time; i++){
      for(int j = 1; j<=numberOfStates ;j++){
        delta.setValue(j, i, getDelta(i, j));
      }
    }
    DeltaMatrix deltaVector = new DeltaMatrix(numberOfStates,1);
    for (int i = 1; i <= numberOfStates; i++) {
      DeltaValuePair deltaValue = delta.getValue(i, time);
      deltaVector.setValue(i, 1, deltaValue); // specifik kolumn
    }
    return deltaVector;
  }

  private DeltaValuePair getDelta(int time, int state){
    Matrix  observationColumn = Matrix.getColumn(B, (int)O.getValue(1,time));
    DeltaMatrix previousDeltaVector;
    double observationBasedOnCurrentState;
    Matrix transitionColumn;
    Matrix tempMatrix;
    DeltaMatrix deltaIncomingStateVector;
    DeltaValuePair maxProbability;

    if (time == 1) {
        maxProbability = DeltaVector.dotProductInitial(pi.transpose(), observationColumn).getValue(state, 1);
    }else{
      previousDeltaVector = delta.getColumn(time-1);
    //  System.out.println("previous delta vector " + previousDeltaVector);
      observationBasedOnCurrentState = observationColumn.getValue(state,1);
    //  System.out.println("observation " + observationBasedOnCurrentState);
      transitionColumn = A.getColumn(state);
    //  System.out.println("transitionColumn " + transitionColumn);
      tempMatrix = Matrix.ScalarMultiplication(transitionColumn, observationBasedOnCurrentState);
    //  System.out.println("Scalar multplied transition " + tempMatrix);
      deltaIncomingStateVector = DeltaVector.dotProduct(previousDeltaVector, tempMatrix);
    //  System.out.println("Row of most probable state trasfer " + deltaIncomingStateVector);
      maxProbability = DeltaVector.maxElement(deltaIncomingStateVector);

    }
//    System.out.println("DELTA FOR " + time + " for state " + state + " " + maxProbability.probability);
//    System.out.println();

    return maxProbability;

  }

}
