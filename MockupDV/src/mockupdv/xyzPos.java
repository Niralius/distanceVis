/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockupdv;

import java.io.File;
import java.util.List;

import mockupdv.xyzChooser;
/**
 *
 * @author MichaelH
 */
public class xyzPos {
    
    public Double x[], y[], z[];
    
    
    void xPosition(List<Double> xpos){
        x = new Double[xpos.size()];
        for(int i = 0; i<xpos.size(); i++ )
            x[i] = xpos.get(i);
    }
    
    void yPosition(List<Double> ypos){
        y = new Double[ypos.size()];
        for(int i = 0; i<ypos.size(); i++ )
            y[i] = ypos.get(i);
    }
    
    void zPosition(List<Double> zpos){
        z = new Double[zpos.size()];
        for(int i = 0; i<zpos.size(); i++ )
            z[i] = zpos.get(i);
    }
    
}
