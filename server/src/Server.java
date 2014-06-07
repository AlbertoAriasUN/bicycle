import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.*;
import java.util.Timer;
import java.util.Vector;
import java.util.regex.*;


public class Server {
	int serverPort;
	final static String idregex = "^[a-z0-9_]{5,12}$";
	final static String pwdregex = "^[a-z0-9_]{5,12}$";
	class ProcThread extends Thread {
		Socket client;
		ObjectInputStream in;
		ObjectOutputStream out;
		public ProcThread(Socket soc){
			client = soc;
			in = null;
			out = null;
			System.out.println("ctor");
		}
		public void run()
		{
			System.out.println("run");
			try {
				System.out.println("connect1");
				in = new ObjectInputStream(client.getInputStream());
				System.out.println("connect2");
				out = new ObjectOutputStream(client.getOutputStream());
				System.out.println("connect3");
				while (true){
					Object msg = in.readObject();
					System.out.println("connect4");
					System.out.print(msg);
					if(msg instanceof LoginData)
					{
						String sql = "";
						ResultSet rs = execSql(sql);
					}
					else if(msg instanceof RegisterData)
					{
						String sql = "";
						ResultSet rs = execSql(sql);
					}
					else if(msg instanceof DriverInfoCheck)
					{
						DriverInfo res = new DriverInfo();
						String sql = "";
						ResultSet rs = execSql(sql);
						send(res);
					}
					else if(msg instanceof HistoryOrderManagement)
					{
						String sql = "";
						ResultSet rs = execSql(sql);
						HistoryOrder res = new HistoryOrder();
						send(res);
					}
					else if(msg instanceof DriverCommentPost)
					{
						String sql = "";
						ResultSet rs = execSql(sql);
					}
					else if(msg instanceof OrderReleased)
					{
						String sql = "";
						ResultSet rs = execSql(sql);
					}
					else if(msg instanceof OrderModification)
					{
						String sql = "";
						ResultSet rs = execSql(sql);
					}
					else if(msg instanceof DriverSelection)
					{
						String sql = "";
						ResultSet rs = execSql(sql);
					}
					else if(msg instanceof OrderModification)
					{
						String sql = "";
						ResultSet rs = execSql(sql);
					}
					else
					{}
				}
			} catch(Exception se) {
				
			} finally {
				
			}
		}
		public ResultSet execSql(String sql)
		{
			java.sql.Connection conn;
			try {
				conn = JdbcSing.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		public void send(Object obj)
		{
			try {
				out.writeObject(obj);
				out.flush();
			} catch (IOException e) {
			
			}
		}
		
	}
	Server() throws IOException {
		ServerSocket server = new ServerSocket(10000);
		serverPort = 10000;
		
		while (true) {
			Socket socket = server.accept();
			System.out.println("one con");
			invoke(socket);
		}
	}
	public static void main(String[] args) throws IOException {
		new Server();
	}
	
	private void invoke(final Socket client) throws IOException {
		ProcThread pt = new ProcThread(client);
		pt.start();
	}
}