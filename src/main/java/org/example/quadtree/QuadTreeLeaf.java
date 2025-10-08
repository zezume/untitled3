package org.example.quadtree;

import collisions.BoundingBox;
import collisions.llist.LList;

public class QuadTreeLeaf<T extends WithBB> extends QuadTree<T> {
    private LList<T> list;

    public QuadTreeLeaf(BoundingBox boundingBox) {
        super(boundingBox);
        list = LList.empty();
    }
    public QuadTreeLeaf(BoundingBox boundingBox, LList<T> list) {
        super(boundingBox);
        this.list = list;
    }

    @Override
    public QuadTree<T> add(T object) {

        if (!object.boundingBox().intersects(boundingBox())) {
            return this;
        }
        if (list.size() >= 10) {
            QuadTreeNode<T> node = new QuadTreeNode<>(boundingBox());
            node.addAll(list.prepend(object));
            return node;
        }else{
            return new QuadTreeLeaf<>(boundingBox(), list.prepend(object));
        }
    }

    @Override
    public LList<T> intersecting(T object, LList<T> acc) {
        if (object.boundingBox().intersects(boundingBox())) {
            for (T o : list) {
                if (o != object && o.boundingBox().intersects(object.boundingBox()) && !acc.contains(o)) {
                    acc = acc.prepend(o);
                }
            }
        }
        return acc;
    }
}

