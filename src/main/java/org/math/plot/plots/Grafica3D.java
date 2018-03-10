
package org.math.plot.plots;

import javax.swing.*;
import static org.math.array.DoubleArray.*;
import org.math.plot.*;
import static java.lang.Math.*;

/**
 *
 * @author JUAN.PINTOB
 */
public class Grafica3D 
{   
    public static void main(String[] args) 
    {
        double [] x = increment(-1.0, 0.1, 1.0); 
        double [] y = increment(-1.0, 0.05, 1.0);
        double [][] z = f(x,y);
        
        
        Plot3DPanel grafica3D = new Plot3DPanel();
        grafica3D.addGridPlot("a", x, y, z);
        JFrame frame = new JFrame("Grafica 1");
        frame.setSize(500,500);
        frame.setContentPane(grafica3D);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static double [][] f(double [] x,double []y)
    {
        double [][] z = new double[y.length][x.length];
        
        for (int i = 0; i < x.length; i++) 
        {
            for (int j = 0; j < y.length; j++) 
            {
                z[j][i] =  pow(x[i],2) + pow(y[i],2);
            }
        }
        return z;
    }

}
