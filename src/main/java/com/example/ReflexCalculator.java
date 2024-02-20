package com.example;

import java.lang.Math;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflexCalculator {

    public String operations(String operator, ArrayList<Double> numbers)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String result = "";
        if (operator == "qck") {
            result = qckOperation(numbers, 0, numbers.size() - 1);
        } else {
            try {
                result = Double.toString(doubleOperations(operator, numbers));
            } catch (NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private String qckOperation(ArrayList<Double> numbers, int inicio, int fin) {
        /**Boolean desorden = true;
        if (inicio < fin) {
            ArrayList<Double> arr = 
            Integer pivot = partition()
        }
        **/
        return "si";
    }

    public double doubleOperations(String operator, ArrayList<Double> numbers) throws NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Class C = Math.class;
        Method m = C.getMethod("cos", Double.TYPE);
        m.invoke(null, numbers.get(0));
        double number = 2.3;
        double result = 0.0;
        if (operator == "cos") {
            result = Math.cos(number);
        }
        if (operator == "tan") {
            result = Math.tan(number);
        }
        if (operator == "abs") {
            result = Math.abs(number);
        }
        if (operator == "acos") {
            result = Math.asin(number);
        }
        if (operator == "sin") {
            result = Math.sin(number);
        }
        return result;
    }

}
