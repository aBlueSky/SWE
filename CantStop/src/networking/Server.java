package networking;
import java.io.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {
	//Instance variables
	ServerSocket s = null;
	Socket player = null;
	int portNumber;
	BufferedReader in = null;
	PrintWriter out = null;
	BufferedReader fileReader = null;
	static int numConnected=0;
	String loginInfo = "CantStop.txt";
	Scanner loginSc = null;

	//Initialize Server and ServerSocket
	public Server()	{
		portNumber=2043;
		try
		{
			File loginFile = new File(loginInfo);
			if(loginFile.createNewFile())
			{
				loginFile.createNewFile();
				System.out.println("File Created: "+loginFile.getName());//debug
				System.out.println("File Path: "+loginFile.getAbsolutePath());//debug
			}
			else
			{
				System.out.println("File already exists.");//debug
			}//debug
		}
		catch(IOException e)
		{
			System.err.println("Could not handle file: "+loginInfo+": " + e.getMessage());
		}
		try
		{
			s=new ServerSocket(portNumber);
			System.out.println("Server started on port: "+portNumber);//debug
			addNewUser("test,test");
			checkExistingUser("test,test");
		}//try
		catch(IOException e){
			System.err.println("Could not listen on port: "+portNumber+". " + e.getMessage());
			System.exit(-1);
		}//catch
	}//Method
	private void checkExistingUser(String requested)
	{
		boolean existing=false;
		try
		{
			System.out.println("Login File reader to be created: "+loginInfo);//debug
			fileReader = new BufferedReader(new FileReader(loginInfo));
			loginSc = new Scanner(fileReader);
			String line = loginSc.nextLine();
			System.out.println(line);//debug
			boolean scan = true;
			while(scan)
			{
				if(line.equals(requested))
				{
					existing=true;
				}
				System.out.println(line);//debug
				System.out.println("Matches requested: "+existing);//debug
				try
				{
					line=loginSc.nextLine();
				} catch (NoSuchElementException e) {
					System.err.println("Could not use Scanner. " + e.getMessage());
					scan=false;
				}
			}
		}
		catch(IOException e)
		{
			System.err.println("Could not use File Reader or Scanner" + e.getMessage());
			System.exit(-1);
		}
		finally
		{
			try 
			{
				fileReader.close();
			} catch (IOException e) 
			{
				System.err.println("Could not close File Reader" + e.getMessage());
			}
		}
		if(existing)
		{
			//allowed
			System.out.println("Allowed to connect.");//debug
		}
		else
		{
			//Rejected, reply to client with error from CS protocol.
			System.out.println("err, User does not exist.");//debug
		}
	}//Method - checkExistingUser
	private void addNewUser(String write)
	{
		try
		{
			System.out.println("Adding new User Login.");//debug
			FileWriter fileWriter = new FileWriter(loginInfo,true);
			System.out.println("Should write: " + write);
			fileWriter.write(write+System.getProperty("line.separator"));

			fileWriter.flush();
			fileWriter.close();
			System.out.println("Finished adding new User Login.");//debug
		}
		catch(IOException e)
		{
			System.err.println("Could not add user to records." + e.getMessage());
			System.exit(-1);
		}
	}
	public void initFileIO()
	{
		try
		{
			System.out.println("Login File reader to be created: "+loginInfo);//debug
			fileReader = new BufferedReader(new FileReader(loginInfo));
			loginSc = new Scanner(fileReader);
		}
		catch(IOException e)

		{
			System.err.println("Could not setup reader or writer." + e.getMessage());
			System.exit(-1);
		}
	}//method
	public boolean handleLogin()
	{
		boolean result = false;
		return result;
	}
	public Socket connect(Socket player)
	{
		try {
			player = s.accept( );
			in = new BufferedReader(new InputStreamReader(player.getInputStream()));
			out = new PrintWriter(player.getOutputStream(), true);
			/* Handle there user name and password 
			 * here before allowing them to connect fully.*/
			
			/*Done Handling Connection Attempt.*/
			out.println(++numConnected);
		}//try
		catch (IOException e) {
			System.err.println("Accept failed: " + e.getMessage());
			close(player);
		}//catch
		return player;
	}//method
	/*Safety Copy of connect(player)
	public Socket connect(Socket player)
	{
		try {
			player = s.accept( );
			in = new BufferedReader(new InputStreamReader(player.getInputStream()));
			out = new PrintWriter(player.getOutputStream(), true);
			/* Handle there user name and password 
			 * here before allowing them to connect fully.*/
			
			/*Done Handling Connection Attempt.*//*
			out.println(++numConnected);
		}//try
		catch (IOException e) {
			System.err.println("Accept failed: " + e.getMessage());
			close(player);
		}//catch
		return player;
	}//method
	End of Safety*/
	public BufferedReader connectReader(Socket socket)
	{
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}//try
		catch (IOException e) {
			System.err.println("Unable to read from or write to the client: "
					+ e.getMessage());
		}//Catch
		return in;
	}//Method
	public PrintWriter connectWriter(Socket socket)
	{
		try{
			out = new PrintWriter
					(socket.getOutputStream(), true /* autoFlush */);
		}//try
		catch (IOException e) {
			System.err.println("Unable to read from or write to the client: "
					+ e.getMessage());
		}//Catch
		return out;
	}//method
	//close the connections
	public void close(Socket socket)
	{
		try
		{
			socket.close();
			numConnected--;
		}
		catch(IOException e)
		{
			System.err.println("Unable to close the connection to the client: "
					+ e.getMessage());
		}

	}
	public void close()
	{
		try {
			out.close();
			in.close();
			s.close();
		}//try
		catch (IOException e) {
			System.err.println("Unable to close writer, reader, or socket: "
					+ e.getMessage());
		}//catch
	}//method
}//class
