/*package learn.solar.domain;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelRepositoryDouble;
import learn.solar.models.Panel;
import learn.solar.models.Material;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelServiceTest {

    PanelService service = new PanelService(new PanelRepositoryDouble());

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Panel> actual = service.findAll();
        assertEquals(3, actual.size());
    }

    @Test
    void shouldFindByType() throws DataAccessException {
        List<Panel> actual = service.findBySection(Material.MULTICRYSTALLINE_SILICON);
        assertEquals(1, actual.size());
    }

    @Test
    void shouldNotAddNull() throws DataAccessException {
        PanelResult expected = makeResult("panel cannot be null");
        PanelResult actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyWhen() throws DataAccessException {
        Panel panel = new Panel(0, Material.AMORPHOUS_SILICON, " ", "test desc", 1);
        PanelResult expected = makeResult("when is required");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullWhen() throws DataAccessException {
        Panel panel = new Panel(0, Material.AMORPHOUS_SILICON, null, "test desc", 1);
        PanelResult expected = makeResult("when is required");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptySection() throws DataAccessException {
        Panel panel = new Panel(0, Material.AMORPHOUS_SILICON, "2/2/2019", "  ", 1);
        PanelResult expected = makeResult("section is required");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullSection() throws DataAccessException {
        Panel panel = new Panel(0, Material.AMORPHOUS_SILICON, "2/2/2019", null, 1);
        PanelResult expected = makeResult("section is required");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddZeroRow() throws DataAccessException {
        Panel panel = new Panel(0, Material.AMORPHOUS_SILICON, "2/2/2019", "test section", 0);
        PanelResult expected = makeResult("row must be greater than 0");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicate() throws DataAccessException {
        Panel panel = new Panel(0, Material.AMORPHOUS_SILICON, "2020-02-01", "short test #2", 1);
        PanelResult expected = makeResult("duplicate panel is not allowed");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Panel panel = new Panel(0, Material.AMORPHOUS_SILICON, "2/2/2019", "test section", 1);
        PanelResult expected = new PanelResult();
        expected.setPayload(panel);

        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNull() throws DataAccessException {
        PanelResult expected = makeResult("panel cannot be null");
        PanelResult actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Panel panelToUpdate = new Panel(1, Material.MULTICRYSTALLINE_SILICON, "2020-01-01",
                "short test #1", 2);
        PanelResult expected = new PanelResult();
        expected.setPayload(panelToUpdate);

        PanelResult actual = service.update(panelToUpdate);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateUnknownPanelId() throws DataAccessException {
        Panel panelToUpdate = new Panel(999999, Material.MULTICRYSTALLINE_SILICON, "2020-01-01",
                "short test #999999", 2);
        PanelResult expected = makeResult("Panel id 999999 was not found.");

        PanelResult actual = service.update(panelToUpdate);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNoPanelId() throws DataAccessException {
        Panel panelToUpdate = new Panel(0, Material.MULTICRYSTALLINE_SILICON, "2020-01-01",
                "short test #999999", 2);
        PanelResult expected = makeResult("Panel `panelId` is required.");

        PanelResult actual = service.update(panelToUpdate);
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteById() throws DataAccessException {
        PanelResult expected = new PanelResult();
        PanelResult actual = service.deleteById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDeleteById() throws DataAccessException {
        PanelResult expected = makeResult("Panel id 999999 was not found.");
        PanelResult actual = service.deleteById(999999);
        assertEquals(expected, actual);
    }

    private PanelResult makeResult(String message) {
        PanelResult result = new PanelResult();
        result.addErrorMessage(message);
        return result;
    }
}

 */