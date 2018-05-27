package EnvioBits;

public class CRC {
    
    // polinomio do tipo x^4+x+1 ---> 16+2+1=19 ---> 10011
    final static String polinomio = "10011";

    // retorna o xor de duas sequencias
    private static String xor(String s1, String s2) {
        int pos = 0;
        String x = "";
        while(pos < s1.length()) {
            if(s1.charAt(pos) == s2.charAt(pos)) x += "0";
            else x += "1";
            pos++;
        }
        return x;
    }
    
    // calcula a divisão polinomial binaria de duas sequencias e retorna o resto
    public static String getXor(String pol1, String pol2) {
        int pos1 = 0;
        String sub = "";
        while(pos1+pol2.length() < pol1.length()+1) {
            // eliminando zeros à esquerda
            while(pol1.charAt(pos1) == '0') pos1++;
            // calcula o xor da substring
            sub = xor(pol1.substring(pos1,pos1+pol2.length()),pol2);
            // copia o restante do dividendo
            for(int i=pos1+pol2.length(); i<pol1.length(); i++) sub += pol1.charAt(i);
            // obtem uma posicao inicial sem zeros na esquerda
            int p = 0;
            while(sub.charAt(p) == '0') p++;
            // copia o polinomio resto para o dividendo
            pol1 = sub.substring(p,sub.length());
            if(pol1.length() >= pol2.length())
                pos1 = 0;
            else pos1++;
        }
        return pol1;
    }
    
    // codigo CRC
    public static String getCodigo(String c) {
        int ascii = (int)c.charAt(0);
        // obtem o resto
        String bin = "";
        String crc = "";
        String resto = "";
        // obtem o binario
        for(int i=0; i<8; i++) {
            bin += ascii%2;
            ascii /= 2;
        }
        
        // copia a mensagem para obtenção do resto
        crc += bin.charAt(7);
        crc += bin.charAt(6);
        crc += bin.charAt(5);
        crc += bin.charAt(4);
        crc += bin.charAt(3);
        crc += bin.charAt(2);
        crc += bin.charAt(1);
        crc += bin.charAt(0);
        crc += "0";
        crc += "0";
        crc += "0";
        crc += "0";
        // copia a mensagem para retorno de CRC
        resto = getXor(crc,polinomio);
        crc = "";
        crc += bin.charAt(7);
        crc += bin.charAt(6);
        crc += bin.charAt(5);
        crc += bin.charAt(4);
        crc += bin.charAt(3);
        crc += bin.charAt(2);
        crc += bin.charAt(1);
        crc += bin.charAt(0);
        // copia o resto
        for(int i=0; i<4-resto.length(); i++)
            crc += "0";
        for(int i=0;i<resto.length(); i++)
            crc += resto.charAt(i);
        
        return crc;
    }
    
}