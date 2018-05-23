package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.borders.db.BordersDAO;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;
import org.jgrapht.alg.ConnectivityInspector;


public class Model {
	
   private List<Country> countries;
   private List<Border> borders;
   public Map<Integer, Country> countriesMap;
   
   
   private Graph<Country, DefaultEdge> graph;
   private BordersDAO bdao;

	  public Model() {
		 countries = new ArrayList<Country>();
		 borders = new ArrayList<Border>();
		 countriesMap = new TreeMap<Integer, Country>();
		 bdao = new BordersDAO();
		 }
	  
	  public List<Country> getCountries(){
		  countries = bdao.loadAllCountries();
		  return countries;
	  }
	  
	  public void createGraph(int anno) {
		  
		  graph = new SimpleWeightedGraph<>(DefaultEdge.class);
		  
		  countries = bdao.loadAllCountries();
		  
		  
		  for(Country c : countries) {
			   graph.addVertex(c);
			   countriesMap.put(c.getNo(), c);
		   }
		  
		  addEdges(anno);
		  }
	  
	  public void addEdges(int anno) {
		  
		  borders = bdao.getCountryPairs(anno);
		  for(Border b : borders) {
			 if(countriesMap.containsKey(b.getState1No()) && countriesMap.containsKey(b.getState2No()))
			 graph.addEdge(countriesMap.get(b.getState1No()), countriesMap.get(b.getState2No()) );
		  }
	  }
	
	  public String stampaGrafo() {
		  String s = "";
		  for(Country c : graph.vertexSet()) {
		   s+=c.getName()+", "+graph.degreeOf(c)+" stati confinanti\n";
		  }
		  return s;
	  }

	  public int getNumberOfConnectedComponents() {
		 ConnectivityInspector<Country,DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge>(graph);
		 return ci.connectedSets().size();
	  }
	  
	  public Country trovaCountry(String name) {
		return null;
	  }
	  
	  public List<Country> trovaVicini(Country c){
	      
			  List<Country> vicini = new ArrayList<Country>();
			  GraphIterator<Country, DefaultEdge> gi = new BreadthFirstIterator<Country, DefaultEdge>(graph, c);
			  while(gi.hasNext())
			  vicini.add(gi.next());
			  return vicini;
		  
	  }
	  

}
