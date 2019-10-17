package modell;

import controller.Main;
import javafx.stage.FileChooser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FileManager {

    private static Main main;

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
        System.out.println(MultiPlayerGame.getCategory());
        System.out.println(MultiPlayerGame.getDifficulty());
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

    public static void loadFromFile() {
        File fileDir = new File("./SavedGames");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.setInitialDirectory(fileDir);
        File openedFile = fileChooser.showOpenDialog(null);

        if(openedFile != null) {
            try {
                readFromFile(openedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("File opened");
        } else {
            System.out.println("File open cancelled");
        }
    }

    private static void readFromFile(File openedFile) throws IOException {
        List<Object> loadedFromFile = new ArrayList<>();
        List<QuizPlayer> players = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(openedFile))) {
            loadedFromFile = (ArrayList<Object>) ois.readObject();
            String category = (String) loadedFromFile.get(0);
            String difficulty = (String) loadedFromFile.get(1);
            System.out.println(category);
            System.out.println(difficulty);
            for(int i=2; i<loadedFromFile.size(); i++) {
                players.add((QuizPlayer) loadedFromFile.get(i));
                System.out.println(loadedFromFile.get(i));
            }
            System.out.println("loaded file size: " + loadedFromFile.size());
            loadMultiPlayerGame(players, category, difficulty);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadMultiPlayerGame(List<QuizPlayer> players, String category, String difficulty) {
        MultiPlayerGame multiPlayerGame = new MultiPlayerGame();
        multiPlayerGame.setCategory(category);
        multiPlayerGame.setDifficulty(difficulty);
        for(QuizPlayer player : players) {
            multiPlayerGame.addPlayer(player.getName(), player.getScore());
        }
        main.gameWindow();
    }
}
