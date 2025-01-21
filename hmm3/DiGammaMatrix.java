public class DiGammaMatrix {

    private final Matrix[] gammaMatrix;
    private final int I_MAX;
    private final int J_MAX;
    private final int T_MAX;

    public DiGammaMatrix(int iMax, int jMax, int tMax) {
        I_MAX = iMax;
        J_MAX = jMax;
        T_MAX = tMax;

        gammaMatrix = new Matrix[T_MAX];
        for (int i = 0; i < T_MAX; i++) {
            gammaMatrix[i] = new Matrix(I_MAX, J_MAX);
        }
    }

    public double getValue(int i, int j, int k) {
        return gammaMatrix[k].getValue(i, j);
    }

    public void setValue(int i, int j, int k, double value) {
        gammaMatrix[k].setValue(i, j, value);
    }
}
