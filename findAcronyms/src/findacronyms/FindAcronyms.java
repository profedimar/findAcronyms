/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findacronyms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Edimar
 */
public class FindAcronyms {

    private final String filePATH;
    private final Set<String> acronyms = new HashSet<>();

    public FindAcronyms(String filePATH) {
        this.filePATH = filePATH;
    }

    private Set<String> find(String line) {
        String parts[] = line.split("[ +]|~|,|;|\\.");
        for (String part : parts) {
            if (part.trim().length() > 1 && part.matches("[A-Z]{2}.*")) {
                acronyms.add(part);
            }
        }
        return acronyms;
    }

    private void readLatexFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                find(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void printAcronyms(){
        for(String acronym: acronyms){
            System.out.println(acronym);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FindAcronyms fa = new FindAcronyms("C:\\Users\\Edimar\\Desktop\\tmp\\tese\\capitulos\\introducao.tex");
        fa.readLatexFile();
        fa.printAcronyms();
    }

}
