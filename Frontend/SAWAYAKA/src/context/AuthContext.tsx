import { createContext, useState, useContext, useEffect } from "react";
import {
  registerRequest,
  loginRequest,
  verifyRequest,
  logoutRequest,
} from "../services/authService";
import { useUser } from "./UserContext";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";

type AuthContextType = {
  signup: (user: any) => Promise<void>;
  signin: (user: any) => Promise<void>;
  logout: () => Promise<void>;
  isAuthenticated: boolean;
  errors: any;
  loading: boolean;
};

export const AuthContext = createContext<AuthContextType>({
  signup: async () => {},
  signin: async () => {},
  logout: async () => {},
  loading: true,
  isAuthenticated: false,
  errors: null,
});

export const useAuth = () => {
  const context = useContext(AuthContext);

  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};

export const AuthProvider = ({ children }: any) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [errors, setErrors] = useState<string | any>([]);
  const [loading, setLoading] = useState(true);
  const { setUser } = useUser();
  const navigate = useNavigate();

  const signup = async (user: any) => {
    try {
      await registerRequest(user);
      navigate("/login");
    } catch (error: any) {
      if (error.response.status === 400) {
        const errorMessage = error.response.data.join("\n");
        setErrors(errorMessage);
      } else {
        setErrors(error.response.data);
      }
    }
  };

  const logout = async () => {
    try {
      const cookies = Cookies.get();
      await logoutRequest(cookies.Authorization);
      Cookies.remove("Authorization");
      setIsAuthenticated(false);
      setUser(null);
    } catch (error) {
      console.log(error);
    }
  };

  const signin = async (user: any) => {
    try {
      const res = await loginRequest(user);

      setUser(res.data.userDto);
      setIsAuthenticated(true);
      Cookies.set("Authorization", res.data.accessToken, {
        expires: 1,
        sameSite: "none",
        secure: true,
      });
    } catch (error: any) {
      if (error.response.status === 401) {
        setErrors("Incorrect password");
      } else {
        setErrors(error.response.data);
      }
    }
  };

  useEffect(() => {
    if (errors.length > 0) {
      const timer = setTimeout(() => {
        setErrors([]);
      }, 5000);
      return () => clearTimeout(timer);
    }
  }, [errors]);

  useEffect(() => {
    async function checkLogin() {
      const cookies = Cookies.get();

      if (!cookies.Authorization) {
        setIsAuthenticated(false);
        setLoading(false);
        return setUser(null);
      }

      try {
        const res = await verifyRequest(cookies.Authorization);
        if (!res.data) {
          setIsAuthenticated(false);
          setLoading(false);
          return setUser(null);
        }
        setIsAuthenticated(true);
        setUser(res.data);
        setLoading(false);
      } catch (error) {
        setIsAuthenticated(false);
        setLoading(false);
        setUser(null);
      }
    }
    checkLogin();
  }, []);

  return (
    <AuthContext.Provider
      value={{
        signup,
        signin,
        logout,
        isAuthenticated,
        errors,
        loading,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};
