/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author zeroc
 */
public class Play {

    private String title;
    private String image;
    private String description;

    //Constructor
    public Play() throws IOException {
        read();
    }

    /**
     * Devuelve el titulo
     *
     * @return Devuelve el titulo
     */
    public String getTitle() {
        return title;
    }

    /**
     * Devuelve el nombre de la imagen
     *
     * @return Devuelve el nombre de la imagen
     */
    public String getImage() {
        return image;
    }

    /**
     * Devuelve la descripcion
     *
     * @return Devuelve la descripcion
     */
    public String getDescription() {
        return description;
    }

    /**
     * Aqui se consigue toda la informacion de la obra
     *
     * @throws IOException Puede saltar errores por archivos vacios o rutas
     * equivocadas
     */
    private void read() throws IOException { //throws IOException{
        try {
            FileReader lector = new FileReader("C:\\Users\\zeroc\\OneDrive\\Desktop\\URJC\\POO\\Practicas\\TheaterApp\\ConfigFilesExample\\play.txt");
            BufferedReader acumulador = new BufferedReader(lector);

            String s = acumulador.readLine();
            while (s != null) {
                String[] aux = s.split(":");
                //Dependiendo de como empieza la linea, se añadira la informacion a cada variable
                if (s.startsWith("play_name")) {
                    this.title = aux[1];
                } else if (s.startsWith("play_poster")) {
                    this.image = aux[1];
                } else if (s.startsWith("description")) {
                    this.description = aux[1];
                }
                s = acumulador.readLine();
            }
            lector.close();
            acumulador.close();
        } catch (FileNotFoundException ex) {

        }
    }
}
