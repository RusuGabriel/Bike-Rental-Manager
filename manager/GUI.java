package manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

import java.io.File;

public class GUI extends Application {
    private static Parent root;
    private static String[] resourceStrings;
    private static GUI instance;
    private static Stage guiStage;
    //resources constant indices
    public static final int HOME = 1;
    public static final int REGISTRED_USERS = 2;
    public static final int REGISTRED_BIKES = 0;

    public static void main(String[] args) {
        loadResourcesFrom("D:\\Proiecte\\Bike Rental Manager\\src\\Views");
        launch(args);
    }

    private static void loadResourcesFrom(String pathname) {
        File currentDirectory = new File(pathname);
        File[] resources = currentDirectory.listFiles((name) -> {
            return name.getName().contains(".fxml");
        });
        resourceStrings = new String[resources.length];
        for (int i = 0; i < resourceStrings.length; i++)
            resourceStrings[i] = resources[i].getName();
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
            System.out.println("Nu merge");
        }
    }

    private static boolean isInvalid(int option) {
        return option < 0 || option > 2;
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
