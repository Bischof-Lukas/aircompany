import Planes.experimentalPlane;
import models.MilitaryType;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.*;
import java.util.stream.Collectors;

// version: 1.1
// made by Vitali Shulha
// 4-Jan-2019

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    // Refactoring: Code zum Filtern der Flugzeuge nach Type extrahiert
    private <T extends Plane> List<T> filterPlanesByType(Class<T> planeType) {
        return planes.stream()
                .filter(planeType::isInstance)
                .map(planeType::cast)
                .collect(Collectors.toList());
    }

    // Refactoring: Code zum Filtern der Militärflugzeuge nach Type extrahiert
    private List<MilitaryPlane> filterMilitaryPlanesByType(MilitaryType type) {
        return planes.stream()
                .filter(plane -> plane instanceof MilitaryPlane)
                .map(plane -> (MilitaryPlane) plane)
                .filter(plane -> plane.getType() == type)
                .collect(Collectors.toList());
    }

    // Refactoring: Helferfunktion
    private void sortPlanesByParameter(Comparator<Plane> comparator) {
        planes.sort(comparator);
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return filterPlanesByType(PassengerPlane.class);
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return filterPlanesByType(MilitaryPlane.class);
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        // Get passenger planes and find the one with the maximum capacity
        List<PassengerPlane> passengerPlanes = getPassengerPlanes();
        PassengerPlane result = passengerPlanes.stream()
                .max(Comparator.comparing(PassengerPlane::getPassengersCapacity))
                .orElse(null);
        return result;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return filterMilitaryPlanesByType(MilitaryType.TRANSPORT);
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return filterMilitaryPlanesByType(MilitaryType.BOMBER);
    }

    public List<experimentalPlane> getExperimentalPlanes() {
        return filterPlanesByType(experimentalPlane.class);
    }

    public Airport sortByMaxDistance() {
        // Refactoring: Sortieren in eigene Klasse PlaneSorter ausgelagert
        return PlaneSorter.sortByMaxDistance(this);
    }

    public Airport sortByMaxSpeed() {
        // Refactoring: Sortieren in eigene Klasse PlaneSorter ausgelagert
        return PlaneSorter.sortByMaxSpeed(this);
    }

    public Airport sortByMaxLoadCapacity() {
        // Refactoring: Sortieren in eigene Klasse PlaneSorter ausgelagert
        return PlaneSorter.sortByMaxLoadCapacity(this);
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes +
                '}';
    }
}