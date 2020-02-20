public class DeltaValuePair{

  public final double  probability;
  public final int  previousState;

  public DeltaValuePair(double probability, int previousState){
      this.probability = probability;
      this.previousState = previousState;
  }
}
