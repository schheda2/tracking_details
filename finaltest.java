public class finaltest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new UpdatePackage();	//updates the packages, i.e send them ahead towards destination
		Thread t2 = new GetDetails();		// takes package_id from the user and prints the details
		t1.start();
		t2.start();
		while (true)
		{
		Connections.getInstance().Connect(t1);
	
		Connections.getInstance().Connect(t2);

	}
	}
}
