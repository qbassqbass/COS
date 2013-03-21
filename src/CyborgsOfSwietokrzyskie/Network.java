/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CyborgsOfSwietokrzyskie;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author qbass
 */
public class Network {
    private int networkid;
    private String owner;
    private Node nodes[];
    
    public Network(int nodeamount, int lastnetid){
        Random r = new Random();
        this.networkid = lastnetid+1;
        this.owner = null;
        nodes = new Node[nodeamount+1];
        nodes[0] = new Node(lastnetid+1);
        for(int i = 1;i<nodeamount+1;i++){
            nodes[i] = new Node(this.networkid,nodes[i-1].getnodeid());
            nodes[i].makeconnection(0);
            for(int j = 0;j<4;j++){
                int tmp = r.nextInt(nodeamount+2)-1;
                System.out.println(tmp);
                nodes[i].makeconnection(tmp);
            }
        }
        this.savenet("testNet");
    }
    
    public Network(int nodeamount, int lastnetid, String owner){
        Random r = new Random();
        this.networkid = lastnetid+1;
        this.owner = owner;
        nodes = new Node[nodeamount];
        nodes[0] = new Node(lastnetid+1, owner);
        for(int i = 1;i<nodeamount;i++){
            nodes[i] = new Node(this.networkid,nodes[i-1].getnodeid(),owner);
            nodes[i].makeconnection(0);
            System.out.println("node "+i);
            for(int j = 0;j<2;j++){
                int tmp = r.nextInt(nodeamount+2)-1;
                System.out.println(tmp);
                nodes[i].makeconnection(tmp);
            }
            
        }
    }
    
    public void setowner(String who){
        this.owner = who;
    }
    
    public String checkowner(){
        return this.owner;
    }
    
    public void deactivatenet(Robots who){
        for(int i = 0;i<this.nodes.length;i++){
            this.nodes[i].deactivate(who);
        }
    }
    
    public void savenet(String filename){
        filename = "Networks/"+filename;
        String nodefile = filename+"_nodes.txt";
        String edgefile = filename+"_edges.txt";
        int net = 0, node = 0;
        System.out.println("Saving network...");
        try{           
           PrintWriter write = new PrintWriter(nodefile);
           write.println("Id,Label");
           for(node = 0;node<this.nodes.length;node++){
               System.out.println(node+","+net+"node"+node);
               write.println(node+","+net+"node"+node);
           }
           write.close();
           write = new PrintWriter(edgefile);
           write.println("Id,Source,Target,Type");
           int id = 0;
           for(node = 1;node<this.nodes.length;node++){
               for(int con = 0;con<this.nodes[node].listconnections().length;con++){
                   System.out.println(id+","+node+","+this.nodes[node].listconnections()[con]+",Undirected");
                   write.println(id+","+node+","+this.nodes[node].listconnections()[con]+",Undirected");
                   id++;
               }
           }
           write.close();
        }catch(FileNotFoundException e){
           System.out.println(e);
        }
    }
    
}
