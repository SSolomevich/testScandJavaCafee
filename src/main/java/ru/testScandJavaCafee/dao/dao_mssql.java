package ru.testScandJavaCafee.dao;

/**
 * Created by 15 on 29.04.2017.
 */
/**
 * Модуль доступа к серверу СУБД MS SQL Server
 */

import java.sql.SQLException;
import java.sql.DriverManager;

import java.sql.Connection;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;

public class dao_mssql extends dao_base
{
    private  SQLServerConnection  connection  = null;

    private  final  String   DATABASE_CREATE = "CREATE DATABASE %s ";
    private  final  String   DROP_DATABASE   = "DROP DATABASE %s"   ;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public dao_mssql(){
        super ("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    };
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void setURL (String host, String database, int port) {
        if (database.length() > 0)
            this.url = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;", host, port, database);
        else
            this.url = String.format("jdbc:sqlserver://%s:%d;", host, port);;
    };
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public Connection getConnection (){
        return (java.sql.Connection) connection;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void Connect (String login, String password) {
        super.Connect (login, password);
        try {
            connection = (SQLServerConnection) DriverManager.getConnection(this.url, properties);
        } catch (SQLException e) {
            connection = null;
        }
    };
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public boolean createSchema(final String schema) {
        return execSQL (String.format(DATABASE_CREATE, schema));
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public boolean dropSchema(final String schema) {
        return execSQL (String.format(DROP_DATABASE, schema));
    }
}