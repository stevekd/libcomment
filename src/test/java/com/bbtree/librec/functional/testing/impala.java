package com.bbtree.librec.functional.testing;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Random;

public class impala {

    // here is an example query based on one of the Hue Beeswax sample tables

    private static final String SQL_STATEMENT = "SELECT * FROM pdw.pdw_zhs_school limit 20";
    // set the impalad host
//    ArrayList<String> list = new ArrayList<String>(); list.add(hadoop1)

    String[] strb = {"120.27.152.224", "120.27.128.16", "120.27.143.56", "120.27.153.103", "120.27.149.186"};
    Random rand = new Random();
    int num2 = rand.nextInt(strb.length);


    private static  String IMPALAD_HOST = "120.27.152.224";

    // port 21050 is the default impalad JDBC port
    private static final String IMPALAD_JDBC_PORT = "21050";

    private static final String CONNECTION_URL = "jdbc:hive2://" + IMPALAD_HOST + ':' + IMPALAD_JDBC_PORT + "/testdb;auth=noSasl";

    private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) {

        System.out.println("\n=============================================");
        System.out.println("Cloudera Impala JDBC Example");
        System.out.println("Using Connection URL: " + CONNECTION_URL);
        System.out.println("Running Query: " + SQL_STATEMENT);

        Connection con = null;

        try {

            Class.forName(JDBC_DRIVER_NAME);

            con = DriverManager.getConnection(CONNECTION_URL);

            Statement stmt = con.createStatement();

            //insert(stmt);
            //delete(stmt);

            ResultSet rs = stmt.executeQuery(SQL_STATEMENT);

            System.out.println("\n== Begin Query Results ======================");

            // print the results to the console
            while (rs.next()) {
                // the example query returns one String column
                System.out.print(rs.getString("school_id") + ":");
                System.out.println(rs.getString("school_name"));
            }

            System.out.println("== End Query Results =======================\n\n");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                // swallow
            }
        }
    }

    public static void insert(Statement stmt) {
        String sql = " INSERT INTO tab001(userid, age, city,name) VALUES (101, 22, 'bj','wanghongxiang001') ";
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


