using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MathNet.Numerics;
using MathNet.Numerics.LinearAlgebra;


namespace Lab_1
{

    class Program
    {
        const double EPS = 0.00001;
        static void Print(List<Vector<double>> items, int begin = 0)
        {
            for (; begin < items.Count; ++begin)
            {
                Console.WriteLine(begin + " Item: ");
                Console.WriteLine(items[begin]);
            }
        }
        static void Main(string[] args)
        {
            var X0 = Vector<double>.Build.Random(5);
            var X = new List<Vector<double>>();

            var A = Matrix<double>.Build.SparseOfArray(new double[,] 
            {   { 0.405,0.05,0.04,0,0.09},
                { -0.061,0.53,0.073,0.11,-0.06},
                { 0.07,-0.036,0.38,0.03,0.02 },
                { -0.05,0,0.066,0.58,0.23},
                { 0,0.081,-0.05,0,0.41 }
            });
            var A1 = Matrix<double>.Build.SparseOfArray(new double[,]
           {    { 0,0.05,0.04,0,0.09},
                { 0,0,0.073,0.11,-0.06},
                { 0,0,0,0.03,0.02 },
                { 0,0,0,0,0.23},
                { 0,0,0,0,0 }
           });
            var A2 = Matrix<double>.Build.SparseOfArray(new double[,]
           {    { 0,0,0,0,0},
                { -0.061,0,0,0,-0 },
                { 0.07,-0.036,0,0,0 },
                { -0.05,0,0.066,0,0 },
                { 0,0.081,-0.05,0,0 }
           });
            var D = Matrix<double>.Build.SparseOfArray(new double[,]
           {    { 0.405,0,0,0,0},
                { 0,0.53,0,0,0 },
                { 0,0,0.38,0,0 },
                { 0,0,0,0.58,0 },
                { 0,0,0,0,0.41 }
           });

            var F = Vector<double>.Build.Dense(new double[]
            { -1.475, 2.281, 0.296,0.492,1.454 });

            X.Add(X0);

            int i = 0;

            do
            {
                X.Add(-D.Inverse()*(A1*X[i] + A2*X[i] - F));
                ++i;
            }
            while (Math.Abs(X[i - 1].Sum() - X[i].Sum()) > EPS);

            Console.WriteLine("Eps: "+ EPS);
            Console.WriteLine("X:\n");
            Print(X, X.Count - 5);
            Console.WriteLine("\n R:\n");
            Console.WriteLine(A * X.Last() - F);

            Console.ReadKey();
        }
    }
}
