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
        double intervals[] = {0,1,0.5,0.25,0.75};
        for(int s=0; s<col.size(); s++){
            for(int max = 1;max<12;max++) {
                for(int i = 0;i<5 && i<=max;i++) {
                    for(int j = 0;j<5 && (i+j)<=max;j++) {
                        int k = max - (i+j);
    //                    System.out.println(i + " " + j + " " + k);
                        if (k<5) {
                            r[i] = intervals[i];
                            g[i] = intervals[j];
                            b[i] = intervals[k];
    //                        availableColors.add(new Color(intervals[i], intervals[j], intervals[k]));
    //                        System.out.println("Color: " + intervals[i] + " " + intervals[j] + " " + intervals[k]);
                        }
                    }
                }
            }
        }
//        r = new Double[col.size()];
//        g = new Double[col.size()];
//        b = new Double[col.size()];
//        for(int i = 3; i<col.size(); i++){
//            r[0] = 1.0;//red
//            g[0] = 0.0;
//            b[0] = 0.0;
//            r[1] = 0.0;//green
//            g[1] = 1.0;
//            b[1] = 0.0;
//            r[2] = 0.0;//blue
//            g[2] = 0.0;
//            b[2] = 1.0;
//        
////        for(int i = 3; i<col.size(); i++){
//            r[i] = Math.random();
//            g[i] = Math.random();
//            b[i] = Math.random();
//        }
        
        
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
