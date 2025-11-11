package org.example.utils;

import org.example.tda.GraphADT;
import org.example.tda.SetADT;
import org.example.implementations.DynamicGraphADT;
import org.example.implementations.DynamicSetADT;

public class GraphADTutil {
    public static void print(GraphADT graph) {
        GraphADT copy = copy(graph);
        SetADT vertices = copy.getVertxs();

        while (!vertices.isEmpty()) {
            int vertex = vertices.choose();

            System.out.print("Vértice " + vertex + " tiene las siguientes aristas: ");

            SetADT allVertices = copy.getVertxs();
            boolean hasEdges = false;

            while (!allVertices.isEmpty()) {
                int otherVertex = allVertices.choose();
                if (copy.existsEdge(vertex, otherVertex)) {
                    System.out.print(otherVertex + " ");
                    hasEdges = true;
                }
                allVertices.remove(otherVertex);
            }

            if (!hasEdges) {
                System.out.print("ninguna");
            }

            System.out.println();
            vertices.remove(vertex);
        }
    }
    public static GraphADT copy(GraphADT graph) {
        GraphADT aux = new DynamicGraphADT();

        SetADT vertices = new DynamicSetADT();
        SetADTutil.copy(graph.getVertxs(), vertices);
        while (!vertices.isEmpty()) {
            int vertex = vertices.choose();
            aux.addVertx(vertex);
            vertices.remove(vertex);
        }

        vertices = graph.getVertxs();
        while (!vertices.isEmpty()) {
            int vertex = vertices.choose();
            SetADT adjacentVertices = graph.getVertxs();

            while (!adjacentVertices.isEmpty()) {
                int adjacentVertex = adjacentVertices.choose();
                if (graph.existsEdge(vertex, adjacentVertex)) {
                    int weight = graph.edgeWeight(vertex, adjacentVertex);
                    aux.addEdge(vertex, adjacentVertex, weight);
                }
                adjacentVertices.remove(adjacentVertex);
            }
            vertices.remove(vertex);
        }

        return aux;
    }
}

