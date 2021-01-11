import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private BufferedReader in;
	private PrintWriter out;
	private Socket client;
	
	// Overloading the constructor to initialize the variables with proper data.
	public ClientHandler(Socket client_Socket) throws IOException {
		this.client = client_Socket;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(), true);
	}
	
	public void run() {
		try {
			while(true) {
				String request = in.readLine();
				if(request.contains("name"))
					out.println("The random name is -> " + DataServer.get_Random());
				else
					out.println("Type 'tell me a name' to give you a random name, and 'stop' to terminate.");
			}
		}
		catch(IOException e) {
			System.out.println("Error in the ClientHandler IO.");
		}
		finally {
			try {
				out.close();
				in.close();
			}
			catch(IOException e) {
				System.out.println("There is an error during socket close.");
			}
		}
	}
}
