package ru.testScandJavaCafee.dao;

/**
 * Created by 15 on 27.04.2017.
 */
/**
 * Базовый класс модуля доступа DAO к объектам БД
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class dao_base {
    protected  String      driver     = null;  // драйвер JDBC
    protected  String      url        = null;  // строка подключения

    protected  Properties  properties = null;  // свойства подключения объекта Connection
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public dao_base(String driver) {
        this.driver = driver;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Процедура регистрации драйвера JDBC
     */
    protected void RegisterDriverManager() {
        try {
            Class.forName(driver).newInstance();
        } catch (InstantiationException e) {e.printStackTrace();
        } catch (IllegalAccessException e) {e.printStackTrace();
        } catch (ClassNotFoundException e) {e.printStackTrace();}
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Процедура определения строки подключения URL к серверу БД
     * @param host - имя компьютера
     * @param database - наименование БД (может быть пустой строкой)
     * @param port - порт сервера
     */
    public abstract void setURL (String host, String database, int port);
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Функция получения объекта подключения
     * @return Connection - объект подключения
     */
    public abstract Connection getConnection ();
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Процедура регистрации драйвера подключения к серверу СУБД JDBC и определения свойств
     * @param login - логин подключения
     * @param password - пароль подключения
     */
    public void Connect (String login, String password) {
        // Регистрация драйвера
        RegisterDriverManager();

        // Определение свойств подключения Connection
        properties = new Properties();
        properties.setProperty("password"         , password);
        properties.setProperty("user"             , login   );
        properties.setProperty("useUnicode"       , "true"  );
        properties.setProperty("characterEncoding", "utf8"  );
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Процедура отключения от сервера БД
     * @param connection объект подключения
     */
    public void Disconnect (Connection connection) {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {}
    };
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

     /**
     * Функция выполнения SQL-запроса
     * @param sql текст запроса
     * @return результат выполнения запроса
     */
    public boolean execSQL (final String sql) {
        boolean result = false;
        try {
            if (getConnection() != null) {
                Statement statement = getConnection().createStatement();
                statement.execute(sql);
                statement.close();
                statement = null;
                result = true;
            }
        } catch (SQLException e) {
            System.err.println ("SQLException : code = " + String.valueOf(e.getErrorCode()) +
                    " - " + e.getMessage());
            System.err.println ("\tSQL : " + sql);

        }
        return result;
    }
}