package EnvioBits;

public class GeradorErro {

    public GeradorErro() {
    }
    
    public static String getErro(String codigo) {
        int tamanho = codigo.length();
        int erroPos = (int)(Math.random()*10)%tamanho;
        String erro = "";
        for(int i=0; i<tamanho; i++) {
            if(i == erroPos) {
                if(codigo.charAt(erroPos) == '1')
                    erro += '0';
                else erro += '1';
            }
            else erro += codigo.charAt(i);
        }
        return erro;
    }
    
}
