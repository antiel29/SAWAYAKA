import { useEffect } from "react";
import ThreadDetail from "../components/threads/ThreadDetail";
import { useThreads } from "../context/ThreadContext";
import { useParams } from "react-router-dom";
import { useComment } from "../context/CommentContext";
import CommentForm from "../components/comments/CommentForm";
import CommentList from "../components/comments/CommentList";

function ThreadDetailPage() {
  const { id } = useParams();
  const { thread, getThread } = useThreads();
  const { comments, getCommentInThread } = useComment();

  useEffect(() => {
    getThread(id);
    getCommentInThread(id);
  }, [id]);

  if (!thread || !comments) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <ThreadDetail thread={thread}></ThreadDetail>
      <CommentList comments={comments}></CommentList>
      <CommentForm></CommentForm>
    </div>
  );
}

export default ThreadDetailPage;
