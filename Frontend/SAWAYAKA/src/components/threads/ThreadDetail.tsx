import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import { transformThread } from "../../utils/threadUtils";
interface ThreadDetailProps {
  thread: any;
}

function ThreadDetail({ thread }: ThreadDetailProps) {
  const [transformedThread, setTransformedThread] = useState<any>(null);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const transformedThread = await transformThread(thread);
        setTransformedThread(transformedThread);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, [thread]);
  const formatDateTime = (dateTimeString: string) => {
    return new Date(dateTimeString).toLocaleString();
  };

  if (!transformedThread) {
    return null;
  }

  return (
    <div>
      <h2 className="text-3xl text-ctp-lavender/90 font-bold mb-4">
        {transformedThread.title}
      </h2>
      <div className="border border-ctp-blue/60 bg-gradient-to-r from-ctp-base to-ctp-blue/5 p-4 rounded-xl  mb-1">
        <div className="mb-4  text-ctp-lavender/80">
          1:{transformedThread.userInfo.name}:
          {formatDateTime(transformedThread.creationDateTime)} ID:
          {transformedThread.userInfo.username}
        </div>
        <p className=" whitespace-pre-line text-zinc-200 ">
          {transformedThread.content}
        </p>
      </div>
    </div>
  );
}
export default ThreadDetail;
