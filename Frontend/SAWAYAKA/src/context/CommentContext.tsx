import { createContext, useState, useContext } from "react";
import {
  createCommentRequest,
  getCommentInThreadRequest,
} from "../services/commentService";

type CommentContextType = {
  getCommentInThread: (id: any) => Promise<void>;
  createComment: (comment: any) => Promise<void> | any;
  comments: any;
};

export const CommentContext = createContext<CommentContextType>({
  getCommentInThread: async () => {},
  createComment: async () => {},
  comments: [],
});

export const useComment = () => {
  const context = useContext(CommentContext);
  if (!context) {
    throw new Error("useComment must be used within a Comment Provider");
  }
  return context;
};

export const CommentProvider = ({ children }: any) => {
  const [comments, setComments] = useState<any>([]);

  const createComment = async (comment: any) => {
    try {
      const response = await createCommentRequest(comment);
      setComments([...comments, response.data]);
      return response;
    } catch (error) {
      console.log(error);
    }
  };

  const getCommentInThread = async (id: any) => {
    try {
      const res = await getCommentInThreadRequest(id);
      setComments(res.data);
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <CommentContext.Provider
      value={{
        getCommentInThread,
        createComment,
        comments,
      }}
    >
      {children}
    </CommentContext.Provider>
  );
};
