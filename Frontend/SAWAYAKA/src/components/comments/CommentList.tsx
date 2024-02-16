import { useEffect, useState } from "react";
import { transformComment } from "../../utils/commentUtils";
interface CommentListProps {
  comments: any[];
}

function CommentList({ comments }: CommentListProps) {
  const [transformedComments, setTransformedComments] = useState<any[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const transformedComments = await Promise.all(
          comments.map(async (comment) => await transformComment(comment))
        );
        setTransformedComments(transformedComments);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, [comments]);

  const formatDateTime = (dateTimeString: string) => {
    return new Date(dateTimeString).toLocaleString();
  };
  return (
    <div>
      {transformedComments.map((transformedComments: any, index: number) => (
        <div
          key={transformedComments.id}
          className="border border-ctp-blue/20 bg-gradient-to-r from-ctp-base to-ctp-blue/5 p-4 rounded-xl  mb-1 "
        >
          <div className=" mb-5  text-ctp-lavender/80">
            {index + 2}:{transformedComments.userInfo?.name}:
            {formatDateTime(transformedComments.creationDateTime)} ID:
            {transformedComments.userInfo?.username}
          </div>
          <p className="text-gray-200 whitespace-pre-line">
            {transformedComments.content}
          </p>
        </div>
      ))}
    </div>
  );
}
export default CommentList;
