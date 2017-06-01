package ru.testScandJavaCafee.dao;

import org.springframework.stereotype.Repository;
import ru.testScandJavaCafee.dao.daoBase.*;
import ru.testScandJavaCafee.model.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15 on 02.05.2017.
 */
@Repository
public class ConfigurationDaoImpl implements ConfigurationDao{

    public List<Configuration> list = createListConfiguration();

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
                    {"127.0.0.1"            , ""  , "login", "password", "Postgres", "а"},  // postgres
                    {"localhost\\SQLEXPRESS", ""  , "login", "password", "MS SQL"  , "true"},  // mssql
                    {"localhost"            , "XE", "login", "password", "Oracle"  , "true"}  // oracle
            };

    private  final static int [] ports    = {3306, 5432, 1433, 1521};    // порты СУБД

    /**
     * Поцедура подключения к серверу БД
     * @param dao модуль доступа
     * @param idx идентификатор сервера СУБД
     */
    public void createConnecion (dao_base dao, final int idx) {
        // Формирование строки подключения
        dao.setURL(params[idx][HOST], params[idx][SCHEMA], ports[idx]);
        // Подключение к серверу
        dao.Connect(params[idx][LOGIN], params[idx][PWD]);
    }

    public  List<Configuration> createListConfiguration() {
        List<Configuration> list = new ArrayList<>();
        // Определение "модуля доступа к СУБД"
        dao_base dm = null;
        String query = "select * from configuration";
        // Цикл перебора параметров подключения к СУБД
        for (int i = 0; i < params.length; i++) {
            // Проверка флага активности СУБД
            if (params[i][ACTIVE].equalsIgnoreCase(String.valueOf(true))) {

                // Создание "модуля доступа" к СУБД
                switch (i) {
                    case 0: dm = new dao_mysql();  break;
                    case 1: dm = new dao_postgres(); break;
                    case 2 : dm = new dao_mssql(); break;
                    case 3 : dm = new dao_oracle(); break;
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
                        // Регистрация соответствующего драйвера
                        dm.RegisterDriverManager();
                        con = dm.getConnection();
                        // getting Statement object to execute query
                        stmt = con.createStatement();
                        // executing SELECT query
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Configuration configuration = new Configuration();
                            configuration.setId(rs.getString("id"));
                            configuration.setValue(rs.getString("value"));

                            list.add(configuration);
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
            dm = null;
        }
        return null;
    }
}
