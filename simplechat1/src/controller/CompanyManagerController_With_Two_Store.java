package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CompanyManagerUI;
import boundery.UserUI;
import entity.Message;
import entity.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class CompanyManagerController_With_Two_Store implements Initializable {
	
	/**
	 * With This Variable I Connect Between The Server And The Client .
	 */
	private Message msg;
	
	/**
	 * This Variable's Is The Index that return from the ComboBox .
	 */
	private static int itemIndex_For_Store_2 = 0;
	private static int itemIndex_For_Store_1 = 0; 
	private static int itemIndex_For_Report_Store_1 = 0;
	private static int itemIndex_For_Report_Store_2 = 0;
	
	/**
	 * This Variabel's Help me With to connection to the Server .
	 */
	private int temp_Store_Id_1;
	private int temp_Store_Id_2;
	private Date temp_Date_Quarter_Report_1;
	private Date temp_Date_Quarter_Report_2;
	
	/**
	 * This Flag's Help Me To know if i Click On the ComboBox Of Store's And Date's .
	 */
	public static boolean Flag_Enter_On_The_Combo_Box_Store_1 = false;
	public static boolean Flag_Enter_On_The_Combo_Box_Store_2 = false;
	public static boolean Flag_Enter_On_The_Combo_Box_Date_1 = false;
	public static boolean Flag_Enter_On_The_Combo_Box_Date_2 = false;
	
	public static boolean Flag_Enter_On_The_Combo_Box_Store_1_For_Compare = false;
	public static boolean Flag_Enter_On_The_Combo_Box_Store_2_For_Compare = false;
	public static boolean Flag_Enter_On_The_Combo_Box_Date_1_For_Compare = false;
	public static boolean Flag_Enter_On_The_Combo_Box_Date_2_For_Compare = false;
	
	/**
	 * ObservableList That Help Me To Get Inside The ComboBox Details .
	 */
	ObservableList<String> storeList_One;
	ObservableList<String> storeList_Two;
	ObservableList<Date> DateList_One;
	ObservableList<Date> DateList_Two;
	
/* -------------------------  For The Window Of Company Manager Report - To See Two Store In Parallel ----------------------------------- */		
	
	 @FXML
	 private ComboBox<String> cmbFirstStores;                  

	 @FXML
	 private ComboBox<String> cmbSecondStores;
	 
	 @FXML
	 private ComboBox<Date> cmbReportsFirstStore;
	 
	 @FXML
	 private ComboBox<Date> cmbReportsSecondStore;

	 @FXML
	 private Button btnCompare;
	 
	 @FXML
	 private Button btnCustomerComplaintStatusReport_Store;

	 @FXML
	 private Button btnQuarterlyRevenueReport_Store;

	 @FXML
	 private Button btnSatisfactionReport_Store;

	 @FXML
	 private Button btnOrderReport_Store;
	 
	 @FXML
	 private Button btnClose;
	 
	 @FXML
	 private Button btnTryAgain;

	 @FXML
	 private TextArea txtArea_Error_Message;
	
/* --------------------------------- Close the GUI Of - The Company Manager Report With Two Store ------------------------------------------------- */	 	
	
	 /**
	  * With This Function We Close The GUI Of Company Manager With Two Store .
	  * @param event - When The Client Press On the Butten This Parameter Start To Work .
	  * @throws Exception - If The FXML Not Work .
	  */
	public void closeTwoStoreWindow(ActionEvent event) throws Exception   
	{ 
		CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store = 1;
		CompanyManagerUI.stores_For_Company_Manager.clear();
		CompanyManagerUI.stores_For_Company_Manager_2.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Company Manager Report Form -----");
		primaryStage.show();										   
	}	
	
/* --------------------------------  The Report About Quarterly Revenue ----------------------------------- */	
	
	/* -------------------------------- Store 1 + 2 ------------------------------------------------------- */
	
	/**
	 * In This Function I See the GUI Of - Two Store In Parrallel ((The Store Can Be The Same , Or Not The Same) & (The Date Can Be The same Or Not The Same)) .  
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void QuarterlyRevenueReport_Store(ActionEvent event) throws Exception        
	{ 	
		if((Flag_Enter_On_The_Combo_Box_Store_1 == true && Flag_Enter_On_The_Combo_Box_Date_1 == true) 
				&& (Flag_Enter_On_The_Combo_Box_Store_2 == true && Flag_Enter_On_The_Combo_Box_Date_2 == true))
		{
			if((temp_Store_Id_1 == temp_Store_Id_2) && (temp_Date_Quarter_Report_1.equals(temp_Date_Quarter_Report_2)))
			{
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
				FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
				Pane root = loader.load(getClass().getResource("/controller/Company_Mannager_Try_To_Watch_On_Same_Store_And_Quarter.fxml").openStream());
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("----- Company Manager Try To Watch On The Same Store And Quarter -----");
				primaryStage.show();
			}
			else
			{
				((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				BorderPane border = new BorderPane(); 
				HBox rootPane = new HBox();
				
				FXMLLoader loader = new FXMLLoader();
				FXMLLoader loader_2 = new FXMLLoader();
				
				Pane root = loader.load(getClass().getResource("/controller/QuarterlyRevenueReportForm_For_CompanyManager.fxml").openStream());
				QuarterlyRevenueReportController_For_CompanyManager quarterlyRevenueReportController = loader.getController();
				quarterlyRevenueReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_First_Store())); 
				
				Pane root_2 = loader_2.load(getClass().getResource("/controller/QuarterlyRevenueReportForm_For_CompanyManager_2.fxml").openStream());
				QuarterlyRevenueReportController_For_CompanyManager_2 quarterlyRevenueReportController_2 = loader_2.getController();
				quarterlyRevenueReportController_2.loadStore(CompanyManagerUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store())); 
				
				rootPane.getChildren().addAll(root,root_2); 
				
				border.setCenter(rootPane);
				
				Scene scene = new Scene(border);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.setTitle("Comparing Between - Two Different Quarter ---> { The Left Is - Store One , The Right Is - Store Two }");
				primaryStage.show();
			}
		}
		else
		{
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
			Pane root = loader.load(getClass().getResource("/controller/Company_Mannager_With_Two_Store_You_Not_Press_On_Store_Or_Date.fxml").openStream());
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("----- Company Manager With Two Store Not Press On The Combo Box Of Store Or Date -----");
			primaryStage.show();
		}
	}
	
/* --------------------------------  The Report About Order ----------------------------------- */	
	
	/* -------------------------------- Store 1 + 2 ------------------------------------------- */
	
	/**
	 * In This Function I See the GUI Of - Two Store In Parrallel ((The Store Can Be The Same , Or Not The Same) & (The Date Can Be The same Or Not The Same)) .  
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void OrderReport_Store(ActionEvent event) throws Exception        
	{
		if((Flag_Enter_On_The_Combo_Box_Store_1 == true && Flag_Enter_On_The_Combo_Box_Date_1 == true) 
				&& (Flag_Enter_On_The_Combo_Box_Store_2 == true && Flag_Enter_On_The_Combo_Box_Date_2 == true))
		{
			if((temp_Store_Id_1 == temp_Store_Id_2) && (temp_Date_Quarter_Report_1.equals(temp_Date_Quarter_Report_2)))
			{
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
				FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
				Pane root = loader.load(getClass().getResource("/controller/Company_Mannager_Try_To_Watch_On_Same_Store_And_Quarter.fxml").openStream());
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("----- Company Manager Try To Watch On The Same Store And Quarter -----");
				primaryStage.show();
			}
			else
			{
				((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				BorderPane border = new BorderPane(); 
				HBox rootPane = new HBox();
				
				FXMLLoader loader = new FXMLLoader();
				FXMLLoader loader_2 = new FXMLLoader();
				
				Pane root = loader.load(getClass().getResource("/controller/OrderReportForm_For_CompanyManager.fxml").openStream());
				OrderReportController_For_ComapnyManager orderReportController = loader.getController();
				orderReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_First_Store()));
				
				Pane root_2 = loader_2.load(getClass().getResource("/controller/OrderReportForm_For_CompanyManager_2.fxml").openStream());
				OrderReportController_For_ComapnyManager_2 orderReportController_2 = loader_2.getController();
				orderReportController_2.loadStore(CompanyManagerUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store()));
				
				rootPane.getChildren().addAll(root,root_2); 
				
				border.setCenter(rootPane);
				
				Scene scene = new Scene(border);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.setTitle("Comparing Between - Two Different Quarter ---> { The Left Is - Store One , The Right Is - Store Two }");
				primaryStage.show();
			}
		}
		else
		{
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
			Pane root = loader.load(getClass().getResource("/controller/Company_Mannager_With_Two_Store_You_Not_Press_On_Store_Or_Date.fxml").openStream());
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("----- Company Manager With Two Store Not Press On The Combo Box Of Store Or Date -----");
			primaryStage.show();
		}
	}
		
/* --------------------------------  The Report About Complaint ----------------------------------- */
	
	/* -------------------------------- Store 1 + 2 ----------------------------------------------- */
	
	
	/**
	 * In This Function I See the GUI Of - Two Store In Parrallel ((The Store Can Be The Same , Or Not The Same) & (The Date Can Be The same Or Not The Same)) .  
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void CustomerComplaintStatusReport_Store(ActionEvent event) throws Exception       
	{
		if((Flag_Enter_On_The_Combo_Box_Store_1 == true && Flag_Enter_On_The_Combo_Box_Date_1 == true) 
				&& (Flag_Enter_On_The_Combo_Box_Store_2 == true && Flag_Enter_On_The_Combo_Box_Date_2 == true))
		{
			if((temp_Store_Id_1 == temp_Store_Id_2) && (temp_Date_Quarter_Report_1.equals(temp_Date_Quarter_Report_2)))
			{
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
				FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
				Pane root = loader.load(getClass().getResource("/controller/Company_Mannager_Try_To_Watch_On_Same_Store_And_Quarter.fxml").openStream());
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("----- Company Manager Try To Watch On The Same Store And Quarter -----");
				primaryStage.show();
			}
			else
			{
				((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				BorderPane border = new BorderPane(); 
				HBox rootPane = new HBox();
				
				FXMLLoader loader = new FXMLLoader();
				FXMLLoader loader_2 = new FXMLLoader();
				
				Pane root = loader.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm_For_ComapnyManager.fxml").openStream());
				CustomerComplaintStatusReportController_For_CompanyManager customerComplaintStatusReportController = loader.getController();
				customerComplaintStatusReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_First_Store()));
				
				Pane root_2 = loader_2.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm_For_ComapnyManager_2.fxml").openStream());
				CustomerComplaintStatusReportController_For_CompanyManager_2 customerComplaintStatusReportController_2 = loader_2.getController();
				customerComplaintStatusReportController_2.loadStore(CompanyManagerUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store()));
				
				rootPane.getChildren().addAll(root,root_2); 
				
				border.setCenter(rootPane);
				
				Scene scene = new Scene(border);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.setTitle("Comparing Between - Two Different Quarter ---> { The Left Is - Store One , The Right Is - Store Two }");
				primaryStage.show();
			}
		}
		else
		{
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
			Pane root = loader.load(getClass().getResource("/controller/Company_Mannager_With_Two_Store_You_Not_Press_On_Store_Or_Date.fxml").openStream());
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("----- Company Manager With Two Store Not Press On The Combo Box Of Store Or Date -----");
			primaryStage.show();
		}
	}
	
/* --------------------------------  The Report About Satisfaction ----------------------------------- */
	
	/* -------------------------------- Store 1 + 2 -------------------------------------------------- */
	
	/**
	 * In This Function I See the GUI Of - Two Store In Parrallel ((The Store Can Be The Same , Or Not The Same) & (The Date Can Be The same Or Not The Same)) .  
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void SatisfactionReport_Store(ActionEvent event) throws Exception         
	{
		if((Flag_Enter_On_The_Combo_Box_Store_1 == true && Flag_Enter_On_The_Combo_Box_Date_1 == true) 
				&& (Flag_Enter_On_The_Combo_Box_Store_2 == true && Flag_Enter_On_The_Combo_Box_Date_2 == true))
		{
			if((temp_Store_Id_1 == temp_Store_Id_2) && (temp_Date_Quarter_Report_1.equals(temp_Date_Quarter_Report_2)))
			{
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
				FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
				Pane root = loader.load(getClass().getResource("/controller/Company_Mannager_Try_To_Watch_On_Same_Store_And_Quarter.fxml").openStream());
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("----- Company Manager Try To Watch On The Same Store And Quarter -----");
				primaryStage.show();
			}
			else
			{
				((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				BorderPane border = new BorderPane(); 
				HBox rootPane = new HBox();
				
				FXMLLoader loader = new FXMLLoader();
				FXMLLoader loader_2 = new FXMLLoader();
				
				Pane root = loader.load(getClass().getResource("/controller/SatisfactionReportForm_For_CompanyManager.fxml").openStream());
				SatisfactionReportController_For_CompanyManager satisfactionReportController = loader.getController();
				satisfactionReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_First_Store()));
				
				Pane root_2 = loader_2.load(getClass().getResource("/controller/SatisfactionReportForm_For_CompanyManager_2.fxml").openStream());
				SatisfactionReportController_For_CompanyManager_2 satisfactionReportController_2 = loader_2.getController();
				satisfactionReportController_2.loadStore(CompanyManagerUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store()));
				
				rootPane.getChildren().addAll(root,root_2); 
				
				border.setCenter(rootPane);
				
				Scene scene = new Scene(border);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.setTitle("Comparing Between - Two Different Quarter ---> { The Left Is - Store One , The Right Is - Store Two }");
				primaryStage.show();
			}
		}
		else
		{
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
			Pane root = loader.load(getClass().getResource("/controller/Company_Mannager_With_Two_Store_You_Not_Press_On_Store_Or_Date.fxml").openStream());
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("----- Company Manager With Two Store Not Press On The Combo Box Of Store Or Date -----");
			primaryStage.show();
		}
	}	
	
/* -------------------------- Taking Store From The Combo Box of Store ------------------------------------ */	
	
	/* -------------------------------- Store 1 ----------------------------------------------------------- */
	
	/**
	 * In This Function We Return The Index From Our Choise From - the ComboBox Of The First Store .
	 * @return
	 */
	public int getItemIndex_First_Store()                                   	
	{
		if(cmbFirstStores.getSelectionModel().getSelectedIndex() == -1)
		{
			Flag_Enter_On_The_Combo_Box_Store_1_For_Compare = false;
			Flag_Enter_On_The_Combo_Box_Store_1 = false;
			return itemIndex_For_Store_1;
		}
		else
		{
			Flag_Enter_On_The_Combo_Box_Store_1_For_Compare = true;
			Flag_Enter_On_The_Combo_Box_Store_1 = true;
		}
	
		return cmbFirstStores.getSelectionModel().getSelectedIndex();
	}
	
	/* -------------------------------- Store 2 ------------------------------------------------------------ */
	
	/**
	 * In This Function We Return The Index From Our Choise From - the ComboBox Of The Second Store .
	 * @return
	 */
	public int getItemIndex_Second_Store()                                   	
	{
		if(cmbSecondStores.getSelectionModel().getSelectedIndex() == -1)
		{
			Flag_Enter_On_The_Combo_Box_Store_2_For_Compare = false;
			Flag_Enter_On_The_Combo_Box_Store_2 = false;
			return itemIndex_For_Store_2;
		}
		else
		{
			Flag_Enter_On_The_Combo_Box_Store_2_For_Compare = true;
			Flag_Enter_On_The_Combo_Box_Store_2 = true;
		}
		return cmbSecondStores.getSelectionModel().getSelectedIndex();
		
	}
	
/* -------------------------- Taking Date From The Combo Box of Report ------------------------------------ */	
	
	/* -------------------------------- Store 1 ----------------------------------------------------------- */
	
	/**
	 * In This Function We Return The Index From Our Choise From - the ComboBox Of The Date Report Of Store One .
	 * @return
	 */
	public int getItemIndexFromDateComboBox_For_CompanyManager_FirstStore()                                   	
	{
		if(cmbReportsFirstStore.getSelectionModel().getSelectedIndex() == -1)
		{
			Flag_Enter_On_The_Combo_Box_Date_1_For_Compare = false;
			Flag_Enter_On_The_Combo_Box_Date_1 = false;
			return itemIndex_For_Report_Store_1;
		}
		else
		{
			Flag_Enter_On_The_Combo_Box_Date_1_For_Compare = true;
			Flag_Enter_On_The_Combo_Box_Date_1 = true;
		}
	
		return cmbReportsFirstStore.getSelectionModel().getSelectedIndex();
	}
	
/* -------------------------- Taking Date From The Combo Box of Report ------------------------------------ */	
	
	/* -------------------------------- Store 2 ----------------------------------------------------------- */
	
	/**
	 * In This Function We Return The Index From Our Choise From - the ComboBox Of The Date Report Of Store Two .
	 * @return
	 */
	public int getItemIndexFromDateComboBox_For_CompanyManager_SecondStore()                                   	
	{
		if(cmbReportsSecondStore.getSelectionModel().getSelectedIndex() == -1)
		{
			Flag_Enter_On_The_Combo_Box_Date_2_For_Compare = false;
			Flag_Enter_On_The_Combo_Box_Date_2 = false;
			return itemIndex_For_Report_Store_2;
		}
		else 
		{
			Flag_Enter_On_The_Combo_Box_Date_2_For_Compare = true;
			Flag_Enter_On_The_Combo_Box_Date_2 = true;
		}
		return cmbReportsSecondStore.getSelectionModel().getSelectedIndex();
	}
	
/* ----------------------------------------- Set The Combo Box Of All the Date Of the Report Of Specific Store ----------------------------------- */		
	
	/* -------------------------------- Store 1 -------------------------------------------------------------------------------------------------- */
	
	/**
	 * In This Function We Set The Date Of the Report Of Store One .
	 * @return
	 */
	public void set_Dates_Of_Report_Of_Store_One_At_ComboBox()      								
	{ 				
		ArrayList<Date> Date_Of_Report = new ArrayList<Date>();	
		
		for(int i = 0 ; i < CompanyManagerUI.Dates_For_Company_Manager.size() ; i++)
		{
			Date_Of_Report.add(CompanyManagerUI.Dates_For_Company_Manager.get(i));
		}
		
		DateList_One = FXCollections.observableArrayList(Date_Of_Report);
		cmbReportsFirstStore.setItems(DateList_One);
	}
	
	/* -------------------------------- Store 2 --------------------------------------------------------------------------------------------------- */
	
	/**
	 * In This Function We Set The Date Of the Report Of Store Two .
	 * @return
	 */
	public void set_Dates_Of_Report_Of_Store_Two_At_ComboBox()      								
	{ 				
		ArrayList<Date> Date_Of_Report = new ArrayList<Date>();	
		
		for(int i = 0 ; i < CompanyManagerUI.Dates_For_Company_Manager_2.size() ; i++)
		{
			Date_Of_Report.add(CompanyManagerUI.Dates_For_Company_Manager_2.get(i));
		}
		
		DateList_Two = FXCollections.observableArrayList(Date_Of_Report);
		cmbReportsSecondStore.setItems(DateList_Two);
	}
	
/* ----------------------------------------- Set The Combo Box Of Stores ----------------------------------- */	
	
	/* -------------------------------- Store 1 ------------------------------------------------------------ */
	
	/**
	 * In This Function We Set The Store In the Company At ComboBox Number One .
	 * @return
	 */
	public void setStores_One_ComboBox()    								
	{ 				
		ArrayList<String> temp_Stores_ID_And_Address_List = new ArrayList<String>();	
		
		for(Store s: CompanyManagerUI.stores_For_Company_Manager)
		{
			temp_Stores_ID_And_Address_List.add(String.valueOf(s.getStoreId()) + " ---> " + s.getStore_Address());
		}
		
		storeList_One = FXCollections.observableArrayList(temp_Stores_ID_And_Address_List);
		cmbFirstStores.setItems(storeList_One);
	}	
	
	/* -------------------------------- Store 2 ------------------------------------------------------------- */
	
	/**
	 * In This Function We Set The Store In the Company At ComboBox Number Two .
	 * @return
	 */
	public void setStores_Two_ComboBox()    								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<String> temp_Stores_ID_And_Address_List = new ArrayList<String>();	
		
		for(Store s: CompanyManagerUI.stores_For_Company_Manager_2)
		{
			temp_Stores_ID_And_Address_List.add(String.valueOf(s.getStoreId()) + " ---> " + s.getStore_Address());
		}
		
		storeList_Two = FXCollections.observableArrayList(temp_Stores_ID_And_Address_List);
		cmbSecondStores.setItems(storeList_Two);
	}	
	
/* -------------------------------- The Button Of The Store That You Choose --------------------------------- */		
	
	/**
	 * In This Function The Company Manager Choose His First Store To Watch .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void Click_On_Your_First_Store_Choise(ActionEvent event) throws Exception
	{
		cmbReportsFirstStore.setVisible(true);
		
		temp_Store_Id_1 = CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_First_Store()).getStoreId();
		
		if(Flag_Enter_On_The_Combo_Box_Store_1 == true && Flag_Enter_On_The_Combo_Box_Store_1_For_Compare == true)
		{
			msg = new Message(temp_Store_Id_1,"Comapny Manager - Take The Date Of All the Report Of Specific Store");
			UserUI.myClient.accept(msg);
			while(CompanyManagerUI.Dates_For_Company_Manager.size() == 0)
			{
				if(CompanyManagerUI.Dates_For_Company_Manager.size() == 0)
				{
					break;
				}
			}
			Thread.sleep(200);
			set_Dates_Of_Report_Of_Store_One_At_ComboBox();
			
			/* ---------------------- For The Revenue Report ---------------------------------- */
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.clear(); 
			CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(temp_Store_Id_1); /* The Store_Id */
			
			/* ----------------------- For The Order Report ----------------------------------- */
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.clear();
			CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(temp_Store_Id_1);  /* The Store_Id */
			
			/* ----------------------- For The Complaint Report ------------------------------- */
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.clear();
			CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(temp_Store_Id_1);  /* The Store_Id */
			
			/* ----------------------- For The Satisfaction Report -----------------------------*/
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.clear();
			CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(temp_Store_Id_1);  /* The Store_Id */
			
			/* ----------------------- For The Comparing Between Two Stores Or Two Quarter's ----------------------*/
			
			CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.clear();
			CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.add(temp_Store_Id_1);  /* The Store_Id */
		}	
	}	
	
/* -------------------------------- The Button Of The Store That You Choose ------------------------------- */		
	
	/**
	 * In This Function The Company Manager Choose His Second Store To Watch .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void Click_On_Your_Second_Store_Choise(ActionEvent event) throws Exception
	{
		cmbReportsSecondStore.setVisible(true);
		
		temp_Store_Id_2 = CompanyManagerUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store()).getStoreId();
		
		if(Flag_Enter_On_The_Combo_Box_Store_2 == true && Flag_Enter_On_The_Combo_Box_Store_2_For_Compare == true)
		{	
			msg = new Message(temp_Store_Id_2,"Comapny Manager - Take The Date Of All the Report Of Specific Store");
			UserUI.myClient.accept(msg);
			while(CompanyManagerUI.Dates_For_Company_Manager_2.size() == 0)
			{
				if(CompanyManagerUI.Dates_For_Company_Manager_2.size() == 0)
				{
					break;
				}
			}
			Thread.sleep(200);
			set_Dates_Of_Report_Of_Store_Two_At_ComboBox();
			
			/* ---------------------- For The Revenue Report ------------------------------- */
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.clear();
			CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.add(temp_Store_Id_2); /* The Store_Id */
			
			/* ----------------------- For The Order Report -------------------------------- */
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.clear();
			CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.add(temp_Store_Id_2);  /* The Store_Id */
			
			/* ----------------------- For The Complaint Report -----------------------------*/
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.clear();
			CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.add(temp_Store_Id_2);  /* The Store_Id */
			
			/* ----------------------- For The Satisfaction Report ------------------------- */
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.clear();
			CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.add(temp_Store_Id_2);  /* The Store_Id */
			
			/* ----------------------- For The Comparing Between Two Different Quarter Report --------------------------------------------*/
			
			CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.clear();
			CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.add(temp_Store_Id_2);  /* The Store_Id */
		}
	}
	
/* -------------------------------- The Button Of The Report That We Choose ------------------------------- */				
	
	/**
	 * In This Function The Company Manager Choose His First Date Of Report Of His First Store Choise .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void Click_On_Your_Quarter_Report_Store_One(ActionEvent event) throws Exception
	{
		
		temp_Date_Quarter_Report_1 = CompanyManagerUI.Dates_For_Company_Manager.get(getItemIndexFromDateComboBox_For_CompanyManager_FirstStore());
		
		if(Flag_Enter_On_The_Combo_Box_Date_1 == true && Flag_Enter_On_The_Combo_Box_Date_1_For_Compare == true)
		{
	
			/* ---------------------- For The Revenue Report ---------------------------------- */
			
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(temp_Date_Quarter_Report_1); /* The Date Of the Report */
			
			/* ----------------------- For The Order Report ----------------------------------- */
			
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(temp_Date_Quarter_Report_1);  /* The Date Of the Report */
			
			/* ----------------------- For The Complaint Report ------------------------------- */
			
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(temp_Date_Quarter_Report_1);  /* The Date Of the Report */
			
			/* ----------------------- For The Satisfaction Report -----------------------------*/
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(temp_Date_Quarter_Report_1);  /* The Date Of the Report */
			
			/* ----------------------- For The Comparing Between Two Stores Or Two Quarter's -----------------*/
			
			CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.add(temp_Date_Quarter_Report_1);  				   /* The Date Of the Report */
		}
	}	
	
/* -------------------------------- The Button Of The Report That We Choose ------------------------------- */				
	
	/**
	 * In This Function The Company Manager Choose His Second Date Of Report Of His Second Store Choise .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void Click_On_Your_Quarter_Report_Store_Two(ActionEvent event) throws Exception
	{
		
		temp_Date_Quarter_Report_2 = CompanyManagerUI.Dates_For_Company_Manager_2.get(getItemIndexFromDateComboBox_For_CompanyManager_SecondStore());
		
		if(Flag_Enter_On_The_Combo_Box_Date_2 == true && Flag_Enter_On_The_Combo_Box_Date_2_For_Compare == true)
		{
			/* ---------------------- For The Revenue Report ------------------------------- */
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.add(temp_Date_Quarter_Report_2); /* The Date Of the Report */
			
			/* ----------------------- For The Order Report -------------------------------- */
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.add(temp_Date_Quarter_Report_2);  /* The Date Of the Report */
			
			/* ----------------------- For The Complaint Report -----------------------------*/
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.add(temp_Date_Quarter_Report_2);  /* The Date Of the Report */
			
			/* ----------------------- For The Satisfaction Report ------------------------- */
			
			CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.add(temp_Date_Quarter_Report_2);  /* The Date Of the Report */
			
			/* ----------------------- For The Comparing Between Two Different Quarter Report --------------------------------------------*/
			
			CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.add(temp_Date_Quarter_Report_2);  /* The Date Of the Report */							
		}	
	}
	
	/**
	 * In This Function We Compare Between Two Different Store Or Two Different Quarter .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void Compare(ActionEvent event) throws Exception
	{
		if((Flag_Enter_On_The_Combo_Box_Store_1_For_Compare == true && Flag_Enter_On_The_Combo_Box_Date_1_For_Compare == true) 
				&& (Flag_Enter_On_The_Combo_Box_Store_2_For_Compare == true && Flag_Enter_On_The_Combo_Box_Date_2_For_Compare == true))
		{	
			if((temp_Store_Id_1 == temp_Store_Id_2) && (temp_Date_Quarter_Report_1.equals(temp_Date_Quarter_Report_2)))
			{
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
				FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
				Pane root = loader.load(getClass().getResource("/controller/Company_Mannager_Try_To_Watch_On_Same_Store_And_Quarter.fxml").openStream());
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("----- Company Manager Try To Watch On The Same Store And Quarter -----");
				primaryStage.show();
			}
			else
			{
				((Node)event.getSource()).getScene().getWindow().hide();    		  /* Hiding primary window */
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportController_Compare_Between_Two_Diffrent_Quarter.fxml").openStream());
						
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Compare Between ---> { Two Different Store || Two Different Quarter }");
				primaryStage.show(); 
			}
		}
		else
		{
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
			Pane root = loader.load(getClass().getResource("/controller/Company_Mannager_With_Two_Store_You_Not_Press_On_Store_Or_Date.fxml").openStream());
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("----- Company Manager With Two Store Not Press On The Combo Box Of Store Or Date -----");
			primaryStage.show();
		}
	}
	
	/**
	 * In This Function We initialize The GUI Of The Company Manager Report With Two Store .
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		cmbReportsFirstStore.setVisible(false);
		cmbReportsSecondStore.setVisible(false);
		
		/* When I Start To Initialize Its Alaways Will Be With the Defult Value */
		Flag_Enter_On_The_Combo_Box_Store_1_For_Compare = false;
		Flag_Enter_On_The_Combo_Box_Store_2_For_Compare = false;
		Flag_Enter_On_The_Combo_Box_Date_1_For_Compare = false;
		Flag_Enter_On_The_Combo_Box_Date_2_For_Compare = false;
		
		/* When I Start To Initialize Its Alaways Will Be With the Defult Value */
		Flag_Enter_On_The_Combo_Box_Store_1 = false;
		Flag_Enter_On_The_Combo_Box_Store_2 = false;
		Flag_Enter_On_The_Combo_Box_Date_1 = false;
		Flag_Enter_On_The_Combo_Box_Date_2 = false;
		
		ArrayList<Store> stores = new ArrayList<Store>();           
		msg = new Message(stores,"Company Manager - Add Store To Combo Box From DB");
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.stores_For_Company_Manager.size() == 0)
		{
			if(CompanyManagerUI.stores_For_Company_Manager.size() == 0)
			{
				break;
			}
		}
		while(CompanyManagerUI.stores_For_Company_Manager_2.size() == 0)
		{
			if(CompanyManagerUI.stores_For_Company_Manager_2.size() == 0)
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
		
		setStores_One_ComboBox();
		setStores_Two_ComboBox();
	}
}
