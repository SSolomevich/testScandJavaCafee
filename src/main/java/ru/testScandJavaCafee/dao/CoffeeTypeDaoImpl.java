package ru.testScandJavaCafee.dao;
import ru.testScandJavaCafee.model.CoffeeType;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
/**
 * Created by 15 on 23.04.2017.
 */
public class CoffeeTypeDaoImpl {



    public  List<CoffeeType>  list = createListFromMysql(new ArrayList<>());
    // JDBC URL, username и password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/test_scand_caffe";
    private static final String url2 = "test_scand_caffe";
    private static final List<String> listUrl = Arrays.asList(
            "jdbc:mysql://localhost:3306/test_scand_caffe",
            "jdbc:postgresql://127.0.0.1:5432/test_scand_caffe"


    );
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;



    private  final  int  HOST      = 0;   //  хост  (компьютер)
    private  final  int  SCHEMA    = 1;   //  схема (база данных)
    private  final  int  LOGIN     = 2;   //  логин подключения
    private  final  int  PWD       = 3;   //  пароль подключения
    private  final  int  DBMS      = 4;   //  СУБД
    private  final  int  ACTIVE    = 5;   //  флаг активности

    private static String[][]  params    =
            {{"localhost"           , "test_scand_caffe"  , "root", "root", "MySQL"   , "true"},  // mysql
            {"127.0.0.1"            , ""  , "login", "password", "Postgres", "а"}  // postgres
//            {"localhost\\SQLEXPRESS", ""  , "login", "password", "MS SQL"  , "true"},  // mssql
//            {"localhost"            , "XE", "login", "password", "Oracle"  , "true"},  // oracle
//            {""                     , "db", ""     , ""        , "Derby"   , "true"} // derby
            };

    private  final static int [] ports    = {3306, 5432, 1433, 1521, 0};    // порты СУБД

    private  final  int  idx_postgres = 1;
    private  final  int  idx_mssql    = 2;
    private  final  int  idx_oracle   = 3;
    private  final  int  idx_derby    = 4;



    /**
     * Поцедура подключения к серверу БД
     * @param dao модуль доступа
     * @param idx идентификатор сервера СУБД
     */
    private void createConnecion (dao_base dao,  final int idx) {
        // Формирование строки подключения
        dao.setURL(params[idx][HOST], params[idx][SCHEMA], ports[idx]);
        // Подключение к серверу
        dao.Connect(params[idx][LOGIN], params[idx][PWD]);
    }

    public  List<CoffeeType> createListFromMysql(
            List<CoffeeType> list
    ) {

// Определение "модуля доступа к СУБД"
        dao_base dm = null;
        String query = "select * from coffeetype";
        // Цикл перебора параметров подключения к СУБД
        for (int i = 0; i < params.length; i++) {
            // Проверка флага активности СУБД

            if (params[i][ACTIVE].equalsIgnoreCase(String.valueOf(true))) {
//                if (params[i][ACTIVE].equalsIgnoreCase("true")) {
                // Создание "модуля доступа" к СУБД
                switch (i) {
                    case 0:

                        dm = new dao_mysql();
                        break;
//                    case 1:
//                        dm = new dao_mysql();
//                        break;
//                    case 1 : dm = new dao_mssql   (); break;
                    case 1:
                        dm = new dao_postgres();
                        break;
//                    case 3 : dm = new dao_oracle  (); break;
//                    case 4 : dm = new dao_derby   (); break;
                }
                            try {
                                // Проверка создания dm
                                if (dm != null) {
                                    // Подключение к серверу БД
                                    createConnecion(dm, i);
                                    // Вывод в консоль информации о подключении
                                    if (dm.getConnection() != null) {
                                        System.out.println(params[i][DBMS] + " ПОДКЛЮЧЕНА!!!");
                                    }

// getting Statement object to execute query
                                dm.RegisterDriverManager();
                                con = dm.getConnection();
                                stmt = con.createStatement();

// executing SELECT query
                                rs = stmt.executeQuery(query);

                                while (rs.next()) {
                                    CoffeeType coffeeType = new CoffeeType();
                                    coffeeType.setId(rs.getInt("id"));
                                    coffeeType.setType_name(rs.getString("type_name"));
                                    coffeeType.setPrice(rs.getDouble("price"));
                                    coffeeType.setDisabled(rs.getString("disabled"));
                                    list.add(coffeeType);
                                }
                                }

                            } catch (SQLException sqlEx) {
                                sqlEx.printStackTrace();
                            } finally {
//close connection ,stmt and resultset here
                                try {
                                    con.close();
                                } catch (SQLException se) { /*can't do anything */ }
                                try {
                                    stmt.close();
                                } catch (SQLException se) { /*can't do anything */ }
                                try {
                                    rs.close();
                                } catch (SQLException se) { /*can't do anything */ }
                            }
                return list;
                }
            else
            {  System.out.println (params[i][DBMS] + "НЕ ПОДКЛЮЧЕНА!!!");}
            dm = null;
            }
        return null;
    }
}


//    public static List<CoffeeType> list = Arrays.asList(
//            new CoffeeType(1, "Очень крепкий и горячий кофе.", 20, 'X'),
//            new CoffeeType(2, "Вкусный кофе со сливками.", 15, 'A'),
//            new CoffeeType(3, "Просто кофе.", 10, 'B'),
//            new CoffeeType(4, "Не важно, его всё равно не видно в меню.", 120, 'Y'));


//        public  List<CoffeeType>  list = createListFromMysql(new ArrayList<>());

//    public  List<CoffeeType>  list = test_jdbc(new ArrayList<>());
//
//
////        // JDBC URL, username и password of MySQL server
//        private static final String url = "jdbc:mysql://localhost:3306/test_scand_caffe";
//        private static final String user = "root";
//        private static final String password = "root";
//
//        // JDBC variables for opening and managing connection
//        private static Connection con;
//        private static Statement stmt;
//        private static ResultSet rs;
//
//
//
//    private  final  int  HOST      = 0;   //  хост  (компьютер)
//    private  final  int  SCHEMA    = 1;   //  схема (база данных)
//    private  final  int  LOGIN     = 2;   //  логин подключения
//    private  final  int  PWD       = 3;   //  пароль подключения
//    private  final  int  DBMS      = 4;   //  СУБД
//    private  final  int  ACTIVE    = 5;   //  флаг активности
//
//    private  String[][]  params    =
//            {{"localhost"           , ""  , "root", "root", "MySQL"   , "true"},  // mysql
//            {"localhost\\SQLEXPRESS", ""  , "login", "password", "MS SQL"  , "true"},  // mssql
//            {"127.0.0.1"            , ""  , "login", "password", "Postgres", "true"},  // postgres
//            {"localhost"            , "XE", "login", "password", "Oracle"  , "true"},  // oracle
//            {""                     , "db", ""     , ""        , "Derby"   , "true"}}; // derby
//
//    private  final  int [] ports    = {3306, 1433, 5432, 1521, 0};                 // порты СУБД
//
//
//    private  final  int  idx_mssql    = 1;
//    private  final  int  idx_postgres = 2;
//    private  final  int  idx_oracle   = 3;
//    private  final  int  idx_derby    = 4;
//
//
//
//    /**
//     * Поцедура подключения к серверу БД
//     * @param dao модуль доступа
//     * @param idx идентификатор сервера СУБД
//     */
//    private void createConnecion (dao_base dao, final int idx) {
//        // Формирование строки подключения
//
////        dao.setURL(params[idx][HOST], params[idx][SCHEMA], ports[idx]);
//
//        dao.url="jdbc:mysql://localhost:3306/test_scand_caffe";
//        // Подключение к серверу
//        dao.Connect(params[idx][LOGIN], params[idx][PWD]);
//
//    }
//
//
//
//
//
//    public List<CoffeeType> test_jdbc(List<CoffeeType> list)
//    {
//        // Определение "модуля доступа к СУБД"
//        dao_base dm = null;
//        // Цикл перебора параметров подключения к СУБД
//        for (int i = 0; i < params.length; i++) {
//            // Проверка флага активности СУБД
//            if (params[i][ACTIVE].equalsIgnoreCase(String.valueOf(true))) {
//                // Создание "модуля доступа" к СУБД
//                switch (i) {
//                    case 0 : dm = new dao_mysql   (); break;
////                    case 1 : dm = new dao_mssql   (); break;
////                    case 2 : dm = new dao_postgres(); break;
////                    case 3 : dm = new dao_oracle  (); break;
////                    case 4 : dm = new dao_derby   (); break;
//                }
//            }
//            // Проверка создания dm
//            if (dm != null) {
//                // Подключение к серверу БД
//                createConnecion (dm, i);
//                // Вывод в консоль информации о подключении
//                if (dm.getConnection() != null) {
//                    System.out.println ( "OK");
//                    // Создание схемы и таблицы
//                    createListFromMysql(dm, i, list);
//
//                    // Отключение от сервера БД
//                    dm.Disconnect(dm.getConnection());
//                }
//                else System.out.println ( "Error");
//            }
//            dm = null;
//        }
//        return list;
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        public  List<CoffeeType> createListFromMysql(dao_base d, int i,
//                List<CoffeeType> list
//        ) {
//            createConnecion (d, i);
//            String query = "select * from coffeetype";
//
//            try {
//// opening database connection to MySQL server
//
////                DriverManager.registerDriver(DB.getActualDriver(config.getDbType));
////
////                DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
//                con = DriverManager.getConnection(d.url, user, password);
////
////// getting Statement object to execute query
////                stmt = con.createStatement();
////
////// executing SELECT query
////                rs = stmt.executeQuery(query);
//
//
//
//
//
//// getting Statement object to execute query
//                    stmt = con.createStatement();
//
//// executing SELECT query
//                    rs = stmt.executeQuery(query);
//
//
//
//                    while (rs.next()) {
//                        CoffeeType coffeeType = new CoffeeType();
//
//                        coffeeType.setId(rs.getInt("id"));
//                        coffeeType.setType_name(rs.getString("type_name"));
//                        coffeeType.setPrice(rs.getDouble("price"));
//                        coffeeType.setDisabled(rs.getString("disabled"));
//
//
//                        list.add(coffeeType);
//
//                    }
//
//            } catch (SQLException sqlEx) {
//                sqlEx.printStackTrace();
//            } finally {
////close connection ,stmt and resultset here
//                try {
//                    con.close();
//                } catch (SQLException se) { /*can't do anything */ }
//                try {
//                    stmt.close();
//                } catch (SQLException se) { /*can't do anything */ }
//                try {
//                    rs.close();
//                } catch (SQLException se) { /*can't do anything */ }
//            }
//
//            return list;
//
//
//
//        }


