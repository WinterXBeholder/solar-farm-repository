package learn.solar.ui;

import learn.solar.data.DataAccessException;
import learn.solar.domain.PanelResult;
import learn.solar.domain.PanelService;
import learn.solar.models.Panel;
import learn.solar.models.Material;

import java.util.List;

public class Controller {

    private final PanelService service;
    private final View view;

    public Controller(PanelService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        view.printHeader("Welcome To Unexplained Panels.");

        try {
            runMenuLoop();
        } catch (DataAccessException ex) {
            view.printHeader("CRITICAL ERROR:" + ex.getMessage());
        }

        view.printHeader("Goodbye");
    }

    private void runMenuLoop() throws DataAccessException {
        MenuOption option;
        do {
            option = view.displayMenuGetOption();
            switch (option) {
                case DISPLAY_ALL:
                    displayAllPanels();
                    break;
                case DISPLAY_BY_TYPE:
                    displayPanelsByType();
                    break;
                case ADD:
                    addPanel();
                    break;
                case UPDATE:
                    updatePanel();
                    break;
                case DELETE:
                    deletePanel();
                    break;
            }
        } while (option != MenuOption.EXIT);
    }

    private void displayAllPanels() throws DataAccessException {
        List<Panel> panels = service.findAll();
        view.printAllPanels(panels);
    }

    private void displayPanelsByType() throws DataAccessException {
        view.printHeader(MenuOption.DISPLAY_BY_TYPE.getMessage());
        Material panelType = view.readType();
        List<Panel> panels = service.findBySection(panelType);
        view.printPanelsByType(panels, panelType);
    }

    private void addPanel() throws DataAccessException {
        Panel panel = view.makePanel();
        PanelResult result = service.add(panel);
        view.printResult(result, "Panel Id %s added.%n");
    }

    private void updatePanel() throws DataAccessException {
        view.printHeader(MenuOption.UPDATE.getMessage());
        List<Panel> panels = service.findAll();
        Panel panel = view.selectPanel(panels);
        if (panel != null) {
            Panel updatedPanel = view.updatePanel(panel);
            PanelResult result = service.update(updatedPanel);
            view.printResult(result, "Panel Id %s updated.%n");
        }
    }

    private void deletePanel() throws DataAccessException {
        view.printHeader(MenuOption.DELETE.getMessage());
        List<Panel> panels = service.findAll();
        Panel panel = view.selectPanel(panels);
        if (panel != null) {
            PanelResult result = service.deleteById(panel.getPanelId());

            // HACK this doesn't feel great... but it solves my immediate problem
            result.setPayload(panel);

            view.printResult(result, "Panel Id %s deleted.%n");
        }
    }
}