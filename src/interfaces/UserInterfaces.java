package interfaces;

import user.User;

public interface UserInterfaces {
    void createUser();
    void displayAllUsers();
    void displayInfoUsers();

    void updateUser();

    void deleteUser();

    void displayInfoUsersMembership();

    void updateProfile(User loggedUser);
}
