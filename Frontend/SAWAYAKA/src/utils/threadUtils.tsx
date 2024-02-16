import { getUserRequest } from "../services/userService";

export const transformThread = async (thread: any) => {
  try {
    const { userId } = thread;

    const res = await getUserRequest(userId);

    const userInfo = {
      username: res.data.username,
      name: res.data.name,
    };

    const transformedThread = {
      ...thread,
      userInfo,
    };

    return transformedThread;
  } catch (error) {
    console.error(error);
    return thread;
  }
};
