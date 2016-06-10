/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockupdv;

import java.util.List;

/**
 *
 * @author MichaelH
 */
public class Labeling {
    public List<String> names = null;
    public List<String> dLabels = null; //discrete
    public List<Double> cLabels = null; //continous
    
    public Labeling(List<String> n, List<String> d, List<Double> c){
        names = n;
        dLabels = d;
        cLabels = c;
        
    }
    
}
