package neuralnetwork;
import java.util.*;
import neuralnetwork.Neuron;

public class BiasNeuron extends Neuron{
	public BiasNeuron(int i){
		super(-1);
		lastYj=1.0;
	}
	public void learnSingleSample(double [] label){
		return;
	}
	public double evaluate(double [] inputVector){
		return 1.0;
	}
}
