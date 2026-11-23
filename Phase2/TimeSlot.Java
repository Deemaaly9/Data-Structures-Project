/**
 * A half-open time interval [start, end): start is the pickup date/time and
 * end is the drop-off date/time.
 *
 * Two slots are compared by position on the time line, and slots that overlap
 * compare as equal (compareTo returns 0). This lets a schedule BST detect a
 * conflict with a single findKey search.
 */
public class TimeSlot implements ITimeSlot {

    private final IDateTime start; // pickup date/time (inclusive)
    private final IDateTime end;   // drop-off date/time (exclusive)

    /**
     * Creates a time slot.
     *
     * @throws IllegalArgumentException if either date/time is null or if
     *                                  start is not strictly before end
     */
    public TimeSlot(IDateTime start, IDateTime end) {
        if (start == null || end == null)
            throw new IllegalArgumentException("Start and end must not be null.");
        if (start.compareTo(end) >= 0)
            throw new IllegalArgumentException("Start must be before end.");
        this.start = start;
        this.end = end;
    }

    @Override
    public IDateTime getStart() {
        return start;
    }

    @Override
    public IDateTime getEnd() {
        return end;
    }

    /**
     * Negative if this slot ends at or before the other starts, positive if
     * this slot starts at or after the other ends, and 0 if they overlap.
     * A slot that starts exactly when another ends does NOT overlap it.
     * O(1).
     */
    @Override
    public int compareTo(ITimeSlot other) {
        if (end.compareTo(other.getStart()) <= 0)
            return -1; // this slot is completely before the other
        if (start.compareTo(other.getEnd()) >= 0)
            return 1;  // this slot is completely after the other
        return 0;      // the two slots overlap
    }

    @Override
    public String toString() {
        return start.format() + " - " + end.format();
    }
}
