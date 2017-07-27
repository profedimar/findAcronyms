/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findacronyms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Edimar
 */
public class FindAcronyms {
    private final Set<String> acronyms = new HashSet<>();

    private void readLatexFiles(File dir){
        File latexChildren[] = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".tex");
            }
        });
        for(File child: latexChildren){
            readLatexFile(child);
        }
        
        File dirChildren[] = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        for(File child: dirChildren){
            readLatexFiles(child);
        }
    }

    private Set<String> find(String line) {
        String parts[] = line.split("[ +]|~|,|;|\\.|\\}|\\\\|\\)|\\?|\\(|:");
        for (String part : parts) {
            if (part.trim().length() > 1 && part.matches("[A-Z]{2}.*")) {
                acronyms.add(part);
            }
        }
        return acronyms;
    }

    private void readLatexFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
        FindAcronyms fa = new FindAcronyms();
        fa.readLatexFiles(new File("C:\\Users\\Edimar\\Desktop\\tmp\\tese\\capitulos"));
        fa.printAcronyms();
    }

}
