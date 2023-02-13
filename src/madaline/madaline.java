package madaline;

import java.util.Scanner;
import java.text.DecimalFormat;

public class madaline {

    double alpa = 0;
    double teta = 0;
    int epoch = 10000;
        

    public double hardlim(double x) {
        if (x >= teta) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        new madaline().prosesmadaline();
    }

    public void prosesmadaline() {
        
        Scanner input = new Scanner(System.in);
        
        System.out.print("a = ");
        alpa=input.nextDouble();
        System.out.print("θ= ");
        teta=input.nextDouble();
        
        double x1[] = {0, 1, 2, 2, 4, -2, -1, -2, -1, 1};
        double x2[] = {4, 4, 3, 2, 1, 4, 3, 2, 1, -2};
        double t[] = {1, 1, 1, 1, 1, -1, -1, -1, -1, -1};

        double w11 = 0;
        double w12 = 0;
        double w21 = 0;
        double w22 = 0;
        
        double w11s = 0;
        double w12s = 0;
        double w21s = 0;
        double w22s = 0;

        double b1 = 0;
        double b2 = 0;

        double z1[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        double z1_out[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        double z2[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        double z2_out[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        double v1 = 0.5;
        double v2 = 0.5;
        double b3 = 0.5;

        double y[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        double y_out[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        double x1_uji []={2,1,3,2,-1,1,-2,-3,2,3};
        double x2_uji []={2,3,1,1,5,-1,3,3,-3,-4};

        String kondisi []= {"","","","","","","","","",""};
        boolean tanda = true;
        DecimalFormat df = new DecimalFormat("#.###");

                System.out.println("                   Data Pelatihan              ");
        System.out.println("");
        System.out.println("|\tx1\t|\tx2\t|      Target\t|");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < x1.length; i++) {
            System.out.println("|\t"+x1[i]+"\t|\t"+x2[i]+"\t|\t"+t[i]+"\t|");
        }
        
        
        int iterasi = 0;
        while (iterasi < epoch && tanda) {
            System.out.print("");
            System.out.println("");
            System.out.println("epoch ke - " + (iterasi + 1));
            for (int i = 0; i < x1.length; i++) {
                z1[i] = b1 + (x1[i] * w11) + (x2[i] * w21);
                z2[i] = b2 + (x1[i] * w12) + (x2[i] * w22);

                z1_out[i] = hardlim(z1[i]);
                z2_out[i] = hardlim(z2[i]);

                y[i] = b3 + (z1_out[i] * v1) + (z2_out[i] * v2);
                y_out[i] = hardlim(y[i]);

                if (t[i] == y_out[i]) {
                } else {
                    if (t[i] == 1) {
                        if (Math.abs(z1[i])<Math.abs(z2[i])) {
                            w11=w11+(alpa*(1-z1[i])*x1[i]);
                            w21=w21+(alpa*(1-z1[i])*x2[i]);
                            b1=b1+(alpa*(1-z1[i]));
                            
                        }else{
                            w12=w12+(alpa*(1-z2[i])*x1[i]);
                            w22=w22+(alpa*(1-z2[i])*x2[i]);
                            b2=b2+(alpa*(1-z2[i]));
                        }
                    }else{
                        if (z1[i]>=0) {
                            w11=w11+(alpa*(-1-z1[i])*x1[i]);
                            w21=w21+(alpa*(-1-z1[i])*x2[i]);
                            b1=b1+(alpa*(-1-z1[i]));
                        }
                        
                        if (z2[i]>=0) {
                            w12=w12+(alpa*(-1-z2[i])*x1[i]);
                            w22=w22+(alpa*(-1-z2[i])*x2[i]);
                            b2=b2+(alpa*(-1-z2[i]));
                        }
                    }
                }
                if (w11==w11s && w12==w12s && w21==w21s && w22==w22s) {
                    kondisi[i]="Benar";
                }else{
                    kondisi[i]="Salah";
                }
                System.out.println("w11 = "+df.format(w11)+"\tw12 = "+df.format(w12)+"\tw21 = "+df.format(w21)+"\tw22 = "+df.format(w22)+"Kondisi = " + kondisi[i]);
                
                w11s=w11;
                w12s=w12;
                w21s=w21;
                w22s=w22;
            }
            System.out.println("");
            
            if(kondisi[0].equals("Benar")&&kondisi[1].equals("Benar")&&kondisi[2].equals("Benar")&&kondisi[3].equals("Benar")&&kondisi[4].equals("Benar")&&kondisi[5].equals("Benar")&&kondisi[6].equals("Benar")&&kondisi[7].equals("Benar")&&kondisi[8].equals("Benar")&&kondisi[9].equals("Benar")) {
                tanda = false;
            }
            iterasi++;
            
        }
        
        System.out.println("");
        System.out.println("            Uji Data Latih");
        
        for (int i = 0; i < x1.length; i++) {
            
            z1[i] = b1 + (x1[i] * w11) + (x2[i] * w21);
            z2[i] = b2 + (x1[i] * w12) + (x2[i] * w22);

            z1_out[i] = hardlim(z1[i]);
            z2_out[i] = hardlim(z2[i]);

            y[i] = b3 + (z1_out[i] * v1) + (z2_out[i] * v2);
            y_out[i] = hardlim(y[i]);
            String uji []={"","","","","","","","","",""};
            if (y_out[i]==t[i]) {
                uji[i]="Benar";
            }else{
                uji[i]="Salah";
            }
            System.out.println("y = "+y_out[i]+"  \t target = "+t[i]+"\tKondisi = "+uji[i]);
            
            
        }
        
        System.out.println("");
        System.out.println("");
        System.out.println("                  Data Pengujian ");
        System.out.println("|\tx1\t|\tx2\t|\tt\t|");
        for (int i = 0; i < x1_uji.length; i++) {
            System.out.println("|\t"+x1_uji[i]+"\t|\t"+x2_uji[i]+"\t|\t"+t[i]+"\t|");}
        
        
        System.out.println("");
        System.out.println("            Uji Data Pengujian");
        
        for (int i = 0; i < x1.length; i++) {
            
            z1[i] = b1 + (x1_uji[i] * w11) + (x2_uji[i] * w21);
            z2[i] = b2 + (x1_uji[i] * w12) + (x2_uji[i] * w22);

            z1_out[i] = hardlim(z1[i]);
            z2_out[i] = hardlim(z2[i]);

            y[i] = b3 + (z1_out[i] * v1) + (z2_out[i] * v2);
            y_out[i] = hardlim(y[i]);
            String uji []={"","","","","","","","","",""};
            if (y_out[i]==t[i]) {
                uji[i]="Benar";
            }else{
                uji[i]="Salah";
            }
            System.out.println("y = "+y_out[i]+"  \t target = "+t[i]+"\tKondisi = "+uji[i]);
           
        }
    }
    
   
    
   
}
