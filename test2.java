import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/internetofthings?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "Crazy1993!";

		try {

			Connection con = DriverManager.getConnection(url, username,
					password);
			Statement st = con.createStatement();
			Matrix m = new Matrix();
			int source, destination;
			String s[] = m.getLoc();

			for (int i = 0; i < 100000; i++) {
				do {
					source = (int) (Math.random() * s.length);
					destination = (int) (Math.random() * s.length);
				} while (source == destination);

				// System.out.println(source+","+destination);

				dijkstra d = new dijkstra(source, destination);
				String src = d.getSource();
				String dst = d.getDest();
				// System.out.println("b4accesing path");
				// String route[] = d.getPath();
				// int count = route.length;

				// System.out.println("update pack");
				// System.out.println("src="+src+"dest="+dst);

				int weight = (int) (Math.random() * 1000);
				int l = 10 + (int) (Math.random() * 90);
				int b = 10 + (int) (Math.random() * 90);
				int h = 10 + (int) (Math.random() * 90);
				String dimension = (l + "x" + b + "x" + h);

				st.executeUpdate("INSERT INTO PACKAGE_DETAIL VALUES(DEFAULT,'"
						+ src
						+ "',"
						+ "'"
						+ dst
						+ "',"
						+ weight
						+ ",'"
						+ dimension + "')");
				/*
				 * for (int i=0; i<count ; i++){ st.executeUpdate(
				 * "INSERT INTO PACKAGE_DETAIL VALUES(DEFAULT,'" +src+"'," +
				 * "'"+dst+"','"+Path[i]+"',now(),"+i+","+i+")"); }
				 */
				// System.out.println("after pack creation");
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.getMessage());
		}

	}

}
