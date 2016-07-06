/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockupdv;

import java.io.File;
import java.util.List;

import mockupdv.Labeling;
/**
 *
 * @author MichaelH
 */
public class Colors{
    
    public Double r[], g[], b[];
    
    void assignColors(List<String> col){
        r = new Double[col.size()];
        g = new Double[col.size()];
        b = new Double[col.size()];
            r[0] = 1.0;//red
            g[0] = 0.0;
            b[0] = 0.0;
            r[1] = 0.0;//green
            g[1] = 1.0;
            b[1] = 0.0;
            r[2] = 0.0;//blue
            g[2] = 0.0;
            b[2] = 1.0;
        
        for(int i = 3; i<col.size(); i++){
            r[i] = Math.random();
            g[i] = Math.random();
            b[i] = Math.random();
        }
        
        
    }
    
    public Double getR(int i){    
        return r[i];
    }
    public Double getG(int i){
        return g[i];
    }
    public Double getB(int i){
        return b[i];
    }
}
