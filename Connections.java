import java.util.concurrent.Semaphore;

public class Connections {
	private static Connections instance = new Connections();//constructor created

	private Semaphore semaphore = new Semaphore(1);// semaphore is created

	private int connections = 0;

	private Connections() {

	}

	public static Connections getInstance() {
		return instance;
	}

	public void Connect(Thread T1) {
		try {
			semaphore.acquire();//semaphore is acquired by the respective thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		semaphore.release();//semaphore is released by the respective thread and can be acquired by any other thread
	}

}
