import EventCategories from "@/CustomComponents/home/EventCategories";
import HomepageBanner from "@/CustomComponents/home/HomepageBanner";
import Listing from "@/CustomComponents/home/Listing";
import React from "react";

const HomeScreen = () => {
  return (
    <div className="flex flex-col gap-4">
      <HomepageBanner />
      <div className="flex flex-col gap-4">
        <Listing />
        <Listing />
      </div>
      <EventCategories />
    </div>
  );
};

export default HomeScreen;
