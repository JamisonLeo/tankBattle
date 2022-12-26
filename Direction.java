package tankbattle;

public enum Direction {
    UP("上"),
    RIGHT("右"),
    DOWN("下"),
    LEFT("左");
    
    private final String direction;
    
    Direction(String direction) {
        this.direction = direction;
    }
    
    public String getDirection() {
        return direction;
    }
}
