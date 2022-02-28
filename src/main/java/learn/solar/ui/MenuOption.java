package learn.solar.ui;

public enum MenuOption {
    EXIT("Exit"),
    DISPLAY_ALL("Display All Panels"),
    DISPLAY_BY_TYPE("Display Panels By Type"),
    ADD("Add An Panel"),
    UPDATE("Update an Panel"),
    DELETE("Delete an Panel");

    private String message;

    MenuOption(String name) {
        this.message = name;
    }

    public String getMessage() {
        return message;
    }
}