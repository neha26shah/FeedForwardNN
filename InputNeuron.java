package neuralnetwork;
import java.util.*;
import neuralnetwork.Neuron;

public class InputNeuron extends Neuron{
	public InputNeuron(int index){
		super(index);
	}
	
	public void learnSingleSample(double alpha,double [] label){
		return;
	}
	public double evaluate(double [] inputVector){
		lastYj = inputVector[index];
		return inputVector[index];
	}
}
