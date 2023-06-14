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
public class TheaterArea {

    private int rows;
    private int cols;
    private String name;
    private int price;
    private ArrayList<ArrayList<SeatState>> seatList;//Es la simulacion de los asientos en un arrayList bidimensional

    //Constructor
    public TheaterArea(String fileName, String linea) throws IOException {
        seatList = new ArrayList<>();
        read(fileName, linea);
    }

    /**
     * Devuelve el numero de filas
     *
     * @return Devuelve el numero de filas
     */
    public int getRows() {
        return rows;
    }

    /**
     * Devuelve el numero de columnas
     *
     * @return Devuelve el numero de columnas
     */
    public int getCols() {
        return cols;
    }

    /**
     * Devuelve el nombre del area
     *
     * @return Devuelve el nombre del area
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve la simulacion de asientos
     *
     * @return Devuelve todos los asientos
     */
    public ArrayList<ArrayList<SeatState>> getSeats() {
        return seatList;
    }

    /**
     * Devuelve el precio de un asiento en el area
     *
     * @return Devuelve el precio de un asiento en el area
     */
    public int getPrice() {
        return price;
    }

    /**
     * Se encarga de leer la info del area
     *
     * @param fileName Ese la ruta del archivo
     * @param linea Es la linea extra en play.txt donde hay info importante de
     * area
     * @throws IOException Puede dar errores al no encontrar el archivo
     */
    private void read(String fileName, String linea) throws IOException {
        try {
            String[] aux = linea.split(";"); //Divide la linea enviada con info
            this.name = aux[0]; //Se consigue el nombre
            aux[1] = aux[1].replace("€", ""); //Se elimina el simbolo de euros
            this.price = Integer.valueOf(aux[1]); //Se guarda el precio de un asiento en el area

            FileReader lector = new FileReader(fileName + "\\" + aux[2]);
            BufferedReader acumulador = new BufferedReader(lector);

            String s = acumulador.readLine();
            while (s != null) {
                ArrayList<SeatState> lista = new ArrayList<>();//Se crea una lista que actuara como la fila
                for (int j = 0; j < s.length(); j++) {
                    //Si se lee "*" se marca como libre para inicializar, en caso contrario sera null
                    if (s.charAt(j) == '*') {
                        lista.add(SeatState.free);
                    } else {
                        lista.add(null);
                    }
                }
                this.seatList.add(lista);
                s = acumulador.readLine();
            }
            //El numero de filas sera el numero de ArrayLists que se añadieron
            this.rows = seatList.size();

            //Para conseguir el numero de columnas se contara el tamaño de cada fila, el numero mas grande sera el numero de columnas.
            for (int i = 0; i < seatList.size(); i++) {
                int numCols = seatList.get(i).size();
                if (numCols > this.cols) {
                    this.cols = numCols;
                }
            }

            lector.close();
            acumulador.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Hubo un error en la lectura del area");
        }
    }
}
