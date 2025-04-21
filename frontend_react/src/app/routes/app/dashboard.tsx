import React, { useEffect, useState } from 'react';
import { FaRegUserCircle, FaRegBell } from 'react-icons/fa';
import { FiMenu } from 'react-icons/fi';
import { MdOutlineStars } from 'react-icons/md';
import { GoGear } from 'react-icons/go';
import { FaArrowRightLong } from 'react-icons/fa6';
import { VscCloudDownload } from 'react-icons/vsc';
import axios from 'axios';

interface Listing {
  id: number;
  title: string;
  description: string;
  category: string;
  location: string;
  price: number;
  availability: string[];
  images: string[];
  rating: number;
  businessId: number;
  createdAt: string;
  updatedAt: string;
}

function Dashboard() {
  const [listings, setListings] = useState<Listing[]>([]);

  useEffect(() => {
    const fetchListings = async () => {
      try {
        const response = await axios.get<Listing[]>('http://localhost:8080/listings/get_all');
        setListings(response.data);
        console.log(response.data)
      } catch (error) {
        console.error('Error fetching listings:', error);
      }
    };

    fetchListings();
  }, []);

  const categories = Array.from({ length: 20 }, (_, i) => (
    <div key={i} className="flex-shrink-0 flex flex-col items-center space-y-2">
      <div className="bg-gray-600 p-8 rounded-full">
        <VscCloudDownload className="text-3xl text-white" />
      </div>
      <p className="text-xl">Label {i + 1}</p>
    </div>
  ));

  return (
    <div className="flex flex-row h-screen bg-gray-200 overflow-hidden">
      <div className="h-screen bg-gray-200">
        <div className="mt-24 mx-10 space-y-24">
          <FiMenu className="text-5xl" />
          <div className="space-y-10">
            {Array.from({ length: 4 }).map((_, i) => (
              <div key={i} className="flex flex-col justify-center items-center">
                <MdOutlineStars className="text-5xl" />
                <label className="text-xl">Label</label>
              </div>
            ))}
          </div>
        </div>
      </div>

      <div className="flex flex-col w-full bg-gray-100 rounded-l-4xl shadow-2xl overflow-hidden">
        <div className="flex justify-between w-full px-10 py-6">
          <FaRegUserCircle className="text-5xl" />
          <div className="flex flex-row space-x-10">
            <FaRegBell className="text-5xl" />
            <GoGear className="text-5xl" />
          </div>
        </div>

        <div className="flex flex-col flex-1 overflow-hidden px-4 sm:px-6 md:px-10">
          <div>
            <h1 className="text-5xl mb-6">DreamSellers</h1>
          </div>

          <div className="flex flex-row items-center space-x-4 mb-4">
            <h1 className="text-3xl">Categories</h1>
            <FaArrowRightLong className="text-2xl pt-1" />
          </div>

          <div className="overflow-x-auto w-full mb-8 scrollbar">
            <div className="flex space-x-10 w-max px-2">
              {categories}
            </div>
          </div>

          <div className="flex-1 overflow-y-auto scrollbar mb-10">
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-6 pb-10 mx-2">
              {listings.map((listing) => (
                <div key={listing.id} className="bg-white rounded-xl shadow-md p-4 flex flex-col justify-between hover:shadow-lg transition">
                  {listing.images.length > 0 ? (
                    <img src={listing.images[0]} alt={listing.title} className="h-32 w-full object-fill rounded-md mb-4" />
                  ) : (
                    <div className="h-32 w-full bg-gray-300 rounded-md mb-4 flex items-center justify-center text-gray-500">No Image</div>
                  )}
                  <h2 className="text-xl font-semibold">{listing.title}</h2>
                  <p className="text-gray-600 text-sm">{listing.location}</p>
                  <p className="text-lg font-bold mt-2">{listing.price} $</p>
                  <p className="text-sm text-yellow-500">⭐ {listing.rating ? listing.rating.toFixed(1) : "No rating"}</p>
                </div>
              ))}
            </div>
          </div>

        </div>
      </div>
    </div>
  );
}

export default Dashboard;
