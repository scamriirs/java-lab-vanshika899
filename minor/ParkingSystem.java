import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

enum VehicleType {
    CAR, BIKE;

    public static Optional<VehicleType> fromInt(int i) {
        return switch (i) {
            case 1 -> Optional.of(CAR);
            case 2 -> Optional.of(BIKE);
            default -> Optional.empty();
        };
    }
}

class ParkingSlot {
    private final int id;
    private final VehicleType type;
    private volatile boolean occupied;

    public ParkingSlot(int id, VehicleType type) {
        this.id = id;
        this.type = type;
        this.occupied = false;
    }

    public int getId() { return id; }
    public VehicleType getType() { return type; }
    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }

    @Override
    public String toString() {
        return String.format("Slot %2d (%s) - %s", id, type, (occupied ? "Occupied" : "Free"));
    }
}

record Vehicle(String number, VehicleType type) {}

class Ticket {
    private final int ticketId;
    private final Vehicle vehicle;
    private final int slotId;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double fee;
    private boolean closed;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Ticket(int ticketId, Vehicle vehicle, int slotId, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slotId = slotId;
        this.entryTime = entryTime;
        this.closed = false;
    }

    public int getTicketId() { return ticketId; }
    public Vehicle getVehicle() { return vehicle; }
    public int getSlotId() { return slotId; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public double getFee() { return fee; }
    public boolean isClosed() { return closed; }

    public void close(LocalDateTime exitTime, double fee) {
        this.exitTime = exitTime;
        this.fee = fee;
        this.closed = true;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("Ticket ID: ").append(ticketId)
          .append(", Vehicle: ").append(vehicle.number())
          .append(" (").append(vehicle.type()).append(")")
          .append(", Slot: ").append(slotId)
          .append(", Entry: ").append(entryTime.format(FMT));
        if (exitTime != null) {
            sb.append(", Exit: ").append(exitTime.format(FMT))
              .append(String.format(", Fee: Rs %.2f", fee));
        }
        return sb.toString();
    }
}

class ParkingLog {
    private final int ticketId;
    private final String vehicleNumber;
    private final VehicleType vehicleType;
    private final int slotId;
    private final LocalDateTime entryTime;
    private final LocalDateTime exitTime;
    private final double fee;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public ParkingLog(int ticketId, String vehicleNumber, VehicleType vehicleType,
                      int slotId, LocalDateTime entryTime, LocalDateTime exitTime, double fee) {
        this.ticketId = ticketId;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.slotId = slotId;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.fee = fee;
    }

    @Override
    public String toString() {
        return String.format("Log - Ticket: %d, Vehicle: %s (%s), Slot: %d, Entry: %s, Exit: %s, Fee: Rs %.2f",
                ticketId, vehicleNumber, vehicleType, slotId,
                entryTime.format(FMT), exitTime.format(FMT), fee);
    }
}

class FeeCalculator {
    // Fee rule:
    // CAR : ₹30 for first hour, then ₹20 per extra hour
    // BIKE: ₹20 for first hour, then ₹10 per extra hour
    private static final double CAR_FIRST = 30.0;
    private static final double CAR_EXTRA = 20.0;
    private static final double BIKE_FIRST = 20.0;
    private static final double BIKE_EXTRA = 10.0;

    public static double calculateFee(LocalDateTime entry, LocalDateTime exit, VehicleType type) {
        long minutes = Math.max(1, Duration.between(entry, exit).toMinutes());
        // Round up to full hours
        long hours = (minutes + 59) / 60; // integer ceiling

        if (type == VehicleType.CAR) {
            return hours <= 1 ? CAR_FIRST : CAR_FIRST + (hours - 1) * CAR_EXTRA;
        } else {
            return hours <= 1 ? BIKE_FIRST : BIKE_FIRST + (hours - 1) * BIKE_EXTRA;
        }
    }
}

public class ParkingSystem {
    private final Map<Integer, ParkingSlot> slots = new LinkedHashMap<>();
    private final Map<Integer, Ticket> activeTickets = new ConcurrentHashMap<>();
    private final List<ParkingLog> logs = Collections.synchronizedList(new ArrayList<>());
    private final AtomicInteger nextTicketId = new AtomicInteger(1);
    private final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public ParkingSystem() {
        // Small mall: 10 car slots (1–10), 10 bike slots (11–20)
        for (int i = 1; i <= 10; i++) slots.put(i, new ParkingSlot(i, VehicleType.CAR));
        for (int i = 11; i <= 20; i++) slots.put(i, new ParkingSlot(i, VehicleType.BIKE));
    }

    public void start() {
        try {
            int choice;
            do {
                printMenu();
                choice = readInt("Enter your choice: ");
                switch (choice) {
                    case 1 -> handleEntry();
                    case 2 -> handleExit();
                    case 3 -> showCurrentStatus();
                    case 4 -> showLogs();
                    case 5 -> lookupActiveByVehicle();
                    case 6 -> System.out.println("Exiting system. Goodbye!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 6);
        } finally {
            scanner.close();
        }
    }

    private void printMenu() {
        System.out.println("\n===== Mall Parking Management System =====");
        System.out.println("1. Vehicle Entry");
        System.out.println("2. Vehicle Exit");
        System.out.println("3. Show Current Parking Status");
        System.out.println("4. Show Entry/Exit Logs");
        System.out.println("5. Lookup Active Ticket by Vehicle Number");
        System.out.println("6. Exit");
    }

    private void handleEntry() {
        System.out.println("\n--- Vehicle Entry ---");
        String number = readLine("Enter vehicle number: ").trim();
        if (number.isEmpty()) {
            System.out.println("Vehicle number cannot be empty.");
            return;
        }

        Optional<VehicleType> typeOpt = askVehicleType();
        if (typeOpt.isEmpty()) {
            System.out.println("Invalid vehicle type.");
            return;
        }
        VehicleType type = typeOpt.get();

        Optional<ParkingSlot> freeSlot = findFreeSlot(type);
        if (freeSlot.isEmpty()) {
            System.out.println("Sorry, no free slot available for " + type + " right now.");
            return;
        }

        ParkingSlot slot = freeSlot.get();
        slot.setOccupied(true);

        Vehicle vehicle = new Vehicle(number, type);
        LocalDateTime entryTime = LocalDateTime.now();
        int ticketId = nextTicketId.getAndIncrement();
        Ticket ticket = new Ticket(ticketId, vehicle, slot.getId(), entryTime);
        activeTickets.put(ticketId, ticket);

        System.out.println("Vehicle parked successfully!");
        System.out.println("Assigned Slot ID: " + slot.getId());
        System.out.println("Your Ticket ID: " + ticket.getTicketId());
        System.out.println("Entry Time: " + entryTime.format(DTF));
    }

    private void handleExit() {
        System.out.println("\n--- Vehicle Exit ---");
        int ticketId = readInt("Enter Ticket ID: ");

        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            System.out.println("Ticket not found or already closed.");
            return;
        }

        LocalDateTime exitTime = LocalDateTime.now();
        double fee = FeeCalculator.calculateFee(ticket.getEntryTime(), exitTime, ticket.getVehicle().type());
        // update ticket and data structures
        ticket.close(exitTime, fee);

        ParkingSlot slot = slots.get(ticket.getSlotId());
        if (slot != null) slot.setOccupied(false);

        ParkingLog log = new ParkingLog(
                ticket.getTicketId(),
                ticket.getVehicle().number(),
                ticket.getVehicle().type(),
                ticket.getSlotId(),
                ticket.getEntryTime(),
                ticket.getExitTime(),
                ticket.getFee()
        );
        logs.add(log);
        activeTickets.remove(ticketId);

        System.out.println("Vehicle exit processed.");
        System.out.println("Ticket ID: " + ticket.getTicketId());
        System.out.println("Vehicle: " + ticket.getVehicle().number());
        System.out.println("Slot Freed: " + ticket.getSlotId());
        System.out.printf("Total Fee: Rs %.2f%n", fee);
    }

    private void showCurrentStatus() {
        System.out.println("\n--- Current Parking Status ---");
        long freeCar = slots.values().stream()
                .filter(s -> s.getType() == VehicleType.CAR && !s.isOccupied())
                .count();
        long freeBike = slots.values().stream()
                .filter(s -> s.getType() == VehicleType.BIKE && !s.isOccupied())
                .count();

        System.out.println("Total Slots: " + slots.size());
        System.out.println("Free CAR slots: " + freeCar);
        System.out.println("Free BIKE slots: " + freeBike);
        System.out.println("\nSlot wise detail:");
        slots.values().forEach(System.out::println);

        System.out.println("\nActive Tickets:");
        if (activeTickets.isEmpty()) {
            System.out.println("No active tickets.");
        } else {
            activeTickets.values().forEach(System.out::println);
        }
    }

    private void showLogs() {
        System.out.println("\n--- Entry/Exit Logs ---");
        if (logs.isEmpty()) {
            System.out.println("No logs available yet.");
            return;
        }
        synchronized (logs) {
            logs.forEach(System.out::println);
        }
    }

    private void lookupActiveByVehicle() {
        String number = readLine("Enter vehicle number to lookup: ").trim();
        if (number.isEmpty()) {
            System.out.println("Vehicle number cannot be empty.");
            return;
        }
        List<Ticket> matches = activeTickets.values().stream()
                .filter(t -> t.getVehicle().number().equalsIgnoreCase(number))
                .collect(Collectors.toList());
        if (matches.isEmpty()) {
            System.out.println("No active ticket found for vehicle: " + number);
        } else {
            matches.forEach(System.out::println);
        }
    }

    private Optional<ParkingSlot> findFreeSlot(VehicleType type) {
        return slots.values().stream()
                .filter(s -> s.getType() == type && !s.isOccupied())
                .findFirst();
    }

    private Optional<VehicleType> askVehicleType() {
        System.out.println("Select Vehicle Type: ");
        System.out.println("1. Car");
        System.out.println("2. Bike");
        int choice = readInt("Enter choice: ");
        return VehicleType.fromInt(choice);
    }

    // --- Input helpers ---
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        new ParkingSystem().start();
    }
}
