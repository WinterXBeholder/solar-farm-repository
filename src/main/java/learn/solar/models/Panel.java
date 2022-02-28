package learn.solar.models;

import java.util.Objects;

public class Panel {

    private int panelId;
    private String section;
    private int row;
    private int column;
    private int yearInstalled;
    private Material type;
    private Boolean isTracking;


    public Panel() {
    }

    public Panel(int panelId, String section, int row, int column, int yearInstalled, Material type, Boolean isTracking) {
        this.panelId = panelId;
        this.type = type;
        this.isTracking = isTracking;
        this.section = section;
        this.row = row;
    }

    public int getPanelId() {return panelId;}

    public void setPanelId(int panelId) {this.panelId = panelId;}

    public String getSection() {return section;}

    public void setSection(String section) {this.section = section;}

    public int getRow() {return row;}

    public void setRow(int row) {this.row = row;}

    public int getColumn() {return column;}

    public void setColumn(int column) {this.column = column;}

    public int getYearInstalled() {return yearInstalled;}

    public void setYearInstalled(int yearInstalled) {this.yearInstalled = yearInstalled;}

    public Material getType() {return type;}

    public void setType(Material type) {this.type = type;}

    public Boolean getIsTracking() {return isTracking;}

    public void setIsTracking(Boolean isTracking) {this.isTracking = isTracking;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panel panel = (Panel) o;
        return panelId == panel.panelId &&
                Objects.equals(section, panel.section) &&
                row == panel.row &&
                column == panel.column &&
                yearInstalled == panel.yearInstalled &&
                type == panel.type &&
                isTracking == panel.isTracking;
    }

    @Override
    public int hashCode() {
        return Objects.hash(panelId, section, row, column, yearInstalled, type, isTracking);
    }
}