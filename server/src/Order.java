import java.io.*;

public class Order implements Serializable{
	public int id;
	public int state;
	public int driverId;
	public int psgId;
	public int[] time = new int[5];
	public int[] origin = new int[4];
	public int[] dest = new int[4];
	public int commentId;
}
