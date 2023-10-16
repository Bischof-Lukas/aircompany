import Planes.Plane;
import java.util.Comparator;
import java.util.List;

//Refactoring: Erstellen dieser Klasse und Bündeln der Sort Methoden
public class PlaneSorter {
    public static Airport sortByMaxDistance(Airport airport) {
        List<? extends Plane> planes = airport.getPlanes();
        planes.sort(Comparator.comparing(Plane::getMaxFlightDistance));
        return new Airport(planes);
    }

    public static Airport sortByMaxSpeed(Airport airport) {
        List<? extends Plane> planes = airport.getPlanes();
        planes.sort(Comparator.comparing(Plane::getMaxSpeed));
        return new Airport(planes);
    }

    public static Airport sortByMaxLoadCapacity(Airport airport) {
        List<? extends Plane> planes = airport.getPlanes();
        planes.sort(Comparator.comparing(Plane::getMinLoadCapacity));
        return new Airport(planes);
    }
}