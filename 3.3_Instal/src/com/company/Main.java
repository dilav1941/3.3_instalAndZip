package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static String makeDir(String path, String name) {
        File dirSrc = new File(path + name);
        if (dirSrc.mkdir())
            return ("Папка: " + dirSrc + " была создана \n");
        else return ("Папка: " + dirSrc + " не создана \n");
    }

    public static String createNewFile(String path, String name) {
        File textToTemptxt = new File (path, name);
        try {
            if (textToTemptxt.createNewFile ())
                return ("Файл: " + name + " был создан \n");
            else
                return ("Файл: " + name + " не создан \n");
        }
        catch (IOException ex) {
            return (ex.getMessage () + "\n");
        }
    }

    public static void main(String[] args) {

        // исходный путь к папке(папку Game(netology_ДЗ) предварительно создал самостоятельно)
        String game = "/Users/DLavr/Game(netology_ДЗ)/";
        StringBuilder log = new StringBuilder();

        // добавление папок в раздел - Game(src, res, savegames, temp)
        log.append (makeDir(game, "srs"));
        log.append (makeDir(game, "res"));
        log.append (makeDir(game, "savegames"));
        log.append (makeDir(game, "temp"));

        // добавление папок в раздел - src(main, test)
        String srcPath = game + "srs/";
        log.append (makeDir(srcPath, "main"));
        log.append (makeDir(srcPath, "test"));

        //обавление файлов в раздел - main(main.java , utils.java
        String fileMain = srcPath + "main/";
        log.append (createNewFile(fileMain, "Main.java"));
        log.append (createNewFile(fileMain, "Utils.java"));

        // добавление папок в раздел - Game : drawables, vectors, icons
        String resPath = game + "res/";
        log.append (makeDir(resPath, "drawables"));
        log.append (makeDir(resPath, "vectors"));
        log.append (makeDir(resPath, "icons"));

        // Добавление файла TEMP.txt в папке temp
        String dirTemp = game + "temp/";
        log.append (createNewFile(dirTemp, "temp.txt"));

        // записываем данные в файл temp.txt при помощи FileWrite
        File textToTemptxt = new File(dirTemp, "temp.txt");
        try (FileWriter writer = new FileWriter (textToTemptxt, false)) {
            writer.write(String.valueOf(log));
        }
        catch (IOException ex) {
            System.out.println (ex.getMessage ());
        }
    }
}
