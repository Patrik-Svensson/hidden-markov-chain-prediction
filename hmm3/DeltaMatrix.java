public class DeltaMatrix {

    public final int M;
    public final int N;
    private DeltaValuePair[][] data;

    public DeltaMatrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new DeltaValuePair[M][N];
    }

    public void setValue(int i, int j, DeltaValuePair value) {
        data[i - 1][j - 1] = value;
    }

    public DeltaValuePair getValue(int i, int j) {
        return data[i - 1][j - 1];
    }

    public DeltaMatrix transpose() {
        DeltaMatrix A = new DeltaMatrix(N, M);

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                A.setValue(j, i, this.getValue(i, j));
            }
        }

        return A;
    }

    public static DeltaMatrix getColumn(DeltaMatrix B, int columnNumber) {
        DeltaMatrix column = new DeltaMatrix(B.M, 1);

        for (int i = 1; i <= B.M; i++) {
            column.setValue(i, 1, B.getValue(i, columnNumber));
        }

        return column;
    }

    public DeltaMatrix getColumn(int columnNumber) {
        DeltaMatrix column = new DeltaMatrix(M, 1);

        for (int i = 1; i <= M; i++) {
            column.setValue(i, 1, this.getValue(i, columnNumber));
        }

        return column;
    }

    public void setColumn(int columnNumber, DeltaMatrix column) {
        for (int i = 1; i <= M; i++) {
            this.setValue(i, columnNumber, column.getValue(i, 1));
        }
    }

    public String toString() {
        StringBuilder strbd = new StringBuilder();
        strbd.append(M + " " + N);
        strbd.append("\n");

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                strbd.append(" " + getValue(i, j).probability + " (" + getValue(i, j).previousState + ")");
            }
            strbd.append("\n");
        }

        return strbd.toString();
    }
}
