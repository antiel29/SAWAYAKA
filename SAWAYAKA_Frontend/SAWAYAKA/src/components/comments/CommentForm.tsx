import { useForm } from "react-hook-form";
import { useComment } from "../../context/CommentContext";
import { useLocation } from "react-router-dom";
import Button from "../common/Button";

function CommentForm() {
  const { register, handleSubmit, reset } = useForm();
  const { createComment } = useComment();
  const location = useLocation();
  const threadId = Number(location.pathname.split("/")[2]);

  const onSubmit = handleSubmit(async (data) => {
    const commentData = {
      threadId: threadId,
      ...data,
    };
    await createComment(commentData);
    reset();
  });

  const handleKeyDown = (data: any) => {
    if (data.key === "Enter" && !data.shiftKey) {
      onSubmit();
    }
  };

  return (
    <div className="border border-ctp-blue/20 bg-gradient-to-r from-ctp-base to-ctp-blue/5 p-4 rounded-xl  mb-1 ">
      <form onSubmit={onSubmit} onKeyDown={handleKeyDown}>
        <textarea
          rows={3}
          placeholder="Write something..."
          {...register("content")}
          className="w-full bg-zinc-800 text-zinc-200 px-4 py-2 rounded-md my-2"
        ></textarea>
        <div className="flex justify-end">
          <Button text="Comment" onClick={onSubmit} inverseColors />
        </div>
      </form>
    </div>
  );
}

export default CommentForm;
