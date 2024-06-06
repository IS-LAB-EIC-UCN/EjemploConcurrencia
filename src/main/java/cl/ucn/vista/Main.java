package cl.ucn.vista;

import cl.ucn.modelo.MarcoRebote;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame marco=new MarcoRebote();
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);
    }
}