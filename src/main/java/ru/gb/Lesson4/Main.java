package ru.gb.Lesson4;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DbHandler dbHandler = new DbHandler();
        dbHandler.conn();
        dbHandler.createDB();
        dbHandler.writeDB();
//        dbHandler.OneRequestDB();
//        dbHandler.TwoRequestDB();
//        dbHandler.ThreeRequestDB();
        dbHandler.FourRequestDB();
        dbHandler.closeDB();
    }
}
