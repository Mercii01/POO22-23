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
public class Screen {
    private Theater theater;
    private DispenserManager screenManager;
    private String title;
    private String description;
    private String image;

    public Screen(String title, ScreenMode mode) {
    }
    
    
    
    public List<String> getOptions(){
        
        return null;
    }
    
    public String getTitle(){       
        return null;
    }
    
    public String getDescription(){
        return null;
    }
    
    public String getImage(){
        return null;
    }
    
    public ScreenMode getScreenMode(){
        return ScreenMode.options;
    }
    
    public ScreenResult optionButtonPressed(DispenserHardware d,char c){
        return null;
    }
    
    public ScreenResult seatButtonPressed(DispenserHardware d, int row, int col){
        return null;
    }
    
    public ScreenResult creditCardDetected(DispenserHardware d){
        return null;
    }
    
    public TheaterAreaState getAreaState(){
        return null;
    }
    
    public ScreenResult begin(DispenserHardware d){
        
        return ScreenResult.CONTINUE;
        
    }
    
    public ScreenResult end(DispenserHardware d){
        
        return ScreenResult.end;
        
    }

    ScreenResult seatButtonPressed(TheaterTicketDispenser dispenser, int row, int col) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    ScreenResult creditCardDetected(DispenserHardware dispenserHardware, TheaterTicketDispenser dispenser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int getAmount(){
        return 0;
    }
    
    public int getTicketsBought(){
        return 0;
    }
    
}
