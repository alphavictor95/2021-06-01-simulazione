package it.polito.tdp.genes.model;

public class Adiacente implements Comparable<Adiacente>{
	private Genes gene;
   private Double peso;
   
	public Adiacente(Genes gene, Double peso) {
	super();
	this.gene = gene;
	this.peso = peso;
}
	

	public Genes getGene() {
		return gene;
	}


	public void setGene(Genes gene) {
		this.gene = gene;
	}


	public Double getPeso() {
		return peso;
	}


	public void setPeso(Double peso) {
		this.peso = peso;
	}


	@Override
	public int compareTo(Adiacente o) {
		// TODO Auto-generated method stub
		return -(this.peso.compareTo(o.peso));
	}


	@Override
	public String toString() {
		return  gene + " " + peso + "\n";
	}
	
	

}
