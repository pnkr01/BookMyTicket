import { FaFacebookF, FaXTwitter } from "react-icons/fa6";

const ShareComponent = () => {
  return (
    <div className="bg-gray-50 p-4 rounded-lg shadow-lg max-h-max">
      <h4 className="text-gray-500">Share this event</h4>
      <div className="flex gap-4 mt-2">
        <FaFacebookF />
        <FaXTwitter />
      </div>
    </div>
  );
};

export default ShareComponent;
