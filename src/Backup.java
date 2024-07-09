import java.io.*;
import java.time.LocalDate;

class BackUpFolder {
    static File folderPath;

    public BackUpFolder(File folderPath){
        this.folderPath = folderPath;
    }

    public void backup(File file, File backupFile) {
        //備份文件
        try {
            FileInputStream inputFile = new FileInputStream(file);
            FileOutputStream output = new FileOutputStream(backupFile);
            byte[] buffer =new byte[1024];
            int len;
            while ((len = inputFile.read(buffer)) > 0){
                output.write(buffer,0, len);
            }

        } catch (IOException e) {
            File newFolder = new File(String.valueOf(folderPath));
            newFolder.mkdir();
            File[] files = file.listFiles();
            for(File f : files){
                //設定匯出資訊
                File newFile = new File(newFolder.getPath()+ "/" + f.getName());
                folderPath = newFile;
                backup(f,newFile);
            }
        }
    }
}


public class Backup {


    public static void main(String[] args) {

        //創建一個新的備份文件夾
        LocalDate date = LocalDate.now();
        String backupFileName = "backup" + date ;

        File backupFolder = new File(backupFileName);
        backupFolder.mkdir();

        //設定寫入來源
         File sourceFolder = new File("src/com/company");
         File[] files = sourceFolder.listFiles();
         for(File file : files){
             //設定匯出資訊
            File backupFile = new File(backupFolder.getPath()+ "/" + file.getName());
            BackUpFolder backUpFolder = new BackUpFolder(backupFile);
            backUpFolder.backup(file,backupFile);
             }
        System.out.println("備份完成");
         }
    }

