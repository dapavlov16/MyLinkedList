package com.company.List;

/*
https://twitter.com/joshbloch/status/583813919019573248
*/

public class MyLinkedList<T> {

    private int size = 0;
    private Node<T> first;
    private Node<T> last;

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
    public void add(T element) {
        add(size, element);
    }

    // Добавление по индексу
    public void add(int index, T element) {
        if (index == size) {
            Node<T> node = new Node<>(element, last, null);
            if (last == null) first = node;
            else last.next = node;
            last = node;
        } else {
            checkIndex(index);
            Node<T> old = getNode(index);
            Node<T> node = new Node<>(element, old.prev, old);
            if (index == 0) first = node;
            else old.prev.next = node;
            old.prev = node;
        }
        size++;
    }

    // Получение по индексу
    public T get(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    // Удаление по индексу
    public void remove(int index) {
        checkIndex(index);
        Node<T> rem = getNode(index);
        if (index != 0) rem.prev.next = rem.next;
        else first = rem.next;
        if (index != size - 1) rem.next.prev = rem.prev;
        else last = rem.prev;
        rem.prev = rem.next = null;
        size--;
    }

    // Удаление первого заданного элемента
    public void remove(T element) {
        int index = indexOf(element);
        if (index != -1) remove(index);
    }

    // Удаление всех заданных элементов
    public void removeAll(T element) {
        int index = indexOf(element);
        while (index != -1) {
            remove(index);
            index = indexOf(element);
        }
    }

    // Возвращает индекс первого вхождения элемента
    // Если элемент отсутствует в списке возвращает -1
    public int indexOf(T element) {
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

    // Заменяет элемент по индексу
    public void replace(int index, T element) {
        checkIndex(index);
        getNode(index).data = element;
    }

    // Заменяет все заданные элементы
    public void replaceAll(T oldElement, T newElement) {
        int index = indexOf(oldElement);
        while (index != -1) {
            getNode(index).data = newElement;
            index = indexOf(oldElement);
        }
    }

    // Проверка индекса
    // Кидает IndexOutOfBoundsException если индекс не входит в диапазон [0, size)
    private void checkIndex(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
    }

    // Возвращает ноду по индексу
    private Node<T> getNode(int index) {
        Node<T> node;
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
        if (size == 0) return "";
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < size; i++) {
            builder.append(getNode(i).data.toString()).append(i == size - 1 ? "]" : ", ");
        }
        return builder.toString();
    }
}
