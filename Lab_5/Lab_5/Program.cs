using System;
using System.Collections.Generic;
using MathNet.Numerics;
using MathNet.Numerics.LinearAlgebra;

namespace Lab_5
{
    class Program
    {
        static void Main(string[] args)
        {

            var f = Vector<double>.Build.Dense(new double[]
            {0, 0.9983e-1, .19866, .29552, .38941, .47942, .56464, .64421, .71735, .78332 });
            var x = Vector<double>.Build.Dense(new double[]
                        { 0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9 });

            var X = Matrix<double>.Build.SparseOfArray(new double[,] { { 0.052,0.303,0.891},
                { 0.801,0.778,0.445},{0.115,0.256,0.669 },{ 0.832,0.575,0.832},
                {0.033,0.555,0.782},{ 0.226,0.431,0.669},{ 0.114,0.357,0.802}, { 0.335, 0.551,0.844} });


            for (int i = 0; i <X.RowCount; i++)
            {
                Console.WriteLine("№" + (int)(i+1));
                    Console.WriteLine("x`=" + X[i,0]);
                    Console.WriteLine("f(x`)=" + L(9, X[i, 0], f, x));
                    Console.WriteLine("x``=" + X[i,1]);
                    Console.WriteLine("f(x``)" + L(9, X[i, 1], f, x));
                    Console.WriteLine("x```=" + X[i, 2]);
                    Console.WriteLine("f(x```)=" + L(9, X[i, 2], f, x));
            }
            Console.ReadKey();
        }

        public static double method(Vector<double> f, Vector<double> x,int k)
        {
            double summ = 0;

            for(int j=0;j<=k;++j)
            {
                double multiply = 1;
                for (int i=0;i<=k;++i)
                {
                    if(i!=j)
                      multiply *= x[j] - x[i];
                }
                summ += f[j] / multiply;
            }
            return summ;
        }
        public static double b(int k, double x,Vector<double> f, Vector<double> X, int n)
        {
            return k > 0 ? (x - X[n - k + 1]) * b(k - 1, x, f, X, n) + method(f, X, n - k + 1) : 0;
        }
        public static double L(int i, double x,Vector<double> f, Vector<double> X)
        {
            return b(i + 1, x, f, X, i);
        }
    }
}
