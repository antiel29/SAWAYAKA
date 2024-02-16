import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { transformThread } from "../../utils/threadUtils";
import { formatDistanceToNow } from "date-fns";

interface ThreadListProps {
  threads: any[];
}

function ThreadList({ threads }: ThreadListProps) {
  const [transformedThreads, setTransformedThreads] = useState<any[]>([]);
  const formatDateTime = (dateTimeString: string) => {
    return new Date(dateTimeString).toLocaleString();
  };

  const DateDistanceToNow = (dateTimeString: string) => {
    const dateTime = new Date(dateTimeString);
    return formatDistanceToNow(dateTime, { addSuffix: true });
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const transformedThreads = await Promise.all(
          threads.map(async (thread) => await transformThread(thread))
        );
        transformedThreads.sort(
          (a, b) =>
            new Date(b.lastActivity).getTime() -
            new Date(a.lastActivity).getTime()
        );
        setTransformedThreads(transformedThreads);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, [threads]);

  return (
    <div>
      <div className="flex items-center text-center  text-ctp-blue/70 p-4 font-semibold ">
        <div className="w-1/12 ">By</div>
        <div className="w-1/2 ">Subject</div>
        <div className="w-1/4 ">Date</div>
        <div className="w-1/12 ">Comments</div>

        <div className="w-1/6 ">Last Activity</div>
      </div>
      {transformedThreads.map((transformedThread: any, index: number) => (
        <Link
          key={transformedThread.id}
          to={`/threads/${transformedThread.id}`}
          className={`bg-gradient-to-r from-ctp-base to-ctp-mauve/5 py-2 rounded-md block border-x-2 ${
            index === 0 ? "border-t-2 border-b-2" : "border-b-2"
          } border-ctp-blue/10`}
        >
          <div className="flex items-center italic text-white/90 p-4 hover:bg-ctp-mauve/50 active:bg-ctp-mauve/20">
            <div className="w-1/12 text-center border-r-2 border-ctp-blue/10">
              {transformedThread.userInfo.username}
            </div>
            <div className="w-1/2  text-center border-r-2 border-ctp-blue/10">
              {transformedThread.title}
            </div>
            <div className="w-1/4 text-center border-r-2 border-ctp-blue/10">
              {formatDateTime(transformedThread.creationDateTime)}
            </div>
            <div className="w-1/12 text-center border-r-2 border-ctp-blue/10">
              {transformedThread.commentCount}
            </div>

            <div className="w-1/6 text-center">
              {DateDistanceToNow(transformedThread.lastActivity)}
            </div>
          </div>
        </Link>
      ))}
    </div>
  );
}
export default ThreadList;
