package modell;

import javafx.stage.FileChooser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class FileManager {

    public static void saveToFile() {
        File fileDir = new File("./SavedGames");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ser files (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(fileDir);
        File savedFile = fileChooser.showSaveDialog(null);

        if(savedFile != null) {
            writeToFile(savedFile);
            System.out.println("File saved: " + savedFile.toString());
        } else {
            System.out.println("File save cancelled.");
        }
    }

    private static void writeToFile(File savedFile) {
        List<Object> objectsToSave = new ArrayList<>();
        objectsToSave.add(0, MultiPlayerGame.getCategory());
        objectsToSave.add(1, MultiPlayerGame.getDifficulty());

        int numberOfPlayers = MultiPlayerGame.getQuizPlayers().size();
        for(int i=0; i<numberOfPlayers; i++) {
            objectsToSave.add(MultiPlayerGame.getQuizPlayers().get(i));
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedFile))) {
            oos.writeObject(objectsToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MultiPlayerGame loadFromFile() {
        File fileDir = new File("./SavedGames");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.setInitialDirectory(fileDir);
        File openedFile = fileChooser.showOpenDialog(null);

        if(openedFile != null) {
            try {
                return new MultiPlayerGame(Objects.requireNonNull(readFromFile(openedFile)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File open cancelled");
        }
        return null;
    }

    private static MultiPlayerGame readFromFile(File openedFile) throws IOException {
        List<Object> loadedFromFile;
        List<QuizPlayer> players = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(openedFile))) {
            loadedFromFile = (ArrayList<Object>) ois.readObject();
            String category = (String) loadedFromFile.get(0);
            String difficulty = (String) loadedFromFile.get(1);

            for(int i=2; i<loadedFromFile.size(); i++) {
                players.add((QuizPlayer) loadedFromFile.get(i));
                System.out.println(loadedFromFile.get(i));
            }

            return new MultiPlayerGame(players, category, difficulty);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
