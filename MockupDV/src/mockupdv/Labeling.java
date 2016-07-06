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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

import mockupdv.Colors;

/**
 *
 * @author MichaelH
 */
public class Labeling extends Colors{

    public List<String> dLabels = new ArrayList<>(); //discrete complete set
    public static List<String> discrete = new ArrayList<>(); //parsed list of labels
    public List<Double> cLabels = new ArrayList<>(); //continuous
    public List<String> names = new ArrayList<>(); //label names (e.g. record-43242)
    public boolean labelType;
    
    public static HashMap<String,Object> labelColors = new LinkedHashMap<>();
    
    Double maxC, minC;
    Colors labelsRGB = new Colors();
    
    public Labeling(File file) throws Exception{
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        //List<String> labelSet = new ArrayList<>();
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

            StringTokenizer st = new StringTokenizer(line,"\t");
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
                
        if(!labelType){  //getting the proper labels for Discrete Type
            discrete.add(dLabels.get(0));
            for(int i = 0; i<dLabels.size(); i++){
                if(!discrete.contains(dLabels.get(i))){
                    discrete.add(dLabels.get(i));
                }
            }
            for(int i = 0; i<discrete.size(); i++){
                labelsRGB.assignColors(discrete);
                labelColors.put(discrete.get(i),labelsRGB);
            }
        }
//        System.out.println(discrete);
//        System.out.println(discrete.size());
       
    }
    
    public static boolean areLabelsContinuous(String labels) { //determine type of labels
        if (!IsNumber.isNumber(labels)) {
            return false;
        }
    return true;
    }
    
}
