import { FaLocationDot } from "react-icons/fa6";

const PlaceBanner = () => {
  const bannerImageUrl =
    "https://assets-in.bmscdn.com/nmcms/events/banner/desktop/media-desktop-chaman-chatora-gaurav-kapoor-s-comedy-special-0-2024-5-29-t-13-6-22.jpg"; // Replace with the actual image URL
  return (
    <div className="flex flex-col">
      <div className="relative h-96 w-full">
        <div
          className="absolute inset-0 bg-cover bg-center filter blur-md h-96"
          style={{
            backgroundImage: `url(${bannerImageUrl})`,
          }}
        />

        <div className="absolute inset-0 bg-black opacity-40 h-96"></div>

        <img
          src={bannerImageUrl}
          alt="Event Banner"
          className="absolute w-full h-96 object-scale-down"
        />
      </div>
      <div className="p-4 shadow-md">
        <div className="flex justify-between items-center">
          <div className="flex flex-col gap-2">
            <h1 className="text-lg md:text-xl font-bold">
              CHAMAN CHATORA - Gaurav Kapoor's Comedy Special
            </h1>
            <p className="text-gray-600 text-sm">
              Comedy | Hindi, English | 18yrs + | 1hr
            </p>
            <div className="flex gap-4 items-center text-sm">
              <p className="text-gray-700">Sat 23 Nov 2024 - Sun 24 Nov 2024</p>
              <p className="flex items-center gap-2 text-gray-700 border-r-2 px-6">
                <FaLocationDot className="text-yellow-600" /> Multiple Venues
              </p>
              <div className="text-red-600 font-semibold">₹ 499 onwards</div>
            </div>
          </div>

          <button className="font-medium bg-red-600 hover:bg-red-500 text-white px-6 py-3 rounded-lg shadow-lg">
            Book
          </button>
        </div>
      </div>
    </div>
  );
};

export default PlaceBanner;
