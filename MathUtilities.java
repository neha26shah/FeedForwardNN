package neuralnetwork;
import java.util.*;

public class MathUtilities{
	public static Random generator = new Random(0L);
	
	public static double getRandom(){
		return generator.nextFloat()-0.5;
	}
	
	public static double getSigmoid(double value){
		return (1.0/( 1 + Math.pow(Math.E,(-1*value))));
	}
	
	public static double getSigmoidDiff(double value){
		return getSigmoid(value)*(1- getSigmoid(value));
	}
	
	public static double hyperbolictanh(double value){
		return 1.7159*Math.tanh(2.0*value/3.0);
	}
	
	public static double hyperbolictanhDiff(double value){
		return 1.7159*2*(1- (hyperbolictanh(value)*hyperbolictanh(value)))/3.0;
	}
	
	public static double activationFunction(double value){
		return getSigmoid(value);
	}
	
	public static double activationFunctionDiff(double value){
		return getSigmoidDiff(value);
	}
}
