package org.example.view;

import org.example.controller.DirectorController;
import org.example.controller.PerformanceController;
import org.example.entity.Performance;
import org.hibernate.service.spi.ServiceException;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PerformanceView {


    private PerformanceController performanceController = new PerformanceController();

    public void getPerformances() throws SQLException {
        try {
            List<Performance> performances = performanceController.getAllPerformances();
            System.out.println("Список всех спектаклей: ");
            performances.forEach(performance -> System.out.println(performance.toString()));
        } catch (NullPointerException e) {  //если программа обращается или получает доступ к объекту, а ссылка на него равна нулю (null)
            System.out.println("cписок пуст.");
            run();
        }
    }

    public void deletePerformance() throws SQLException {
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("Введите id сектакля, которго хотите удалить: ");
        Long id = Long.parseLong(scanner1.next());
        try {
            performanceController.deletePerformance(id);
            System.out.println("Спектакль с id " + id + " удалён.");
        } catch (ServiceException e) {
            System.out.println("Спектакль с id " + id + " не найден.");
        }
    }

    public void performanceById() throws SQLException {
        Scanner scanner2 = new Scanner(System.in);

        System.out.println("Введите id спектакля, которго хотите посмотреть: ");
        Long id = Long.parseLong(scanner2.next());
        try {
            if (performanceController.getPerformanceById(id) != null) {
                System.out.println(performanceController.getPerformanceById(id).toString());
            } else {
                System.out.println("Спектакля с стаким id не существует.");
            }
        } catch (NullPointerException e) {  //если программа обращается или получает доступ к объекту, а ссылка на него равна нулю (null)
            System.out.println("Неверный номер, попробуйте ещё раз.");
            performanceById();
        }
    }

    public void addPerformance() throws SQLException {

        Scanner scanner3 = new Scanner(System.in);
        Performance newPerformance = new Performance();

        System.out.println("Название спектакля: ");
        String perfName = scanner3.nextLine();
        newPerformance.setName(perfName);

        System.out.println("Дата спектакля: ");
        String perfDate = scanner3.nextLine();
        newPerformance.setDate(perfDate);

        performanceController.savePerformance(newPerformance);

    }

    public void updatePerformance() throws SQLException {
        try {
            Scanner scanner4 = new Scanner(System.in);
            System.out.println("id спектакля: ");
            Long id = Long.parseLong(scanner4.next());

            Performance newPerformance;
            newPerformance = performanceController.getPerformanceById(id);

            Scanner scanner5 = new Scanner(System.in);

            System.out.println("Новое название: ");
            String perfName = scanner5.nextLine();
            newPerformance.setName(perfName);

            System.out.println("Новая дата: ");
            String perfDate = scanner5.nextLine();
            newPerformance.setDate(perfDate);

            performanceController.editPerformance(newPerformance);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() throws SQLException {
        boolean work = true;
        while (work) {
            System.out.println("\nВыберете действие из списка:" +
                    "\n1 - посмотреть все спектакли" +
                    "\n2 - удалить спектакль" +
                    "\n3 - найти спектакль по id" +
                    "\n4 - добавить новый спектакль" +
                    "\n5 - редактировать спектакль" +
                    "\n6 - выйти.");
            System.out.println("\nВведите номер действия: ");
            Scanner scanner6 = new Scanner(System.in);
            int number = scanner6.nextInt();
            switch (number) {
                case 1:
                    getPerformances();
                    break;
                case 2:
                    deletePerformance();
                    break;
                case 3:
                    performanceById();
                    break;
                case 4:
                    addPerformance();
                    break;
                case 5:
                    updatePerformance();
                    break;
                case 6:
                    work = false;
                    break;
                default:
                    System.out.println("Нет действия под таким номером.");
            }
        }
    }
}
