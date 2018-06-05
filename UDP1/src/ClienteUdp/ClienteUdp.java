package ClienteUdp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteUdp {

	private int puerto= 9990;
	private InetAddress servidorDestino;
	private Scanner lector=new Scanner(System.in);
	private DatagramSocket socketUdp;
	private DatagramPacket paqueteRecibido;
	private DatagramPacket paqueteAEnviar;
	private byte mensaje[]= new byte[1024];
	
	
	public ClienteUdp(String h, int p) throws UnknownHostException{
	servidorDestino= InetAddress.getByName(h);
	puerto=p;
		
		
		
	}
	
	public void iniciar() throws Exception{
		
	//Creamos un socket bajo UDP
		socketUdp= new DatagramSocket();
		
		
	System.out.println("Ingresa Un Numero");
	String peticion=new String(lector.nextLine())+" ";
	mensaje=peticion.getBytes();
	
	//Creamos un Datagrama, con el mensaje, la longitud, la direccion
	
	paqueteAEnviar= new DatagramPacket(mensaje,mensaje.length, servidorDestino,puerto);
			
	
	//Enviamos el datagrama
	
	socketUdp.send(paqueteAEnviar);
	paqueteRecibido= new DatagramPacket(new byte[1024], 1024);
	
	
	
	//Esperamos a que nos llegue respuesta desde el servidor
	socketUdp.receive(paqueteRecibido);
	
	//Ha llegado un datagrama, para ver los datos se utiliza getData()
	String recived = new String(paqueteRecibido.getData());
	System.out.println("\nDATOS DEl DATAGRAMA" + recived);
	
	System.out.println("La sumatoria es:" +recived);
	
	//Cerramos el Socket UDP
	finalizar();
	
	}
	public void finalizar() {
		
	try {
		
	socketUdp.close();
	System.out.println("Conexion Finalizada!!");
	}catch (Exception e) {
		
		
	e.printStackTrace();	
		
	
	}
			
	}	
}
