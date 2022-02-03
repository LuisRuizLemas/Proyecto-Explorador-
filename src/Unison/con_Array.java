package Unison;

import java.io.*;
import java.util.ArrayList;

public class con_Array {
    public static void main(String[] args) {
        FileReader fi = null;

        try {
            fi = new FileReader("C:\\Users\\Latitude\\Documents\\mensajes\\m01.txt");

        } catch (FileNotFoundException ex) {
            System.out.println( ex.getMessage());
            System.exit(-1);
        }


        File dir = new File("C:\\Users\\Latitude\\Documents\\mensajes");
        if(dir.exists()){ // Directory exists then proceed.
            ArrayList<String> list2 = new ArrayList<String>(); // Lista de archivos

            for (int i = 0; i < list2.size(); i++) {
                System.out.println(list2.get(i));
            }}


        //Usar para leer linea x linea el archivo
        BufferedReader inputFile = new BufferedReader(fi);

        String textLine = null;

        int lineCount = 0;
        int wordCount = 0;
        int numberCount = 0;

        String delimiters = "\\s+|,\\s*|\\.\\s*|\\;\\s*|\\:\\s*|\\!\\s*|\\¡\\s*|\\¿\\s*|\\?\\s*|\\-\\s*"
                + "|\\[\\s*|\\]\\s*|\\(\\s*|\\)\\s*|\\\"\\s*|\\_\\s*|\\%\\s*|\\+\\s*|\\/\\s*|\\#\\s*|\\$\\s*";


        // Lista con todas las palabras diferentes
        ArrayList<String> list = new ArrayList<String>();

        // Tiempo inicial
        long startTime = System.currentTimeMillis();
        try {
            while ((textLine = inputFile.readLine()) != null) {
                lineCount++;

                if (textLine.trim().length() == 0) {
                    continue; // la linea esta vacia, continuar
                }

                // separar las palabras en cada linea
                String words[] = textLine.split( delimiters );

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

                    // si la palabra no esta en la lista, agregar a la lista
                    if ( !list.contains(theWord) ) {
                        list.add( theWord );
                    }
                }
            }


            // Obtener tiempo de ejecución
            long tiempoEjecucion = System.currentTimeMillis() - startTime;
            inputFile.close();
            fi.close();

            System.out.printf("%2.3f  segundos, %2d lineas y %3d palabras\n",
                    tiempoEjecucion / 1000.00, lineCount, wordCount - numberCount);

            // Mostrar total de palabras diferentes
            System.out.printf("%5d palabras diferentes\n", list.size() );


        } catch (IOException ex) {
            System.out.println( ex.getMessage() );
        }

    }

}
