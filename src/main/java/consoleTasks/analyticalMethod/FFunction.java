package consoleTasks.analyticalMethod;

import consoleTasks.Evaluatable;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;

public class FFunction implements Evaluatable {
    Variable x;
    Variable a;
    Expression function;

    public FFunction(String function, double a) {
        Parser parser = new Parser(Parser.STANDARD_FUNCTIONS);
        x = new Variable("x");
        this.a = new Variable("a");
        this.a.setVal(a);
        parser.add(x);
        parser.add(this.a);
        this.function = parser.parse(function);
    }

    public FFunction(String function) {
        this(function, 1.0);
    }

    public double getA() {
        return a.getVal();
    }

    public void setA(double a) {
        this.a.setVal(a);
    }

    @Override
    public double evalf(double x) {
        this.x.setVal(x);
        return function.getVal();
    }

    public double evalfDerivative(double x) {
        this.x.setVal(x);
        return function.derivative(this.x).getVal();
    }
}
