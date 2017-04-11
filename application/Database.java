package FinalAssignment.application;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Database {
    
    private ArrayList<Clothing> clothes;
    
    public Database(String url) throws Exception{
        clothes = new ArrayList<>();
        ReadToDatabase("data.txt");     
    }
    
    public void ReadToDatabase(String url) throws Exception{
        BufferedReader in = 
                new BufferedReader(new FileReader(url));
        for(String line = in.readLine(); line!=null; line = in.readLine()){
            String[] items = new String[6];
            items[0] = line;
            for(int x = 1; x < 6; x++){
                items[x] = in.readLine();
            }
            clothes.add(new Clothing(items[0], items[1],
                    Double.parseDouble(items[2]), Boolean.parseBoolean(items[3]),
                    items[4], items[5]));
        }
    }
    
    public void WriteFromDatabase(String url){
        
    }
    
    @Override
    public String toString(){
        String list = "";
        for (int i = 0; i < clothes.size(); ++i) {
            list += clothes.get(i).getName() + "\n";
        }
        return list;
    }
}
