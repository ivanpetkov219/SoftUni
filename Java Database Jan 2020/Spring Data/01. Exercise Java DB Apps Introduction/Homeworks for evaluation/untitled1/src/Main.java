import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


public class Main {

    private static final String dbLocation = "jdbc:mysql://127.0.0.1:3306/";
    private static final String dbName = "minions_db";

    private static Connection conn;
    private static ResultSet result;
    private static String query;
    private static PreparedStatement sqlstatement;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws SQLException, IOException {
        Properties props = new Properties();
        props.setProperty("user", "root");                //set your user here
        props.setProperty("password", "qwerty");       //set your password here

        conn = DriverManager.getConnection(dbLocation + dbName, props);

        // uncomment only method you want to test and dont forget to refresh database before that ;)

//         GetVillainsNames();        //problem two -> Correct
//         GetMinionNames();          //problem three -> Correct
//         GetMinionNamesDistinct();  //problem three with distinct names !!! I think it should be like that ,
        // but it is not mentioned in condition.
//         AddMinion();               //problem four
//         ChangeTownsName();         //problem five
//         RemoveVillains();          //problem six
//         PrintMinionNames();        //problem seven
//         IncreaseMinionsAge();      //problem eight
         IncrAgeStoredProcedure();    //problem nine

    }


    private static void GetVillainsNames() throws SQLException {
        query = "select v.`name`, count(m.minion_id) as counter from villains as v\n" +
                " join  minions_villains as m on v.id = m.villain_id\n" +
                " group by m.villain_id\n" +
                " having counter >15\n" +
                " order by counter desc;";
        sqlstatement = conn.prepareStatement(query);
        result = sqlstatement.executeQuery();

        while(result.next()) {
            System.out.printf("%s %s%n", result.getString("name"), result.getString("counter"));
        }
        conn.close();
    }


    private static void GetMinionNames() throws SQLException, IOException {
        System.out.print("Please enter villains ID: ");
        int villainsID = Integer.parseInt(reader.readLine());
        System.out.println();

        String name = checkNameById(villainsID,"villains");
        if(name.equals("none")){
            System.out.printf("No villain with ID %s exists in the database.",villainsID);
        }else {
            System.out.printf("Villain: %s%n",name);
            query = "select m.`name`,m.age from minions as m\n" +
                    "join minions_villains as mv on m.id=mv.minion_id\n" +
                    "where mv.villain_id = ?;";
            sqlstatement = conn.prepareStatement(query);
            sqlstatement.setInt(1, villainsID);

            result = sqlstatement.executeQuery();

            int numbering = 0;
            while (result.next()) {
                System.out.printf("%d. %s %d%n",++numbering, result.getString("name"),result.getInt("age"));
            }
        }
        conn.close();
    }


    private static void GetMinionNamesDistinct() throws SQLException, IOException {
        System.out.print("Please enter villains ID: ");
        int villainsID = Integer.parseInt(reader.readLine());
        System.out.println();

        String name = checkNameById(villainsID,"villains");
        if(name.equals("none")){
            System.out.printf("No villain with ID %s exists in the database.",villainsID);
        }else {
            System.out.printf("Villain: %s%n",name);
            query = "select distinct m.name,m.age from minions as m\n" +
                    "\tjoin minions_villains as mv on m.id=mv.minion_id\n" +
                    "\twhere mv.villain_id = ? ;";
            sqlstatement = conn.prepareStatement(query);
            sqlstatement.setInt(1, villainsID);

            result = sqlstatement.executeQuery();

            int numbering = 0;
            while (result.next()) {
                System.out.printf("%d. %s %d%n",++numbering, result.getString("name"),result.getInt("age"));
            }
        }
        conn.close();
    }


    private static void AddMinion() throws IOException, SQLException {
        System.out.println("Please enter minion data: ");
        String[] minionDetails = reader.readLine().trim().split("\\s+");
        System.out.println("Please inter villains data: ");
        String[] villainsDetaills = reader.readLine().trim().split("\\s+");
        String town = minionDetails[3];
        int age = Integer.parseInt(minionDetails[2]);
        String minionName = minionDetails[1];
        String villainName = villainsDetaills[1];

        if(!checkNameExistence(town,"towns")){
            query = "insert into towns(name,country) values (?,null);";
            sqlstatement = conn.prepareStatement(query);
            sqlstatement.setString(1,town);
            sqlstatement.executeUpdate();
            System.out.printf("Town %s was added to the database.%n",town);
        }
        if(!checkNameExistence(villainName,"villains")){
            query = "insert into villains(`name`,evilness_factor) values (?,'evil');";
            sqlstatement = conn.prepareStatement(query);
            sqlstatement.setString(1,villainName);
            sqlstatement.executeUpdate();
            System.out.printf("Villain %s was added to the database.%n",villainName);
        }


        query = "insert into minions(`name`,age,town_id) values (\n" +
                "   ?,\n" +
                "    ?,\n" +
                "    (select t.id from towns as t where t.`name`= ?)\n" +
                " )";
        sqlstatement = conn.prepareStatement(query);
        sqlstatement.setString(1,minionName);
        sqlstatement.setInt(2,age);
        sqlstatement.setString(3,town);
        sqlstatement.executeUpdate();
        System.out.printf("Successfully added %s to be minion of %s%n",minionName,villainName);

        query = "insert into minions_villains(minion_id, villain_id) values \n" +
                "(\n" +
                " (select id from minions where `name` = ?),\n" +
                " (select id from villains where `name` = ?)\n" +
                ");";
        sqlstatement = conn.prepareStatement(query);
        sqlstatement.setString(1,minionName);
        sqlstatement.setString(2,villainName);
        sqlstatement.executeUpdate();
    }


    private static void ChangeTownsName() throws IOException, SQLException {
        System.out.println("Please enter country name: ");
        String countryName = reader.readLine();

        int townsnumber = countTowns(countryName);
        if(townsnumber==0){
            System.out.println("No town names were affected.");
        }else{
            query = "update towns set name = upper(name) where country = ?;";
            sqlstatement = conn.prepareStatement(query);
            sqlstatement.setString(1,countryName);
            sqlstatement.executeUpdate();
            System.out.printf("%d town names were affected.%n",townsnumber);
            query = "select `name` from towns where country = ?";
            sqlstatement = conn.prepareStatement(query);
            sqlstatement.setString(1,countryName);
            result = sqlstatement.executeQuery();
            List<String> out = new ArrayList<>();
            while (result.next()){
                out.add(result.getString("name"));
            }
            System.out.println(out.toString());
        }
    }


    private static void RemoveVillains() throws IOException, SQLException {
        System.out.println("Please enter villain ID: ");
        int villainID = Integer.parseInt(reader.readLine());
        int minionsNumber =0;

        String villainName = checkNameById(villainID,"villains");

        if(villainName.equals("none")){
            System.out.println("No such villain was found");
        }else{
            query = "delete from minions_villains where villain_id = (select id from villains where name = ?);";
            sqlstatement = conn.prepareStatement(query);
            sqlstatement.setString(1,villainName);
            minionsNumber = sqlstatement.executeUpdate();

            query = "delete from villains where `name` = ?;";
            sqlstatement = conn.prepareStatement(query);
            sqlstatement.setString(1,villainName);
            sqlstatement.executeUpdate();

            System.out.printf("%s was deleted%n%d minions released%n",villainName,minionsNumber);
        }
    }


    private static void PrintMinionNames() throws SQLException {
        query = "select `name` from minions ;";
        sqlstatement = conn.prepareStatement(query);
        result = sqlstatement.executeQuery();
        List<String> names = new ArrayList<>();
        while (result.next()){
            names.add(result.getString("name"));
        }
        int n = names.size();
        for (int i = 0; i < (n/2); i++) {
            System.out.println(names.get(i));
            System.out.println(names.get(n-1-i));
        }
        if(n%2!=0){
            System.out.println(names.get(n/2));
        }
    }


    private static void IncreaseMinionsAge() throws IOException, SQLException {
        System.out.println("please enter id's: ");
        List<Integer> input = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        String minionsIDs = input.toString().replaceAll("[\\[\\]]","");
        minionsIDs = "("+minionsIDs+")";

        query = "update minions set `name` = lower(`name`), age = age+1\n" +
                "where id in"+minionsIDs;
        sqlstatement = conn.prepareStatement(query);
        sqlstatement.executeUpdate();

        query = "select `name`,age from minions";
        sqlstatement = conn.prepareStatement(query);
        result = sqlstatement.executeQuery();
        while (result.next()){
            System.out.printf("%s %d%n",result.getString("name"),result.getInt("age"));
        }
    }


    private static void IncrAgeStoredProcedure() throws IOException, SQLException {
        System.out.println("Please enter minion id: ");
        int minionID = Integer.parseInt(reader.readLine().trim());
        query = "call usp_get_older(?);";
        CallableStatement sqlcall = conn.prepareCall(query);
        sqlcall.setInt(1,minionID);
        sqlcall.executeQuery();

        query = "select `name`,age from minions where id = ?";
        sqlstatement = conn.prepareStatement(query);
        sqlstatement.setInt(1,minionID);
        result = sqlstatement.executeQuery();
        while (result.next()){
            System.out.printf("%s %d%n",result.getString("name"),result.getInt("age"));
        }
    }


    private static int countTowns(String countryName) throws SQLException {
        int count = 0;
        query = "select count(id) as counter from towns where country = ?;";
        sqlstatement = conn.prepareStatement(query);
        sqlstatement.setString(1,countryName);
        result = sqlstatement.executeQuery();
        if(result.next()){
            count = result.getInt("counter");
        }
        return count;
    }


    private static boolean checkNameExistence(String checkName,String table) throws SQLException {
        query = "select * from "+table+" where name = ?;";
        sqlstatement = conn.prepareStatement(query);
        sqlstatement.setString(1,checkName);
        result = sqlstatement.executeQuery();
        return result.next();
    }


    private static String checkNameById(int villainsID, String table) throws SQLException {
        String name = "none";
        query = "select `name` from "+table+" where id = ?;";
        sqlstatement = conn.prepareStatement(query);
        sqlstatement.setInt(1,villainsID);
        result = sqlstatement.executeQuery();
        if(result.next()){
            name = result.getString("name");
        }
        return  name;
    }


}
