import { useEffect } from "react";
import ThreadList from "../components/threads/ThreadList";
import { useThreads } from "../context/ThreadContext";

function HomePage() {
  const { threads, getThreads } = useThreads();

  useEffect(() => {
    getThreads();
  }, []);

  return (
    <div>
      <h1 className="text-4xl text-ctp-lavender/90 font-bold text-center py-5">
        Kita High message board
      </h1>

      <div>
        <ThreadList threads={threads} />
      </div>
    </div>
  );
}

export default HomePage;
