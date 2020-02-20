public class HiddenMarkovModel{

  public static Sequence<Integer> getStateSequence(Matrix A, Matrix B, Matrix pi, Matrix observations){
    DeltaMatrix delta = new DeltaMatrix(A.M, observations.N);
    ViterbiAlgorithm backwardAlgorithm = new ViterbiAlgorithm(A, B, pi, observations);
    DeltaMatrix deltaVector;
    for(int time = 1; time<= observations.N; time++){
      deltaVector = backwardAlgorithm.getDeltaVector(time);
      delta.setColumn(time, deltaVector);
    }

    Sequence<Integer> stateSequence = backtracking(delta, observations);

    return stateSequence;
  }

  private static Sequence<Integer> backtracking(DeltaMatrix deltaMatrix, Matrix observations){
    Sequence<Integer> stateSequence = new Sequence<Integer>(observations.N);


    double maxProbability = Double.MIN_VALUE;
    int maxState = 0;
    for (int i = 1; i <= deltaMatrix.M; i++)
    {
          if(deltaMatrix.getValue(i, observations.N).probability > maxProbability)
          {
                maxProbability = deltaMatrix.getValue(i, observations.N).probability;
                maxState = i;
          }
    }

    stateSequence.setValue(observations.N, maxState);
    for(int i = observations.N - 1; i>=1 ; i--)
    {
      stateSequence.setValue(i, deltaMatrix.getValue(maxState, i + 1).previousState);
      maxState =  deltaMatrix.getValue(maxState, i + 1).previousState;

    }

    return stateSequence;
  }
}
