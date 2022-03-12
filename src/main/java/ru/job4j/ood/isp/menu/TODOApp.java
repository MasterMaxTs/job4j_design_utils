package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    private static final String LS = System.lineSeparator();
    public static final String EXIT = "выход";

    public static void main(String[] args) {
        TODOApp app = new TODOApp();
        SimpleMenu menu = new SimpleMenu();
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        app.init(menu, scanner, printer);
    }

    public void init(SimpleMenu menu, Scanner scanner, Printer printer) {
        int input;
        while (true) {
            showMenu();
            System.out.println("Введите номер: ");
            Scanner sc = new Scanner(System.in);
            input = validate(sc);
            if (input == 4) {
                break;
            }
            switch (input) {
                case 1:
                    showInputInfo();
                    inputTasks(menu, scanner);
                    break;
                case 2:
                    outputSubtasks(menu, scanner);
                    break;
                default:
                    System.out.println(printer.print(menu));
            }
        }
        System.out.println("Успешный выход из программы!");
    }

    private int validate(Scanner scanner) {
        List<Integer> inputs = List.of(1, 2, 3, 4);
            String input = scanner.next();
            int num =  input.charAt(0);
            int valid = num - 48;
            while (true) {
                if (input.length() == 1 && inputs.contains(valid)) {
                    return valid;
                }
                System.out.println("Введен некорректный номер! Введите номер [1-4]");
                input = scanner.next();
                num = input.charAt(0);
                valid = num - 48;
            }
    }

    private void inputTasks(SimpleMenu menu, Scanner scanner) {
        while (true) {
            System.out.println("Введите названия задачи:");
            String task = scanner.nextLine();
            if (task.equalsIgnoreCase("Выход")) {
                break;
            }
            System.out.println("Введите список подзадач "
                    + "через <;>");
            String subtask = scanner.nextLine();
            if (subtask.equalsIgnoreCase(EXIT)) {
                menu.add(null, task, STUB_ACTION);
                continue;
            }
            String[] subtasks = subtask.split(";");
            if (menu.select(task).isEmpty()) {
                menu.add(null, task, STUB_ACTION);
            }
            for (String st
                    : subtasks) {
                menu.add(task, st, STUB_ACTION);
            }
        }
    }

    private void outputSubtasks(SimpleMenu menu, Scanner scanner) {
        System.out.println("Введите название задачи");
        String select = scanner.nextLine();
        Optional<Menu.MenuItemInfo> menuItemInfo =
                menu.select(select);
        if (menuItemInfo.isEmpty()) {
            System.out.println("Указанной задачи нет в списке задач!");
        } else {
            List<String> subList = menuItemInfo.get().getChildren();
            if (subList.size() == 0) {
                System.out.printf("Подзадач в задаче <%s> нет <%s>",
                        menuItemInfo.get().getName(), LS);
            }
            menuItemInfo.get().getChildren().forEach(System.out::println);
        }
    }

    private void showInputInfo() {
        String inputInfo = "================ СПИСОК ЗАДАЧ ================="
                + LS
                + "Введите поочередно задачу и подзадачу по образцу:"
                + LS
                + "Задача  "
                + LS
                + "\tПодзадача  "
                + LS
                + "\t\tПодзадача  "
                + LS
                + "\t\t......... "
                + LS
                + "\t\tПодзадача  "
                + LS
                + "Задача  "
                + LS
                + "-------------------------------------------------------------------"
                + LS
                + "ПРИМЕЧАНИЕ: \tЕсли задач/подзадач нет, наберите <выход>. "
                + LS
                + "\t\t\t\tЧтобы полностью закончить ввод, наберите <выход> "
                + LS
                + "-------------------------------------------------------------------"
                + LS;
        System.out.println(inputInfo);
    }

    private void showMenu() {
        System.out.println("================== MENU ====================");
        String menu =   "ВВОД СПИСКА ЗАДАЧ ........................ 1"
                        + LS
                        + "ПРОСМОТР ПОДЗАДАЧ ВЫБРАННОЙ ЗАДАЧИ ....... 2"
                        + LS
                        + "ПРОСМОТР ВСЕХ ЗАДАЧ ...................... 3"
                        + LS
                        + "ВЫХОД .................................... 4"
                        + LS;
        System.out.println(menu);
    }
}
