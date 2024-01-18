package main.java.com.sgedts.tdp;

import main.java.com.sgedts.tdp.constant.QueryConst;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        QueryConst queryList = new QueryConst();

        System.out.println("""
                Youkoso Iman no Java Query e! Nani ni suru?
                1. Add Bank User
                2. Deposit
                3. Withdraw
                4. Transfer
                5. Get User with Biggest Transaction Value
                6. Get User Transaction Count (Both Sending and Receiving)
                7. Get Bank Cuan
                """);

        int param = scanner.nextInt();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://172.18.185.131:3306/classicmodels",
                    "idmapps",
                    "edts321"
            );

            String query = "SELECT * FROM iman_16_jan_bank_feeder_user";

            String paramString1 = "";
            int paramInt1 = 0;
            int paramInt2 = 0;
            int paramInt3 = 0;
            int paramInt4 = 0;

            switch (param) {
                case 1 -> {
                    query = queryList.createBankAccountQuery;
                    System.out.print("ENTER NAME : ");
                    paramString1 = scanner.next();
                }
                case 2 -> {
                    query = queryList.depositQuery;
                    System.out.print("ENTER USER ID : ");
                    paramInt1 = scanner.nextInt();
                    System.out.print("ENTER AMOUNT : ");
                    paramInt2 = scanner.nextInt();
                }
                case 3 -> {
                    query = queryList.withdrawQuery;
                    System.out.print("ENTER USER ID : ");
                    paramInt1 = scanner.nextInt();
                    System.out.print("ENTER AMOUNT : ");
                    paramInt2 = scanner.nextInt();
                }
                case 4 -> {
                    System.out.print("Enter Sender : ");
                    paramInt1 = scanner.nextInt();
                    System.out.print("Enter Receiver : ");
                    paramInt2 = scanner.nextInt();
                    System.out.print("Enter Amount : ");
                    paramInt3 = scanner.nextInt();
                    System.out.println("Press 1 for Single or Any Number for Multiple Value");
                    paramInt4 = scanner.nextInt();
                    if (paramInt4 == 1) query = queryList.transferQuery;
                    else {
                        query = queryList.multipleTransferQuery;
                        System.out.println("Enter Transaction Times Value (2 for 2x Transaction, 3 for 3x, and such)");
                        paramInt4 = scanner.nextInt();
                    }
                }
                case 5 -> query = queryList.getUserWithBiggestTransactionValueQuery;
                case 6 -> query = queryList.getTotalTransferReceiveCountEachUserQuery;
                case 7 -> query = queryList.getBankProfit;
            }

            if (param < 5) {
                try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                    // Set parameters for the insert query
                    if (param != 1) {
                        preparedStatement.setInt(1, paramInt1);
                        preparedStatement.setInt(2, paramInt2);
                        if (param == 4) {
                            preparedStatement.setInt(3, paramInt3);
                            if (Objects.equals(query, queryList.multipleTransferQuery)) preparedStatement.setInt(4, paramInt4);
                        }
                    }

                    else {
                        preparedStatement.setString(1, paramString1);
                        preparedStatement.setInt(2, 0);
                    }
                    preparedStatement.executeUpdate();
                    System.out.println("Table Updated");
                }

                // Execute a select query to verify the insertion
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM iman_16_jan_bank_feeder_user");

                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
                }
            }

            else {
                // Executing the query
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                // Getting metadata to determine the number of columns
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Printing column headers
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + "\t");
                }
                System.out.println();

                // Printing result set data
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(resultSet.getString(i) + "\t");
                    }
                    System.out.println();
                }
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}