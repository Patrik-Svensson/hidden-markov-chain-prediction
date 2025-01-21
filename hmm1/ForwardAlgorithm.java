public class ForwardAlgorithm {

    private final Matrix A;
    private final Matrix B;
    private final Matrix pi;
    private final Matrix O;
    private Matrix alpha;

    private final int numberOfStates;
    private final int numberOfObservations;

    // Constructor to initialize matrices and dimensions
    public ForwardAlgorithm(Matrix A, Matrix B, Matrix pi, Matrix O) {
        this.A = A;
        this.B = B;
        this.pi = pi;
        this.O = O;
        this.numberOfObservations = O.N;
        this.numberOfStates = A.M;
        this.alpha = new Matrix(numberOfStates, numberOfObservations);
    }

    // Method to get the alpha vector at a specific time
    public Matrix getAlphaVector(int time) {
        // Compute alpha for all states at the given time
        for (int i = 1; i <= time; i++) {
            for (int state = 1; state <= numberOfStates; state++) {
                alpha.setValue(state, i, getAlpha(i, state)); // Set alpha vector values
            }
        }

        // Prepare the alpha vector to return
        Matrix alphaVector = new Matrix(numberOfStates, 1);
        for (int i = 1; i <= numberOfStates; i++) {
            double test = alpha.getValue(i, time);
            alphaVector.setValue(i, 1, test); // Set the final value for the vector
        }
        return alphaVector;
    }

    // Private method to compute the alpha value for a specific time and state
    private double getAlpha(int time, int state) {
        Matrix observationColumn = Matrix.getColumn(B, (int) O.getValue(1, time));
        Matrix alphaVector;
        Matrix previousAlphaVector;
        Matrix tempMatrix;

        if (time == 1) {
            // Initial alpha vector computation
            alphaVector = Vector.dotProduct(pi.transpose(), observationColumn);
        } else {
            // Recursive alpha vector computation
            previousAlphaVector = Matrix.getColumn(alpha, time - 1);
            tempMatrix = previousAlphaVector.transpose().matrixMultiplication(A);
            alphaVector = Vector.dotProduct(tempMatrix.transpose(), observationColumn);
        }

        return alphaVector.getValue(state, 1);
    }
}
