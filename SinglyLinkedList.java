import java.util.NoSuchElementException;

public class SinglyLinkedList<E extends Comparable<E>> {
    private ListNode<E> head;
    private ListNode<E> tail;
    private int size;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // 11. Implement a copy constructor for SinglyLinkedList
    public SinglyLinkedList(SinglyLinkedList<E> oldList) {
        if (oldList.isEmpty()) {
            head = null;
            tail = null;
            size = 0;
        } else {
            head = new ListNode<>(oldList.head.getValue());
            ListNode<E> currentOld = oldList.head.getNext();
            ListNode<E> currentNew = head;
            while (currentOld != null) {
                currentNew.setNext(new ListNode<>(currentOld.getValue()));
                currentNew = currentNew.getNext();
                currentOld = currentOld.getNext();
            }
            tail = currentNew;
            size = oldList.size;
        }
    }

    // 1. boolean add(E value)
    public boolean add(E value) {
        ListNode<E> newNode = new ListNode<>(value);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
        return true;
    }

    // 2. boolean add(int index, E value)
    public boolean add(int index, E value) {
        if (index < 0 || index > size) {
            throw new NoSuchElementException("Invalid index.");
        }

        ListNode<E> newNode = new ListNode<>(value);
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            ListNode<E> prevNode = getNodeAtIndex(index - 1);
            newNode.setNext(prevNode.getNext());
            prevNode.setNext(newNode);
        }

        if (index == size) {
            tail = newNode;
        }
        size++;
        return true;
    }

    // 3. void clear()
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // 4. int size()
    public int size() {
        return size;
    }

    // 5. E get(int index)
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ListNode<E> currentNode = head;


        for (int i = 0; i < index; i++) {
            if(currentNode == null)
                return null;
            currentNode = currentNode.getNext();
        }
        if(currentNode == null)
            return null;
        return currentNode.getValue();
    }

    // 6. E set(int index, E value)
    public E set(int index, E value) {
        ListNode<E> node = getNodeAtIndex(index);
        if (node == null) {
            throw new NoSuchElementException("Invalid index.");
        }
        E oldValue = node.getValue();
        node.setValue(value);
        return oldValue;
    }


    public void addLast(E e) {
        ListNode<E> newNode = new ListNode<>(e, null);

        if (isEmpty()) {
            head = newNode;
        } else if(tail != null) {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    public void addFirst(E value) {
        ListNode<E> newNode = new ListNode<>(value);
        newNode.setNext(head);
        head = newNode;
        size++;
    }


    // 7. E remove(int index)
    public E remove(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }

        ListNode<E> removedNode;
        if (index == 0) {
            removedNode = head;
            head = head.getNext();
            if (size == 1) {
                tail = null;
            }
        } else {
            ListNode<E> prevNode = getNodeAtIndex(index - 1);
            if (prevNode == null || prevNode.getNext() == null) {
                throw new NoSuchElementException("Invalid index.");
            }
            removedNode = prevNode.getNext();
            prevNode.setNext(removedNode.getNext());
            if (index == size - 1) {
                tail = prevNode;
            }
        }
        size--;
        return removedNode.getValue();
    }

    // 8. boolean isEmpty()
    public boolean isEmpty() {
        return size == 0;
    }

    // 9. boolean contains(E value)
    public boolean contains(E value) {
        ListNode<E> current = head;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void printList() {
        if (isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        ListNode<E> current = head;
        while (current != null) {
            System.out.print(current.getValue());
            if (current.getNext() != null) {
                System.out.print(" -> ");
            }
            current = current.getNext();
        }
        System.out.println();
    }

    // 10. int indexOf(E value)
    public int indexOf(E value) {
        ListNode<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    // Helper method to get the node at the specified index
    private ListNode<E> getNodeAtIndex(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        ListNode<E> current = head;
        for (int i = 0; i < index; i++) {
            if(current == null)
                return null;
            current = current.getNext();
        }
        return current;
    }
}