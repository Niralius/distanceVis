/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockupdv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author MichaelH
 */
public class Labeling {

    public List<String> dLabels = new ArrayList<>(); //discrete
    public List<Double> cLabels = new ArrayList<>(); //continous
    public boolean labelType;
    List<String> names = new ArrayList<>();
    
    public Labeling(File file) throws Exception{
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> labelSet = new ArrayList<>();
        while(true) {
            String line = br.readLine();
            if (line==null) break;
//            if (first) {
//                StringTokenizer st = new StringTokenizer(line," \t");
//                st.nextToken();
//                while(st.hasMoreTokens()) {
//                    String t = st.nextToken();
//                    sizeOfMatrix++;
//                }
//                m = new double[sizeOfMatrix][sizeOfMatrix];
//                first = false;
//            }

            StringTokenizer st = new StringTokenizer(line," \t");
            String id = st.nextToken();
            String label = st.nextToken();
            
            if (areLabelsContinuous(label)){
                double number = Double.parseDouble(label);
                cLabels.add(number); //continuous labels
                labelType = true; 
            } else {
                dLabels.add(label); // discrete labels
                labelType = false;
            }
 
            names.add(id);
            
        }
        
    }
    
        public static boolean areLabelsContinuous(String labels) { //determine type of labels
            if (!IsNumber.isNumber(labels)) {
                return false;
            }
        return true;
    }
    
}
