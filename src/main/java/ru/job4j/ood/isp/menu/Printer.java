package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {

    @Override
    public String print(Menu menu) {
        StringBuilder sb = new StringBuilder();
        menu.forEach(i -> {
                            sb.append(
                                    "\t".repeat(
                                            i.getNumber().split("\\.").length - 1)
                                    );
                            sb.append(i.getNumber()).append(" ");
                            sb.append(i.getName()).append(MenuPrinter.LS);
                            }
        );
        return sb.toString();
    }
}
