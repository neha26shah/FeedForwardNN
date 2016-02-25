package neuralnetwork;
import java.util.*;
import neuralnetwork.Neuron;
import neuralnetwork.Edge;
import neuralnetwork.InputNeuron;
import neuralnetwork.BiasNeuron;

public class NeuralNetwork{
	public int numLayers;
	public InputNeuron [] inputNeurons;
	public Neuron [] outputNeurons;
	
	public NeuralNetwork(int numInput,int numOutput,int numLayers,int [] numNeuronLayer){
		this.numLayers= numLayers;
		this.inputNeurons = new InputNeuron[numInput];
		this.outputNeurons = new Neuron[numOutput];
		for(int i=0;i<numInput;i++){
			this.inputNeurons[i] = new InputNeuron(i);
		}
		for(int j=0;j<numOutput;j++){
			this.outputNeurons[j] =new  Neuron(j);
		}
		ArrayList<Neuron []> listLayers = new ArrayList<Neuron []>();
		for(int i=0;i<numLayers;i++){
			Neuron [] list_neurons = new Neuron[numNeuronLayer[i]];
			for(int j=0;j<numNeuronLayer[i];j++){
				list_neurons[j] = new Neuron(j);
			}
			listLayers.add(list_neurons);
		}
		Neuron [] hid1 = listLayers.get(0);
		for (int i=0;i<inputNeurons.length;i++){
			for(int j=0;j<hid1.length;j++){
				new Edge(inputNeurons[i],hid1[j],numInput);
			}
		}
		for(int l1 = 0;l1<numLayers-1;l1++){
			Neuron [] layer1 = listLayers.get(l1);
			Neuron [] layer2 = listLayers.get(l1+1);
			for(int i=0;i<layer1.length;i++){
				for(int j=0;j<layer2.length;j++)
					new Edge(layer1[i],layer2[j],layer1.length);
			}
		}
		Neuron hidlast [] = listLayers.get(this.numLayers-1);
		for(int i =0;i<hidlast.length;i++){
			for(int j=0;j<outputNeurons.length;j++){
				new Edge(hidlast[i],outputNeurons[j],hidlast.length);
			}
		}
	}
	public void learnSingleSample(double alpha,double [] inputVector,double[] outputVector){
		double [] result = this.evaluate(inputVector);
		ArrayList<Neuron> todo = new ArrayList<Neuron>();
		for(int i=0;i<outputNeurons.length;i++){
			todo.add(outputNeurons[i]);
		}
		while(true){
			for(Neuron n: todo){
				n.learnSingleSample(alpha,outputVector);
			}
			Neuron g = todo.get(0);
			todo = new ArrayList<Neuron>();
			for(Edge e: g.incomingEdges){
				todo.add(e.source);
			}
			if (todo.size() ==0)
				break;
		}
	}
	public int predictSymbol(double [] inputVector){
		double [] result = evaluate(inputVector);
		int max_r = -1;
		double max_value=0;
		for(int i =0;i<result.length;i++){
			//System.out.println(result[i]);
			if(result[i] > max_value){
				max_value = result[i];
				max_r=i;
			}
		}
		return max_r;
	}
	public void printArray(double arr[]){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	
	}
	public double [] evaluate(double [] inputVector){
		ArrayList<Neuron> todo = new ArrayList<Neuron>();
		for(int i=0;i<inputNeurons.length;i++){
			todo.add(inputNeurons[i]);
		}
		int counter =0;
		while(true){
			for(Neuron n: todo){
				n.evaluate(inputVector);
			}
			Neuron g = todo.get(0);
			todo = new ArrayList<Neuron>();
			for(Edge e: g.outgoingEdges){
				todo.add(e.destination);
			}
			if (todo.size() ==0)
				break;
			counter++;
		}
		double [] result = new double[outputNeurons.length]; 
		for(int i=0;i<outputNeurons.length;i++){
			result[i] = outputNeurons[i].lastYj;
			//System.out.print(result[i] + " ");
		}
		//System.out.println(" ");
		return result;
	}
	public void printNeuralNetwork(){
		ArrayList<Neuron> todo = new ArrayList<Neuron>();
		for(int i=0;i<inputNeurons.length;i++){
			todo.add(inputNeurons[i]);
		}
		int l=0;
		while(true){
			System.out.println("Layer "+l);
			for(Neuron n: todo){
				n.printNeuron();
				System.out.print(" ");
			}
			System.out.println("");
			for(Neuron n: todo){
				n.printEdges();
			}
			Neuron g = todo.get(0);
			todo = new ArrayList<Neuron>();
			for(Edge e: g.outgoingEdges){
				todo.add(e.destination);
			}
			if (todo.size() ==0)
				break;
			l++;
		}
	}
}
