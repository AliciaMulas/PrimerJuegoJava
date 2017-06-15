package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Alicia on 16/06/2017.
 */
public final class Teclado implements KeyListener {


    //numero de teclas que se puedan necesitar(osea el codigo del caracter no la tecla)
    private final static int numeroTeclas = 120;
    private final boolean[] teclas = new boolean[numeroTeclas];
    //teclas de movimiento
    public boolean arriba;
    public boolean abajo;
    public boolean derecha;
    public boolean izquierda;



    public void actualizar(){
        arriba = teclas[KeyEvent.VK_W]; //Arriba es W
        abajo = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_D];
    }

    @Override
    public void keyTyped(KeyEvent e) { //pulsar y soltar

    }

    @Override
    public void keyPressed(KeyEvent e) { //pulsar
        teclas[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) { //soltar la tecla
        teclas[e.getKeyCode()] = false;
    }
}
