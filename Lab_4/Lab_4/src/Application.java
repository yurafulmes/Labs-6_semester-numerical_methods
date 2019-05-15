
import java.util.Scanner;

public class Application {

	public static double inputAccurancy() {
		double number;
		System.out.println("Введіть точність:");
		Scanner scan = new Scanner(System.in);
		while (true) {
			while (!scan.hasNextDouble()) {
				System.err.println("Неправильний ввід. Введіть дійсне число.");
				scan.nextLine();
			}
			number = scan.nextDouble();

			break;

		}
		return number;

	}

	public static double[] input() {
		double[] bounds = new double[2];
		double number;
		System.out.println("Введіть a:");
		Scanner scan = new Scanner(System.in);

		while (true) {
			while (!scan.hasNextDouble()) {
				System.err.println("Неправильний ввід. Введіть дійсне число.");
				System.out.println("Введіть a:");
				scan.nextLine();
			}
			number = scan.nextDouble();
			bounds[0] = number;
			break;

		}
		while (true) {
			System.out.println("Введіть b:");
			while (!scan.hasNextDouble()) {
				System.err.println("Неправильний ввід. Введіть дійсне число.");
				System.out.println("Введіть b:");
				scan.nextLine();
			}
			number = scan.nextDouble();
			bounds[1] = number;
			break;

		}
		return bounds;
	}

	public static double[] checkInput(double a, double b) {
		double[] bounds = new double[2];
		bounds[0] = a;
		bounds[1] = b;
		while (true) {
			if (a > b) {
				System.out.println("Недійсний інтервал");
				bounds = input();
				checkInput(bounds[0], bounds[1]);
			}
			if (function(a) * function(b) > 0) {
				System.out.println("Функція не має коренів на цьому інтервалі");
				bounds = input();
				checkInput(bounds[0], bounds[1]);

			} else {
				break;
			}
		}
		return bounds;

	}

	public static double function(double x) {
		return 2*Math.sin(x/2) - Math.exp(-x);

	}

	public static double functionFi(double x) {
		double t = -0.65;
		return x + t * function(x);
	}

	public static double functionDerivative(double x) {
		double t = -0.65;
		return 1 + t * Math.cos(x/2) - t *Math.exp(-x);
	}

	public static void rootsRefinement(double x, double e) {

		if (Math.abs(functionDerivative(x)) > 1) {
			System.out.println("Метод не є збіжним");
			return;
		}
		int newCount = 0;
		System.out.println("Метод збіжний!");
		double y;
		double d;

		double r;

		do {

			y = functionFi(x);
			d = Math.abs(y - x);
			x = y;
			newCount += 1;

		} while (d >= e);

		System.out.printf("Новий корінь: %.10f  ", y);
		System.out.println();
		System.out.println("Новий рахунок: " + newCount);

		r = function(y);
		System.out.printf("Невязка: %.10f ", r);
	}

	public static double devision(double a, double b, double e) {
		double x;
		int count = 0;
		double c;
		double r;
		c = (a + b) / 2;

		while (Math.abs(b - a) >= e) {

			c = (a + b) / 2;
			System.out.println("a: " + a + " c: " + c + " b: " + b);

			// if(function(c) == 0){
			// count += 1
			// x = c
			// break
			// }

			if (function(a) * function(c) > 0) {
				a = c;

			} else {
				b = c;
			}

			count += 1;
		}

		x = c;

		System.out.println("Кількість:" + count);
		System.out.printf("Корінь: %.10f ", x);
		System.out.println();
		r = function(x);
		System.out.printf("Невязка: %.10f ", r);
		System.out.println();
		return x;
	}

	public static double[] inputForSecondMethod(){
		double [] bounds = new double [2];
		double number;
		 System.out.println("Enter x0:");
		Scanner scan = new Scanner(System.in);
	  
	   while (true) {
            while (!scan.hasNextDouble()) {
                System.err.println("Incorrect input. Please enter a valid number.");
                System.out.println("Enter x0:");
                scan.nextLine(); 
            }
            number = scan.nextDouble();
            bounds[0] = number;
                break;
            
         
        }
	   while (true) {
		   System.out.println("Введіть x1:");
            while (!scan.hasNextDouble()) {
                System.err.println("Неправильний ввід. Будь ласку введіть дійсне число.");
                System.out.println("Введіть x1:");
                scan.nextLine(); 
            }
            number = scan.nextDouble();
            bounds[1] = number;
                break;
            
         
        }
	   return bounds;
	}

	public static double[] checkInputForSecondMethod(double a, double b){
		double [] bounds = new double[2];
		bounds[0]=a;
		bounds[1]=b;
	    while( true ){
	    if (a > b){
	    	System.out.println("Не правильний інтервал");
	        bounds = input();
	        checkInput(bounds[0], bounds[1]);
	      
	  } else {
	           break;}
	  }
	    return bounds;
	    
	}
	
	public static void rootsSecantRefinement(double x0, double x1){
		double e = 1e-5;
		double xN = x1;
		double xOfPreviousStep = x0;

		int newCount = 0;

		double xOfNextStep;


		double r;
		 System.out.println("x0 = " + xOfPreviousStep +"   x1 =" +xN );
		
		do {


		    xOfNextStep = xN - (xN - xOfPreviousStep)/(function(xN)-function(xOfPreviousStep))*function(xN);
		    System.out.println("xN-1 = " + xOfPreviousStep +"   xN =" +xN + "    xN+1 =" + xOfNextStep );

		    xOfPreviousStep = xN ;
		    xN = xOfNextStep;
		    
		    newCount += 1 ;
		    


		} while (Math.abs(function(xOfNextStep)) >= e);

		
		
		 System.out.printf("Root: %.10f ", xOfNextStep);
		 System.out.println();
		 System.out.println("NewCount:" + newCount);
		 

		r = function(xOfNextStep);
		System.out.printf("Disconnection: %.10f ", r);
		 System.out.println();
		}

   public static void main(String[] args) {
   
		double e;
		double a;
		double b;
		double x;

       double [] bounds = new double[2];

		e = inputAccurancy();
		bounds = input();
		
		bounds = checkInput(bounds[0], bounds[1]);
		a = bounds[0];
		b = bounds[1];

		
		System.out.println();
		System.out.println("The Bisection method");
		x = devision(a,b,e);
	
		System.out.println();
		System.out.println("The Secant method");
		double x0;
		double x1;
       bounds = inputForSecondMethod();
		
		bounds = checkInputForSecondMethod(bounds[0], bounds[1]);
		x0 = bounds[0];
		x1 = bounds[1];
		rootsSecantRefinement(x0, x1);
		
		System.out.println("Method of simple iterations");
	
		rootsRefinement(x,  0.00001);
   }
}
