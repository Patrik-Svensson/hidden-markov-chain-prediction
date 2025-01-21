public class DeltaVector {

    public static DeltaMatrix dotProductInitial(Matrix A, Matrix B) {
        if (A.M != B.M || A.N != 1 || B.N != 1) {
            throw new IllegalArgumentException();
        }

        DeltaMatrix C = new DeltaMatrix(A.M, 1);

        for (int i = 1; i <= A.M; i++) {
            double probability = A.getValue(i, 1) * B.getValue(i, 1);
            DeltaValuePair deltaValue = new DeltaValuePair(probability, 0); // Special case for the first time (hardcoded)
            C.setValue(i, 1, deltaValue);
        }

        return C;
    }

    public static DeltaMatrix dotProduct(DeltaMatrix A, Matrix B) {
        if (A.M != B.M || A.N != 1 || B.N != 1) {
            throw new IllegalArgumentException();
        }

        DeltaMatrix C = new DeltaMatrix(A.M, 1);

        for (int i = 1; i <= A.M; i++) {
            double probability = A.getValue(i, 1).probability * B.getValue(i, 1);
            DeltaValuePair deltaValue = new DeltaValuePair(probability, 1);
            C.setValue(i, 1, deltaValue);
        }

        return C;
    }

    public static DeltaValuePair maxElement(DeltaMatrix A) {
        double maxValue = Double.MIN_VALUE;
        int previousState = 0;

        for (int i = 1; i <= A.M; i++) {
            if (A.getValue(i, 1).probability == maxValue) {
                previousState = i;
            } else if (A.getValue(i, 1).probability > maxValue) {
                previousState = i;
                maxValue = A.getValue(i, 1).probability;
            }
        }

        return new DeltaValuePair(maxValue, previousState);
    }
}
