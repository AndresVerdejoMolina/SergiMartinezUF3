import java.net.*;
import java.util.HashMap;
import java.io.*;

public class servidortcp {
	public static void main(String argv[]) {
		ServerSocket socketEscolta;//Creando Servidor socket para coneccion cliente/servidor
		boolean fi = false;
		String missatge = "";//Declarando variable vacia
		
		producto producto1=new producto("REF", 2);//Refresc
		producto producto2=new producto("PAT", 3);//Patates fregides
		producto producto3=new producto("CRO", 1);//Croissant
		producto producto4=new producto("BOC", 2);//Entrepa
		producto producto5=new producto("ENS", 4);//Ensaladilla
		producto producto6=new producto("CAF", 1);// Cafe
		
		HashMap<String, Integer> mapa_de_productos = new HashMap<String, Integer>();
		
		mapa_de_productos.put(producto1.ref, producto1.precio);
		mapa_de_productos.put(producto2.ref, producto2.precio);
		mapa_de_productos.put(producto3.ref, producto3.precio);
		mapa_de_productos.put(producto4.ref, producto4.precio);
		mapa_de_productos.put(producto5.ref, producto5.precio);
		mapa_de_productos.put(producto6.ref, producto6.precio);
		

		try {
			System.out.println("Esperando cliente.....");
			socketEscolta = new ServerSocket(6018);//Puerto por donde se comunicara con el cliente
			while(1>0) {//Bucle infinito
				Socket socketClient = socketEscolta.accept();//En el momento en el que el servidor esta esperando al cliente, crea una instancia especifica del socket para la comunicacion con el cliente
				DataInputStream in = new DataInputStream(socketClient.getInputStream());//Socket aparte del servidor para datos de entrada
				DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());//Socket aparte del servidor para datos de salida
				fi = false;

				while (!fi) {//Mientras que la variable fi devuelva false
					missatge = in.readUTF();//recibir mensaje que envia el cliente
										
					Integer precio = mapa_de_productos.get(missatge);
					if (missatge.startsWith("fi")) {//Si el mensaje recibido por el cliente empieza por fi
						System.out.println(missatge);//Imprimir mensaje por consola
						out.writeUTF(missatge);//Enviar mensaje al cliente
						in.close();//Cerrando flujo de entrada
						out.close();//Cerrando flujo de salida
						socketClient.close();//Cerramos el socket principal
						fi = true;//Asignar true a fi
						
					}
					else if (precio != null) {
						System.out.println(missatge + " " + precio);//Imprimir mensaje por consola
						out.writeUTF(missatge + " " + precio);
					}
					else if (missatge.equals("HELLO")) {
						System.out.println(missatge);//Imprimir mensaje por consola
						out.writeUTF(missatge);
					}
					else {
						out.writeUTF(missatge + " ERROR");
					}
				}
				fi = false;
			}
		} 
		catch (Exception e) {//En caso de que la coneccion falle
			System.err.println(e.getMessage());//Imprimir error por pantalla
			System.exit(1);
		}
	}
}
