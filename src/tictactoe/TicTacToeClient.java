package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TicTacToeClient extends Thread{
	
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	
	// method for connecting to server
	public static void main(String[] args) throws IOException{
		 if (args.length != 2) {
	            System.err.println(
	                "Usage: java TicTacToeClient <host name> <port number>");
	            System.exit(1);
	        }
	    String hostName = args[0];
	    int portNumber = Integer.parseInt(args[1]);
	    new TicTacToeClient(hostName, portNumber);
    }
	TicTacToeClient(String hostName, int portNumber) {
		try {
			socket = new Socket(hostName, portNumber);
			InputStreamReader inputstream = new InputStreamReader(socket.getInputStream());
			in = new BufferedReader(inputstream);
			out = new PrintWriter(socket.getOutputStream());
			this.start();
			takeInput();
		} catch (IOException ex) {
			if(ex instanceof IOException)
			{	
				//if there is no server up with such port number
				System.out.println("There is currently no server with port number " + portNumber + " up!");
			}
			else
			{
				//unknown error
				ex.printStackTrace();
			}
		}
	}

	private void takeInput() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String move = null;
			while ((move = br.readLine()) != null) {
				sendDataToServer(move);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendDataToServer(String data) {
		out.println(data);
		out.flush();
	}

	@Override
	public void run() {
		String stream;
		try {
			while ((stream = in.readLine()) != null) {
				System.out.println(stream);// Server response
				//if its the end, system exit
				if (stream.contains("The End!")) {
					System.exit(0);
				}
			}
		} catch (Exception ex) {
			if(ex instanceof IOException)
			{
				//if server closed unexpectedly
				System.out.println("\nThe server is currently down!");
			}
			else
			{	
				//unknown error
				ex.printStackTrace();
			}
		}
	}

}