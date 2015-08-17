/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTMLGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class Util {
    
    String html="";
    Path file = FileSystems.getDefault().getPath("D:\\Trabalhos\\PF\\GIT\\PLC2Mobile\\PLC2Mobile Client\\src\\resources\\html.html");;
    
    public Util(ArrayList<DataBlock> dbs){
        int index=0;
        for(int i =0; i < dbs.size(); i++){
            for(int j=0; j<dbs.get(i).vars.size(); j++){
                html+="                        <tr>\n"
                    + "                            <td>\n"
                    + "                                :=\""+dbs.get(i).name+"\"."+dbs.get(i).vars.get(j).name+":\n"
                    + "                            </td>\n"
                    + "                            <td>\n"
                    + "                                VarId"+index+"\n"
                    + "                            </td>\n"
                    + "                        </tr>\n";
                index++;
            }
        }
        
        Charset charset = Charset.forName("ASCII");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                if(line.contains("$$")){
                    line = line.replace("$$", html);
                }
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        
    }
    
    public Util(){
        
        Charset charset = Charset.forName("ASCII");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                if(line.contains("$")){
                    line.replace("$", html);
                }
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
