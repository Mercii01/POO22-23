/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sienens.TheaterTicketDispenser;

/**
 *
 * @author zeroc
 */
public class SeatSelectionScreen extends Screen {

    private TheaterState theaterState;
    private TheaterAreaState selectArea;
    private DispenserManager dispenserManager;
    private int ticketsBought = 0; //Es el numero de tickets que se han comprado
    private List<String> ticketsPos; //Es la lista de tickets que se han escogido

    //Constructor
    public SeatSelectionScreen(TheaterAreaState tas, String title, ScreenMode mode, DispenserManager dispenserManager, TheaterState theaterState) {
        super(title, mode);
        this.ticketsPos = new ArrayList<>();//Se inicializa el array de compras
        this.dispenserManager = dispenserManager;
        this.selectArea = tas;
        this.theaterState = theaterState;
    }

    /**
     * Metodo para saber que boton presiono
     *
     * @param d DispenserHardware
     * @param c boton presionad
     * @return Devuelve un ScreenrResult, sera end.
     */
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware d, char c) {
        switch (c) {
            case 0: {
                return ScreenResult.end;
            }
            case 'A': {
                try {
                    int amount = computePrice();//El precio total se calcula
                    PaymentScreen ps = new PaymentScreen(amount, "", "", ScreenMode.message, dispenserManager, ticketsPos, selectArea);
                    dispenserManager.showScreen(30, ps);
                    //Si se realizo el pago con exito, se actualiza los datos del AreaState
                    if (ps.getPaymentDone()) {
                        updateState();
                    }
                    return ScreenResult.end;
                } catch (IOException ex) {
                    System.out.println("Hubo un error con la pantalla de seleccion");
                }
            }
            case 'B': {
                return ScreenResult.end;
            }
            default:
                return ScreenResult.CONTINUE; //En caso de presionar cancelar
        }

    }

    /**
     * El asiento escogido se convierte a ocupado y se añade
     *
     * @param dispenser Es el que marcara el sitio
     * @param row Es la posicion de la fila
     * @param col Es la posiciond de la columna
     * @return Devuelve que se mantenga en la pantalla
     */
    @Override
    public ScreenResult seatButtonPressed(TheaterTicketDispenser dispenser, int row, int col) {
        //Si la posicion esta libre se permitira cambiar
        //Se le resta 1 porque el dibujo empieza por 1, pero el arraylist empieza por 0.
        if (selectArea.getSeatsState(row - 1, col - 1) == SeatState.free) {
            selectArea.setseat(row - 1, col - 1, SeatState.ocuppied);
            dispenser.markSeat(row, col, 1);
            //Se convierte a String el int
            String rowString = String.valueOf(row);
            String colString = String.valueOf(col);

            ticketsPos.add(rowString + "," + colString);//Se añade en modo String la posicion para luego imprimirlo
            ticketsBought++; //Se suma uno a que se compro un ticket
        }
        return ScreenResult.CONTINUE;
    }

    /**
     * Sobreescribe los datos del estado del teatro en esa fecha
     *
     * @throws IOException Puede devolver erroes por serializacion o archivos
     * vacios
     */
    public void updateState() throws IOException {
        theaterState.createFiles();
    }

    /**
     * Calcula el precio a pagar segun el numero de asientos y el precio del
     * area
     *
     * @return Devuelve lo que se debe
     */
    private int computePrice() {
        int price = selectArea.getPrice();
        int debt = price * ticketsBought;
        return debt;
    }

    /**
     * Devuelve el modo de esta pantalla
     *
     * @return Devuelve el modo de esta pantalla
     */
    @Override
    public ScreenMode getScreenMode() {
        return ScreenMode.theater;
    }

    /**
     * Devuelve el titulo
     *
     * @return Devuelve el titulo
     */
    @Override
    public String getTitle() {
        return "Escoge un asiento";
    }

    /**
     * Devuelve la descripcion
     *
     * @return Devuelve la descripcion
     */
    @Override
    public String getDescription() {
        return "null";
    }

    /**
     * Devuelve el area
     *
     * @return Devuelve el area
     */
    @Override
    public TheaterAreaState getAreaState() {
        return selectArea.getArea();
    }

    /**
     * Devuelve la opciones
     *
     * @return Devuelve las opciones
     */
    @Override
    public List<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Confiramr y pagar");
        options.add("Cancelar");
        return options;
    }

    /**
     * Devuelve el numero de tickets que se han comprado
     *
     * @return Devuelve el numero de asientos seleccionados
     */
    @Override
    public int getTicketsBought() {
        return ticketsBought;
    }
}