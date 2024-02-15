import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/common/Navbar";
import { AuthProvider } from "./context/AuthContext";
import { ThreadProvider } from "./context/ThreadContext";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import HomePage from "./pages/HomePage";
import ProfilePage from "./pages/ProfilePage";
import ThreadDetailPage from "./pages/ThreadDetailPage";
import ThreadCreatePage from "./pages/ThreadCreatePage";
import ProtectedRoute from "./ProtectedRoute";
import { CommentProvider } from "./context/CommentContext";
import { UserProvider } from "./context/UserContext";

function App() {
  return (
    <BrowserRouter>
      <UserProvider>
        <AuthProvider>
          <ThreadProvider>
            <CommentProvider>
              <main className="container mx-auto px10 min-h-screen">
                <Navbar></Navbar>
                <Routes>
                  <Route path="/login" element={<LoginPage />}></Route>
                  <Route path="/register" element={<RegisterPage />}></Route>

                  <Route element={<ProtectedRoute></ProtectedRoute>}>
                    <Route path="/" element={<HomePage></HomePage>}></Route>
                    <Route path="/profile" element={<ProfilePage />}></Route>
                    <Route
                      path="/threads/:id"
                      element={<ThreadDetailPage />}
                    ></Route>
                    <Route
                      path="/new-thread"
                      element={<ThreadCreatePage />}
                    ></Route>
                  </Route>
                </Routes>
              </main>
            </CommentProvider>
          </ThreadProvider>
        </AuthProvider>
      </UserProvider>
    </BrowserRouter>
  );
}

export default App;
