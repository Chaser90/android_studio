package com.example.doublecalculator;

import java.text.DecimalFormat;

class Calculator {

   private final String CLEAR_INPUT_TEXT = "0";
   private double resultNumber = 0;
   private double lastInputNumber = 0;

   private String operator = "+";
   private String operatorString = "";

   private DecimalFormat decimalFormat = new DecimalFormat("###,###.#####");

   public Calculator () {
       decimalFormat = new DecimalFormat("###,###.#####");
   }

   public Calculator(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    String getDecimalString(String changeString) {
        String setChangeString = changeString.replace(",","");
        return decimalFormat.format(Double.parseDouble(setChangeString));
    }

    private String getDecimalString(double changeNumber) {
        return decimalFormat.format(changeNumber);
    }

    String getOperatorString() {
        return operatorString;
    }

    String getclearInputText(){
        return CLEAR_INPUT_TEXT;
    }



    void setAllClear(){
        resultNumber = 0;
        lastInputNumber = 0;
        operator = "+";
        operatorString = "";
    }

   private double doubleCalculator(double result, double lastNumber, String operator) {
        switch (operator) {
            case "+":
                result += lastNumber;
                break;
            case "-":
                result -= lastNumber;
                break;
            case "*":
                result *= lastNumber;
                break;
            case "/":
                result /= lastNumber;
                break;

        }
            return result;
    }

    String getResult(boolean isFirstInput, String getResultString, String lastOperator) {
        if (isFirstInput) {
            if(lastOperator.equals("=")){
                resultNumber = doubleCalculator(resultNumber, lastInputNumber, operator);
                operatorString = "";
            } else {
                operator = lastOperator;
                if (operatorString.equals("")) {
                    operatorString = getResultString + " " + lastOperator;
                } else {
                    operatorString = operatorString.substring(0, operatorString.length() - 1);
                    operatorString = operatorString + lastOperator;
                }
            }

        } else {
            lastInputNumber = Double.parseDouble(getResultString.replace(",", ""));
            resultNumber = doubleCalculator(resultNumber, lastInputNumber, operator);
            if(lastOperator.equals("=")){
                operatorString = "";
            } else {
                operatorString = operatorString + " " + getResultString + " " + lastOperator;
                operator = lastOperator;
            }
        }
        return getDecimalString(resultNumber);
    }

}
