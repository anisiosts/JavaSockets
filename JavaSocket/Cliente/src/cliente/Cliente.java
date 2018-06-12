package cliente;

import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) throws Exception {
        
        String frase; // frase digitada pelo usuário
        String fraseModificada; // frase modificada pelo servidor
        String servidor; // Nome do servidor

        System.out.println("Teste de Socket - Cliente");
        System.out.println("-------------------------\n");

        // Prepara a leitura da variável do teclado
        BufferedReader doUsuario = 
            new BufferedReader(new InputStreamReader(System.in));

        while (true) {
 
            // Inicia o socket do cliente com o computador especificado em servidor
            Socket socketCliente = new Socket("localhost", 6789);

            // Prepara para escrever no socket   
            DataOutputStream paraServidor = 
                new DataOutputStream(socketCliente.getOutputStream());
  
            // Prepara para ler do socket
            BufferedReader doServidor = 
                new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

            //Lendo Comando
            System.out.println("Digite o Comando:");
            frase = doUsuario.readLine();
            paraServidor.writeBytes(frase + "\r\n");
            
            //Lendo Resposta 
            frase = doServidor.readLine();
            while(!frase.equals("-1")){
                System.out.println(frase);
                frase = doServidor.readLine();
            }

        }
 
    }
    
}