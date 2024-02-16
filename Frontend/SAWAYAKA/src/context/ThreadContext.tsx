import { createContext, useContext, useState } from "react";
import {
  createThreadRequest,
  getThreadsRequest,
  getThreadRequest,
} from "../services/threadService";

type ThreadContextType = {
  thread: any;
  threads: any;
  createThread: (thread: any) => Promise<void> | any;
  getThreads: () => Promise<void>;
  getThread: (id: any) => Promise<void>;
};

const ThreadContext = createContext<ThreadContextType>({
  thread: null,
  threads: [],
  createThread: async () => {},
  getThreads: async () => {},
  getThread: async () => {},
});

export const useThreads = () => {
  const context = useContext(ThreadContext);
  if (!context) {
    throw new Error("useThreads must be used within a ThreadProvider");
  }
  return context;
};

export function ThreadProvider({ children }: any) {
  const [threads, setThreads] = useState([]);
  const [thread, setThread] = useState(null);

  const createThread = async (thread: any) => {
    try {
      const response = await createThreadRequest(thread);
      return response;
    } catch (error) {
      console.log(error);
    }
  };

  const getThreads = async () => {
    try {
      const res = await getThreadsRequest();
      setThreads(res.data);
    } catch (error) {
      console.log(error);
    }
  };

  const getThread = async (id: any) => {
    try {
      const res = await getThreadRequest(id);
      setThread(res.data);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <ThreadContext.Provider
      value={{ thread, threads, createThread, getThreads, getThread }}
    >
      {children}
    </ThreadContext.Provider>
  );
}
