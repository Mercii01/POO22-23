/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.IOException;
import sienens.TheaterTicketDispenser;

/**
 *
 * @author zeroc
 */
public class TheaterManager {

    public void run() throws IOException {
        TheaterTicketDispenser teatro = new TheaterTicketDispenser();
        DispenserManager dispenser = new DispenserManager(teatro);

        String fileDir = "C:\\Users\\zeroc\\OneDrive\\Desktop\\URJC\\POO\\Practicas\\TheaterApp\\ConfigFilesExample";
        Theater theater = new Theater(fileDir);
        String title = null;

        //Primera pantalla en mostrarse
        WelcomeScreen screen = new WelcomeScreen(theater, title, ScreenMode.options, dispenser);
        dispenser.showScreen(30, screen);
    }
}
