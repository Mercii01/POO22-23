/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zeroc
 */
public class DateSelectionScreen extends Screen {

    private Map<LocalDate, TheaterState> schedule;
    private DispenserManager dispenserManager;

    //Constructor
    public DateSelectionScreen(String title, ScreenMode mode, DispenserManager dispenserManager) throws IOException, ClassNotFoundException {
        super(title, mode);
        this.dispenserManager = dispenserManager;
        loadStateFiles();
    }

    /**
     * Devuelve las fechas disponibles
     *
     * @return Devuelve las fechas disponibles
     */
    private ArrayList<LocalDate> getDatesFromToday() {
        ArrayList<LocalDate> dateList = new ArrayList();
        LocalDate date = LocalDate.now();
        int aux = 0;
        do {
            //Consigue el dia en numero
            aux = date.getDayOfWeek().getValue();
            //Si el numero es 1 (lunes), lo salta e intenta el proximo dia
            if (aux != 1) {
                dateList.add(date);
                date = date.plusDays(1);
            } else {
                date = date.plusDays(1);
            }
        } while (dateList.size() < 5); //Tiene que dar el hasta 5 dias
        return dateList;
    }

    /**
     * En este metodo se escoge si se carga los archivos o si se deben de crear
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void loadStateFiles() throws IOException, ClassNotFoundException {
        ArrayList<LocalDate> dateList = getDatesFromToday(); //Consigue las fechas
        schedule = new HashMap(); //Crea un mapa donde la fecha es la llave y se devuelve un TheaterState
        for (int i = 0; i < dateList.size(); i++) {
            //Verifica si ya existe el archivo
            if (alreadyExists(dateList.get(i).toString()) == true) {
                //Si existe lo carga
                FileInputStream fileIn = new FileInputStream("C:\\Users\\zeroc\\OneDrive\\Desktop\\URJC\\POO\\Practicas\\TheaterApp\\StateFiles\\" + dateList.get(i).toString());
                ObjectInputStream lector = new ObjectInputStream(fileIn);
                TheaterState theaterState = (TheaterState) lector.readObject();
                lector.close();
                fileIn.close();
                schedule.put(dateList.get(i), theaterState);
            } else {
                //Si no existe, lo crea
                Theater teatroAux = new Theater("C:\\Users\\zeroc\\OneDrive\\Desktop\\URJC\\POO\\Practicas\\TheaterApp\\ConfigFilesExample");
                TheaterState teatro = new TheaterState(teatroAux, dateList.get(i));
                schedule.put(dateList.get(i), teatro);
            }
        }

    }

    /**
     * Devuelve las opciones
     *
     * @return Devuelve las opciones
     */
    @Override
    public List<String> getOptions() {
        ArrayList<LocalDate> dates = getDatesFromToday();
        ArrayList<String> options = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            options.add(dates.get(i).toString());
        }
        return options;
    }

    /**
     * Devuelve el titulo
     *
     * @return Devuelve el titulo
     */
    @Override
    public String getTitle() {
        return "Seleccione dia de representacion";
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
     * Devuelve un booleano, dependiendo de si encontro el archivo en la carpeta
     *
     * @param date Es el nombre del archivo que se buscara
     * @return Devuelve si lo encontro o no
     */
    private boolean alreadyExists(String date) {
        File states = new File("C:\\Users\\zeroc\\OneDrive\\Desktop\\URJC\\POO\\Practicas\\TheaterApp\\StateFiles");
        String[] dateStates = states.list();
        for (int i = 0; i < dateStates.length; i++) {
            if (date.equals(dateStates[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Encuentra la opcion presionada
     *
     * @param d DispenserHardware
     * @param c Es la opcion presionada
     * @return Devuelve ScreenResult.end porque sea lo q sea que presione,
     * terminara regresando a WelcomeScreen
     */
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware d, char c) {
        ArrayList<LocalDate> dates = getDatesFromToday();
        switch (c) {
            case 0: {
                return ScreenResult.end;
            }

            case 'A': {
                AreaSelectionScreen areaScreen = new AreaSelectionScreen(schedule.get(dates.get(0)), "", ScreenMode.options, dispenserManager);
                dispenserManager.showScreen(30, areaScreen);
                return ScreenResult.end;
            }

            case 'B': {
                AreaSelectionScreen areaScreen = new AreaSelectionScreen(schedule.get(dates.get(1)), "", ScreenMode.options, dispenserManager);
                dispenserManager.showScreen(30, areaScreen);
                return ScreenResult.end;
            }

            case 'C': {
                AreaSelectionScreen areaScreen = new AreaSelectionScreen(schedule.get(dates.get(2)), "", ScreenMode.options, dispenserManager);
                dispenserManager.showScreen(30, areaScreen);
                return ScreenResult.end;
            }

            case 'D': {
                AreaSelectionScreen areaScreen = new AreaSelectionScreen(schedule.get(dates.get(3)), "", ScreenMode.options, dispenserManager);
                dispenserManager.showScreen(30, areaScreen);
                return ScreenResult.end;
            }

            case 'E': {
                AreaSelectionScreen areaScreen = new AreaSelectionScreen(schedule.get(dates.get(4)), "", ScreenMode.options, dispenserManager);
                dispenserManager.showScreen(30, areaScreen);
                return ScreenResult.end;
            }

            default: {
                return ScreenResult.end;
            }

        }
    }
}
