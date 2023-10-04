package Calculator;

import java.util.ArrayList;

import Calculator.exception.*;
import Calculator.models.Constant;
import Calculator.models.Cos;
import Calculator.models.Division;
import Calculator.models.Exp;
import Calculator.models.Log;
import Calculator.models.Multiplication;
import Calculator.models.Node;
import Calculator.models.Plus;
import Calculator.models.Sin;
import Calculator.models.Subtraction;
import Calculator.models.Tan;
import Calculator.models.Variable;

public class Calculator {

	static ArrayList<String> table = new ArrayList<>();

	public static Node analyse(String s) throws SyntaxeErrorException {
		s = s.trim();
		// System.out.println("|" + s + "|");

		int i = 0;
		if (s.contains("(")) {
			while (s.contains("(")) {
				// if faut prendre le dernier index de (
				int lastIndex = s.lastIndexOf('(');
				String s1 = s.substring(lastIndex);
				// le premier ) après ( => c'est sa parenthèse fermante
				String s2 = s1.substring(1, s1.indexOf(')'));
				// c'est le s2 qu'on doit ajouter dans un table
				table.add(i, s2);
				String id = "@" + i++;
				String s3 = s.replace("(" + s2 + ")", id);
				s = new String(s3);
			}
			return analyse(s);
		}

		int indexAdress = s.indexOf("@");
		if (indexAdress == 0) {
			int j = indexAdress + 1;
			int index = ((int) s.charAt(j)) - 48;
			return analyse(table.get(index));
		}

		int indexMoins = s.indexOf("-");
		if (indexMoins == 0)
			return new Subtraction(new Constant(0), analyse(s.substring(1)));
		if (indexMoins > 0)
			return new Subtraction(analyse(s.substring(0, indexMoins)),
					analyse(s.substring(indexMoins + 1, s.length())));

		int indexPlus = s.indexOf("+");
		if (indexPlus == 0)
			return new Plus(new Constant(0), analyse(s.substring(1)));
		if (indexPlus > 0)
			return new Plus(analyse(s.substring(0, indexPlus)), analyse(s.substring(indexPlus + 1, s.length())));

		int indexDivision = s.indexOf("/");
		if (indexDivision == 0)
			throw new SyntaxeErrorException("Syntaxe error expected");
		if (indexDivision > 0)
			return new Division(analyse(s.substring(0, indexDivision)),
					analyse(s.substring(indexDivision + 1, s.length())));

		int indexMultiplication = s.indexOf("*");
		if (indexMultiplication == 0)
			throw new SyntaxeErrorException("Syntaxe error expected");
		if (indexMultiplication > 0)
			return new Multiplication(analyse(s.substring(0, indexMultiplication)),
					analyse(s.substring(indexMultiplication + 1, s.length())));

		if (s.indexOf("sin") == 0)
			return new Sin(analyse(s.substring(3)));
		if (s.indexOf("cos") == 0)
			return new Cos(analyse(s.substring(3)));
		if (s.indexOf("tan") == 0)
			return new Tan(analyse(s.substring(3)));
		if (s.indexOf("log") == 0)
			return new Log(analyse(s.substring(3)));
		if (s.indexOf("exp") == 0)
			return new Exp(analyse(s.substring(3)));
		if (s.equalsIgnoreCase("pi"))
			return new Constant((float) Math.PI);
		if (s.equalsIgnoreCase("x"))
			return new Variable();
		else
			return new Constant(stringToFloat(s));
	}

	// function to convert string to float
	public static float stringToFloat(String s) {
		float x;
		try {
			x = (float) Float.parseFloat(s);
			return x;
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return 0;
	}

	public static float evaluateExpression(String expression, float x)
			throws SyntaxeErrorException, DivisionByZeroException, LogByZeroException, NumberFormatException {
		Node nd = analyse(expression);
		float result = nd.eval(x);
		nd.buidTree();

		// Check for division by zero
		if (nd instanceof Division && result == Float.POSITIVE_INFINITY) {
			throw new DivisionByZeroException("Division by zero error.");
		}

		// Check for log by zero
		if (nd instanceof Log && result == Float.NEGATIVE_INFINITY) {
			throw new LogByZeroException("Logarithm inside zero error.");
		}

		return result;

	}

}
