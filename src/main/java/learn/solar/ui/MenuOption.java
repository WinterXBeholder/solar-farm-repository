package learn.solar.ui;

public enum MenuOption {
    EXIT("Exit"),
    DISPLAY_ALL("Display All Panels"),
    DISPLAY_BY_TYPE("Display Panels By Type"),
    ADD("Add A Panel"),
    UPDATE("Update A Panel"),
    DELETE("Delete A Panel");

    private String message;

    MenuOption(String name) {
        this.message = name;
    }

    public String getMessage() {
        return message;
    }
}