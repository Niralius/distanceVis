package mockupdv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import mockupdv.Labeling;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MichaelH
 */

public class matrixChooser extends matrixPos{
    
    public matrixChooser(File file) throws Exception {
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        boolean first = true;
        int sizeOfMatrix = 0;
        double m[][] = null;
        int i = 0;
        while(true) {
            String line = br.readLine();
            if (line==null) break;
            if (first) {
                StringTokenizer st = new StringTokenizer(line,"\t");
                st.nextToken();
                while(st.hasMoreTokens()) {
                    String t = st.nextToken();
                    matrixD.add(t);
                    sizeOfMatrix++;
                }
                m = new double[sizeOfMatrix][sizeOfMatrix];
                first = false;
            }

            StringTokenizer st = new StringTokenizer(line,"\t");
            int j = 0;
            String id = st.nextToken();
            names.add(id);
            while(st.hasMoreTokens()) {
                String t = st.nextToken();
                m[i][j] = Double.parseDouble(t);
                j++;
            }
            i++;
        }

          System.out.println(names);
          System.out.println(names.size());

          System.out.println(matrixD);
          System.out.println(matrixD.size());

    }
    
}



