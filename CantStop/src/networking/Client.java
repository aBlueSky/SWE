package networking;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
	
	private Socket socket;
	
	private GUI gui;
	
	private String userType;
	private String password;
	private String username;
	
	Client(String userType1, String userName1, String password1, GUI gui1){
		userType = userType1;
		username = userName1; 
		password = password1;
		gui = gui1;
	}

}
