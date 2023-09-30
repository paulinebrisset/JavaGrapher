package Eval;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvaluatorProvisoire {
    public static float randomFunctionResult(String expression, float x) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        // Replace 'x' with the actual value of x in the expression
        // Replace standard mathematical functions and x² in the expression
        String updatedExpression = expression
                .replace("cos(x)", "Math.cos(" + x + ")")
                .replace("sin(x)", "Math.sin(" + x + ")")
                .replace("log(x)", "Math.log(" + x + ")")
                .replace("x", String.valueOf(x))
                .replace("x²", "Math.pow(" + x + ", 2)");

        try {
            Object result = engine.eval(updatedExpression);
            if (result instanceof Number) {
                return ((Number) result).floatValue();
            } else {
                throw new IllegalArgumentException("Expression did not return a valid number.");
            }
        } catch (ScriptException e) {
            throw new IllegalArgumentException("Invalid expression: " + expression, e);
        }
    }

}
