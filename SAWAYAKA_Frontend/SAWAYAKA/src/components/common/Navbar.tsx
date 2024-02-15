import { Link } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";
import { useUser } from "../../context/UserContext";
import cat from "../../assets/images/cat_zakuro.png";
function Navbar() {
  const { isAuthenticated, logout } = useAuth();
  const { user } = useUser();

  return (
    <nav className="bg-gradient-to-r from-ctp-lavender/5  to-ctp-base  border border-ctp-lavender/50  mb-3 flex justify-between py-5 pr-10 rounded-lg">
      <div className="flex items-center">
        <img src={cat} className="w-10 h-10" alt="Cat Image" />
        <h1 className="text-2xl font-bold px-2 text-ctp-mauve italic tracking-wide">
          <Link to="/">Sawayaka</Link>
        </h1>
      </div>

      <ul className="flex gap-x-2">
        {isAuthenticated ? (
          <>
            <li className="text-ctp-mauve/90 font-semibold">
              Welcome {user.username}
            </li>
            <li>
              <Link
                to="/new-thread"
                className="text-ctp-base font-bold bg-ctp-lavender hover:bg-ctp-mauve active:bg-ctp-mauve/75 px-4 py-1 rounded-sm"
              >
                New Thread
              </Link>
            </li>
            <li>
              <Link
                to="/profile"
                className="text-ctp-base font-bold bg-ctp-lavender hover:bg-ctp-mauve active:bg-ctp-mauve/75 px-4 py-1 rounded-sm"
              >
                Profile
              </Link>
            </li>
            <li>
              <Link
                to="/"
                className="text-ctp-base font-bold bg-ctp-maroon hover:bg-ctp-red active:bg-ctp-red/75 px-4 py-1 rounded-sm"
                onClick={() => {
                  logout();
                }}
              >
                Logout
              </Link>
            </li>
          </>
        ) : (
          <></>
        )}
      </ul>
    </nav>
  );
}

export default Navbar;
