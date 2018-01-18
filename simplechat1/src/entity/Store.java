package entity;

import java.io.Serializable;

public class Store implements Serializable {
	
	private int StoreId;
	private String Store_Address;
	private int QuantityOfOrders;
	
	public Store() {}
	
	public Store(int storeId, String store_Address, int quantityOfOrders) {
		super();
		StoreId = storeId;
		Store_Address = store_Address;
		QuantityOfOrders = quantityOfOrders;
	}
	
	public int getStoreId() {
		return StoreId;
	}
	public void setStoreId(int storeId) {
		StoreId = storeId;
	}
	public String getStore_Address() {
		return Store_Address;
	}
	public void setStore_Address(String store_Address) {
		Store_Address = store_Address;
	}
	
	public int getQuantityOfOrders() {
		return QuantityOfOrders;
	}

	public void setQuantityOfOrders(int quantityOfOrders) {
		QuantityOfOrders = quantityOfOrders;
	}
	@Override
	public String toString() {
		return "Store [StoreId=" + StoreId + ", Store_Address=" + Store_Address + ", QuantityOfOrders="
				+ QuantityOfOrders + "]";
	}

}