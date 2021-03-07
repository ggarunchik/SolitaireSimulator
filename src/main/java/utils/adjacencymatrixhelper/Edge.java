package utils.adjacencymatrixhelper;

public class Edge {
    public int src, dest;

    Edge(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }

    public int getSrc() {
        return src;
    }

    public int getDest() {
        return dest;
    }

    @Override
    public String toString() {
        return src + "," + dest;
    }

}
