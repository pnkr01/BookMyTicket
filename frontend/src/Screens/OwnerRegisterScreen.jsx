import React from "react";

const OwnerRegisterScreen = () => {
  return (
    <div className="-mt-6 max-w-5xl mx-auto flex justify-center items-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-lg w-full border border-gray-300">
        <h1 className="text-xl font-semibold text-center mb-6">
          Register yourself as Place Owner
        </h1>

        <form className="space-y-4">
          {/* Name Field */}
          <div>
            <label htmlFor="name" className="text-sm font-medium">
              Name
            </label>
            <input
              type="text"
              id="name"
              className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
              placeholder="Enter your name"
            />
          </div>

          {/* Email Field */}
          <div className="flex space-x-4">
            <div className="flex-1">
              <label htmlFor="email" className="text-sm font-medium">
                Email
              </label>
              <input
                type="email"
                id="email"
                className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                placeholder="Email"
              />
            </div>

            {/* Phone Number Field */}
            <div className="flex-1">
              <label htmlFor="phone" className="text-sm font-medium">
                Phone Number
              </label>
              <input
                type="tel"
                id="phone"
                className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                placeholder="Phone number"
              />
            </div>
          </div>

          {/* Address Field */}
          <div>
            <label htmlFor="address" className="text-sm font-medium">
              Local Address
            </label>
            <textarea
              id="address"
              rows="3"
              className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
              placeholder="Enter your address"
            />
          </div>

          {/* City and State Fields */}
          <div className="flex space-x-4">
            <div className="flex-1">
              <label htmlFor="city" className="text-sm font-medium">
                City
              </label>
              <input
                type="text"
                id="city"
                className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                placeholder="City"
              />
            </div>

            <div className="flex-1">
              <label htmlFor="state" className="text-sm font-medium">
                State
              </label>
              <input
                type="text"
                id="state"
                className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                placeholder="State"
              />
            </div>
          </div>

          {/* Register Button */}
          <div className="flex justify-center">
            <button
              type="submit"
              className="mt-4 w-full px-6 py-2 bg-indigo-600 text-white font-semibold rounded hover:bg-indigo-500 focus:outline-none"
            >
              Register
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default OwnerRegisterScreen;
