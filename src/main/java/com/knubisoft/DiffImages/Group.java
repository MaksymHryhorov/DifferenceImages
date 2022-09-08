package com.knubisoft.DiffImages;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
public class Group {
    private int distance = 100;

    public Set<Point> points = new TreeSet<>((o1, o2) -> {
        int x1 = o1.getX();
        int y1 = o1.getY();

        int y2 = o2.getY();
        int x2 = o2.getX();

        return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    });

    void addPoint(Point point) {
        points.add(point);
    }



}
