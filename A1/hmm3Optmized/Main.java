import java.util.Scanner;

public class Main
{
    public static int N;
    public static int T;
    public static double[][] a;
    public static double[][] b;
    public static double[][] pi;
    public static double[][][] diGamma;
    public static double[][] gamma;
    public static double[] c;
    public static int[] o;
    public static int M;
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		int aHeight = scanner.nextInt(); //row
		int aWidth = scanner.nextInt();   // column
		a = new double[aHeight][aWidth];

		for (int i = 0; i < aHeight; i++) {
			for (int j = 0; j < aHeight; j++) {
				a[i][j] = scanner.nextDouble();
			}
		}
		N = aHeight;

		int bHeight = scanner.nextInt(); //row
        int bWidth = scanner.nextInt();   // column
        M = bWidth; // antal observationer 
		b = new double[bHeight][bWidth];
		for (int i = 0; i < bHeight; i++) {
			for (int j = 0; j < bWidth; j++) {
				b[i][j] = scanner.nextDouble();
			}
		}

		int piHeight = scanner.nextInt(); //row
		int piWidth = scanner.nextInt();   // column
        pi = new double[piHeight][piWidth];
		for (int i = 0; i < piHeight; i++) {
			for (int j = 0; j < piWidth; j++) {
				pi[i][j] = scanner.nextDouble();
			}
		}

		int oWidth = scanner.nextInt();   // column
		o = new int[oWidth];
		for (int i = 0; i < oWidth; i++) {
			o[i] = scanner.nextInt();
        }  
        T = oWidth;
        
		c = new double[T];
        diGamma = new double[N][N][T];
        gamma = new double[N][T];
        double oldLogProb = -Double.MAX_VALUE;
        int count=0;
    
        for (int iters = 0; iters < 100000; iters++) {
            System.out.println("Iterations until converged: " + count++);
                
            double[][] alpha = calculateAlpha();
            double[][] beta = calculateBeta();

            for (int t = 0; t <= T-2; t++) {
                for (int i = 0; i <= N-1; i++) {
                    gamma[i][t] = 0.0;

                    for (int j = 0; j <= N-1; j++) {
                        diGamma[i][j][t] = alpha[i][t] * a[i][j] * b[j][o[t+1]] * beta[j][t+1]; 
                        gamma[i][t] += diGamma[i][j][t];
                    }
                }
                
            }
            for (int i = 0; i <= N-1; i++) {
                gamma[i][T-1] = alpha[i][T-1];                
            }

            reestimate();
            if(!(getLogProb() > oldLogProb)){
                break;
        
            }
            

            oldLogProb = getLogProb();
        }

        System.out.print(N + " " + N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + a[i][j]);
            }
        }

        System.out.println();
        System.out.print(N + " " + M);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(" " + b[i][j]);
            }
        }
    }

    public static double getLogProb()
    {
        double logProb = 0.0;

        for (int i = 0; i <= T - 1; i++) {
            logProb += Math.log(c[i]);
        }

        logProb *= -1;

        return logProb;
    }

    public static double[][] calculateAlpha(){
		double[][] alpha = new double[N][T];

		c[0] = 0.0;

		// Compute alpha-0
		for(int i = 0; i < N; i++)
		{
			alpha[i][0] = pi[0][i] * b[i][o[0]];
			c[0] += alpha[i][0];
		}

		// Scale alpha-0
		c[0] = 1 / c[0];
		for (int i = 0; i <= N - 1; i++)
			alpha[i][0] *= c[0];

		// Compute alpha-i
		for(int t = 1; t <= T - 1; t++)
		{
			c[t] = 0.0; 
			for(int i = 0; i <= N - 1 ; i++)
			{
				alpha[i][t] = 0.0;
				for(int j = 0; j <= N -1; j++)
				{
					alpha[i][t] += alpha[j][t - 1] * a[j][i];
				}

				alpha[i][t] *= b[i][o[t]];
				c[t] += alpha[i][t];
			}

			// Scale alpha-i
			c[t] = 1 / c[t];
			for(int i = 0; i <= N -1 ; i++)
			{
				alpha[i][t] *= c[t];
			} 
		}

		return alpha;
    }
    
    public static double[][] calculateBeta()
    {
        //LetβT−1(i) = 1, scaled bycT−1s
        double[][] beta = new double[N][T];
        
        for (int i = 0; i <= N-1 ; i++) 
        {
            beta[i][T-1] = c[T-1];
        }

        for (int t = T-2; t >= 0; t--)
        {
            for (int i = 0; i <= N-1; i++) 
            {
                beta[i][t] = 0.0; 
                for (int j = 0; j <= N-1; j++) 
                {
                    beta[i][t] +=a[i][j] * b[j][o[t+1]] * beta[j][t+1];
                }
                beta[i][t] *= c[t];
            }     
        }
        return beta;
    }

    public static void reestimate()
    {
        double[][] newA  = new double[N][N];
        double[][] newB  = new double[N][M];
        double[][] newPi = new double[1][N];
        
        double denom;
        double numer;
        // re.estimate pi
        for (int i = 0; i <= N-1; i++) 
        {
            newPi[0][i] = gamma[i][0];    
        }

        // re-estimate A
    
        for (int i = 0; i <= N-1; i++) 
        {
            denom=0.0;
            for (int t = 0; t <= T - 2; t++) 
            {
                denom += gamma[i][t];
            }
            for (int j = 0; j <= N - 1; j++) 
            {
                numer = 0.0;
                for (int t = 0; t <= T - 2; t++) 
                {
                    numer += diGamma[i][j][t];
                }   
                newA[i][j] = numer/denom;
            }
        }
        // re-estimate B
        for (int i = 0; i <= N - 1; i++) 
        {
            denom = 0.0;
            for (int t = 0; t <= T - 1 ; t++) 
            {
                denom += gamma[i][t];    
            }
            for (int j = 0; j <= M - 1; j++) 
            {
                numer = 0.0;
                for (int t = 0; t <= T - 1; t++) 
                {
                    if(o[t] == j)
                    {
                        numer += gamma[i][t];    
                    }
                }
                newB[i][j] = numer/denom;
            }
        }

        a = newA;
        b = newB;
        pi = newPi;
    }
}