package org.example;

import java.util.Objects;

public record State (int x, int y, String direction){
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return x == state.x && y == state.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
