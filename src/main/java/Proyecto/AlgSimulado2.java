package Proyecto;

import javax.swing.*;
import static org.math.array.DoubleArray.*;
import org.math.plot.*;
import static java.lang.Math.*;

/**
 *
 * @author JUAN.PINTOB
 */
public class AlgSimulado2 extends JFrame
{
    static double temperatura = 100, DeltaX = 0.9;
    
    static public double[][] funcion (double [] x, double [] y)
    {
        double [][] z = new double[y.length][x.length];
        
        for (int i = 0; i < x.length; i++) 
        {
            for (int j = 0; j < y.length; j++) 
            {
                z[j][i] =  -pow(x[i],2) + pow(y[i],2) + 2;
            }
        }
        return z;
    }
    
    static double funcionDato (double x, double y)
    {
        return -pow(x,2) + pow(y,2) + 2;
    }
    
    public double perturbacion (double x)
    {
        if (x < 0) 
        {
            return x+0.2;
        }
        else
        {
            return x-0.2;
        }
    }
    
    public void plot3D (Plot3DPanel grafica3D)
    {  
        double Arreglo_X [] = increment(-5.0, 0.1, 2.0);
        double Arreglo_Y[] = increment(-5.0, 0.05, 2.0);
        double Arreglo_Z [][] = funcion(Arreglo_X,Arreglo_Y);
        grafica3D.addGridPlot("-X^2 + Y^2", Arreglo_X, Arreglo_Y, Arreglo_Z);
        JFrame frame = new JFrame("Grafica 1");
        frame.setSize(500,500);
        frame.setContentPane(grafica3D);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        AlgSimulado2 n = new AlgSimulado2();
        Plot3DPanel grafica3D = new Plot3DPanel();
        n.plot3D(grafica3D);
        double Z = Math.random()-2.4;  
        double ZPerturb = n.perturbacion(Z);
        double Probabilidad = 0;
        double Z2 = Math.random()-2.4;
        double ZPerturb2 = n.perturbacion(Z2);
        
        System.out.println("*************** DATOS INICIALES ***************");
        System.out.println("Z inicial = "+Z);
        System.out.println("F(Z inicial) = "+AlgSimulado2.funcionDato(Z,Z2));
        System.out.println("Z Perturbado = "+ZPerturb);
        System.out.println("F(Z Perturbado) = "+AlgSimulado2.funcionDato(ZPerturb,ZPerturb2));
        System.out.println("***********************************************");
        
        while (temperatura > 0 || Probabilidad > 0) 
        { 
            System.out.println("Z  = "+Z);
            System.out.println("Temperatura = "+temperatura);
            System.out.println("Probab = "+Probabilidad);
            ZPerturb = n.perturbacion(Z);
            if (AlgSimulado2.funcionDato(ZPerturb,ZPerturb2) > AlgSimulado2.funcionDato(Z,Z2)) 
            {
                Z = ZPerturb;
                System.out.println("<<< Pos Mayor 1 >>>");
            }
            else
            {
                Probabilidad = Math.exp((AlgSimulado2.funcionDato(ZPerturb,ZPerturb2) - AlgSimulado2.funcionDato(Z,Z2))/(0.00138054*temperatura));
                if (Z2 < Probabilidad) 
                {
                    Z = ZPerturb;
                    System.out.println("<<< Pos Mayor 2 >>>");
                }
                else
                {
                    System.out.println("MENOR");
                }
            }
            temperatura = temperatura - DeltaX;    
        }
    }
}
