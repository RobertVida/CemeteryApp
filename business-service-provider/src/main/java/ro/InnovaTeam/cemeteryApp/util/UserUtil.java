package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.UserDTO;
import ro.InnovaTeam.cemeteryApp.model.User;

/**
 * Created by robert on 1/10/2015.
 */
public class UserUtil {

    public static User toDB(UserDTO userDTO) {
        if(userDTO == null){
            return null;
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setUserId(userDTO.getUserId());
        user.setRole(userDTO.getRole());
        user.setToken(userDTO.getToken());
        user.setId(userDTO.getId());

        return user;
    }

    public static UserDTO toDTO(User user) {
        if(user == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserId(user.getUserId());
        userDTO.setRole(user.getRole());
        userDTO.setToken(user.getToken());
        userDTO.setId(user.getId());

        return userDTO;
    }
}
