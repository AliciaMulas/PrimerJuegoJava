package graficos;

import static graficos.HojaSprites.desierto;

/**
 * Created by Alicia on 15/06/2017.
 */
public final class Sprite {

    private final int lado;

    private int x; //horizontal
    private int y; //vertical

    public int [] pixeles;
    private final HojaSprites hoja;

    //coleccion de sprites
    public static Sprite asfalto = new Sprite(32, 0, 0, HojaSprites.desierto);
    //fin de la coleccion

    public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja){
        this.lado = lado;

        pixeles = new int[lado * lado];

        //Para saber las coordenadas

        this.x = columna * lado;
        this.y = fila * lado;
        this.hoja = hoja;

        //Extraer el sprite

        for(int y = 0; y < lado; y++){
            for(int x = 0; x < lado; x++){
                pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.getAncho()];
            }
        }

    }


}
