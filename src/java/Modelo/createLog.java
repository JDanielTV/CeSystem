/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author PC
 */
public class createLog{

    File finalOutFilePost;
    
    public void createLog(String fileName){
        String outDir = "C:\\Users\\PC\\Documents\\NetBeansProjects\\Cesystem\\";
        String outFileName = fileName+".log"; 

        finalOutFilePost = new File(outDir + outFileName);
     }   

    createLog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void writelog(String lineW){
      
        try (FileWriter fwOut = new FileWriter(finalOutFilePost, true);
            BufferedWriter bwOut = new BufferedWriter(fwOut)) {
            bwOut.append(lineW).append("\n");

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }                           

    }

}
