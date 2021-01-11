import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;	
import java.net.Socket;


public class Client {

	private static final int server_PORT = 9090;
	private static final String server_IP = "127.0.0.1";

	public static void main(String[] args) throws IOException {
		System.out.println("Here is the client.");
		Socket socket = new Socket(server_IP, server_PORT);


		// Creating input for teh client side.
		BufferedReader client_in = new BufferedReader(new InputStreamReader(System.in));

		// Creating I/O to interact with the server.
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // True -> to flush the stream all the time.

		while(true) {
			String command = client_in.readLine();
			if(command.equals("stop"))
				break;
			out.println(command);
			String server_Response = in.readLine();
			System.out.println("The server response is : " + server_Response);
		}
		socket.close();
		System.exit(0);

	}
}
