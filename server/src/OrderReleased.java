import java.io.*;

public class OrderReleased implements Serializable{
	public int[] time = new int[5];
	public int[] origin = new int[4];
	public int[] dest = new int[4];
	public int cookie;
}