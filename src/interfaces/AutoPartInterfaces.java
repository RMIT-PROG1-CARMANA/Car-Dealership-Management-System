package interfaces;

import part.AutoPart;

public interface AutoPartInterfaces {
    void addPart();

    // Method to view part details with validation
    void displayPartDetails();

    void updatePart();
    void deletePart();

    boolean deletePart(String partID);

    void listAllParts();

}
