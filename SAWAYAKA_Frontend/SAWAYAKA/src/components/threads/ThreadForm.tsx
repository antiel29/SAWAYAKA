import { useForm } from "react-hook-form";
import { useThreads } from "../../context/ThreadContext";
import { useNavigate } from "react-router-dom";
import Button from "../common/Button";

function ThreadForm() {
  const { register, handleSubmit } = useForm();
  const { createThread } = useThreads();
  const navigate = useNavigate();

  const onSubmit = handleSubmit(async (data) => {
    const res = await createThread(data);
    navigate(`/threads/${res.data.id}`);
  });

  const handleKeyDown = (data: any) => {
    if (data.key === "Enter" && !data.shiftKey) {
      onSubmit();
    }
  };
  return (
    <div className="flex items-center justify-center h-[48rem] p-5">
      <div className="w-full h-full rounded-2xl py-10 px-60 ">
        <h1 className=" text-ctp-pink/90 font-bold pb-5">
          It's my Own Invention
        </h1>
        <form onSubmit={onSubmit} onKeyDown={handleKeyDown}>
          <input
            type="text"
            placeholder="Subject"
            {...register("title")}
            className="w-full bg-zinc-800 text-zinc-200 rounded-md px-5 py-3 mb-5"
            autoFocus
          />

          <textarea
            rows={3}
            placeholder="Content"
            {...register("content")}
            className="w-full bg-zinc-800 text-zinc-200 h-64 rounded-md px-5 py-5"
          ></textarea>
          <div className="flex justify-center py-4">
            <Button text="Post" onClick={onSubmit} />
          </div>
        </form>
      </div>
    </div>
  );
}

export default ThreadForm;
