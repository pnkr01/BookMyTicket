import React from 'react';
import { FaPhoneAlt, FaMapMarkerAlt, FaEnvelope, FaFacebook, FaTwitter, FaInstagram, FaYoutube } from 'react-icons/fa';

const Footer = () => {
  return (
    <footer className="bg-blue-900 text-white py-10 px-5 md:px-20">
      <div className="max-w-7xl mx-auto flex flex-col md:flex-row justify-between gap-10">
        
        <div className="flex flex-col items-start space-y-4">
          <h1 className="text-2xl font-bold text-white flex items-center gap-2">
            <img src="/logo.png" alt="bookMyEvent Logo" className="w-8 h-8" /> 
            bookMyTicket
          </h1>
          <p className="text-sm text-blue-200">Multivendor Event</p>
          <div className="space-y-2 text-blue-200">
            <div className="flex items-center gap-2">
              <FaPhoneAlt /> <span>(123) 345-6789</span>
            </div>
            <div className="flex items-center gap-2">
              <FaMapMarkerAlt /> <span>518-520 5th Ave, New York, USA</span>
            </div>
            <div className="flex items-center gap-2">
              <FaEnvelope /> <span>support@infodesign.net</span>
            </div>
          </div>
          <p className="text-blue-200 pt-4">Follow our social media</p>
          <div className="flex items-center space-x-4 text-blue-200">
            <FaFacebook className="hover:text-white cursor-pointer" />
            <FaTwitter className="hover:text-white cursor-pointer" />
            <FaInstagram className="hover:text-white cursor-pointer" />
            <FaYoutube className="hover:text-white cursor-pointer" />
          </div>
        </div>

        {/* Useful Links */}
        <div className="text-blue-200">
          <h2 className="text-lg font-semibold text-white border-b-2 border-blue-500 mb-3">Useful Links</h2>
          <ul className="space-y-2">
            <li>About Us</li>
            <li>Contact Us</li>
            <li>Our Team</li>
            <li>FAQ</li>
            <li>Privacy Policy</li>
          </ul>
        </div>

        {/* Categories */}
        <div className="text-blue-200">
          <h2 className="text-lg font-semibold text-white border-b-2 border-blue-500 mb-3">Categories</h2>
          <ul className="space-y-2">
            <li>Live Music</li>
            <li>Visual Arts</li>
            <li>Holiday</li>
            <li>Health</li>
            <li>Hobbies</li>
          </ul>
        </div>

        {/* Locations */}
        <div className="text-blue-200">
          <h2 className="text-lg font-semibold text-white border-b-2 border-blue-500 mb-3">Locations</h2>
          <ul className="space-y-2">
            <li>London</li>
            <li>Paris</li>
            <li>Barcelona</li>
            <li>Berlin</li>
            <li>Dublin</li>
          </ul>
        </div>

        {/* Newest Events */}
        <div className="text-blue-200">
          <h2 className="text-lg font-semibold text-white border-b-2 border-blue-500 mb-3">Newest Event</h2>
          <ul className="space-y-3">
            <li className="flex items-center gap-3">
              <img src="/images/event1.jpg" alt="Event 1" className="w-10 h-10 rounded-md" /> {/* Add image path */}
              <div>
                <p className="font-bold text-white">30 Minute Meditation</p>
                <p className="text-sm">Free Event</p>
              </div>
            </li>
            <li className="flex items-center gap-3">
              <img src="/images/event2.jpg" alt="Event 2" className="w-10 h-10 rounded-md" />
              <div>
                <p className="font-bold text-white">Global Education Expo</p>
                <p className="text-sm">Ticket Price: $25</p>
              </div>
            </li>
            <li className="flex items-center gap-3">
              <img src="/images/event3.jpg" alt="Event 3" className="w-10 h-10 rounded-md" />
              <div>
                <p className="font-bold text-white">Free Career Empowerment</p>
                <p className="text-sm">Free Event</p>
              </div>
            </li>
          </ul>
        </div>

      </div>

      {/* Footer Bottom */}
      <div className="border-t border-blue-700 mt-10 pt-5 text-center text-sm text-blue-200">
        © 2023 bookMyTicket multi-events. All rights reserved.
      </div>
    </footer>
  );
};

export default Footer;
