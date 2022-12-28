package DS09;

import java.util.Arrays;

public class Calculator {
    // 상수
    private static final int MAX_EXPRESSION_LENGTH = 20;

    // 비공개 인스턴스 변수
    private Stack<Character> _operatorStack;
    private String _infixExpression;
    private String _postfixExpression;
    private PostfixCalculator _postfixCalculator;

    // getter, setter
    private String infixExpression(){
        return this._infixExpression;
    }
    private void setInfixExpression(String newInfixExpression){
        this._infixExpression = newInfixExpression;
    }

    private String postfixExpression(){
        return this._postfixExpression;
    }
    private void setPostfixExpression(String newPostExpression) {
        this._postfixExpression = newPostExpression;
    }

    private PostfixCalculator postfixCalculator(){
        return this._postfixCalculator;
    }
    private void setPostfixCalculator(PostfixCalculator newPostfixCalculator){
        this._postfixCalculator = newPostfixCalculator;
    }

    private Stack<Character> operatorStack(){
        return this._operatorStack;
    }
    private void setOperatorStack(Stack<Character> newOperatorStack){
        this._operatorStack = newOperatorStack;
    }

    // 생성자
    public Calculator(){
        this.setOperatorStack(new ArrayList<Character>(Calculator.MAX_EXPRESSION_LENGTH));
        this.setPostfixCalculator(new PostfixCalculator(Calculator.MAX_EXPRESSION_LENGTH));
    }

    // 공개함수
    public int evaluate(String anInfixExpression) throws CalculatorException {
        this.setInfixExpression(anInfixExpression);
        AppView.outputLineDebugMessage("[Infix to Postfix] " + anInfixExpression);
        if (this.infixExpression() == null || this.infixExpression().length() == 0){
            throw new CalculatorException(CalculatorError.InfixError_NoExpression);
        }

        CalculatorError infixError = this.infixToPostfix();
        if (infixError == CalculatorError.InfixError_None){
            AppView.outputLineDebugMessage("[Evaluate Postfix] " + this.postfixExpression());
            return this.postfixCalculator().evaluate(this.postfixExpression());
        }
        else{
            throw new CalculatorException(infixError);
        }
    }

    // 비공개 함수
    private void showOperatorStack(String anOperationLabel){
        AppView.outputDebugMessage("  : " + anOperationLabel + " OperatorStack <Bottom> ");

            for (int order = 0; order < this.operatorStack().size(); order++){
                AppView.outputDebugMessage(
                        ((ArrayList<Character>)this.operatorStack()).elementAt(order).charValue() + " ");
            }

        AppView.outputLineDebugMessage("<Top>");
    }

    private void showTokenAndPostfixExpression(char aToken, char[] aPostfixExpressionArray){
        AppView.outputDebugMessage(aToken + " : (Postfix 수식으로 출력) ");
        AppView.outputLineDebugMessage(new String(aPostfixExpressionArray));
    }

    private void showTokenAndMessage(char aToken, String aMessage){
        AppView.outputLineDebugMessage(aToken + " : (" + aMessage + ") ");
    }

    private int inComingPrecedence(Character aToken){
        switch (aToken.charValue()){
            case '(': return 20;
            case ')': return 19;
            case '^': return 17;
            case '*': return 13;
            case '/': return 13;
            case '%': return 13;
            case '+': return 12;
            case '-': return 12;
            default:
                return -1;
        }
    }

    private int inStackPrecedence(Character aToken){
        switch (aToken.charValue()){
            case '(': return 0;
            case ')': return 19;
            case '^': return 16;
            case '*': return 13;
            case '/': return 13;
            case '%': return 13;
            case '+': return 12;
            case '-': return 12;
            default:
                return -1;
        }
    }

    private CalculatorError infixToPostfix(){
        char[] postfixExpressionArray = new char[this.infixExpression().length()];
        Arrays.fill(postfixExpressionArray, ' ');   // char 배열을 공백 문자로 채워 초기화 한다.

        Character currentToken, poppedToken, topToken;
        this.operatorStack().clear(); // 연산자 스택 초기화
        int p = 0;
        for (int i = 0; i < this.infixExpression().length(); i++){
            currentToken = this.infixExpression().charAt(i);
            if (Character.isDigit(currentToken.charValue())){
                postfixExpressionArray[p++] = currentToken;
                this.showTokenAndPostfixExpression(currentToken, postfixExpressionArray);
            }
            else{
                if (currentToken == ')'){
                    this.showTokenAndMessage(currentToken, "왼쪽 괄호가 나타날 때 까지 스택에서 꺼내어 출력");
                    poppedToken = this.operatorStack().pop();
                    while (poppedToken != null && poppedToken.charValue() != '('){
                        postfixExpressionArray[p++] = poppedToken.charValue();
                        this.showOperatorStack("Popped");
                        this.showTokenAndPostfixExpression(poppedToken, postfixExpressionArray);
                        poppedToken = this.operatorStack().pop();
                    }
                    if (poppedToken == null){
                        return CalculatorError.InfixError_MissingLeftParen;
                    }
                    this.showOperatorStack("Popped");
                }
                else{
                    int inComingPrecedence = this.inComingPrecedence(currentToken.charValue());
                    if(inComingPrecedence < 0){
                        AppView.outputLineDebugMessage(currentToken + " : (Unknown Operator)");
                        return CalculatorError.InfixError_UnknownOperator;
                    }
                    this.showTokenAndMessage(currentToken, "입력 연산자보다 순위가 높지 않은 연산자를 스택에서 꺼내어 출력");
                    topToken = this.operatorStack().peek();
                    while (topToken != null && this.inStackPrecedence(topToken) >= inComingPrecedence){
                        poppedToken = this.operatorStack().pop();
                        postfixExpressionArray[p++] = poppedToken;
                        this.showOperatorStack("Popped");
                        this.showTokenAndPostfixExpression(poppedToken, postfixExpressionArray);
                        topToken = this._operatorStack.peek();
                    }
                    if (this.operatorStack().isFull()){
                        this.showOperatorStack("Fulled");
                        return CalculatorError.InfixError_TooLongExpression;
                    }
                    this.operatorStack().push(currentToken);
                    this.showOperatorStack("Pushed");
                }
            }
        }
        AppView.outputLineDebugMessage("(End of infix expression: 스택에서 모든 연산자를 꺼내어 출력)");

        while (!this.operatorStack().isEmpty()){
            poppedToken = this.operatorStack().pop();
            this.showOperatorStack("Popped");
            if (poppedToken == '(') {
                return CalculatorError.InfixError_MissingRightParen;
            }
            postfixExpressionArray[p++] = poppedToken;
            this.showTokenAndPostfixExpression(poppedToken, postfixExpressionArray);
        }
        this.setPostfixExpression(new String(postfixExpressionArray).trim());
        return CalculatorError.InfixError_None;
    }
}
