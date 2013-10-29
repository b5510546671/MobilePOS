package controller;
import com.core.Store;

public abstract class AbstractController {
	private Store store;
	
	public AbstractController() {
		this.store = Store.getInstance();
	}
}
