/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.ArrayList;

/**
 *
 * @author zeroc
 */
public class IdiomSelectionScreen extends Screen {

    private DispenserManager dispenserManager;

    //Constructor
    public IdiomSelectionScreen(String title, ScreenMode mode, DispenserManager dispenserManager) {
        super(title, mode);
        this.dispenserManager = dispenserManager;
    }

    /**
     * Escoger que idioma presiono
     *
     * @param d DispenserHardware
     * @param c Opcion escogida
     * @return Devuelve ScreenResult.end porque pase lo que pase debera volver a
     * WelcomScreen
     */
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware d, char c) {
        switch (c) {
            case 0: {
                return ScreenResult.end;
            }
            case 'A': {
                dispenserManager.changeIdiom("Español");
                return ScreenResult.end;
            }
            case 'B': {
                dispenserManager.changeIdiom("English");
                return ScreenResult.end;
            }
            case 'C': {
                dispenserManager.changeIdiom("Catalan");
                return ScreenResult.end;
            }
            case 'D':{
                dispenserManager.changeIdiom("Euskera");
                return ScreenResult.end;
            }
        }
        return ScreenResult.end;
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

    /**
     * Devuelve el titulo
     *
     * @return Devuelve el titulo
     */
    @Override
    public String getTitle() {
        return "Escoge un idioma";
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
     * Devuelve las opciones
     *
     * @return Devuelve las opciones
     */
    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Español");
        options.add("Ingles");
        options.add("Catalan");
        options.add("Euskera");
        return options;

    }
}
