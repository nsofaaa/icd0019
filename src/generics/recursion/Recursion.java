package generics.recursion;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Recursion {

    public List<String> getParts(Path path) {
        // a)
        if (path == null) {
            return new ArrayList<>();
        }
        List<String> parts = getParts(path.getParent());
        parts.add(path.getFileName().toString());
        return parts;
    }

    public List<String> getParts2(Path path) {
        // b)
        return getParts3(path, new ArrayList<>());
    }

    public List<String> getParts3(Path path, List<String> parts) {
        // c)
        if (path != null) {
            getParts3(path.getParent(), parts);
            parts.add(path.getFileName().toString());
        }
        return parts;
    }

    public List<String> getParts4(Path path) {
        // d)
        List<String> parts = new ArrayList<>();
        for (Path p : path) {
            parts.add(p.toString());
        }
        return parts;
    }
}