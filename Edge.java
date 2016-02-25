package neuralnetwork;
import java.util.*;
import neuralnetwork.Neuron;
import neuralnetwork.MathUtilities;

public class Edge{
	public double weight;
	public double lastWeight;
	public Neuron source;
	public Neuron destination;
	
	public Edge(Neuron source, Neuron destination,int num_connections){
		this.source = source;
		this.destination = destination;
		this.weight = (MathUtilities.getRandom())/num_connections;
		source.outgoingEdges.add(this);
		destination.incomingEdges.add(this);
	}
	public void printEdge(){
		System.out.println(source.index + " "+ destination.index + " " + weight);
	}
}
