/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTMLGenerator;

/**
 *
 * @author Luis
 */
public class Variable {
    //Nome da variavel
    String name;
    //Varios tipos de dados para a variavel
    String txt;
    Boolean bool;
    Integer value;
    
    public Variable(){
        
    }
    
        public Variable(String name){
            this.name=name;
            txt=null;
            bool=null;
            value=null;
        }
    //Contrutor para vaiaveis tipo texto
    public Variable(String name, String txt){
        this.name = name;
        this.txt = txt;
        this.bool = null;
        this.value = null;
    }
    //Contrutor para vaiaveis tipo boolean
    public Variable(String name, Boolean bool){
        this.name = name;
        this.txt = null;
        this.bool = bool;
        this.value = null;
    }
    //Contrutor para vaiaveis tipo numerico
    public Variable(String name, int val){
        this.name = name;
        this.txt = null;
        this.bool = null;
        this.value = val;
    }
    
    public String getname(){
        return name;
    }
    
      public void setName(String name){
        this.name = name;
    }
    

    
}
