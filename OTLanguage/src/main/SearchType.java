package main;

import Item.Check;
import Item.TextType;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchType implements Check {

    public void getType(@NotNull String oneLine) {
        String[] words = oneLine.split(" ");
        List<String> list = new ArrayList<>();
        for (String word : words) list.add(word.trim().replaceAll("[;|:]", ""));
    }


    @Override
    public boolean check(String text) {
        return false;
    }
}
