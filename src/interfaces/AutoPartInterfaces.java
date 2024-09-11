package interfaces;

import part.AutoPart;

public interface AutoPartInterfaces {
    void addPart();
    void viewPartDetails();
    void updatePart();
    void deletePart();

    boolean deletePart(String partID);

    void listAllParts();
    AutoPart getPartByID(String partID);  // This method should return AutoPart
}
