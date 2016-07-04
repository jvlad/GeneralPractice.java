package com.freeraven.datastructures.linkedlist.implementation.singlylinked;

import com.freeraven.datastructures.linkedlist.implementation.LinkedList;

/**
 * Created by zvlad on 6/29/16.
 */
public class SinglyLinkedListNode<T> implements LinkedList<T> {
    private T data;
    private int size = 0;
    private SinglyLinkedListNode<T> next;
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;

    public SinglyLinkedListNode() {
    }

    @Override
    public void append(T obj) {
        if (size == 0) {
            createFirstNode(obj);
        } else {
            appendToTail(obj);
        }
        size++;
    }

    private void createFirstNode(T obj) {
        head = new SinglyLinkedListNode<T>();
        tail = head;
        head.data = obj;
    }

    private void appendToTail(T obj) {
        tail.next = new SinglyLinkedListNode<T>();
        tail.next.data = obj;
        tail = tail.next;
    }

    @Override
    public T getData(int targetPosition) {
        return getNodeAtPosition(targetPosition).data;
    }

    @Override
    public void deleteNodeAt(int targetPosition) {
        if (targetPosition == 0) {
            deleteFirstNode();
        } else {
            deleteNotFirstNode(targetPosition);
        }
        size--;
    }

    private void deleteFirstNode() {
        head = head.next;
    }

    private void deleteNotFirstNode(int targetPosition) {
        SinglyLinkedListNode<T> nodeBeforeTarget = getNodeAtPosition(targetPosition - 1);
        SinglyLinkedListNode<T> targetNode = nodeBeforeTarget.next;
        if (targetNode != tail) {
            nodeBeforeTarget.next = targetNode.next;
        } else {
            nodeBeforeTarget.next = null;
            tail = nodeBeforeTarget;
        }
    }

    private SinglyLinkedListNode<T> getNodeAtPosition(int targetPosition) {
        checkPositionIsValid(targetPosition);
        SinglyLinkedListNode<T> targetNode = head;
        for (int i = 0; i < targetPosition; i++) {
            targetNode = targetNode.next;
        }
        return targetNode;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        String itemsDelimiter = ", ";
        SinglyLinkedListNode<T> currentNode = head;
        while (currentNode != null) {
            builder.append(currentNode.data);
            builder.append(itemsDelimiter);
            currentNode = currentNode.next;
        }
        int trailingDelimiterPosition = builder.length() - itemsDelimiter.length();
        builder.delete(trailingDelimiterPosition, trailingDelimiterPosition + itemsDelimiter.length());
        builder.append("]");
        return builder.toString();
    }

    private void checkPositionIsValid(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
