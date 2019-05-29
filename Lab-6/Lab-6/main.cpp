
#include<iostream>
#include<iomanip>
#include <string>
#include<math.h>
#include <sstream>

int size = 9;
double *arrayOfX = new double[size];
double *arrayOfY = new double[size];

void fillMatrixOfKoef(double **matrixOfKoef) {
	matrixOfKoef[0][0] = size;
	for (int i = 0; i < size; i++) {
		matrixOfKoef[0][1] += arrayOfX[i];
	}
	matrixOfKoef[1][0] = matrixOfKoef[0][1];
	for (int i = 0; i < size; i++) {
		matrixOfKoef[1][1] += pow(arrayOfX[i], 2);
	}
	for (int i = 0; i < size; i++) {
		matrixOfKoef[0][2] += arrayOfY[i];
	}
	for (int i = 0; i < size; i++) {
		matrixOfKoef[1][2] += (arrayOfX[i] * arrayOfY[i]);
	}
};
void outputSystem(double **Matrix_of_koef) {
	std::cout << "System : \n";
	std::cout << "a1 * " << Matrix_of_koef[0][0] << " + a2 * " <<
		Matrix_of_koef[0][1] << " = " << Matrix_of_koef[0][2] << "\n";
	std::cout << "a1 * " << Matrix_of_koef[1][0] << " + a2 * " <<
		Matrix_of_koef[1][1] << " = " << Matrix_of_koef[1][2] << "\n";
}
void Calculation(double &a1, double &a2, double **MatrixOfKoef) {
	//kramer
	double temp = MatrixOfKoef[0][0] * MatrixOfKoef[1][1] -
		MatrixOfKoef[1][0] * MatrixOfKoef[0][1];

	a1 = (MatrixOfKoef[0][2] * MatrixOfKoef[1][1] -
		MatrixOfKoef[1][2] * MatrixOfKoef[0][1]) / temp;
	a2 = (MatrixOfKoef[0][0] * MatrixOfKoef[1][2] -
		MatrixOfKoef[1][0] * MatrixOfKoef[0][2]) / temp;
}
void printResult(double &a_1, double &a_2) {
	double *Matrix = new double[size];
	double temp;
	for (int i = 0; i < size; i++) {
		temp = a_1 + a_2 * arrayOfX[i];
		Matrix[i] = arrayOfY[i] - temp;
		std::cout << "y(" << arrayOfX[i] << ")=" << temp << "\tf("
			<< arrayOfX[i] << ")=" << arrayOfY[i] << "\terror : " << abs(Matrix[i]) << "\n";
	}temp = 0;
	for (int i = 0; i < size; i++)
		temp += (abs(Matrix[i]) * abs(Matrix[i]));
	std::cout << "\nSum of errors : " << temp << "\n";
};

double resultInPoint(double &a1, double &a2, double point) {
	double temp = 0;
	for (int i = 0; i < size; i++) {
		temp = a1 + a2 * point;
	};
	return temp;
}
int main() {

	double **matrixOfKoef = new double *[3];
	for (int i = 0; i < 3; i++)
		matrixOfKoef[i] = new double[3];
	for (int i = 0; i < 3; i++)
		for (int j = 0; j < 3; j++)
			matrixOfKoef[i][j] = 0;

	arrayOfX[0] = 0.0;    arrayOfY[0] = 2.00000;
	arrayOfX[1] = 0.1;    arrayOfY[1] = 1.95533;
	arrayOfX[2] = 0.2;    arrayOfY[2] = 1.82533;
	arrayOfX[3] = 0.4;    arrayOfY[3] = 1.36235;
	arrayOfX[4] = 0.5;    arrayOfY[4] = 1.07073;
	arrayOfX[5] = 0.6;    arrayOfY[5] = 0.77279;
	arrayOfX[6] = 0.7;    arrayOfY[6] = 0.49515;
	arrayOfX[7] = 0.8;    arrayOfY[7] = 0.26260;
	arrayOfX[8] = 0.9;    arrayOfY[8] = 0.09592;

	fillMatrixOfKoef(matrixOfKoef);
	outputSystem(matrixOfKoef);
	double a1, a2;
	Calculation(a1, a2, matrixOfKoef);
	std::cout << "\n Function : " << a1 << " + " << a2 << " * x\n\n";
	printResult(a1, a2);

	while (true) {
		std::cout << "\nEnter a point: ";
		std::string input;
		std::cin >> input;
		if (input == "stop") { break; }
		bool flag = true;
		for (int i = 0; i < input.length(); i++)
		{
			if (isalpha(input[i])) {
				flag = false;
				break;

			}
		}
		if (flag) {
			double point = stod(input);
			std::cout << "Value in point:" << resultInPoint(a1, a2, point);
		}
		else { std::cout << "Try again."; }
	}
	system("pause");
	return 0;
}
