package com.cellexperts.interfaces;

import java.util.List;

import com.cellexperts.db.hbm.Employees;
import com.cellexperts.db.hbm.Store;

public interface IStore {
	
	public Employees getStore(int storeId);
	public List<Store> getAllStores(); // TODO Not sure if its good way to return hibernate bean or the bean object.
	public int createStore(Store store); //TODO return type can be Employee instead of just ID
	

}
