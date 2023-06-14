/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author zeroc
 */
public class Theater {

    private String theaterPlay; //Es el nombre de la obra
    private ArrayList<TheaterArea> theaterAreas;
    private String theaterImage; //Ruta de la direccion de la imagen

    //Constructor
    public Theater(String fileDir) throws IOException {
        theaterAreas = new ArrayList();
        read(fileDir);
    }

    /**
     * Devuelve el numero de areas
     *
     * @return Devuelve el numero de areas
     */
    public int getNumAreas() {
        return theaterAreas.size();
    }

    /**
     * Devuelve un area de una posicion especifica
     *
     * @param pos Es la posicion
     * @return Devuelve un area
     */
    public TheaterArea getArea(int pos) {
        return theaterAreas.get(pos);
    }

    /**
     * Es el metodo para conseguir la info leyendo el archivo
     *
     * @param theatreDir Es la direccion de la carpeta
     * @throws IOException Puede dar errores si la ruta es equivocada o con
     * archivos vacios
     */
    private void read(String theatreDir) throws IOException {
        try {
            FileReader lector = new FileReader(theatreDir + "\\theater.txt");
            BufferedReader acumulador = new BufferedReader(lector);

            String s = acumulador.readLine();
            while (s != null) {
                String[] aux = s.split(":");
                //Dependiendo de como empieza la linea, se dara distinta informacion
                if (s.startsWith("TheaterName")) {
                    this.theaterPlay = aux[1];
                } else if (s.startsWith("TheaterPlaneImageFile")) {
                    this.theaterImage = aux[1];
                } else if (s.startsWith("Area")) {
                    TheaterArea area = new TheaterArea(theatreDir, aux[1]); //Se crea un area pasandole la linea de la info y la ruta
                    this.theaterAreas.add(area);
                }
                s = acumulador.readLine();
            }

            lector.close();
            acumulador.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro los archivos");
        }
    }

}
