"use client"; 
import { GoogleMap, Marker, useLoadScript } from '@react-google-maps/api';

const Map = ({ locations }) => {
  const { isLoaded } = useLoadScript({
    googleMapsApiKey: "YOUR_GOOGLE_MAPS_API_KEY",
  });

  if (!isLoaded) return <div>Loading...</div>;

  return (
    <GoogleMap
      zoom={10}
      center={{ lat: 10.762622, lng: 106.660172 }}
      mapContainerClassName="w-full h-96 rounded-lg shadow-md"
    >
      {locations.map((location, index) => (
        <Marker
          key={index}
          position={{ lat: location.latitude, lng: location.longitude }}
        />
      ))}
    </GoogleMap>
  );
};

export default Map;
