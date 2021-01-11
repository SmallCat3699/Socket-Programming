import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class DataServer {

	// Variables Section.
	private static String[] names = {"First", "Second", "Third", "Fourth"};
	private static ExecutorService pool = Executors.newFixedThreadPool(4); // Creating a pool for threads.
	private static ArrayList <ClientHandler> clients = new ArrayList<>(); // List for all of the clients.
	private static final int PORT = 9090; // The server port ID.


	
	// The main function.
	public static void main(String[] args) throws IOException {
		// Waiting to get request for connection on this port.
		System.out.println("Here is the Server waiting...");
		ServerSocket listener = new ServerSocket(PORT); // Using PORT to enable the connection.
		
		while(true) {
			// i accept the connection once i get it.
			Socket client = listener.accept();
			
			
			// adding each client to an array list to keep track of it.
			ClientHandler current_Client = new ClientHandler(client);
			clients.add(current_Client);

			// Using multithreading to deal with all clients at the same time.
			// Will execute the (run) function in ClientHandler.
			pool.execute(current_Client);
		}
	}


	// The random generator, try later to change the seed if it's possible.
	public static String get_Random() {
		return names[(int)(Math.random() * names.length)];
	}
}
