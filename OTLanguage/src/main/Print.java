package main;

import Item.Check;

public class Print implements Check {

    public void setPrint(String text) {
        text = text.strip();
        if (text.startsWith(":")) {

        }

    }

    @Override
    public boolean check(String text) {
        return false;
    }
}
