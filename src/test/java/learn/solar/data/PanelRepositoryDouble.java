package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.Material;

import java.util.ArrayList;
import java.util.List;

public class PanelRepositoryDouble implements PanelRepository {
    private List<Panel> panels = new ArrayList<>(List.of(
            new Panel(1, Material.MULTICRYSTALLINE_SILICON, "2020-01-01", "short test #1", 1),
            new Panel(2, Material.AMORPHOUS_SILICON, "2020-02-01", "short test #2", 1),
            new Panel(3, Material.CADMIUM_TELLURIDE, "2020-03-01", "short test #3", 1)
    ));

    @Override
    public List<Panel> findAll() throws DataAccessException {
        return panels;
    }

    @Override
    public List<Panel> findBySection(Material panelType) throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<>();
        for (Panel panel : panels) {
            if (panel.getType() == panelType) {
                result.add(panel);
            }
        }
        return result;
    }

    @Override
    public Panel findById(int panelId) throws DataAccessException {
        return null;
    }

    @Override
    public Panel add(Panel panel) throws DataAccessException {
        return panel;
    }

    @Override
    public boolean update(Panel panel) throws DataAccessException {
        return panel.getPanelId() == 1;
    }

    @Override
    public boolean deleteById(int panelId) throws DataAccessException {
        return panelId == 1;
    }
}