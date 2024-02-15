import { getUserRequest } from "../services/userService";

export const transformComment = async (comment: any) => {
  try {
    const { userId } = comment;

    const res = await getUserRequest(userId);

    const userInfo = {
      username: res.data.username,
      name: res.data.name,
    };

    const transformedComment = {
      ...comment,
      userInfo,
    };

    return transformedComment;
  } catch (error) {
    console.error(error);
    return comment;
  }
};
