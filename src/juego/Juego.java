package juego;

import control.Teclado;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Alicia on 04/06/2017.
 */
public class Juego extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    private static final String NOMBRE = "Juego";

    private static int aps = 0;
    private static int fps = 0;

    private static JFrame ventana;

    //Volalite para que no se pueda ejecutar en 2 hilos a la vez
    private static volatile boolean enFuncionamiento = false;
    //Hilo
    private static Thread hilo;
    //clase teclado
    private static Teclado teclado;

    private Juego() {
        setPreferredSize(new Dimension(ANCHO, ALTO));

        teclado = new Teclado();
        addKeyListener(teclado); //Detectar todas las teclas que se pulsen en el canvas

        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }

    private synchronized void iniciar() {
        enFuncionamiento = true;
        hilo = new Thread(this, "Graficos");
        hilo.start();
    }

    private synchronized void detener() {
        enFuncionamiento = false;
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para actualizar las variables del juego, vida etc
     */
    private void actualizar() {
        teclado.actualizar();

        if(teclado.arriba){
            System.out.println("Arriba");
        }
        if(teclado.abajo){
            System.out.println("Abajo");
        }
        if(teclado.derecha){
            System.out.println("Derecha");
        }
        if(teclado.izquierda){
            System.out.println("Izquierda");
        }

        aps++;
    }

    /**
     * Metodo para dibujar los graficos
     */
    private void mostrar() {
        fps++;
    }

    /**
     * aps = actualizaciones por segundo, cuanto mas bajo mejor pero sin pasarse que se lagea
     */
    @Override
    public void run() {
        final int NS_POR_SEGUNDO = 1000000000; //nanosegundos en 1 segundo
        final byte APS_OBJETIVO = 60; //actualizacion por segundo
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO; //nanosegundo por actualizacion

        long referenciaActualizacion = System.nanoTime(); // se guarda el tiempo en nanosegundos
        long referenciaContador = System.nanoTime(); //Para hacer el contador

        double tiempoTranscurrido;
        double delta = 0; //delta es la cantidad de tiempo que pasa hasta que se hace una actualizacion

        //Coger el foco
        requestFocus();

        while (enFuncionamiento) {
            final long inicioBucle = System.nanoTime();

            tiempoTranscurrido = inicioBucle - referenciaActualizacion; //cuanto pasa entre esos momentos
            referenciaActualizacion = inicioBucle; //para qur funcione bien se puede medir el tiempo entre 2 puntos fijos

            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

            while(delta >= 1){
                actualizar();
                delta--;
            }

            mostrar();

            if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){ // si le diferencia es mayor de un segundo, osea se actualiza cada segundo
                ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS: " + fps );
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }
}
