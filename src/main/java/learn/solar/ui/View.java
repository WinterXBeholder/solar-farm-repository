package learn.solar.ui;

import learn.solar.domain.PanelResult;
import learn.solar.models.Panel;
import learn.solar.models.Material;

import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner console = new Scanner(System.in);

    public MenuOption displayMenuGetOption() {
        printHeader("Main Menu");

        MenuOption[] options = MenuOption.values();
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%s. %s%n", i + 1, options[i].getMessage());
        }

        String msg = String.format("Select [%s-%s]:", 1, options.length);
        int value = readInt(msg, 1, options.length);
        return options[value - 1];
    }

    public void printHeader(String message) {
        printMessage(message);
        System.out.println("=".repeat(message.length()));
    }

    private void printMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    public void printAllPanels(List<Panel> panels) {
        printHeader(MenuOption.DISPLAY_ALL.getMessage());
        printPanels(panels);
    }

    public void printPanelsByType(List<Panel> panels, Material panelType) {
        printHeader(MenuOption.DISPLAY_BY_TYPE.getMessage() + ": " + panelType);
        printPanels(panels);
    }

    public Panel selectPanel(List<Panel> panels) {
        printPanels(panels);
        int panelId = readInt("Enter an panel ID: ");

        Panel selectedPanel = null;
        for (Panel panel : panels) {
            if (panel.getPanelId() == panelId) {
                selectedPanel = panel;
                break;
            }
        }

        if (selectedPanel == null) {
            printMessage("Panel not found.");
        }

        return selectedPanel; // returns null if we didn't find an panel
    }

    private void printPanels(List<Panel> panels) {
        if (panels == null || panels.size() == 0) {
            System.out.println();
            System.out.println("No panels found.");
        } else {
            for (Panel e : panels) {
                printPanel(e);
            }
        }
    }

    private void printPanel(Panel panel) {
        System.out.printf("%s. Type:%s, Row:%s, When:%s, Desc:%s%n",
                panel.getPanelId(),
                panel.getType(),
                panel.getRow(),
                panel.getWhen(),
                panel.getSection());
    }

    public void printResult(PanelResult result, String successMessageTemplate) {
        if (result.isSuccess()) {
            if (result.getPayload() != null) {
                System.out.printf(successMessageTemplate, result.getPayload().getPanelId());
            }
        } else {
            printHeader("Errors");
            for (String msg : result.getMessages()) {
                System.out.printf("- %s%n", msg);
            }
        }
    }

    public Panel makePanel() {
        printHeader(MenuOption.ADD.getMessage());
        Panel panel = new Panel();
        panel.setType(readType());
        panel.setRow(readInt("Number of row:"));
        panel.setWhen(readRequiredString("When:"));
        panel.setSection(readRequiredString("Section:"));
        return panel;
    }

    public Panel updatePanel(Panel panel) {
        System.out.println();
        printPanel(panel);
        System.out.println();
        // TODO how can we improve this user experience?
        panel.setType(readType());
        panel.setRow(readInt("Number of row:"));
        panel.setWhen(readRequiredString("When:"));
        panel.setSection(readRequiredString("Section:"));
        return panel;
    }

    private String readString(String message) {
        System.out.print(message);
        return console.nextLine();
    }

    private String readRequiredString(String message) {
        String result;
        do {
            result = readString(message);
            if (result.trim().length() == 0) {
                System.out.println("Value is required.");
            }
        } while (result.trim().length() == 0);
        return result;
    }

    private int readInt(String message) {
        String input = null;
        int result = 0;
        boolean isValid = false;
        do {
            try {
                input = readRequiredString(message);
                result = Integer.parseInt(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("%s is not a valid number.%n", input);
            }
        } while (!isValid);

        return result;
    }

    private int readInt(String message, int min, int max) {
        int result;
        do {
            result = readInt(message);
            if (result < min || result > max) {
                System.out.printf("Value must be between %s and %s.%n", min, max);
            }
        } while (result < min || result > max);
        return result;
    }

    // This is pretty helpful... maybe bookmark this :)
    public Material readType() {
        int index = 1;
        // Print panel type enum values...
        for (Material type : Material.values()) {
            System.out.printf("%s. %s%n", index++, type);
        }
        index--;
        String msg = String.format("Select Panel Type [1-%s]:", index);
        return Material.values()[readInt(msg, 1, index) - 1];
    }
}