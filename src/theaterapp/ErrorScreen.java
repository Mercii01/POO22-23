/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.ArrayList;
import urjc.UrjcBankServer;

/**
 *
 * @author zeroc
 */
public class ErrorScreen extends Screen {

    private UrjcBankServer bank;

    //Constructor
    public ErrorScreen(String title, ScreenMode mode, DispenserManager dispenserManager) {
        super(title, mode);
    }

    /**
     * Devuelve el titulo
     *
     * @return Devuelve el titulo
     */
    @Override
    public String getTitle() {
        return "ERROR EN LA COMPRA";
    }

    /**
     * Devuelve la descripcion
     *
     * @return Devuelve la descripcion
     */
    @Override
    public String getDescription() {
        return "ERROR EN LA COMPRA";
    }

    /**
     * Devuelve el modo de pantalla
     *
     * @return Devuelve la pantalla
     */
    @Override
    public ScreenMode getScreenMode() {
        return ScreenMode.message;
    }

    /**
     * Devuelve a la pantalla de inicio, ya sea por el boton cancelar o esperar
     * 30 segundos
     *
     * @param d DispenserHardware
     * @param c opcion escogida
     * @return Devuelve ScreenResult.end
     */
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware d, char c) {
        switch (c) {
            case 0:
                return ScreenResult.end;
            case 'A':
                return ScreenResult.end;
        }
        return ScreenResult.CONTINUE;
    }

    /**
     * Devuelve las opciones
     *
     * @return Devuelve las opciones
     */
    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Volver al inicio");
        return options;
    }
}
