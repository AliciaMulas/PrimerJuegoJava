package graficos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Alicia on 04/06/2017.
 */
public class HojaSprites {
    private final int ancho;
    private final int alto;
    public final int[] pixeles;

    public HojaSprites(final String ruta, final int ancho, final int alto) {
        this.alto = alto;
        this.ancho = ancho;

        pixeles = new int[ancho * alto];

        try {
            BufferedImage imagen = ImageIO.read(HojaSprites.class.getResourceAsStream(ruta));

            imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAncho(){
        return ancho;
    }

}
