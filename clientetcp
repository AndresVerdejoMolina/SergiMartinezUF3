import java.net.*;
import java.io.*;

public class clientetcp {

   public static void main(String argv[]) {

      BufferedReader teclat = new BufferedReader(new InputStreamReader(System.in));//Creando BufferedReader para leer entrada por consola

      //Declarando variables vacias
      Socket socket;
      InetAddress address;
      String missatge="";
      String missatgeServidor="";

      try {   	  
    	 
         socket = new Socket("localhost",6018);//Asignadno al socket la IP y el puerto 
        
         DataInputStream in = new DataInputStream(socket.getInputStream());//Socket aparte del servidor para datos de entrada
         DataOutputStream out = new DataOutputStream(socket.getOutputStream());//Socket aparte del servidor para datos de salida
         
         out.writeUTF("HELLO");
         missatgeServidor = in.readUTF();
        
         do {
            	missatge = teclat.readLine();//leer entrada por consola 
            	out.writeUTF(missatge);//Enviar mensaje al servidor
        	
            	missatgeServidor = in.readUTF();//Leer entrada del servidor
            	System.out.println(missatgeServidor);//Imprimir mensaje recibido
        } while (!missatge.startsWith("fi"));//Mientras que el mensaje recibido no empieze por fi, se ejecuta infinitamente

        in.close();//Cerrar flujo de entrada
        out.close();//Cerrar flujo de salida
        socket.close();//Cerrar socket principal
      } 
      catch (Exception e) {//En el caso de que la coneccion falle
         System.err.println(e.getMessage());//Imprimir el error por consola
         System.exit(1);
      }
   }
}
