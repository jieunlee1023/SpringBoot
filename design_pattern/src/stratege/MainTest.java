package stratege;

public class MainTest {

	public static void main(String[] args) {

		Encoder encoder = new Encoder();
		Base64Strategy base64Strategy = new Base64Strategy();
		NormalStrategy normalStrategy = new NormalStrategy();

		encoder.setEncodingStrategy(base64Strategy);
		System.out.println(encoder.getMessage("안뇽 ~ "));

		encoder.setEncodingStrategy(normalStrategy);
		System.out.println(encoder.getMessage("안뇽 ~ "));
	}

}
