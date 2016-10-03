import java.io.*;
import java.util.*;

public class GetDetails extends Thread {
	
	public void run() {
		while(true)
		{
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			// int i;

			System.out.println("enter tracking no");
		

			int b = Integer.parseInt(br.readLine());
			Packages p=new Packages(b);
			System.out.println("Package id : " +p.package_id);
			System.out.println("Source : "+ p.source + "  Destination : "+p.destination);
			System.out.println("Weight in lbs : "+ p.weight);
			System.out.println("Dimension in inch :"+ p.dimension);
			p.getDetails();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e);
		}
	}

}
}