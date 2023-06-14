/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author zeroc
 */
public class TheaterState implements Serializable {

    private LocalDate date;
    private ArrayList<TheaterAreaState> areaList;

    //Constructor
    public TheaterState(Theater theater, LocalDate date) throws IOException, ClassNotFoundException {
        areaList = new ArrayList();
        this.date = date;
        initializeState(theater);
        createFiles();
    }

    /**
     * Devuelve la fecha
     *
     * @return Devuelve la fehca
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Devuelve el numero de areas
     *
     * @return Devuelve el numero de areas
     */
    public int getNumAreas() {
        return areaList.size();
    }

    /**
     * Devuelve el area de una posicion especifica del ArrayList
     *
     * @param pos Es la posicion del area
     * @return Devuelve un area
     */
    public TheaterAreaState getArea(int pos) {
        return areaList.get(pos);

    }

    /**
     * Inicializa las areas disponibles del teatro
     *
     * @param theater Es la info del teatro, especificamente se usa el area.
     */
    private void initializeState(Theater theater) {
        for (int i = 0; i < theater.getNumAreas(); i++) {
            TheaterAreaState areaState = new TheaterAreaState(theater.getArea(i));
            areaList.add(areaState);
        }
    }

    /**
     * Crea el archivo del objeto en caso
     *
     * @throws IOException Puede dar errores de serializacion, ruta equivocada,
     * etc.
     */
    public void createFiles() throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\zeroc\\OneDrive\\Desktop\\URJC\\POO\\Practicas\\TheaterApp\\StateFiles\\" + date.toString());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Hubo un error en el la actualizacion de datos.");
        }
    }

}
