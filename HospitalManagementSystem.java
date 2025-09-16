import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

// Doctor class
class Doctor {
    int id;
    String name;
    String specialization;

    Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    void displayDoctor() {
        System.out.println("ID: " + id + ", Name: " + name + ", Specialization: " + specialization);
    }
}

// Patient class
class Patient {
    int id;
    String name;
    boolean isEmergency;

    Patient(int id, String name, boolean isEmergency) {
        this.id = id;
        this.name = name;
        this.isEmergency = isEmergency;
    }

    void displayPatient() {
        System.out.println("ID: " + id + ", Name: " + name + ", Emergency: " + isEmergency);
    }
}

// Appointment class
class Appointment {
    Doctor doctor;
    Patient patient;
    String date;

    Appointment(Doctor doctor, Patient patient, String date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }

    void displayAppointment() {
        System.out.println("Appointment: " + patient.name + " with Dr. " + doctor.name + " on " + date);
    }
}

// Hospital Management System
public class HospitalManagementSystem {
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();
    private LinkedList<Appointment> appointments = new LinkedList<>();

    Scanner sc = new Scanner(System.in);

    void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Doctor Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Specialization: ");
        String spec = sc.nextLine();
        doctors.add(new Doctor(id, name, spec));
    }

    void addPatient() {
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        System.out.print("Is Emergency (true/false): ");
        boolean emergency = sc.nextBoolean();
        patients.add(new Patient(id, name, emergency));
    }

    void makeAppointment() {
        System.out.println("Select Patient:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i).name);
        }
        int pIndex = sc.nextInt() - 1;

        System.out.println("Select Doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i).name);
        }
        int dIndex = sc.nextInt() - 1;
        sc.nextLine();
        System.out.print("Enter Appointment Date: ");
        String date = sc.nextLine();

        appointments.add(new Appointment(doctors.get(dIndex), patients.get(pIndex), date));
        System.out.println("Appointment booked successfully!");
    }

    void showDoctors() {
        System.out.println("Doctors List:");
        for (Doctor d : doctors) d.displayDoctor();
    }

    void showPatients() {
        System.out.println("Patients List:");
        for (Patient p : patients) p.displayPatient();
    }

    void showAppointments() {
        System.out.println("Appointments List:");
        for (Appointment a : appointments) a.displayAppointment();
    }

    public static void main(String[] args) {
        HospitalManagementSystem hms = new HospitalManagementSystem();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Hospital Management System ---");
            
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Make Appointment");
            System.out.println("4. Show Doctors");
            System.out.println("5. Show Patients");
            System.out.println("6. Show Appointments");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                hms.addDoctor();
                break;
                case 2:
                hms.addPatient();
                break;
                case 3:
                hms.makeAppointment();
                break;
                case 4:
                hms.showDoctors();
                case 5:
                hms.showPatients();
                break;
                case 6:
                hms.showAppointments();
                break;
                case 0:
                System.out.println("Exiting...");
                break;
                default:
                System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }
}
