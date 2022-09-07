package com.knubisoft.DiffImages;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
public class Group {
    private int distance = 100; // take one считаем дистанцию // новая група //

    public Set<Point> points = new TreeSet<>(new Comparator<Point>() {

        @Override
        public int compare(Point o1, Point o2) {
            return o1.getX();
        }
    });

    void addPoint(Point point) {
        points.add(point);
    }

    void draw() {

    }
}
