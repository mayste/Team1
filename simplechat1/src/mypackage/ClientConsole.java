package mypackage;
/* This file contains material supporting section 3.7 of the textbook: */
/* "Object Oriented Software Engineering" and is issued under the open-source */
/* license found at www.lloseng.com */

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;
import boundery.AccountUI;
import boundery.CatalogUI;
import boundery.CompanyManagerUI;
import boundery.ComplaintUI;
import boundery.CustomerUI;
import boundery.DataCompanyManagerUI;
import boundery.OrderUI;
import boundery.StoreManagerUI;
import boundery.StoreUI;
import boundery.UserUI;
import client.ChatClient;
import common.ChatIF;
import controller.AccountController;
import controller.CancelOrderController;
import controller.CatalogController;
import controller.ComplaintController;
import controller.ComplaintHandleController;
import controller.CustomerController;
import controller.ExpertSurveyController;
import controller.OrderController;
import controller.ProfileController;
import controller.StoreManagerController;
import controller.StoreWorkerButtonController;
import controller.SurveyController;
import controller.SurveyInfoController;
import controller.SurveyResultController;
import controller.UserController;
import entity.Account;
import entity.Complaint;
import entity.Message;
import entity.Order;
import entity.Product;
import entity.Store;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unittests.CancelOrderControllerJUnitTest;

/**
 * This class constructs the UI for a chat client.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ServerConsole 
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge  
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
public class ClientConsole implements ChatIF 
{
  //Class variables *************************************************
  
  /**
   * The default port to connect on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Instance variables **********************************************
  
  /**
   * The instance of the client that created this ConsoleChat.
   */
  ChatClient client; 
  
  
  //Constructors ****************************************************

  /**
   * Constructs an instance of the ClientConsole UI.
   *
   * @param host - The host to connect to.
   * @param port - The port to connect on.
   */
  public ClientConsole(String host, int port) 
  {
    try 
    {
      client = new ChatClient(host, port, this);
    } 
    catch(IOException exception) 
    {
      System.out.println("Error: Can't setup connection!"
                + " Terminating client.");
      System.exit(1);
    }
  }

  public ClientConsole(ActionEvent event , String host, int port)  /* This Constructor Help Me In The First Connection To The Client Console After I Connect to The Server */
  {
    try 
    {
      client= new ChatClient(host, port, this);
    } 
    catch(Exception exception) 
    {
    	Stage primaryStage = new Stage();
		((Node) event.getSource()).getScene().getWindow().hide(); 
		Parent root = null;
		try 
		{
			root = FXMLLoader.load(getClass().getResource("/controller/Wrong_Details_To_Connect_The_Client.fxml"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Error Massage");
		primaryStage.show();
    }
  }
  
  //Instance methods ************************************************
  
  /**
   * This method waits for input from the console.  Once it is 
   * received, it sends it to the client's message handler.
   */
  public void accept(Message msg) 
  {

    try
    {
    	client.handleMessageFromClientUI(msg);
    } 
    catch (Exception ex) 
    {
      System.out.println
        ("Unexpected error while reading from console!");
    }
  }

  
  /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the Console.
   *
   * @param message - The string to be displayed.
   */
  public void display(String message) 
  {
    System.out.println("> " + message);
  }
  
  
   /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the Screen In shape Of GUI.
   * or put some values on variables/arrayList that we take from the DB
   * @param message - The string to be displayed.
   */
  @SuppressWarnings("unchecked")
  public void displayUI(Object message) 
  {
	  
	  	if(((Message)message).getOption().compareTo("Test - Update - The Order Table , Product In Order Table , Account Table") == 0)
	    {
	  		CancelOrderControllerJUnitTest.Size_Of_Order_Table_For_Test.clear();
	  			
	  		for(int i = 0 ; i < 4 ; i++)
	  		{
	  			CancelOrderControllerJUnitTest.Size_Of_Order_Table_For_Test.add((Integer)((Message)message).getMsg());
	  		}  
	    }
	  
	    if(((Message)message).getOption().compareTo("Test - Give - Me The Size Of Order_Table") == 0)
	    {
	    	CancelOrderControllerJUnitTest.Size_Of_Order_Table_For_Test.clear();
	    	
	    	for(int i = 0 ; i < 1 ; i++)
  			{
  				CancelOrderControllerJUnitTest.Size_Of_Order_Table_For_Test.add((Integer)((Message)message).getMsg());
  			}
	    	
	    }
	  
	    if(((Message)message).getOption().compareTo("Test - Bring Me The Order That I Add To Table - For Test") == 0)
	    {
	    	CancelOrderControllerJUnitTest.Orders_For_Test.clear();
	    	
	    	ArrayList<Order> Temp_Order_For_Test = new ArrayList<Order>();
	    	Temp_Order_For_Test = ((ArrayList<Order>)((Message)message).getMsg());
	    	
	    	for(int i = 0 ; i < Temp_Order_For_Test.size() ; i++)
  			{
  				CancelOrderControllerJUnitTest.Orders_For_Test.add(Temp_Order_For_Test.get(i));
  			}
	    }
	    
	  	if(((Message)message).getOption().compareTo("succed!") ==0) 
	  	{
	  		SurveyResultController.errorMsg = "succed!";
	  	}
	  
	  	if(((Message)message).getOption().compareTo("Error your date not between start and end date of the survey") ==0) 
	  	{
	  		SurveyResultController.errorMsg = "Error your date not between start and end date of the survey";
	  	}
	  
	  	if(((Message)message).getOption().compareTo("The storeId is not correct") ==0) 
	  	{

	  		SurveyResultController.errorMsg = "The storeId is not correct";
	  	}
	  	
	  	if(((Message)message).getOption().compareTo("customer twice") ==0) 
	  	{

	  		SurveyResultController.errorMsg = "customer twice";
	  	}
	  
	  	if(((Message)message).getOption().compareTo("date between error") ==0) 
	  	{
	  		SurveyController.errorMsg = "date between error";
	  	}
	  
	  	if(((Message)message).getOption().compareTo("error store have survey") ==0) 
	  	{
	  		SurveyController.errorMsg = "error store have survey";
	  	}
	  
	  	if(((Message)message).getOption().compareTo("succes survey") ==0) 
	  	{
	  		SurveyController.errorMsg = "succes survey";
	  	}
	  
	  	if(((Message)message).getOption().compareTo("get all customers have FULLPRICE in this store") ==0) 
	  	{
		  	int i=0;
	  	  	ArrayList<String> temp = new ArrayList<String>();
	  	  	temp = (ArrayList<String>)((Message)message).getMsg();
	  	  	
	  	  	AccountUI.customersId.clear();
	  	  	for(i=0;i<temp.size();i++)
	  	  	{
	  	  	AccountUI.customersId.add(temp.get(i));
	  	  	}
	  	  	AccountController.loadCustomersFlag=true;
	  	}

	  	if(((Message)message).getOption().compareTo("get all products in DB") ==0) 		/* Check that its update */
	  	{
	  	  	int i=0;
	  	  	ArrayList<Product> temp = new ArrayList<Product>();
	  	  	temp = (ArrayList<Product>)((Message)message).getMsg();
	  	  	
	  	  	CatalogUI.products.clear();
	  	  	for(i=0;i<temp.size();i++)
	  	  	{
	  	  	CatalogUI.products.add(temp.get(i));
	  	  	}
	  	  	CatalogController.waitFlag=1;
	    }
	    
	    if(((Message)message).getOption().compareTo("get all products in sale from DB") ==0)
	    {
	  	  	int i=0;
	  	  	ArrayList<Product> temp = new ArrayList<Product>();
	  	  	temp = (ArrayList<Product>)((Message)message).getMsg();
	  	  	CatalogUI.productsInSale.clear();

	  	  	for(i=0;i<temp.size();i++)
	  	  	{
	  	  	CatalogUI.productsInSale.add(temp.get(i));
	  	  	}
	  	  CatalogController.waitFlag=1;
	    }
	    
	    if(((Message)message).getOption().compareTo("get all products in order") ==0)
	    {
	  	  	int i=0;
	  	  	ArrayList<Product> temp = new ArrayList<Product>();
	  	  	temp = (ArrayList<Product>)((Message)message).getMsg();
	  	  	OrderUI.productInOrder.clear();

	  	  	for(i=0;i<temp.size();i++)
	  	  		OrderUI.productInOrder.add(temp.get(i));
	  	  CancelOrderController.getProducstFlag=true;
	    }
	    
	    if(((Message)message).getOption().compareTo("insert order to DB") == 0)
	    {
	    	if(((String)((Message)message).getMsg()).compareTo("No account") == 0)
	    		OrderController.accountExistFlag = false;
	    	OrderController.accountFlag = true;
	    }
	    
	    if(((Message)message).getOption().compareTo("Add User To Combo Box From DB") == 0)
	    {
		  	  int i=0;
			  ArrayList<User> temp = new ArrayList<User>();
			  temp = (ArrayList<User>)((Message)message).getMsg();
			  DataCompanyManagerUI.users.clear();

			  for(i=0;i<temp.size();i++)
		  	  {
				  DataCompanyManagerUI.users.add(temp.get(i));
		  	  }
	    }
	    
	    if(((Message)message).getOption().compareTo("Update customer account") == 0)
	    {
	    	if(((Message)message).getMsg() != null)
	    	{
	    		Account a = (Account)(((Message)message).getMsg());
			    	CustomerUI.account = new Account();
			    	CustomerUI.account.setAccountBalanceCard(a.getAccountBalanceCard());
			    	CustomerUI.account.setAccountCreditCardNum(a.getAccountCreditCardNum());
			    	CustomerUI.account.setAccountPaymentArrangement(a.getAccountPaymentArrangement());
			    	CustomerUI.account.setAccountSubscriptionEndDate(a.getAccountSubscriptionEndDate());
			    	CustomerUI.account.setAccountUserId(a.getAccountUserId());
	    			}
	    	else
	    		OrderController.accountExistFlag = false;
	    	OrderController.accountFlag = true;
	    }
	    
	    if(((Message)message).getOption().compareTo("get all stores from DB") == 0)
	    {
		  	  int i=0;
			  ArrayList<Store> temp = new ArrayList<Store>();
			  temp = (ArrayList<Store>)((Message)message).getMsg();
			  StoreUI.stores.clear();

			  for(i=0;i<temp.size();i++)
		  	  {
				  StoreUI.stores.add(temp.get(i));
		  	  }
			  CustomerController.cflag = 1;
	    }
	    
	    if(((Message)message).getOption().compareTo("get all the survey") == 0)
	    {
	    	UserUI.Id = (ArrayList<Integer>)(((Message)message).getMsg());
	    	SurveyResultController.flag = true;
	    }
	    
	    if(((Message)message).getOption().compareTo("get all the customerId") == 0)
	    {
	    	UserUI.CId = (ArrayList<Integer>)(((Message)message).getMsg());
	    	SurveyResultController.flag2 = true;
	    }
	    if(((Message)message).getOption().compareTo("get info survey") == 0)
	    {
	    	if(((ArrayList<Integer>)(((Message)message).getMsg())).get(0)==11)
	    	{
	  		  ExpertSurveyController.errorMsg = "11";
	    	}
	    	else {
	    	SurveyInfoController.ans = (ArrayList<Integer>)(((Message)message).getMsg());
	  		ExpertSurveyController.errorMsg = "10";
	    	SurveyInfoController.flag = true;
	             }
	    }
	    
	    if(((Message)message).getOption().compareTo("add surveyConclusion") == 0)
	    {
	    	if(((ArrayList<Integer>)(((Message)message).getMsg())).get(0)==11)
	    	{
	  		  ExpertSurveyController.errorMsg = "11";
	    	}
	    	else {
	  		  ExpertSurveyController.errorMsg = "10";
	             }
	    }

	    else if(((Message)message).getOption().compareTo("Get all orders for this customer") == 0) //get all the orders to specific customer
	    {
	  	  	int i=0;
	  	  	ArrayList<Integer> temp = new ArrayList<Integer>();
	  	  	temp = (ArrayList<Integer>)((Message)message).getMsg();
	  	  	ComplaintUI.ordersNumbers.clear();

	  	  	for(i=0;i<temp.size();i++)	  	  	
	  	  		ComplaintUI.ordersNumbers.add(temp.get(i));
	  	  	
	  	  	ComplaintController.loadOrdersFlag=true; //finish to get all the orders to this customer
	    }
	    
	    else if(((Message)message).getOption().compareTo("Get all orders numbers for this customer can cancel") == 0) //get all the orders to specific customer that he can to cancel
	    {
	  	  	int i=0;
	  	  	ArrayList<Integer> temp = new ArrayList<Integer>();
	  	  	temp = (ArrayList<Integer>)((Message)message).getMsg();
	  	  	OrderUI.ordersNumbers.clear();

	  	  	for(i=0;i<temp.size();i++)	  	  	
	  	  		OrderUI.ordersNumbers.add(temp.get(i));
	  	  	
	  	  	CancelOrderController.getCancelOrdersFlag=true; //finish to get all the orders to this customer that he can to cancel
	    }
	    
	    else if(((Message)message).getOption().compareTo("Get all complaints numbers for this customer service worker") == 0) //get all the complaints to specific customer service worker
	    {
	  	  	int i=0;
	  	  	ArrayList<Integer> temp = new ArrayList<Integer>();
	  	  	temp = (ArrayList<Integer>)((Message)message).getMsg();
	  	  	ComplaintUI.complaintsNumbers.clear();

	  	  	for(i=0;i<temp.size();i++)	  	  	
	  	  		ComplaintUI.complaintsNumbers.add(temp.get(i));
	  	  	
	  	  	ComplaintHandleController.loadComplaintsFlag=true; //finish to get all the complaints to this customer service worker
	    }
	    
	    else if(((Message)message).getOption().compareTo("Get complaint details") == 0) //get all the details for this complaint
	    {
	  	  	ComplaintUI.complaint=new Complaint();
	  	  	ComplaintUI.complaint=(Complaint)((Message)message).getMsg(); //save the complaint from the DB with all the details at the ComplaintUI
	  	  	ComplaintHandleController.complaintFlag=true; //finish to get all the details for this complaint
	    }
	    
	    else if(((Message)message).getOption().compareTo("Get order details") == 0) //get all the details for this order
	    {
	    	OrderUI.order=new Order();
	    	OrderUI.order=(Order)((Message)message).getMsg(); //save the order from the DB with all the details at the OrderUI
	    	CancelOrderController.orderFlag=true; //finish to get all the details for this order
	    }
	    
	    else if(((Message)message).getOption().compareTo("Store manager want store number") == 0) //get the store number for this store manager
	    {
  	  		StoreManagerController.storeID=((Integer)((Message)message).getMsg()); //save the store number
  	  		StoreManagerController.flag=true; //finish to get the store number
	    }
	    
	    else if(((Message)message).getOption().compareTo("get store ID of specific store worker") == 0) //get the store number for this store manager
	    {
	    	StoreWorkerButtonController.storeId=((Integer)((Message)message).getMsg()); //save the store number
	    	OrderController.storeIdFlag=true; //finish to get the store number
	    }
	    
	    else if(((Message)message).getOption().compareTo("get all order of specific store with status APPROVED") == 0) //get the store number for this store manager
	    {
	    	int i=0;
	  	  	ArrayList<Integer> temp = new ArrayList<Integer>();
	  	  	temp = (ArrayList<Integer>)((Message)message).getMsg();
	  	  	OrderUI.ordersNumbers.clear();

	  	  	for(i=0;i<temp.size();i++)	  	  	
	  	  		OrderUI.ordersNumbers.add(temp.get(i));

	    	OrderController.ordersFlag = true;
	    }
			
	    else if(((Message)message).getOption().compareTo("Update complaint") == 0) //update complaint	   
	    {
	    	ComplaintUI.complaint=new Complaint();
  	  		ComplaintUI.complaint=(Complaint)((Message)message).getMsg(); //save the complaint from the DB with all the details at the ComplaintUI
  	  		ComplaintHandleController.complaintFlag=true; //finish to get all the details for this complaint
	   	 }
	    
		 else if(((Message)message).getOption().compareTo("Store Manager - Take The Orders Of Specific Store") == 0)
	     {
		  	  int i=0;
			  ArrayList<Order> temp_Order = new ArrayList<Order>();
			  temp_Order = (ArrayList<Order>)((Message)message).getMsg();
			  StoreManagerUI.orders.clear();

			  for(i=0;i<temp_Order.size();i++)
		  	  {
				  StoreManagerUI.orders.add(temp_Order.get(i));
		  	  }
	     } 
	    
	     else if(((Message)message).getOption().compareTo("Store Manager - Take The Complaints Of Specific Store") == 0)
	     {
		  	  int i=0;
			  ArrayList<Complaint> temp_Complaint = new ArrayList<Complaint>();
			  temp_Complaint = (ArrayList<Complaint>)((Message)message).getMsg();
			  StoreManagerUI.complaints.clear();

			  for(i=0;i<temp_Complaint.size();i++)
		  	  {
				  StoreManagerUI.complaints.add(temp_Complaint.get(i));
		  	  }
	    }
	    
	    else if(((Message)message).getOption().compareTo("Store Manager - Take the Revenue Of Specific Quarter Of Specific Store") == 0)
	    {
	    	  int i = 0;
			  ArrayList<Object> temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter = new ArrayList<Object>();
			  temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter = (ArrayList<Object>)((Message)message).getMsg();
			  StoreManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.clear();

			  for(i=0;i<temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter.size();i++)
		  	  {
				  StoreManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.add(temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter.get(i));
		  	  }
	    }
	    
	    else if(((Message)message).getOption().compareTo("Store Manager - Take The Date Of All the Report Of Specific Store") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Date> temp_Date_Of_Report = new ArrayList<Date>();
			  temp_Date_Of_Report = (ArrayList<Date>)((Message)message).getMsg();
			  StoreManagerUI.Dates.clear();

			  for(i=0;i<temp_Date_Of_Report.size();i++)
		  	  {
				  StoreManagerUI.Dates.add(temp_Date_Of_Report.get(i));
		  	  }
	    }
	    
	    else if(((Message)message).getOption().compareTo("Store Manager - Take The Surveys Of Specific Store In Specific Quarter") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Double> temp_Survey_Result = new ArrayList<Double>();
			  temp_Survey_Result = (ArrayList<Double>)((Message)message).getMsg();
			  StoreManagerUI.Average_Result_Of_Each_Qustions_In_surveys.clear();

			  for(i=0;i<temp_Survey_Result.size();i++)
		  	  {
				  StoreManagerUI.Average_Result_Of_Each_Qustions_In_surveys.add(temp_Survey_Result.get(i));
		  	  }
	    }
	    
	    else if(((Message)message).getOption().compareTo("Store Manager - Want To Store Number And Address Of The Store") == 0)
	    {
		  	  int i = 0;
			  ArrayList<String> temp_Store = new ArrayList<String>();
			  temp_Store = (ArrayList<String>)((Message)message).getMsg();
			  StoreManagerUI.stores.clear();

			  for(i = 0 ; i < temp_Store.size() ; i++)
		  	  {
				  StoreManagerUI.stores.add(temp_Store.get(i));
		  	  }
	    }
	    
	    else if(((Message)message).getOption().compareTo("Company Manager - Add Store To Combo Box From DB") == 0)
	    {
		  	  int i=0;
			  ArrayList<Store> temp = new ArrayList<Store>();
			  temp = (ArrayList<Store>)((Message)message).getMsg();
			  CompanyManagerUI.stores_For_Company_Manager.clear();
			  CompanyManagerUI.stores_For_Company_Manager_2.clear();

			  for(i=0;i<temp.size();i++)
		  	  {
				  CompanyManagerUI.stores_For_Company_Manager.add(temp.get(i));
				  CompanyManagerUI.stores_For_Company_Manager_2.add(temp.get(i));
		  	  }
	    }
	    
	    else if(((Message)message).getOption().compareTo("Company Manager - Take The Orders Of Specific Store") == 0)
	    {
		  	  int i=0;
			  ArrayList<Order> temp_Order = new ArrayList<Order>();
			  temp_Order = (ArrayList<Order>)((Message)message).getMsg();
			  CompanyManagerUI.orders_For_Company_Manager.clear();
			  CompanyManagerUI.orders_For_Company_Manager_2.clear();

			  for(i=0;i<temp_Order.size();i++)
		  	  {
				  CompanyManagerUI.orders_For_Company_Manager.add(temp_Order.get(i));
				  CompanyManagerUI.orders_For_Company_Manager_2.add(temp_Order.get(i));
		  	  }
	    } 
	    
	    else if(((Message)message).getOption().compareTo("Company Manager - Take The Complaints Of Specific Store") == 0)
	    {
		  	  int i=0;
			  ArrayList<Complaint> temp_Complaint = new ArrayList<Complaint>();
			  temp_Complaint = (ArrayList<Complaint>)((Message)message).getMsg();
			  CompanyManagerUI.complaints_For_Company_Manager.clear();
			  CompanyManagerUI.complaints_For_Company_Manager_2.clear();

			  for(i=0;i<temp_Complaint.size();i++)
		  	  {
				  CompanyManagerUI.complaints_For_Company_Manager.add(temp_Complaint.get(i));
				  CompanyManagerUI.complaints_For_Company_Manager_2.add(temp_Complaint.get(i));
		  	  }
	    }
	    
	    else if(((Message)message).getOption().compareTo("Company Manager - Take the Revenue Of Specific Quarter Of Specific Store") == 0)
	    {
	    	  int i = 0;
			  ArrayList<Object> temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter = new ArrayList<Object>();
			  temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter = (ArrayList<Object>)((Message)message).getMsg();
			  CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.clear();
			  CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager_2.clear();
			  for(i=0;i<temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter.size();i++)
		  	  {
				  CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.add(temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter.get(i));
				  CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager_2.add(temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter.get(i));
		  	  }
	    }
	    
	    else if(((Message)message).getOption().compareTo("Comapny Manager - Take The Date Of All the Report Of Specific Store") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Date> temp_Date_Of_Report = new ArrayList<Date>();
			  temp_Date_Of_Report = (ArrayList<Date>)((Message)message).getMsg();
			  CompanyManagerUI.Dates_For_Company_Manager.clear();
			  CompanyManagerUI.Dates_For_Company_Manager_2.clear();

			  for(i=0;i<temp_Date_Of_Report.size();i++)
		  	  {
				  CompanyManagerUI.Dates_For_Company_Manager.add(temp_Date_Of_Report.get(i));
				  CompanyManagerUI.Dates_For_Company_Manager_2.add(temp_Date_Of_Report.get(i));
		  	  }
	    }
	    
	    else if(((Message)message).getOption().compareTo("Company Manager - Take The Surveys Of Specific Store In Specific Quarter") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Double> temp_Survey_Result = new ArrayList<Double>();
			  temp_Survey_Result = (ArrayList<Double>)((Message)message).getMsg();
			  CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager.clear();
			  CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager_2.clear();
			  
			  for(i=0;i<temp_Survey_Result.size();i++)
		  	  {
				  CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager.add(temp_Survey_Result.get(i));
				  CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager_2.add(temp_Survey_Result.get(i));
		  	  }
	    }
	    
	    else if(((Message)message).getOption().compareTo("Company Manager - Compare Between Two Different Quarter") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Object> temp_Object_From_Comparing = new ArrayList<Object>();
			  temp_Object_From_Comparing = (ArrayList<Object>)((Message)message).getMsg();
			  CompanyManagerUI.Object_From_Comparing_For_Store_1.clear();
			  CompanyManagerUI.Object_From_Comparing_For_Store_2.clear();
			  
			  for(i = 0 ; i < temp_Object_From_Comparing.size() ; i++)
		  	  {
				  if(i % 2 == 0)
				  {
					  CompanyManagerUI.Object_From_Comparing_For_Store_1.add(temp_Object_From_Comparing.get(i));
				  }
				  else 
				  {
					  CompanyManagerUI.Object_From_Comparing_For_Store_2.add(temp_Object_From_Comparing.get(i));
				  }
		  	  }
	    }
	    
	    else if(((Message)message).getOption().compareTo("Customer - Want To Take His Order") == 0)
	    {
		  	  int i = 0;
		  	  Vector<Order> temp_Order_Of_Specific_Customer = new Vector<Order>();
			  temp_Order_Of_Specific_Customer = (Vector<Order>)((Message)message).getMsg();
			  CustomerUI.Order_Of_Specific_Customer.clear();
			  
			  for(i=0 ; i <temp_Order_Of_Specific_Customer.size() ; i++)
		  	  {
				  CustomerUI.Order_Of_Specific_Customer.add(temp_Order_Of_Specific_Customer.get(i));
		  	  }
	    } 
	    
	    else if(((Message)message).getOption().compareTo("Customer - Want To Take His Complaints") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Complaint> temp_Complaint_Of_Specific_Customer = new ArrayList<Complaint>();
			  temp_Complaint_Of_Specific_Customer = (ArrayList<Complaint>)((Message)message).getMsg();
			  CustomerUI.Complaint_Of_Specific_Customer.clear();
			  
			  for(i=0 ; i <temp_Complaint_Of_Specific_Customer.size() ; i++)
		  	  {
				  CustomerUI.Complaint_Of_Specific_Customer.add(temp_Complaint_Of_Specific_Customer.get(i));
		  	  }
	    } 
	    
	    else if(((Message)message).getOption().compareTo("Customer - Want To Take His Account Details") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Account> temp_Account_Of_Specific_Customer = new ArrayList<Account>();
			  temp_Account_Of_Specific_Customer = (ArrayList<Account>)((Message)message).getMsg();
			  CustomerUI.Account_Of_Specific_Customer.clear();
			  
			  for(i=0 ; i <temp_Account_Of_Specific_Customer.size() ; i++)
		  	  {
				  CustomerUI.Account_Of_Specific_Customer.add(temp_Account_Of_Specific_Customer.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Customer - Check If To The Customer There Have Account") == 0)
	    {
	    	  int Count_Account = (int)((Message)message).getMsg();
	    	  
		  	  if(Count_Account > 0)
		  	  {
		  		  CustomerController.Customer_Have_Account = true;
		  	  }
		  	  else 
		  	  {
		  		 CustomerController.Customer_Have_Account = false;
		  	  }
	    }
	 }
  
  
  	public void sendUser(Object message) 
  	{
		  if(((User)((Message)message).getMsg()).getId().equals("Does Not Exist")) //user is NOT exist
		  {
			  UserUI.user.setId("Does Not Exist");
		  }
		  else
		  {
			  UserUI.user.setId(((User)((Message)message).getMsg()).getId());
			  UserUI.user.setPassword(((User)((Message)message).getMsg()).getPassword());
			  UserUI.user.setPermission(((User)((Message)message).getMsg()).getPermission());
			  UserUI.user.setPhone(((User)((Message)message).getMsg()).getPhone());
			  UserUI.user.setStatus(((User)((Message)message).getMsg()).getStatus());
			  UserUI.user.setUserName(((User)((Message)message).getMsg()).getUserName());
		  }
		  UserController.flag = true;
	 }
  
  	/**
  	 * handle the option that we choose to add an account- put the msg if success
  	 * null the account variable
  	 * and put true on the flag that we can continue
  	 */
     public void addAccount(Object message)
     {
		  AccountUI.success=(String)((Message)message).getMsg();
		  AccountUI.account=null;
		  AccountController.flag = true;  
     }
  
   	/**
   	 * handle the option that we choose to add an complaint- put the msg if success
   	 * null the complaint variable
   	 * and put true on the flag that we can continue
   	 */
     public void addComplaint(Object message)
     {
    	 ComplaintUI.success=(String)((Message)message).getMsg();
    	 ComplaintUI.complaint=null;	  
		 ComplaintController.flag = true;  
     }
     
     public void close() throws IOException {
    	 
    	 client.closeConnection();
     }
}

