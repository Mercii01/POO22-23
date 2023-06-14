/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.List;
import sienens.TheaterTicketDispenser;

/**
 *
 * @author zeroc
 */
public class DispenserHardware {

    /**
     * Imprime lo que se le manda
     *
     * @param info Es la informacion enviada
     * @param dispenser Es el que imprime
     */
    public void printTicket(List<String> info, TheaterTicketDispenser dispenser) {
        dispenser.print(info);
    }

    /**
     * Escoge si se quedara con la tarjeta de credito
     *
     * @param b Booleano que escoge si se mantiene o no
     * @param dispenser Es el que realiza la operacion
     */
    public void retainCreditCard(boolean b, TheaterTicketDispenser dispenser) {
        dispenser.retainCreditCard(b);
    }

    /**
     * Expulsa la tarjeta de credito
     *
     * @param dispenser Es el que realiza la operacion
     */
    public void expelCreditCard(TheaterTicketDispenser dispenser) {
        dispenser.expelCreditCard(0);
    }

    /**
     * Devuelve el numero de la tarjeta de credito
     *
     * @param dispenser Es el que hace la operacion
     * @return Devuelve un long, siendo el numero de tarjeta
     */
    public long getCardNumber(TheaterTicketDispenser dispenser) {
        long cardNumber = dispenser.getCardNumber();
        return cardNumber;
    }

}
