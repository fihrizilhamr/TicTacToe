package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicTacToeServer {
	private static TicTacToeProtocol game = new TicTacToeProtocol();
	private static PrintWriter[] clientOutput = new PrintWriter[2];
	private static String[] clientUserName = new String[2];
	private static int[] clientScore = new int [2];
	private static Boolean[] clientAgree = new Boolean [2];
	
	
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("Usage: java TicTacToeServer <port number>");
			System.exit(1);
		}
		int portNumber = Integer.parseInt(args[0]);
		new TicTacToeServer().startServer(portNumber);
	}
	private void startServer(int port) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server started on port " + port);
			for(int i = 0; i < 2; i++){
				Socket clientSocket = serverSocket.accept();
				new TicTacToeServerThread(clientSocket).start();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeResource(serverSocket);
		}
	}

	private void closeResource(ServerSocket socket) {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class TicTacToeServerThread extends Thread{
		private char id;
		Socket socket;
		BufferedReader in;
		PrintWriter out;
		private String userName;
		private boolean ready;
		
		public TicTacToeServerThread(Socket clientSocket) {
				// client = user, catching user
			try {
				socket = clientSocket;
				InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
				in = new BufferedReader(isReader);
				out = new PrintWriter(socket.getOutputStream());
				setClient();
				if(id == TicTacToeProtocol.CIRCLE)
					clientOutput[0] = out;
				else
					clientOutput[1] = out;
				} catch (Exception ex) {
			}
		}
			//preparing client, his/her id asn also score
			private void setClient() {
				if (clientOutput[0] == null) {
					this.id = TicTacToeProtocol.CIRCLE;
					clientScore[0] = 0;
				} else {
					this.id = TicTacToeProtocol.CROSS;
					clientScore[1] = 0;
				}
			}
			//ask for username
			private void setUserName() throws IOException {
				out.println("Enter your name :");
				out.flush();
				String user = in.readLine(); 
				this.userName = user;
				if(id == TicTacToeProtocol.CIRCLE)
					clientUserName[0] = user;
				else
					clientUserName[1] = user;
			}
			//welcome screen
			private void sendWelcomeScreen(){
				out.println("**** Welcome to TicTacToe, "+ userName +"! **** ");
				out.println("It's now session number " + TicTacToeProtocol.SESSION);
				out.println("|---|---|---|\n|   |   |   |\n|---|---|---|\n|   |   |   |\n|---|---|---|\n|   |   |   |\n|---|---|---|");
				out.println("Your symbol is: " + id);
				out.println("Move needs to be made by " + game.currentMove());
				out.println("To make a move, just type two numbers of row and column side by side starting from 0 and end on 2");
				out.println("Oh, if you wanna send a text to another player just type '//' and then your message");
				out.println("Ex. //<Your Message Here>");
				out.flush();
			}
			//welcome screen v2 as string
			private String sendWelcomeScreenTwo(String userName, char id){
				return "**** Welcome Again to TicTacToe, "+ userName +"! ****\nIt's now session number " + TicTacToeProtocol.SESSION+"\n|---|---|---|\n|   |   |   |\n|---|---|---|\n|   |   |   |\n|---|---|---|\n|   |   |   |\n|---|---|---|\nYour symbol is: " + id+"\nMove needs to be made by " + game.currentMove()+"\nYou know the rules, and so do I\nSo let's jump in to it!!";
			}
			//write to specific client
			private void writeToClient(PrintWriter response, String data) {
				response.println(data);
				response.flush();
			}
			//write to current client
			private void writeToClient(String data) {
				out.println(data);
				out.flush();
			}
			//preparing for new session
			private void newSession(char id) {
				if(id == TicTacToeProtocol.CIRCLE) {
					clientScore[0]++;
				}
				else {
					clientScore[1]++;
				}
				game.resetBoard();
				broadCastGame(clientUserName[0] + " : " + clientScore[0] + "\n"+clientUserName[1]+" : "+clientScore[1]);
				broadCastGame("Wanna Rematch?(y/n)");
			}

			@Override
			public void run() {
				String message = null;
				try {
					setUserName();
					sendWelcomeScreen();
					while ((message = in.readLine()) != null) {
						int row = -1, col = -1;
						try {
							//if agree for another match
							if(ready && message.equalsIgnoreCase("y")) {
								if(id == 'O')
									clientAgree[0] = true;
								else 
									clientAgree[1] = true;
								if (clientAgree[0] == true && clientAgree[1] == true) {
									writeToClient(clientOutput[0], sendWelcomeScreenTwo(clientUserName[0], TicTacToeProtocol.CIRCLE));
									writeToClient(clientOutput[1], sendWelcomeScreenTwo(clientUserName[1], TicTacToeProtocol.CROSS));
									clientAgree[0] = false;
									clientAgree[1] = false;
								}
								ready = false;
							}
							//if not
							else if(ready && message.equalsIgnoreCase("n")) {
								if(id == 'O') {
									clientAgree[0] = false;
								}
								else { 
									clientAgree[1] = false;
								}
								ready = false;
								if (id == TicTacToeProtocol.CIRCLE)
									writeToClient(clientOutput[1], clientUserName[0] + " doesn't want to play anymore!");
								else
									writeToClient(clientOutput[0], clientUserName[1] + " doesn't want to play anymore!");
								broadCastGame("The End!");
							}
							//if user want to send a message
							else if (message.startsWith("//")) {
								if (id == TicTacToeProtocol.CIRCLE)
									writeToClient(clientOutput[1], userName + ": " + message.substring(2));
								else
									writeToClient(clientOutput[0], userName + ": " + message.substring(2));
							}
							//else, make a move
							else {
								row = message.charAt(0)-'0';
								col = message.charAt(1)-'0';
								String result = game.makeMove(id, row, col);
								if (result.contains("~~")) {
									writeToClient(result);
								}
								else if (result.contains("$")) {
									broadCastGame(result);
									newSession(id);
									ready = true;
								}
								else {
									broadCastGame(result);
									if(TicTacToeProtocol.CIRCLE == game.currentMove()) {
										writeToClient(clientOutput[0],"Your Turn!");
										writeToClient(clientOutput[1],"Next move to be made by "+clientUserName[0]);
									}
									else {
										writeToClient(clientOutput[1],"Your Turn!");
										writeToClient(clientOutput[0],"Next move to be made by "+clientUserName[1]);
									}
								}
							}
						} catch (Exception ex) {
//							writeToClient("Invalid Input!");
						}
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			
			//broadcast to every player
			private void broadCastGame(String data) {
				for (PrintWriter client : clientOutput) {
					writeToClient(client, data);
				}
			}
	}

	
}
