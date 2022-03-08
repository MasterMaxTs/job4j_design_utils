package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {

    @Override
    public String print(Menu menu) {
        StringBuilder sb = new StringBuilder();
        for (Menu.MenuItemInfo itemInfo : menu) {
            sb.append(
                    "\t".repeat(
                            itemInfo.getNumber().split("\\.").length - 1
                    )
            );
            sb.append(itemInfo.getNumber()).append("\s");
            sb.append(itemInfo.getName()).append("\n");
        }
        return sb.toString();
    }
}
