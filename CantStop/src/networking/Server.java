package networking;
import java.io.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Server.java
 * Server methods.
 * @author Kelsey LaPointe
 * @author Matthew Koval
 */
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
	/**
	 * create a server with the port number
	 */
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
		}//try
		catch(IOException e){
			System.err.println("Could not listen on port: "+portNumber+". " + e.getMessage());
			System.exit(-1);
		}//catch
	}//Method
	/**check to see if user already exists in the records
	 * @param requested
	 * @return
	 */
	private boolean checkExistingUser(String requested)
	{
		boolean existing=false;
		try
		{
			System.out.println("Login File reader to be created: "+loginInfo);//debug
			fileReader = new BufferedReader(new FileReader(loginInfo));
			loginSc = new Scanner(fileReader);
			String line=",";
			boolean scan = true;
			try {
				line = loginSc.nextLine();
			} catch (NoSuchElementException e) {
				System.err.println("No line found" + e.getMessage());
				scan=false;
			}
			System.out.println(line);//debug
			String store[];
			while(scan)
			{
				store = line.split(",");
				if(store[0].equals(requested))
				{
					existing=true;
				}
				System.out.println(store[0]);//debug
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
		return existing;
	}//Method - checkExistingUser
	/**check to see if user u has password matching p
	 * @param u
	 * @param p
	 * @return
	 */
	private boolean checkPasswordWithName(String u, String p)
	{
		boolean accept = false;
		try
		{
			System.out.println("Login File reader to be created: "+loginInfo);//debug
			fileReader = new BufferedReader(new FileReader(loginInfo));
			loginSc = new Scanner(fileReader);
			String line = loginSc.nextLine();
			System.out.println(line);//debug
			String store[];
			boolean scan = true;
			while(scan)
			{
				store = line.split(",");
				if(store[0].equals(u) && store[1].equals(p))
				{
					accept=true;
				}
				System.out.println(store[0]);//debug
				System.out.println("Matches requested: "+accept);//debug
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
		return accept;
	}
	/**add a new user with the information delimited by a ","
	 * @param write
	 */
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
	/**create the file io for loginInfo
	 * 
	 */
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
	/**handle the login attempt
	 * @return
	 */
	public boolean handleLogin()
	{
		boolean result = false;
		return result;
	}
	/**connect a player to the server
	 * @param player
	 * @return
	 */
	public Socket connect(Socket player)
	{
		boolean userNameCheck;
		boolean passwordCheck;
		boolean error;
		try {
			player = s.accept( );
			in = new BufferedReader(new InputStreamReader(player.getInputStream()));
			out = new PrintWriter(player.getOutputStream(), true);
			/* Handle there user name and password 
			 * here before allowing them to connect fully.*/
			boolean done = false;
			while(!done)
			{
				userNameCheck=false;
				passwordCheck=false;
				error = false;
				String line = in.readLine();
				String store[];
				String password;
				int passwordsAttempted=0;
				System.out.println("Line: "+line);//debug
				if(line.matches("[RN]{1}[,]{1}[\\w|\\s]*"));
				{
					
					store=line.split(",");
					if(store[0]=="R")
					{
						System.out.println(store[0]);//debug
						System.out.println(store[1]);//debug
						if(checkExistingUser(store[1]))
						{
							
							System.out.println(store[1]);//debug
							out.println("ack");
							userNameCheck=true;
							PW: while(!error)
							{
								password=in.readLine();
								if(checkPasswordWithName(store[1], password))
								{
									out.println("ack");
									passwordCheck=true;
									break PW;
								}//password matched
								else
								{
									out.println("err,Invalid Password");
									passwordsAttempted++;
									if(passwordsAttempted>2)
									{
										error=true;
									}
								}//invalid password
							}
						}//check to see if user name exists.
						else
						{
							System.out.println(store[0]);//debug
							out.println("err,Unknown User");
						}
					}//returning user.
					else
					{
						if(!checkExistingUser(store[1]))
						{
							out.println("ack");
							password = in.readLine();
							userNameCheck=true;
							addNewUser(store[1]+","+password);
							passwordCheck=true;
							out.println("ack");
							//get password then store both
						}//check to see if User Name is free;
						else
						{
							out.println("err,Duplicate User Name");
						}//err,Duplicate User Name
					}//new user.
				}//only excepts in the form of regex: "(r|d){1}[,]{1}(\\w|\\s)*"
				if(error)
				{
					//close connection
				}
				else if(passwordCheck && userNameCheck)
				{
					//Keep Connection
					done=true;
				}
			}//while not done
			/*Done Handling Connection Attempt.*/
		}//try
		catch (IOException e) {
			System.err.println("Accept failed: " + e.getMessage());
		}//catch
				out.println(++numConnected);
		return player;
	}//method
	/**connect a bufferedreader to the socket and return it
	 * @param socket
	 * @return
	 */
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
	/**connect a writer to the socket and return it
	 * @param socket
	 * @return
	 */
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
	/**close the socket
	 * @param socket
	 */
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
	/**
	 * close the server
	 */
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
