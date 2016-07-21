/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockupdv;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MichaelH
 */
public class matrixPos {
    
    public Double x[], y[], z[];
    
    public Double minX, maxX,
                  minY, maxY,
                  minZ, maxZ;
    
    public Double centerX, centerY, centerZ;
    
    public static List<String> names = new ArrayList<>();   
    public static List<String> matrixD = new ArrayList<>();
    
    public static Double getMax(Double[] inputArray){ 
        Double maxValue = inputArray[0]; 
        for(int i=1;i < inputArray.length;i++){ 
            if(inputArray[i] > maxValue){ 
                maxValue = inputArray[i]; 
            } 
        } 
      return maxValue; 
    }
    
    public static Double getMin(Double[] inputArray){ 
        Double minValue = inputArray[0]; 
        for(int i=1;i<inputArray.length;i++){ 
            if(inputArray[i] < minValue){ 
                minValue = inputArray[i]; 
            } 
        } 
    return minValue; 
  }
    
    
}
