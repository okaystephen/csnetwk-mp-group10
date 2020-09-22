import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	static int PORT_NUMBER;
	String FILE_PATH;
	boolean started = false;
	ServerSocket listener;
	int clientNum = 1;
	List<Client> list = new ArrayList<Client> ();
	public static void main(String[] args) {
		PORT_NUMBER = Integer.valueOf(args[0]);
		new Server().begin();
	}
	public void begin() {
		try {
			listener = new ServerSocket(PORT_NUMBER);
			started = true;
		} catch (BindException e1) {
			System.out.println("Port is in use.");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();					
		}
		try {
			while (started) {
			Socket socket = listener.
			Client c = new Client(soc
			new Thread(c).start();  
			list.add(c);
			System.out.println("Client client" + clien

		}
	} catch(IOException e) {
		e.printStackTrace();		

		try {

		} catch (IOException e) {
		e.printStackTrace();
		

									
		ss Client implements Runnable {
		ivate Socket socket;
		ivate ObjectInputStream in;

		ivate boolean bconnected;
		ivate int number = clientNum;
		ring str;

		his.socket = socket;

		blic void send(String str) {
		ry {

		out.flush();
		 catch(IOException e) 

		System.out.println("I/O exception");
		

		send file method*/
		ivate void sendFile(String path) 

		File file = new File(path);
		      FileInputStream fis = new FileInput

		      OutputStream os = socket.get
		      byte[] 
		      long fileLength = fi
		      String fileClient

		      long curren
		      while(current!=fileLength){ 

		          if(fileL
		              current += size;    
			        else{ 
				     
					        current = fileLength;

					    contents = new byte[size]; 
					    bis.read(contents, 0, size); 
					    os.write(contents);
					}   
					os.flush(); 
					(I
					emove(this);
						ut.println("I/O exception");
						

						d run() {
						
							
							ObjectInputStream(socket.getInputStream());
							 ObjectOutputStream(
							();
							d = true;
							nnected) {	 
						(String)
							 temp = str.split("\\s+");
						 
					ng MOF =
						.equals("file")) {
					E
				
					ng message = content(str);// extract content from str
				(
				
					
		

								}
							Client c = list.get(i);
						c.send("@client"+number+":"+message);
				}
				
				
						for(int i = 0; i < 
							if( i==number-1) {
								continue;
							}
							Client c = list.get(i);
							c.send("file");

							c.send(ex
						c.sendFile(FILE_PATH
					}

				System.out.println("clien
			} else if(method.equals("unicast")) {  //unicast method
				int target = 0;
				if(MOF.equals("message")) {
					String client = temp[temp.l
					target = getTarget(client);
					for(int i = 0; i < list.size(); i ++) {
						if(i == target ) {
					Client c = list.get(i);
							c.send("@client"+number+":"+message);
						}	
					}
				}

					String client = te
					target = getTarget(client);

						if(target == i) {
							Client c = list.get(i)

							c.send(Integer
							c.send(extractFilec.sendFil
					e(FILE_PATH);  

						
				}
					
				n
				

				int target = 0;
				if(MOF.equals("message")) {
			
		

								if(target == i || i==nu
									continue;
							}
							Client c = list.ge
							c.send("@client"+number+":"+message);
						}
					}
					if(MOF.equals("file")) {
						String client = temp[temp.length - 1

						for(int i = 0; i < list.size();
						if(target == i || i
						continue;
				
			
		

								c.send(extractFileName(temp[2])
								c.sendFile(FILE_PATH); 
						}
					}
					int blockClient=target+1;
				System.out.println("client"+nu
				else {
				ystem.out.print
				
					
				t
			ist.remo
				entNum--;
				tem.out.println("Client
				tch (IOException e1) {
				printStackTrace();
				tch (ClassNotFoundException e) {
				rintStackTrace();
				tch(NullPointerException e) {
			i
			
			System.out.pr
			finally {
			ry {
			if(out != null) {
				out.close();
					
			}
			 catch(IOException e) {
			
		

				if (in != null) {
					try {
					in.close();
				} catch (IOExceptio
					e.printStackTrace();
			}
				
				(socket != null)
				y {
				ocket.close();
				ocket = null; 

				.printStackTrace();
				
				
					
				h
			ate Stri
				ursor = 0;
				(sentence.charAt(cursor
				or++;
				
				tart = cursor++;
				
				(sentence.charAt(cursor) != '\"') {
			r
		
		nt end = curso
			urn sentence.substring(
			
			t Client target*/
			ic int getTarget(String client) { 
			urn (client.charAt(client.length
			
		
	*

	String[] a = str.split("/");
	int length = a.length;
		ring temp = a[length - 1];
			emp.charAt(0) == '"') {
		e
	}

	}
		
		
		
			
			
		
			
		
	

	
	

	
		
		
		
	

	
		

		
		
		
			

			
				
				
				
			
				
				
			
		
		
		
		
		
	
	
	
	
	
	
	
	

	
	
		
		
		
		
		
		
		
	

	
	
		
	

	
		
	

	
		
	

	
	
		
	
	
			

	
		
	