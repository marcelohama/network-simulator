package EnvioBits;

public class CheckSum {

    public CheckSum() {
    }
    
    public static String getCodigo(String c) {
        int ascii = (int)c.charAt(0);
        String bin = "";
        String sum = "";
        // obtem o binario do caracter
        for(int i=0; i<8; i++) {
            bin += ascii%2;
            ascii /= 2;
        }
        // bits de informacao
        sum += bin.charAt(7);
        sum += bin.charAt(6);
        sum += bin.charAt(5);
        sum += bin.charAt(4);
        sum += bin.charAt(3);
        sum += bin.charAt(2);
        sum += bin.charAt(1);
        sum += bin.charAt(0);
        // soma invertida
        int vaiUm = 0;
        int x1 = (bin.charAt(0)-48+bin.charAt(4)-48)%2;
        vaiUm = (bin.charAt(0)-48+bin.charAt(4)-48)/2;
        int x2 = (bin.charAt(1)-48+bin.charAt(5)-48)%2+vaiUm;
        vaiUm = (bin.charAt(1)-48+bin.charAt(5)-48+vaiUm)/2;
        int x3 = (bin.charAt(2)-48+bin.charAt(6)-48)%2+vaiUm;
        vaiUm = (bin.charAt(2)-48+bin.charAt(6)-48+vaiUm)/2;
        int x4 = (bin.charAt(3)-48+bin.charAt(7)-48)%2+vaiUm;
        
        if(x4 == 1) sum +=  String.valueOf(0);
        else sum +=  String.valueOf(1);
        if(x3 == 1) sum +=  String.valueOf(0);
        else sum +=  String.valueOf(1);
        if(x2 == 1) sum +=  String.valueOf(0);
        else sum +=  String.valueOf(1);
        if(x1 == 1) sum +=  String.valueOf(0);
        else sum +=  String.valueOf(1);
        return sum;
    }

}