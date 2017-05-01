package ru.testScandJavaCafee.dao;

/**
 * Created by 15 on 27.04.2017.
 */

/**
 * Модуль доступа к серверу СУБД Oracle
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.OracleConnection;

public class dao_oracle extends dao_base
{
    private  OracleConnection  connection = null;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public dao_oracle() {
        super ("oracle.jdbc.OracleDriver");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void setURL (String host, String service, int port) {
        this.url = String.format("jdbc:oracle:thin:@%s:%d:%s", host, port, service);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public Connection getConnection () {
        return (java.sql.Connection) connection;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void Connect (String login, String password) {
        super.Connect(login, password);
        try {
            connection = (OracleConnection) DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            connection = null;
        }
    }
}