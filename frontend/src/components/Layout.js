import Head from 'next/head';
import Navbar from './Navbar';

export default function Layout({ children }) {
  return (
    <div className="min-h-screen bg-gray-100">
      <Head>
        <title>Volunteer Management</title>
        <meta name="description" content="Volunteer Management System" />
      </Head>
      <Navbar />
      <main className="container mx-auto p-4">{children}</main>
    </div>
  );
}