import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/internetofthings?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "Crazy1993!";


		
		try{
			Connection con = DriverManager.getConnection(url, username,
					password);
			Statement st = con.createStatement();
			Packages p = new Packages(1);
			ResultSet rs = st
					.executeQuery("SELECT route_no FROM PACKAGE_STATUS WHERE PACKAGE_ID ="
							+ p.package_id+ " AND status=1 ");
			
			if (rs.next()){
				int routeNo=rs.getInt(1);
				System.out.println(routeNo);
				st.executeUpdate("UPDATE PACKAGE_STATUS SET status = 0 WHERE route_no="+routeNo);
				System.out.println("1st stat");
				
				routeNo++;
				st.executeUpdate("UPDATE PACKAGE_STATUS SET package_time=now(), status=1 WHERE route_no = "+routeNo );
				System.out.println("inside next");
			}

			else{
				System.out.println(p.path[0]);
				System.out.println(p.path.length);
				for (int i = 0; i < p.path.length; i++) {
					System.out.println("inside for");
					if (i == 0) {
						System.out.println("i=0");
						st.executeUpdate("INSERT INTO PACKAGE_STATUS VALUES("
								+ p.package_id + ",'" + p.path[i] + "',now()," + 1
								+ "," + i + ")");
					} else {
						System.out.println("i>0");
						st.executeUpdate("INSERT INTO PACKAGE_STATUS VALUES("
								+ p.package_id + ",'" + p.path[i] + "','00:00:00',"
								+ 0 + "," + i + ")");
					}
				}
			}
			System.out.println("Complete");
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e);
		}
		/*
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			// int i;

			System.out.println("enter tracking no");
		

			int b = Integer.parseInt(br.readLine());
			Packages p=new Packages(b);
			p.getDetails();

		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e);
		}
		*/
	}
}
