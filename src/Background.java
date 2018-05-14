public class Background extends Animation{

	public Background() {
		super("Images/Background/background_",5, 101, ".jpg");

	}
	public Background(int duration) {
		super("Images/Background/background_",duration, 101, ".jpg");
	}
	public Background(int width, int height) {
		super("Images/Background/background_",5, 101, ".jpg",width,height);
	}

}
