/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.IOException;
import java.util.List;
import sienens.TheaterTicketDispenser;

/**
 *
 * @author zeroc
 */
public class DispenserManager {

    private TranslatorManager translator;
    private TheaterTicketDispenser dispenser;

    public DispenserManager(TheaterTicketDispenser dispenser) throws IOException {
        this.dispenser = dispenser;
        this.translator = new TranslatorManager();
    }

    /**
     * Muestra la pantalla enviada
     *
     * @param time Es el tiempo disponible para que cuente como inactividad
     * @param screen La pantalla especifica de la clase Screen
     */
    public void showScreen(int time, Screen screen) {
        DispenserHardware dispenserHardware = new DispenserHardware();
        ScreenMode mode = screen.getScreenMode();
        //Dependiendo del modo escogido se realizara el caso especifico
        switch (mode) {
            case options -> {
                doOptionsMode(dispenserHardware, screen);
                break;
            }
            case theater -> {
                doTheaterMode(dispenserHardware, screen);
                break;
            }
            case message -> {
                doMessageMode(dispenserHardware, screen);
                break;
            }
        }

    }

    /**
     * drawArea dibujara los asientos del modo teatro dependiendo de si estan
     * ocupados, libres o es una fila vacia.
     *
     * @param areaState
     */
    private void drawArea(TheaterAreaState areaState) {
        int rowsNum = areaState.getRow();
        int colsNum = areaState.getCols();
        for (int row = 1; row <= rowsNum; row++) {
            for (int col = 1; col <= colsNum; col++) {
                try {
                    SeatState state = areaState.getSeatsState(row - 1, col - 1);
                    switch (state) {
                        case free:
                            dispenser.markSeat(row, col, 2);
                            break;
                        case ocuppied:
                            dispenser.markSeat(row, col, 1);
                            break;
                        default:
                            dispenser.markSeat(row, col, 0);
                            break;
                    }
                } catch (Exception ex) {
                    dispenser.markSeat(row, col, 0);
                }
            }
        }
    }

    /**
     * Esto es lo que sucede dentro de modo opciones
     *
     * @param dispenserHardware No se utiliza
     * @param screen Es la pantalla que se envio
     */
    private void doOptionsMode(DispenserHardware dispenserHardware, Screen screen) {
        ScreenResult CONTINUE = screen.begin(dispenserHardware);
        do {
            //Info principal
            dispenser.setMenuMode();
            String title = screen.getTitle();
            dispenser.setTitle(translator.translate(title));
            String description = screen.getDescription();
            dispenser.setDescription(translator.translate(description));
            String image = screen.getImage();
            dispenser.setImage("C:\\Users\\zeroc\\OneDrive\\Desktop\\URJC\\POO\\Practicas\\TheaterApp\\ConfigFilesExample\\" + image);
            vaciarOpciones();

            //En el modo eleccion de dia no se traduce nada
            if (title.equals("Seleccione dia de representacion")) {
                setOptions(false, screen);
            } else {  //Para cualquier otro modo se traduce la opcion
                setOptions(true, screen);
            }
            char c = dispenser.waitEvent(30);
            CONTINUE = screen.optionButtonPressed(dispenserHardware, c);

        } while (CONTINUE == ScreenResult.CONTINUE);
    }

    /**
     * Es el metodo que se ejecuta cuando se utiliza el modo teatro
     *
     * @param dispenserHardware No se utiliza
     * @param screen Es la pantalla en la que estamos trabajanod
     */
    private void doTheaterMode(DispenserHardware dispenserHardware, Screen screen) {
        dispenser.setTheaterMode(screen.getAreaState().getRow(), screen.getAreaState().getCols());
        ScreenResult CONTINUE = screen.begin(dispenserHardware);
        do {
            //Info principal
            String title = screen.getTitle();
            dispenser.setTitle(translator.translate(title));
            String description = screen.getDescription();
            dispenser.setDescription(translator.translate(description));

            //Aqui se consigue el area seleccionada y la va a dibujar
            TheaterAreaState areaState = screen.getAreaState();
            drawArea(areaState);

            setOptions(false, screen);
            do {
                char c = dispenser.waitEvent(30);
                byte col = (byte) (c & 0xFF);
                byte row = (byte) ((c & 0xFF00) >> 8);
                //Se verifican varias cosas:
                //1. Que no hayan pasado 30 segundos inactivo
                //2. Que no se hayan pedido mas de 4 tickets
                //3. Que el boton presionado este entre las filas y columnas disponibles
                //En caso de no estar entre las filas y columnas disponibles, se sume que presiono un boton
                if ((c != ' ') && (screen.getTicketsBought() <= 3) && (row <= screen.getAreaState().getRow()) && (col <= screen.getAreaState().getCols())) {
                    CONTINUE = screen.seatButtonPressed(dispenser, row, col);
                } else {
                    CONTINUE = screen.optionButtonPressed(dispenserHardware, c);
                }
            } while (CONTINUE == ScreenResult.CONTINUE);
        } while (CONTINUE == ScreenResult.CONTINUE);
    }

    /**
     * Este el metodo que se ejecuta cuando se muestra un modo mensaje en
     * pantalla
     *
     * @param dispenserHardware Se utiliza para hacer los metodos de pagos e
     * impresiones
     * @param screen Es la pantalla en la que estamos trabajando
     */
    private void doMessageMode(DispenserHardware dispenserHardware, Screen screen) {
        dispenser.setMessageMode();
        ScreenResult CONTINUE = screen.begin(dispenserHardware);
        do {
            //Info principal
            String title = screen.getTitle();
            dispenser.setTitle(translator.translate(title));
            vaciarOpciones();
            String description = screen.getDescription();

            //Si estamos en la pantalla de error, no se añade el monto a pagar.
            if (description.equals("ERROR EN LA COMPRA")) {
                dispenser.setDescription(translator.translate(description));
            } else {
                dispenser.setDescription(translator.translate(description) + " " + screen.getAmount() + " Euro");
            }

            setOptions(true, screen);
            char c = dispenser.waitEvent(30);
            //En caso de que se inserte la tarjeta, se ejecuta el metodo de tarjeta insertada, 
            //en caso contrario se habra presionado el boton cancelar
            if (c == '1') {
                CONTINUE = screen.creditCardDetected(dispenserHardware, dispenser);
            } else {
                CONTINUE = screen.optionButtonPressed(dispenserHardware, c);
            }
        } while (CONTINUE == ScreenResult.CONTINUE);
    }

    /**
     * Se utiliza como mediador al translator para seleccionar el idioma desde
     * IdiomSelectionScreen
     *
     * @param idiom
     */
    public void changeIdiom(String idiom) {
        translator.setActiveIdiom(idiom);
    }

    /**
     * Reinicia los botones para no dejar opciones antiguas
     */
    private void vaciarOpciones() {
        for (int i = 0; i < 6; i++) {
            dispenser.setOption(i, null);
        }
    }

    /**
     * Traduce las opciones o no dependiendo si se desea traducir.Por ejemplo,
     * no se traducira en casos como mostrar las fechas
     *
     * @param translate Escoge si se traducira o no las opcion
     * @param screen Se consiguen las opciones de la Screen en la que se esta
     * trabajando
     */
    private void setOptions(boolean translate, Screen screen) {
        List<String> options = screen.getOptions();
        int i = 0;
        if (translate == true) {
            for (String option : options) {
                dispenser.setOption(i, translator.translate(option));
                i++;
            }
        } else {
            for (String option : options) {
                dispenser.setOption(i, option);
                i++;
            }
        }
        dispenser.setOption(5, translator.translate("Cancelar"));
    }
}
