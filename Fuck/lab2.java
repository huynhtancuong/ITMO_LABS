import static java.lang.Math.*;

public class lab2 {
	public static void main(String[] args) {
		float[] x = new float[11];
		for (int i = 0; i < 11; i++) {
			x[i] = (float)random()*(4.001f);
			while(x[i]>4){
				x[i] = (float)random()*(4.001f);
			}
		}
		for (int i = 0; i < 11; i++) {
			System.out.println(x[i]);
		}

	}
}
