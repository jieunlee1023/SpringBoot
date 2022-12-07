package observer;

public class MainTest {

	public static void main(String[] args) {
		
		Button button = new Button("button");
		button.addListener(new IButtonListener() {
			
			@Override
			public void clickEvent(String event) {
				System.out.println("event : " + event);
				
			}
		});
		
		//button click ...
		button.clickEvent("AAA");
		button.clickEvent("BBB");
		button.clickEvent("CCC");
		button.clickEvent("DDD");
		button.clickEvent("EEE");
		
		
	}// end of main
}
