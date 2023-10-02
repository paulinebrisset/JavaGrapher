

import Grapher.exception.*;
import Grapher.models.Constant;
import Grapher.models.Cos;
import Grapher.models.Division;
import Grapher.models.Exp;
import Grapher.models.Log;
import Grapher.models.Multiplication;
import Grapher.models.Node;
import Grapher.models.Plus;
import Grapher.models.Sin;
import Grapher.models.Subtraction;
import Grapher.models.Tan;
import Grapher.models.Variable;


public class main {

	
	public static Node analyse(String s) throws SyntaxeErrorException {
		s = s.trim();
		System.out.println("|"+s+"|");

		int indexMoins = s.indexOf("-");		
		if(indexMoins == 0) return new Subtraction(new Constant(0), analyse(s.substring(1)));
		if(indexMoins > 0 ) return new Subtraction(analyse(s.substring(0, indexMoins)), analyse(s.substring(indexMoins+1, s.length())));
		
		int indexPlus = s.indexOf("+");
		if(indexPlus == 0) return new Plus(new Constant(0), analyse(s.substring(1)));
		if(indexPlus > 0) return new Plus(analyse(s.substring(0, indexPlus)), analyse(s.substring(indexPlus+1, s.length())));
		
		int indexDivision = s.indexOf("/");
		if(indexDivision == 0) throw new SyntaxeErrorException("Syntaxe error expected");
		if(indexDivision > 0) return new Division(analyse(s.substring(0, indexDivision)), analyse(s.substring(indexDivision+1, s.length())));
		
		int indexMultiplication = s.indexOf("*");
		if(indexMultiplication == 0) throw new SyntaxeErrorException("Syntaxe error expected");
		if(indexMultiplication > 0) return new Multiplication(analyse(s.substring(0, indexMultiplication)), analyse(s.substring(indexMultiplication+1, s.length())));
		
		if(s.indexOf("sin") == 0) return new Sin(analyse(s.substring(3)));
		if(s.indexOf("cos") == 0) return new Cos(analyse(s.substring(3)));
		if(s.indexOf("tan") == 0) return new Tan(analyse(s.substring(3)));
		if(s.indexOf("log") == 0) return new Log(analyse(s.substring(3)));
		if(s.indexOf("exp") == 0) return new Exp(analyse(s.substring(3)));
		if(s.equalsIgnoreCase("pi")) return new Constant((float) Math.PI);
		if(s.equalsIgnoreCase("x")) return new Variable();
		else return new Constant(stringToFloat(s));
	}
	
	//function to convert string to float
	public static float stringToFloat(String s){
		float x;
		try {
			x =(float) Float.parseFloat(s);
			System.out.println(x);
			return x;
		}catch (Exception e) {
			System.out.println("Exception: "+ e);
		}
		return 0;
	}

	public static void main(String[] args) throws SyntaxeErrorException, DivisionByZeroException, LogByZeroException {
		// Node nd = new Multiplication(new Constant(3), new Sin(new Variable()));
		try {
			Node nd = analyse("sinpi");
			System.out.println("solution = "+ nd.eval((float)3));
		} catch (SyntaxeErrorException e) {
			e.printStackTrace();
		}


	}
}
