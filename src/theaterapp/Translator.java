
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zeroc
 */
public class Translator {

    private Map<String, String> messages;

    //Constructor
    public Translator(String translatorFile) throws IOException {
        messages = new HashMap();
        read(translatorFile);
    }

    /**
     * Aqui se devuelve la palabra traducida
     *
     * @param msg Es la palabra que se busca traducir//La key del mapa
     * @return Devuelve la palabra traducida
     */
    public String translate(String msg) {
        return messages.get(msg);
    }

    /**
     * El lector del traductor, lee el archivo y va añadiendo al mapa la clase y
     * su traduccion con un split
     *
     * @param filename Es la direccion del archivo
     * @throws IOException Generara errores si esta vacio o si tiene lineas sin
     * ":" o vacias.
     */
    private void read(String filename) throws IOException {
        try {
            FileReader lector = new FileReader(filename);
            BufferedReader acumulador = new BufferedReader(lector);

            String s = acumulador.readLine();
            String[] aux;
            while (s != null) {
                aux = s.split(":");
                messages.put(aux[0], aux[1]);//Despues de dividir la linea, añade la llave primero y luego la traduccion
                s = acumulador.readLine();
            }

            lector.close();
            acumulador.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Hubo un error con las traducciones!");
        }
    }
}