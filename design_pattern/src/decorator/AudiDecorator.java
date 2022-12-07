package decorator;

public class AudiDecorator implements ICar{
	
	protected ICar audi; //인터페이스 타입
	protected String modelName;
	protected int modelPrice;
	
	public AudiDecorator(ICar audi, String modelName, int modelPrice) {
		this.audi = audi;
		this.modelName = modelName;
		this.modelPrice = modelPrice;
	}
	
	@Override
	public int getPrice() {
		return audi.getPrice() + modelPrice;
	}

	@Override
	public void showPrice() {
		System.out.println(modelName + "에 가격은 " + this.getPrice()+"만원 입니다.");
	}

}
