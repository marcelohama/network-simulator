package RecebeBits;

import javax.swing.JTextArea;

public class TratadorErro {
    
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
            // obtem uma posicao inicial sem zeros na esquerda (retorna se for tudo 0)
            int p = 0;
            while(sub.charAt(p) == '0') {
                if(p == sub.length()-1) return "0";
                p++;
            }
            // copia o polinomio resto para o dividendo
            pol1 = sub.substring(p,sub.length());
            if(pol1.length() >= pol2.length())
                pos1 = 0;
            else pos1++;
        }
        return pol1;
    }
    
    // soma binaria de 4 bits
    private static String getSum4Bits(String s) {
        String bin = "";
        String sum = "";
        int valor1 = 0;
        int valor2 = 0;
        // soma
        valor1 += (s.charAt(0)-48)*8;
        valor1 += (s.charAt(1)-48)*4;
        valor1 += (s.charAt(2)-48)*2;
        valor1 += (s.charAt(3)-48)*1;
        valor2 += (s.charAt(4)-48)*8;
        valor2 += (s.charAt(5)-48)*4;
        valor2 += (s.charAt(6)-48)*2;
        valor2 += (s.charAt(7)-48)*1;
        valor1 += valor2;
        for(int i=0; i<4; i++) {
            bin += valor1%2;
            valor1 /= 2;
        }
        for(int i=3; i>=0; i--) {
            sum += bin.charAt(i);
        }
        return sum;
    }

    // verificacao do codigo hamming
    private static String getHamReceiver(String bin) {

        String hamming = "";

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
    
    // trata os tipos de erros erros
    public static String tratamentoErros(int modo, String bits, JTextArea msnRecebida) {
        // polinomio da CRC, x^4+x+1
        final String polinomio = "10011";
        // tratamento de erros para o checksum
        if(modo == 0) {
            // soma dos bits recebidos
            String soma = getSum4Bits(bits.substring(0, 8));
            // soma invertida recebida
            String inv = bits.substring(8,12);
            // verifica se a soma soma+somaInvertida resulta em 1111
            soma += inv;
            soma = getSum4Bits(soma);
            if(!soma.equals("1111")) {
                return "erro detectado, soma = "+soma;
            }
            else return "mensagem s/ erros";
        }
        // tratamento de erros para o CRC
        else if(modo == 1) {
            String resto = getXor(bits,polinomio);
            for(int i=0; i<resto.length(); i++) {
                if(resto.charAt(i) != '0')
                    return "erro detectado, resto = "+resto;
            }
            return "mensagem s/ erros";
        }
        // tratamento de erros para o Hamming
        else if(modo == 2) {
            String codigoRecebido = "";
            String hamming = "";
            codigoRecebido += bits.charAt(2);
            codigoRecebido += bits.charAt(4);
            codigoRecebido += bits.charAt(5);
            codigoRecebido += bits.charAt(6);
            codigoRecebido += bits.charAt(8);
            codigoRecebido += bits.charAt(9);
            codigoRecebido += bits.charAt(10);
            codigoRecebido += bits.charAt(11);
            hamming = getHamReceiver(codigoRecebido);

            int pos = 0;
            if(hamming.charAt(0) != bits.charAt(0)) pos += 1;
            if(hamming.charAt(1) != bits.charAt(1)) pos += 2;
            if(hamming.charAt(3) != bits.charAt(3)) pos += 4;
            if(hamming.charAt(7) != bits.charAt(7)) pos += 8;

            String corrigido = "";
            if(pos != 0) {
                if(hamming.charAt(pos-1) == '1') {
                    if(pos != 0) corrigido += hamming.substring(0,pos-1);
                    corrigido += '0';
                    if(pos != 11) corrigido += hamming.substring(pos,hamming.length());
                }
                else {
                    if(pos != 0) corrigido += hamming.substring(0,pos-1);
                    corrigido += '1';
                    if(pos != 11) corrigido += hamming.substring(pos,hamming.length());
                }
                msnRecebida.append(" >>> "+getChar(2,corrigido));
                return "erro na posicao " + (13-pos);
            }
            
            else return "mensagem s/ erros";
        }
        return "";
    }
    
    private static String getChar(int modo, String str) {
        int ascii = 0;
        String s = "";
        if(modo == 0 || modo == 1) {
            s = str.substring(0,8);
        }
        else {
            s += str.charAt(2);
            s += str.charAt(4);
            s += str.charAt(5);
            s += str.charAt(6);
            s += str.charAt(8);
            s += str.charAt(9);
            s += str.charAt(10);
            s += str.charAt(11);
        }
        for(int i=s.length()-1; i>=0; i--) {
            ascii += (s.charAt(i)-48)*Math.pow(2, 7-i);
        }
        s = ""+(char)ascii;
        return s;
    }
    
}
