package org.example;

import org.example.view.ViewMain;

import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws SQLException {
        ViewMain.getInstance().run();
    }
}
