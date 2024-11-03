import React, { useState } from "react";
import { FaPlus } from "react-icons/fa6";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
} from "@/Components/ui/carousel";
import Autoplay from "embla-carousel-autoplay"
import AddPlaceDialog from "@/CustomComponents/ownerDashboard/AddPlaceDialog";

const PlaceCard = () => {
  return (
    <div className="flex items-center p-4 m-4 bg-white border border-gray-300 rounded-lg shadow-md">
      {/* Carousel */}
      <div className="w-1/4">
        <Carousel
          className="w-full h-40 rounded-lg overflow-hidden"
          plugins={[
            Autoplay({
              delay: 2000,
            }),
          ]}
        >
          <CarouselContent>
            {/* Replace the src attributes with your image URLs */}
            <CarouselItem>
              <img
                src="https://assets-in.bmscdn.com/discovery-catalog/events/tr:w-400,h-600,bg-CCCCCC:w-400.0,h-660.0,cm-pad_resize,bg-000000,fo-top:l-text,ie-VHVlLCAzMSBEZWM%3D,fs-29,co-FFFFFF,ly-612,lx-24,pa-8_0_0_0,l-end/et00412615-jrjhmwxzqr-portrait.jpg"
                alt="Slide 1"
                className="object-scale-down w-full h-full"
              />
            </CarouselItem>
            <CarouselItem>
              <img
                src="https://assets-in.bmscdn.com/discovery-catalog/events/tr:w-400,h-600,bg-CCCCCC:w-400.0,h-660.0,cm-pad_resize,bg-000000,fo-top:l-image,i-discovery-catalog@@icons@@star-icon-202203010609.png,lx-24,ly-615,w-29,l-end:l-text,ie-Ni42LzEwICAxOC4ySyBWb3Rlcw%3D%3D,fs-29,co-FFFFFF,ly-612,lx-70,pa-8_0_0_0,l-end/et00353996-qhqgafrfxu-portrait.jpg"
                alt="Slide 2"
                className="object-cover w-full h-full"
              />
            </CarouselItem>
            <CarouselItem>
              <img
                src="https://assets-in.bmscdn.com/discovery-catalog/events/tr:w-400,h-600,bg-CCCCCC:w-400.0,h-660.0,cm-pad_resize,bg-000000,fo-top:l-image,i-discovery-catalog@@icons@@star-icon-202203010609.png,lx-24,ly-615,w-29,l-end:l-text,ie-Ny40LzEwICAxNy40SyBWb3Rlcw%3D%3D,fs-29,co-FFFFFF,ly-612,lx-70,pa-8_0_0_0,l-end/et00354858-ayxrjajhcy-portrait.jpg"
                alt="Slide 3"
                className="object-cover w-full h-full"
              />
            </CarouselItem>
          </CarouselContent>
        </Carousel>
      </div>

      {/* Event Details */}
      <div className="flex-1 mx-4">
        <h2 className="text-lg font-semibold">Place: Venue Name</h2>
        <p className="text-gray-600">Address</p>
        <p className="text-gray-600">Date & Time: 10th Oct, 6:00 PM</p>
      </div>

      {/* Price */}
      <div className="text-lg font-semibold text-gray-800">$500</div>
    </div>
  );
};

const PlaceOwnerDashboard = () => {
  const [isAddPlaceDialogOpen, setAddPlaceDialogOpen] = useState(false);

  const handleAddPlaceOpen = () => setAddPlaceDialogOpen(true);
  const handleAddPlaceClose = () => setAddPlaceDialogOpen(false);
  return (
    <>
      <div className="max-w-7xl mx-auto flex justify-center items-center my-8 bg-gray-100">
        <div className="bg-white p-2 rounded-lg shadow-lg w-full border border-gray-300">
          <div className="flex justify-between p-4">
            <h1 className="text-2xl font-bold">Listed Places</h1>
            <button
              className="px-4 py-2 bg-indigo-700 text-white font-medium rounded-md flex items-center space-x-2"
              onClick={handleAddPlaceOpen}
            >
              <FaPlus />
              <span>Add Place</span>
            </button>
          </div>

          <PlaceCard />

          <AddPlaceDialog
            open={isAddPlaceDialogOpen}
            onClose={handleAddPlaceClose}
          />
        </div>
      </div>
    </>
  );
};

export default PlaceOwnerDashboard;
