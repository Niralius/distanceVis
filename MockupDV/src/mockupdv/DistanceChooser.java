/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockupdv;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author MichaelH
 */
public class DistanceChooser {
    
    public List<String> pos;
    public List<String> labels;
    public List<String> xPos;
    public List<String> yPos;
    
    DistanceChooser(String file) throws IOException{
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
            // process the line.
            
            }
        }
        
    }
}
