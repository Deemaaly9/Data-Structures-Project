/**
 * Abstract base class of Rider and Driver: the fields and behavior shared by
 * every person in the system. Each person owns a schedule, a BST of their
 * rides keyed by time slot (no two rides in a schedule overlap).
 */
public abstract class Person implements IPerson {

    private final int id;                          // unique ID, never changes
    private final String name;                     // full name, never changes
    private String phoneNumber;                    // exactly 10 digits
    private final IBST<ITimeSlot, IRide> schedule; // this person's rides by time slot

    /**
     * Creates a person with an empty schedule.
     *
     * @throws IllegalArgumentException if the name is null/empty or the phone
     *                                  number is not exactly 10 digits
     */
    protected Person(int id, String name, String phoneNumber) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name must not be empty.");
        this.id = id;
        this.name = name;
        if (!isValidPhoneNumber(phoneNumber)) // not setPhoneNumber: it can be overridden
            throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
        this.phoneNumber = phoneNumber;
        this.schedule = new BST<ITimeSlot, IRide>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number.
     *
     * @throws IllegalArgumentException unless it is exactly 10 digits
     */
    @Override
    public void setPhoneNumber(String phoneNumber) {
        if (!isValidPhoneNumber(phoneNumber))
            throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
        this.phoneNumber = phoneNumber;
    }

    @Override
    public IBST<ITimeSlot, IRide> getSchedule() {
        return schedule;
    }

    /** Returns true if the string is not null and made of exactly 10 digits. */
    private static boolean isValidPhoneNumber(String phone) {
        if (phone == null || phone.length() != 10)
            return false;
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID=" + id + ", Name=" + name + ", Phone=" + phoneNumber;
    }
}
