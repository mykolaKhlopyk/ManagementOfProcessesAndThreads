package main.java.ua.mykola.lab5.task2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Checking implements Runnable {
    private List<Changer> changers = new ArrayList<>();

    public void addChanger(Changer changer) {
        this.changers.add(changer);
    }

    @Override
    public void run() {
        Set<Long> set = new HashSet<>();
        for (Changer currentChanger : changers) {
            String str= currentChanger.getStr();
            long n =(str.chars().map(t->(char)t).filter(t->t=='A'||t=='B').count());
            System.out.println(str+" "+n);
            set.add(n);
        }
        System.out.println();
        if (set.size()<=2){
            Changer.stopChanging();
        }
    }
}
