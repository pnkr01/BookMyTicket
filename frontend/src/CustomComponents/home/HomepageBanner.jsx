import React from "react";

const HomepageBanner = () => {
  return (
    <div className="mt-2 flex flex-col md:flex-row justify-center items-center p-6 md:p-12 bg-gradient-to-r from-indigo-200 to-indigo-300 border-b border-indigo-200">
      <div className="flex-1 flex flex-col gap-4 md:gap-2 justify-center tracking-wider text-center md:text-left">
        <h1 className="text-5xl font-bold text-indigo-700 leading-tight font-serif">
          Book the Moments <br className="hidden md:block" /> That Matter
        </h1>
        <div>
          <h2 className="font-semibold text-2xl md:text-3xl text-gray-800">
            Seamless Ticket Booking
          </h2>
          <p className="flex gap-2 text-lg font-medium text-gray-600">
            Made
            <span className="text-indigo-700 font-bold">simple</span>
            with
          </p>
          <h4 className="font-extrabold text-3xl text-indigo-600">
            bookMyTicket
          </h4>
        </div>
        <div className="flex flex-col md:flex-row gap-4 mt-4 md:mt-4 justify-center md:justify-start">
          <button className="font-medium bg-indigo-700 hover:bg-indigo-600 text-white px-4 py-2 rounded-lg shadow-lg transition duration-150 hover:-translate-y-1 hover:scale-110 ease-in-out">
            Book Now
          </button>
          <button className="font-semibold bg-gray-100 hover:bg-gray-200 text-indigo-700 px-4 py-2 rounded-lg shadow-lg transition duration-300 hover:-translate-y-1 hover:scale-110 ease-in-out">
            Login
          </button>
        </div>
      </div>

      <div className="flex-1 flex justify-center items-center mt-8 md:mt-0">
        <img
          src="path/to/caricature-image.jpg" // Replace with your caricature image path
          alt="Event Booking Illustration"
          className="w-72 h-72 md:w-96 md:h-96 object-cover rounded-xl shadow-xl border border-indigo-200"
        />
      </div>
    </div>
  );
};

export default HomepageBanner;
