package org.example.quadtree;

import collisions.BoundingBox;
import collisions.llist.LList;

import java.awt.*;

public abstract class QuadTree<T extends WithBB> implements WithBB {
    private final BoundingBox boundingBox;

    public QuadTree(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public static <T extends WithBB> QuadTree<T> from(BoundingBox bb, Iterable<T> objects) {
        QuadTree<T> t = empty(bb);
        for (var o : objects) {
            t = t.add(o);
        }
        return t;
    }

    @Override
    public BoundingBox boundingBox() {
        return boundingBox;
    }


    static <T extends WithBB> QuadTree<T> empty(BoundingBox bb) {
        throw new UnsupportedOperationException();
    }

    public abstract QuadTree<T> add(T object);

    public LList<T> intersecting(T object) {
        return intersecting(object, LList.empty());
    }

    public abstract LList<T> intersecting(T object, LList<T> acc);

    public void draw(Graphics2D g2d, double height) {
        g2d.setColor(Color.CYAN);
        boundingBox.draw(g2d, height);
    }

    public QuadTree<T> addAll(LList<T> list) {
        for (T obj : list) {
            add(obj);
        }
        return this;
    }

}
