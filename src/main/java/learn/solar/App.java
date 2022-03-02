package learn.solar;

import learn.solar.data.PanelFileRepository;
import learn.solar.domain.PanelService;
import learn.solar.ui.Controller;
import learn.solar.ui.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("dependency-configuration.xml");
        Controller controller = context.getBean(Controller.class);
        controller.run();
        /*
        PanelFileRepository repository = new PanelFileRepository("./data/panels.csv");
        PanelService service = new PanelService(repository);
        View view = new View();

        Controller controller = new Controller(service, view);
        controller.run();
         */
    }
}