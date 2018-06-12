package trabalhoed2;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class TrabalhoED2 {
    public static String[] selecao(String[] palavrasUnicas ){
        int menorPalavra = 0;
        int palavraDaVez = 0;
        while ( palavraDaVez < palavrasUnicas.length ) { 
            
            menorPalavra = procurarMenor(palavrasUnicas,menorPalavra,palavraDaVez);
            trocarComMenor(palavrasUnicas,menorPalavra,palavraDaVez); 
            
            menorPalavra=palavrasUnicas.length-1;
            palavraDaVez++;
            
        } 
        return palavrasUnicas;
    }
    
    public static String arrumarSinais(String texto) { 
        return texto.replaceAll("[ãâàáä]", "a")   
                    .replaceAll("[êèéë]", "e")   
	            .replaceAll("[îìíï]", "i")   
	            .replaceAll("[õôòóö]", "o")   
	            .replaceAll("[ûúùü]", "u")     
	            .replace('ç', 'c')   
	            .replace('ñ', 'n');
    }   
    
    public static String tirarPontuacao(String texto){
        return texto.replace(",","")
                    .replace(".","")
                    .replace(":","")
                    .replace(";","")
                    .replace("!","")
                    .replace("?","")
                    .replace("(","")
                    .replace("’","")
                    .replace("'","")
                    .replace("\"","")
                    .replace(")","")
                    .replace("\"","")
                    .replace("\'","")
                    .replace("[","")
                    .replace("]", "")
                    .replace("  ", "")
                    .replace("“","")
                    .replace("”","")
                    .replace("\\","");
    }
    
    public static void trocarComMenor(String [] palavrasUnicas, int menorPalavra, int palavraDaVez){
        String palavraAux = new String();
        palavraAux = palavrasUnicas[menorPalavra];
        palavrasUnicas[menorPalavra] = palavrasUnicas[palavraDaVez];
        palavrasUnicas[palavraDaVez] = palavraAux;               
    }
    
    public static int procurarMenor(String [] palavrasUnicas, int menorPalavra, int palavraDaVez){        
            for ( int i = palavraDaVez ; i < (palavrasUnicas.length-1) ; i ++ ){
                if ( palavrasUnicas[i].compareTo( palavrasUnicas[menorPalavra] ) < 0 ){
                    menorPalavra = i;                    
                }
            }
                      
            return menorPalavra;
    }
    
    public static void main(String[] args) { 
        
        File arquivoResposta = new File("G:\\TRABALHO\\Resposta - TrabalhoED - 1.txt");
        String texto = new String();    
        
        
        try (BufferedWriter escreverArquivoResposta = new BufferedWriter ( new FileWriter(arquivoResposta) );
                BufferedReader lerArquivoPergunta = new BufferedReader(new InputStreamReader(new FileInputStream("G:\\TRABALHO\\TextoED1.txt"), "ISO-8859-1"))){
                       
            arquivoResposta.createNewFile();
            texto = lerArquivoPergunta.readLine();        
            
            texto = texto.toLowerCase();
            int palavrasIguais = 0;
            
            //arrumando texto e separando as palavras em um array
            texto = tirarPontuacao(texto);
            String textoDividido[] = texto.split(" "); 
            
            // tirar palavras repetidas
            String palavrasUnicas[] = new String[textoDividido.length];
            int quantidade = 0;
            for (String textoDividido1 : textoDividido) {
                boolean existe = false;
                for (int j = 0; j < quantidade; j++) {
                    if (palavrasUnicas[ j ].equals(textoDividido1)) {    
                        existe = true;
                        palavrasIguais++;
                        break;
                    }
                }
                if (!existe) {
                    palavrasUnicas[ quantidade++ ] = textoDividido1;
                }              
            }            
            palavrasUnicas = Arrays.copyOf( palavrasUnicas , quantidade );
            
            //Separando Palavras com acento
            String armazenarTodasPalavras[] = new String[(palavrasUnicas.length * 2)]; 
            String palavrasUnicasComSinal[] = new String [ quantidade ];
            palavrasUnicasComSinal = Arrays.copyOf( palavrasUnicas , quantidade );
            
            //Tirando os acentos do array que sera analisado
            for( int i = 0 ; i < palavrasUnicasComSinal.length ; i++ ){
                palavrasUnicas [i] = arrumarSinais(palavrasUnicas[i]);             
            }
            
            //Armazenando palavras com e sem acento em um array separado
            int j = 0;
            for ( int i = 0 ; i < armazenarTodasPalavras.length ; i+=2, j++  ){                
                armazenarTodasPalavras[i] = palavrasUnicas[j];                   
            }
            j=0;
            for ( int i = 1 ; i < armazenarTodasPalavras.length ; i+=2, j++ ){
                armazenarTodasPalavras[i] = palavrasUnicasComSinal[j];                 
            }
            
            //Diferenciando palavras com e sem acento para organizacao e troca posterior
            j=0;           
            for ( int i = 0 ; i < armazenarTodasPalavras.length ; i+=2 , j++ ){               
                if ( ! palavrasUnicas[j].equals(palavrasUnicasComSinal[j]) ){
                    armazenarTodasPalavras[i] = armazenarTodasPalavras[i].concat("a");
                    palavrasUnicas[j] = palavrasUnicas[j].concat("a");
                }
            }           
                      
            //Chamada da funcao que ira arrumar em ordem alfabetica
            palavrasUnicas = selecao(palavrasUnicas);
            
            //Trocando palavras com acento por suas verdadeiras palavras
            for ( int i = 0 ; i < armazenarTodasPalavras.length ; i+=2){
                for ( int z = 0 ; z < palavrasUnicas.length ; z++){
                    j = i;
                    j++;
                    if((armazenarTodasPalavras[i].equals(palavrasUnicas[z]))&&(!(armazenarTodasPalavras[i].equals(armazenarTodasPalavras[j])))){                       
                        palavrasUnicas[z] = armazenarTodasPalavras[j];                       
                    }
                }
            }
           
            //Escrevendo lista no terminal
            for(int i = 0 ; i < palavrasUnicas.length ; i++){
                //System.out.println(i + " - "+lista.get(i));
                System.out.println(i + " - "+palavrasUnicas[i]);
            }
            
            //Escrevendo no arquivo de Resposta:
            escreverArquivoResposta.write("Texto com "+palavrasUnicas.length+" palavras únicas.");
            escreverArquivoResposta.newLine();
            escreverArquivoResposta.write(palavrasIguais+" palavras repetidas.");
            escreverArquivoResposta.newLine();
            escreverArquivoResposta.write(textoDividido.length+" palavras no total.");
            escreverArquivoResposta.newLine();
            escreverArquivoResposta.write("Lista de palavras (Em ordem alfabetica): ");
            escreverArquivoResposta.newLine();
            for(int i = 0; i < palavrasUnicas.length ; i++){               
                escreverArquivoResposta.write(i+" - "+palavrasUnicas[i]); 
                escreverArquivoResposta.newLine();
                escreverArquivoResposta.flush();
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(TrabalhoED2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}