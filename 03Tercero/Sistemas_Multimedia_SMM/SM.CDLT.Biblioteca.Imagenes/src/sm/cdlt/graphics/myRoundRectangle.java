package sm.cdlt.graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 02-jun-2016
 */
public class myRoundRectangle extends myRectangleShape {

    /**
     * Este es el punto de origen de la forma rectangular.
     */
    private static final int BEGIN_POINT = 0;
    /**
     * Este es el punto final de la forma rectangular.
     */
    private static final int END_POINT = 1;
    /**
     * Este es el punto que determina el arco de las esquinas
     */
    private static final int ARC_POINT = 2;
    /**
     * Variable que almacena en que estado de dibujo se encuentra la forma
     */
    private boolean status;

    /**
     * Constructor con dos parámetros que nos permite construir un rectángulo
     * simplemente pasando los dos puntos que unen la diagonal del rectángulo.
     *
     * @param p1 [in] Objeto de tipo geoPoint que indica el primer punto de la
     * diagonal.
     * @param p2 [in] Objeto de tipo geoPoint que indica el segundo punto de la
     * diagonal.
     */
    public myRoundRectangle(geoPoint p1, geoPoint p2) {
        super(p1);
        this.list_of_points.add(p2);
        this.myForma = new RoundRectangle2D.Double();
    }

    /**
     * Constructor con tres parámetros que inicializa un rectángulo sabiendo sus
     * coordenadas x e y del punto que hay arriba a la izquierda, e indicando
     * cual es la anchura y la altura de la forma.
     *
     * @param p [in] Objeto de tipo geoPoint que indica la posición actual
     * @param w [in] De tipo double almacena la cantidad de pixels que tiene la
     * anchura de la forma.
     * @param h [in] De tipo double almacena la cantidad de pixels que tiene la
     * altura de la forma.
     */
    public myRoundRectangle(geoPoint p, double w, double h) {
        super(p);
        this.list_of_points.add(new geoPoint(p.getPosX() + w, p.getPosY() + h));
        this.status = false;
        this.myForma = new RoundRectangle2D.Double();
    }

    /**
     * Constructor con cinco parámetros que inicializa un rectángulo sabiendo
     * sus coordenadas x e y del punto que hay arriba a la izquierda, e
     * indicando cual es la anchura y la altura de la forma, aparte del color y
     * el ancho del perímetro de la forma.
     *
     * @param p [in] Objeto de tipo geoPoint que indica la posición actual
     * @param w [in] De tipo double almacena la cantidad de pixels que tiene la
     * anchura de la forma.
     * @param h [in] De tipo double almacena la cantidad de pixels que tiene la
     * altura de la forma.
     * @param c [in] Del tipo Color este sera el color del perímetro.
     * @param s [in] Del tipo entero indica cuantos pixels tiene el perímetro de
     * ancho.
     */
    public myRoundRectangle(geoPoint p, double w, double h, Color c, float s) {
        super(p, c, s);
        this.list_of_points.add(new geoPoint(p.getPosX() + w, p.getPosY() + h));
        this.status = false;
        this.myForma = new RoundRectangle2D.Double();
    }

    /**
     * Con este método podemos actualizar la posición que ocupa el rectángulo en
     * le lienzo.
     *
     * @param p [in] Objeto geoPoint que indica cual será las coordenadas nuevas
     */
    public void updateRectangle(geoPoint p) {
        // @TODO hacer que se pueda crear el rectangulo en cualquier dirección
        if (this.status) {
            this.list_of_points.add(myRoundRectangle.ARC_POINT, p);
            ((RoundRectangle2D) this.myForma).setRoundRect(
                    this.list_of_points.get(myRoundRectangle.BEGIN_POINT).getPosX(),
                    this.list_of_points.get(myRoundRectangle.BEGIN_POINT).getPosY(),
                    this.list_of_points.get(myRoundRectangle.END_POINT).getPosX(),
                    this.list_of_points.get(myRoundRectangle.END_POINT).getPosY(),
                    Math.abs(((RoundRectangle2D) this.myForma).getCenterX() - this.list_of_points.get(myRoundRectangle.ARC_POINT).getPosX()),
                    Math.abs(((RoundRectangle2D) this.myForma).getCenterY() - this.list_of_points.get(myRoundRectangle.ARC_POINT).getPosY()));
        } else {
            this.list_of_points.set(myRoundRectangle.END_POINT, p);
            ((RoundRectangle2D) this.myForma).setFrameFromDiagonal(
                    this.list_of_points.get(myRoundRectangle.BEGIN_POINT).getPosX(),
                    this.list_of_points.get(myRoundRectangle.BEGIN_POINT).getPosY(),
                    this.list_of_points.get(myRoundRectangle.END_POINT).getPosX(),
                    this.list_of_points.get(myRoundRectangle.END_POINT).getPosY());
            this.bounding_rect.update(p);
        }
        
    }

    /**
     * Método que nos permite decidir si la figura ha terminado de pintarse o
     * no.
     *
     * @param s [in] estado en el que se encuentra la forma.
     */
    public void endPaint(boolean s) {
        this.status = s;
    }

    /**
     * Método que nos permite saber si es el ultimo punto a pintar.
     *
     * @return verdadero si es el ultimo punto, falso en caso contrario.
     */
    public boolean isLastPoint() {
        return this.status;
    }

    /**
     * Con este método podemos asignar el punto superior izquierda de la forma.
     *
     * @param p [in] Objeto de tipo geoPoint que indica la primera posición de
     * la forma.
     */
    @Override
    public void setLocation(geoPoint p) {
        double pfijox, pfijoy;
        pfijox = p.getPosX() - ((RoundRectangle2D)this.myForma).getBounds().getX();
        pfijoy = p.getPosY() - ((RoundRectangle2D)this.myForma).getBounds().getY();
        for (geoPoint point : this.list_of_points) {
            point.traslate(pfijox, pfijoy, 0);
        }
        this.bounding_rect.setLocation(p);
    }

    /**
     * Con este método podemos comprobar donde esta el primer punto de la forma,
     * en este caso será el punto superior izquierda.
     *
     * @return Objeto de tipo geoPoint con las coordenadas.
     */
    @Override
    public geoPoint getLocation() {
        return new geoPoint(this.myForma.getBounds2D().getX(), this.myForma.getBounds2D().getY());
    }

    /**
     * Método que comprueba si el punto que pasamos por parámetros esta
     * contenido en la forma.
     *
     * @param p [in] punto que queremos comprobar
     * @return verdadero si el punto esta dentro de la forma, falso en caso
     * contrario.
     */
    @Override
    public boolean contains(geoPoint p) {
        return this.myForma.contains(p.getPosX(), p.getPosY());
    }

    /**
     * Con este método dibujamos la forma en un tipo Graphics2D, la forma se
     * "auto dibuja"
     *
     * @param g2d Objeto de tipo Graphics2D que será donde se dibuje la forma.
     */
    @Override
    public void drawShapeIn(Graphics2D g2d) {
        if (this.list_of_points.size() > 2) {
            ((RoundRectangle2D) this.myForma).setRoundRect(
                    this.list_of_points.get(myRoundRectangle.BEGIN_POINT).getPosX(),
                    this.list_of_points.get(myRoundRectangle.BEGIN_POINT).getPosY(),
                    Math.abs(this.list_of_points.get(myRoundRectangle.BEGIN_POINT).getPosX() - this.list_of_points.get(myRoundRectangle.END_POINT).getPosX()),
                    Math.abs(this.list_of_points.get(myRoundRectangle.BEGIN_POINT).getPosY() - this.list_of_points.get(myRoundRectangle.END_POINT).getPosY()),
                    Math.abs(((RoundRectangle2D) this.myForma).getCenterX() - this.list_of_points.get(myRoundRectangle.ARC_POINT).getPosX()),
                    Math.abs(((RoundRectangle2D) this.myForma).getCenterY() - this.list_of_points.get(myRoundRectangle.ARC_POINT).getPosY())
            );
        } else {
            ((RoundRectangle2D) this.myForma).setRoundRect(
                    this.list_of_points.get(myRoundRectangle.BEGIN_POINT).getPosX(),
                    this.list_of_points.get(myRoundRectangle.BEGIN_POINT).getPosY(),
                    Math.abs(this.list_of_points.get(myRoundRectangle.BEGIN_POINT).getPosX() - this.list_of_points.get(myRoundRectangle.END_POINT).getPosX()),
                    Math.abs(this.list_of_points.get(myRoundRectangle.BEGIN_POINT).getPosY() - this.list_of_points.get(myRoundRectangle.END_POINT).getPosY()),
                    0, 0
            );
        }

        if (this.border_color != null) {
            g2d.setColor(this.border_color);
        }
        if (this.discontinuity_type != myDiscontinuity.NO_DISC) {
            float[] pattern;
            switch (this.discontinuity_type) {
                case DASH_BLANK:
                    pattern = new float[]{10.0f, 4.0f};
                    g2d.setStroke(new BasicStroke(this.line_width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, pattern, 0));
                    break;
                case POINT_BLANK:
                    pattern = new float[]{2.0f, 3.0f};
                    g2d.setStroke(new BasicStroke(this.line_width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, pattern, 0));
                    break;
                case DASH_POINT:
                    pattern = new float[]{10.0f, 4.0f, 2.0f, 4.0f};
                    g2d.setStroke(new BasicStroke(this.line_width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, pattern, 0));
                    break;
                case BORDER_IMAGE:
                    pattern = new float[]{10.0f, 4.0f};
                    g2d.setStroke(new BasicStroke(this.line_width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, pattern, 0));
                    break;
            }
        } else {
            g2d.setStroke(new BasicStroke(this.line_width));
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha / 100f));
        if (this.render != null) {
            g2d.setRenderingHints(this.render);
        }
        if (this.option_fill > 0) {
            switch (this.option_fill) {
                case 1: // solo color solido
                    g2d.setPaint(this.fill_colors[0]);
                    g2d.fill(this.myForma);
                    break;
                case 2: // LinearGradient
                    g2d.setPaint(new LinearGradientPaint(
                            (float)this.list_of_points.get(BEGIN_POINT).getPosX(),
                            (float)this.list_of_points.get(BEGIN_POINT).getPosY(),
                            (float)this.list_of_points.get(END_POINT).getPosX()+0.1f,
                            (float)this.list_of_points.get(END_POINT).getPosY()+0.1f,
                            this.fill_spaces, this.fill_colors));
                    g2d.fill(this.myForma);
                    break;
                case 3: // RadialGradient
                    g2d.setPaint(new RadialGradientPaint(
                            (float)((RoundRectangle2D)this.myForma).getCenterX(),
                            (float)((RoundRectangle2D)this.myForma).getCenterY(),
                            (float)Math.abs(this.list_of_points.get(BEGIN_POINT).getPosX() - this.list_of_points.get(END_POINT).getPosX()),
                            this.fill_spaces, this.fill_colors));
                    g2d.fill(this.myForma);
                    break;
            }
        }
        if (this.border_color != null) {
            g2d.setPaint(this.border_color);
            g2d.draw(this.myForma);
        }
        if (this.showBounding){
            this.bounding_rect.drawShapeIn(g2d);
        }
    }

}
