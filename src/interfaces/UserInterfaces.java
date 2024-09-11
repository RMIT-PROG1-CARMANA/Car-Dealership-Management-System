package interfaces;

import user.User;

public interface UserInterfaces {
    void createUser();
    void displayAllUsers();
    void displayInfoUsers();
    void deleteUser();
    void editProfile(User loggedUser);
}
