package org.example.view;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewMain {

    private ActorView actorView;
    private DirectorView directorView;
    private PerformanceView performanceView;
    private RepetitionView repetitionView;
    private static ViewMain viewMain;

    private ViewMain() {
        actorView = new ActorView();
        directorView = new DirectorView();
        performanceView = new PerformanceView();
        repetitionView = new RepetitionView();
    }

    public static ViewMain getInstance() {
        if (viewMain == null) {
            viewMain = new ViewMain();
        }
        return viewMain;
    }

    public void run() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean work = true;
        while (work) {
            System.out.println("Выберете из списка, с чем хотите работать: " +
                    "\n1-актёры," +
                    " 2-режиссёры," +
                    " 3-спектакли," +
                    " 4-репетиции." +
                    "\n5-выйти.");
            System.out.println("\nВведите номер: ");
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    runActor();
                    break;
                case 2:
                    runDirector();
                    break;
                case 3:
                    runPerformance();
                    break;
                case 4:
                    runRepetition();
                case 5:
                    work = false;
                    break;
                default:
                    System.out.println("Нет такого номера");
            }
        }
    }
    public void runActor()throws SQLException {
        actorView.run();
    }
    public void runDirector()throws SQLException {
        directorView.run();
    }

    public void runPerformance()throws SQLException {
        performanceView.run();
    }

    public void runRepetition()throws SQLException {
        repetitionView.run();
    }


}
