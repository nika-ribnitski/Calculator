package com.example.simplecalculator;

import java.util.Stack;

public class CalculatorUtil {

    // instance variables **************************************************************************

    private String value; // Stores multi-digit numbers as they are being built, before they are added to the array arr
    private boolean decimalUsed; // records if a decimal has already been used in a number
    private String[] arr; // array that holds all the user input before it gets computed
    private int tail; // keeps track of the next empty space in the array arr


    // constructor *********************************************************************************

    public CalculatorUtil(){
        value = "";
        decimalUsed = false;
        arr = new String[20];
        tail = 0;
    }


    // setter and getter methods *******************************************************************

    public String getValue(){
        return this.value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public void appendValue(String value){
        this.value += value;
    }

    public boolean getDecimalUsed(){
        return this.decimalUsed;
    }

    public void setDecimalUsed(boolean decimalUsed){
        this.decimalUsed = decimalUsed;
    }

    public void addToArray(String value){
        this.arr[tail++] = value;
    }

    public String peek(){
        return this.arr[tail-1];
    }

    public int getTail(){
        return this.tail;
    }

    public void setTail(int tail){
        this.tail = tail;
    }


    // instance methods ****************************************************************************

    // computes the answer
    protected float compute(){
        float result = 0f;
        String[] post = in2post(this.arr);
        result = post2ans(post);
        return result;
    }

    // adds an operator and the value preceding it to the array
    protected void operation(String operator){
        if (this.tail<2){
            this.arr[tail++] = this.value;
            this.arr[tail++] = operator;
        } else{
            if (this.peek() != ")"){
                this.arr[this.tail++] = this.value;
            }
            this.arr[this.tail++] = operator;
        }
        this.value=""; // reset tracking variables
        this.decimalUsed = false;
    }

    // empties the array
    protected void spill(){
        for(int i=0 ; i<(this.tail-1) ; i++){
            this.arr[i] = "";
        }
        this.tail=0;
    }


    // static methods ******************************************************************************

    // converts an array from infix to postfix notation
    protected String[] in2post(String[] a){
        String[] result = new String[20];
        int resCount = 0;
        Stack<String> stack = new Stack<String>();
        String s;

        for (int i=0 ; i<(tail) ; i++) {
            s = a[i];

            switch (s) {
                case "/":
                case "*":
                case "-":
                case "+":
                    while (!stack.isEmpty() && Prec(s) <= Prec(stack.peek())) {
                        result[resCount++] = stack.pop();
                    }
                    stack.push(s);
                    break;
                case "(":
                    stack.push(s);
                    break;
                case ")":
                    while (!stack.isEmpty() && stack.peek() != "(") {
                        result[resCount++] = stack.pop();
                    }
                    stack.pop();    //pop the open parentheses
                    break;
                default:      //by this point assume it is a number
                    result[resCount++] = s;
            }
        }

        // pop all the operators from the stack
        while (!stack.isEmpty()){
            result[resCount++] = stack.pop();
        }

        tail = resCount;
        return result;
    }

    // a utility function to return precedence of a given operator (higher returned value means higher precedence)
    static int Prec(String s){
        switch (s)
        {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
        }
        return -1;
    }

    // calculates the answer from postfix notation
    protected float post2ans(String[] a){
        Stack<Float> stack=new Stack<>();
        float result = 0f;
        String s;

        for (int i=0 ; i<(tail) ; i++){
            boolean notNum = true;
            s = a[i];

            switch (s){
                case"/":
                case"*":
                case"+":
                case"-":
                    break;
                default:  // by here assume number
                    stack.push(toFloat(s));
                    notNum = false;
            }

            if(notNum){
                float val2 = stack.pop();
                float val1 = stack.pop();

                switch(s){
                    case"+":
                        stack.push(val1+val2);
                        break;
                    case"-":
                        stack.push(val1-val2);
                        break;
                    case"/":
                        stack.push(val1/val2);
                        break;
                    case"*":
                        stack.push(val1*val2);
                        break;

                }
            }
        }
        return stack.pop();
    }

    // converts a String to a float
    protected float toFloat(String s){
        return Float.parseFloat(s);
    }

}
