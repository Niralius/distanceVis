package mockupdv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author santi
 */
public class DistanceChooser {
    
    public DistanceChooser(String file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("/Users/Michael/Documents/1.txt"));
        
        boolean first = true;
        int sizeOfMatrix = 0;
        List<String> ids = new ArrayList<>();
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
            ids.add(id);
            while(st.hasMoreTokens()) {
                String t = st.nextToken();
                m[i][j] = Double.parseDouble(t);
                j++;
            }
            i++;
        }

        System.out.println(ids);
        
    }
}



