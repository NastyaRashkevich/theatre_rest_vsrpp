package org.example.view;

import org.example.controller.DirectorController;
import org.example.entity.Actor;
import org.example.entity.Director;
import org.hibernate.service.spi.ServiceException;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DirectorView {

    private DirectorController directorController = new DirectorController();

    public void getDirectors() throws SQLException {
        List<Director> directors = directorController.getAllDirectors();
        System.out.println("Список всех режиссёров: ");
        directors.forEach(director -> System.out.println(director.toString()));
    }

    public void deleteDirector() throws SQLException {
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("Введите id режиссёра, которго хотите удалить: ");
        Long id = Long.parseLong(scanner1.next());
        try {
            directorController.deleteDirector(id);
        } catch (ServiceException e) {
            System.out.println("Режиссёр с id " + id + " удалён.");
        }
    }

    public void directorById() throws SQLException {
        Scanner scanner2 = new Scanner(System.in);

        System.out.println("Введите id режиссёра, которго хотите посмотреть: ");
        Long id = Long.parseLong(scanner2.next());  //Как работают сканнеры?
        try {   //Почему в предыдущем трай было без иф элс??
            if (directorController.getDirectorById(id) != null) {
                System.out.println(directorController.getDirectorById(id).toString());
            } else {
                System.out.println("Режиссёра с стаким id не существует.");
            }
        } catch (NullPointerException e) {  //Поч кэтч разный каждый раз?
            System.out.println("Такого номера нет, попробуйте ещё раз.");
            directorById();
        }
    }

    public void addDirector() throws SQLException {
        try {
            Scanner scanner3 = new Scanner(System.in);
            Director newDirector = new Director();

            System.out.println("Имя режиссёра: ");
            String directorName = scanner3.nextLine();  //К вопросу о сканнерах, поч здесь сразу 3?
            newDirector.setName(directorName);

            System.out.println("Телефон режиссёра: ");
            String directorTel = scanner3.nextLine();
            newDirector.setTelephone(directorTel);
            //Почему не добавляем сюда айдишник репы??
            System.out.println("Стаж режиссёра: ");
            String directorExp = scanner3.nextLine();
            newDirector.setExperience(directorExp);

            directorController.saveDirector(newDirector);
        } catch (SQLException e) {
            e.printStackTrace();  //Сделала, как в видосе, но чо было у тебя?
        }
    }

    public void updateDirector() throws SQLException {
        try {
            Scanner scanner4 = new Scanner(System.in);
            System.out.println("id режиссёра: ");
            Long id = Long.parseLong(scanner4.next());

            Director newDirector; //Избыточный инициализатор переменной (был у тебя, но я удалила поле видео )
            newDirector = directorController.getDirectorById(id);

            Scanner scanner5 = new Scanner(System.in);

            System.out.println("Новое имя: ");
            String directorName = scanner5.nextLine();
            newDirector.setName(directorName);

            System.out.println("Новый телефон: ");
            String directorTel = scanner5.nextLine();
            newDirector.setTelephone(directorTel);

            System.out.println("Новый стаж: ");
            String directorExp = scanner5.nextLine();
            newDirector.setExperience(directorExp);

            directorController.editDirector(newDirector);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() throws SQLException {
        boolean work = true;
        while (work) {
            System.out.println("\nВыберете действие из списка:" +
                    "\n1 - посмотреть всех режиссёров" +
                    "\n2 - удалить режиссёра" +
                    "\n3 - найти режиссёра по id" +
                    "\n4 - добавить нового режиссёра" +
                    "\n5 - редактировать режиссёра" +
                    "\n6 - выйти.");
            System.out.println("\nВведите номер действия: ");
            Scanner scanner6 = new Scanner(System.in);
            int number = scanner6.nextInt();
            switch (number) {
                case 1:
                    getDirectors();
                    break;
                case 2:
                    deleteDirector();
                    break;
                case 3:
                    directorById();
                    break;
                case 4:
                    addDirector();
                    break;
                case 5:
                    updateDirector();
                    break;
                case 6:
                    work = false;
                    break;
                default:
                    System.out.println("Нет тдействия под таким номером.");
            }
        }
    }

}
