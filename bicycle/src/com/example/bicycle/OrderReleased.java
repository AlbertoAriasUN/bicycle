package com.example.bicycle;

import java.io.*;

public class OrderReleased implements Serializable{
	public int[] time = new int[5];
	public String origin;
	public String dest;
	public int cookie;
}