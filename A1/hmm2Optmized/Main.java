import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		int aHeight = scanner.nextInt(); //row
		int aWidth = scanner.nextInt();   // column
		double[][] a = new double[aHeight][aWidth];

		for (int i = 0; i < aHeight; i++) { // A matrix
			for (int j = 0; j < aHeight; j++) {
				a[i][j] = scanner.nextDouble();
			}
		}
		int N = aHeight;

		int bHeight = scanner.nextInt(); //row
		int bWidth = scanner.nextInt();   // column
		double[][] b = new double[bHeight][bWidth]; // B matrix
		for (int i = 0; i < bHeight; i++) {
			for (int j = 0; j < bWidth; j++) {
				b[i][j] = scanner.nextDouble();
			}
		}

		int piHeight = scanner.nextInt(); //row
		int piWidth = scanner.nextInt();   // column
		double[][] pi = new double[piHeight][piWidth];  //pi Matrix
		for (int i = 0; i < piHeight; i++) {
			for (int j = 0; j < piWidth; j++) {
				pi[i][j] = scanner.nextDouble();
			}
		}

		int oWidth = scanner.nextInt();   // column // observationSequence
		int[] o = new int[oWidth];
		for (int i = 0; i < oWidth; i++) {
			o[i] = scanner.nextInt();
		}
		int T = oWidth;

        //LetβT−1(i) = 1, scaled bycT−1
        double[] c = new double[T];
        double[][] beta = new double[N][T];
        
        for (int i = 0; i <= N-1 ; i++) {
            beta[i][T-1] = c[T-1];
            //SCALE 
            
        }

        for (int t = T-2; t >= 0; t--){
            for (int i = 0; i <= N-1; i++) {
                beta[i][t] = 0; 
                for (int j = 0; j <= N-1; j++) {
                    beta[i][t] +=a[i][j] * b[j][o[t+1]] * beta[j][t+1];
                }
                beta[i][t] *= c[t];
            }
            
        }
    }
}