public class BaumWelchAlgorithm {

  Matrix A;
  Matrix B;
  Matrix pi;
  private Gamma gamma;
  private Matrix O;

  private final int numberOfStates;
  private final int numberOfObservations;
  public BaumWelchAlgorithm(Matrix A, Matrix B, Matrix pi, Matrix O){

    this.A = A;
    this.B = B;
    this.pi = pi;
    this.O = O;
    this.numberOfObservations = O.N;
    this.numberOfStates = A.M;
    gamma = new Gamma(A, B, pi, O);
  }

  public Lambda getApproximatedLambda()
  {  
        Matrix newTransitionMatrix = getApproximatedTransitionMatrix();
        Matrix newEmissionMatrix = getApproximatedEmissionMatrix();
        Matrix newPiMatrix = getApproximatedPI();
        // CONVERGING METHOD 

        Lambda newApproximatedValues = new Lambda(newTransitionMatrix, newEmissionMatrix, pi);

        return newApproximatedValues;
  }

  private Matrix getApproximatedPI()
  {
    Matrix newPIMatrix = new Matrix(1,numberOfStates);
    double sum = 0.0;
    for (int time = 1; time <= numberOfObservations; time++) {
      newPIMatrix.setValue(1, time, gamma.getGammaValue(1,time));
    }

    return newPIMatrix;
  }

  private Matrix getApproximatedTransitionMatrix()
  {
    double sumDiGamma = 0.0;
    double sumGamma = 0.0;
    Matrix newTransitionMatrix = new Matrix(numberOfStates, numberOfStates);


    for(int i = 1; i <= numberOfStates; i++)
    {
      double sumOfGamma = 0.0;
      for(int time = 1; time <= numberOfObservations - 1; time++)
      {
        sumOfGamma += gamma.getGammaValue(time, i);
      }

      for(int j = 1; j <= numberOfStates; j++)
      {
        double sumofDiGamma = 0.0;
        for(int time = 1; time <= numberOfObservations - 1; time++)
        {
          sumofDiGamma += gamma.getDiGamma(time, i, j);

        }
        newTransitionMatrix.setValue(i, j, sumofDiGamma/sumOfGamma);
      }
    }
    return newTransitionMatrix;
  }


private Matrix getApproximatedEmissionMatrix()
{
  Matrix newEmissionMatrix = new Matrix(numberOfStates, B.N);

  for(int j =  1; j <= numberOfStates; j++)
  {
    for(int k = 1; k <= B.N; k++)
    {
      double nominatorSum = 0.0;
      for(int time = 1; time <= numberOfObservations - 1; time++)
      {
        if(k == (int)O.getValue(1,time))
        {
          nominatorSum += gamma.getGammaValue(time, j);
        }
      }

      double denominatorSum = 0.0;
      for(int time = 1; time <= numberOfObservations - 1; time++)
      {
        denominatorSum += gamma.getGammaValue(time, j);
      }

      newEmissionMatrix.setValue(j, k, nominatorSum/denominatorSum);
    }
  }

  return newEmissionMatrix;
}
}
