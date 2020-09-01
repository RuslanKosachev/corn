package net.krm.optimizer.colors;


public interface Colored {

    /**
     * Устанавливает цвет  по enum Color
     */
    void setColor(Color color) throws ColorException;

    /**
     * Устанавливает цвет по названию увета
     */
    void setColor(String colorString) throws ColorException;

    /**
     * Возвращает цвет
     */
    Color getColor();
}
