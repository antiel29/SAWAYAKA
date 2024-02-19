import { useUser } from "../context/UserContext";
import { useAuth } from "../context/AuthContext";
import UserDetail from "../components/profile/UserDetail";
import Button from "../components/common/Button";

function ProfilePage() {
  const { user, updateCurrentUser, deleteCurrentUser } = useUser();
  const { logout } = useAuth();

  const handleEdit = async (editedUser: any) => {
    await updateCurrentUser(editedUser);
  };

  const handleSubmit = async () => {
    await deleteCurrentUser();
    await logout();
  };

  return (
    <div>
      <UserDetail user={user} onEdit={handleEdit} />
      <Button text="Delete" onClick={handleSubmit}></Button>
    </div>
  );
}

export default ProfilePage;
