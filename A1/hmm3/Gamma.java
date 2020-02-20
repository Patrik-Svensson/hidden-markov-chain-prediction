import com.sun.javafx.geom.Matrix3f;

public class Gamma{


  private final Matrix A;
  private final Matrix B;
  private final Matrix pi;
  private final Matrix O;
  private final BackwardAlgorithm beta;
  private final ForwardAlgorithm alpha;

  private final int numberOfStates;
  private final int numberOfObservations;
  public Gamma(Matrix A, Matrix B, Matrix pi, Matrix O){

      this.A = A;
      this.B = B;
      this.pi = pi;
      this.O = O;
      this.numberOfObservations = O.N;
      this.numberOfStates = A.M;
      this.beta = new BackwardAlgorithm(A, B, O);
      this.alpha = new ForwardAlgorithm(A, B, pi, O);
  }
  public double getGammaValue(int time, int i){

    double sum = 0.0;

    for(int state = 1; state <= numberOfStates; state++)
      sum += getDiGamma(time,i,state);
      return sum;
  }

  public double getDiGamma(int time, int i, int j){
  //  System.out.println(time + " time " );
    double alphaValue = alpha.getAlphaVector(time).getValue(i,1);
    Matrix lastAlphaVector = alpha.getAlphaVector(numberOfObservations);
    double betaValue = beta.getBetaVector(time).getValue(j,1);
    double transitionValue = A.getValue(i,j);
    double emissionValue = Matrix.getColumn(B, (int)O.getValue(1, time+1)).getValue(j, 1);
    double nominator = 0.0;

    double sum=0.0;
    for (int state = 1; state <= numberOfStates; state++)
    {
        sum += lastAlphaVector.getValue(state, 1);
    }


    nominator = alphaValue*transitionValue*emissionValue*betaValue;

    double total = nominator/sum;
    return total;

  }
}
