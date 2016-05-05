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
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author MichaelH
 */
public class DistanceChooser {
    
    public ArrayList<String> pos = new ArrayList();
    public ArrayList<String> labels = new ArrayList();
    public ArrayList<String> xPos = new ArrayList();
    public ArrayList<String> yPos = new ArrayList();
    public ArrayList<String> zPos = new ArrayList();
    
    DistanceChooser(String file) throws IOException{
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
            // process the line.
            StringTokenizer stk = new StringTokenizer(line, "\t");
            labels.add(stk.nextToken());
            xPos.add(stk.nextToken());
            yPos.add(stk.nextToken());
            zPos.add(stk.nextToken());
            
            }
        }
        
    }
}
