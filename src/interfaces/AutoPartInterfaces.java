package interfaces;


public interface AutoPartInterfaces {
    void addPart();
    // Method to view part details with validation
    void searchPartByID();
    void updatePart();
    void deletePart();

    boolean deletePart(String partID);

    void listAllParts();

}
