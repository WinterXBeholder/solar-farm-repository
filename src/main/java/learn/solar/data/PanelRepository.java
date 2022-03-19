package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.Material;

import java.util.List;

public interface PanelRepository {
    List<Panel> findAll() throws DataAccessException;

    List<Panel> findBySection(Material panelType) throws DataAccessException;

    Panel findById(int panelId) throws DataAccessException;

    Panel add(Panel panel) throws DataAccessException;

    boolean update(Panel panel) throws DataAccessException;

    boolean deleteById(int panelId) throws DataAccessException;
}