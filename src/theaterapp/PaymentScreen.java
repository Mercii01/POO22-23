/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.CommunicationException;
import sienens.TheaterTicketDispenser;
import urjc.UrjcBankServer;

/**
 *
 * @author zeroc
 */
public class PaymentScreen extends Screen {

    private UrjcBankServer bank;
    private int amount; //Es lo que se debe pagar, ya calculado en SeatSelectionScreen
    private DispenserManager dispenserManager;
    private List<String> tickets; //Es la lista de tickets que se escogieron en SeatSelectionScreen
    private TheaterAreaState area;
    private boolean paymentDone; //Hasta que no se realice el pago correctamente, no se pondra a true

    //Constructor
    public PaymentScreen(int amount, String reusme, String title, ScreenMode mode, DispenserManager dispenserManager, List<String> tickets, TheaterAreaState area) {
        super(title, mode);
        this.amount = amount;
        this.bank = new UrjcBankServer();
        this.dispenserManager = dispenserManager;
        this.tickets = tickets;
        this.area = area;
        this.paymentDone =false;//Se inicializa a falso
    }

    /**
     * Devuelve ScreenResult.end porque si se queda inactivo o presiona cancelar, lo devuelve a welcomeScreen
     * @param c La opcion escogida
     * @return Devuelve ScreenResult
     */
    public ScreenResult optionButtonPressed(char c) {
        switch (c) {
            case 0:
                return ScreenResult.end;
            case 'A':
                return ScreenResult.end;
            default:
                return ScreenResult.CONTINUE;
        }
    }

    /**
     * Si se detecto una tarjeta, procede a hacer la operacion
     * @param d DispenserHardware, imprimera los tickets, conseguira la info de la tajeta
     * @param dispenser TheaterTicketDispenser, para poder utilizar DispenserHardware
     * @return Devulve ScreenResult.end, Es el final de las operaciones
     */
    @Override
    public ScreenResult creditCardDetected(DispenserHardware d, TheaterTicketDispenser dispenser) {
        try {
            d.retainCreditCard(false, dispenser);
            long creditCard = d.getCardNumber(dispenser);
            boolean doOperation = bank.doOperation(creditCard, amount);
            //En caso de no poder hacerse la operacion dara error
            if (doOperation) {
                printAllTickets(d, dispenser);
                this.paymentDone=true; //Una varaible auxiliar para saber si se actualizan los archivos o no
                //Checar si se quedo la tarjeta olvidada
                if (dispenser.expelCreditCard(30)) {
                    dispenser.retainCreditCard(true);
                } else {
                    return ScreenResult.end;
                }
            } else {
                //En caso de haber un error durante la compra salta un error
                ErrorScreen es = new ErrorScreen("", ScreenMode.message, dispenserManager);
                dispenserManager.showScreen(30, es);
                return ScreenResult.end;
            }
        //Si ocurrre un error tambien se crea un ErrorScreen
        } catch (IOException | CommunicationException ex) {
            ErrorScreen es = new ErrorScreen("", ScreenMode.message, dispenserManager);
            dispenserManager.showScreen(30, es);
            return ScreenResult.end;
        }
        return ScreenResult.end;
    }

    /**
     * Si es posible la comunicacion con el banco, dara acceso a la pantalla
     * @param dh DispenserHardware
     * @return Devuelve CONTINUE o end dependiendo de si hay comunicaion con el banco
     */
    @Override
    public ScreenResult begin(DispenserHardware dh) {
        if (bank.comunicationAvaiable()) {
            return ScreenResult.CONTINUE;
        } else {
            return ScreenResult.end;
        }
    }

    /**
     * Devuelve el titulo
     * @return Devuelve el titulo
     */
    @Override
    public String getTitle() {
        return "Inserta la tarjeta";
    }

    /**
     * Devuelve la descripcion
     * @return Devuelve la descripcion
     */
    @Override
    public String getDescription() {
        return "En total son";
    }

    /**
     * Devuelve el modo de pantalla
     * @return Devuelve el modo de pantalla
     */
    @Override
    public ScreenMode getScreenMode() {
        return ScreenMode.message;
    }

    /**
     * Devuelve el monto a pagar
     * @return Devuelve el monto a pagar
     */
    @Override
    public int getAmount() {
        return this.amount;
    }

    /**
     * Devuelve las opciones
     * @return Devuelve las opciones
     */
    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Cancelar");
        return options;
    }
    
    /**
     * Devuelve si se ha completado el pago
     * @return Devuelve la variable paymentDone
     */
    public boolean getPaymentDone(){
        return paymentDone;
    }

    /**
     * Este metodo se encarga de imprimir los tickets despues de la compra
     * @param d DispenserHardware, que imprimira los tickets
     * @param dispenser TheaterTicketDispenser
     * @throws IOException Puede causar errores si esta vacia la lista o con errores en la obra
     */
    private void printAllTickets(DispenserHardware d, TheaterTicketDispenser dispenser) throws IOException {
        //Imprimir cada ticket
        Play auxPlay = new Play();
        for (int i = 0; i < tickets.size(); i++) {
            String[] aux = tickets.get(i).split(",");
            List<String> info = new ArrayList<>();
            info.add("   Entrada para " + auxPlay.getTitle());
            info.add("   ===================");
            info.add("   " + area.getName());
            info.add("   Fila " + aux[0]);
            info.add("   Asiento " + aux[1]);
            info.add("   Precio " + amount);
            d.printTicket(info, dispenser);
        }
    }
}
