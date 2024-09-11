package interfaces;

public interface ServiceInterfaces {
    void addService();
    void getServiceByID();
    void updateService();
    void deleteService();
    void addPartToService(String serviceID, String partID);
    void removePartFromService(String serviceID);
    void listAllServices();

}
