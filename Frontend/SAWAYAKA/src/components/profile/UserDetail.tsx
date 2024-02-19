import { useState } from "react";
import Button from "../common/Button";

function UserDetail({ user, onEdit }: any) {
  const [isEditing, setIsEditing] = useState(false);
  const [editedName, setEditedName] = useState(user.name);

  const handleSave = () => {
    onEdit({ ...user, name: editedName });
    setIsEditing(false);
  };

  const handleCancel = () => {
    setIsEditing(false);
  };

  const handleEdit = () => {
    setIsEditing(true);
  };

  return (
    <div className="flex items-center justify-center h-[48rem] p-5">
      <div className="w-full h-full rounded-2xl py-10 px-20  border border-ctp-sky/40">
        <h1 className="text-ctp-sky font-bold italic text-center text-2xl pb-5">
          Looking-Glass
        </h1>
        {isEditing ? (
          <div>
            <input
              className="my-2 w-full bg-zinc-800 text-zinc-200  "
              type="text"
              value={editedName}
              onChange={(e) => setEditedName(e.target.value)}
            />
            <Button text="Save" onClick={handleSave} inverseColors />
            <Button text="Cancel" onClick={handleCancel} inverseColors />
          </div>
        ) : (
          <div className="font-semibold text-xl text-ctp-mauve">
            <p className="my-10">{user.username}</p>
            <p className="my-10">{user.name}</p>
            <p className="my-10">{user.email}</p>
            <Button text="Edit" onClick={handleEdit} inverseColors />
          </div>
        )}
      </div>
    </div>
  );
}

export default UserDetail;
