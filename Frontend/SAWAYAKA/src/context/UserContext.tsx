import { createContext, useContext, useState } from "react";
import {
  deleteCurrentUserRequest,
  updateCurrentUserRequest,
} from "../services/userService";

type UserContextType = {
  user: any;
  setUser: React.Dispatch<React.SetStateAction<any>>;
  updateCurrentUser: (user: any) => any;
  deleteCurrentUser: () => any;
};

const UserContext = createContext<UserContextType>({
  user: null,
  setUser: () => {},
  updateCurrentUser: async () => {},
  deleteCurrentUser: async () => {},
});

export const useUser = () => {
  const context = useContext(UserContext);
  if (!context) {
    throw new Error("useUser must be used within a UserProvider");
  }
  return context;
};

export function UserProvider({ children }: any) {
  const [user, setUser] = useState(null);

  const updateCurrentUser = async (user: any) => {
    try {
      const res = await updateCurrentUserRequest(user);
      console.log(res.data);
      setUser(res.data);
    } catch (error) {
      console.log(error);
    }
  };

  const deleteCurrentUser = async () => {
    try {
      const res = await deleteCurrentUserRequest();
      console.log(res.data);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <UserContext.Provider
      value={{ user, deleteCurrentUser, setUser, updateCurrentUser }}
    >
      {children}
    </UserContext.Provider>
  );
}
