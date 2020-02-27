package Demo;


import java.sql.*;
import java.util.*;

public class Main {
    private static final String CONNECTION_PATH = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "minions_db";
    private static Properties props = new Properties();
    private static Connection connection;
    private static PreparedStatement statement;
    private static String query;

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        setProps();

        connectToDB();

//        2.	Get Villains’ Names
//        getVillainsNames();

//        3.	Get Minion Names
//        getMinionNames(sc);

//        4.	Add Minion
//        addMinionExercise(sc);

//        5.	Change Town Names Casing
//        changeTownNamesToUpperCaseWithGivenCountry(sc);

//        6.	*Remove Villain
//        removeVillainExercise(sc);

//        7.	Print All Minion Names
//        printAllMinionNamesExercise();

//        8.	Increase Minions Age
//        increaseMinionsAgeExercise(sc);

//        9.	Increase Age Stored Procedure
//        increaseAgeCallingStoredProcedure(sc);
    }

    private static void connectToDB() throws SQLException {
        connection =
                DriverManager.getConnection(CONNECTION_PATH + DB_NAME, props);
    }

    private static void setProps() {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public static void getVillainsNames() throws SQLException {
        statement = connection
                .prepareStatement(
                        "SELECT `name`, COUNT(mv.minion_id) AS count\n" +
                                "FROM villains as v\n" +
                                "JOIN minions_villains AS mv\n" +
                                "ON v.id = mv.villain_id\n" +
                                "GROUP BY `name`\n" +
                                "HAVING `count` > 15\n" +
                                "ORDER BY count DESC");

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getString("count"));
        }
    }

    private static void getMinionNames(Scanner sc) throws SQLException {
        statement = connection
                .prepareStatement("SELECT  v.`name`,m.age ,m.name AS minion_name\n" +
                        "FROM villains as v\n" +
                        "JOIN minions_villains AS mv\n" +
                        "ON v.id = mv.villain_id\n" +
                        "JOIN minions AS m on mv.minion_id = m.id\n" +
                        "WHERE v.id = ?");

        int villainId = Integer.parseInt(sc.nextLine());

        statement.setInt(1, villainId);

        ResultSet rs = statement.executeQuery();

        if (!rs.next()) {
            System.out.println(String.format("No villain with ID %d exists in the database.", villainId));
        } else {
            System.out.println("Villain: " + rs.getString("name"));
            rs.beforeFirst();
        }

        int minionCount = 1;

        while (rs.next()) {
            System.out.println(
                    String.format("%d. %s %d",
                            minionCount++,
                            rs.getString("minion_name"),
                            rs.getInt("age")
                    ));
        }
    }

    private static void addMinionExercise(Scanner sc) throws SQLException {
        System.out.println("Enter input: ");

        String[] minionInfo = sc.nextLine().split("\\s+");
        String[] villainInfo = sc.nextLine().split("\\s+");

        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String townName = minionInfo[3];

        String villainName = villainInfo[1];

        if (!checkIfEntityExistByName(townName, "towns")) {
            query = "INSERT INTO towns (name, country) VALUES (?,NULL)";

            statement = connection.prepareStatement(query);
            statement.setString(1, townName);

            statement.execute();

            System.out.println(String.format(
                    "Town %s was added to the database.",
                    townName
            ));
        }
        if (!checkIfEntityExistByName(villainName, "villains")) {
            query = "INSERT INTO villains (name, evilness_factor) VALUES (?,?)";

            statement = connection.prepareStatement(query);
            statement.setString(1, villainName);
            statement.setString(2, "evil");

            statement.execute();

            System.out.println(String.format(
                    "Villain %s was added to the database.",
                    villainName
            ));
        }

        int townId = getEntityId(townName, "towns");

        insertMinionIntoTable(minionName, minionAge, townId);

        int minionId = getEntityId(minionName, "minions");
        int villainId = getEntityId(villainName, "villains");

        connectMinionWithVillain(minionId, villainId);

        System.out.println(String.format("Successfully added %s to be minion of %s", minionName, villainName));

    }

    private static void connectMinionWithVillain(int minionId, int villainId) throws SQLException {
        query = "INSERT INTO minions_villains (minion_id,villain_id) VALUES (?,?)";

        statement = connection.prepareStatement(query);
        statement.setInt(1, minionId);
        statement.setInt(2, villainId);

        statement.execute();
    }

    private static int getEntityId(String entityName, String tableName) throws SQLException {
        int id = 0;

        query = "SELECT id FROM " + tableName + " WHERE `name` = ?";

        statement = connection.prepareStatement(query);
        statement.setString(1, entityName);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            id = rs.getInt(1);
        }

        return id;
    }

    private static void insertMinionIntoTable(String minionName, int minionAge, int townId) throws SQLException {
        query = "INSERT INTO minions (name,age,town_id) VALUES (?,?,?)";

        statement = connection.prepareStatement(query);
        statement.setString(1, minionName);
        statement.setInt(2, minionAge);
        statement.setInt(3, townId);

        statement.execute();
    }

    private static boolean checkIfEntityExistByName(String townName, String table) throws SQLException {
        query = " SELECT * FROM " + table + " WHERE `name` = ?";

        statement = connection.prepareStatement(query);

        statement.setString(1, townName);

        ResultSet rs = statement.executeQuery();

        return rs.next();
    }

    private static void changeTownNamesToUpperCaseWithGivenCountry(Scanner sc) throws SQLException {
        System.out.println("Enter country name:");
        String countryName = sc.nextLine();
        ArrayList<String> towns = getTowns(countryName);

        if (towns.isEmpty()) {
            System.out.println("No town names were affected.");
        } else {
            query = "UPDATE towns\n" +
                    "SET `name` = UPPER(`name`)\n" +
                    "WHERE country = ?";

            statement = connection.prepareStatement(query);
            statement.setString(1, countryName);

            int rowsAffected = statement.executeUpdate();

            System.out.println(String.format("%d town names were affected.", rowsAffected));
            System.out.println(Arrays.toString(towns.toArray()));
        }
    }

    private static ArrayList<String> getTowns(String townName) throws SQLException {
        query = "SELECT name FROM towns WHERE country = ?";

        statement = connection.prepareStatement(query);
        statement.setString(1, townName);

        ResultSet rs = statement.executeQuery();

        ArrayList<String> towns = new ArrayList<>();

        while (rs.next()) {
            towns.add(rs.getString(1).toUpperCase());
        }

        return towns;
    }

    private static void removeVillainExercise(Scanner sc) throws SQLException {
        System.out.println("Enter villain ID:");
        int villainID = Integer.parseInt(sc.nextLine());

        ResultSet rs = getVillainInfo(villainID);

        if (rs.next()) {
            query = "DELETE FROM minions_villains WHERE villain_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, villainID);

            int minionsReleased = statement.executeUpdate();


            query = "DELETE FROM villains WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, villainID);

            statement.executeUpdate();

            System.out.println(String.format("%s was deleted", rs.getString("name")));
            System.out.println(String.format("%d minions released", minionsReleased));
        } else {
            System.out.println("No such villain was found");
        }
    }

    private static ResultSet getVillainInfo(int villainID) throws SQLException {
        query = "SELECT * FROM villains WHERE id = ?";

        statement = connection.prepareStatement(query);
        statement.setInt(1, villainID);

        return statement.executeQuery();
    }

    private static void printAllMinionNamesExercise() throws SQLException {
        query = "SELECT `name` FROM minions";

        statement = connection.prepareStatement(query);

        ResultSet rs = statement.executeQuery();

        ArrayList<String> names = new ArrayList<>();

        while (rs.next()) {
            names.add(rs.getString(1));
        }

        String[] namesArr = names.toArray(String[]::new);

        printNames(namesArr);
    }

    private static void printNames(String[] namesArr) {
        for (int i = 0; i <= namesArr.length / 2; i++) {

            String first = namesArr[i];
            String last = namesArr[namesArr.length - 1 - i];

            if(i == namesArr.length / 2) {
                if (namesArr.length % 2 != 0) {
                    System.out.println(first);
                }
                break;
            }
            System.out.println(first);
            System.out.println(last);

        }
    }

    private static void increaseMinionsAgeExercise(Scanner sc) throws SQLException {
        int[] ids = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        updateMinionsWithGivenId(ids);

        showUpdatedMinions();
    }

    private static void showUpdatedMinions() throws SQLException {
        query = "SELECT name, age FROM minions";
        statement = connection.prepareStatement(query);

        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            System.out.println(rs.getString("name") + " | " + rs.getInt("age"));
        }
    }

    private static void updateMinionsWithGivenId(int[] ids) throws SQLException {
        query = "UPDATE minions\n" +
                "SET age = age + 1, name = LOWER(name)\n" +
                "WHERE id = ?";

        for (int id : ids) {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }

    private static void increaseAgeCallingStoredProcedure(Scanner sc) throws SQLException {
        int minionId = Integer.parseInt(sc.nextLine());

        updateMinionAgeById(minionId);

        showUpdatedMinion(minionId);
    }

    private static void showUpdatedMinion(int minionId) throws SQLException {
        query = "SELECT name,age FROM minions WHERE id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1,minionId);

        ResultSet rs = statement.executeQuery();

        rs.next();

        System.out.println(String.format("Minion name: %s, Minion Age: %d",
                rs.getString("name"),
                rs.getInt("age")));
    }

    private static void updateMinionAgeById(int minionId) throws SQLException {
        query = "CALL usp_get_older(?)";

        statement = connection.prepareStatement(query);
        statement.setInt(1,minionId);

        statement.executeUpdate();
    }
}
