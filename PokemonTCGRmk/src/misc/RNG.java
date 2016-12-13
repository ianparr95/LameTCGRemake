package misc;
import java.util.Random;

public class RNG {
	private static Random rng = new Random();
	
	public static boolean flipCoin() {
		int result = rng.nextInt(2);
		return result == 0;
	}
}
