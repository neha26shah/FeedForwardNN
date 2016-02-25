package neuralnetwork;
import java.util.*;
import neuralnetwork.MathUtilities;
import neuralnetwork.Edge;
import neuralnetwork.BiasNeuron;

public class Neuron{
	int index;
	ArrayList<Edge> incomingEdges;
	ArrayList<Edge> outgoingEdges;
	double lastYj;
	double lastVj;
	double gradient;
	
	public Neuron(int index){
		this.index= index;
		incomingEdges = new ArrayList<Edge>();
		outgoingEdges = new ArrayList<Edge>();
		if(index !=-1)	{
			new Edge(new BiasNeuron(-1), this,1);
		}
	}
	public void printNeuron(){
		System.out.print(index);
	}
	public void learnSingleSample(double alpha,double [] outputVector){
		if(this.outgoingEdges.size() ==0){
			this.gradient = MathUtilities.activationFunctionDiff(this.lastVj)*(outputVector[index] - lastYj);
		}
		else{
			double accumulate=0;
			for(Edge e: this.outgoingEdges)
				accumulate=  accumulate + (e.lastWeight * e.destination.gradient);
			this.gradient = this.lastYj *(1 - this.lastYj) * accumulate;
		}
		double c = alpha *this.gradient;
		for(Edge e: this.incomingEdges){
			e.weight = e.weight + (c * e.source.lastYj  );
		}
	}
	public double evaluate(double [] inputVector){
		double sum_values=0;
		for(Edge e: incomingEdges){
			//System.out.println("Hey, "+ e.source.lastYj + " "+ e.weight);
			sum_values = sum_values + e.source.lastYj * e.weight;
			e.lastWeight  = e.weight;
		}
		//System.out.println("Confirming vj "+ sum_values);
		this.lastYj = MathUtilities.activationFunction(sum_values);
		this.lastVj = sum_values;
		//System.out.println("Confirming vj "+ this.lastVj);
		//System.out.println("Confirming yj "+ this.lastYj);
		return this.lastYj;
	}
	
	public void printEdges(){
		for(Edge e: outgoingEdges){
			e.printEdge();
		}
		System.out.println(incomingEdges.get(0).weight);
	}
}
