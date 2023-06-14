/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zeroc
 */
public class TranslatorManager {

    private Map<String, Translator> translatorMap;
    private String activeIdiom;

    //Constructor
    public TranslatorManager() throws IOException {
        translatorMap = new HashMap();
        loadTranslators("C:\\Users\\zeroc\\OneDrive\\Desktop\\URJC\\POO\\Practicas\\TheaterApp\\Translation");
        this.activeIdiom = "Español";//CASO BASE: ESPAÑOL
    }

    /**
     * Activa el idioma de la maquina
     *
     * @param activeIdiom Esta es la eleccion de idioma
     *
     */
    public void setActiveIdiom(String activeIdiom) {
        this.activeIdiom = activeIdiom;
    }

    /**
     * Este metodo enviara una palabra al mapa del traductor y devolvera la
     * traduccion
     *
     * @param msg Es lo que se busca traducir
     * @return Devuelve la traduccion encontrada en el mapa
     */
    public String translate(String msg) {
        Translator translator = translatorMap.get(activeIdiom);
        return translator.translate(msg);
    }

    /**
     * Aqui se cargan los archivos encontrados en la carpeta
     *
     * @param FileDirectory Es la ruta de la carpeta
     * @throws IOException Puede haber errores si no existe la ruta o si no hay
     * archivos.
     */
    private void loadTranslators(String FileDirectory) throws IOException {
        String[] pathnames; //Aqui se consiguen los nombres de los archivos
        String[] aux; //Auxiliar para hacer un split
        File f = new File(FileDirectory);
        pathnames = f.list(); //Se inicializa pathnames
        for (String name : pathnames) {
            Translator translator = new Translator(FileDirectory + "\\" + name); //Se envia la direccion del archivo
            aux = name.split("\\.");
            translatorMap.put(aux[0], translator);//Se añade al mapa el traductor serugn su nombre
        }
    }
}
