package connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.barak.coupons.exception.CouponException;



public class ConnectionPool {

	private Set<Connection> connections = new HashSet<Connection>();
	private boolean close;
	public static final int MAX = 5;
	private String url = "jdbc:mysql://localhost:3306/sys?serverTimezone=Israel &user=root &password=1234";

	private static ConnectionPool instance;

	private ConnectionPool() throws SQLException {
		for (int i = 0; i < MAX; i++) {
			connections.add(DriverManager.getConnection(url));
		}
	}

	public static ConnectionPool getInstance() throws CouponException {
		try {
			if (instance == null) {
				instance = new ConnectionPool();
			}
		} catch (SQLException e) {
			throw new CouponException("The getinstance failed", e);
		}
		return instance;
	}
	//--------------------------------------------------------------------
	public synchronized Connection getConnection() throws CouponException {
		if (close) {
			throw new CouponException("The connectionPoll close");
		}
		while (connections.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Iterator<Connection> it = connections.iterator();
		Connection con = it.next();
		it.remove();
		return con;
	}
	//--------------------------------------------------------------------
	public synchronized void restoreConnection(Connection connection) {
		connections.add(connection);

		try {
			notify();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//--------------------------------------------------------------------
	public synchronized void closeAllConnections() throws CouponException {

		while (connections.size() < MAX) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (Connection connection : connections) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new CouponException("The Connections NOT close", e);
			}
		}
	}

}
