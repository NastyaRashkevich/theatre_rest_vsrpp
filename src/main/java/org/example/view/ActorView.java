package org.example.view;

import org.example.controller.ActorController;
import org.example.entity.Actor;
import org.hibernate.service.spi.ServiceException;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ActorView {

    private ActorController actorController = new ActorController();

    public void getActors() throws SQLException {
        try {
            List<Actor> actors = actorController.getAllActors();
            System.out.println("Список всех актёров: ");
            actors.forEach(actor -> System.out.println(actor.toString()));
        } catch (NullPointerException e) {  //если программа обращается или получает доступ к объекту, а ссылка на него равна нулю (null)
            System.out.println("cписок пуст.");
            run();
        }
    }

    public void deleteActor() throws SQLException {
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("Введите id актёра, которго хотите удалить: ");
        Long id = Long.parseLong(scanner1.next());
        try {
            actorController.deleteActor(id);
            System.out.println("Актёр с id " + id + " удалён.");
        } catch (ServiceException e) {
            System.out.println("Актёр с id " + id + " не найден.");
        }
    }

    public void actorById() throws SQLException {
        Scanner scanner2 = new Scanner(System.in);

        System.out.println("Введите id актёра, которго хотите посмотреть: ");
        Long id = Long.parseLong(scanner2.next());
        try {
            if (actorController.getActorById(id) != null) {
                System.out.println(actorController.getActorById(id).toString());
            } else {
                System.out.println("Актёра с стаким id не существует.");
            }
        } catch (NullPointerException e) {
            System.out.println("Неверный номер, попробуйте ещё раз.");
            actorById();
        }
    }

    public void addActor() throws SQLException {

            Scanner scanner3 = new Scanner(System.in);
            Actor newActor = new Actor();

            System.out.println("Имя актёра: ");
            String actorName = scanner3.nextLine();
            newActor.setName(actorName);

            System.out.println("Телефон актёра: ");
            String actorTel = scanner3.nextLine();
            newActor.setTelephone(actorTel);

            System.out.println("Стаж актёра: ");
            String actorExp = scanner3.nextLine();
            newActor.setExperience(actorExp);

            actorController.saveActor(newActor);

    }

    public void updateActor() throws SQLException {
        try {
            Scanner scanner4 = new Scanner(System.in);
            System.out.println("id актёра: ");
            Long id = Long.parseLong(scanner4.next());

            Actor newActor;
            newActor = actorController.getActorById(id);

            Scanner scanner5 = new Scanner(System.in);

            System.out.println("Новое имя: ");
            String actorName = scanner5.nextLine();
            newActor.setName(actorName);

            System.out.println("Новый телефон: ");
            String actorTel = scanner5.nextLine();
            newActor.setTelephone(actorTel);

            System.out.println("Новый стаж: ");
            String actorExp = scanner5.nextLine();
            newActor.setExperience(actorExp);

            actorController.editActor(newActor);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() throws SQLException {
        boolean work = true;
        while (work) {
            System.out.println("\nВыберете действие из списка:" +
                    "\n1 - посмотреть всех актёров" +
                    "\n2 - удалить актёра" +
                    "\n3 - найти актёра по id" +
                    "\n4 - добавить нового актёра" +
                    "\n5 - редактировать актёра" +
                    "\n6 - выйти.");
            System.out.println("\nВведите номер действия: ");
            Scanner scanner6 = new Scanner(System.in);
            int number = scanner6.nextInt();
            switch (number) {
                case 1:
                    getActors();
                    break;
                case 2:
                    deleteActor();
                    break;
                case 3:
                    actorById();
                    break;
                case 4:
                    addActor();
                    break;
                case 5:
                    updateActor();
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
