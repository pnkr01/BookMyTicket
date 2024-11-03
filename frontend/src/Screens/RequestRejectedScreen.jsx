import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Link } from "react-router-dom";

const RequestRejectedScreen = () => {
  return (
    <div className="my-16 flex items-center justify-center bg-gray-50">
      <Card className="w-full max-w-lg p-6 bg-white shadow-lg rounded-lg border">
        <CardHeader>
          <CardTitle className="text-center text-xl font-bold text-red-500">
            Request Rejected
          </CardTitle>
        </CardHeader>
        <CardContent className="mt-4 text-center">
          <p className="text-gray-600 leading-relaxed">
            <strong>Unfortunately</strong>, your request to be a place owner has
            been rejected by the authorities. Please register again with
            accurate information. For further queries, feel free to reach out at{" "}
            <a
              href="mailto:support@bookMyTicket.com"
              className="text-blue-600 underline"
            >
              support@bookMyTicket.com
            </a>
            .
          </p>
          <div className="mt-6 flex justify-center gap-4 text-white ">
            <Link to="/register-as-owner">
              <Button
                variant="outline"
                className="px-6 py-2 bg-red-600 hover:bg-red-500 hover:text-white"
              >
                Register Again
              </Button>
            </Link>
            <a href="mailto:support@bookMyTicket.com">
              <Button
                variant="outline"
                className="px-6 py-2 bg-indigo-600 hover:bg-indigo-500 hover:text-white"
              >
                Contact Us
              </Button>
            </a>
          </div>
        </CardContent>
      </Card>
    </div>
  );
};

export default RequestRejectedScreen;
