package mockupdv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import mockupdv.Labeling;
import mockupdv.xyzPos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author santi
 */

public class xyzChooser extends xyzPos {
    
    public static List<String> names = new ArrayList<>();
    
    
    public xyzChooser(File file) throws Exception {
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        List<Double> xpos = new ArrayList<>();
        List<Double> ypos = new ArrayList<>();
        List<Double> zpos = new ArrayList<>();
        
        boolean first = true;
        int sizeOfMatrix = 0;
        double m[][] = null;
        int i = 0;
        while(true) {
            String line = br.readLine();
            if (line==null) break;
            if (first) {
                StringTokenizer st = new StringTokenizer(line," \t");
                st.nextToken();
                while(st.hasMoreTokens()) {
                    String t = st.nextToken();
                    sizeOfMatrix++;
                }
                m = new double[sizeOfMatrix][sizeOfMatrix];
                first = false;
            }

            StringTokenizer st = new StringTokenizer(line," \t");
            int j = 0;
            String id = st.nextToken();
            String xp = st.nextToken();
            String yp = st.nextToken();
            String zp = st.nextToken();
            double number = Double.parseDouble(xp);
            xpos.add(number);
            double number2 = Double.parseDouble(yp);
            ypos.add(number2);
            
            //if(st.hasMoreTokens()){
                
                double number3 = Double.parseDouble(zp);
                zpos.add(number3);
  //              positions.zPosition(zpos);
            
            //}
            names.add(id);
//            while(st.hasMoreTokens()) {
//                String t = st.nextToken();
//                m[i][j] = Double.parseDouble(t);
//                j++;
//            } 
            i++;
        }
        
        x = new Double[xpos.size()];
        y = new Double[ypos.size()];
        z = new Double[zpos.size()];
        for(i = 0;i<xpos.size();i++) {
            x[i] = xpos.get(i);
            y[i] = ypos.get(i);
            z[i] = zpos.get(i);
        }
        
    }
    
    /*
    public List getx(){
        return this.xpos;
    }
    
    public List gety(){
        return this.ypos;
    }
    
    public List getz(){
        return this.zpos;
    }
    */
}
