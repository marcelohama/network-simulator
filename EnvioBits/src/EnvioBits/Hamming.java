package EnvioBits;

public class Hamming {
    
    public Hamming() {
    }
    
    public static String getCodigo(String c) {
        int ascii = (int)c.charAt(0);
        String bin = "";
        String hamming = "";
        String binario = "";
        // obtem o binario do caracter
        for(int i=0; i<8; i++) {
            binario += ascii%2;
            ascii /= 2;
        }
        for(int i=7; i>=0; i--)
            bin += binario.charAt(i);
        int x1 = (bin.charAt(0)+48+bin.charAt(1)+48+bin.charAt(3)+48+bin.charAt(4)+48+bin.charAt(6)+48)%2;
        int x2 = (bin.charAt(0)+48+bin.charAt(2)+48+bin.charAt(3)+48+bin.charAt(5)+48+bin.charAt(6)+48)%2;
        int x3 = (bin.charAt(1)+48+bin.charAt(2)+48+bin.charAt(3)+48+bin.charAt(7)+48)%2;
        int x4 = (bin.charAt(4)+48+bin.charAt(5)+48+bin.charAt(6)+48+bin.charAt(7)+48)%2;
        
        hamming += x1;
        hamming += x2;
        hamming += bin.charAt(0);
        hamming += x3;
        hamming += bin.charAt(1);
        hamming += bin.charAt(2);
        hamming += bin.charAt(3);
        hamming += x4;
        hamming += bin.charAt(4);
        hamming += bin.charAt(5);
        hamming += bin.charAt(6);
        hamming += bin.charAt(7);
        
        return hamming;
    }
    
}
