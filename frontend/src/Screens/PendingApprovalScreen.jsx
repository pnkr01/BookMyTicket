import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";

const PendingApprovalScreen = () => {
  return (
    <div className="my-16 flex items-center justify-center bg-gray-50">
      <Card className="w-full max-w-lg p-6 bg-white shadow-lg rounded-lg border">
        <CardHeader>
          <CardTitle className="text-center text-xl font-bold text-gray-700">
            Waiting for Approval
          </CardTitle>
        </CardHeader>
        <CardContent className="mt-4 text-center">
          <p className="text-gray-600 leading-relaxed">
            Your request to be a place owner has been sent to the respective authorities for approval. 
            Thanks for your patience.
          </p>
        </CardContent>
      </Card>
    </div>
  );
};

export default PendingApprovalScreen;
