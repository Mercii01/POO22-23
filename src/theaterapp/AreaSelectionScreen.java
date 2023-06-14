/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zeroc
 */
public class AreaSelectionScreen extends Screen {

    private TheaterState theaterState;
    private DispenserManager dispenserManager;

    //Constructor
    public AreaSelectionScreen(TheaterState ts, String title, ScreenMode mode, DispenserManager dispenserManager) {
        super(title, mode);
        this.theaterState = ts;
        this.dispenserManager = dispenserManager;
    }

    /**
     * Escoge la opcion seleccionada
     *
     * @param d DispenserHardware
     * @param c Es la opcion escogida
     * @return Deveulve ScreenResult.end porque siempre vuelve a WelcomeScreen
     */
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware d, char c) {
        switch (c) {
            case 0: {
                return ScreenResult.end;
            }
            case 'A': {
                SeatSelectionScreen seatScreen = new SeatSelectionScreen(theaterState.getArea(0), "", ScreenMode.theater, dispenserManager, theaterState);
                dispenserManager.showScreen(30, seatScreen);
                return ScreenResult.end;
            }
            case 'B': {
                SeatSelectionScreen seatScreen = new SeatSelectionScreen(theaterState.getArea(1), "", ScreenMode.theater, dispenserManager, theaterState);
                dispenserManager.showScreen(30, seatScreen);
                return ScreenResult.end;
            }
            case 'C': {
                SeatSelectionScreen seatScreen = new SeatSelectionScreen(theaterState.getArea(2), "", ScreenMode.theater, dispenserManager, theaterState);
                dispenserManager.showScreen(30, seatScreen);
                return ScreenResult.end;
            }
            case 'D': {
                SeatSelectionScreen seatScreen = new SeatSelectionScreen(theaterState.getArea(3), "", ScreenMode.theater, dispenserManager, theaterState);
                dispenserManager.showScreen(30, seatScreen);
                return ScreenResult.end;
            }
            case 'E': {
                SeatSelectionScreen seatScreen = new SeatSelectionScreen(theaterState.getArea(4), "", ScreenMode.theater, dispenserManager, theaterState);
                dispenserManager.showScreen(30, seatScreen);
                return ScreenResult.end;
            }
        }
        return ScreenResult.end;
    }

    /**
     * Devuelve el titulo
     *
     * @return Devuelve el titulo
     */
    @Override
    public String getTitle() {
        return "Escoge un area";
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
     * Devuelve la imagen
     *
     * @return Devuelve la imagen
     */
    @Override
    public String getImage() {
        return "plane.png";
    }

    /**
     * Devuelve las opciones
     *
     * @return Devuelve las opciones
     */
    @Override
    public List<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        for (int i = 0; i < theaterState.getNumAreas(); i++) {
            options.add(theaterState.getArea(i).getName());
        }
        return options;
    }

    /**
     * Devuelve el modo de pantalla
     *
     * @return Devuelve el modo de pantalla
     */
    @Override
    public ScreenMode getScreenMode() {
        return ScreenMode.options;
    }

}
