package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;
import javafx.collections.FXCollections;

public class Model {
	GenesDao dao = new GenesDao();
	Map<String, Genes> idMap;
	Graph<Genes, DefaultWeightedEdge> grafo;
	
	
	public List<String> getGenes(){
		List<String> res = new ArrayList<>();
		for(String s: idMap.keySet()) {
			res.add(s);
		}
		return res;
		
	}
	public String creaGrafo() {
		idMap = new HashMap<String, Genes>();
		dao.getAllGenes(idMap);
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		
        for(Interaction i : dao.getInteractions(idMap)) {
        	if(	i.getG1().getChromosome()==i.getG2().getChromosome()) {
        		Graphs.addEdge(this.grafo, i.getG1(), i.getG2(), Math.abs(i.getExpression_corr()*2.0));
        	}else {
        		Graphs.addEdge(this.grafo, i.getG1(), i.getG2(), Math.abs(i.getExpression_corr()));
            	
        	}
        	}
        return String.format("Grafo creato con %d vertici e %d archi", this.grafo.vertexSet().size(),
        		this.grafo.edgeSet().size());
        }
	public List<Adiacente> getAdiacenti(String selezionato) {
		List<Adiacente> res = new ArrayList<>();
		List<Genes> adiacenti = Graphs.neighborListOf(this.grafo, idMap.get(selezionato));
		for(Genes g : adiacenti) {
			res.add(new Adiacente(g, this.grafo.getEdgeWeight(this.grafo.getEdge(idMap.get(selezionato), g))));
		}
		return res;
	}
	
	
}
