package modell;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SaveAndLoad {

    private static List<Player> players;

    public static void saveToFile() {
        File fileDir = new File("./SavedGames");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ser files (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(fileDir);
        File savedFile = fileChooser.showSaveDialog(null);

        if(savedFile != null) {
            saveFileRoutine(savedFile);
            System.out.println("File saved: " + savedFile.toString());
        } else {
            System.out.println("File save cancelled.");
        }
    }

    private static void saveFileRoutine(File savedFile) {
        players = MultiPlayerGame.getPlayers();
        List<Object> objectsToSave = new ArrayList<>(players);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedFile))) {
            oos.writeObject(objectsToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFile() {
        File fileDir = new File("./SavedGames");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.setInitialDirectory(fileDir);
        File openedFile = fileChooser.showOpenDialog(null);

        if(openedFile != null) {
            try {
                openFileRoutine(openedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("File opened");
        } else {
            System.out.println("File open cancelled");
        }
    }

    private static void openFileRoutine(File openedFile) throws IOException {
        List<Player> loadedUsers = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(openedFile))) {
            loadedUsers = (ArrayList<Player>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        handleButtonLoadedUsers(loadedUsers);
//        for(Person p : loadedUsers) {
//            System.out.println(p.toString());
//        }

    }

}
