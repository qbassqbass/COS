/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CyborgsOfSwietokrzyskie;

/**
 *
 * @author qbass
 */
public class Node {
    private int netid;
    private int nodeid;
    private int parentid;
    private int connections;
    private int[] conns;
    private boolean active;
    private boolean server;
    private String owner;
    private int seclvl;
    
    //For nodes creation
    public Node(int netid, int lastnodeid, String owner){
        this.netid = netid;
        this.nodeid = lastnodeid+1;
        this.active = true;
        this.server = false;
        this.seclvl = 0;
        this.owner = owner;
        this.conns = new int[5];
    }
    
    public Node(int netid, int lastnodeid){
        this.netid = netid;
        this.nodeid = lastnodeid+1;
        this.active = true;
        this.server = false;
        this.seclvl = 0;
        this.owner = null;
        this.conns = new int[5];
    }
    //For server creation
    public Node(int netid, String owner){
        this.owner = owner;
        this.netid = netid;
        this.nodeid = 0;
        this.active = true;
        this.server = true;
        this.seclvl = 2;
    }
    public Node(int netid){
        this.owner = null;
        this.netid = netid;
        this.nodeid = 0;
        this.active = true;
        this.server = true;
        this.seclvl = 2;
    }
    
    public int securecheck(){
        return seclvl;
    }
    public int getnodeid(){
        return this.nodeid;
    }
    public int getnetid(){
        return this.netid;
    }
    public int getparent(){
        return this.parentid;
    }
    
    public boolean isactive(){
        return this.active;
    }
    public boolean isserver(){
        return this.server;
    }
    
    public void setowner(String who){
        this.owner = who;
    }
    
    public int securenode(Robots who){
        if (who.hacklvlc()<this.seclvl){
            return -1;
        }else{
            this.seclvl++;
            return 0;
        }
    }
    
    public int deactivate(Robots who){
        if (who.hacklvlc()<this.seclvl+3){
            return -1;
        }else{
            this.active = false;
            return 0;
        }
    }
    
    public int cracknode(Robots who){
        if (who.hacklvlc()<this.seclvl){
            return -1;
        }else{
            this.seclvl--;
            return 0;
        }
    }
    
    public void makeconnection(int nodeid){       
        if(nodeid<0);
        else{
            this.conns[this.connections] = nodeid;
            this.connections++;
        }
    }
    
    public int[] listconnections(){
        return this.conns;
    }
}
