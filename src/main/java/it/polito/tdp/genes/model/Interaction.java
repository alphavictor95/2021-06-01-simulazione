package it.polito.tdp.genes.model;

public class Interaction {

	Genes g1;
	Genes g2;
	String type;
	Double expression_corr;
	public Interaction(Genes g1, Genes g2, String type, Double expression_corr) {
		super();
		this.g1 = g1;
		this.g2 = g2;
		this.type = type;
		this.expression_corr = expression_corr;
	}
	
	public Genes getG1() {
		return g1;
	}
	public void setG1(Genes g1) {
		this.g1 = g1;
	}
	public Genes getG2() {
		return g2;
	}
	public void setG2(Genes g2) {
		this.g2 = g2;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getExpression_corr() {
		return expression_corr;
	}
	public void setExpression_corr(Double expression_corr) {
		this.expression_corr = expression_corr;
	}
	
	
}
