package Unison;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

// Luis Ruiz
//Implementacion de contar palabras con multiples txt, con error

public class pruebas {
    public static void main(String[] args) throws IOException {

        File dir = new File("C:\\Users\\Latitude\\Documents\\mensajes"); // directory = target directory.
        int counter = 0;
        String delimiters = "\\s+|,\\s*|\\.\\s*|\\;\\s*|\\:\\s*|\\!\\s*|\\¡\\s*|\\¿\\s*|\\?\\s*|\\-\\s*"
                + "|\\[\\s*|\\]\\s*|\\(\\s*|\\)\\s*|\\\"\\s*|\\_\\s*|\\%\\s*|\\+\\s*|\\/\\s*|\\#\\s*|\\$\\s*";


        if(dir.exists()){ // Directory exists then proceed.
            ArrayList<String> list = new ArrayList<String>(); // Lista de archivos

            //Lista para guardar las palabras por archivo
            ArrayList<Integer> palabras = new ArrayList<Integer>();

            System.out.println("Numero total de palabras por archivo: ");


            for(File f : dir.listFiles()){
                if(!f.isFile()){
                    continue;
                }
                try
                {
                    FileInputStream fis = new FileInputStream(f);
                    byte[] data = new byte[fis.available()];
                    fis.read(data);
                    String text = new String(data);

                   // BufferedReader inputFile = new BufferedReader(fis);
                    String textLine = null;

                    //contador para guardar las palabras por archivo
                    int numberCount = 0;
                    int wordCount = 0;
                    int lineCount = 0;
                    int i= 0;


                    while(i < text.length()){
                        lineCount++;

                        if (textLine.trim().length() == 0) {
                            continue; // la linea esta vacia, continuar
                        }

                        String words[] = text.split( delimiters );

                        wordCount += words.length;

                        for (String theWord : words) {

                            theWord = theWord.toLowerCase().trim();

                            boolean isNumeric = true;

                            // verificar si el token es un numero
                            try {
                                Double num = Double.parseDouble(theWord);
                            } catch (NumberFormatException e) {
                                isNumeric = false;
                            }

                            // Si el token es un numero, pasar al siguiente
                            if( isNumeric ) {
                                numberCount++;
                                continue;
                            }
                    }
                }

                    //Si hay una o más repeticiones agregar el nombre del archivo
                    // y total de repeticiones a sus correspondientes listas
                    if(wordCount > 0){
                        list.add(f.getName()); // add file to found-keyword list.
                        palabras.add(wordCount);
                    }

                    fis.close();
                }
                catch(Exception e){
                    System.out.println("\n\t Error processing file : "+f.getName()+ counter);
                }
            }

            //recorrer la lista de archivos e imprimir el nombre junto con las
            //palabras
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i) + " aparece: " + palabras.get(i) + " veces");//Lista

            }
        } // IF directory exists then only process.
        else{
            System.out.println("\n Directory doesn't exist.");
        }
    }
}


