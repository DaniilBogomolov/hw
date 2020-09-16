package application;

import models.UserInfo;
import services.AbstractService;
import services.InsuranceService;
import services.PassportService;
import services.PublishService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

import static java.util.Map.entry;

public class Main {

    private static PublishService publishService;
    private static final Map<String, AbstractService> services =
            Map.ofEntries(
                    entry("passport", new PassportService()),
                    entry("insurance", new InsuranceService())
            );

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            UserInfo info = null;
            String input;
            System.out.println("Для входа введите информацию о вас в следующем формате:\n" +
                    "Имя_Фамилия_Возраст_Номер паспорта_Дата выдачи паспорта");
            while (!(input = in.readLine()).equals("")) {
                if (input.equals("help")) {
                    System.out.println("send - Отправить запрос на генерацию заявления");
                    System.out.println("getAll - Получить все заявления");
                    System.out.println("get: [OPTION] - Получить определенный тип заявления");
                    System.out.println("Доступные сервисы: insurance, passport");
                    System.out.println("quit - Выйти");
                } else if (input.equals("getAll")) {
                    if (info != null) {
                        UserInfo finalInfo = info;
                        System.out.println("Начало загрузки файлов");
                        services.values().forEach(service -> service.getForUser(finalInfo));
                        System.out.println("Загрузка завершена");
                    } else {
                        System.out.println("Пожалуйста, заполните информацию о Вас");
                        System.out.println("Для входа введите информацию о вас в следующем формате:\n" +
                                "Имя_Фамилия_Возраст_Номер паспорта_Дата выдачи паспорта");
                    }
                } else if (input.startsWith("get: ")) {
                    if (info == null) {
                        System.out.println("Пожалуйста, заполните информацию о Вас");
                        System.out.println("Для входа введите информацию о вас в следующем формате:\n" +
                                "Имя_Фамилия_Возраст_Номер паспорта_Дата выдачи паспорта");
                    } else {
                        String[] services = input.substring(5).split(" ");
                        UserInfo finalInfo1 = info;
                        System.out.println("Начало загрузки файлов");
                        Arrays.stream(services).forEach(string -> {
                            AbstractService possibleService = Main.services.get(string);
                            if (possibleService != null)
                                possibleService.getForUser(finalInfo1);
                            else
                                System.out.println("Неизвестный сервис: " + string);
                        });
                        System.out.println("Загрузка завершена");
                    }
                } else if (input.equals("quit")) {
                    System.exit(0);
                } else if (input.equals("send")) {
                    if (info != null) {
                        if (publishService == null)
                            publishService = new PublishService(info);
                        publishService.publishAll();
                        System.out.println("Заявление успешно отправлено на формирование");
                    } else {
                        System.out.println("Пожалуйста, заполните информацию о Вас");
                        System.out.println("Для входа введите информацию о вас в следующем формате:\n" +
                                "Имя_Фамилия_Возраст_Номер паспорта_Дата выдачи паспорта");
                    }
                } else {
                    String[] inputData = input.split("_");
                    if (inputData.length != 5) {
                        System.out.println("Неизвестная команда, введите help для получения списка команд");
                    } else {
                        info = UserInfo.builder()
                                .firstName(inputData[0])
                                .lastName(inputData[1])
                                .age(Integer.parseInt(inputData[2]))
                                .passport(inputData[3])
                                .passportDate(inputData[4])
                                .build();
                        System.out.println("Вход успешно выполнен");
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
