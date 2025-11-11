package org.example.implementations;

import org.example.tda.GraphADT;
import org.example.tda.SetADT;
import org.example.implementations.nodes.VertexNode;
import org.example.implementations.nodes.EdgeNode;
import org.example.utils.exceptions.EmptyStructureException;
import org.example.utils.exceptions.ElementNotFoundException;
import org.example.utils.exceptions.DuplicateElementException;

public class DynamicGraphADT implements GraphADT {

    private VertexNode firstVertex;

    @Override
    public SetADT getVertxs() {
        SetADT set = new DynamicSetADT();
        VertexNode current = this.firstVertex;
        while (current != null) {
            set.add(current.getValue());
            current = current.getNext();
        }
        return set;
    }

    @Override
    public void addVertx(int vertex) {
        if (this.firstVertex == null) {
            this.firstVertex = new VertexNode(vertex, null, null);
            return;
        }

        VertexNode current = this.firstVertex;
        while (current.getNext() != null) {
            if (current.getValue() == vertex) {
                throw new DuplicateElementException("El vértice ya existe: " + vertex);
            }
            current = current.getNext();
        }

        if (current.getValue() == vertex) {
            throw new DuplicateElementException("El vértice ya existe: " + vertex);
        }

        current.setNext(new VertexNode(vertex, null, null));
    }

    @Override
    public void removeVertx(int vertex) {
        if (this.firstVertex == null) {
            throw new EmptyStructureException("El grafo está vacío");
        }

        // Eliminar aristas que apunten a este vértice
        VertexNode current = this.firstVertex;
        while (current != null) {
            EdgeNode edge = current.getEdgeNode();
            EdgeNode prev = null;
            while (edge != null) {
                if (edge.getTo().getValue() == vertex) {
                    if (prev == null) {
                        current.setEdgeNode(edge.getNext());
                    } else {
                        prev.setNext(edge.getNext());
                    }
                } else {
                    prev = edge;
                }
                edge = edge.getNext();
            }
            current = current.getNext();
        }

        // Eliminar el vértice de la lista
        if (this.firstVertex.getValue() == vertex) {
            this.firstVertex = this.firstVertex.getNext();
            return;
        }

        VertexNode prevVertex = this.firstVertex;
        VertexNode currentVertex = this.firstVertex.getNext();

        while (currentVertex != null) {
            if (currentVertex.getValue() == vertex) {
                prevVertex.setNext(currentVertex.getNext());
                return;
            }
            prevVertex = currentVertex;
            currentVertex = currentVertex.getNext();
        }

        throw new ElementNotFoundException("El vértice no existe: " + vertex);
    }

    @Override
    public void addEdge(int vertxOne, int vertxTwo, int weight) {
        VertexNode from = null;
        VertexNode to = null;

        VertexNode current = this.firstVertex;
        while (current != null) {
            if (current.getValue() == vertxOne) from = current;
            if (current.getValue() == vertxTwo) to = current;
            current = current.getNext();
        }

        if (from == null || to == null) {
            throw new ElementNotFoundException("Alguno de los vértices no existe");
        }

        EdgeNode edge = from.getEdgeNode();
        if (edge == null) {
            from.setEdgeNode(new EdgeNode(to, weight, null));
            return;
        }

        while (edge.getNext() != null) {
            if (edge.getTo().getValue() == vertxTwo) {
                throw new DuplicateElementException("La arista ya existe entre " + vertxOne + " y " + vertxTwo);
            }
            edge = edge.getNext();
        }

        if (edge.getTo().getValue() == vertxTwo) {
            throw new DuplicateElementException("La arista ya existe entre " + vertxOne + " y " + vertxTwo);
        }

        edge.setNext(new EdgeNode(to, weight, null));
    }

    @Override
    public void removeEdge(int vertxOne, int vertxTwo) {
        VertexNode from = this.firstVertex;
        while (from != null && from.getValue() != vertxOne) {
            from = from.getNext();
        }

        if (from == null) {
            throw new ElementNotFoundException("El vértice no existe: " + vertxOne);
        }

        EdgeNode edge = from.getEdgeNode();
        if (edge == null) {
            throw new ElementNotFoundException("La arista no existe");
        }

        if (edge.getTo().getValue() == vertxTwo) {
            from.setEdgeNode(edge.getNext());
            return;
        }

        EdgeNode prev = edge;
        edge = edge.getNext();
        while (edge != null) {
            if (edge.getTo().getValue() == vertxTwo) {
                prev.setNext(edge.getNext());
                return;
            }
            prev = edge;
            edge = edge.getNext();
        }

        throw new ElementNotFoundException("La arista no existe");
    }

    @Override
    public boolean existsEdge(int vertxOne, int vertxTwo) {
        VertexNode from = this.firstVertex;
        while (from != null && from.getValue() != vertxOne) {
            from = from.getNext();
        }

        if (from == null) return false;

        EdgeNode edge = from.getEdgeNode();
        while (edge != null) {
            if (edge.getTo().getValue() == vertxTwo) return true;
            edge = edge.getNext();
        }
        return false;
    }

    @Override
    public int edgeWeight(int vertxOne, int vertxTwo) {
        VertexNode from = this.firstVertex;
        while (from != null && from.getValue() != vertxOne) {
            from = from.getNext();
        }

        if (from == null) {
            throw new ElementNotFoundException("El vértice no existe: " + vertxOne);
        }

        EdgeNode edge = from.getEdgeNode();
        while (edge != null) {
            if (edge.getTo().getValue() == vertxTwo) return edge.getWeight();
            edge = edge.getNext();
        }

        throw new ElementNotFoundException("La arista no existe");
    }

    @Override
    public boolean isEmpty() {
        return this.firstVertex == null;
    }
}

