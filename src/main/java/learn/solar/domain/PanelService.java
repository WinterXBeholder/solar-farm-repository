package learn.solar.domain;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelRepository;
import learn.solar.models.Panel;
import learn.solar.models.Material;

import java.util.List;
import java.util.Objects;

public class PanelService {
// TODO: update Service to align with new model
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

        if (panel.getWhen() == null || panel.getWhen().trim().length() == 0) {
            result.addErrorMessage("when is required");
        }

        if (panel.getSection() == null || panel.getSection().trim().length() == 0) {
            result.addErrorMessage("section is required");
        }

        if (panel.getRow() <= 0) {
            result.addErrorMessage("row must be greater than 0");
        }

        // check for duplicate
        List<Panel> panels = repository.findAll();
        for (Panel e : panels) {
            if (!Objects.equals(panel.getPanelId(), e.getPanelId())
                    && Objects.equals(panel.getWhen(), e.getWhen())
                    && Objects.equals(panel.getType(), e.getType())
                    && Objects.equals(panel.getSection(), e.getSection())) {
                result.addErrorMessage("duplicate panel is not allowed");
                break;
            }
        }

        return result;
    }
}