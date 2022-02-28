package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.Material;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PanelFileRepository implements PanelRepository {
    private static final String DELIMITER = ",";
    private static final String DELIMITER_REPLACEMENT = "@@@";
    private static final String HEADER = "panel_id,section, row, column, year_installed, type, is_tracking";
    private final String filePath;

    public PanelFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Panel> findAll() throws DataAccessException {

        ArrayList<Panel> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Panel panel = deserialize(line);
                if (panel != null) {
                    result.add(panel);
                }
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }

        return result;
    }

    @Override
    public List<Panel> findBySection(Material panelType) throws DataAccessException {
//        ArrayList<Panel> result = new ArrayList<>();
//        List<Panel> panels = findAll();
//        for (Panel panel : panels) {
//            if (panel.getType() == panelType) {
//                result.add(panel);
//            }
//        }
//        return result;

        // a stream is a sequence of data
        // Panel
        //              panel 3 ... panel 2 ... panel 1
        return findAll().stream()
                .filter(panel -> panel.getType() == panelType)
                .collect(Collectors.toList());
    }

    @Override
    public Panel findById(int panelId) throws DataAccessException {
        List<Panel> panels = findAll();
        for (Panel panel : panels) {
            if (panel.getPanelId() == panelId) {
                return panel;
            }
        }
        return null;
    }

    @Override
    public Panel add(Panel panel) throws DataAccessException {
        List<Panel> all = findAll();
        panel.setPanelId(getNextId(all));
        all.add(panel);
        writeAll(all);
        return panel;
    }

    @Override
    public boolean update(Panel panel) throws DataAccessException {
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getPanelId() == panel.getPanelId()) {
                all.set(i, panel);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(int panelId) throws DataAccessException {
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getPanelId() == panelId) {
                all.remove(i);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    private int getNextId(List<Panel> allPanels) {
        int nextId = 0;
        for (Panel e : allPanels) {
            nextId = Math.max(nextId, e.getPanelId());
        }
        return nextId + 1;
    }

    private void writeAll(List<Panel> panels) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(HEADER);
            for (Panel e : panels) {
                writer.println(serialize(e));
            }
        } catch (IOException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }

    private String serialize(Panel panel) {
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                panel.getPanelId(),
                clean(panel.getSection()),
                panel.getRow(),
                panel.getColumn(),
                panel.getYearInstalled(),
                panel.getType(),
                panel.getIsTracking());
    }

    private Panel deserialize(String line) {
        String[] fields = line.split(DELIMITER, -1);
        if (fields.length == 5) {
            Panel panel = new Panel();

            panel.setPanelId(Integer.parseInt(fields[0]));
            panel.setSection(restore(fields[1]));
            panel.setRow(Integer.parseInt(fields[2]));
            panel.setColumn(Integer.parseInt(fields[3]));
            panel.setYearInstalled(Integer.parseInt(fields[4]));
            panel.setType(Material.valueOf(fields[5]));
            panel.setIsTracking(Boolean.parseBoolean(fields[6]));

            return panel;
        }
        return null;
    }

    private String clean(String value) {
        return value.replace(DELIMITER, DELIMITER_REPLACEMENT);
    }

    private String restore(String value) {
        return value.replace(DELIMITER_REPLACEMENT, DELIMITER);
    }

}