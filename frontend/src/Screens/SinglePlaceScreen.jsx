import React from "react";
import PlaceBanner from "@/CustomComponents/singlePlace/PlaceBanner";
import ShareComponent from "@/CustomComponents/singlePlace/ShareComponent";
import PlaceDescription from "@/CustomComponents/singlePlace/PlaceDescription";
import PlaceLocation from "@/CustomComponents/singlePlace/PlaceLocation";

const SinglePlaceScreen = () => {
  return (
    <div className="bg-gray-100 min-h-screen max-w-5xl mx-auto p-4 md:p-8">
      <div className="bg-white shadow-lg rounded-lg overflow-hidden">
        <PlaceBanner />
      </div>
      <div>
        <div className="grid grid-cols-4 gap-6 mt-6">
          <ShareComponent />
          <div className="col-span-2">
            <PlaceDescription />
          </div>
          <div className="col-span-1">
            <PlaceLocation />
          </div>
        </div>
      </div>
    </div>
  );
};

export default SinglePlaceScreen;
