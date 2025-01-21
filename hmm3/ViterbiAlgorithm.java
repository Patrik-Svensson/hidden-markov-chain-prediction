public class ViterbiAlgorithm {
    private final Matrix A;
    private final Matrix B;
    private final Matrix pi;
    private final Matrix O;
    private DeltaMatrix delta;

    private final int numberOfStates;
    private final int numberOfObservations;

    public ViterbiAlgorithm(Matrix A, Matrix B, Matrix pi, Matrix O) {
        this.A = A;
        this.B = B;
        this.pi = pi;
        this.O = O;
        this.numberOfObservations = O.N;
        this.numberOfStates = A.M;
        this.delta = new DeltaMatrix(numberOfStates, numberOfObservations);
    }

    public DeltaMatrix getDeltaVector(int time) {
        for (int i = 1; i <= time; i++) {
            for (int j = 1; j <= numberOfStates; j++) {
                delta.setValue(j, i, getDelta(i, j));
            }
        }
        
        DeltaMatrix deltaVector = new DeltaMatrix(numberOfStates, 1);
        
        for (int i = 1; i <= numberOfStates; i++) {
            DeltaValuePair deltaValue = delta.getValue(i, time);
            deltaVector.setValue(i, 1, deltaValue);
        }
        
        return deltaVector;
    }

    private DeltaValuePair getDelta(int time, int state) {
        Matrix observationColumn = Matrix.getColumn(B, (int) O.getValue(1, time));
        DeltaMatrix previousDeltaVector;
        double observationBasedOnCurrentState;
        Matrix transitionColumn;
        Matrix tempMatrix;
        DeltaMatrix deltaIncomingStateVector;
        DeltaValuePair maxProbability;

        if (time == 1) {
            maxProbability = DeltaVector.dotProductInitial(pi.transpose(), observationColumn).getValue(state, 1);
        } else {
            previousDeltaVector = delta.getColumn(time - 1);
            observationBasedOnCurrentState = observationColumn.getValue(state, 1);
            transitionColumn = A.getColumn(state);
            tempMatrix = Matrix.ScalarMultiplication(transitionColumn, observationBasedOnCurrentState);
            deltaIncomingStateVector = DeltaVector.dotProduct(previousDeltaVector, tempMatrix);
            maxProbability = DeltaVector.maxElement(deltaIncomingStateVector);
        }

        return maxProbability;
    }
}
