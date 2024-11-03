import React from "react";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel";

// Data for event categories
const eventCategories = [
  {
    title: "Workshops & More",
    events: "15+ Events",
    bgColor: "bg-gradient-to-br from-red-400 to-pink-500",
    imageUrl: "/images/workshop.jpg", // Add relevant images here
  },
  {
    title: "Kids",
    events: "6 Events",
    bgColor: "bg-gradient-to-br from-blue-300 to-blue-500",
    imageUrl: "/images/kids.jpg",
  },
  {
    title: "Comedy Shows",
    events: "45+ Events",
    bgColor: "bg-gradient-to-br from-purple-500 to-pink-600",
    imageUrl: "/images/comedy.jpg",
  },
  {
    title: "Music Shows",
    events: "10+ Events",
    bgColor: "bg-gradient-to-br from-indigo-400 to-blue-600",
    imageUrl: "/images/music.jpg",
  },
  {
    title: "Upskill & Training",
    events: "3 Events",
    bgColor: "bg-gradient-to-br from-orange-400 to-red-500",
    imageUrl: "/images/upskill.jpg",
  },
];

const EventCategoryCard = ({ title, events, bgColor, imageUrl }) => (
  <CarouselItem className="md:basis-1/3 lg:basis-1/4">
    <div
      className={`relative w-60 h-80 rounded-lg overflow-hidden shadow-lg ${bgColor} text-white`}
    >
      {/* Background image */}
      <img
        src={imageUrl}
        alt={title}
        className="absolute inset-0 w-full h-full object-cover opacity-60"
      />

      {/* Overlay and text */}
      <div className="relative z-10 flex flex-col justify-end p-4 h-full backdrop-filter backdrop-brightness-50">
        <h3 className="text-2xl font-bold mb-1">{title}</h3>
        <p className="text-lg">{events}</p>
      </div>
    </div>
  </CarouselItem>
);

const EventCategories = () => {
  return (
    <div className="py-10 px-4 md:px-12 lg:px-24">
      <h2 className="text-3xl font-bold mb-6 text-gray-800">
        The Best Of Live Events
      </h2>
      <Carousel
        opts={{
          align: "start",
        }}
        className="w-full max-w-7xl"
      >
        <CarouselContent>
          {/* <div className="flex gap-4 overflow-x-auto pb-4"> */}
          {eventCategories.map((category, index) => (
            <EventCategoryCard
              key={index}
              title={category.title}
              events={category.events}
              bgColor={category.bgColor}
              imageUrl={category.imageUrl}
            />
          ))}
          {/* </div> */}
        </CarouselContent>
        <CarouselPrevious className="mx-2" />
        <CarouselNext className="mx-2" />
      </Carousel>
    </div>
  );
};

export default EventCategories;
