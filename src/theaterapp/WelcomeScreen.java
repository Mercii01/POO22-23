/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author zeroc
 */
public class WelcomeScreen extends Screen {

    private Theater theater;
    private Play play; //nueva variable
    private DispenserManager dispenserManager;

    //Constructor
    public WelcomeScreen(Theater theater, String title, ScreenMode mode, DispenserManager dispenserManager) throws IOException {
        super(title, mode);
        this.dispenserManager = dispenserManager;
        this.theater = theater;
        this.play = new Play();

    }

    /**
     * Dependiendo de la opcion pulsada se realizara la compra de tickets o
     * eleccion de idioma
     *
     * @param d DispenserHardware
     * @param c Es la opcion escogida
     * @return Devuelve si se mantiene en pantalla o no
     */
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware d, char c) {
        switch (c) {
            case 0: {
                return ScreenResult.CONTINUE;
            }
            case 'A': {
                try {
                    DateSelectionScreen dateScreen = new DateSelectionScreen("", ScreenMode.options, dispenserManager);
                    dispenserManager.showScreen(30, dateScreen);
                    return ScreenResult.CONTINUE;
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Hubo un error en la pantalla de bienvenida");
                }
            }

            case 'B': {
                IdiomSelectionScreen idiomScreen = new IdiomSelectionScreen("", ScreenMode.options, dispenserManager);
                dispenserManager.showScreen(30, idiomScreen);
                return ScreenResult.CONTINUE;
            }
            default: {
                return ScreenResult.CONTINUE;
            }

        }
    }

    /**
     * Metodo para conseguir el titulo de la obra
     *
     * @return Devuelve el titulo
     */
    @Override
    public String getTitle() {
        return play.getTitle();
    }

    /**
     * Metodo para conseguir la descripcion de la obra
     *
     * @return Devuelve la descripcion
     */
    @Override
    public String getDescription() {
        return play.getDescription();
    }

    /**
     * Metodo para conseguir la imagen de la obra
     *
     * @return Devuelve la imagen
     */
    @Override
    public String getImage() {
        return play.getImage();
    }

    /**
     * Las opciones disponibles en esta pantalla
     *
     * @return devuelve la lista de opciones
     */
    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Comprar entradas");
        options.add("Seleccion de idioma");
        return options;

    }
}
