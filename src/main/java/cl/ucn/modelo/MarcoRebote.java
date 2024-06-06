package cl.ucn.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarcoRebote extends JFrame {

    private final LaminaPelota lamina;
    public MarcoRebote(){

        setBounds(600,300,400,350);
        setTitle ("Rebotes");
        lamina=new LaminaPelota();
        add(lamina, BorderLayout.CENTER);
        JPanel laminaBotones=new JPanel();
        ponerBoton(laminaBotones, "Iniciar!", new ActionListener(){

            public void actionPerformed(ActionEvent evento){
                comenzarJuego();
            }
        });
        ponerBoton(laminaBotones, "Salir", new ActionListener(){

            public void actionPerformed(ActionEvent evento){
                System.exit(0);
            }

        });
        add(laminaBotones, BorderLayout.SOUTH);
    }

    //Ponemos botones
    public void ponerBoton(Container c, String titulo, ActionListener oyente){
        JButton boton=new JButton(titulo);
        c.add(boton);
        boton.addActionListener(oyente);
    }

    //Añade pelota y la hace rebotar 3000 veces

    public void comenzarJuego ()  {

        Pelota pelota=new Pelota();
        lamina.add(pelota);

        for (int i=1; i<=3000; i++){

            pelota.mueve_pelota(lamina.getBounds());
            this.lamina.paint(lamina.getGraphics());

            try{
                Thread.sleep(10);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}