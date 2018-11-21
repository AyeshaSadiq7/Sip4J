package graphutilities;



import graphconstruction.Graph_Construction;
import graphconstruction.LabeledEdge;
import graphstructure.E_ClassGraphs;
import graphstructure.E_MVertice;
import graphstructure.E_MethodGraph;
import graphstructure.E_PackGraphs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;


import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DirectedMultigraph;
//import org.jgrapht.graph.DirectedSubgraph;
import org.jgrapht.graph.GraphUnion;

import com.jgraph.layout.tree.JGraphTreeLayout;

public class Graph_Visualization extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private String methodName;
	
	private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );
	
	//private static JGraphModelAdapter<String, DefaultEdge> m_jgAdapter;
	private static JGraphModelAdapter<String, LabeledEdge> m_jgAdapter;
	
	
	public Graph_Visualization(String method) {
	        //Make the X close the application.
	        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	       
	        Dimension d = getMaximumSize();
	        
	        //setSize(d.width, d.height);
	        
	        try
	        {
	            setUIFont(new javax.swing.plaf.FontUIResource("Tahoma",Font.PLAIN,14));
	        }
	        catch(Exception e){}
	        
	        setSize(DEFAULT_SIZE);
	        
	        this.methodName = method;     
	}
	
    public void paint(Graphics g) {
    	
    	//A good idea to call super.paint() to make sure all components get repainted.
        
    	super.paint(g);
        
       // g.drawString(" This is Method "+methodName, 10, 20);
    }
    public static void setUIFont(javax.swing.plaf.FontUIResource f)
    {   
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while(keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof javax.swing.plaf.FontUIResource) UIManager.put(key, f);
        }
    }

    public static void displayGraph(){
    	
    	Graph_Visualization app = new Graph_Visualization("Hello");
    	
    	//app.setLayout(new FlowLayout());
    	
    	//ArrayList<JGraph> graphList = new ArrayList<JGraph>();
    	
    	///////////////////////
    	
    	ArrayList<HashMap<String,JGraph>> graphList = new ArrayList<HashMap<String,JGraph>>();
    	
    	HashMap<String, JGraph> map = new HashMap<String, JGraph>();

        ////////////////////////
    	
    	JGraph jgraph  = null;
    	
    	E_PackGraphs pkg = Graph_Generator.getPackage();

		LinkedList<E_ClassGraphs> _gclass = pkg.getClasses();
	
	for(E_ClassGraphs _class:_gclass){
    	
    	LinkedList<E_MethodGraph> mgraphs = _class.getMethodgraphs();
    	
    	int i = 0;
    	
    	for(E_MethodGraph graph:mgraphs){
    		
    		//System.out.println("Graph to be displayed = "+graph.getMgraphName());
    		
    		//DirectedGraph<String, LabeledEdge> gg = graph.getMethodgraph();
    	
    		//m_jgAdapter = new JGraphModelAdapter<String, LabeledEdge>(gg);
    		
    		//jgraph = new JGraph(m_jgAdapter);
    		
    		// map.put("foo"+i, jgraph);
    	       
    		 //graphList.add(map);
    		 
    		// i++;
    		
    	}
    	
    	/*int i = 1;
    	
    	for(DirectedGraph<String, LabeledEdge> g: graphs){
    		
    		m_jgAdapter = new JGraphModelAdapter<String, LabeledEdge>(g);
    		
    		jgraph = new JGraph(m_jgAdapter);
    		
    		 map.put("foo"+i, jgraph);
    	       
    		 graphList.add(map);
    	        
    		  //graphList.add(jgraph);
              i++;
    	}*/
    	
    	JPanel panel = new JPanel();
    	
        app.add(panel);
    	
    	for(int j = 0; j < graphList.size(); j++){
    		
    		JGraph graph = null;
    		
    	//System.out.println("Size of Graph List = "+graphList.size());
    		
    		if(!graphList.get(j).isEmpty()){
    		
    			graph = graphList.get(j).get("foo"+j);
    	
    		}
    	     panel.add(graph);
    	
    	     app.setVisible(true);
    	}
    	
	}
   }

	public static void positionVertexAt( Object vertex, int x, int y ) {

		DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );

        Map  attr = cell.getAttributes();
   //      Rectangle       b    =  new Rectangle((int)(Math.random()*1000),(int)(Math.random()*500),100,30);

        Rectangle  b    =  new Rectangle(20,50 ,100,30);
        
        GraphConstants.setBounds(attr, b );

         Map cellAttr = new HashMap(  );
         
         cellAttr.put( cell, attr );

         m_jgAdapter.edit( cellAttr, null, null, null );
    }

 public static void main(String[] args) {
    	
    	Graph_Visualization app = new Graph_Visualization("Gello");
    	
        app.setVisible(true);
    
    }
	
}
	
	
