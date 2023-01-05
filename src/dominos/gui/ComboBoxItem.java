package dominos.gui;

import dominos.model.Case;

public class ComboBoxItem {
    Case c;

    public ComboBoxItem(Case c) {
        this.c = c;
    }

    public Case getCase() {
        return c;
    }


    public boolean willAbandon() {
        return c == null;
    }

    @Override
    public String toString() {
        return c==null? "Abandon" : String.format("(%d, %d)", c.getRow(), c.getCol());
    }
}
