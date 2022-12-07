package observer;

public class Button implements IButtonListener{

	String name;
	private IButtonListener buttonListener;
	
	public Button(String name) {
		this.name = name;
	}

	
	@Override
	public void clickEvent(String message) {
		buttonListener.clickEvent(message);
	}
	
	public void addListener(IButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}
	
}
