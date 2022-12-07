package decorator;

public class MainTest {

	public static void main(String[] args) {

		// 기반, 기본 클래스는 유지하되, 이 후에 필요한 녀석을 꾸밀 때 사용!
		ICar audi = new Audi(1_000);
		audi.showPrice();
		
		ICar audiA3 = new A3(audi, "A3");
		audiA3.showPrice();

		
		ICar audiA4 = new A4(audi, "A4");
		audiA4.showPrice();
		
		
		ICar audiA5 = new A5(audi, "A5");
		audiA5.showPrice();
	}

}
