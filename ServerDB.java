import java.io.FileReader;
import java.io.FileWriter;

public class ServerDB implements DbInterfase {
    private String DBFileName;
    
    ServerDB(){
        loadSitings();
    }
    @Override
    public void loadSitings() {
        this.DBFileName = "log.txt";
    }

    @Override
    public void saveInDB(String str) {
        try (FileWriter writer = new FileWriter(DBFileName, true)){
            writer.write(str + "\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String loadINDB() {
    StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(DBFileName);){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
   
    }
    
}
