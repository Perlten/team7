import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class State implements Comparable<State> {
    private Map<String, Set<Integer>> stateMap = new HashMap<>();


    public void addProperty(String key, Set<Integer> set) {
        this.stateMap.put(key, set);
    }

    public Set<Integer> getSet(String key){
        return this.stateMap.get(key);
    }

    public int getSingleValue(String key){
        return this.stateMap.get(key).iterator().next();
    }

    public void print(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {

        String str = "";
        for (String key: this.stateMap.keySet()) {
            str += key + ": " + this.stateMap.get(key) + "\n";
        }
        return str;
    }

    public State union(State other){
        State newState = new State();

        for (String key : this.stateMap.keySet()){
            Set<Integer> currentSet = this.stateMap.get(key);
            newState.addProperty(key, currentSet);
        }

        for (String key : other.stateMap.keySet()){
            Set<Integer> currentSet = other.stateMap.get(key);
            if(!newState.stateMap.containsKey(key)){
                newState.addProperty(key, currentSet);
            }else {
                newState.stateMap.get(key).addAll(currentSet);
            }
        }
        return newState;
    }

    public State intersection(State other){
        State newState = new State();

        for(String key : this.stateMap.keySet()){
            if(!other.stateMap.containsKey(key)){
                continue;
            }
            Set<Integer> otherSet = other.stateMap.get(key);
            Set<Integer> thisSet = this.stateMap.get(key);
            Set<Integer> finalSet = new HashSet<>();
            for(Integer thisInt : thisSet){
                if(otherSet.contains(thisInt)){
                    finalSet.add(thisInt);
                }
            }
            if(!finalSet.isEmpty()){
                newState.addProperty(key, finalSet);
            }
        }
        return newState;
    }


    @Override
    public int compareTo(State other) {
        for(String key : other.stateMap.keySet()){
            if(!this.stateMap.containsKey(key)){
                return -2;
            }
            Set<Integer> otherSet = other.stateMap.get(key);
            Set<Integer> thisSet = this.stateMap.get(key);
            for(Integer otherInt : otherSet){
                if(!thisSet.contains(otherInt)){
                    return -2;
                }
            }
        };
        return 1;
    }
}
