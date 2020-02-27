package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";

    private static Connection connection;
    private static String query;
    private static PreparedStatement statement;
    private static BufferedReader reader;

    public static void main(String[] args) throws SQLException, IOException {

        //1. Initial Setup
        reader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");


       connection = DriverManager
               .getConnection(CONNECTION_STRING + DATABASE_NAME, properties);


        //2. Get Villains' Names
        //    getVillainsNamesAndCountOfMinions();

        //3. Get Minion Names
        //    getMinionNamesEx();

        // 4. Add Minion
        //    addMinionEx();

        //5. Change Town Names Casing
        //    changeTownNameToUpperCase();

        //6. *Remove Villain
        //    deleteVillainAndReleaseMinions();

        //7. Print All Minion Names
        //    printOrderedMinionNames();

        //8. Increase Minions Age
        //    changeNameAndAgeOfMinions();

        //9. Increase Age Stored Procedure
        //    increaseAgeProcedure();


    }
    private static void increaseAgeProcedure() throws SQLException, IOException {
        System.out.println("Enter minion id: ");
        int minionId = Integer.parseInt(reader.readLine());

        query = "CALL usp_get_older(?)";

        CallableStatement callableStatement = connection.prepareCall(query);

        callableStatement.setInt(1, minionId);
        callableStatement.execute();

    }

    private static void changeNameAndAgeOfMinions() throws IOException, SQLException {
        System.out.println("Enter id's: ");
        String[] id = reader.readLine().split("\\s+");

        for (int i = 0; i < id.length; i++) {
            int currentId = Integer.parseInt(id[i]);
            updateRow(currentId);
        }
        printResult();
    }

    private static void printResult() throws SQLException {
        query = "SELECT name, age FROM minions";
        statement = connection.prepareStatement(query);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getInt("age"));
        }

    }

    private static void updateRow(int currentId) throws SQLException {
        query = "UPDATE minions\n" +
                "SET age = age+1, name = LOWER(name)\n" +
                "WHERE id = ?";

        statement = connection.prepareStatement(query);
        statement.setInt(1, currentId);
        statement.executeUpdate();
    }

    private static void printOrderedMinionNames() throws SQLException {
        query = "SELECT name FROM minions";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<String> names = new ArrayList<>();

        while (resultSet.next()) {
            names.add(resultSet.getString("name"));
        }

        while (names.size() > 1) {
            System.out.println(names.get(0));
            System.out.println(names.get(names.size() - 1));
            names.remove(0);
            names.remove(names.size() - 1);
        }
    }

    private static void deleteVillainAndReleaseMinions() throws IOException, SQLException {
        System.out.println("Enter villain id: ");

        int id = Integer.parseInt(reader.readLine());

        if (!checkIfEntityExist(id, "villains")) {
            System.out.println("No such villain was found");
            return;
        }


        StringBuilder outputFirstLine = new StringBuilder();
        outputFirstLine.append(getEntityNameByid(id, "villains"));
        outputFirstLine.append(" was deleted");
        System.out.println(outputFirstLine.toString());
        String outputSecondLine = "" + getCountOfReleasedMinions(id);
        System.out.println(outputSecondLine + " minions released");


    }

    private static int getCountOfReleasedMinions(int id) throws SQLException {
        query = "DELETE FROM minions_villains\n" +
                "WHERE villain_id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        int count = statement.executeUpdate();
        return count;
    }

    private static void changeTownNameToUpperCase() throws IOException, SQLException {
        System.out.println("Enter country name");

        String country = reader.readLine();

        if (!checkIfEntityExistByName(country, "towns")) {
            System.out.println("No town names were affected.");
            return;
        }

        updateTownNames(country);

    }

    private static void updateTownNames(String countryName) throws SQLException {
        query = "UPDATE towns\n" +
                "SET name = UPPER(name)\n" +
                "WHERE country  = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, countryName);

        int resultSet = statement.executeUpdate();

        System.out.printf("%d town names were affected.%n", resultSet);

        printUpdatedData(countryName);

    }

    private static void printUpdatedData(String countryName) throws SQLException {
        query = "SELECT name FROM towns WHERE country = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, countryName);

        ResultSet results = statement.executeQuery();
        StringBuilder resultToPrint = new StringBuilder();
        resultToPrint.append("[");

        while (results.next()) {
            resultToPrint.append(results.getString("name") + ", ");
        }

        resultToPrint.delete(resultToPrint.length() - 2, resultToPrint.length());
        resultToPrint.append("]");
        System.out.println(resultToPrint.toString());

    }

    private static void addMinionEx() throws IOException, SQLException {
        System.out.println("Enter Minion parameter: ");
        String[] minionsParameter = reader.readLine().split("\\s+");
        String minionName = minionsParameter[0];
        int minionAge = Integer.parseInt(minionsParameter[1]);
        String minionTown = minionsParameter[2];

        System.out.println("Enter vilian name:");
        String villainName = reader.readLine();

        if (!checkIfEntityExistByName(minionTown, "towns")) {
            insertEntityInTown(minionTown);
        }
    }

    private static void insertEntityInTown(String minionTown) throws SQLException {
        query = "INSERT INTO towns (name, country) VALUES(?, ?)";

        statement = connection.prepareStatement(query);
        statement.setString(1, minionTown);
        statement.setString(2, "NULL");

        statement.execute();

    }

    private static boolean checkIfEntityExistByName(String entityName, String tableName) throws SQLException {
        query = "SELECT * FROM " + tableName + " WHERE country = ?";

        statement = connection.prepareStatement(query);
        statement.setString(1, entityName);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static void getMinionNamesEx() throws IOException, SQLException {
        System.out.println("Enter villain id:");
        int villain_id = Integer.parseInt(reader.readLine());

        if (!checkIfEntityExist(villain_id, "villains")) {
            System.out.printf("No villain with ID %d exists in the database.", villain_id);
            return;
        }
        System.out.printf("Villain: %s%n", getEntityNameByid(villain_id, "villains"));
        getMinionsAndAgeByVillainId(villain_id);
    }

    private static void getMinionsAndAgeByVillainId(int villain_id) throws SQLException {
        query = "SELECT m.name, m.age FROM minions AS m\n" +
                "JOIN minions_villains mv ON m.id = mv.minion_id\n" +
                "WHERE mv.villain_id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, villain_id);

        ResultSet resultSet = statement.executeQuery();
        int minionNumber = 0;

        while (resultSet.next()) {
            System.out.printf("%d. %s %d%n", ++minionNumber, resultSet.getString("name"), resultSet.getInt(2));
        }
    }

    private static String getEntityNameByid(int entityId, String tableName) throws SQLException {
        query = "SELECT name FROM " + tableName + " WHERE id = ?";

        statement = connection.prepareStatement(query);
        statement.setInt(1, entityId);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next() ? resultSet.getString("name") : null;
    }

    private static boolean checkIfEntityExist(int villain_id, String villains) throws SQLException {
        query = "SELECT * FROM " + villains + " WHERE id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, villain_id);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static void getVillainsNamesAndCountOfMinions() throws SQLException {
        query = "SELECT v.name, COUNT(mv.minion_id) AS count FROM villains AS v\n" +
                "JOIN minions_villains mv ON v.id = mv.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING count  > 15\n" +
                "ORDER BY count DESC";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d%n", resultSet.getString(1), resultSet.getInt(2));
        }
    }
}
