/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTMLGenerator;

import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class DataBlock {
    String name;
    ArrayList<Variable> vars;
    
    public void DataBlock(){
        
    }
    
    public void DataBlock(String name, ArrayList<Variable> vars){
        this.name = name;
        this.vars = vars;
    }
    
    public void DataBlock(String name){
        this.name = name;
    }
    
    public void addVar(Variable var){
        this.vars.add(var);
    }
}
