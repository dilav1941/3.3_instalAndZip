package com.company;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        //исходный путь к папке
        String saveGamePath = "/users/DLavr/Game(netology_ДЗ)/savegames/";

        //значения нескольких игры
        GameProgress game1 = new GameProgress (1,2,3,4.3);
        GameProgress game2 = new GameProgress (5, 6, 7, 8.7);
        GameProgress game3 = new GameProgress (9, 10, 11, 12.3);

        //сохраненный прогресс игры
        save(saveGamePath + "save1.dat", game1);
        save(saveGamePath + "save2.dat", game2);
        save(saveGamePath + "save3.dat", game3);

        //создание .zip архива
        String archivesPath = saveGamePath + "/newSave.zip";

        File dirSaveGame = new File (saveGamePath);
        if (dirSaveGame.isDirectory ())
            zip(archivesPath, dirSaveGame.listFiles ());
        else
            System.out.println ("Такой папки не существует");
    }

    public static void save(String pathFile, GameProgress progress) {
        try (FileOutputStream fileOut = new FileOutputStream (pathFile);
             ObjectOutputStream objectOut = new ObjectOutputStream (fileOut)) {
            objectOut.writeObject (progress);
            System.out.println ("Создан новый файл для " + progress.toString ());
        }
        catch (Exception ex){
            System.out.println ("Файл сохраненной игры " + pathFile + " не записан!");
            System.out.println (ex.getMessage ());
        }
    }

    public static void zip(String archivesPath, File[] fileList) {
        try {
            byte[] buffer = new byte[1024];
            FileOutputStream fileOut = new FileOutputStream (archivesPath);
            ZipOutputStream zipOut = new ZipOutputStream (fileOut);
            for ( int i = 0; i<fileList.length ; i++){
                FileInputStream fileInput = new FileInputStream (fileList[i]);
                ZipEntry zipEntry = new ZipEntry (fileList[i].getName ());
                zipOut.putNextEntry (zipEntry);
                fileInput.read (buffer);
                zipOut.write (buffer);
                zipOut.closeEntry ();
                // завершение процесса input stream
                fileInput.close ();
                System.out.println ("Файл " + fileList[i].getName () + " добавлен в архив");
            }
            // завершение процесса zip out
            zipOut.close ();
            File archive = new File (archivesPath);
            System.out.println ("Файл " + archive.getName () + " успешно создан");
        }
        catch (IOException ioe) {
            System.out.println ("Ошибка создания файла " + ioe);
        }
    }
    // удаление файло
    public static void delFile(String pathFile) {
        File file = new File(pathFile);
        if (file.delete ()) {
            System.out.println ("Файл " + pathFile + " удален");
        }
        else{
            System.out.println ("Файл " + pathFile + " не получилось удалить!");
        }
    }
}
