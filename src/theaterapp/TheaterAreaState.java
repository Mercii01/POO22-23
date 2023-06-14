/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author zeroc
 */
public class TheaterAreaState implements Serializable {

    private ArrayList<ArrayList<SeatState>> seatList; //Es una lista de dos dimensiones para simular los asientos
    private String name;
    private int cols;
    private int row;
    private int price;

    //Constructor
    public TheaterAreaState(TheaterArea area) {
        this.seatList = area.getSeats();
        this.name = area.getName();
        this.cols = area.getCols();
        this.row = area.getRows();
        this.price = area.getPrice();
    }

    /**
     * Devuelve el estado de un asiento, ya sea free,occupied o null
     *
     * @param row El numero de la fila
     * @param col El numero de la columna
     * @return Devulve un SeatState de la fila y columna
     */
    public SeatState getSeatsState(int row, int col) {
        return seatList.get(row).get(col);
    }

    /**
     * Devuelve el nombre del Area
     *
     * @return El nombre del area
     */
    public String getName() {
        return name;
    }

    /**
     * Para conseguir la columna
     *
     * @return devuelve la posicion de la columna
     */
    public int getCols() {
        return cols;
    }

    /**
     * Para conseguir la fila
     *
     * @return Devuelve la posiciond de la fila
     */
    public int getRow() {
        return row;
    }

    /**
     * Cambiara el estado de un asiento a uno especifico
     *
     * @param row La posicion en la fila
     * @param col La posicion en la columna
     * @param state El estado que se desea marcar
     */
    public void setseat(int row, int col, SeatState state) {
        ArrayList<SeatState> auxColumn = seatList.get(row); //Es una columna auxiliar que sera la que se marque
        auxColumn.set(col, state);
        seatList.set(row, auxColumn); //Se añade la columna auxiliar donde estaba la anterior
    }

    /**
     * Devuelve a si mismo
     *
     * @return Devuelve a si mismo
     */
    public TheaterAreaState getArea() {
        return this;
    }

    /**
     * Devuelve el precio de los asientos en esta area
     *
     * @return Devuelve el precio
     */
    public int getPrice() {
        return this.price;
    }
}
