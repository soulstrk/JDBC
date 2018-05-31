package com.soulstrk.JYexer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
	static String rank;
	static String song;
	static String artist; 
	static String album;
	static String likeCount;
	
	public static void inputDb() {
		Main.rank = JOptionPane.showInputDialog("순위를 입력하세요");
		Main.song = JOptionPane.showInputDialog("노래이름을 입력하세요");
		Main.artist = JOptionPane.showInputDialog("가수를 입력하세요");
		Main.album = JOptionPane.showInputDialog("앨범명을 입력하세요");
		Main.likeCount = JOptionPane.showInputDialog("좋아요 숫자를 입력하세요");
	}
	
	public static void main(String[] args) throws SQLException {
		DBtest db = new DBtest();
		boolean flag = true;
		String sql;
				
		do {
		inputDb();
		
		sql = "INSERT INTO musicinfo(rank,song_TITLE,artist,album,likeCount)VALUES("+rank+","+"'"+song+"','"+artist+"','"+album+"',"+likeCount+")";

		String num = JOptionPane.showInputDialog("중지>0");
		if(num.equals("0")) {
			flag = false;
		}
		
		
		System.out.println(sql);
		
		int cnt = db.st.executeUpdate(sql);
		System.out.println(cnt>0?"등록성공":"등록실패");
		}while(flag);
		
		sql = "SELECT * FROM musicinfo";
		db.rs = db.st.executeQuery(sql);
		while(db.rs.next()){
			int testCount = db.rs.getInt("idMusicInfo");
			int wer=db.rs.getInt("rank");
			System.out.println(testCount+"+"+wer);
		}
		db.rs.close(); db.st.close(); db.conn.close();
	}
	
}

	class DBtest {

		String url = "jdbc:mysql://localhost:3306/music?serverTimezone=UTC";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		public DBtest() {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				conn = (Connection) DriverManager.getConnection(url, "root", "kged01");
				st = ((java.sql.Connection) conn).createStatement();

				System.out.println("성공!");

			} catch (Exception e) {
				System.out.println("커넥트 연결실패" + e.getMessage());
			}
		}
	}

