package org.example.quadtree;

import collisions.BoundingBox;
import collisions.llist.LList;

import java.awt.*;

public class QuadTreeNode<T extends WithBB> extends QuadTree<T>{
    private Graphics2D g2d;
    private QuadTree<T> nw;
    private QuadTree<T> ne;
    private QuadTree<T> sw;
    private QuadTree<T> se;

    public QuadTreeNode(BoundingBox boundingBox) {
        super(boundingBox);
        nw = new QuadTreeLeaf<T>(boundingBox.topLeftQuarter());
        ne = new QuadTreeLeaf<T>(boundingBox.topRightQuarter());
        sw = new QuadTreeLeaf<T>(boundingBox.bottomLeftQuarter());
        se = new QuadTreeLeaf<T>(boundingBox.bottomRightQuarter());
    }


    @Override
    public QuadTree<T> add(T object) {
        if (object.boundingBox().intersects(nw.boundingBox())) {
            nw = nw.add(object);
        }
        if (object.boundingBox().intersects(ne.boundingBox())) {
            ne = ne.add(object);
        }
        if (object.boundingBox().intersects(sw.boundingBox())) {
            sw = sw.add(object);
        }
        if (object.boundingBox().intersects(se.boundingBox())) {
            se = se.add(object);
        }
        return this;

    }

    @Override
    public LList<T> intersecting(T object, LList<T> acc) {
        if (object.boundingBox().intersects(nw.boundingBox())) {
            acc = nw.intersecting(object, acc);
        }
        if (object.boundingBox().intersects(ne.boundingBox())) {
            acc = ne.intersecting(object, acc);
        }
        if (object.boundingBox().intersects(sw.boundingBox())) {
            acc = sw.intersecting(object, acc);
        }
        if (object.boundingBox().intersects(se.boundingBox())) {
            acc = se.intersecting(object, acc);
        }
        return acc;
    }

    @Override
    public void draw(Graphics2D g2d, double height) {
        nw.draw(g2d, height);
        ne.draw(g2d, height);
        sw.draw(g2d, height);
        se.draw(g2d, height);
    }

}
