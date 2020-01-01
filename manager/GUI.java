package manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.text.SimpleDateFormat;

public class GUI extends Application {


    private static Parent root;
    private static String[] resourceStrings;
    private static GUI instance;
    private static Stage guiStage;
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
    
    //resources constant indices
    public static final int REGISTERED_BIKES = 0;
    public static final int HOME = 1;
    public static final int LOG_IN = 2;
    public static final int CHANGE_PRICING = 3;
    public static final int REGISTERED_RENTS = 4;
    public static final int SEARCH = 5;
    public static final int REGISTERED_USERS = 6;


    public static void main(String[] args) {
        loadResourcesFrom("D:\\Proiecte\\Bike Rental Manager\\src\\manager");
        launch(args);
    }

    private static void loadResourcesFrom(String pathname) {
        File currentDirectory = new File(pathname);
        File[] resources = currentDirectory.listFiles((name) -> {
            return name.getName().contains(".fxml");
        });
        resourceStrings = new String[resources.length];
        for (int i = 0; i < resourceStrings.length; i++) {
            resourceStrings[i] = resources[i].getName();
        }
    }

    public void start(Stage stage) {
        instance = this;
        guiStage = stage;
        display(HOME);

    }

    public static void display(int option) {
        if (isInvalid(option))
            return;
        try {
            root = FXMLLoader.load(instance.getClass().getResource(resourceStrings[option]));
            Scene scene = new Scene(root);
            guiStage.setScene(scene);
            guiStage.show();
        } catch (Exception e) {
            System.out.println("Nu merge:");
            System.out.println(e.getStackTrace());
        }
    }

    private static boolean isInvalid(int option) {
        return option < 0 || option > 7;
    }

    /**
     * @return the GUI Instance
     */
    public static GUI getInstance() {
        return instance;
    }

    /**
     * @return the root
     */
    public static Parent getParent() {
        return root;
    }
}
