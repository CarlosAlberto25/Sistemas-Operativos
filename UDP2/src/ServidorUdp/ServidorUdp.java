package ServidorUdp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class ServidorUdp {
	
   private DatagramSocket socketUDP;	
   private DatagramPacket paqueteRecibido;	
   private DatagramPacket paqueteAEnviar;	
   private int puerto;
   private byte msg[] = new byte[1024];
   
   
   public ServidorUdp(int p) {
	   
	puerto= p;   
	   
   }

   public void iniciar() {
   
   try {
		
	  //Creamos el socket UDP del Servidor en el puerto asociado
	   socketUDP= new DatagramSocket(puerto);
	   System.out.println("-SERVIDOR UDP INICIADO-");
	   System.out.println("-Esperando Cliente-");
	   
	   
	   while(true) {
	   
	  paqueteRecibido= new DatagramPacket(new byte [1024], 1024);	   
	  //llega un datagrama
	  socketUDP.receive(paqueteRecibido);
	  String recibidos[]=(new String(paqueteRecibido.getData(),StandardCharsets.UTF_8)).split("");
	  System.out.println(new String(paqueteRecibido.getData(),StandardCharsets.UTF_8));
		
	  if (recibidos[0]!=null) {
	//preguntamos si la primera palabra es "sumar" 	  
      if(recibidos[0].equals("sumar")) {
    	  
    	//ConvertimosString a enteros
    	  int xy1=Integer.parseInt(recibidos[1]);
    	  //llamamos a la funcion que procesara los resultados
    	  
    	  
    	  
    	  
    	  //aqui se guarda lo que se va a enviar como respuesta al cliente
    	 System.out.println(suma(xy1));
    	 //guadamos los datos en esta cadena
    	 String mensajeRespuesta="La suma es"+ suma(xy1);
    	 msg=mensajeRespuesta.getBytes();
    	 
    	  
      
    	 
    	 
    	  
      }else {
    	  
    	String mensajeRespuesta= " Solicitud Invalida";
    	msg=mensajeRespuesta.getBytes();
    	  
      }
		  
	  }
		  
		System.out.println("Ha llegado una peticion\n");  
		System.out.println("Procedente de :"+paqueteRecibido.getAddress());
		System.out.println("En el puerto :"+paqueteRecibido.getPort());
		System.out.println("Sirviendo la peticion");
		
		
		//Se crea el datagrama que contendra al mensaje
		paqueteAEnviar= new DatagramPacket(msg, msg.length, paqueteRecibido.getAddress(), paqueteRecibido.getPort());
		
		//luego se envia el paquete al cliente
		socketUDP.send(paqueteAEnviar);
		
	   }
	  
	   }catch (Exception e) {
		   
		e.printStackTrace();   
		   
	   } finally {
		   
		   finalizar();
	   }
   
}
   public void finalizar() {
	   
	   
	   try {
		   socketUDP.close();
		   System.out.println("Conexion Finalizada!!");
	   }catch (Exception e) {
		   
		   
		   e.printStackTrace();
	   }
	   
   }
   
   int sumatoria=0;
   private int n;
   public int suma(int a) {
	   this.n=a;
	  for (int i=0; i<=n; i++) {
		  sumatoria=sumatoria+i;
		   
	  }
	
	return a;
	  
   }
}
   
