import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        String eingabe = new String(Files.readAllBytes(Paths.get("file.txt")));

        String[] eingaben = eingabe.split("\n");
        String[] temp;
        String[][] farben = new String[100][100];
        int red = 0;
        int green = 0;
        int blue = 0;
        int ergebnis = 0;
        boolean erfolgreich = true;
        Pattern pattern = Pattern.compile("[0-9]{1,2}");


        for (int i = 0; i<100 ; i++) {
            temp = eingaben[i].split(":");
            String[] games = temp[1].split(";");
            erfolgreich = true;
            for(int j = 0; j < games.length; j++){
//                System.out.println("i: " + i + ", j: " + j);
                 temp = games[j].split(",");

                farben[i][j] = games[j];
                for (String s : temp) {
                    Matcher matcher = pattern.matcher(s);
                    if (s.contains("red")) {
                        while(matcher.find()){
                            red = red + Integer.parseInt(matcher.group());
                        }
                    }
                    if (s.contains("blue")) {
                        while(matcher.find()){
                            blue = blue + Integer.parseInt(matcher.group());
                        }
                    }
                    if (s.contains("green")) {
                        while(matcher.find()){
                            green = green + Integer.parseInt(matcher.group());
                        }
                    }

                }
                if(red > 12 || blue > 14 || green > 13){
                    erfolgreich = false;
                    red = 0;
                    blue = 0;
                    green = 0;
                    continue;
                }
                red = 0;
                blue = 0;
                green = 0;

            }

            if(erfolgreich){
                ergebnis = ergebnis + i+1;
            }

        }

        for (int i = 0; i<5 ; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(farben[i][j]);
            }
        }
        System.out.println("Ergebnis: " + ergebnis);
    }
}