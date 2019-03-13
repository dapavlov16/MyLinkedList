package com.company.List;

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

    // Возвращает размер листа
    public int size() {
        return size;
    }

    // Добавление в конец списка
    public void add(E element) {
        add(size, element);
    }

    // Добавление по индексу
    public void add(int index, E element) {
        if (index == size) {
            final Node<E> node = new Node<>(element, last, null);
            if (last == null) first = node;
            else last.next = node;
            last = node;
        } else {
            checkIndex(index);
            Node<E> old = getNode(index);
            old.next.prev = old.next = new Node<>(element, old, old.next);
        }
        size++;
    }

    // Получение по индексу
    public E get(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    // Удаление по индексу
    public void remove(int index) {
        checkIndex(index);
        Node<E> rem = getNode(index);
        if (index != 0) rem.prev.next = rem.next;
        else first = rem.next;
        if (index != size - 1) rem.next.prev = rem.prev;
        else last = rem.prev;
        rem.prev = rem.next = null;
        size--;
    }

    // Удаление первого заданного элемента
    public void remove(E element) {
        int index = indexOf(element);
        if (index != -1) remove(index);
    }

    // Удаление всех заданных элементов
    public void removeAll(E element) {
        int index = indexOf(element);
        while (index != -1) {
            remove(index);
            index = indexOf(element);
        }
    }

    // Возвращает индекс первого вхождения элемента
    // Если элемент отсутствует в списке возвращает -1
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (getNode(i).data.equals(element)) return i;
        }
        return -1;
    }

    // Очищает список
    public void clear() {
        first = last = null;
        size = 0;
    }

    // Проверка индекса
    // Кидает OutOfBounds exception если индекс не входит в диапазон [0, size)
    private void checkIndex(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
    }

    // Возвращает ноду по индексу
    private Node<E> getNode(int index) {
        Node<E> node;
        if (index > size / 2) {
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < size; i++) {
            builder.append(getNode(i).data.toString()).append(i == size - 1 ? "]" : ", ");
        }
        return builder.toString();
    }
}
