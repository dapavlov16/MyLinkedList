package com.company;

/*
https://twitter.com/joshbloch/status/583813919019573248
 */

public class MyLinkedList<E> {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    public MyLinkedList() {
    }

    private static class Node<E> {
        private E data;
        private Node<E> prev;
        private Node<E> next;

        Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public int size() {
        return size;
    }

    public void add(E element) {
        add(size, element);
    }

    public void add(int index, E element) {
        if(index == size) {
            final Node<E> node = new Node<>(element, last, null);
            if(last == null) first = node;
            else last.next = node;
            last = node;
        }
        else {
            checkIndex(index);
            Node<E> old = getNode(index);
            old.next.prev = old.next = new Node<>(element, old, old.next);
        }
        size++;
    }

    public E get(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    public void remove(int index) {
        checkIndex(index);
        Node<E> rem = getNode(index);
        if(index != 0) rem.prev.next = rem.next;
        if(index != size - 1) rem.next.prev = rem.prev;
        rem.prev = rem.next = null;
        size--;
    }

    //TODO
    /*
    public boolean remove(E element) {
        if(element != null) {

        }
    }
    */

    public void clear() {
        first = last = null;
        size = 0;
    }

    private void checkIndex(int i) {
        if(i < 0 || i >= size) throw new IndexOutOfBoundsException("Size: " + size + ", Index: " + i);
    }

    private Node<E> getNode(int index) {
        Node<E> node;
        if (index > size/2) {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        } else {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        return node;
    }
}
