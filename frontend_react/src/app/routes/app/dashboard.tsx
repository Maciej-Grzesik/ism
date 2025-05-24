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

const mapAvailabilityToSchema = (availabilityStatus: string): string | null => {
  const lowerStatus = availabilityStatus.toLowerCase().replace(/\s+/g, '');
  const availabilityMap: { [key: string]: string } = {
    'instock': 'http://schema.org/InStock',
    'outofstock': 'http://schema.org/OutOfStock',
    'preorder': 'http://schema.org/PreOrder',
    'presale': 'http://schema.org/PreSale',
    'onlineonly': 'http://schema.org/OnlineOnly',
    'instoreonly': 'http://schema.org/InStoreOnly',
    'limitedavailability': 'http://schema.org/LimitedAvailability',
    'discontinued': 'http://schema.org/Discontinued',
    'soldout': 'http://schema.org/SoldOut',
  };
  return availabilityMap[lowerStatus] || 'http://schema.org/InStock';
};

const generateProductJsonLd = (listing: Listing): object => {
  const productSchema: any = {
    "@type": "Product",
    "productID": String(listing.id),
    "name": listing.title,
    "description": listing.description,
    "image": listing.images.length > 0 ? listing.images[0] : undefined,
    "category": listing.category,
    "dateCreated": listing.createdAt,
    "dateModified": listing.updatedAt,
    "offers": {
      "@type": "Offer",
      "priceCurrency": "PLN",
      "price": String(listing.price),
      "availability": listing.availability && listing.availability.length > 0
                      ? mapAvailabilityToSchema(listing.availability[0])
                      : "http://schema.org/InStock",
      "url": typeof window !== 'undefined' ? `${window.location.href.split('#')[0]}#product-${listing.id}` : `#product-${listing.id}`,
      "seller": {
        "@type": "Organization",
        "name": `Sprzedawca ${listing.businessId}`
      }
    }
  };

  if (listing.rating) {
    productSchema.aggregateRating = {
      "@type": "AggregateRating",
      "ratingValue": String(listing.rating.toFixed(1)),
      "ratingCount": "1"
    };
  }

  if (listing.location) {
    productSchema.offers.areaServed = {
        "@type": "Place",
        "name": listing.location
    };
  }
  
  Object.keys(productSchema).forEach(key => productSchema[key] === undefined && delete productSchema[key]);
  if (productSchema.offers) {
    Object.keys(productSchema.offers).forEach(key => productSchema.offers[key] === undefined && delete productSchema.offers[key]);
  }

  return productSchema;
};

const generatePageJsonLd = (listingsData: Listing[]): object[] => {
  const pageUrl = typeof window !== 'undefined' ? window.location.href.split('#')[0] : "https://example.com/dreamsellers";

  const websiteSchema = {
    "@context": "http://schema.org",
    "@type": "WebSite",
    "name": "DreamSellers",
    "url": pageUrl,
  };

  const itemListSchema = {
    "@context": "http://schema.org",
    "@type": "ItemList",
    "name": "Oferty DreamSellers",
    "description": "Lista aktualnych ofert w serwisie DreamSellers.",
    "numberOfItems": listingsData.length,
    "itemListElement": listingsData.map((listing, index) => ({
      "@type": "ListItem",
      "position": index + 1,
      "item": generateProductJsonLd(listing)
    }))
  };

  return [websiteSchema, itemListSchema];
};


function Dashboard() {
  const [listings, setListings] = useState<Listing[]>([]);

  useEffect(() => {
    const fetchListings = async () => {
      try {
        const response = await axios.get<Listing[]>('http://localhost:8080/listings/get_all');
        const listingsWithDescription = response.data.slice(0, 3);
        setListings(listingsWithDescription);
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

  const pageUrl = typeof window !== 'undefined' ? window.location.href.split('#')[0] : "https://example.com/dreamsellers";

  return (
    <div className="flex flex-row h-screen bg-gray-200 overflow-hidden" vocab="http://schema.org/" prefix="schema: http://schema.org/">
      
      {listings.length > 0 && (
        <script
          type="application/ld+json"
          dangerouslySetInnerHTML={{ __html: JSON.stringify(generatePageJsonLd(listings), null, 2) }}
        />
      )}

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

      {/* Główna zawartość strony */}
      {/* Oznaczamy tę sekcję jako WebSite dla RDFa */}
      <div className="flex flex-col w-full bg-gray-100 rounded-l-4xl shadow-2xl overflow-hidden" typeof="schema:WebSite">
        <link property="schema:url" href={pageUrl} /> {/* URL strony dla RDFa */}
        
        <div className="flex justify-between w-full px-10 py-6">
          <FaRegUserCircle className="text-5xl" />
          <div className="flex flex-row space-x-10">
            <FaRegBell className="text-5xl" />
            <GoGear className="text-5xl" />
          </div>
        </div>

        <div className="flex flex-col flex-1 overflow-hidden px-4 sm:px-6 md:px-10">
          <div>
            {/* Nazwa strony dla RDFa */}
            <h1 property="schema:name" className="text-5xl mb-6">DreamSellers</h1>
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
              {listings.map((listing, index) => (
            
                <div key={listing.id} id={`product-${listing.id}`} className="bg-white rounded-xl shadow-md p-4 flex flex-col justify-between hover:shadow-lg transition" typeof="schema:Product">
                 
                  <meta property="schema:productID" content={String(listing.id)} />
                  <meta property="schema:description" content={listing.description} />
                  <meta property="schema:category" content={listing.category} />
                  <meta property="schema:dateCreated" content={listing.createdAt} />
                  <meta property="schema:dateModified" content={listing.updatedAt} />
                 

                  {listing.images.length > 0 ? (
                    <img property="schema:image" src={listing.images[0]} alt={listing.title} className="h-32 w-full object-fill rounded-md mb-4" />
                  ) : (
                    <div className="h-32 w-full bg-gray-300 rounded-md mb-4 flex items-center justify-center text-gray-500">No Image</div>
                  )}
                  
                  <h2 property="schema:name" className="text-xl font-semibold">{listing.title}</h2>

                 
                  <div property="schema:offers" typeof="schema:Offer">
                    <link property="schema:url" href={`${pageUrl}#product-${listing.id}`} /> {/* URL oferty */}
                    
                   
                    <div property="schema:seller" typeof="schema:Organization">
                        <meta property="schema:name" content={`Sprzedawca ${listing.businessId}`} />
                    </div>

                  
                    <div property="schema:areaServed" typeof="schema:Place" className="text-gray-600 text-sm">
                      <span property="schema:name">{listing.location}</span>
                    </div>
                    
                    <p className="text-lg font-bold mt-2">
                      <span property="schema:price" content={String(listing.price)}>{listing.price}</span>
                      {/* Waluta, np. PLN. Upewnij się, że content jest poprawnym kodem waluty ISO 4217 */}
                      <span property="schema:priceCurrency" content="PLN"> zł</span> {/* ZMIEŃ NA ODPOWIEDNIĄ WALUTĘ */}
                    </p>

                    {/* Dostępność */}
                    {(() => {
                        const schemaAvailabilityUrl = listing.availability && listing.availability.length > 0
                                                      ? mapAvailabilityToSchema(listing.availability[0])
                                                      : 'http://schema.org/InStock';
                        if (schemaAvailabilityUrl) {
                            return <link property="schema:availability" href={schemaAvailabilityUrl} />;
                        }
                        return null;
                    })()}
                  </div>

                  {/* Ocena produktu (RDFa) */}
                  {listing.rating ? (
                    <div property="schema:aggregateRating" typeof="schema:AggregateRating" className="text-sm text-yellow-500">
                       ⭐ <span property="schema:ratingValue">{listing.rating.toFixed(1)}</span>
                       {/* Założenie: jeśli jest rating, to jest przynajmniej jedna ocena. Dostosuj jeśli masz dane. */}
                       <meta property="schema:ratingCount" content="1" /> 
                    </div>
                  ) : (
                    <p className="text-sm text-gray-400">No rating</p>
                  )}
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