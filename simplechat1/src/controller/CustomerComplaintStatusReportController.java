package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.StoreManagerUI;
import boundery.StoreUI;
import boundery.UserUI;
import entity.Message;
import entity.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/* For Store Manager */

public class CustomerComplaintStatusReportController implements Initializable {

	/**
	 * This Variable Help Me To Load The ComboBox Of Store's .
	 */
	private Store store;
	
	/**
	 * This Variable Helping To Transfer Message From the Client to the DB .
	 */
	private Message msg;
	
	/**
	 * Variabel's That Help Me To Show The Month In The BarChart . 
	 */
	private static String[] Month_Of_Quarter_One = {"January","Febuary","March"};
	private static String[] Month_Of_Quarter_Two = {"April","May","June"};
	private static String[] Month_Of_Quarter_Three = {"July","August","September"};
	private static String[] Month_Of_Quarter_Four = {"October","November","December"};
	
	/* -------------------------  For The Window Of First Store - Customer Complaint Report - For The Store Manager --------------------- */	
	
	 @FXML
	 private TextField txtStoreID;
	
	 @FXML
	 private TextField txtYear;

	    @FXML
	 private TextField txtNumberOfQuarter;  
	    
	 @FXML
	 private BarChart<String, Integer> Complaint_BarChart;
	 
	 @FXML
	 private CategoryAxis X_Month;

	 @FXML
	 private NumberAxis Y_Complaint;
	 
	 @FXML
	 private Button btnClose;
	 
	/* --------------------------------- Loading Store To the Text Fields --------------------------------------------------------------- */	  
	 
	/**	
	* In This Function We Load The Number ID Of Specific Store .
	* @param s
	*/
	public void loadStore(Store s) 					
	{ 
		this.store = s;
		this.txtStoreID.setText(String.valueOf(store.getStoreId()));
	}
	
	/* --------------------------------- Close the Customer Complaint Report Window ----------------------------------------------------- */			
	
	/**
	* In This Function I close The GUI Of Customer Complaint Report Of the Store That The Store Manager Watch .
	* @param event - When The Client Press On the Butten This Parameter Start To Work .
	* @throws Exception - If The FXML Not Work .
	*/
	public void closeCustomerComplaintStatusReportWindow(ActionEvent event) throws Exception    
	{ 
		StoreManagerUI.stores.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/StoreManagerReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Store Manager Report Form -----");
		primaryStage.show();										 
	}
	
	/* --------------------------------- Initialize The Satisfaction Report GUI --------------------------------------------------------- */
	
	/** 
	 * In This Function We Initialize The GUI Of Customer Complaint Report Of the Store That The Store Manager Watch .
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		int Year_Integer;
		int Month_Integer;
		String Month;
		String Year;
		String Full_Date_String;
		Date temp_Date_Quarter_Report;
		
		/* ---------------------------------------------------------------------------------------------------------------- */
		
		/* This Code Is For Defult Value - Its Mean If The Store Manager Not Press On The ComboBox Of Date .
		 * But On Our Code - We Will Not Get Into To This Code Because We Will See Error Prompt Instead To See Defult Value */
		
		if(StoreManagerReportController.Flag_Press_On_The_Date_ComboBox == false)
		{
			StoreManagerUI.Help_To_Transfer_Object_At_Complaint_Report.clear();
			StoreManagerUI.Help_To_Transfer_Object_At_Complaint_Report.add(StoreManagerReportController.temp_Store_Id);
			StoreManagerUI.Help_To_Transfer_Object_At_Complaint_Report.add(StoreManagerUI.Dates.get(0));
			temp_Date_Quarter_Report = (Date)StoreManagerUI.Help_To_Transfer_Object_At_Complaint_Report.get(1);
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
		}
		
		/* ---------------------------------------------------------------------------------------------------------------- */
		
		/* For This Code We Will Alaways Get In ! */
		
		else 
		{
			temp_Date_Quarter_Report = (Date)StoreManagerUI.Help_To_Transfer_Object_At_Order_Report.get(1); /* The Date */
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
		}
		
		this.txtYear.setText(String.valueOf(Year_Integer)); 					/* Set The Year */
		
		if(Month_Integer == 1 || Month_Integer == 2 || Month_Integer == 3)      /* Set The Month */
		{
			this.txtNumberOfQuarter.setText(String.valueOf(1));
		}
		if(Month_Integer == 4 || Month_Integer == 5 || Month_Integer == 6)      /* Set The Month */
		{
			this.txtNumberOfQuarter.setText(String.valueOf(2));
		}
		if(Month_Integer == 7 || Month_Integer == 8 || Month_Integer == 9)      /* Set The Month */
		{
			this.txtNumberOfQuarter.setText(String.valueOf(3));
		}
		if(Month_Integer == 10 || Month_Integer == 11 || Month_Integer == 12)   /* Set The Month */
		{
			this.txtNumberOfQuarter.setText(String.valueOf(4));
		}
		
		ArrayList<Object> StoreID_And_Date_Of_Report = new ArrayList<Object>();
		StoreID_And_Date_Of_Report.add(StoreManagerUI.Help_To_Transfer_Object_At_Complaint_Report.get(0));    			 /* The Store Id */
		StoreID_And_Date_Of_Report.add(StoreManagerUI.Help_To_Transfer_Object_At_Complaint_Report.get(1));   			 /* The Date Of the Report */
		msg = new Message(StoreID_And_Date_Of_Report, "Store Manager - Take The Complaints Of Specific Store"); 		 /* I take All the Orders Of Specific Store , And After That I Take All the Complaint Of All The Order Of the Specific Store */
		UserUI.myClient.accept(msg);
		while(StoreManagerUI.complaints.size() == 0)
		{
			if(StoreManagerUI.complaints.size() == 0)
				break;
		}
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		Put_At_The_Chart_All_The_Complaints();
	}

/* --------------------------------- Initialize The Customer Complaint And The Month Of the Complaint At the Bar Chart -------------------- */	 			
	
	/**
	 * In This Function I Initialize The BarChart . 
	 */
	public void Put_At_The_Chart_All_The_Complaints()
	{
		int [] Count_In_Chart;
		ArrayList<String> Months_Of_Complaint = new ArrayList<String>();   								/* All the Product That We Order On Specific Store */
		ArrayList<String> Month_Of_Complaint_Without_Duplicate = new ArrayList<String>();
		Date date_Report = (Date)StoreManagerUI.Help_To_Transfer_Object_At_Complaint_Report.get(1);
		String String_Date_Report = String.valueOf(date_Report);
		String Month = String_Date_Report.substring(5 , 7);
		int Integer_Month = Integer.parseInt(Month);
		                       						  
		for(int i = 0 ; i < StoreManagerUI.complaints.size() ; i++)             						/* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			Months_Of_Complaint.add(StoreManagerUI.complaints.get(i).getComplaintMonth());
		}
		
		for(int i = 0 ; i < Months_Of_Complaint.size() ; i++)             								/* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			if((Month_Of_Complaint_Without_Duplicate.contains(Months_Of_Complaint.get(i))) == false) 	/* If Month_Of_Complaint_Without_Duplicate Not Contain */
				Month_Of_Complaint_Without_Duplicate.add(Months_Of_Complaint.get(i));
		}
		
		Count_In_Chart = new int[3];   /* 3 = Three Month In Each Quarter */
		ArrayList<XYChart.Series<String,Integer>> setChart = new ArrayList<XYChart.Series<String,Integer>>();
		
		if(Integer_Month == 1 || Integer_Month == 2 || Integer_Month == 3)
		{
			for(int i = 0 ; i < Month_Of_Quarter_One.length ; i++)
			{
				XYChart.Series<String,Integer> Chart = new XYChart.Series<String,Integer>();
				Chart.setName(Month_Of_Quarter_One[i]);
				setChart.add(Chart);
			}
		}
		else if(Integer_Month == 4 || Integer_Month == 5 || Integer_Month == 6)
		{
			for(int i = 0 ; i < Month_Of_Quarter_Two.length ; i++)
			{
				XYChart.Series<String,Integer> Chart = new XYChart.Series<String,Integer>();
				Chart.setName(Month_Of_Quarter_Two[i]);
				setChart.add(Chart);
			}
		}
		else if(Integer_Month == 7 || Integer_Month == 8 || Integer_Month == 9)
		{
			for(int i = 0 ; i < Month_Of_Quarter_Three.length ; i++)
			{
				XYChart.Series<String,Integer> Chart = new XYChart.Series<String,Integer>();
				Chart.setName(Month_Of_Quarter_Three[i]);
				setChart.add(Chart);
			}
		}
		else if(Integer_Month == 10 || Integer_Month == 11 || Integer_Month == 12)
		{
			for(int i = 0 ; i < Month_Of_Quarter_Four.length ; i++)
			{
				XYChart.Series<String,Integer> Chart = new XYChart.Series<String,Integer>();
				Chart.setName(Month_Of_Quarter_Four[i]);
				setChart.add(Chart);
			}
		}
		
		
		
		for(int i = 0 ; i < Months_Of_Complaint.size() ; i++)
		{
			for(int WithOut_Duplicate_Index = 0 ; WithOut_Duplicate_Index < Month_Of_Complaint_Without_Duplicate.size() ; WithOut_Duplicate_Index++)
			{
				if(Months_Of_Complaint.get(i).compareTo(Month_Of_Complaint_Without_Duplicate.get(WithOut_Duplicate_Index)) == 0) 
				{
					for(int SetChart_Index = 0 ; SetChart_Index < setChart.size() ; SetChart_Index++)
					{
						if(setChart.get(SetChart_Index).getName().compareTo(Month_Of_Complaint_Without_Duplicate.get(WithOut_Duplicate_Index)) == 0)
							setChart.get(SetChart_Index).getData().add(new XYChart.Data<String, Integer>(setChart.get(SetChart_Index).getName() , ++(Count_In_Chart[SetChart_Index])));
					}
				}
			}
		}
		
		Complaint_BarChart.getData().addAll(setChart);
	}
}
