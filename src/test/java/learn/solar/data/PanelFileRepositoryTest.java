package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelFileRepositoryTest {

    static final String SEED_FILE_PATH = "./data/panels-seed.csv";
    static final String TEST_FILE_PATH = "./data/panels-test.csv";

    PanelFileRepository repository = new PanelFileRepository(TEST_FILE_PATH);

    @BeforeEach
    void setupTest() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Panel> panels = repository.findAll();
        assertEquals(3, panels.size());
    }

    @Test
    void shouldFindByType() throws DataAccessException {
        List<Panel> panels = repository.findBySection(Material.MULTICRYSTALLINE_SILICON);
        assertEquals(1, panels.size());
    }

    @Test
    void shouldNotFindByTypeEmptyList() throws DataAccessException {
        List<Panel> panels = repository.findBySection(Material.MONOCRYSTALLINE_SILICON);
        assertEquals(0, panels.size());
    }

    @Test
    void shouldFindById() throws DataAccessException {
        Panel panel = repository.findById(1);
        assertNotNull(panel);
        assertEquals(1, panel.getPanelId());
        assertEquals(Material.MULTICRYSTALLINE_SILICON, panel.getType());
        assertEquals("2020-01-01", panel.getWhen());
        assertEquals("short test #1", panel.getSection());
        assertEquals(1, panel.getRow());
    }

    @Test
    void shouldNotFindByIdNull() throws DataAccessException {
        Panel panel = repository.findById(999999);
        assertNull(panel);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Panel panel = new Panel();
        panel.setType(Material.MULTICRYSTALLINE_SILICON);
        panel.setWhen("Jan 15, 2005");
        panel.setSection("moving pinpoint of light." +
                "seemed to move with me along the highway. " +
                "then suddenly reversed direction without slowing down. it just reversed.");
        panel.setRow(1);

        Panel actual = repository.add(panel);

        assertNotNull(actual);
        assertEquals(4, actual.getPanelId());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Panel panelToUpdate = new Panel(1, Material.MULTICRYSTALLINE_SILICON, "2020-01-01",
                "short test #1 updated", 1);
        assertTrue(repository.update(panelToUpdate));

        Panel actual = repository.findById(1);
        assertNotNull(actual);
        assertEquals(panelToUpdate, actual);
    }

    @Test
    void shouldNotUpdate() throws DataAccessException {
        Panel panelToUpdate = new Panel(999999, Material.MULTICRYSTALLINE_SILICON, "2020-01-01",
                "short test #1 updated", 1);
        assertFalse(repository.update(panelToUpdate));
    }

    @Test
    void shouldDeleteById() throws DataAccessException {
        boolean actual = repository.deleteById(1);
        assertTrue(actual);

        assertNull(repository.findById(1));
    }

    @Test
    void shouldNotDeleteById() throws DataAccessException {
        boolean actual = repository.deleteById(999999);
        assertFalse(actual);
    }
}