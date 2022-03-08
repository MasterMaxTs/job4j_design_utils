package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static void main(String[] args) {
        TODOApp app = new TODOApp();
        SimpleMenu menu = new SimpleMenu();
        Printer printer = new Printer();
        app.init(menu, printer);
    }

    public void init(SimpleMenu menu, Printer printer) {
        int input;
        while (true) {
            showMenu();
            Scanner sc = getScanner();
            System.out.println("Введите номер: ");
            input = validate(sc);
            if (input == 4) {
                break;
            }
            switch (input) {
                case 1:
                    showInputInfo();
                    inputTasks(menu);
                    break;
                case 2:
                    outputSubtasks(menu);
                    break;
                default:
                    System.out.println(printer.print(menu));
            }
        }
        System.out.println("Успешный выход из программы!");
    }

    private int validate(Scanner scanner) {
        List<Integer> inputs = List.of(1, 2, 3, 4);
        int input = scanner.nextInt();
        while (true) {
            if (inputs.contains(input)) {
                return input;
            }
            System.out.println("Введен некорректный номер! Введите номер [1-4]");
            Scanner sc = getScanner();
            input = sc.nextInt();
        }
    }

    private Scanner getScanner() {
        return new Scanner(System.in);
    }

    private void inputTasks(SimpleMenu menu) {
        String task;
        while (true) {
            Scanner scanner = getScanner();
            System.out.println("Введите названия задачи:");
            task = scanner.nextLine();
            if (task.equalsIgnoreCase("Выход")) {
                break;
            }
            System.out.println("Введите список подзадач "
                    + "через ;");
            scanner = getScanner();
            String subtask = scanner.nextLine();
            if (subtask.equalsIgnoreCase("Выход")) {
                menu.add(task, task, STUB_ACTION);
                continue;
            }
            String[] subtasks = subtask.split(";");
            for (String st
                    : subtasks) {
                if (menu.select(task).isEmpty()) {
                    menu.add(task, task, STUB_ACTION);
                }
                menu.add(task, st, STUB_ACTION);
            }
        }
    }

    private void outputSubtasks(SimpleMenu menu) {
        String select;
        Scanner scanner = getScanner();
        System.out.println("Введите название задачи");
        select = scanner.nextLine();
        Optional<Menu.MenuItemInfo> menuItemInfo =
                menu.select(select);
        if (menuItemInfo.isEmpty()) {
            System.out.println("Указанной задачи нет в списке задач!");
        } else {
            List<String> subList = menuItemInfo.get().getChildren();
            if (subList.size() == 0) {
                System.out.printf("Подзадач в задаче <%s> нет\n",
                        menuItemInfo.get().getName());
            }
            menuItemInfo.get().getChildren().forEach(System.out::println);
        }
    }

    private void showInputInfo() {
        String inputInfo = "================ СПИСОК ЗАДАЧ =================\n"
                + "Введите поочередно задачу и подзадачу по образцу:\n"
                + "Задача \n"
                + "\tПодзадача \n"
                + "\t\tПодзадача \n"
                + "\t\t.........\n"
                + "\t\tПодзадача \n"
                + "Задача \n"
                + "......\n\n"
                + "-------------------------------------------------------------------"
                + "\nПРИМЕЧАНИЕ: \tЕсли задач/подзадач нет, наберите <выход>.\n"
                + "\t\t\t\tЧтобы полностью закончить ввод, наберите <выход>\n"
                + "-------------------------------------------------------------------";
        System.out.println(inputInfo);
    }

    private void showMenu() {
        System.out.println("================== MENU ====================");
        String[] menu = {
                        "ВВОД СПИСКА ЗАДАЧ:                         ",
                        "ПРОСМОТР ПОДЗАДАЧ ВЫБРАННОЙ ЗАДАЧИ:        ",
                        "ПРОСМОТР ВСЕХ ЗАДАЧ:                       ",
                        "ВЫХОД:                                     "
        };
        for (int i = 0; i < menu.length; i++) {
            System.out.println(menu[i] + (i + 1));
        }
        System.out.println();
    }
}
