package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import boundery.StoreManagerUI;
import boundery.UserUI;
import entity.Message;
import entity.Order;
import entity.Product;
import entity.Product.ProductType;
import entity.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/* For Store Manager */

public class OrderReportController implements Initializable{

	/**
	 * This Variable Help Me To Load The ComboBox Of Store's .
	 */
	private Store store;
	
	/**
	 * This Variable Helping To Transfer Message From the Client to the DB .
	 */
	private Message msg;
	
	/**
	 * Variabel's That Help Me To Show The Product Type In The BarChart . 
	 */
	private static String[] Type_Of_Products_In_Order = {"BOUQUET","ARRANGEMENT","VASE","BRIDAL_BOUQUET","FLOWER_CROWN","SWEET_BOUQUET","WREATH_FLOWERS"};
	
/* -------------------------  For The Window Of Order Report - For Store Manager ------------------------------------- */		
	
	 @FXML
	 private TextField txtStoreID;                			/* Text Field Of the Store ID */
	
	 @FXML
	 private TextField txtYear;                   			/* Text Field Of The Year */

	 @FXML
	 private TextField txtNumberOfQuarter;        			/* Text Field Of The Number Of Quarter */
	
	 @FXML
	 private BarChart<String, Integer> ChartOrderReport;    /* Bar Chart */

	 @FXML
	 private CategoryAxis X_ProductType;            		/* Axis X of the - Bar Chart */

	 @FXML
	 private NumberAxis Y_OrderSpecificStore;       		/* Axis Y of the - Bar Chart */
	 
	 @FXML
	 private Button btnClose;                      			 /* Button For Close The Window */
	 
/* --------------------------------- Loading Store To the Text Fields ------------------------------------------------ */	 
	 
	/**	
	* In This Function We Load The Number ID Of Specific Store .
	* @param s
	*/
	public void loadStore(Store s) 						
	{ 
		this.store = s;
		this.txtStoreID.setText(String.valueOf(store.getStoreId()));
	}
	
/* --------------------------------- Close the Order Report Window --------------------------------------------------- */	 	
	
	/**
	* In This Function I close The GUI Of Order Report Of the Store That The Store Manager Watch .
	* @param event - When The Client Press On the Butten This Parameter Start To Work .
	* @throws Exception - If The FXML Not Work .
	*/
	public void closeOrderReportWindow(ActionEvent event) throws Exception   
	{ 
		StoreManagerUI.stores.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/StoreManagerReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);		
		primaryStage.show();										   
	}

/* --------------------------------- Initialize The Bar Chart Of the Order Report ------------------------------------ */	 	
	
	/** 
	 * In This Function We Initialize The GUI Of Order Report Of the First Store Of The Store Manager .
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
			StoreManagerUI.Help_To_Transfer_Object_At_Order_Report.clear();
			StoreManagerUI.Help_To_Transfer_Object_At_Order_Report.add(StoreManagerReportController.temp_Store_Id);
			StoreManagerUI.Help_To_Transfer_Object_At_Order_Report.add(StoreManagerUI.Dates.get(0));
			temp_Date_Quarter_Report = (Date)StoreManagerUI.Help_To_Transfer_Object_At_Order_Report.get(1);
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
		StoreID_And_Date_Of_Report.add(StoreManagerUI.Help_To_Transfer_Object_At_Order_Report.get(0)); /* The Store Id */
		StoreID_And_Date_Of_Report.add(StoreManagerUI.Help_To_Transfer_Object_At_Order_Report.get(1)); /* The Date Of the Report */
		
		msg = new Message(StoreID_And_Date_Of_Report, "Store Manager - Take The Orders Of Specific Store");
		UserUI.myClient.accept(msg);
		while(StoreManagerUI.orders.size() == 0)
		{
			if(StoreManagerUI.orders.size() == 0)
			{
				break;
			}
		}
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		Put_At_The_Chart_All_The_Orders();
	}
	
/* --------------------------------- Initialize The Order And the Product Type At the Bar Chart ---------------------- */	 		
	
	public void Put_At_The_Chart_All_The_Orders()
	{
		int [] Count_In_Chart;
		Product.ProductType product_Type;
		ArrayList<Product.ProductType> productType_Of_Specific_Order_Of_Specific_Store = new ArrayList<Product.ProductType>();   /* All the Product That We Order On Specific Store */
		ArrayList<Order> orders = new ArrayList<Order>();                         						   						 /* All The Orders That We Order On Specific Store */
		
		for(int i = 0 ; i < StoreManagerUI.orders.size() ; i++)                                                 				 /* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			orders.add(StoreManagerUI.orders.get(i));
			
			for (Entry<ProductType, Integer> entry : orders.get(i).getProductInOrderType().entrySet()) 
			{
				
				if((productType_Of_Specific_Order_Of_Specific_Store.contains(entry.getKey())) == false)
				{
					product_Type = entry.getKey();
					productType_Of_Specific_Order_Of_Specific_Store.add(product_Type);
				}
			}
		}
		
		Count_In_Chart = new int[Type_Of_Products_In_Order.length];
		ArrayList<XYChart.Series<String,Integer>> setChart = new ArrayList<XYChart.Series<String,Integer>>();
		for(int i = 0 ; i < Type_Of_Products_In_Order.length ; i++)
		{
			XYChart.Series<String,Integer> Chart = new XYChart.Series<String,Integer>();
			Chart.setName(Type_Of_Products_In_Order[i]);
			setChart.add(Chart);
		}
		
		for(int Order_Index = 0 ; Order_Index < orders.size() ; Order_Index++)
		{
			for (Entry<ProductType, Integer> entry : orders.get(Order_Index).getProductInOrderType().entrySet()) 
			{
				for(int Product_Type_Index = 0 ; Product_Type_Index < productType_Of_Specific_Order_Of_Specific_Store.size(); Product_Type_Index++)
				{
					if((entry.getKey().equals(productType_Of_Specific_Order_Of_Specific_Store.get(Product_Type_Index)) == true))
					{
						for(int SetChart_Index = 0 ; SetChart_Index < setChart.size() ; SetChart_Index++)
						{
							if(String.valueOf(productType_Of_Specific_Order_Of_Specific_Store.get(Product_Type_Index)).compareTo(setChart.get(SetChart_Index).getName()) == 0)
								setChart.get(SetChart_Index).getData().add(new XYChart.Data<String, Integer>(setChart.get(SetChart_Index).getName(), ++(Count_In_Chart[SetChart_Index])));
						}	
					}
				}
			}
		}
		
		ChartOrderReport.getData().addAll(setChart);
	}
	
/* ------------------------------------------------------------------------------------------------------------------- */
	
}
