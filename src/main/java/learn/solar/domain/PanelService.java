package learn.solar.domain;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelRepository;
import learn.solar.models.Panel;
import learn.solar.models.Material;

import java.util.List;
import java.util.Objects;

public class PanelService {
    private final PanelRepository repository;

    public PanelService(PanelRepository repository) {
        this.repository = repository;
    }

    public List<Panel> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public List<Panel> findBySection(Material panelType) throws DataAccessException {
        return repository.findBySection(panelType);
    }

    public PanelResult add(Panel panel) throws DataAccessException {
        PanelResult result = validate(panel);
        if (!result.isSuccess()) {
            return result;
        }

        panel = repository.add(panel);
        result.setPayload(panel);
        return result;
    }

    public PanelResult update(Panel panel) throws DataAccessException {
        PanelResult result = validate(panel);
        if (!result.isSuccess()) {
            return result;
        }

        if (panel.getPanelId() <= 0) {
            result.addErrorMessage("Panel `panelId` is required.");
        }

        if (result.isSuccess()) {
            if (repository.update(panel)) {
                result.setPayload(panel);
            } else {
                String message = String.format("Panel id %s was not found.", panel.getPanelId());
                result.addErrorMessage(message);
            }
        }

        return result;
    }

    public PanelResult deleteById(int panelId) throws DataAccessException {
        PanelResult result = new PanelResult();

        if (!repository.deleteById(panelId)) {
            String message = String.format("Panel id %s was not found.", panelId);
            result.addErrorMessage(message);
        }

        return result;
    }

    private PanelResult validate(Panel panel) throws DataAccessException {

        PanelResult result = new PanelResult();
        if (panel == null) {
            result.addErrorMessage("panel cannot be null");
            return result;
        }

        if (panel.getSection() == null || panel.getSection().trim().length() == 0) {
            result.addErrorMessage("section is required");
        }

        if (panel.getRow() <= 0 || panel.getRow() > 250) {
            result.addErrorMessage("row must be greater than 0 and less than 250");
        }
        if (panel.getColumn() <= 0 || panel.getColumn() > 250) {
            result.addErrorMessage("column must be greater than 0 and less than 250");
        }
//TODO: implement dynamic year
        if (panel.getYearInstalled() > 2022) {
            result.addErrorMessage("year installed cannot be in the future");
        }
        // Enum verification and isTracking verification not really necessary: handled by View


        // check for duplicate
        List<Panel> panels = repository.findAll();
        for (Panel e : panels) {
            if (!Objects.equals(panel.getPanelId(), e.getPanelId())
                    && Objects.equals(panel.getSection(), e.getSection())
                    && (panel.getRow() == e.getRow())
                    && (panel.getColumn() == e.getColumn())
                    ) {
                result.addErrorMessage("this Section-Row-Column combination is already occupied");
                break;
            }
        }

        return result;
    }
}