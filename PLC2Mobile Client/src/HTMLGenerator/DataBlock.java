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
    
    public DataBlock(){
        vars = new ArrayList();
    }
    
    public void DataBlock(String name, ArrayList<Variable> vars){
        this.name = name;
        this.vars = vars;
    }
    
    public DataBlock(String name){
        this.name = name;
        vars = new ArrayList();
    }
    
    public void addVar(Variable var){
        this.vars.add(var);
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public ArrayList<Variable> getVars(){
        return vars;
    }
    
    public void delvar(int index){
        this.vars.remove(index); 
    }
}
