/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CyborgsOfSwietokrzyskie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author qbass
 */

public class Robots {
    private String name;    
    //private String alias;
    //private String path;
    //private String file;
    private boolean cpu;
    private int type;
    private String nametype;
    private boolean alive;
    private int lvl;
    private int hacklvl;
    private int exp;
    private int health;
    private int attack;
    private int speed;
    private int att_range;  
    private int kills;
    private int killed;
    private Network homenet;
    private String macroatt[];
    private String macrodef[];
    

    public Robots(String name, int type,boolean cpu){
        String filename;
        if(cpu) filename = "cpu/"+name;
        else filename = "saved/"+name;
        File check = new File(filename);
        if (check.exists()){
            try{
                if(this.readfile(filename) == 0) System.out.println("Robot "+name+" exists. Read OK");
                else System.out.println("Robot "+name+"doesn't exist. Read ERROR");
            }catch(FileNotFoundException e){
                System.out.println(e);
            }
        }else{
            this.cpu = cpu;
            this.name = name;
            this.type = type;
            this.lvl = 0; 
            this.exp = 0;
            this.kills = 0;
            this.killed = 0;
            typedef(type);
            try{
                this.savefile(this.name,"info.txt");
            }catch(FileNotFoundException e){
                System.out.println(e);
            }
        }
        makenet(this.type);
    }
    
    public Robots(String filein) throws FileNotFoundException{
        filein = "saved/"+filein;
        if(this.readfile(filein) == 0) System.out.println("Read OK");
        else System.out.println("Read ERROR");
    }
    
    public void winbattle(int opplvl){
        int levl = this.lvl - opplvl;
        this.kills++;
        if (levl > 0) addexp(15*lvl);
    }
    
    public void losebattle(){
        this.killed++;
        this.alive = false;
        this.health = 0;
    }
    
    public int getlvl(){
        return this.lvl;
    }
    
    private void addexp(int xp){
        this.exp += xp;
        this.check_exp();
    }
    
    private void makenet(int type){
        switch(type){
            case 0:{
                this.homenet = new Network(7,0,this.name);
                break;
            }
            case 1:{
                this.homenet = new Network(3,0,this.name);
                break;
            }
        }
    }
    
    private void typedef(int type){
        switch(type){
            case 0:{
                this.nametype = "Engineer";
                this.health = 10;
                this.attack = 5;
                this.speed = 12;
                this.att_range = 5;
                this.hacklvl = 3;
                break;
            }
            case 1:{
                this.nametype = "Tank";
                this.health = 25;
                this.attack = 13;
                this.speed = 3;
                this.att_range = 1;
                this.hacklvl = 1;
                break;                
            }
        }
    }
    
    public int hacklvlc(){
        return this.hacklvl;
    }
    
    public String namec(){
        return this.name;
    }
    public int healthc(){
        return this.health;
    }
    public int attackc(){
        return this.attack;
    }
    public void healthch(int h){
        if(h==0) this.health = 0;
        else this.health += h;
    }
    
    public void printstat(){
        System.out.println("Name: "+this.name);
        System.out.println("Type: "+this.type);
        System.out.println("Class: "+this.nametype);
        System.out.println("Health: "+this.health);
        System.out.println("Attack: "+this.attack);
        System.out.println("Attack Range: "+this.att_range);
        System.out.println("EXP: "+this.exp);
        System.out.println("Level: "+this.lvl);
        System.out.println("Hack Level: "+this.hacklvl);
        System.out.println("Speed: "+this.speed);
        System.out.println("Kills: "+this.kills);
        System.out.println("Killed: "+this.killed);
    }
    
    public void check_exp(){
        if(this.exp > 400){
            this.exp -= 400;           
            this.lvlup();
        }
    }
    
    private void lvlup(){
        this.lvl++;
        System.out.println("Congratulations "+this.name+"! You've reached "+this.lvl+" level!");
        switch(this.type){
            case 0: {
                this.health += 2;
                this.attack += 1;
                this.speed += 1;
                this.att_range += 2;
            }
        }
        this.check_exp();
        try{
            this.savefile(this.name,"info.txt");
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
    
    private void savefile(String name, String fileout) throws FileNotFoundException{
        
        File dir = new File("saved/"+name);
        if(!dir.exists()){
            System.out.println("creating directory: " + dir.getName());
            boolean result = dir.mkdir();
            if(result){    
                System.out.println("DIR created");  
            }
        }
        fileout = "saved/"+name+"/"+fileout;
        PrintWriter write = new PrintWriter(fileout);
        write.println(this.name);
        write.print(this.type+" ");
        write.print(this.nametype+" ");
        write.print(this.health+" ");
        write.print(this.attack+" ");
        write.print(this.att_range+" ");
        write.print(this.lvl+" ");
        write.print(this.hacklvl+" ");
        write.print(this.exp+" ");
        write.print(this.speed+" ");
        write.print(this.kills+" ");
        write.print(this.killed+" ");
        write.close();
        
    }
    
    private int readfile(String filein) throws FileNotFoundException{
        File file = new File(filein+"/info.txt");
        if(file.exists()){
            Scanner read = new Scanner(file);
            this.name = read.nextLine();
            this.type = read.nextInt();
            this.nametype = read.next();
            this.health = read.nextInt();
            this.attack = read.nextInt();
            this.att_range = read.nextInt();
            this.lvl = read.nextInt();
            this.hacklvl = read.nextInt();
            this.exp = read.nextInt();
            this.speed = read.nextInt();
            this.kills = read.nextInt();
            this.killed = read.nextInt();
            return 0;
        }else{
            return -1;
        }
    }
    
}
