package ClienteUdp;

public class MainCliente {

	
	public static void main (String args[]) throws Exception{
	
	ClienteUdp c= new ClienteUdp("127.0.0.1", 9990);
  
	c.iniciar();
	
}
}