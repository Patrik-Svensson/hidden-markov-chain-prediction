import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		int aHeight = scanner.nextInt(); //row
		int aWidth = scanner.nextInt();   // column
		double[][] a = new double[aHeight][aWidth];

		for (int i = 0; i < aHeight; i++) {
			for (int j = 0; j < aHeight; j++) {
				a[i][j] = scanner.nextDouble();
			}
		}
		int N = aHeight;

		int bHeight = scanner.nextInt(); //row
		int bWidth = scanner.nextInt();   // column
		double[][] b = new double[bHeight][bWidth];
		for (int i = 0; i < bHeight; i++) {
			for (int j = 0; j < bWidth; j++) {
				b[i][j] = scanner.nextDouble();
			}
		}

		int piHeight = scanner.nextInt(); //row
		int piWidth = scanner.nextInt();   // column
		double[][] pi = new double[piHeight][piWidth];
		for (int i = 0; i < piHeight; i++) {
			for (int j = 0; j < piWidth; j++) {
				pi[i][j] = scanner.nextDouble();
			}
		}

		int oWidth = scanner.nextInt();   // column
		int[] o = new int[oWidth];
		for (int i = 0; i < oWidth; i++) {
			o[i] = scanner.nextInt();
		}
		int T = oWidth;

		double[] c = new double[T];
		double[][] alpha = new double[N][T];

	//	c[0] = 0.0;

		// Compute alpha-0
		for(int i = 0; i < N; i++)
		{
			alpha[i][0] = pi[0][i] * b[i][o[0]];
	//		c[0] += alpha[0][i];
		}

		// Scale alpha-0
//		c[0] = 1 / c[0];
//		for (int i = 0; i < N; i++)
//			alpha[0][i] *= c[0];

		// Compute alpha-i
		for(int t = 1; t < T; t++)
		{
//			c[t] = 0.0;
			for(int i = 0; i < N; i++)
			{
				alpha[i][t] = 0.0;
				for(int j = 0; j < N; j++)
				{
					alpha[i][t] += alpha[j][t - 1] * a[j][i];
				}

				alpha[i][t] *= b[i][o[t]];
//				c[t] += alpha[i][t];
			}

			// Scale alpha-i
//			c[t] = 1 / c[t];
//			for(int i = 0; i < N; i++)
//			{
//				alpha[i][t] *= c[t];
//			}
		}

		// Sum alpha up
		double sum = 0.0;
		for(int i = 0; i < N; i++)
			sum += alpha[i][T - 1];

		System.out.println(sum);
	}
}
