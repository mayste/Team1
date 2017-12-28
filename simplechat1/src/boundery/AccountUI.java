package boundery;
import java.util.Scanner;
import java.util.Vector;
import controller.AccountController;
import controller.CatalogController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import entity.Product;
import entity.User;


public class AccountUI extends Application //With This Class We Show the Account GUI
{
	public static Vector <Product> products = new Vector<Product>();
	public static ClientConsole myClient;
	
	public static void main( String args[] ) throws Exception
	{ 
		System.out.println("Please enter the server IP");
		Scanner scanner = new Scanner(System.in);
		String IP = scanner.next(); /* Enter Server IP */
		myClient = new ClientConsole(IP, 5555);
        launch(args);		
	} 
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		
		AccountController aFrame = new AccountController(); /* Create AccountController */				  
		aFrame.start(arg0);
	}
	
}
