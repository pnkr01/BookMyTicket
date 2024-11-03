import React, { useState } from "react";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const AddPlaceDialog = ({ open, onClose }) => {
  const [openDate, setOpenDate] = useState(new Date());
  const [openTimeFrom, setOpenTimeFrom] = useState("09:00");
  const [openTimeTo, setOpenTimeTo] = useState("18:00");

  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent className="rounded-lg p-8 shadow-lg overflow-auto max-h-[80vh] bg-white">
        <DialogHeader>
          <DialogTitle className="text-2xl font-semibold">
            Add Place
          </DialogTitle>
          {/* <DialogDescription className="text-gray-500">
            Enter place details below.
          </DialogDescription> */}
        </DialogHeader>
        <form className="space-y-4">
          {/* Name Field */}
          <div>
            <label
              htmlFor="name"
              className="block text-sm font-medium text-gray-700"
            >
              Name
            </label>
            <input
              id="name"
              type="text"
              placeholder="Enter place name"
              required
              className="mt-1 block w-full border border-gray-300 p-2 placeholder-gray-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
          </div>

          {/* Location Field */}
          <div>
            <label
              htmlFor="location"
              className="block text-sm font-medium text-gray-700"
            >
              Location
            </label>
            <input
              id="location"
              type="text"
              placeholder="Enter location"
              required
              className="mt-1 block w-full border border-gray-300 p-2 placeholder-gray-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
          </div>

          <div className="flex space-x-4">
            <div className="flex-1">
            <label
              htmlFor="city"
              className="block text-sm font-medium text-gray-700"
            >
              City
            </label>
            <input
              id="city"
              type="text"
              placeholder="Enter city"
              className="mt-1 block w-full border border-gray-300 p-2 placeholder-gray-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
            </div>
            <div className="flex-1">
            <label
              htmlFor="state"
              className="block text-sm font-medium text-gray-700"
            >
              State
            </label>
            <input
              id="state"
              type="text"
              placeholder="Enter State"
              className="mt-1 block w-full border border-gray-300 p-2 placeholder-gray-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
            </div>
          </div>

          {/* Description Field */}
          <div>
            <label
              htmlFor="description"
              className="block text-sm font-medium text-gray-700"
            >
              Description
            </label>
            <textarea
              id="description"
              placeholder="Describe the place"
              required
              className="mt-1 block w-full border border-gray-300 p-2 placeholder-gray-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
          </div>

          {/* Ticket Price Field */}
          <div>
            <label
              htmlFor="ticketPrice"
              className="block text-sm font-medium text-gray-700"
            >
              Ticket Price
            </label>
            <input
              id="ticketPrice"
              type="text"
              placeholder="Enter ticket price"
              className="mt-1 block w-full border border-gray-300 p-2 placeholder-gray-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
          </div>

          {/* Open Date Field */}
          <div>
            <label
              htmlFor="openDate"
              className="block text-sm font-medium text-gray-700"
            >
              Open Date
            </label>
            <DatePicker
              selected={openDate}
              onChange={(date) => setOpenDate(date)}
              dateFormat="MM/dd/yyyy"
              className="mt-1 block w-full border border-gray-300 p-2 placeholder-gray-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
              id="openDate"
            />
          </div>

          {/* Open Timings Fields */}
          <div className="flex space-x-4">
            <div className="flex-1">
              <label
                htmlFor="openTimeFrom"
                className="block text-sm font-medium text-gray-700"
              >
                Open Timing (From)
              </label>
              <input
                aria-label="Time"
                type="time"
                className="mt-1 block w-full border border-gray-300 p-2 placeholder-gray-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
                id="openTimeFrom"
              />
            </div>
            <div className="flex-1">
              <label
                htmlFor="openTimeTo"
                className="block text-sm font-medium text-gray-700"
              >
                Open Timing (To)
              </label>
              <input
                aria-label="Time"
                type="time"
                className="mt-1 block w-full border border-gray-300 p-2 placeholder-gray-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
                id="openTimeTo"
              />
            </div>
          </div>

          {/* Add Pictures Field */}
          <div>
            <label
              htmlFor="pictures"
              className="block text-sm font-medium text-gray-700"
            >
              Add Pictures
            </label>
            <input
              id="pictures"
              type="file"
              multiple
              accept="image/*"
              className="mt-1 block w-full border border-gray-300 p-2 placeholder-gray-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
          </div>

          {/* Submit Button */}
          <DialogFooter className="mt-6">
            <Button
              type="submit"
              className="w-full bg-indigo-600 text-white hover:bg-indigo-500 p-3 rounded-lg font-semibold"
            >
              Add Place
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
};

export default AddPlaceDialog;
