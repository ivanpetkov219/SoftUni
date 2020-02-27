import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Main {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";
    private static Connection connection;
    private static String query;
    private static PreparedStatement statement;

    public static void main(String[] args) throws SQLException, IOException {

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "qwerty");

        connection = DriverManager.getConnection(CONNECTION_STRING + DATABASE_NAME, properties);

        //2.Get Villains’ Names
        //getVillainsNamesEx();

        //3.	Get Minion Names
        //getMinionNamesEx();

        //4.	Add Minion
        //addMinionExercise();

        //5.	Change Town Names Casing
        //changeTownNamesCasingExc();

        //6.	*Remove Villain
        //removeVillainExc();

        //7.	Print All Minion Names
        //printAllMinionNamesExc();

        //8.	Increase Minions Age
        //increaseMinionsAgeExc();

        //9.	Increase Age Stored Procedure
        //increaseAgeStoredProcedureExc();

    }

    private static void increaseAgeStoredProcedureExc() throws IOException, SQLException {
        System.out.println("Enter minion ID:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());
        query = "CALL usp_get_older(?)";
        CallableStatement callableStatement = connection.prepareCall(query);

        callableStatement.setInt(1, id);
        callableStatement.execute();
    }

    private static void increaseMinionsAgeExc() throws IOException, SQLException {
        System.out.println("Enter minions IDs separated by space:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] minionsIds = reader.readLine().split("\\s+");

        inceaseAges(minionsIds);

        makeNamesToLowerCase(minionsIds);

        printNamesAndAgesOfAllMinions();
    }

    private static void printNamesAndAgesOfAllMinions() throws SQLException {
        query = "select name, age from minions";
        statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d%n", resultSet.getString(1), resultSet.getInt(2));
        }
    }

    private static void makeNamesToLowerCase(String[] minionsIds) throws SQLException {
        for (String minionId : minionsIds) {
            int id = Integer.parseInt(minionId);
            query = "UPDATE minions\n" +
                    "SET name = lower(name)\n" +
                    "WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        }
    }

    private static void inceaseAges(String[] minionsIds) throws SQLException {
        for (String minionId : minionsIds) {
            int id = Integer.parseInt(minionId);
            query = "UPDATE minions\n" +
                    "SET age = age + 1\n" +
                    "WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        }
    }

    private static void printAllMinionNamesExc() throws SQLException {
        List<String> minionNames = new ArrayList<>();
        query = "Select name from minions";
        statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            minionNames.add(resultSet.getString(1));
        }

        for (int i = 0; i < minionNames.size() / 2; i++) {
            System.out.println(minionNames.get(i));
            System.out.println(minionNames.get(minionNames.size() - i - 1));
        }
    }

    private static void removeVillainExc() throws IOException, SQLException {
        System.out.println("Enter villain ID:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int villainId = Integer.parseInt(reader.readLine());

        getAffectedVillain(villainId);
    }

    private static void getAffectedVillain(int villainId) throws SQLException {
        query = "select name from villains where id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, villainId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {

            int minionsCount = getVillainsMinionsCount(villainId);

            deleteVillain(villainId);

            System.out.println(resultSet.getString(1) + " was deleted");
            System.out.println(minionsCount + " minions released");
        } else {
            System.out.println("No such villain was found");
        }
    }

    private static void deleteVillain(int villainId) throws SQLException {

        query = "SET FOREIGN_KEY_CHECKS=OFF";
        statement = connection.prepareStatement(query);
        statement.execute();


        query = "delete from villains where id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, villainId);

        statement.execute();

        query = "delete from minions_villains where villain_id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, villainId);

        statement.execute();

        query = "SET FOREIGN_KEY_CHECKS=ON";
        statement = connection.prepareStatement(query);
        statement.execute();
    }

    private static int getVillainsMinionsCount(int villainId) throws SQLException {
        query = "select count(*) from minions_villains\n" +
                "where villain_id = ?\n" +
                "GROUP BY villain_id";
        statement = connection.prepareStatement(query);
        statement.setInt(1, villainId);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next() ? resultSet.getInt(1) : null;
    }

    private static void changeTownNamesCasingExc() throws IOException, SQLException {
        System.out.println("Enter country name:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String countryName = reader.readLine();

        getCountOfAffectedTowns(countryName);
    }

    private static void printAffectedTowns(String countryName) throws SQLException {
        query = "select name from towns where country = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, countryName);

        ResultSet resultSet = statement.executeQuery();

        List<String> affectedTowns = new ArrayList<>();

        while (resultSet.next()) {
            affectedTowns.add(resultSet.getString(1));
        }
        System.out.println("[" + String.join(", ", affectedTowns) + "]");
    }

    private static void setNamesToUpperCase(String countryName) throws SQLException {
        query = "update towns\n" +
                "set name = upper(name)\n" +
                "where country = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, countryName);

        statement.execute();
    }

    private static void getCountOfAffectedTowns(String countryName) throws SQLException {
        query = "SELECT COUNT(*) FROM towns\n" +
                "where country = ?\n" +
                "GROUP BY country;";
        statement = connection.prepareStatement(query);
        statement.setString(1, countryName);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {

            System.out.println(resultSet.getInt(1) + " town names were affected.");

            setNamesToUpperCase(countryName);

            printAffectedTowns(countryName);
        } else {
            System.out.println("No town names were affected.");
        }
    }

    private static void addMinionExercise() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter minion details:");
        String[] minionInput = reader.readLine().split("\\s+");
        String minionName = minionInput[0];
        int minionAge = Integer.parseInt(minionInput[1]);
        String minionTown = minionInput[2];

        System.out.println("Enter villain name:");
        String villainName = reader.readLine();

        if (!checkIfEntityExistsByName(minionTown, "towns")) {
            addTown(minionTown);
            System.out.printf("Town %s was added to the database.%n", minionTown);
        }
        if (!checkIfEntityExistsByName(villainName, "villains")) {
            addVillain(villainName);
            System.out.printf("Villain %s was added to the database.%n", villainName);
        }

        addMinion(minionName, minionAge, minionTown);

        setMinionToBeServant(minionName, villainName);
    }

    private static void setMinionToBeServant(String minionName, String villainName) throws SQLException {
        int minionId = getEntityIdByName(minionName, "minions");
        int villainId = getEntityIdByName(villainName, "villains");

        addMinionToMappingTable(minionId, villainId);

        System.out.printf("Successfully added %s to be minion of %s", minionName, villainName);
    }

    private static void addMinionToMappingTable(int minionId, int villainId) throws SQLException {
        query = "INSERT INTO minions_villains (minion_id, villain_id) value (?, ?)";
        statement = connection.prepareStatement(query);
        statement.setInt(1, minionId);
        statement.setInt(2, villainId);

        statement.execute();
    }

    private static int getEntityIdByName(String name, String tableName) throws SQLException {
        query = "select id from " + tableName + " where name = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next() ? resultSet.getInt(1) : null;
    }

    private static void addMinion(String minionName, int minionAge, String minionTown) throws SQLException {
        query = "INSERT INTO minions(name, age) value(?, ?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, minionName);
        statement.setInt(2, minionAge);
        //statement.setInt(3, getEntityIdByName(minionTown, "towns"));

        statement.execute();
    }

    private static void addVillain(String villainName) throws SQLException {
        query = "INSERT INTO villains (name, evilness_factor) value (?, ?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, villainName);
        statement.setString(2, "evil");

        statement.execute();
    }

    private static void addTown(String minionTown) throws SQLException {
        query = "INSERT INTO towns (name, country) value (?, ?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, minionTown);
        statement.setString(2, "Nyama takava dyrjava");

        statement.execute();
    }

    private static boolean checkIfEntityExistsByName(String name, String tableName) throws SQLException {
        query = "select name from " + tableName + " where name = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static void getMinionNamesEx() throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter villain id: ");
        int villaintID = Integer.parseInt(reader.readLine());

        query = "select v.name, m.name, m.age from minions as m\n" +
                "JOIN minions_villains mv on m.id = mv.minion_id\n" +
                "JOIN villains v on mv.villain_id = v.id\n" +
                "where v.id = " + villaintID;
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        if (checkIfEntityExistsById(villaintID, "villains")) {
            System.out.printf("Villain: %s%n", getEntityNameByID(villaintID, "villains"));
            getMinionsNamesAndAgeByVillainId(villaintID);
        } else {
            System.out.printf("No villain with ID %d exists in the database.", villaintID);
        }
    }

    private static void getMinionsNamesAndAgeByVillainId(int villaintID) throws SQLException {
        query = "select name, age from minions as m\n" +
                "join minions_villains mv on m.id = mv.minion_id\n" +
                "where mv.villain_id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, villaintID);

        ResultSet resultSet = statement.executeQuery();

        int counter = 1;
        while (resultSet.next()) {
            System.out.printf("%d. %s %d%n", counter++, resultSet.getString(1), resultSet.getInt(2));
        }
    }

    private static String getEntityNameByID(int id, String tableName) throws SQLException {
        query = "select name from " + tableName + " where id = ?";

        statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? resultSet.getString(1) : null;
    }

    private static boolean checkIfEntityExistsById(int id, String tableName) throws SQLException {
        query = "select * from " + tableName + " where id = ?";

        statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static void getVillainsNamesEx() throws SQLException {

        query = "select v.name, count(mv.minion_id) as 'count'\n" +
                "FROM villains as v\n" +
                "JOIN minions_villains mv on v.id = mv.villain_id\n" +
                "GROUP BY v.id\n" +
                "HAVING count > 15\n" +
                "order by count DESC";

        statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d%n", resultSet.getString("name"), resultSet.getInt("count"));
        }
    }
}
