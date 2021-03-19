public class Game {
    public static final int     MAX_GAME_OBJECTS = 1000;

    GameObject[] gameObjects;
    int objectCount;

    public Game() {
        gameObjects = new GameObject[MAX_GAME_OBJECTS];
        objectCount = 0;
    }

    public void add(GameObject obj) {
        if (objectCount < MAX_GAME_OBJECTS)
            gameObjects[objectCount++] = obj;
    }

    public void updateObjects(){
        for(int x=0;x<objectCount;x++){
            gameObjects[x].update();
        }
    }

    public Harmful[] harmfulObjects() {
        // First find out how many objects are Harmful
        int count = 0;
        for (GameObject g: gameObjects)
            if (g instanceof Harmful)
                count++;

        // Now create the array and fill it up
        Harmful[] bad = new Harmful[count];
        count = 0;
        for (GameObject g: gameObjects)
            if (g instanceof Harmful)
                bad[count++] = (Harmful)g;
        return bad;
    }

    public int assessDanger(){
        Harmful[] hurtOjects = harmfulObjects();
        int dmgTotal=0;
        for(Harmful h: hurtOjects){
            dmgTotal = dmgTotal +h.getDamageAmount();
        }
        return dmgTotal;
    }

    // The get methods
    public Object[] getGameObjects() { return gameObjects; }
    public int getObjectCount() { return objectCount; }

    public String toString() {
        return "Game with " + objectCount + " objects";
    }

    public void displayObjects() {
        for (int i=0; i<objectCount; i++)
            System.out.println(gameObjects[i]);
    }
}