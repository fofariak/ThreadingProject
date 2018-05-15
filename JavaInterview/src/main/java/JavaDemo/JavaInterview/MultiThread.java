package JavaDemo.JavaInterview;

import org.json.*;

public class MultiThread implements Runnable {

	private static final int N = 25;
	static long val = 1;
	public Thread thread;
	private static Object object = new Object();

	public MultiThread(String name) {
		thread = new Thread(this, name);
		thread.start();
	}

	public void run() {
		for (int i = 0; i < N; i++) {
			synchronized (object) {
				String str = "{ \"Val\": \""+ Long.toString(val++) + "\"}";
				JSONObject obj = new JSONObject(str);
				System.out.println(thread.getName());
				System.out.println(obj.toString(2));
				object.notify();
				try {
					object.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public static void main(String[] args ) throws InterruptedException
	{
		System.out.println("Free memory (bytes): " + 
	    Runtime.getRuntime().freeMemory());
		MultiThread message1 = new MultiThread("Ping");
		MultiThread message2 = new MultiThread("Pong");
		MultiThread message3 = new MultiThread("Ding");
		MultiThread message4 = new MultiThread("Dong");
		message1.thread.join();
		message2.thread.join();
		message3.thread.join();
		message4.thread.join();
		System.out.println("Free memory (bytes): " + 
		Runtime.getRuntime().freeMemory());
	}
}

//class Value{
//	static long val = 0;
//	public Value(){		
//	}
//	
//	public void increment(String threadName){
//		val ++;
//	}
//	
//	public String toString(){
//		String str = "{ \"Val\": \" "+ this.val + "\"}";
//		JSONObject obj = new JSONObject(str);
//	}
//}