/**
 * A driver. Drivers are compared by ID. The vehicle plate and vehicle type
 * are BST keys in the system's indexes, so they have no setters.
 */
public class Driver extends Person implements IDriver {

    private final String vehiclePlate;      // 3 uppercase letters + 4 digits
    private final VehicleType vehicleType;

    /**
     * Creates a driver.
     *
     * @throws IllegalArgumentException if the phone number is not 10 digits,
     *                                  the plate is not 3 uppercase letters
     *                                  followed by 4 digits, or the vehicle
     *                                  type is null
     */
    public Driver(int id, String name, String phoneNumber,
                  String vehiclePlate, VehicleType vehicleType) {
        super(id, name, phoneNumber);
        if (!isValidPlate(vehiclePlate))
            throw new IllegalArgumentException(
                    "Plate must be 3 uppercase letters followed by 4 digits (e.g., ABC1234).");
        if (vehicleType == null)
            throw new IllegalArgumentException("Vehicle type must not be null.");
        this.vehiclePlate = vehiclePlate;
        this.vehicleType = vehicleType;
    }

    @Override
    public String getVehiclePlate() {
        return vehiclePlate;
    }

    @Override
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /** Compares drivers by ID. */
    @Override
    public int compareTo(IDriver other) {
        return Integer.compare(getId(), other.getId());
    }

    /** Returns true for exactly 3 uppercase letters (A-Z) followed by 4 digits. */
    private static boolean isValidPlate(String plate) {
        if (plate == null || plate.length() != 7)
            return false;
        for (int i = 0; i < 3; i++) {
            char c = plate.charAt(i);
            if (c < 'A' || c > 'Z')
                return false;
        }
        for (int i = 3; i < 7; i++) {
            char c = plate.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Driver [" + super.toString() + ", Plate=" + vehiclePlate
                + ", Vehicle=" + vehicleType + "]";
    }
}
