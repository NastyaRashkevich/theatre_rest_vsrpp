package org.example.view;

import org.example.controller.RepetitionController;
import org.example.entity.Repetition;
import org.hibernate.service.spi.ServiceException;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RepetitionView {


    private RepetitionController repetitionController = new RepetitionController();

    public void getRepetitions() throws SQLException {
        try {
            List<Repetition> repetitions = repetitionController.getAllRepetition();
            System.out.println("Список всех репетиций: ");
            repetitions.forEach(repetition -> System.out.println(repetition.toString()));
        } catch (NullPointerException e) {  //если программа обращается или получает доступ к объекту, а ссылка на него равна нулю (null)
            System.out.println("cписок пуст.");
            run();
        }

    }

    public void deleteRepetition() throws SQLException {
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("Введите id репетиции, которую хотите удалить: ");
        Long id = Long.parseLong(scanner1.next());
        try {
            repetitionController.deleteRepetition(id);
            System.out.println("Репетиция с id " + id + " удалёна.");
        } catch (ServiceException e) {
            System.out.println("Репетиция с id " + id + " не найдена.");
        }
    }

    public void repetitionById() throws SQLException {
        Scanner scanner2 = new Scanner(System.in);

        System.out.println("Введите id репетиции, которую хотите посмотреть: ");
        Long id = Long.parseLong(scanner2.next());
        try {
            if (repetitionController.getRepetitionById(id) != null) {
                System.out.println(repetitionController.getRepetitionById(id).toString());
            } else {
                System.out.println("Репетиции с стаким id не существует.");
            }
        } catch (NullPointerException e) {  //если программа обращается или получает доступ к объекту, а ссылка на него равна нулю (null)
            System.out.println("Неверный номер, попробуйте ещё раз.");
            repetitionById();
        }
    }

    public void addRepetition() throws SQLException {

        Scanner scanner3 = new Scanner(System.in);
        Repetition newRepetition = new Repetition();

        System.out.println("Дата репетиции: ");
        String repDate = scanner3.nextLine();
        newRepetition.setDate(repDate);

        System.out.println("Место репетиции: ");
        String repPlace = scanner3.nextLine();
        newRepetition.setPlace(repPlace);

        repetitionController.saveRepetition(newRepetition);

    }

    public void updateRepetition() throws SQLException {
        try {
            Scanner scanner4 = new Scanner(System.in);
            System.out.println("id репетиции: ");
            Long id = Long.parseLong(scanner4.next());

            Repetition newRepetition;
            newRepetition = repetitionController.getRepetitionById(id);

            Scanner scanner5 = new Scanner(System.in);

            System.out.println("Новая дата: ");
            String repDate = scanner5.nextLine();
            newRepetition.setDate(repDate);

            System.out.println("Новое место: ");
            String repPlace = scanner5.nextLine();
            newRepetition.setPlace(repPlace);

            repetitionController.editRepetition(newRepetition);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() throws SQLException {
        boolean work = true;
        while (work) {
            System.out.println("\nВыберете действие из списка:" +
                    "\n1 - посмотреть все репетиции" +
                    "\n2 - удалить репетицию" +
                    "\n3 - найти репетицию по id" +
                    "\n4 - добавить новую репетицию" +
                    "\n5 - редактировать репетицию" +
                    "\n6 - выйти.");
            System.out.println("\nВведите номер действия: ");
            Scanner scanner6 = new Scanner(System.in);
            int number = scanner6.nextInt();
            switch (number) {
                case 1:
                    getRepetitions();
                    break;
                case 2:
                    deleteRepetition();
                    break;
                case 3:
                    repetitionById();
                    break;
                case 4:
                    addRepetition();
                    break;
                case 5:
                    updateRepetition();
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
