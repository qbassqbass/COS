/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CyborgsOfSwietokrzyskie;

import java.io.File;
import java.util.Scanner;


/**
 *  http://translate.google.pl/#vi/pl/Cyborgs%20of%20Swietokrzyskie
 * @author qbass
 * @title Cyborgs of Swietokrzyskie
 */
public class Game {
    
    private static String listrobots(){
        File dir = new File("saved");
        String[] chld = dir.list();
        if(chld.length < 1){
            System.out.println("There are no saved Robots. Create one");
        }else{
           for(int i = 0; i < chld.length; i++){
                String fileName = chld[i];
                System.out.println(i+". "+fileName);
            } 
        }
        System.out.println("999. Create new Robot");
        System.out.print("Your choice: ");
        Scanner choice = new Scanner(System.in);
        int ch = choice.nextInt();
        if (ch == 999) return "new";
        if (ch > chld.length) return null;
        
        else return chld[ch];
        
    }
    
    public static void battle(Robots att, Robots off){
        if(off.healthc()>att.attackc()){
            off.healthch(-att.attackc());
            System.out.println(att.namec()+" attacks "+off.namec()+" for "+att.attackc()+" points!");
        }else{
            off.losebattle();
            System.out.println(att.namec()+": You have killed "+off.namec());
            att.winbattle(off.getlvl());
        }
    }
    
    public static void main(String args[]){// throws FileNotFoundException{
       String name = listrobots();
       if (name != "new"){            
            Robots player1 = new Robots(name,0,false);
            player1.printstat();
       }else{
           Scanner newname = new Scanner(System.in);
           System.out.print("Choose name for your Robot: ");
           String newn = newname.next();
           Robots player1 = new Robots(newn,0,false);
           player1.printstat();
       }
       Network net = new Network(10,0);
        
        //test
        /*Robots player1 = new Robots("PL",0);
        Robots player2 = new Robots("CPU",1);
        battle(player2,player1);
        player2.printstat();*/
    }
}
