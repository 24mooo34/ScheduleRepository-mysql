/*--------------------------------------------------------------------------------------------------------
 * DBコネクションを提供するクラス
 --------------------------------------------------------------------------------------------------------*/
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {	
	//DB接続用フィールド
	//mysqlを設定する、libにドライバー入れる
	private static String driver = "com.mysql.cj.jdbc.Driver"; //ドライバーのバージョン8.xなら　com.mysql.cj.jdbc.driver
		//private static String url = "jdbc:mySQL://localhost/Schedule";//ローカルとの接続
	private static String url = "jdbc:mySQL://database-01-instance-1.ctcb5xn3a4zt.ap-northeast-1.rds.amazonaws.com/Schedule"; //adminのホストは%になっているので、このままでは無理かもしれません。
	private static String user = "admin";
	private static String pass = "hyst0512";
	//DBコネクション
	private static Connection con = null;
	
	/* * DB接続 * */
	public static Connection connection() {	
		try {
			//JDBCドライバー読み込み
			Class.forName( DBconnection.driver );
			System.out.println("JDBCok");
			//コネクションを取得する（引数：URL,ユーザID,パスワード）
			DBconnection.con = DriverManager.getConnection( DBconnection.url, DBconnection.user, DBconnection.pass );	
    				
			System.out.println("接続ok");
    
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
		
	/* * DB切断 * */
	public static void close(Connection con) {
		//データベースが接続されている場合
		try {
			if (con != null) {
				//接続を切断
				con.close();
			}
			System.out.println("切断ok");
					
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}	
	
}
